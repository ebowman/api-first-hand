



//noinspection ScalaStyle
package object nested_options_yaml {

    type BasicOptional = Option[BasicOptionalOpt]
    type BasicOptionalNested_optional = Option[String]



}
//noinspection ScalaStyle
package nested_options_yaml {


    case class Basic(optional: BasicOptional) 
    case class BasicOptionalOpt(nested_optional: BasicOptionalNested_optional) 


}
