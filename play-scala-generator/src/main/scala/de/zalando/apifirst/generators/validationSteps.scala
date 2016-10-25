package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.{ ApiCall, Parameter, ParameterRef, StrictModel }
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.ScalaName._
import de.zalando.apifirst.StringUtil
import de.zalando.apifirst.generators.DenotationNames._
import de.zalando.apifirst.naming.Reference

/**
 * @author  slasch
 * @since   28.12.2015.
 */
trait CallValidatorsStep extends EnrichmentStep[ApiCall] with ValidatorsCommon {

  override def steps: Seq[SingleStep] = callValidators +: super.steps

  /**
   * Puts validation related information into the denotation table
   *
   * @return
   */
  protected val callValidators: SingleStep = call => table =>
    Map("validators" -> callValidatorsProps(call._1, call._2)(table))

  private def callValidatorsProps(ref: Reference, call: ApiCall)(table: DenotationTable) = call match {
    case c if c.handler.parameters.nonEmpty =>
      call.handler.parameters.foreach {
        p => assert(p.isInstanceOf[ParameterRef], "All call parameters expected to be typeRefs, but got: " + p)
      }
      Map("call_validations" -> Seq(callValidations(ref, call)(table)))
    case _ => empty
  }

  /**
   * Creates validations for ApiCall parameters.
   *
   * At this point all api call parameters validations should already be generated and only
   * needed to be sequenced together
   *
   * @return
   */
  private def callValidations(r: Reference, call: ApiCall)(table: DenotationTable): Map[String, Object] =
    Map(
      "validation_name" -> validator(r, table),
      "fields" -> call.handler.parameters.map { p =>
        Map(
          "field_name" -> escape(StringUtil.camelize("\\.", p.name.simple)),
          "type_name" -> typeNameDenotation(table, p.name),
          "validation_name" -> validator(p.name, table)
        )
      }
    )

}

trait ParametersValidatorsStep extends EnrichmentStep[Parameter] with ValidatorsCommon {

  override def steps: Seq[SingleStep] = callValidators +: super.steps

  implicit def app: StrictModel

  /**
   * Puts validation related information into the denotation table
   *
   * @return
   */
  protected val callValidators: SingleStep = parameter => table =>
    parameterValidatorsProps(parameter._1, parameter._2)(table)

  private def parameterValidatorsProps(ref: Reference, parameter: Parameter)(table: DenotationTable): Denotation = {
    val result = parametersValidations(table)(ref, parameter)
    val byType = result.groupBy(_._1).map { case (k, v) => k -> v.map(_._2) }
    Map("validators" -> byType)
  }

  /**
   * Wrapper method for real constraints generator
   * Needed to generate different constraints for different parameter types
   *
   * @return
   */
  private def parametersValidations(table: DenotationTable)(ref: Reference, param: Parameter) =
    constraints0(ref, param.typeName)(table)

  /**
   * Depending upon, what we want to validate, we can have one of following situations:
   * - Primitive types only need to be validated themself
   * - Options does not need any constraints, only primitive contents, recursive
   * - Arrays need to validate {maxItems, minItems, uniqueItems, enum} and elements recursive
   * - CatchAll need to validate {MaxProperties} and {MinProperties}
   * - TypeDefs does not need to constraint anything and fields recursive
   */
  def constraints0(types: (Reference, Type))(implicit table: DenotationTable): Validations =
    types match {
      case (r: Reference, t: PrimitiveType) =>
        Seq("primitive_validations" -> typeConstraints(r, t))
      case (r: Reference, t: Arr) =>
        tpeConstraints(r, t, "Arr", "array")
      case (r: Reference, t: ArrResult) =>
        tpeConstraints(r, t, "Arr", "array")
      case (r: Reference, t: CatchAll) =>
        tpeConstraints(r, t, "CatchAll", "catch")
      case (r: Reference, t: Opt) =>
        tpeConstraints(r, t, "Opt", "opt")
      case (r: Reference, t: EnumType) =>
        tpeConstraints(r, t, "Enum", "enum")
      case (r, t: TypeDef) =>
        typeDefConstraints(r, t)
      case (r: Reference, t: AllOf) =>
        compositeConstraints(r, t, "AllOf", "allof")
      case (r: Reference, t: OneOf) =>
        compositeConstraints(r, t, "OneOf", "oneof")
      case (r, TypeRef(ref)) if !app.findType(ref).isInstanceOf[TypeRef] =>
        constraints0(ref -> app.findType(ref))
      case (r, TypeRef(ref)) =>
        Nil
    }

  private def typeDefConstraints(r: Reference, t: TypeDef)(implicit table: DenotationTable): Seq[(String, Map[String, Any])] = {
    val fields = t.fields.flatMap { f => constraints0(f.name -> f.tpe) }
    if (r == t.name) {
      val mainType = "typedef_validations" -> typeDefValidations(r, t)
      mainType +: fields
    } else
      fields
  }

  private def tpeConstraints(r: Reference, t: Container, suffix: String, key: String)(implicit table: DenotationTable): Validations = {
    val delegate = delegateName(r, t.tpe, suffix)
    val constraints = constraints0(delegate -> t.tpe)
    if (constraints.nonEmpty)
      ((key + "_validations") -> optValidations(r, t, delegate)) +: constraints
    else
      constraints
  }

  private def compositeConstraints(r: Reference, t: Composite, suffix: String, key: String)(implicit table: DenotationTable): Seq[(String, Map[String, Any])] = {
    val descendants = t.descendants.flatMap { f =>
      val delegate = delegateName(r, f, suffix)
      constraints0(delegate -> f)
    }
    val mainType = "composite_validations" -> compositeValidations(r, t)
    mainType +: descendants
  }

  private def delegateName(r: Reference, t: Type, suffix: String): Reference = {
    t match {
      case p: PrimitiveType => r / suffix
      case t: CatchAll => r / suffix
      case _ => t.name
    }
  }

  private def typeDefValidations(r: Reference, t: TypeDef)(implicit table: DenotationTable) =
    Map(
      "validation_name" -> validator(r, table),
      "type_name" -> abstractTypeNameDenotation(table, r).getOrElse(typeNameDenotation(table, r)),
      "fields" -> t.fields.collect {
        case f if constraints0(f.name -> f.tpe).nonEmpty =>
          Map(
            "field_name" -> escape(f.name.simple),
            "validation_name" -> validator(fieldName(f), table)
          )
      }
    )

  // TODO add validations for allOf or oneOf (maxProperties, minProperties, what else?)
  // current code is probably only valid for allOf but not for oneOf
  private def compositeValidations(r: Reference, t: Composite)(implicit table: DenotationTable) = {
    val descendants = t.descendants.collect {
      case d if d.realType.isInstanceOf[TypeDef] =>
        typeDefValidations(d.name, d.realType.asInstanceOf[TypeDef])(table)
    }
    Map(
      "validation_name" -> validator(r, table),
      "type_name" -> typeNameDenotation(table, r), // need a concrete type here, abstract does not have all the properties
      "descendants" -> descendants
    )
  }

  private def optValidations(r: Reference, t: Container, delegateName: Reference)(implicit table: DenotationTable) = {
    val typeName = typeNameDenotation(table, r)
    val nonEmptyConstraints = t.meta.constraints.filterNot(_.isEmpty)
    Map(
      "restrictions" -> nonEmptyConstraints.zipWithIndex.map {
        case (c, i) =>
          Map("name" -> c, "last" -> (i == nonEmptyConstraints.length - 1))
      },
      "constraint_name" -> constraint(r, table), // restrictions and constraint_name are needed for Arr, not needed for Opt
      "delegate_validation_name" -> validator(delegateName, table),
      "validation_name" -> validator(r, table),
      "type_name" -> typeName
    )
  }

  private def typeConstraints(r: Reference, t: Type)(implicit table: DenotationTable) = {
    val typeName = t match {
      case p: PrimitiveType => p.name.typeAlias()
      case _ => typeNameDenotation(table, r)
    }
    val validation_type_name = t match {
      case _: Base64String => "String"
      case _: BinaryString => "String"
      case _ => typeName
    }
    val implicits = t match {
      case f: Flt if f.meta.constraints.exists(_.contains("multipleOf")) =>
        Seq("implicit val floatIntegral = scala.math.Numeric.FloatAsIfIntegral")
      case f: Dbl if f.meta.constraints.exists(_.contains("multipleOf")) =>
        Seq("implicit val doubleIntegral = scala.math.Numeric.DoubleAsIfIntegral")
      case _ =>
        Seq.empty[String]
    }
    Map(
      "restrictions" -> t.meta.constraints.filterNot(_.isEmpty).map { c => Map("name" -> c) },
      "constraint_name" -> constraint(r, table),
      "validation_name" -> validator(r, table),
      "type_name" -> typeName,
      "validation_type_name" -> validation_type_name,
      "implicits" -> implicits.map { i => Map("name" -> i) }
    )
  }

  def fieldName(f: Field): TypeName = if (f.tpe.isInstanceOf[PrimitiveType]) f.name else f.tpe.name

}

trait ValidatorsCommon {

  def app: StrictModel

  type Validations = Seq[(String, Map[String, Any])]

  val constraintsSuffix = "Constraints"

  def constraint(ref: Reference, table: DenotationTable): String =
    append(memberNameDenotation(table, ref), constraintsSuffix)

}