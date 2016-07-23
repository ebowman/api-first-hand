



//noinspection ScalaStyle
package object options_yaml {

    type BasicRequired = Seq[String]
    type BasicOptional = Option[BasicRequired]



}
//noinspection ScalaStyle
package options_yaml {


    case class Basic(id: Long, required: BasicRequired, optional: BasicOptional) 


}
