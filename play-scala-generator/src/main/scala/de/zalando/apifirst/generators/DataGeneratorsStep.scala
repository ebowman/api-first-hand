package de.zalando.apifirst.generators

import de.zalando.apifirst.Domain._
import de.zalando.apifirst.ScalaName._
import de.zalando.apifirst.naming.Reference
import DenotationNames._

/**
 * @author slasch
 * @since 28.12.2015.
 */

trait DataGeneratorsStep extends EnrichmentStep[Type] {

  override def steps: Seq[SingleStep] = dataGenerators +: super.steps

  /**
   * Puts data generators related information into the denotation table
   *
   * @return
   */
  protected val dataGenerators: SingleStep = typeDef => table =>
    generatorProps(typeDef._1, typeDef._2)(table)

  private def generatorProps(ref: Reference, v: Type)(table: DenotationTable): Denotation = v match {
    case t: EnumTrait =>
      Map("test_data_aliases" -> enumGenerator(ref, t)(table))
    case t: EnumObject =>
      empty
    case t: Container =>
      Map("test_data_aliases" -> containerGenerator(ref, t)(table))
    case t: PrimitiveType =>
      Map("test_data_aliases" -> containerGenerator(ref, t)(table))
    case t: TypeDef if !ref.simple.startsWith("AllOf") =>
      Map("test_data_classes" -> classGenerator(ref, t)(table))
    case t: Composite =>
      Map("test_data_classes" -> classGenerator(ref, t)(table))

    case _ => empty
  }

  private def containerGenerator(k: Reference, v: Type)(table: DenotationTable): Map[String, Any] = {
    Map(
      GENERATOR_NAME -> generatorNameForType(v, table, k),
      "generator_type" -> generatorTypeNameForType(v, table, k),
      "creator_method" -> prepend("create", generator(k, table)),
      "generator" -> generator(k, table),
      "imports" -> v.realImports
    )
  }

  private def enumGenerator(k: Reference, v: Type)(table: DenotationTable): Map[String, Any] = {
    Map(
      GENERATOR_NAME -> generatorNameForType(v, table, k),
      "creator_method" -> prepend("create", generator(k, table)),
      "generator" -> generator(k, table),
      "imports" -> v.realImports
    )
  }

  private def classGenerator(k: Reference, v: Type)(table: DenotationTable): Map[String, Any] =
    containerGenerator(k, v)(table) +
      (
        "class_name" -> typeNameDenotation(table, k),
        "fields" -> typeFields(table, k).map { f =>
          Map(
            "name" -> escape(f.name.simple),
            "generator" -> generatorNameForType(f.tpe, table, k)
          )
        },
        "imports" -> v.realImports
      )

  private val generatorTypeNameForType: (Type, DenotationTable, Reference) => String = {
    case (c: Container, table, k) => containerTypeName(c, table, k)
    case _ => ""
  }

  private val generatorNameForType: (Type, DenotationTable, Reference) => String = {
    case (s: PrimitiveType, table, _) => primitiveType(s, table)
    case (c: Container, table, k) => containerType(c, table, k)
    case (TypeRef(r), table, _) => generator(r, table)
    case (d: TypeDef, table, _) => generator(d.name, table)
    case (c: Composite, table, _) => generator(c.name, table)
    case o =>
      throw new Exception("Unexpected request for generator name for: " + o.toString)
  }

  private def containerType(c: Container, t: DenotationTable, ref: Reference): String = {
    val innerGenerator = generatorNameForType(c.tpe, t, ref)
    val className = typeNameDenotation(t, c.tpe.name)
    c match {
      case Opt(tpe, _) => s"Gen.option($innerGenerator)"
      case Arr(tpe, _, format) => s"""_genList($innerGenerator, "$format")"""
      case ArrResult(tpe, _) => s"Gen.containerOf[List,$className]($innerGenerator)"
      case e @ EnumTrait(tpe, _, leaves) =>
        val choice = leaves.map { l => memberNameDenotation(t, l.name) }.mkString(", ")
        val outer = memberNameDenotation(t, ref)
        s"""{ import $outer._ ; Gen.oneOf(Seq($choice)) }"""
      case c @ CatchAll(tpe, _) => s"_genMap[String,$className](arbitrary[String], $innerGenerator)"
    }
  }

  private def containerTypeName(c: Container, t: DenotationTable, ref: Reference): String = {
    lazy val className = typeNameDenotation(t, c.tpe.name)
    c match {
      case ArrResult(_, _) => s": Gen[List[$className]]"
      case CatchAll(_, _) => s": Gen[Map[String, $className]]"
      case _ => ""
    }
  }

  private def primitiveType(tpe: Type, t: DenotationTable) =
    s"arbitrary[${typeNameDenotation(t, tpe.name)}]"
}