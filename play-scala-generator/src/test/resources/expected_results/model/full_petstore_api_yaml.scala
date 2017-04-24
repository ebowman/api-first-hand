package full.petstore.api


    import java.time.ZonedDateTime
    import de.zalando.play.controllers.ArrayWrapper

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class User(email: Option[String], username: Option[String], userStatus: Option[Int], lastName: Option[String], firstName: Option[String], id: Option[Long], phone: Option[String], password: Option[String]) 
    case class Order(shipDate: Option[ZonedDateTime], quantity: Option[Int], petId: Option[Long], id: Option[Long], complete: Option[Boolean], status: Option[String]) 
    case class Tag(id: Option[Long], name: Option[String]) 
    case class Pet(name: String, photoUrls: Seq[String], id: Option[Long], status: Option[String], tags: Option[Seq[Tag]], category: Option[Tag]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val TagReads: Reads[Tag] = (
            (JsPath \ "id").read[Option[Long]] and (JsPath \ "name").read[Option[String]]
        )(Tag.apply _)
        implicit val PetReads: Reads[Pet] = (
            (JsPath \ "name").read[String] and (JsPath \ "photoUrls").read[Seq[String]] and (JsPath \ "id").read[Option[Long]] and (JsPath \ "status").read[Option[String]] and (JsPath \ "tags").read[Option[Seq[Tag]]] and (JsPath \ "category").read[Option[Tag]]
        )(Pet.apply _)
        implicit val UserReads: Reads[User] = (
            (JsPath \ "email").read[Option[String]] and (JsPath \ "username").read[Option[String]] and (JsPath \ "userStatus").read[Option[Int]] and (JsPath \ "lastName").read[Option[String]] and (JsPath \ "firstName").read[Option[String]] and (JsPath \ "id").read[Option[Long]] and (JsPath \ "phone").read[Option[String]] and (JsPath \ "password").read[Option[String]]
        )(User.apply _)
        implicit val OrderReads: Reads[Order] = (
            (JsPath \ "shipDate").read[Option[ZonedDateTime]] and (JsPath \ "quantity").read[Option[Int]] and (JsPath \ "petId").read[Option[Long]] and (JsPath \ "id").read[Option[Long]] and (JsPath \ "complete").read[Option[Boolean]] and (JsPath \ "status").read[Option[String]]
        )(Order.apply _)
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val UserWrites: Writes[User] = new Writes[User] {
        def writes(ss: User) =
          Json.obj(
            "email" -> ss.email, 
            "username" -> ss.username, 
            "userStatus" -> ss.userStatus, 
            "lastName" -> ss.lastName, 
            "firstName" -> ss.firstName, 
            "id" -> ss.id, 
            "phone" -> ss.phone, 
            "password" -> ss.password
          )
        }
    implicit val OrderWrites: Writes[Order] = new Writes[Order] {
        def writes(ss: Order) =
          Json.obj(
            "shipDate" -> ss.shipDate, 
            "quantity" -> ss.quantity, 
            "petId" -> ss.petId, 
            "id" -> ss.id, 
            "complete" -> ss.complete, 
            "status" -> ss.status
          )
        }
    implicit val TagWrites: Writes[Tag] = new Writes[Tag] {
        def writes(ss: Tag) =
          Json.obj(
            "id" -> ss.id, 
            "name" -> ss.name
          )
        }
    implicit val PetWrites: Writes[Pet] = new Writes[Pet] {
        def writes(ss: Pet) =
          Json.obj(
            "name" -> ss.name, 
            "photoUrls" -> ss.photoUrls, 
            "id" -> ss.id, 
            "status" -> ss.status, 
            "tags" -> ss.tags, 
            "category" -> ss.category
          )
        }
    }
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