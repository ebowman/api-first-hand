package form_data


    import java.io.File
    import scala.math.BigInt

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type MultipartPostAvatar = Option[File]
    type BothPostResponses200Name = Option[String]
    type MultipartPostName = String
    type BothPostRingtone = File
    type BothPostYear = Option[BigInt]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_FileQuery = PlayPathBindables.queryBindableFile
    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt
    implicit val bindable_OptionFileQuery: QueryStringBindable[Option[File]] = PlayPathBindables.createOptionQueryBindable[File]
    implicit val bindable_OptionBigIntQuery: QueryStringBindable[Option[BigInt]] = PlayPathBindables.createOptionQueryBindable[BigInt]

}
//noinspection ScalaStyle
package yaml {


    case class MultipartPostResponses200(name: BothPostResponses200Name, year: BothPostYear, fileSize: BothPostYear, fileName: BothPostResponses200Name) 
    case class BothPostResponses200(name: BothPostResponses200Name, year: BothPostYear, avatarSize: BothPostYear, ringtoneSize: BothPostYear) 


}
