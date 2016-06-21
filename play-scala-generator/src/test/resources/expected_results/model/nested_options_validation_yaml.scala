package nested_options_validation

package object yaml {




    type BasicOptional = Option[BasicOptionalOpt]
    type BasicOptionalNested_optional = Option[String]
    type GetResponses200 = Null


    case class BasicOptionalOpt(nested_optional: BasicOptionalNested_optional) 
    case class Basic(optional: BasicOptional) 



}
