package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.StrictModel
import de.zalando.apifirst.{ ParameterPlace, ScalaName }
import de.zalando.apifirst.generators.DenotationNames._

/**
 * @author  slasch
 * @since   30.12.2015.
 */
trait MarshallersStep extends EnrichmentStep[StrictModel] {

  override def steps: Seq[SingleStep] = readersAndWriteables +: super.steps

  val providedWriterFactories: Set[String]
  /**
   * Puts marshaller and parser related information into the denotation table
   *
   * @return
   */
  protected val readersAndWriteables: SingleStep = spec => table => {
    val marshallers = specMarshallers(spec._2)(table)
    val unMarshallers = specUnMarshallers(spec._2)(table)
    Map.empty ++
      (if (marshallers.nonEmpty) Map("marshallers" -> Map("data" -> marshallers)) else Map.empty) ++
      (if (unMarshallers.nonEmpty) Map("unmarshallers" -> Map("data" -> unMarshallers)) else Map.empty)
  }

  def specMarshallers(spec: StrictModel)(table: DenotationTable): Seq[Map[String, String]] = {
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

  def specUnMarshallers(spec: StrictModel)(table: DenotationTable): Seq[Map[String, String]] = {
    val inputPairs = for {
      call <- spec.calls
      mime <- call.mimeIn.map(_.name).diff(providedWriterFactories)
      param <- call.handler.parameters
      bodyParam = app.findParameter(param) if bodyParam.place == ParameterPlace.BODY
      inputType = typeNameDenotation(table, bodyParam.typeName.name)
    } yield (mime, inputType)
    inputPairs map { p =>
      val mime = p._1.replace('/', '_').replace('+', '_')
      Map(
        "mime_type" -> p._1,
        "result_type" -> p._2,
        "reader_name" -> ScalaName.escape(s"reader_${mime}_${p._2})")
      )
    }
  }

}
