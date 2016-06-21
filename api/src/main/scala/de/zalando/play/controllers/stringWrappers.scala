package de.zalando.play.controllers

import java.util.Base64

import scala.language.implicitConversions

/**
 * @since 20.02.2016.
 */
case class Base64String(value: String) {
  override val toString: String = Base64String.base64string2string(this)
}

object Base64String {
  implicit def string2base64string(s: String): Base64String =
    Base64String(new String(Base64.getEncoder.encode(s.getBytes)))
  implicit def base64string2string(s: Base64String): String =
    new String(Base64.getDecoder.decode(s.value))
}

case class BinaryString(value: Array[Byte])

object BinaryString {
  def fromString(s: String): BinaryString = BinaryString(s.getBytes)
  implicit def binaryString2String(s: BinaryString): String = new String(s.value)
  implicit def byteArray2binaryString(s: Array[Byte]): BinaryString = BinaryString(s)
  implicit def binaryString2byteArray(s: BinaryString): Array[Byte] = s.value
}