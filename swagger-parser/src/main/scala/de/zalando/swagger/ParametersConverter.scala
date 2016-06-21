package de.zalando.swagger

import java.net.URI

import de.zalando.apifirst.Application.{ ParameterLookupTable, ParameterRef }
import de.zalando.apifirst.Domain.Type
import de.zalando.apifirst._
import de.zalando.apifirst.naming.{ Reference, uriToReference }
import de.zalando.swagger.strictModel._

import scala.language.postfixOps

/**
 * @author  slasch
 * @since   03.11.2015.
 */
class ParametersConverter(val base: URI, val model: SwaggerModel, val keyPrefix: String,
  typeDefs: ParameterNaming#NamedTypes, val useFileNameAsPackage: Boolean)
    extends ParameterNaming with HandlerGenerator with ParameterReferenceGenerator {

  private val FIXED = None // There is no way to define fixed parameters in swagger spec

  val definitionFileName: Option[String] =
    if (base.getScheme == "file") Option(base.getSchemeSpecificPart) else None

  /**
   * For each parameter defined inline, creates definition in lookup table
   * Converts existing references as well by creating type pointer
   */
  lazy val parameters: ParameterLookupTable = fromPaths(model.paths, model.basePath).flatten.toMap

  private def fromPaths(paths: Paths, basePath: BasePath) =
    Option(paths).toSeq.flatten flatMap fromPath(basePath)

  private def fromPath(basePath: BasePath)(pathDef: (String, PathItem)) = {
    implicit val (url, path) = pathDef
    for {
      operationName <- path.operationNames
      operation = path.operation(operationName)
      namePrefix = base / "paths" / url / operationName
    } yield parameters(path, operation, namePrefix)
  }

  private def parameters(path: PathItem, operation: Operation, namePrefix: Reference) = {
    val pathParams = fromParameterList(path.parameters, namePrefix)
    val operationParams = fromParameterList(operation.parameters, namePrefix)
    pathParams ++ operationParams
  }

  private def fromParameterList(parameters: ParametersList, parameterNamePrefix: Reference): ParameterLookupTable = {
    Option(parameters).toSeq.flatten map fromParametersListItem(parameterNamePrefix) toMap
  }

  @throws[MatchError]
  private def fromParametersListItem(prefix: Reference)(li: ParametersListItem): (Application.ParameterRef, Application.Parameter) = {
    val paramRef = refFromParametersListItem(prefix)(li)
    li match {
      case jr @ JsonReference(ref) =>
        paramRef -> fromExplicitParameter(prefix, ref)
      case bp: BodyParameter[_] =>
        paramRef -> fromBodyParameter(prefix, bp)
      case nbp: NonBodyParameterCommons[_, _] =>
        paramRef -> fromNonBodyParameter(prefix, nbp)
      case nbp: NonBodyParameter[_] =>
        throw new IllegalStateException("Something went wrong, this case should not be reachable")
    }
  }

  private def fromExplicitParameter(prefix: Reference, ref: String): Application.Parameter = {
    val default = None
    val parameter = model.parameters.find {
      case (paramName, _) =>
        paramName == Reference.deref(ref).simple
    } map {
      _._2
    } getOrElse {
      throw new IllegalStateException(s"Could not find parameter definition for reference $ref")
    }
    val typeDef = findTypeByParameterRef(prefix, ref)
    val (constraint, encode) = Constraints(parameter.in)
    val place = ParameterPlace.withName(parameter.in)
    Application.Parameter(parameter.name, typeDef, FIXED, default, constraint, encode, place)
  }

  private def fromBodyParameter(prefix: Reference, p: BodyParameter[_]): Application.Parameter = {
    val default = None
    val (_, typeDef) = findType(prefix, p.name)
    val (constraint, encode) = Constraints(p.in)
    Application.Parameter(p.name, typeDef, FIXED, default, constraint, encode, ParameterPlace.BODY)
  }

  private def fromNonBodyParameter(prefix: Reference, p: NonBodyParameterCommons[_, _]): Application.Parameter = {
    val default = if (!p.required) Option(p.default).map(_.toString) else None
    val (_, typeDef) = findType(prefix, p.name)
    val (constraint, encode) = Constraints(p.in)
    val place = ParameterPlace.withName(p.in)
    Application.Parameter(p.name, typeDef, FIXED, default, constraint, encode, place)
  }

  private def findType(prefix: Reference, paramName: String): NamedType = {
    val name = prefix / paramName
    val typeDef = typeDefByName(name) orElse findTypeByPath(prefix, paramName) getOrElse {
      throw new IllegalStateException(s"Could not find type definition with a name $name")
    }
    (name, typeDef)
  }

  private def findTypeByParameterRef(fullPath: Reference, ref: String): Type = {
    val parent = base / ref
    typeDefs.find(_._1.parent == parent).map(_._2) getOrElse {
      throw new IllegalStateException(s"Could not find parameter definition with reference $ref")
    }
  }

  private def findTypeByPath(fullPath: Reference, name: String): Option[Type] =
    typeDefByName(fullPath.parent / "" / name)

  private def typeDefByName(name: Reference): Option[Type] =
    typeDefs.find(_._1 == name).map(_._2)

}

object Constraints {
  private val byType: Map[String, (String, Boolean)] = Map(
    ("formData", (".+", true)),
    ("path", ("[^/]+", true)),
    ("header", (".+", false)),
    ("body", (".+", false)),
    ("query", (".+", true))
  )

  def apply(in: String): (String, Boolean) = byType(in)
}

trait ParameterReferenceGenerator {
  protected def refFromParametersListItem(prefix: Reference)(li: ParametersListItem): ParameterRef = li match {
    case jr @ JsonReference(ref) =>
      ParameterRef(prefix / Reference.deref(ref).simple)
    case p: BodyParameter[_] =>
      ParameterRef(prefix / p.name)
    case p: NonBodyParameter[_] =>
      ParameterRef(prefix / p.name)
  }
}