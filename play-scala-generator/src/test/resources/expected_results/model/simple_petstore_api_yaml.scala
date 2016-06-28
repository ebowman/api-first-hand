

    import de.zalando.play.controllers.ArrayWrapper

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object simple_petstore_api_yaml {

    type PetsIdDeleteResponses204 = Null
    type NewPetTag = Option[String]
    type PetsIdDeleteId = Long
    type PetsGetLimit = Option[Int]
    type NewPetId = Option[Long]
    type PetsGetTagsOpt = ArrayWrapper[String]
    type PetsGetResponses200 = Seq[Pet]
    type PetsGetTags = Option[PetsGetTagsOpt]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionIntQuery: QueryStringBindable[Option[Int]] = PlayPathBindables.createOptionQueryBindable[Int]
    implicit val bindable_ArrayWrapperStringQuery: QueryStringBindable[ArrayWrapper[String]] = PlayPathBindables.createArrayWrapperQueryBindable[String]("csv")
    implicit val bindable_OptionPetsGetTagsOptQuery: QueryStringBindable[Option[PetsGetTagsOpt]] = PlayPathBindables.createOptionQueryBindable[PetsGetTagsOpt]

}
//noinspection ScalaStyle
package simple_petstore_api_yaml {


    case class ErrorModel(code: Int, message: String) 
    case class Pet(id: Long, name: String, tag: NewPetTag) 
    case class NewPet(name: String, id: NewPetId, tag: NewPetTag) 


}
