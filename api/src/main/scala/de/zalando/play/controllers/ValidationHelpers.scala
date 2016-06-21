package de.zalando.play.controllers

import play.api.data.validation._

/**
 * @since 03.09.2015
 */
// Parsing error to display to the user of the API
case class ParsingError(messages: Seq[String], args: Seq[Any] = Nil)

trait Validator {
  def errors: Seq[ParsingError]
}
/**
 * This trait allows recursive validation of complex types
 * The idea is like this:
 *   - for primitive types, some {@Constraint}s can be defined
 *   - {@Constraint}s then wrapped into {@ValidationBase}s
 *   - {@ValidationBase}s then combined inside of the {@RecursiveValidator}
 */
trait RecursiveValidator extends Validator {
  def validators: Seq[Validator]

  override def errors: Seq[ParsingError] = validators.flatMap(_.errors)

}

/**
 * This is a wrapper for a constraint for primitive type
 * @tparam T
 */
trait ValidationBase[T] extends Validator {
  def instance: T
  def constraints: Seq[Constraint[T]]

  // helper to convert failing constraints to errors
  override def errors: Seq[ParsingError] = {
    constraints.map(_(instance)).collect {
      case Invalid(errors) => errors.toSeq
    }.flatten.map(ve => ParsingError(ve.messages, ve.args))
  }
}

object PlayValidations extends Constraints {

  /**
   * Defines a ‘lowerCase’ constraint for `String` values, i.e. one in which string
   * should be non-empty and lowercase. Has no direct relationship with swagger,
   * for testing purposes
   *
   * '''name'''[constraint.lowerCase]
   * '''error'''[error.lowerCase]
   */
  def lowerCase: Constraint[String] = Constraint[String]("constraint.lowerCase") { o =>
    if (o == null) Invalid(ValidationError("error.required"))
    else if (o.trim.isEmpty) Invalid(ValidationError("error.lowerCase")) else Valid
  }
  /**
   * Defines a ‘multipleOf’ constraint for `Number` values, i.e. one in which a number
   * valid against "multipleOf" if the result of the division of the instance by
   * this keyword's value is an integer
   *
   * '''name'''[constraint.multipleOf]
   * '''error'''[error.multipleOf]
   */
  def multipleOf[T](mOf: T)(implicit integral: scala.math.Integral[T]): Constraint[T] = Constraint[T]("constraint.multipleOf") { o =>
    if (o == null) Invalid(ValidationError("error.required"))
    else if (integral.rem(o, mOf) != 0) Invalid(ValidationError("error.multipleOf")) else Valid
  }

  /**
   * Defines a ‘enum’ constraint for `Array` values
   * Items must be comma-separated, commas inside of the elements of the list must be escaped with comma
   *
   * '''name'''[constraint.enum]
   * '''error'''[error.enum]
   */
  def enum[T <: String](commaSeparatedList: String): Constraint[T] = Constraint[T]("constraint.enum") { o =>
    def onlyAllowedItems: Boolean = {
      val items: Seq[String] = commaSeparatedList.split(",[^,]").map(_.replaceAll(",,", ",")).toSeq
      o.map { _.toString }.forall { s => items.contains(s) }
    }
    if (o == null) Invalid(ValidationError("error.required"))
    else if (commaSeparatedList.isEmpty) Invalid(ValidationError("error.enum.empty"))
    else if (onlyAllowedItems) Valid
    else Invalid(ValidationError("error.enum.not.allowed", o))

  }

  /**
   * Defines a ‘maxItems’ constraint for `Array` values
   * An array instance is valid against "maxItems" if its size is less than, or equal to, the value of this keyword.
   *
   * '''name'''[constraint.maxItems]
   * '''error'''[error.maxItems]
   */
  def maxItems[T <: ArrayWrapper[_]](maxItems: Int): Constraint[T] = Constraint[T]("constraint.maxItems") { o =>
    if (o == null) Invalid(ValidationError("error.required"))
    else if (o.items.size > maxItems) Invalid(ValidationError("error.maxItems")) else Valid
  }

  /**
   * Defines a ‘minItems’ constraint for `Array` values
   * An array instance is valid against "minItems" if its size is greater than, or equal to, the value of this keyword.
   *
   * '''name'''[constraint.minItems]
   * '''error'''[error.minItems]
   */
  def minItems[T <: ArrayWrapper[_]](minItems: Int): Constraint[T] = Constraint[T]("constraint.minItems") { o =>
    if (o == null) Invalid(ValidationError("error.required"))
    else if (o.items.size < minItems) Invalid(ValidationError("error.minItems")) else Valid
  }

  /**
   * Defines a ‘uniqueItems’ constraint for `Array` values
   * If this keyword has boolean value false, the instance validates successfully.
   * If it has boolean value true, the instance validates successfully if all of its elements are unique.
   *
   * '''name'''[constraint.uniqueItems]
   * '''error'''[error.uniqueItems]
   */
  def uniqueItems[T <: Seq[_]](uniqueItems: Boolean = false): Constraint[T] = Constraint[T]("constraint.uniqueItems") { o =>
    if (!uniqueItems) Valid
    else if (o == null) Invalid(ValidationError("error.required"))
    else if (o.size != o.distinct.size) Invalid(ValidationError("error.uniqueItems")) else Valid
  }

  /**
   * Defines a ‘maxProperties’ constraint for `CatchAll` values
   * An object instance is valid against "maxProperties" if its number of properties
   * is less than, or equal to, the value of this keyword.
   *
   * '''name'''[constraint.maxProperties]
   * '''error'''[error.maxProperties]
   */
  def maxProperties[T <: Map[String, Any]](maxProperties: Int): Constraint[T] = Constraint[T]("constraint.maxProperties") { o =>
    if (o == null) Invalid(ValidationError("error.required"))
    else if (o.size > maxProperties) Invalid(ValidationError("error.maxProperties")) else Valid
  }

  /**
   * Defines a ‘minProperties’ constraint for `CatchAll` values
   * An object instance is valid against "minProperties" if its number of properties
   * is greater than, or equal to, the value of this keyword.
   *
   * '''name'''[constraint.minProperties]
   * '''error'''[error.minProperties]
   */
  def minProperties[T <: Map[String, Any]](minProperties: Int): Constraint[T] = Constraint[T]("constraint.minProperties") { o =>
    if (o == null) Invalid(ValidationError("error.required"))
    else if (o.size < minProperties) Invalid(ValidationError("error.minProperties")) else Valid
  }

}
