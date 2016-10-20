package string_formats_validation


    import java.time.LocalDate
    import de.zalando.play.controllers.BinaryString
    import BinaryString._
    import java.time.ZonedDateTime
    import de.zalando.play.controllers.Base64String
    import Base64String._

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type StringPostString_optional = Option[String]
    type StringPostPassword_optional = Option[String]
    type StringPostDate_optional = Option[LocalDate]
    type StringPostBinary_optional = Option[BinaryString]
    type String2PostResponses200 = Null
    type StringPostDate_time_optional = Option[ZonedDateTime]
    type StringPostBase64optional = Option[Base64String]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_DateTimeQuery = PlayPathBindables.queryBindableDateTime
    implicit val bindable_LocalDateQuery = PlayPathBindables.queryBindableLocalDate
    
    implicit val bindable_Base64StringQuery = PlayPathBindables.queryBindableBase64String
    implicit val bindable_OptionStringQuery: QueryStringBindable[Option[String]] = PlayPathBindables.createOptionQueryBindable[String]
    implicit val bindable_OptionLocalDateQuery: QueryStringBindable[Option[LocalDate]] = PlayPathBindables.createOptionQueryBindable[LocalDate]
    implicit val bindable_OptionZonedDateTimeQuery: QueryStringBindable[Option[ZonedDateTime]] = PlayPathBindables.createOptionQueryBindable[ZonedDateTime]
    implicit val bindable_OptionBase64StringQuery: QueryStringBindable[Option[Base64String]] = PlayPathBindables.createOptionQueryBindable[Base64String]

}
//noinspection ScalaStyle
package yaml {




}
