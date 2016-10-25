package heroku.petstore.api



    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class Pet(name: PetName, birthday: PetBirthday) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val PetReads: Reads[Pet] = (
            (JsPath \ "name").readNullable[String] and (JsPath \ "birthday").readNullable[Int]
        )(Pet.apply _)
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val PetWrites: Writes[Pet] = new Writes[Pet] {
        def writes(ss: Pet) =
          Json.obj(
            "name" -> ss.name, 
            "birthday" -> ss.birthday
          )
        }
    }
}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type PetName = Option[String]
    type PetBirthday = Option[Int]
    type PostResponses200 = Null
    type PutPet = Option[Pet]
    type GetResponses200 = Seq[Pet]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt

}