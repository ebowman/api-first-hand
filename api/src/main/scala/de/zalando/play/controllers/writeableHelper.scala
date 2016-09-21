package de.zalando.play.controllers

import akka.util.ByteString
import play.api.http.Writeable
import play.api.mvc.Results.{ Redirect, Status }
import play.api.mvc.{ AnyContentAsMultipartFormData, RequestHeader, Results }

import scala.language.implicitConversions

case class WriteableWrapper[T](w: Writeable[T], m: Manifest[T])

object WriteableWrapper {
  implicit def writeable2wrapper[T](w: Writeable[T])(implicit m: Manifest[T]): WriteableWrapper[T] =
    WriteableWrapper(w, m)
  implicit val anyContentAsMultipartFormWritable: Writeable[AnyContentAsMultipartFormData] = {
    MultipartFormDataWritable.singleton.map(_.mdf)
  }
}

/**
 * @since 28.02.2016.
 */
object ResponseWriters extends ResponseWritersBase

trait ResponseWritersBase {

  type ContentType = String

  def custom: Seq[WriteableWrapper[_]] = Seq.empty

  case class choose[T](mimeType: ContentType) {
    def apply[R <: Any](registry: Seq[WriteableWrapper[_]] = custom)(implicit m: Manifest[R]): Option[Writeable[R]] =
      registry filter {
        _.w.contentType.exists(_ == mimeType)
      } find { p =>
        m.runtimeClass.isAssignableFrom(p.m.runtimeClass)
      } map {
        _.asInstanceOf[WriteableWrapper[R]]
      } map (_.w)
  }

}

object WrappedBodyParsers extends WrappedBodyParsersBase

trait WrappedBodyParsersBase {
  implicit def parser2parserWrapper[T](p: Parser[T])(implicit m: Manifest[T]): ParserWrapper[T] = ParserWrapper(p, m)
  type Parser[T] = ByteString => T
  case class ParserWrapper[T](p: Parser[T], m: Manifest[T])
  val custom: Seq[(String, ParserWrapper[_])] = Seq.empty
  def anyParser[T](implicit manifest: Manifest[T]): Seq[(String, Parser[T])] =
    custom.filter(_._2.m.runtimeClass.isAssignableFrom(manifest.runtimeClass)).map { e =>
      e.copy(_2 = e._2.asInstanceOf[ParserWrapper[T]].p)
    }
  def optionParser[T](implicit manifest: Manifest[T]): Seq[(String, Parser[Option[T]])] = anyParser[Option[T]]
}

trait ResultWrapper[ResultT] {
  val emptyByteString = akka.util.CompactByteString.empty
  def statusCode: Int
  def result: ResultT
  def writer: String => Option[Writeable[ResultT]]
  def toResult(mimeType: String): Option[play.api.mvc.Result] =
    if (statusCode / 100 == 3)
      Option(Redirect(result.toString, statusCode))
    else
      writer(mimeType).map(Status(statusCode)(result)(_))
}
