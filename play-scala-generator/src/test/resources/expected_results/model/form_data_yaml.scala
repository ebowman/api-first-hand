package form_data

package object yaml {

    import java.io.File
    import scala.math.BigInt

    import de.zalando.play.controllers.PlayPathBindables



    type MultipartPostAvatar = Option[File]
    type BothPostResponses200Name = Option[String]
    type MultipartPostName = String
    type BothPostRingtone = File
    type BothPostYear = Option[BigInt]


    case class MultipartPostResponses200(name: BothPostResponses200Name, year: BothPostYear, fileSize: BothPostYear, fileName: BothPostResponses200Name) 
    case class BothPostResponses200(name: BothPostResponses200Name, year: BothPostYear, avatarSize: BothPostYear, ringtoneSize: BothPostYear) 


    implicit val bindable_FileQuery = PlayPathBindables.queryBindableFile

    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt

    implicit val bindable_OptionFileQuery = PlayPathBindables.createOptionQueryBindable[File]

    implicit val bindable_OptionBigIntQuery = PlayPathBindables.createOptionQueryBindable[BigInt]


}
