package string_formats


    import de.zalando.play.controllers.Base64String
    import Base64String._
    import de.zalando.play.controllers.BinaryString
    import BinaryString._
    import org.joda.time.DateTime
    import java.util.UUID
    import org.joda.time.LocalDate

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type GetBase64 = Option[Base64String]
    type GetPetId = BinaryString
    type GetDate_time = Option[DateTime]
    type GetUuid = Option[UUID]
    type GetDate = Option[LocalDate]
    type GetResponses200 = Null


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_Base64StringQuery = PlayPathBindables.queryBindableBase64String
    implicit val bindable_DateTimeQuery = PlayPathBindables.queryBindableDateTime
    implicit val bindable_UUIDQuery = PlayPathBindables.queryBindableUUID
    implicit val bindable_LocalDateQuery = PlayPathBindables.queryBindableLocalDate
    implicit val bindable_OptionBase64StringQuery: QueryStringBindable[Option[Base64String]] = PlayPathBindables.createOptionQueryBindable[Base64String]
    implicit val bindable_OptionDateTimeQuery: QueryStringBindable[Option[DateTime]] = PlayPathBindables.createOptionQueryBindable[DateTime]
    implicit val bindable_OptionUUIDQuery: QueryStringBindable[Option[UUID]] = PlayPathBindables.createOptionQueryBindable[UUID]
    implicit val bindable_OptionLocalDateQuery: QueryStringBindable[Option[LocalDate]] = PlayPathBindables.createOptionQueryBindable[LocalDate]

}
//noinspection ScalaStyle
package yaml {




}
