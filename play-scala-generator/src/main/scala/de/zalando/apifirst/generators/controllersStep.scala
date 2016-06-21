package de.zalando.apifirst.generators

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.Http.{ ApplicationFormUrlEncoded, MultipartFormData }
import de.zalando.apifirst.Security.{ Constraint, OAuth2Constraint }
import de.zalando.apifirst.{ Http, ParameterPlace }
import de.zalando.apifirst.ScalaName._
import de.zalando.apifirst.StringUtil._
import de.zalando.apifirst.generators.DenotationNames._
import de.zalando.apifirst.naming.Reference

/**
 * @author slasch
 * @since 31.12.2015.
 */
trait CallControllersStep extends EnrichmentStep[ApiCall]
    with ControllersCommons with SecurityCommons with ActionResults with ParameterData {

  override def steps: Seq[SingleStep] = controllers +: super.steps

  def providedWriterFactories: Set[String]

  val nameMapping = Map(
    "action" -> controllersSuffix,
    "parser_name" -> "Parser",
    "action_type" -> "ActionType",
    "action_result_type" -> "ActionResultType",
    "action_success_status_name" -> "ActionSuccessStatus",
    "action_request_type" -> "ActionRequestType",
    "action_constructor" -> "ActionConstructor",
    "response_mime_type_name" -> "ResponseMimeType",
    "request" -> "Request",
    "method" -> "",
    "form_parser_name" -> formParserSuffix
  )

  /**
   * Puts controllers related information into the denotation table
   *
   * @return
   */
  protected val controllers: SingleStep = call => table =>
    Map(
      "controller" -> controllerProps(call._1, call._2)(table),
      "form_data" -> formDataProps(call._1, call._2)(table)
    )

  private def formDataProps(ref: Reference, call: ApiCall)(implicit table: DenotationTable) = {
    val formParams = validationsByType(call, p => p.place == ParameterPlace.FORM)
    Map(
      "name" -> nameWithSuffix(call, formParserSuffix),
      "support_url_encoded" -> call.mimeIn.find(_ == ApplicationFormUrlEncoded),
      "support_multipart_form_data" -> call.mimeIn.find(_ == MultipartFormData),
      "form_parameters" -> formParams
    )

  }

  private def controllerProps(ref: Reference, call: ApiCall)(implicit table: DenotationTable) = {
    val bodyParam = validationsByType(call, p => p.place == ParameterPlace.BODY)
    val headerParams = validationsByType(call, p => p.place == ParameterPlace.HEADER)
    val nonBodyParams = validationsByType(call, p => p.place != ParameterPlace.BODY && p.place != ParameterPlace.HEADER && p.place != ParameterPlace.FORM)
    val formParams = validationsByType(call, p => p.place == ParameterPlace.FORM)

    val allValidations = callValidations(ref).asInstanceOf[Seq[_]]

    val actionErrorMappings = errorMappings(call)
    val nameParamPair = singleOrMultipleParameters(call)(table)

    val customWriters = needsCustom(call.mimeOut)
    val customReaders = bodyParam.nonEmpty && needsCustom(call.mimeIn)

    val nameMappings = nameMapping map { case (k, v) => k -> nameWithSuffix(call, v) }

    val action = nameMappings("action")

    val (allActionResults, defaultResultType) = actionResults(call)(table)

    val (nonEmptyActionResults, nullActionResults) = allActionResults.partition(_("type") != "Null")

    Map(
      "full_result_types" -> nonEmptyActionResults,
      "empty_result_types" -> nullActionResults,
      "default_result_type" -> defaultResultType,
      "end_comment" -> endComment(call),
      "start_comment" -> startComment(call),

      "process_valid_request" -> prepend("processValid", nameMappings("request")),
      "error_to_status" -> prepend("errorToStatus", nameMappings("method")),

      "error_mappings" -> actionErrorMappings,
      "validations" -> allValidations,
      "body_param" -> bodyParam,
      "non_body_params" -> nonBodyParams,
      "header_params" -> headerParams,

      "produces" -> mimeTypes2StringList(call.mimeOut),
      "consumes" -> mimeTypes2StringList(call.mimeIn),

      "needs_custom_writers" -> customWriters,
      "needs_custom_readers" -> customReaders,
      "has_no_validations" -> allValidations.isEmpty,
      "has_no_error_mappings" -> actionErrorMappings.isEmpty,
      "form_parameters" -> formParams,
      "result_class_prefix" -> escape(capitalize("$", call.handler.method))
    ) ++ nameMappings ++ nameParamPair ++ securityData(call)
  }

  private def securityData(call: ApiCall)(implicit table: DenotationTable): Map[String, Any] = {
    val checks = securityChecks(call.security.toSeq)
    val allParams = checks.flatMap(_.apply("params").asInstanceOf[Iterable[String]])
    Map(
      "needs_security" -> call.security.nonEmpty,
      "secure_action" -> nameWithSuffix(call, "SecureAction"),
      "security_checks" -> checks,
      "security_instance" -> allParams.nonEmpty,
      "security_params" -> securityParams(call.security)
    )
  }

  private def securityChecks(security: Seq[Constraint]) =
    security.map(s => Map("name" -> securityCheck(s.name), "params" -> securityParamValue(s)))

  override def actionResults(call: ApiCall)(table: DenotationTable): (Seq[Map[String, Any]], Option[String]) = {
    val resultTypes = call.resultTypes.results.toSeq map {
      case (code, ref) =>
        Map("code" -> code, "type" -> singleResultType(table)(ref))
    }
    val default = call.resultTypes.default.map(singleResultType(table))
    if (default.isEmpty && resultTypes.isEmpty)
      println("Could not found any response code definition. It's not possible to define any marshallers. This will lead to the error at runtime.")
    (resultTypes, default)
  }

  private def securityParams(security: Set[Constraint]) = security collect {
    case OAuth2Constraint(_, _, scopes) => Map("name" -> "scopes", "type" -> "String*")
  }

  private def securityParamValue(c: Constraint) = c match {
    case OAuth2Constraint(_, _, scopes) => scopes map { s => Map("name" -> ("\"" + s + "\"")) }
    case _ => Set.empty[Map[String, String]]
  }

  private def needsCustom(mimeTypes: Set[Http.MimeType]): Boolean =
    mimeTypes.map(_.name).diff(providedWriterFactories).nonEmpty

  private def mimeTypes2StringList(in: Set[Http.MimeType]): String =
    in.map("\"" + _.name + "\"").mkString("Seq[String](", ", ", ")")

  private def nameWithSuffix(call: ApiCall, suffix: String): String =
    escape(call.handler.method + suffix)

  private def errorMappings(call: ApiCall): Iterable[Map[String, String]] =
    call.errorMapping.flatMap {
      case (k, v) => v.map { ex =>
        Map(
          "exception_name" -> ex.getCanonicalName,
          "simple_exception_name" -> ex.getSimpleName,
          "exception_code" -> k
        )
      }
    }

  private def singleOrMultipleParameters(call: ApiCall)(table: DenotationTable) = {
    val parameters = call.handler.parameters map parameterMap(table)
    val parameter = parameters.headOption
    Map(
      "single_parameter" -> (if (call.handler.parameters.length == 1) parameter else None),
      "multiple_parameters" -> (if (call.handler.parameters.length > 1) parameters else Nil)
    )
  }

  private def endComment(action: ApiCall) = s"$eof ${PlayScalaControllerAnalyzer.asMarker(action)}"

  private def startComment(action: ApiCall) = s"$sof ${PlayScalaControllerAnalyzer.asMarker(action)}"

  def callValidations(ref: Reference)(implicit table: DenotationTable): Any =
    table(ref)("validators").getOrElse("call_validations", Nil)

}

object PlayScalaControllerAnalyzer extends ControllersCommons {

  case class UnmanagedPart(marker: ApiCall, relevantCode: Seq[String])

  val controllerImports = Seq(
    "play.api.mvc.{Action, Controller}",
    "play.api.data.validation.Constraint",
    "de.zalando.play.controllers._",
    "PlayBodyParsing._",
    "PlayValidations._",
    "scala.util._"
  )

  def analyzeController(calls: Seq[ApiCall], codeParts: Map[String, Seq[String]]): Map[ApiCall, UnmanagedPart] =
    calls.collect {
      case call: ApiCall if codeParts.contains(asMarker(call)) =>
        call -> UnmanagedPart(call, codeParts(asMarker(call)).tail.init)
    }.toMap

  def asMarker(apiCall: ApiCall): String = apiCall.handler.controller + "." + apiCall.handler.method

  def unmanagedImports(currentVersion: String, modelTypes: TypeLookupTable): Seq[String] = {
    val allLines = currentVersion.split("\n").filter(_.trim.nonEmpty).dropWhile(_.trim.isEmpty).toSeq
    allLines.filter(_.trim.startsWith("import")).map(_.replace("import ", "")).
      filterNot(controllerImports.contains).filterNot(standardImports(modelTypes).contains)
  }

  def standardImports(modelTypes: TypeLookupTable): Seq[String] =
    modelTypes.collect {
      case (ref, tpe: PrimitiveType) =>
        tpe.imports
    }.toSeq.flatten

  def collectImplementations(currentVersion: Seq[String], start: String, end: String): Map[String, Seq[String]] = {
    val actionIndexes = currentVersion.zipWithIndex.filter(_._1.trim.startsWith(start)).map { case (line, idx) => line.replace(start, "") -> idx }
    val codeParts = actionIndexes map {
      case (action, idx) =>
        val fromStart = currentVersion.drop(idx)
        val endIdx = fromStart.takeWhile(!_.trim.startsWith(end)).size + 1
        action.trim -> fromStart.take(endIdx)
    }
    codeParts.toMap
  }
}

trait ParameterData {

  def app: StrictModel

  def parameterMap(table: DenotationTable): ParameterRef => Map[String, String] = param => {
    val parameter = app.findParameter(param)
    val typeName = parameter.typeName
    val commonTypeName = typeNameDenotation(table, typeName.name)
    val (parser, parserType) = typeName match {
      case TypeRef(ref) =>
        app.findType(ref) match {
          case Opt(underlyingType, _) =>
            val tpeName = typeNameDenotation(table, underlyingType.name)
            ("optionParser", tpeName)
          case _ => ("anyParser", commonTypeName)
        }
      case _ => ("anyParser", commonTypeName)
    }
    Map(
      "field_name" -> escape(camelize("\\.", param.simple)), // should be taken from the validation
      "type_name" -> commonTypeName,
      "parser_type" -> parserType,
      "body_parser" -> parser,
      "optional" -> parser.replaceAll("anyParser", ""),
      "real_name" -> param.simple,
      "is_file" -> (if (parserType == "File") parserType else "")
    )
  }

  def validationsByType(call: ApiCall, filter: Parameter => Boolean)(implicit table: DenotationTable): Seq[Map[String, String]] =
    parametersValidations(table)(parametersByPlace(call, filter))

  private def parametersByPlace(call: ApiCall, filter: Parameter => Boolean): Seq[ParameterRef] =
    call.handler.parameters.filter { p => filter(app.findParameter(p)) }

  private def parametersValidations(table: DenotationTable)(parameters: Seq[ParameterRef]) =
    parameters map parameterMap(table)

}

trait ControllersCommons {

  val controllersSuffix = "Action"
  val formParserSuffix = "ParseForm"

  val eof = "// ----- End of unmanaged code area for action "
  val sof = "// ----- Start of unmanaged code area for action "

}