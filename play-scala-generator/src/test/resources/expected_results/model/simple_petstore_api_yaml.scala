

    import de.zalando.play.controllers.ArrayWrapper

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object simple_petstore_api_yaml {

    type PetsIdDeleteResponses204 = Null
    type NewPetTag = Option[String]
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


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val NewPetReads: Reads[NewPet] = (
            (JsPath \ "name").read[String] and (JsPath \ "id").readNullable[Long] and (JsPath \ "tag").readNullable[String]
        )(NewPet.apply _)
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val PetWrites: Writes[Pet] = new Writes[Pet] {
        def writes(ss: Pet) =
          Json.obj(
            "id" -> ss.id, 
            "name" -> ss.name, 
            "tag" -> ss.tag
          )
        }
    }
}
