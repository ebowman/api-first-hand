package de.zalando.play.controllers

import java.math.BigInteger

import play.api.data.validation.ValidationError
import play.api.libs.json.{ JsError, _ }

import scala.reflect.ClassTag

object MissingDefaultWrites extends MissingDefaultWrites

trait MissingDefaultWrites extends DefaultWrites {

  /**
   * Serializer for BigInt types.
   */
  implicit object BigIntWrites extends Writes[BigInt] {
    def writes(o: BigInt): JsNumber = JsNumber(BigDecimal(o))
  }
  /**
   * Serializer for BigInteger types.
   */
  implicit object BigIntegerWrites extends Writes[BigInteger] {
    def writes(o: BigInteger): JsNumber = JsNumber(BigDecimal(o))
  }
  /**
   * Serializer for AnyVal types.
   */
  implicit object StringAnyValWrites extends Writes[StringAnyVal] {
    def writes(o: StringAnyVal): JsString = JsString(o.value)
  }

  /**
   * Serializer for ArrResult types.
   */
  implicit def arrayWrapperWrites[T](implicit fmt: Writes[T]): Writes[ArrayWrapper[T]] = new Writes[ArrayWrapper[T]] {
    def writes(o: ArrayWrapper[T]) = JsArray(o.items.map(fmt.writes))
  }
}

object MissingDefaultReads extends MissingDefaultReads

trait MissingDefaultReads extends DefaultReads {

  /**
   * Deserializer for BigInt
   */
  implicit val bigIntReads: Reads[BigInt] = Reads[BigInt] {
    case JsString(s) =>
      scala.util.control.Exception.catching(classOf[NumberFormatException])
        .opt(JsSuccess(BigInt(s)))
        .getOrElse(JsError(ValidationError("error.expected.numberformatexception")))
    case JsNumber(d) => JsSuccess(d.toBigInt())
    case _ => JsError(ValidationError("error.expected.jsnumberorjsstring"))
  }

  /**
   * Deserializer for BigInteger
   */
  implicit val javaBigIntegerReads: Reads[BigInteger] = Reads[java.math.BigInteger] {
    case JsString(s) =>
      scala.util.control.Exception.catching(classOf[NumberFormatException])
        .opt(JsSuccess(new java.math.BigInteger(s)))
        .getOrElse(JsError(ValidationError("error.expected.numberformatexception")))
    case JsNumber(d) => JsSuccess(d.toBigInt().bigInteger)
    case _ => JsError(ValidationError("error.expected.jsnumberorjsstring"))
  }

  /**
   * Deserializer for StringAnyVal
   */
  implicit def stringAnyValReads[T <: StringAnyVal: ClassTag]: Reads[T] = Reads[T] {
    case JsString(s) =>
      val instance = implicitly[ClassTag[T]].runtimeClass.getConstructor(classOf[String]).newInstance(s)
      JsSuccess(instance.asInstanceOf[T])
    case _ =>
      JsError(ValidationError("error.expected.jsstring"))
  }

}
