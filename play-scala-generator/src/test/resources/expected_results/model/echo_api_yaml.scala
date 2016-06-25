


    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object echo {

    type Test_pathIdGetResponses200 = Null
    type PostName = Option[String]
    type Test_pathIdGetId = String


    implicit val bindable_OptionStringQuery = PlayPathBindables.createOptionQueryBindable[String]


}
//noinspection ScalaStyle
package echo {


    case class PostResponses200(name: PostName, year: PostName) 


}
