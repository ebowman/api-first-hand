



//noinspection ScalaStyle
package nested_options_yaml {


    case class Basic(optional: Option[BasicOptionalOptionOptional]) 
    case class BasicOptionalOptionOptional(nested_optional: Option[String]) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object nested_options_yaml {




}