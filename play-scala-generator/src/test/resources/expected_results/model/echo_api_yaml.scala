


    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object echo {

    type Test_pathIdGetResponses200 = Null
    type PostName = Option[String]
    type Test_pathIdGetId = String


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionStringQuery: QueryStringBindable[Option[String]] = PlayPathBindables.createOptionQueryBindable[String]

}
//noinspection ScalaStyle
package echo {


    case class PostResponses200(name: PostName, year: PostName) 


}
