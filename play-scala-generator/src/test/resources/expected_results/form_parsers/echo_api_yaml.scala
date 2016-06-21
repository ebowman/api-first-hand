
package echo

import play.api.libs.Files.TemporaryFile
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{AnyContent, Request}
import de.zalando.play.controllers.PlayBodyParsing


import de.zalando.play.controllers.PlayPathBindables


object FormDataParser {



    def postParseForm(request: Request[AnyContent]):Either[Seq[String],(PostName, PostName)] = {
        val contentType = request.contentType.getOrElse("application/x-www-form-urlencoded")
        def fromDataParts(data: Map[String, Seq[String]], files: Map[String, Option[FilePart[TemporaryFile]]], useFiles: Boolean):Either[Seq[String],(PostName, PostName)] = {
            val name: Either[String,PostName] = PlayBodyParsing.fromParameters[PostName]("form")("name", data)
            val year: Either[String,PostName] = PlayBodyParsing.fromParameters[PostName]("form")("year", data)
            val all = Seq(name, year)
            val errors = all.filter(_.isLeft).flatMap(_.left.toSeq)
            if (errors.nonEmpty) Left(errors) else Right((name.right.toOption.get, year.right.toOption.get))
        }
        contentType.toLowerCase match {
            
            case other =>
                Left(Seq("Content type " + other + " is not supported"))
        }
    }

}
