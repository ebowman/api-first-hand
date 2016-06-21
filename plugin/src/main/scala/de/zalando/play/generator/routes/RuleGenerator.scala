package de.zalando.play.generator.routes

import de.zalando.apifirst.Application.{ ApiCall, StrictModel }
import de.zalando.apifirst.{ Domain, ParameterPlace, ScalaName, StringUtil }
import de.zalando.apifirst.ScalaName._
import de.zalando.apifirst.naming.{ Path, Reference }
import play.routes.compiler._

/**
 * @author  slasch
 * @since   27.11.2015.
 *
 * TODO this generator is a copy of the prototypical implementation
 * It must be refactored after FullPath implementation is removed
 */
object RuleGenerator {

  def apiCalls2PlayRules(calls: ApiCall*)(implicit model: StrictModel): Seq[Rule] = calls map { call =>
    val verb = HttpVerb(call.verb.name)

    val handlerCall = HandlerCall(packageName(model.packageName, call.handler.packageName), call.handler.controller, call.handler.instantiate,
      call.handler.method, Some(parameters2parameters(call)))

    val path = convertPath(call.path)

    val comments = List.empty
    Route(verb, path, handlerCall, comments)
  }

  def packageName(spec: Option[String], handler: String): String = ScalaName.scalaPackageName(spec getOrElse handler)

  private def parameters2parameters(call: ApiCall)(implicit model: StrictModel): Seq[Parameter] = {
    val params = call.handler.parameters flatMap { param =>
      val p = model.findParameter(param)
      if (p.place != ParameterPlace.BODY && p.place != ParameterPlace.HEADER && p.place != ParameterPlace.FORM) {
        val default = p.typeName match {
          case s: Domain.Str => p.default map StringUtil.quoteIfNeeded
          case _ => p.default
        }
        Some(Parameter(escape(p.name), p.typeName.name.typeAlias(), p.fixed, default))
      } else
        None
    }
    params
  }

  /**
   * See `RuleGeneratorTest` for Play path conversion rules:
   *
   * @param path   a path to convert
   * @param model  the model used to look up parameters
   * @return
   */
  def convertPath(path: Path)(implicit model: StrictModel): PathPattern = {
    val playParts = path.ref.parts.foldLeft(Seq.empty[PathPart]) {
      case (url, current) => url match {
        case Nil => Seq(convertSingle(current))
        case head :: tail => head match {
          case StaticPart(value) if !Path.pathParam(current) =>
            convertSingle(value + "/" + current) :: tail
          case StaticPart(value) => convertSingle(current) :: convertSingle(value + "/") :: tail
          case DynamicPart(name, constraint, encode) if !Path.pathParam(current) =>
            convertSingle("/" + current) :: head :: tail
          case DynamicPart(name, constraint, encode) =>
            convertSingle(current) :: StaticPart("/") :: head :: tail
        }

      }
    }
    if (playParts == Seq(StaticPart(""))) PathPattern(Nil) else PathPattern(playParts.reverse)
  }

  private def convertSingle(part: String)(implicit model: StrictModel): PathPart =
    if (Path.pathParam(part)) {
      val name = Path.strip(part)
      val (constraint, encode) = model.findParameter(Reference(name)) match {
        case Some(param) => (param.constraint, param.encode)
        case None => ("""[^/]+""", true)
      }
      DynamicPart(name, constraint, encode)
    } else {
      StaticPart(part)
    }
}