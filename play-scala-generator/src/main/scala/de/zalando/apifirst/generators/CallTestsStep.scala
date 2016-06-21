package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.{ ApiCall, ParameterRef }
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.generators.DenotationNames._
import de.zalando.apifirst.{ ParameterPlace, ScalaName, StringUtil }

/**
 * @author  slasch
 * @since   30.12.2015.
 */
trait CallTestsStep extends EnrichmentStep[ApiCall] with ActionResults with ParameterData {

  override def steps: Seq[SingleStep] = tests +: super.steps

  val testsSuffix = "Spec"

  /**
   * Puts tests related information into the denotation table
   *
   * TODO Secure endpoints are not tested now
   *
   * Currently, no tests are generated for secure endpoints.
   * Design test extension which will allow to augment test
   * requests with security-related data
   *
   * @return
   */
  protected val tests: SingleStep = apiCall => table =>
    if (apiCall._2.handler.parameters.isEmpty) empty
    else if (apiCall._2.security.nonEmpty) {
      println(s"INFO: Not generating tests for secure API endpoint ${apiCall._2.verb} ${apiCall._2.path.asSwagger} , " +
        "this issue will be addressed in future versions of the plugin")
      empty
    } else Map("tests" -> callTest(apiCall._2)(table))

  def callTest(call: ApiCall)(implicit table: DenotationTable): Map[String, Object] = {
    val namespace = if (app.basePath == "/") "" else app.basePath

    val (allActionResults, defaultResultType) = actionResults(call)(table)

    val formParams = validationsByType(call, p => p.place == ParameterPlace.FORM)

    Map(
      "accept_headers" -> acceptHeader(call),
      "content_types" -> contentTypes(call),
      "result_types" -> allActionResults,
      "default_result_type" -> defaultResultType,
      "verb_name" -> call.verb.name,
      "full_path" -> call.path.prepend(namespace).asSwagger,
      "full_url" -> fullUrl(namespace, call),
      "validation_name" -> validator(call.asReference, table),
      "body_param" -> bodyParameter(call, defaultResultType),
      "form_parameters" -> formParams,
      "headers" -> headers(call)
    ) ++ parameters(call)(table)
  }

  def acceptHeader(call: ApiCall): Set[Map[String, String]] =
    call.mimeOut.map(_.name).map { header =>
      Map("name" -> header)
    }

  def contentTypes(call: ApiCall): Set[Map[String, String]] =
    call.mimeIn.map(_.name).map { header =>
      Map("name" -> header)
    }

  // TODO should try all possible marshallers
  def bodyParameter(call: ApiCall, resultType: Option[String]): Option[Map[String, String]] = {
    call.handler.parameters.map {
      app.findParameter
    }.find {
      _.place == ParameterPlace.BODY
    }.map { p =>
      Map(
        "body_parameter_name" -> p.name,
        "expected_result_type" -> resultType.getOrElse("application/json")
      )
    }
  }

  def headers(call: ApiCall): Seq[Map[String, String]] = {
    call.handler.parameters.filter { p =>
      app.findParameter(p).place == ParameterPlace.HEADER
    }.map { p =>
      Map(
        "name" -> p.name.simple,
        "parameter_name" -> ScalaName.escape(StringUtil.camelize("\\.", p.simple))
      )
    }
  }

  def singleParameter(table: DenotationTable)(param: ParameterRef): Map[String, String] = {
    val paramName = app.findParameter(param)
    val typeName = paramName.typeName
    val genName = generator(typeName.name, table)
    Map(
      "name" -> ScalaName.escape(StringUtil.camelize("\\.", param.simple)),
      "type" -> typeNameDenotation(table, param.name),
      "generator" -> genName
    )
  }

  private def parameters(call: ApiCall)(table: DenotationTable) = {
    lazy val parameters = call.handler.parameters.map { singleParameter(table) }
    lazy val parameter = call.handler.parameters.headOption map singleParameter(table)
    Map(
      "multiple_parameters" -> (if (call.handler.parameters.length > 1) parameters else Nil),
      "single_parameter" -> (if (call.handler.parameters.length == 1) parameter else None)
    )
  }

  private def fullUrl(namespace: String, call: ApiCall) = {
    val query = call.handler.parameters.filter { p =>
      app.findParameter(p).place == ParameterPlace.QUERY
    } map { p =>
      val param = app.findParameter(p)
      singleQueryParam(param.name, param.typeName)
    }
    val fullQuery = if (query.isEmpty) "" else query.mkString("?", "&", "")
    val url = call.path.prepend(namespace).interpolated
    "s\"\"\"" + url + fullQuery + "\"\"\""
  }

  private def singleQueryParam(name: String, typeName: Type): String =
    "$" + s"""{toQuery("${ScalaName.escape(name)}", $name)}"""
}

trait ActionResults extends EnrichmentStep[ApiCall] {

  import de.zalando.apifirst.ScalaName._

  def actionResults(call: ApiCall)(table: DenotationTable): (Seq[Map[String, Any]], Option[String]) = {
    val resultTypes = call.resultTypes.results.toSeq map {
      case (code, ref) =>
        Map("code" -> code, "type" -> singleResultType(table)(ref))
    }
    val default = call.resultTypes.default.map(singleResultType(table))
    if (default.isEmpty && resultTypes.isEmpty)
      println("Could not found any response code definition. It's not possible to define any marshallers. This will lead to the error at runtime.")
    (resultTypes, default)
  }

  def singleResultType(table: DenotationTable)(ref: ParameterRef): String = {
    val tpe = app.findType(ref.name)
    tpe match {
      case c: Container =>
        // TODO this should be readable from model
        c.name.simple + c.nestedTypes.map { t => typeNameDenotation(table, t.name) }.mkString("[", ", ", "]")
      case p: ProvidedType => typeNameDenotation(table, p.name)
      case p: TypeDef => typeNameDenotation(table, p.name)
      case o => o.name.className
    }
  }

}