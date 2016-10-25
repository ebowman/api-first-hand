



//noinspection ScalaStyle
package nested_options_yaml {


    case class Basic(optional: BasicOptional) 
    case class BasicOptionalOpt(nested_optional: BasicOptionalNested_optional) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object nested_options_yaml {

    type BasicOptional = Option[BasicOptionalOpt]
    type BasicOptionalNested_optional = Option[String]



}