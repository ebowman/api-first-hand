package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.{ ApiCall, StrictModel }
import de.zalando.apifirst.Domain.TypeDef
import de.zalando.apifirst.Http.ApplicationJson
import de.zalando.apifirst.generators.DenotationNames._
import de.zalando.apifirst.{ Http, ParameterPlace, ScalaName }
import org.slf4j.{ Logger, LoggerFactory }

/**
 * @author  slasch
 * @since   30.12.2015.
 */
trait MarshallersStep extends EnrichmentStep[StrictModel] {
  private val logger: Logger = LoggerFactory.getLogger(getClass)
  override def steps: Seq[SingleStep] = readersAndWriteables +: super.steps

  private val jsonPattern = """application/(.*\+)?json.*""".r.pattern

  val providedWriterFactories: Set[String]
  /**
   * Puts marshaller and parser related information into the denotation table
   *
   * @return
   */
  protected val readersAndWriteables: SingleStep = spec => table => {
    val marshallers = specMarshallers(spec._2)(table)
    val unMarshallers = specUnMarshallers(spec._2)(table)
    val jsonWritables = specJsonWritables(spec._2)(table)
    val jsonReadables = specJsonReadables(spec._2)(table)
    Map.empty ++
      (if (marshallers.nonEmpty) Map("marshallers" -> Map("data" -> marshallers)) else Map.empty) ++
      (if (unMarshallers.nonEmpty) Map("unmarshallers" -> Map("data" -> unMarshallers)) else Map.empty) ++
      (if (jsonReadables.nonEmpty) Map("json_readables" -> Map("data" -> jsonReadables)) else Map.empty) ++
      (if (jsonWritables.nonEmpty) Map("json_writables" -> Map("data" -> jsonWritables)) else Map.empty)
  }

  private def specMarshallers(spec: StrictModel)(table: DenotationTable): Seq[Map[String, String]] = {
    val requiredPairs = for {
      call <- spec.calls
      mime <- call.mimeOut.map(_.name).diff(providedWriterFactories)
      resultTypeRef <- call.resultTypes.results.values ++ call.resultTypes.default.toSeq
      resultType = typeNameDenotation(table, resultTypeRef.name)
    } yield (mime, resultType)
    requiredPairs map { p =>
      val mime = p._1.replace('/', '_').replace('+', '_')
      val typeName = p._2.replace("[", "").replace("]", "")
      Map(
        "mime_type" -> p._1,
        "result_type" -> p._2,
        "writable_name" -> ScalaName.escape(s"writable_${mime}_${typeName}")
      )
    }
  }

  private def specUnMarshallers(spec: StrictModel)(table: DenotationTable): Seq[Map[String, String]] = {
    val bodyParams = bodyParameters(spec)
    bodyParams map {
      case (mimeType, bodyParam) =>
        val inputType = typeNameDenotation(table, bodyParam.typeName.name)
        val mime = mimeType.replace('/', '_').replace('+', '_')
        Map(
          "mime_type" -> mimeType,
          "result_type" -> inputType,
          "reader_name" -> ScalaName.escape(s"reader_${mime}_$inputType)")
        )
    }
  }

  private def specJsonReadables(spec: StrictModel)(table: DenotationTable): Seq[Map[String, Any]] = {
    val bodyParams = bodyParameters(spec).filter { case (mimeType, _) => jsonPattern.matcher(mimeType).matches() }.distinct
    val bodyTypes =
      bodyParams.flatMap(_._2.typeName.allNestedTypes).distinct.map(_.realType).collect { case TypeDef(name, _, _) => name }.reverse
    val denotations = bodyTypes.flatMap(table.apply)
    val typeDefs = denotations.collect { case (k, v) if k == "classes" => v.toSeq }
    typeDefs.map(_.toMap)
  }

  private def specJsonWritables(spec: StrictModel)(table: DenotationTable): Seq[Map[String, Any]] = {
    val results = callResults(spec).filter { case (mimeType, _) => jsonPattern.matcher(mimeType.name).matches() }.distinct
    val types = results.flatMap(_._2.allNestedTypes).distinct.map(_.realType).collect { case TypeDef(name, _, _) => name }.reverse
    val denotations = types.flatMap(table.apply)
    val typeDefs = denotations.collect { case (k, v) if k == "classes" => v.toSeq }
    typeDefs.map(_.toMap)
  }

  private def bodyParameters(spec: StrictModel) = {
    for {
      call <- spec.calls
      param <- call.handler.parameters
      bodyParam = app.findParameter(param) if bodyParam.place == ParameterPlace.BODY
      mime <- nonEmptyOrDefault(call.mimeIn, call)
    } yield (mime.name, bodyParam)
  }

  private def callResults(spec: StrictModel) = {
    for {
      call <- spec.calls
      mime <- nonEmptyOrDefault(call.mimeOut, call)
      resultRef <- call.resultTypes.results.values
      resultType = app.findType(resultRef.name)
    } yield (mime, resultType)
  }

  private def nonEmptyOrDefault(mime: Set[Http.MimeType], call: ApiCall): Set[Http.MimeType] =
    if (mime.isEmpty) {
      logger.warn(s"No mime-types is defined for call ${call.verb} ${call.path}, defaulting to $ApplicationJson")
      Set(ApplicationJson)
    } else {
      mime
    }

}
