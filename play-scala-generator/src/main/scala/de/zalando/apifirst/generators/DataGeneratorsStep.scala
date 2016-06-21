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
      GENERATOR_NAME -> generatorNameForType(v, table),
      "creator_method" -> prepend("create", generator(k, table)),
      "generator" -> generator(k, table)
    )
  }

  private def enumGenerator(k: Reference, v: Type)(table: DenotationTable): Map[String, Any] = {
    Map(
      GENERATOR_NAME -> generatorNameForType(v, table),
      "creator_method" -> prepend("create", generator(k, table)),
      "generator" -> generator(k, table)
    )
  }

  private def classGenerator(k: Reference, v: Type)(table: DenotationTable): Map[String, Any] =
    containerGenerator(k, v)(table) +
      (
        "class_name" -> typeNameDenotation(table, k),
        "fields" -> typeFields(table, k).map { f =>
          Map(
            "name" -> escape(f.name.simple),
            "generator" -> generatorNameForType(f.tpe, table)
          )
        }
      )

  private val generatorNameForType: (Type, DenotationTable) => String = {
    case (s: PrimitiveType, table) => primitiveType(s, table)
    case (c: Container, table) => containerType(c, table)
    case (TypeRef(r), table) => generator(r, table)
    case (d: TypeDef, table) => generator(d.name, table)
    case (c: Composite, table) => generator(c.name, table)
    case o =>
      throw new Exception("Unexpected request for generator name for: " + o.toString)
  }

  private def containerType(c: Container, t: DenotationTable): String = {
    val innerGenerator = generatorNameForType(c.tpe, t)
    val className = typeNameDenotation(t, c.tpe.name)
    c match {
      case Opt(tpe, _) => s"Gen.option($innerGenerator)"
      case Arr(tpe, _, format) => s"""_genList($innerGenerator, "$format")"""
      case ArrResult(tpe, _) => s"Gen.containerOf[List,$className]($innerGenerator)"
      case EnumTrait(tpe, _, leaves) =>
        val choice = leaves.map { l => memberNameDenotation(t, l.name) }.mkString(", ")
        s"""Gen.oneOf(Seq($choice))"""
      case c @ CatchAll(tpe, _) => s"_genMap[String,$className](arbitrary[String], $innerGenerator)"
    }
  }

  private def primitiveType(tpe: Type, t: DenotationTable) =
    s"arbitrary[${typeNameDenotation(t, tpe.name)}]"
}