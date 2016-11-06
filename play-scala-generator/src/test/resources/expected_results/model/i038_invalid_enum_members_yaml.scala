package i038_invalid_enum_members




//noinspection ScalaStyle
package yaml {



    case class GetResponses200(override val value: String) extends AnyVal with de.zalando.play.controllers.StringAnyVal

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {


    object GetResponses200 {
        
        val Status_One = new GetResponses200("Status One")
        val Status_Two = new GetResponses200("Status Two")
        val Status_Three = new GetResponses200("Status Three")

        implicit def stringToGetResponses200: String => GetResponses200 = {
            case "Status One" => Status_One
            case "Status Two" => Status_Two
            case "Status Three" => Status_Three
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }


}