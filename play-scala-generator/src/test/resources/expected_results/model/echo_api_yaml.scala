
package object echo {


    import de.zalando.play.controllers.PlayPathBindables



    type Test_pathIdGetResponses200 = Null
    type PostName = Option[String]
    type Test_pathIdGetId = String


    case class PostResponses200(name: PostName, year: PostName) 


    implicit val bindable_OptionStringQuery = PlayPathBindables.createOptionQueryBindable[String]


}
