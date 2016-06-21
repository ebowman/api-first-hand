package de.zalando.play.controllers

import java.nio.file.{ Files, Paths }

import akka.util.ByteString
import play.api.http.{ HeaderNames, Writeable }
import play.api.libs.Files.TemporaryFile
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{ Codec, MultipartFormData }

/**
 * @author slasch
 * @since 04.05.2016.
 *
 * taken from <a href="http://tech.fongmun.com/post/125479939452/test-multipartformdata-in-play">here</a>
 */
object MultipartFormDataWritable {

  val boundary = "--------ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"

  def formatDataParts(data: Map[String, Seq[String]]): ByteString = {
    val dataParts = data.flatMap {
      case (key, values) =>
        values.map { value =>
          val name = s""""$key""""
          s"--$boundary\r\n${HeaderNames.CONTENT_DISPOSITION}: form-data; name=$name\r\n\r\n$value\r\n"
        }
    }.mkString("")
    val bytes: ByteString = Codec.utf_8.encode(dataParts)
    bytes
  }

  def filePartHeader(file: FilePart[TemporaryFile]): ByteString = {
    val name = s""""${file.key}""""
    val filename = s""""${file.filename}""""
    val contentType = file.contentType.map { ct =>
      s"${HeaderNames.CONTENT_TYPE}: $ct\r\n"
    }.getOrElse("")
    Codec.utf_8.encode(s"--$boundary\r\n${HeaderNames.CONTENT_DISPOSITION}: form-data; name=$name; filename=$filename\r\n$contentType\r\n")
  }

  val singleton = Writeable[MultipartFormData[TemporaryFile]](
    transform = { form: MultipartFormData[TemporaryFile] =>
    formatDataParts(form.dataParts) ++
      form.files.flatMap { file =>
        val fileBytes = Files.readAllBytes(Paths.get(file.ref.file.getAbsolutePath))
        filePartHeader(file) ++ fileBytes ++ Codec.utf_8.encode("\r\n")
      } ++
      Codec.utf_8.encode(s"--$boundary--")
  },
    contentType = Some(s"multipart/form-data; boundary=$boundary")
  )
}