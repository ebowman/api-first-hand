package de.zalando.play.controllers

import java.io.File

import akka.util.ByteString
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import de.zalando.play.controllers.WrappedBodyParsers.Parser
import play.api.Logger
import play.api.http.Status._
import play.api.http._
import play.api.libs.Files.TemporaryFile
import play.api.mvc.{ BodyParser, BodyParsers, QueryStringBindable, RequestHeader }
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.Results.Status

import scala.language.implicitConversions
import scala.reflect.ClassTag
import scala.util._

/**
 * @since 02.09.2015
 */
object PlayBodyParsing extends PlayBodyParsing {

  /**
   * Returns proper jackson mapper for given mime type
   *
   * @param mimeType the mimeType of the required mapper
   * @return
   */
  def jacksonMapper(mimeType: String): ObjectMapper = {
    //noinspection ScalaStyle
    assert(mimeType != null)
    val factory = WriterFactories.factories(mimeType)
    val mapper = new ObjectMapper(factory)
    mapper.registerModule(DefaultScalaModule)
    mapper
  }

  import play.api.libs.iteratee.Execution.Implicits.trampoline
  /**
   * Parser factory for optional bodies
   *
   * @param mimeType  name of the parser
   * @param errorMsg  error message to return if an input cannot be parsed
   * @param maxLength the maximal length of the content
   * @param tag       the ClassTag to use at runtime
   * @tparam T the type of the input the parser should be created for
   * @return BodyParser for the type Option[T]
   */
  def optionParser[T](
    mimeType: Option[MediaType] => String,
    customParsers: Seq[(String, Parser[Option[T]])],
    errorMsg: String,
    maxLength: Long = parse.DefaultMaxTextLength.toLong
  )(requestHeader: RequestHeader)(implicit oTag: ClassTag[Option[T]], tag: ClassTag[T]): BodyParser[Option[T]] =
    parse.raw(maxLength = maxLength).map {
      _.asBytes(maxLength).flatMap { byteString =>
        if (byteString.nonEmpty) {
          parserCore(mimeType, customParsers, byteString, requestHeader.mediaType)
        } else
          None

      }
    }

  /**
   * Parser factory for any type
   *
   * @param mimeType  name of the parser
   * @param errorMsg  error message to return if an input cannot be parsed
   * @param maxLength the maximal length of the content
   * @param tag       the ClassTag to use at runtime
   * @tparam T the type of the input the parser should be created for
   * @return BodyParser for the type T
   */
  def anyParser[T](
    mimeType: Option[MediaType] => String,
    customParsers: Seq[(String, Parser[T])],
    errorMsg: String,
    maxLength: Long = parse.DefaultMaxTextLength.toLong
  )(requestHeader: RequestHeader)(implicit tag: ClassTag[T]): BodyParser[T] =
    parse.raw(maxLength = maxLength).map { rawBuffer =>
      parserCore(mimeType, customParsers, rawBuffer.asBytes(maxLength).getOrElse(ByteString.empty), requestHeader.mediaType)
    }

  private def parserCore[T](
    mimeType: (Option[MediaType]) => String,
    customParsers: Seq[(String, Parser[T])],
    bytes: ByteString, mediaType: Option[MediaType]
  )(implicit tag: ClassTag[T]): T = {
    val mimeTypeName = mimeType(mediaType)
    val jacksonParser: Parser[T] =
      byteString => jacksonMapper(mimeTypeName).readValue(bytes.toArray, tag.runtimeClass.asInstanceOf[Class[T]])
    // TODO default play parsers could be used here as well
    val parser = customParsers.find(_._1 == mimeTypeName).map(_._2).getOrElse {
      jacksonParser
    }
    parser(bytes)
  }

  /**
   * Converts parsing errors to Writeable
   */
  def parsingErrors2Writable(mimeType: String): Writeable[Seq[ParsingError]] =
    Writeable(parsingErrors2Bytes(mimeType), Some(mimeType))

  def anyToWritable[T <: Any]: String => Writeable[T] = mimeType =>
    Writeable(w => ByteString(jacksonMapper(mimeType).writeValueAsBytes(w)), Some(mimeType))

  /**
   * Converts anything of type Either[Throwable, T] to Writeable
   */
  def eitherToWritable[T](mimeType: String): Writeable[Either[Throwable, T]] =
    Writeable(eitherToT(mimeType), Some(mimeType))

  private def eitherToT[T](mimeType: String): (Either[Throwable, T]) => ByteString =
    (t: Either[Throwable, T]) => {
      val result = t match {
        case Right(rt) => rt
        case Left(throwable) => throwable.getLocalizedMessage
      }
      ByteString(jacksonMapper(mimeType).writeValueAsBytes(result))
    }

  private def parsingErrors2Bytes(mimeType: String): Seq[ParsingError] => ByteString = errors =>
    ByteString(jacksonMapper(mimeType).writeValueAsBytes(errors))

  implicit def writers[T]: String => Option[Writeable[T]] =
    mimeType => Try(Some(PlayBodyParsing.anyToWritable[T](mimeType))).recover {
      case _: java.util.NoSuchElementException => None
    }.get
}

trait PlayBodyParsing extends BodyParsers {
  val logger = Logger.logger

  type ContentMap = Map[Int, PartialFunction[String, Writeable[Any]]]

  def merge(m1: ContentMap, m2: ContentMap): ContentMap = {
    val onlyFirst = m1.filterKeys(!m2.keySet.contains(_))
    val onlySecond = m2.filterKeys(!m1.keySet.contains(_))
    val both = m1.filterKeys(m2.keySet.contains)
    val merged = both map {
      case (code, f) =>
        code -> f.orElse(m2(code))
    }
    onlyFirst ++ onlySecond ++ merged
  }

  def negotiateContent(acceptedTypes: Seq[MediaRange], providedTypes: Seq[String]): Option[String] =
    acceptedTypes.sorted collectFirst {
      case mr: MediaRange if providedTypes.exists(mr.accepts) => providedTypes.find(mr.accepts).get
    }

  def defaultErrorMapping: PartialFunction[Throwable, Status] = {
    case _: IllegalArgumentException => Status(BAD_REQUEST)
    case _: IndexOutOfBoundsException => Status(NOT_FOUND)
    case _ => Status(INTERNAL_SERVER_ERROR)
  }

  /**
   * Helper method to parse parameters sent as Headers
   */
  def fromParameters[T](place: String)(key: String, headers: Map[String, Seq[String]], default: Option[T] = None)(implicit binder: QueryStringBindable[T]): Either[String, T] =
    binder.bind(key, headers).getOrElse {
      default.map(d => Right(d)).getOrElse(Left(s"Missing $place parameter(s) for '$key'"))
    }

  /**
   * Helper methods to parse files
   */
  def fromFileOptional[T <: Option[File]](name: String, file: Option[FilePart[TemporaryFile]]): Either[Nothing, Option[File]] =
    Right(file.map(_.ref.file))

  def fromFileRequired[T <: File](name: String, file: Option[FilePart[TemporaryFile]]): Either[String, File] = file match {
    case Some(filePart) => Right(filePart.ref.file)
    case None => Left(s"Missing file parameter for '$name'")
  }

}
