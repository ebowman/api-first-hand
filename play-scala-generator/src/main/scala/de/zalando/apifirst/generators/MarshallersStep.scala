package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.StrictModel
import de.zalando.apifirst.Domain.{ TypeDef, TypeRef }
import de.zalando.apifirst.generators.DenotationNames._
import de.zalando.apifirst.{ Domain, ParameterPlace, ScalaName }

/**
 * @author  slasch
 * @since   30.12.2015.
 */
trait MarshallersStep extends EnrichmentStep[StrictModel] {

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
      Map(
        "mime_type" -> p._1,
        "result_type" -> p._2,
        "writable_name" -> ScalaName.escape(s"writable_${mime}_${p._2})")
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

  private def specJsonReadables(spec: StrictModel)(table: DenotationTable): Map[String, Any] = {
    val bodyParams = bodyParameters(spec).filter { case (mimeType, _) => jsonPattern.matcher(mimeType).matches() }
    val bodyTypes = parameterDependencies(bodyParams.map(_._2.typeName)).distinct.collect { case d: TypeDef => d }.reverse.map(_.name)
    val denotations = bodyTypes.flatMap(table.apply)
    val typeDefs = denotations.collect { case (k, v) if k == "classes" => v.toSeq }
    typeDefs.flatten.toMap
  }

  private def specJsonWritables(spec: StrictModel)(table: DenotationTable): Map[String, Any] = {
    val results = callResults(spec).filter { case (mimeType, _) => jsonPattern.matcher(mimeType).matches() }
    val types = parameterDependencies(results.map(_._2)).distinct.collect { case d: TypeDef => d }.reverse.map(_.name)
    val denotations = types.flatMap(table.apply)
    val typeDefs = denotations.collect { case (k, v) if k == "classes" => v.toSeq }
    typeDefs.flatten.toMap
  }

  private def parameterDependencies(parameters: Seq[Domain.Type]): Seq[Domain.Type] =
    parameters.flatMap { p =>
      val nested = nestedTypes(realType(p).nestedTypes)
      realType(p) +: nested
    }

  private def nestedTypes(ts: Seq[Domain.Type]): Seq[Domain.Type] = {
    (ts map realType) ++ (ts.map(_.nestedTypes map realType) flatMap nestedTypes)
  }

  private def realType(p: Domain.Type) = p match {
    case r: TypeRef => app.findType(r.name)
    case _ => p
  }

  private def bodyParameters(spec: StrictModel) = {
    for {
      call <- spec.calls
      mime <- call.mimeIn.map(_.name).diff(providedWriterFactories)
      param <- call.handler.parameters
      bodyParam = app.findParameter(param) if bodyParam.place == ParameterPlace.BODY
    } yield (mime, bodyParam)
  }

  private def callResults(spec: StrictModel) = {
    for {
      call <- spec.calls
      mime <- call.mimeOut.map(_.name).diff(providedWriterFactories)
      resultRef <- call.resultTypes.results.values
      resultType = app.findType(resultRef.name)
    } yield (mime, resultType)
  }

}
