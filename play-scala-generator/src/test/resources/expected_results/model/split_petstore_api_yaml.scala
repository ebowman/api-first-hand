package split.petstore.api


    import java.time.ZonedDateTime
    import de.zalando.play.controllers.ArrayWrapper

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class PetCategoryOptionCategory(id: Option[Long], name: Option[String]) 
    case class User(email: Option[String], username: Option[String], userStatus: Option[Int], lastName: Option[String], firstName: Option[String], id: Option[Long], phone: Option[String], password: Option[String]) 
    case class Pet(name: String, photoUrls: Seq[String], id: Option[Long], status: Option[String], tags: Option[Seq[PetCategoryOptionCategory]], category: Option[PetCategoryOptionCategory]) 
    case class Order(shipDate: Option[ZonedDateTime], quantity: Option[Int], petId: Option[Long], id: Option[Long], complete: Option[Boolean], status: Option[String]) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type UsersCreateWithListPostResponsesDefault = Null


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionStringQuery: QueryStringBindable[Option[String]] = PlayPathBindables.createOptionQueryBindable[String]
    implicit val bindable_ArrayWrapperStringQuery: QueryStringBindable[ArrayWrapper[String]] = PlayPathBindables.createArrayWrapperQueryBindable[String]("multi")
    implicit val bindable_OptionArrayWrapperQuery: QueryStringBindable[Option[ArrayWrapper]] = PlayPathBindables.createOptionQueryBindable[ArrayWrapper]

}