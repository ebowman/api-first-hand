package security.api


    import de.zalando.play.controllers.ArrayWrapper

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class ErrorModel(code: Int, message: String) 
    case class Pet(name: String, tag: Option[String]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val PetWrites: Writes[Pet] = new Writes[Pet] {
        def writes(ss: Pet) =
          Json.obj(
            "name" -> ss.name, 
            "tag" -> ss.tag
          )
        }
    }
}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {



import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_ArrayWrapperStringPath: PathBindable[ArrayWrapper[String]] = PlayPathBindables.createArrayWrapperPathBindable[String]("csv")

}