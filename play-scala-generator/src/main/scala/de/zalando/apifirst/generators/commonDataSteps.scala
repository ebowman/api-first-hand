package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.{ StrictModel, ApiCall, Parameter }
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.naming.Reference

import scala.annotation.tailrec

import de.zalando.apifirst.ScalaName._
import de.zalando.apifirst.generators.DenotationNames._

/**
 * @author slasch
 * @since 30.12.2015.
 */
trait CommonParamDataStep extends EnrichmentStep[Parameter] with CommonData {

  override def steps: Seq[SingleStep] = types +: super.steps

  /**
   * Puts type information into the denotation table
   *
   * @return
   */
  protected def types: SingleStep = parameter => table => singleParameter(parameter)

  private def singleParameter(paramPair: (Reference, Parameter)) = {
    val (ref, param) = paramPair
    val t = param.typeName
    val name = typeName(t, ref)
    Map(COMMON -> Map(TYPE_NAME -> name, FIELDS -> fieldsForType(t), MEMBER_NAME -> memberName(t, ref)))
  }
}

trait CommonCallDataStep extends EnrichmentStep[ApiCall] with CommonData {

  override def steps: Seq[SingleStep] = types +: super.steps

  /**
   * Puts type information into the denotation table
   *
   * @return
   */
  protected def types: SingleStep = parameter => table => singleParameter(parameter)

  private def singleParameter(paramPair: (Reference, ApiCall)) = {
    val ref = paramPair._2.asReference
    val t = null
    val name = typeName(t, ref)
    Map(COMMON -> Map(TYPE_NAME -> name, FIELDS -> fieldsForType(t), MEMBER_NAME -> memberName(t, ref)))
  }
}

trait CommonDataStep extends EnrichmentStep[Type] with CommonData {

  override def steps: Seq[SingleStep] = types +: super.steps

  @tailrec
  private def avoidClashes(table: DenotationTable, name: String)(names: Iterable[String] = table.values.map(_(COMMON)(TYPE_NAME).toString)): String =
    if (names.exists(_.equalsIgnoreCase(name))) avoidClashes(table, name + "NameClash")(names) else name

  /**
   * Puts type information into the denotation table
   *
   * @return
   */
  protected def types: SingleStep = typeDef => table => typeDef match {
    case (ref, t) =>
      val name = avoidClashes(table, typeName(t, ref))()
      Map(COMMON -> Map(TYPE_NAME -> name, FIELDS -> fieldsForType(t), MEMBER_NAME -> memberName(t, ref)))
  }

}

trait CommonData {

  def app: StrictModel

  protected def typeName(t: Type, r: Reference, suffix: String = "") = t match {
    case TypeRef(ref) =>
      app.findType(ref) match {
        case p: PrimitiveType => useType(p.name, suffix, "")
        case d: TypeDef => useType(d.name, suffix, "")
        case _ => useType(ref, suffix, "")
      }
    case p: PrimitiveType => useType(t.name, suffix, "")
    case TypeDef(name, _, _) if name.isDefinition && !r.isDefinition =>
      useType(name, suffix, "")
    case _ => useType(r, suffix, "")
  }

  @tailrec
  protected final def memberName(t: Type, r: Reference, suffix: String = ""): String = t match {
    case TypeRef(ref) if !app.findType(ref).isInstanceOf[TypeRef] => memberName(app.findType(ref), ref, suffix)
    case TypeRef(ref) => useType(ref, suffix, "")
    case p: PrimitiveType => useType(r, suffix, "")
    case _ => useType(r, suffix, "")
  }

  protected def dereferenceFields(t: Composite): Seq[Field] =
    t.descendants flatMap {
      case td: TypeDef => td.fields
      case r: TypeRef => app.findType(r.name) match {
        case td: TypeDef => td.fields
        case c: Composite => dereferenceFields(c)
        case other =>
          throw new IllegalStateException(s"Unexpected contents of Composite $r: $other")
      }
    }

  protected def fieldsForType(t: Type): Seq[Field] = {
    t match {
      case c: Composite => dereferenceFields(c).distinct
      case c: TypeDef => c.fields
      case _ => Seq.empty[Field]
    }
  }

  private def useType(ref: Reference, suffix: String, prefix: String) = {
    val fullName = ref.qualifiedName(prefix, suffix)
    fullName._2
  }

}