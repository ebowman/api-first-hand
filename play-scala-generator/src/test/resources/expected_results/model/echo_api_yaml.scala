


    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package echo {


    case class PostResponses200(name: PostName, year: PostName) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val PostResponses200Writes: Writes[PostResponses200] = new Writes[PostResponses200] {
        def writes(ss: PostResponses200) =
          Json.obj(
            "name" -> ss.name, 
            "year" -> ss.year
          )
        }
    }
}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object echo {

    type Test_pathIdGetResponses200 = Null
    type PostName = Option[String]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionStringQuery: QueryStringBindable[Option[String]] = PlayPathBindables.createOptionQueryBindable[String]

}