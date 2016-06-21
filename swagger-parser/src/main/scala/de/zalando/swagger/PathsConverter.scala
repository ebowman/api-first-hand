package de.zalando.swagger

import java.net.URI

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Http.{ MimeType, Verb }
import de.zalando.apifirst.Hypermedia.{ NamedState, Self, State }
import de.zalando.apifirst.Security.Constraint
import de.zalando.apifirst._
import de.zalando.apifirst.naming.{ Path, Reference }
import de.zalando.swagger.strictModel._

/**
 * @author  slasch
 * @since   20.10.2015.
 */
class PathsConverter(val base: URI, val model: SwaggerModel, val keyPrefix: String,
  params: ParameterLookupTable, securityDefinitions: SecurityDefinitionsTable,
  val definitionFileName: Option[String] = None, val useFileNameAsPackage: Boolean = true)
    extends ParameterNaming with HandlerGenerator with ParameterReferenceGenerator {

  lazy val convert = fromPaths(model.paths, model.basePath)

  import naming._

  private def fromPath(basePath: BasePath)(pathDef: (String, PathItem)) = {
    implicit val (url, path) = pathDef
    for {
      operationName <- path.operationNames
      verb <- verbFromOperationName(operationName)
      operation = path.operation(operationName)
      namePrefix = base / "paths" / url / operationName
      astPath = uriFragmentToReference(url)
      params = parameters(path, operation, namePrefix)
      handlerCall <- handler(operation, path, params, operationName, astPath).toSeq
      (types, states) = resultTypes(namePrefix, operation)
      (mimeIn, mimeOut) = mimeTypes(operation)
      errMappings = errorMappings(path, operation)
      security = securityRequirements(operation)
    } yield ApiCall(verb, Path(astPath), handlerCall, mimeIn, mimeOut, errMappings, types, states, security.toSet)
  }

  private def securityRequirements(operation: Operation) = {
    val security = Option(operation.security) orElse Option(model.security)
    for {
      requirement <- security.toSeq.flatten
      (name, scopes) <- requirement
      definition = securityDefinitionByName(name)
    } yield Constraint.fromDefinition(name, definition, scopes)
  }

  private def securityDefinitionByName(name: String): Security.Definition =
    securityDefinitions.getOrElse(
      name,
      throw new scala.IllegalArgumentException(s"Could not find security definition with name $name")
    )

  private def fromPaths(paths: Paths, basePath: BasePath) = Option(paths).toSeq.flatten flatMap fromPath(basePath)

  private def resultTypes(prefix: Reference, operation: Operation): (TypesResponseInfo, StateResponseInfo) = {
    val default = operation.responses collectFirst {
      case ("default", definition) =>
        ParameterRef(prefix / Reference.responses / "default") -> targetState(definition)
    }
    val responses = operation.responses collect {
      case (code, definition) if code.forall(_.isDigit) =>
        Some((code.toInt, (ParameterRef(prefix / Reference.responses / code), targetState(definition))))
      case ("default", definition) => None
      case (other, _) =>
        println(s"Expected numeric error code or 'default' for response, but was $other")
        None
    }
    val resultMap = responses.flatten.toMap
    val types = resultMap.map { case (code, (ref, state)) => code -> ref }
    val states = resultMap.map { case (code, (ref, state)) => code -> state }
    (TypesResponseInfo(types, default.map(_._1)), StateResponseInfo(states, default.map(_._2)))
  }

  def targetState(definition: Response[_]): State = definition.targetState.map(NamedState.apply).getOrElse(Self)

  private def parameters(path: PathItem, operation: Operation, namePrefix: Reference) = {
    val pathParams = fromParameterList(path.parameters, namePrefix)
    val operationParams = fromParameterList(operation.parameters, namePrefix)
    val simpleNames = operationParams map (_.simple)
    pathParams.filterNot { p => simpleNames.contains(p.simple) } ++ operationParams.toSet
  }

  private def fromParameterList(parameters: ParametersList, parameterNamePrefix: Reference): Seq[Application.ParameterRef] = {
    Option(parameters).toSeq.flatten map refFromParametersListItem(parameterNamePrefix)
  }

  private def verbFromOperationName(operationName: String): Seq[Verb] =
    Http.string2verb(operationName).orElse {
      throw new scala.IllegalArgumentException(s"Could not parse HTTP verb $operationName")
    }.toSeq

  private def errorMappings(path: PathItem, operation: Operation) =
    Seq(model.vendorErrorMappings, path.vendorErrorMappings, operation.vendorErrorMappings).
      filter(_ != null).reduce(_ ++ _).toSet.toMap

  private def mimeTypes(operation: Operation) = {
    val mimeIn = orderedMediaTypeList(operation.consumes, model.consumes)
    val mimeOut = orderedMediaTypeList(operation.produces, model.produces)
    (mimeIn, mimeOut)
  }

  def orderedMediaTypeList(hiPriority: MediaTypeList, lowPriority: MediaTypeList): Set[MimeType] = {
    Option(hiPriority).orElse(Option(lowPriority)).toSet.flatten.map {
      MimeType(_)
    }
  }

}

trait HandlerGenerator {
  import StringUtil._
  def useFileNameAsPackage: Boolean
  def keyPrefix: String
  def model: SwaggerModel
  def definitionFileName: Option[String]
  def handler(operation: Operation, path: PathItem, params: Seq[Application.ParameterRef], verb: String, url: Reference): Option[HandlerCall] = for {
    handlerText <- getOrGenerateHandlerLine(operation, path, verb, url)
    parseResult = HandlerParser.parse(handlerText)
    handler <- if (parseResult.successful) Some(parseResult.get) else None
  } yield handler.copy(parameters = params)

  private def getOrGenerateHandlerLine(operation: Operation, path: PathItem, verb: String, callPath: Reference): Option[String] =
    operation.vendorExtensions.get(s"$keyPrefix-handler") orElse
      path.vendorExtensions.get(s"$keyPrefix-handler").map(_ + capitalize("/", verb)) orElse
      generateHandlerLine(operation, callPath, verb)

  private def generateHandlerLine(operation: Operation, path: Reference, verb: String): Option[String] = {
    model.vendorExtensions.get(s"$keyPrefix-package") orElse packageFromFilename map { pkg =>
      val controller = definitionFileName map { capitalize("\\.", _) } getOrElse {
        throw new IllegalStateException(s"The definition file name must be defined in order to use '$keyPrefix-package' directive")
      }
      val method = Option(operation.operationId).map(camelize("\\.", _)).map(ScalaName.escape) getOrElse {
        verb.toLowerCase + Path(path).asMethod
      }
      s"$pkg.$controller.$method"
    }
  }

  private def packageFromFilename: Option[String] =
    if (useFileNameAsPackage) definitionFileName else None
}