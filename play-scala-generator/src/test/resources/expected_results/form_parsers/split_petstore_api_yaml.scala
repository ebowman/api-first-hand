
package split.petstore.api.yaml

import play.api.libs.Files.TemporaryFile
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{AnyContent, Request}
import de.zalando.play.controllers.PlayBodyParsing

import de.zalando.play.controllers.ArrayWrapper
import org.joda.time.DateTime

import de.zalando.play.controllers.PlayPathBindables


object FormDataParser {












    def updatePetWithFormParseForm(request: Request[AnyContent]):Either[Seq[String],(String, String)] = {
        val contentType = request.contentType.getOrElse("application/x-www-form-urlencoded")
        def fromDataParts(data: Map[String, Seq[String]], files: Map[String, Option[FilePart[TemporaryFile]]], useFiles: Boolean):Either[Seq[String],(String, String)] = {
            val name: Either[String,String] = PlayBodyParsing.fromParameters[String]("form")("name", data)
            val status: Either[String,String] = PlayBodyParsing.fromParameters[String]("form")("status", data)
            val all = Seq(name, status)
            val errors = all.filter(_.isLeft).flatMap(_.left.toSeq)
            if (errors.nonEmpty) Left(errors) else Right((name.right.toOption.get, status.right.toOption.get))
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
