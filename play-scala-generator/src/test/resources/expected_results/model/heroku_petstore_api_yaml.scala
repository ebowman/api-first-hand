package heroku.petstore.api


    import scala.math.BigInt

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type PetName = Option[String]
    type PetIdGetPetId = String
    type PetBirthday = Option[Int]
    type PostResponses200 = Null
    type GetLimit = BigInt
    type PutPet = Option[Pet]
    type GetResponses200 = Seq[Pet]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt

}
//noinspection ScalaStyle
package yaml {


    case class Pet(name: PetName, birthday: PetBirthday) 


}
