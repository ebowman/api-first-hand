package de.zalando.play.controllers

import java.io.File
import java.util.UUID

import com.fasterxml.jackson.databind.{ MappingIterator, ObjectReader, ObjectWriter }
import com.fasterxml.jackson.dataformat.csv.{ CsvMapper, CsvParser, CsvSchema }
import org.joda.time.{ DateTime, LocalDate }
import play.api.mvc.{ PathBindable, QueryStringBindable }

import scala.io.Source

/**
 * @author slasch
 * @since 03.01.2016.
 */
object PlayPathBindables {

  private def schema[T](wrapper: ArrayWrapper[T]) =
    CsvSchema.emptySchema().withColumnSeparator(wrapper.separator).withLineSeparator("\n")

  private[controllers] def createMapper =
    new CsvMapper().enable(CsvParser.Feature.WRAP_AS_ARRAY)

  private[controllers] def createReader[T](mapper: CsvMapper, wrapper: ArrayWrapper[T]) =
    mapper.readerFor(classOf[Array[String]]).`with`(schema(wrapper))

  private[controllers] def createWriter[T](mapper: CsvMapper, wrapper: ArrayWrapper[T]) =
    mapper.writer(schema(wrapper))

  private[controllers] def readArray(reader: ObjectReader)(line: String) = {
    val array = reader.readValues(line.getBytes).asInstanceOf[MappingIterator[Array[String]]]
    val resArray = if (array.hasNext) array.next() else Array.empty[String]
    resArray
  }

  private[controllers] def writeArray(writer: ObjectWriter)(items: Seq[String]): String =
    writer.writeValueAsString(items.toArray)

  implicit object pathBindableDateTime extends PathBindable.Parsing[DateTime](
    Rfc3339Util.parseDateTime,
    Rfc3339Util.writeDateTime,
    (key: String, e: Exception) => "Cannot parse parameter %s as DateTime: %s".format(key, e.getMessage)
  )

  implicit object pathBindableLocalDate extends PathBindable.Parsing[LocalDate](
    Rfc3339Util.parseDate,
    Rfc3339Util.writeDate,
    (key: String, e: Exception) => "Cannot parse parameter %s as LocalDate: %s".format(key, e.getMessage)
  )

  implicit object queryBindableDateTime extends QueryStringBindable.Parsing[DateTime](
    Rfc3339Util.parseDateTime,
    Rfc3339Util.writeDateTime,
    (key: String, e: Exception) => "Cannot parse parameter %s as DateTime: %s".format(key, e.getMessage)
  )

  implicit object queryBindableLocalDate extends QueryStringBindable.Parsing[LocalDate](
    Rfc3339Util.parseDate,
    Rfc3339Util.writeDate,
    (key: String, e: Exception) => "Cannot parse parameter %s as LocalDate: %s".format(key, e.getMessage)
  )

  implicit object pathBindableBase64String extends PathBindable.Parsing[Base64String](
    s => Base64String.string2base64string(s),
    s => Base64String.base64string2string(s),
    (key: String, e: Exception) => "Cannot parse parameter %s as Base64String: %s".format(key, e.getMessage)
  )

  implicit object queryBindableBase64String extends QueryStringBindable.Parsing[Base64String](
    s => Base64String.string2base64string(s),
    s => Base64String.base64string2string(s),
    (key: String, e: Exception) => "Cannot parse parameter %s as Base64String: %s".format(key, e.getMessage)
  )

  implicit object queryBindableFile extends QueryStringBindable.Parsing[java.io.File](
    s => tempFileFromString(s),
    s => Source.fromFile(s).getLines().mkString("\n"),
    (key: String, e: Exception) => "Cannot parse parameter %s as java.io.File: %s".format(key, e.getMessage)
  )

  implicit object pathBindableBigInt extends PathBindable.Parsing[BigInt](
    str => BigInt(str),
    bInt => bInt.toString,
    (key: String, e: Exception) => "Cannot parse parameter %s as BigInt: %s".format(key, e.getMessage)
  )

  implicit object pathBindableBigDecimal extends PathBindable.Parsing[BigDecimal](
    str => BigDecimal(str),
    bDcml => bDcml.toString,
    (key: String, e: Exception) => "Cannot parse parameter %s as BigDecimal: %s".format(key, e.getMessage)
  )

  implicit object pathBindableUUID extends PathBindable.Parsing[UUID](
    str => UUID.fromString(str),
    uuid => uuid.toString,
    (key: String, e: Exception) => "Cannot parse parameter %s as UUID: %s".format(key, e.getMessage)
  )

  implicit object queryBindableBigInt extends QueryStringBindable.Parsing[BigInt](
    str => BigInt(str),
    bInt => bInt.toString,
    (key: String, e: Exception) => "Cannot parse parameter %s as BigInt: %s".format(key, e.getMessage)
  )

  implicit object queryBindableBigDecimal extends QueryStringBindable.Parsing[BigDecimal](
    str => BigDecimal(str),
    bDcml => bDcml.toString,
    (key: String, e: Exception) => "Cannot parse parameter %s as BigDecimal: %s".format(key, e.getMessage)
  )

  implicit object queryBindableUUID extends QueryStringBindable.Parsing[UUID](
    str => UUID.fromString(str),
    uuid => uuid.toString,
    (key: String, e: Exception) => "Cannot parse parameter %s as UUID: %s".format(key, e.getMessage)
  )

  def tempFileFromString(s: String): File = {
    val prefix = "tmp_" + s.hashCode
    val f = File.createTempFile(prefix, "")
    f.deleteOnExit()
    import java.nio.file.{ Paths, Files }
    import java.nio.charset.StandardCharsets
    Files.write(Paths.get(f.getAbsolutePath), s.getBytes(StandardCharsets.UTF_8))
    f
  }
  /**
   * Factory to create PathBindable for optional values of any type
   *
   * @param tBinder
   * @tparam T
   * @return
   */
  def createOptionPathBindable[T](implicit tBinder: PathBindable[T]): PathBindable[Option[T]] = new PathBindable[Option[T]] {
    override def bind(key: String, value: String): Either[String, Option[T]] = {
      val wrap = Option(value).map(tBinder.bind(key, _))
      val result = wrap.map(_.right.map(Option.apply)).getOrElse(Right(None))
      result
    }

    override def unbind(key: String, value: Option[T]): String = value match {
      case None => null
      case Some(v) => tBinder.unbind(key, v)
    }
  }
  /**
   * Factory to create QueryBindable for optional values of any type
   *
   * @param tBinder
   * @tparam T
   * @return
   */
  def createOptionQueryBindable[T](implicit tBinder: QueryStringBindable[T]): QueryStringBindable[Option[T]] = new QueryStringBindable[Option[T]] {
    override def bind(key: String, values: Map[String, Seq[String]]): Option[Either[String, Option[T]]] = {
      val wrap = values.get(key).flatMap(_ => tBinder.bind(key, values))
      val result = wrap.map(_.right.map(Option.apply)).getOrElse(Right(None))
      Some(result)
    }

    override def unbind(key: String, value: Option[T]): String = value match {
      case None => null
      case Some(v) => tBinder.unbind(key, v)
    }
  }

  /**
   * Constructor for ArrayWrapper with specific format
   *
   * @param tBinder  this binder should be available from Play
   * @return
   */
  def createArrayWrapperPathBindable[T](format: String)(implicit tBinder: PathBindable[T]): PathBindable[ArrayWrapper[T]] =
    createArrPathBindable(ArrayWrapper[T](format)(Nil))

  /**
   * Constructor for ArrayWrapper with specific format
   *
   * @param tBinder  this binder should be available from Play
   * @return
   */
  def createArrayWrapperQueryBindable[T](format: String)(implicit tBinder: QueryStringBindable[T]): QueryStringBindable[ArrayWrapper[T]] =
    createArrQueryBindable(ArrayWrapper[T](format)(Nil))

  /**
   * Factory method for path bindables of different types
   *
   * @param wrapper  the wrapper is used to distinguish different separator chars
   * @param tBinder  the binder for underlying types
   * @tparam T       the type of array items
   * @return
   */
  def createArrPathBindable[T](wrapper: ArrayWrapper[T])(implicit tBinder: PathBindable[T]): PathBindable[ArrayWrapper[T]] = new PathBindable[ArrayWrapper[T]] {

    val mapper = createMapper
    val reader = createReader(mapper, wrapper)
    val writer = createWriter(mapper, wrapper)

    def bind(key: String, value: String): Either[String, ArrayWrapper[T]] = try {
      val line = readArray(reader)(value)
      val xs = line map { tBinder.bind(key, _) }

      val lefts = xs collect { case Left(x) => x }
      lazy val rights = xs collect { case Right(x) => x }

      lazy val success = wrapper.copy(rights.toSeq)
      if (lefts.isEmpty) Right(success) else Left(lefts.mkString("\n"))
    } catch {
      case e: Exception => Left(e.getMessage)
    }

    /**
     * Unbind method converts an ArrayWrapper to the Path string
     *
     * @param key  parameter name
     * @param w    wrapper to convert
     * @return
     */
    def unbind(key: String, w: ArrayWrapper[T]): String = writeArray(writer)(w.items map (tBinder.unbind(key, _)))

  }

  /**
   * Factory method for query bindables of different types
   *
   * @param wrapper  the wrapper is used to distinguish different separator chars
   * @param tBinder  the binder for underlying types
   * @tparam T       the type of array items
   * @return
   */
  def createArrQueryBindable[T](wrapper: ArrayWrapper[T])(implicit tBinder: QueryStringBindable[T]): QueryStringBindable[ArrayWrapper[T]] = new QueryStringBindable[ArrayWrapper[T]] {

    val mapper = createMapper
    val reader = createReader(mapper, wrapper)
    val writer = createWriter(mapper, wrapper)

    def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, ArrayWrapper[T]]] = Some(try {
      val line: Option[Seq[String]] = params.get(key) flatMap {
        case Nil => None
        case null => None
        case single :: Nil if wrapper.separator == '&' => Some(Seq(single))
        case single :: Nil => Some(readArray(reader)(single))
        case multiple if wrapper.separator == '&' => Some(multiple)
        case _ =>
          throw new IllegalArgumentException("Got multiple parameters with the same name, but parameter type is not 'multi'")
      }
      val xs = line flatMap { l => tBinder.bind(key, Map(key -> l)) }
      val lefts = xs collect { case Left(x) => x }
      lazy val rights = xs collect { case Right(x) => x }
      lazy val success = wrapper.copy(rights.toSeq)
      if (lefts.isEmpty) Right(success) else Left(lefts.mkString("\n"))
    } catch {
      case e: Exception => Left(e.getMessage)
    })

    /**
     * Unbind method converts an ArrayWrapper to the Path string
     *
     * @param key  parameter name
     * @param w    wrapper to convert
     * @return
     */
    def unbind(key: String, w: ArrayWrapper[T]): String = writeArray(writer)(w.items map (tBinder.unbind(key, _)))

  }
}