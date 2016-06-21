package string_formats_validation

package object yaml {

    import org.joda.time.DateTime
    import org.joda.time.LocalDate
    import de.zalando.play.controllers.BinaryString
    import BinaryString._
    import de.zalando.play.controllers.Base64String
    import Base64String._

    import de.zalando.play.controllers.PlayPathBindables



    type StringPostString_optional = Option[String]
    type StringPostDate_time_required = DateTime
    type StringPostDate_required = LocalDate
    type StringPostPassword_optional = Option[String]
    type StringPostDate_optional = Option[LocalDate]
    type StringPostString_required = String
    type StringPostBinary_optional = Option[BinaryString]
    type StringPostPassword_required = String
    type String2PostResponses200 = Null
    type String2PostBinary_required = BinaryString
    type StringPostBase64required = Base64String
    type StringPostDate_time_optional = Option[DateTime]
    type StringPostBase64optional = Option[Base64String]




    implicit val bindable_DateTimeQuery = PlayPathBindables.queryBindableDateTime

    implicit val bindable_LocalDateQuery = PlayPathBindables.queryBindableLocalDate

    

    implicit val bindable_Base64StringQuery = PlayPathBindables.queryBindableBase64String

    implicit val bindable_OptionStringQuery = PlayPathBindables.createOptionQueryBindable[String]

    implicit val bindable_OptionLocalDateQuery = PlayPathBindables.createOptionQueryBindable[LocalDate]

    implicit val bindable_OptionDateTimeQuery = PlayPathBindables.createOptionQueryBindable[DateTime]

    implicit val bindable_OptionBase64StringQuery = PlayPathBindables.createOptionQueryBindable[Base64String]


}
