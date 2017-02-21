package form_data


    import scala.math.BigInt
    import java.io.File

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class MultipartPostResponses200(name: Option[String], year: Option[BigInt], fileSize: Option[BigInt], fileName: Option[String]) 
    case class BothPostResponses200(name: Option[String], year: Option[BigInt], avatarSize: Option[BigInt], ringtoneSize: Option[BigInt]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val BothPostResponses200Writes: Writes[BothPostResponses200] = new Writes[BothPostResponses200] {
        def writes(ss: BothPostResponses200) =
          Json.obj(
            "name" -> ss.name, 
            "year" -> ss.year, 
            "avatarSize" -> ss.avatarSize, 
            "ringtoneSize" -> ss.ringtoneSize
          )
        }
    implicit val MultipartPostResponses200Writes: Writes[MultipartPostResponses200] = new Writes[MultipartPostResponses200] {
        def writes(ss: MultipartPostResponses200) =
          Json.obj(
            "name" -> ss.name, 
            "year" -> ss.year, 
            "fileSize" -> ss.fileSize, 
            "fileName" -> ss.fileName
          )
        }
    }
}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {



import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_FileQuery = PlayPathBindables.queryBindableFile
    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt
    implicit val bindable_OptionFileQuery: QueryStringBindable[Option[File]] = PlayPathBindables.createOptionQueryBindable[File]
    implicit val bindable_OptionBigIntQuery: QueryStringBindable[Option[BigInt]] = PlayPathBindables.createOptionQueryBindable[BigInt]

}