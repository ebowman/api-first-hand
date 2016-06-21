package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.Parameter
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.ParameterPlace
import de.zalando.apifirst.generators.DenotationNames._
import de.zalando.apifirst.naming.Reference

/**
 * @author slasch
 * @since 06.01.2016.
 */
/**
 * @author slasch
 * @since 30.12.2015.
 */
trait ParamBindingsStep extends EnrichmentStep[Parameter] {

  override def steps: Seq[SingleStep] = bindings +: super.steps

  /**
   * Puts type information into the denotation table
   *
   * @return
   */
  protected def bindings: SingleStep = parameter => table =>
    Map("bindings" -> singleParameter(parameter, table))

  private def singleParameter(paramPair: (Reference, Parameter), table: DenotationTable): Map[String, Any] =
    paramPair._2.place match {
      case ParameterPlace.BODY =>
        // body parameters do not need bindables
        Map.empty
      case ParameterPlace.FORM =>
        val place = "Query"
        bindingForPlace(paramPair, table, place)
      case ParameterPlace.HEADER =>
        // headers are handled in the same way path parameters are
        val place = "Path"
        bindingForPlace(paramPair, table, place)
      case other =>
        val place = other.toString.capitalize
        bindingForPlace(paramPair, table, place)
    }

  def bindingForPlace(paramPair: (Reference, Parameter), table: DenotationTable, place: String): Map[String, Seq[Map[String, Any]]] = {
    Map("binding" -> forType(place, paramPair._1, paramPair._2.typeName, table))
  }

  val providedBindings = Seq(classOf[Flt], classOf[Intgr], classOf[Lng], classOf[Dbl], classOf[Bool], classOf[Str])

  def forType(tpe: String, k: Reference, typeName: Type, table: DenotationTable): Seq[Map[String, Any]] = typeName match {
    case i if providedBindings.contains(i.getClass) => Nil
    case someTpe if someTpe.nestedTypes.nonEmpty => forNestedTypes(tpe, table, someTpe)
    case TypeRef(ref) => forType(tpe, ref, app.findType(ref), table)
    case d: Date => forDateType(tpe)
    case d: DateTime => forDateTimeType(tpe)
    case d: Base64String => forBase64Type(tpe)
    case d: File => forFileType(tpe)
    case d: Password => forPasswordType
    case d: BInt => forBigIntegerType(tpe)
    case d: BDcml => forBigDecimalType(tpe)
    case d: UUID => forUUID(tpe)
    case d: BinaryString =>
      throw new IllegalArgumentException("'type: string, format: binary' can only be used with body parameters")
  }

  def forNestedTypes(tpe: String, table: DenotationTable, someTpe: Type): Seq[Map[String, Any]] = {
    val alias = someTpe.name.simple
    val underlyingType = someTpe.nestedTypes.map { t => typeNameDenotation(table, t.name) }.mkString(", ")
    val bindable = s"""implicit val bindable_$alias$underlyingType$tpe = PlayPathBindables.create$alias${tpe}Bindable[$underlyingType]"""
    val format = someTpe match {
      case arr: Arr => "(\"" + arr.format + "\")"
      case _ => ""
    }
    val mainType = Seq(Map(
      "name" -> bindable,
      "binding_imports" -> Set("de.zalando.play.controllers.PlayPathBindables"),
      "format" -> (bindable + format),
      "dependencies" -> someTpe.nestedTypes.filterNot(c => providedBindings.contains(c.getClass)).length
    ))
    val nestedTypes = someTpe.nestedTypes.flatMap { nt => forType(tpe, nt.name, nt, table) }
    mainType ++ nestedTypes
  }

  def forPasswordType: Seq[Map[String, Object]] = {
    Seq(Map(
      "name" -> "", "format" -> "",
      "binding_imports" -> Set("de.zalando.play.controllers.PlayPathBindables")
    ))
  }

  private def withName(name: String): String => Seq[Map[String, Object]] = tpe => {
    Seq(Map(
      "name" -> "",
      "format" -> s"""implicit val bindable_$name$tpe = PlayPathBindables.${tpe.toLowerCase}Bindable$name""",
      "binding_imports" -> Set("de.zalando.play.controllers.PlayPathBindables")
    ))
  }

  type TypeNameToDescription = (String) => Seq[Map[String, Object]]

  def forFileType: TypeNameToDescription = withName("File")

  def forBase64Type: TypeNameToDescription = withName("Base64String")

  def forDateTimeType: TypeNameToDescription = withName("DateTime")

  def forDateType: TypeNameToDescription = withName("LocalDate")

  def forBigIntegerType: TypeNameToDescription = withName("BigInt")

  def forBigDecimalType: TypeNameToDescription = withName("BigDecimal")

  def forUUID: TypeNameToDescription = withName("UUID")

}