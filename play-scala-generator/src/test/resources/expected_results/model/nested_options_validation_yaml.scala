package nested_options_validation




//noinspection ScalaStyle
package yaml {


    case class BasicOptionalOptionOptional(nested_optional: Option[String]) 
    case class Basic(optional: Option[BasicOptionalOptionOptional]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val BasicReads: Reads[Basic] = (
            (JsPath \ "optional").read[Option[BasicOptionalOptionOptional]]
        ).map(Basic.apply )
    }

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type GetResponses200 = Null



}