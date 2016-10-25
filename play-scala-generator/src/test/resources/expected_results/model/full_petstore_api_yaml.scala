package full.petstore.api


    import de.zalando.play.controllers.ArrayWrapper
    import java.time.ZonedDateTime

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class User(email: OrderStatus, username: OrderStatus, userStatus: OrderQuantity, lastName: OrderStatus, firstName: OrderStatus, id: OrderPetId, phone: OrderStatus, password: OrderStatus) 
    case class Order(shipDate: OrderShipDate, quantity: OrderQuantity, petId: OrderPetId, id: OrderPetId, complete: OrderComplete, status: OrderStatus) 
    case class Tag(id: OrderPetId, name: OrderStatus) 
    case class Pet(name: String, tags: PetTags, photoUrls: PetPhotoUrls, id: OrderPetId, status: OrderStatus, category: PetCategory) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val TagReads: Reads[Tag] = (
            (JsPath \ "id").readNullable[Long] and (JsPath \ "name").readNullable[String]
        )(Tag.apply _)
        implicit val PetReads: Reads[Pet] = (
            (JsPath \ "name").read[String] and (JsPath \ "tags").readNullable[PetTagsOpt] and (JsPath \ "photoUrls").read[PetPhotoUrls] and (JsPath \ "id").readNullable[Long] and (JsPath \ "status").readNullable[String] and (JsPath \ "category").readNullable[Tag]
        )(Pet.apply _)
        implicit val UserReads: Reads[User] = (
            (JsPath \ "email").readNullable[String] and (JsPath \ "username").readNullable[String] and (JsPath \ "userStatus").readNullable[Int] and (JsPath \ "lastName").readNullable[String] and (JsPath \ "firstName").readNullable[String] and (JsPath \ "id").readNullable[Long] and (JsPath \ "phone").readNullable[String] and (JsPath \ "password").readNullable[String]
        )(User.apply _)
        implicit val OrderReads: Reads[Order] = (
            (JsPath \ "shipDate").readNullable[ZonedDateTime] and (JsPath \ "quantity").readNullable[Int] and (JsPath \ "petId").readNullable[Long] and (JsPath \ "id").readNullable[Long] and (JsPath \ "complete").readNullable[Boolean] and (JsPath \ "status").readNullable[String]
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
            "tags" -> ss.tags, 
            "photoUrls" -> ss.photoUrls, 
            "id" -> ss.id, 
            "status" -> ss.status, 
            "category" -> ss.category
          )
        }
    }
}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type UsersCreateWithListPostResponsesDefault = Null
    type OrderStatus = Option[String]
    type PetsFindByStatusGetStatusOpt = ArrayWrapper[String]
    type UsersCreateWithListPostBodyOpt = Seq[User]
    type OrderPetId = Option[Long]
    type PetsFindByStatusGetResponses200 = Seq[Pet]
    type PetsPostBody = Option[Pet]
    type OrderShipDate = Option[ZonedDateTime]
    type UsersUsernamePutBody = Option[User]
    type StoresOrderPostBody = Option[Order]
    type OrderComplete = Option[Boolean]
    type PetTags = Option[PetTagsOpt]
    type OrderQuantity = Option[Int]
    type PetPhotoUrls = Seq[String]
    type UsersCreateWithListPostBody = Option[UsersCreateWithListPostBodyOpt]
    type PetsFindByStatusGetStatus = Option[PetsFindByStatusGetStatusOpt]
    type PetCategory = Option[Tag]
    type PetTagsOpt = Seq[Tag]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionStringQuery: QueryStringBindable[Option[String]] = PlayPathBindables.createOptionQueryBindable[String]
    implicit val bindable_ArrayWrapperStringQuery: QueryStringBindable[ArrayWrapper[String]] = PlayPathBindables.createArrayWrapperQueryBindable[String]("multi")
    implicit val bindable_OptionPetsFindByStatusGetStatusOptQuery: QueryStringBindable[Option[PetsFindByStatusGetStatusOpt]] = PlayPathBindables.createOptionQueryBindable[PetsFindByStatusGetStatusOpt]

}