package de.zalando.play.controllers

/**
 * An abstraction over parameters of type Array
 *
 * Needed for parsing as there are many possibilities to encode an array
 * as query parameter or a header
 *
 * @author slasch
 * @since 03.01.2016.
 */
trait ArrayWrapper[+T] {
  def items: Seq[T]
  def separator: Char
  def copy[B >: T](newItems: Seq[B]): ArrayWrapper[B]
  def map[B](f: T => B): Seq[B] = items map f
  def find(f: T => Boolean): Option[T] = items find f
}

case class CsvArrayWrapper[+T](items: Seq[T]) extends ArrayWrapper[T] {
  val separator = ','
  override def copy[B >: T](newItems: Seq[B]): ArrayWrapper[B] = CsvArrayWrapper(newItems)
}
case class TsvArrayWrapper[T](items: Seq[T]) extends ArrayWrapper[T] {
  val separator = '\t'
  override def copy[B >: T](newItems: Seq[B]): ArrayWrapper[B] = TsvArrayWrapper(newItems)
}
case class SsvArrayWrapper[T](items: Seq[T]) extends ArrayWrapper[T] {
  val separator = ' '
  override def copy[B >: T](newItems: Seq[B]): ArrayWrapper[B] = SsvArrayWrapper(newItems)
}
case class PipesArrayWrapper[T](items: Seq[T]) extends ArrayWrapper[T] {
  val separator = '|'
  override def copy[B >: T](newItems: Seq[B]): ArrayWrapper[B] = PipesArrayWrapper(newItems)
}
// TODO this supposed to wrap multiple query parameters with same name, with or without []
case class MultiArrayWrapper[T](items: Seq[T]) extends ArrayWrapper[T] {
  val separator = '&'
  override def copy[B >: T](newItems: Seq[B]): ArrayWrapper[B] = MultiArrayWrapper(newItems)
}
object ArrayWrapper {
  def apply[T](format: String): Seq[T] => ArrayWrapper[T] = format.toLowerCase match {
    case "tsv" => TsvArrayWrapper.apply
    case "ssv" => SsvArrayWrapper.apply
    case "pipes" => PipesArrayWrapper.apply
    case "multi" => MultiArrayWrapper.apply
    case "csv" => CsvArrayWrapper.apply
    case other => throw new IllegalArgumentException(s"Array format $other is not supported")
  }
}