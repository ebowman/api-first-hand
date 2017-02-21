package heroku.petstore.api


    import scala.math.BigInt

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class Pet(name: Option[String], birthday: Option[Int]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val PetReads: Reads[Pet] = (
            (JsPath \ "name").read[Option[String]] and (JsPath \ "birthday").read[Option[Int]]
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

    type PostResponses200 = Null


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt

}