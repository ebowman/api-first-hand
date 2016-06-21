package security.api

package object yaml {

    import de.zalando.play.controllers.ArrayWrapper

    import de.zalando.play.controllers.PlayPathBindables



    type PetsIdGetId = ArrayWrapper[String]
    type PetsIdGetResponses200 = Seq[Pet]
    type PetTag = Option[String]


    case class ErrorModel(code: Int, message: String) 
    case class Pet(name: String, tag: PetTag) 


    implicit val bindable_ArrayWrapperStringPath = PlayPathBindables.createArrayWrapperPathBindable[String]("csv")


}
