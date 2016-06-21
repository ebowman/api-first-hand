package full.petstore.api

package object yaml {

    import de.zalando.play.controllers.ArrayWrapper
    import org.joda.time.DateTime

    import de.zalando.play.controllers.PlayPathBindables



    type UsersUsernameGetUsername = String
    type UsersCreateWithListPostResponsesDefault = Null
    type OrderStatus = Option[String]
    type PetsFindByStatusGetStatusOpt = ArrayWrapper[String]
    type UsersCreateWithListPostBodyOpt = ArrayWrapper[User]
    type OrderPetId = Option[Long]
    type PetsFindByStatusGetResponses200 = Seq[Pet]
    type PetsPostBody = Option[Pet]
    type OrderShipDate = Option[DateTime]
    type UsersUsernamePutBody = Option[User]
    type StoresOrderPostBody = Option[Order]
    type OrderComplete = Option[Boolean]
    type PetTags = Option[PetTagsOpt]
    type PetsPetIdDeletePetId = Long
    type OrderQuantity = Option[Int]
    type PetPhotoUrls = Seq[String]
    type UsersCreateWithListPostBody = Option[UsersCreateWithListPostBodyOpt]
    type PetsFindByStatusGetStatus = Option[PetsFindByStatusGetStatusOpt]
    type PetCategory = Option[Tag]
    type PetTagsOpt = ArrayWrapper[Tag]


    case class User(email: OrderStatus, username: OrderStatus, userStatus: OrderQuantity, lastName: OrderStatus, firstName: OrderStatus, id: OrderPetId, phone: OrderStatus, password: OrderStatus) 
    case class Order(shipDate: OrderShipDate, quantity: OrderQuantity, petId: OrderPetId, id: OrderPetId, complete: OrderComplete, status: OrderStatus) 
    case class Tag(id: OrderPetId, name: OrderStatus) 
    case class Pet(name: String, tags: PetTags, photoUrls: PetPhotoUrls, id: OrderPetId, status: OrderStatus, category: PetCategory) 


    implicit val bindable_OptionStringQuery = PlayPathBindables.createOptionQueryBindable[String]

    implicit val bindable_ArrayWrapperStringQuery = PlayPathBindables.createArrayWrapperQueryBindable[String]("multi")

    implicit val bindable_OptionPetsFindByStatusGetStatusOptQuery = PlayPathBindables.createOptionQueryBindable[PetsFindByStatusGetStatusOpt]


}
