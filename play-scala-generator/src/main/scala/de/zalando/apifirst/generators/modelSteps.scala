package de.zalando.apifirst.generators

import de.zalando.apifirst.Domain._
import de.zalando.apifirst.ScalaName._
import de.zalando.apifirst.StringUtil
import de.zalando.apifirst.naming.Reference
import de.zalando.apifirst.generators.DenotationNames._

/**
 * @author slasch
 * @since 30.12.2015.
 */

trait ClassesStep extends EnrichmentStep[Type] {

  override def steps: Seq[SingleStep] = classes +: super.steps

  /**
   * Puts class related information into the denotation table
   *
   * @return
   */
  protected def classes: SingleStep = typeDef => table => typeDef match {
    case (ref, t: TypeDef) if !ref.simple.contains("AllOf") && !ref.simple.contains("OneOf") =>
      val traitName = app.discriminators.get(ref).map(_ =>
        Map("name" -> abstractTypeNameDenotation(table, ref).getOrElse("I" + typeNameDenotation(table, ref))))
      Map("classes" -> (typeDefProps(ref, t)(table) + ("trait" -> traitName)))
    case (ref, t: Composite) =>
      Map("classes" -> (typeDefProps(ref, t)(table) + ("trait" -> t.root.map { r =>
        val an = abstractTypeNameDenotation(table, r).getOrElse("I" + r.className)
        Map("name" -> an)
      })))
    case _ => empty
  }

  protected def typeDefProps(k: Reference, t: Type)(table: DenotationTable): Map[String, Any] = {
    Map(
      "name" -> typeNameDenotation(table, k),
      "optional" -> t.isInstanceOf[Opt],
      "single_field" -> (typeFields(table, k).size == 1),
      "fields" -> typeFields(table, k).map { f =>
        val nullableType = f.tpe match {
          case TypeRef(r) =>
            val underlying = app.findType(r)
            if (underlying.isInstanceOf[Opt]) Option(typeNameDenotation(table, underlying.asInstanceOf[Opt].nestedTypes.head.name)) else None
          case _ => None
        }
        Map(
          "name" -> escape(f.name.simple),
          "nullable_type_name" -> nullableType,
          TYPE_NAME -> typeNameDenotation(table, f.tpe.name)
        )
      },
      "imports" -> t.imports
    ) ++ abstractTypeNameDenotation(table, k).map("abstract_name" -> _).toSeq
  }

}

trait EnumsStep extends EnrichmentStep[Type] {

  override def steps: Seq[SingleStep] = enums +: super.steps

  /**
   * Puts trait related information into the denotation table
   *
   * @return
   */
  protected def enums: SingleStep = typeDef => table => typeDef match {
    case (ref, t: EnumTrait) =>
      Map("enums" -> mapForEnumTrait(ref, t)(table))
    case _ => empty
  }

  def mapForEnumTrait(k: Reference, v: EnumTrait)(table: DenotationTable): Map[String, Object] =
    Map(
      "name" -> memberNameDenotation(table, k),
      "type_name" -> typeNameDenotation(table, v.tpe.name),
      "imports" -> v.imports,
      "leaves" -> (v.leaves map mapForEnumObject(table, k))
    )

  def mapForEnumObject(table: DenotationTable, k: Reference)(v: EnumObject): Map[String, Object] =
    Map(
      "name" -> memberNameDenotation(table, v.name),
      "value" -> cleanItemValue(v)
    )

  private def cleanItemValue(v: EnumObject): String =
    v.tpe match {
      case s: Str => StringUtil.quoteIfNeeded(v.fieldValue)
      case _ => v.fieldValue
    }

}

trait TraitsStep extends EnrichmentStep[Type] {

  override def steps: Seq[SingleStep] = traits +: super.steps

  /**
   * Puts trait related information into the denotation table
   *
   * @return
   */
  protected def traits: SingleStep = typeDef => table => typeDef match {
    case (ref, t: TypeDef) if app.discriminators.contains(ref) =>
      Map("traits" -> typeDefProps(ref, t)(table))
    case _ => empty
  }

  protected def typeDefProps(k: Reference, t: Type)(table: DenotationTable): Map[String, Any] // FIXME should be defined only once
}

trait AliasesStep extends EnrichmentStep[Type] {

  override def steps: Seq[SingleStep] = aliases +: super.steps

  /**
   * Puts type related information into the denotation table
   *
   * @return
   */
  protected val aliases: SingleStep = typeDef => table => typeDef match {
    case (ref, t: EnumType) => empty
    case (ref, t: Container) =>
      Map("aliases" -> aliasProps(ref, t)(table))

    case (k, v: Null) =>
      Map("aliases" -> mapForAlias(k, v)(table))

    case (k, v: TypeRef) =>
      Map("aliases" -> mapForAlias(v.name, v)(table))
    case _ => empty
  }

  private def aliasProps(k: Reference, v: Container)(table: DenotationTable): Map[String, Any] = {
    Map(
      "name" -> typeNameDenotation(table, k),
      "alias" -> v.name.simple,
      "underlying_type" -> v.nestedTypes.map { t =>
        abstractTypeNameDenotation(table, t.name).getOrElse(typeNameDenotation(table, t.name))
      }.mkString("[", ", ", "]"),
      "imports" -> v.allImports
    )
  }

  private def mapForAlias(k: Reference, v: Type)(table: DenotationTable): Map[String, Object] = {
    Map(
      "name" -> memberNameDenotation(table, k),
      "alias" -> typeNameDenotation(table, v.name),
      "imports" -> v.imports,
      "underlying_type" -> ""
    )
  }
}