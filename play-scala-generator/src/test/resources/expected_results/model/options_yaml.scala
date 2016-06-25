

    import de.zalando.play.controllers.ArrayWrapper


//noinspection ScalaStyle
package object options_yaml {

    type BasicRequired = ArrayWrapper[String]
    type BasicOptional = Option[BasicRequired]



}
//noinspection ScalaStyle
package options_yaml {


    case class Basic(id: Long, required: BasicRequired, optional: BasicOptional) 


}
