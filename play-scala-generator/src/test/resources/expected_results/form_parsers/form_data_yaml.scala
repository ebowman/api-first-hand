
package form_data.yaml

import play.api.libs.Files.TemporaryFile
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{AnyContent, Request}
import de.zalando.play.controllers.PlayBodyParsing

import scala.math.BigInt
import java.io.File

import de.zalando.play.controllers.PlayPathBindables


object FormDataParser {

    def postmultipartParseForm(request: Request[AnyContent]):Either[Seq[String],(String, Option[BigInt], Option[File])] = {
        val contentType = request.contentType.getOrElse("application/x-www-form-urlencoded")
        def fromDataParts(data: Map[String, Seq[String]], files: Map[String, Option[FilePart[TemporaryFile]]], useFiles: Boolean):Either[Seq[String],(String, Option[BigInt], Option[File])] = {
            val name: Either[String,String] = PlayBodyParsing.fromParameters[String]("form")("name", data)
            val year: Either[String,Option[BigInt]] = PlayBodyParsing.fromParameters[Option[BigInt]]("form")("year", data)
            val avatar: Either[String,Option[File]] = if (useFiles) PlayBodyParsing.fromFileOptional("avatar", files("avatar")) else PlayBodyParsing.fromParameters[Option[File]]("form")("avatar", data)
            val all = Seq(name, year, avatar)
            val errors = all.filter(_.isLeft).flatMap(_.left.toSeq)
            if (errors.nonEmpty) Left(errors) else Right((name.right.toOption.get, year.right.toOption.get, avatar.right.toOption.get))
        }
        contentType.toLowerCase match {
            
            case "multipart/form-data" => request.body.asMultipartFormData.map { form =>
                val files: Map[String, Option[FilePart[TemporaryFile]]] =
                (("avatar", form.file("avatar")) :: Nil).toMap
                fromDataParts(form.dataParts, files, useFiles = true)
            }.getOrElse(Left(Seq("Could not find 'multipart/form-data' body")))
            
            case other =>
                Left(Seq("Content type " + other + " is not supported"))
        }
    }


    def postbothParseForm(request: Request[AnyContent]):Either[Seq[String],(String, Option[BigInt], Option[File], File)] = {
        val contentType = request.contentType.getOrElse("application/x-www-form-urlencoded")
        def fromDataParts(data: Map[String, Seq[String]], files: Map[String, Option[FilePart[TemporaryFile]]], useFiles: Boolean):Either[Seq[String],(String, Option[BigInt], Option[File], File)] = {
            val name: Either[String,String] = PlayBodyParsing.fromParameters[String]("form")("name", data)
            val year: Either[String,Option[BigInt]] = PlayBodyParsing.fromParameters[Option[BigInt]]("form")("year", data)
            val avatar: Either[String,Option[File]] = if (useFiles) PlayBodyParsing.fromFileOptional("avatar", files("avatar")) else PlayBodyParsing.fromParameters[Option[File]]("form")("avatar", data)
            val ringtone: Either[String,File] = if (useFiles) PlayBodyParsing.fromFileRequired("ringtone", files("ringtone")) else PlayBodyParsing.fromParameters[File]("form")("ringtone", data)
            val all = Seq(name, year, avatar, ringtone)
            val errors = all.filter(_.isLeft).flatMap(_.left.toSeq)
            if (errors.nonEmpty) Left(errors) else Right((name.right.toOption.get, year.right.toOption.get, avatar.right.toOption.get, ringtone.right.toOption.get))
        }
        contentType.toLowerCase match {
            case "application/x-www-form-urlencoded" => request.body.asFormUrlEncoded.map { form =>
                val noFiles = Map.empty[String, Option[FilePart[TemporaryFile]]]
                fromDataParts(form, noFiles, useFiles = false)
            }.getOrElse(Left(Seq("Could not find 'application/x-www-form-urlencoded' body")))
            
            case "multipart/form-data" => request.body.asMultipartFormData.map { form =>
                val files: Map[String, Option[FilePart[TemporaryFile]]] =
                (("avatar", form.file("avatar")) :: ("ringtone", form.file("ringtone")) :: Nil).toMap
                fromDataParts(form.dataParts, files, useFiles = true)
            }.getOrElse(Left(Seq("Could not find 'multipart/form-data' body")))
            
            case other =>
                Left(Seq("Content type " + other + " is not supported"))
        }
    }


    def posturl_encodedParseForm(request: Request[AnyContent]):Either[Seq[String],(String, Option[BigInt], File)] = {
        val contentType = request.contentType.getOrElse("application/x-www-form-urlencoded")
        def fromDataParts(data: Map[String, Seq[String]], files: Map[String, Option[FilePart[TemporaryFile]]], useFiles: Boolean):Either[Seq[String],(String, Option[BigInt], File)] = {
            val name: Either[String,String] = PlayBodyParsing.fromParameters[String]("form")("name", data)
            val year: Either[String,Option[BigInt]] = PlayBodyParsing.fromParameters[Option[BigInt]]("form")("year", data)
            val avatar: Either[String,File] = if (useFiles) PlayBodyParsing.fromFileRequired("avatar", files("avatar")) else PlayBodyParsing.fromParameters[File]("form")("avatar", data)
            val all = Seq(name, year, avatar)
            val errors = all.filter(_.isLeft).flatMap(_.left.toSeq)
            if (errors.nonEmpty) Left(errors) else Right((name.right.toOption.get, year.right.toOption.get, avatar.right.toOption.get))
        }
        contentType.toLowerCase match {
            case "application/x-www-form-urlencoded" => request.body.asFormUrlEncoded.map { form =>
                val noFiles = Map.empty[String, Option[FilePart[TemporaryFile]]]
                fromDataParts(form, noFiles, useFiles = false)
            }.getOrElse(Left(Seq("Could not find 'application/x-www-form-urlencoded' body")))
            
            case other =>
                Left(Seq("Content type " + other + " is not supported"))
        }
    }

}
