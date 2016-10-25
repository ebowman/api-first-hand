package nested_options_validation




//noinspection ScalaStyle
package yaml {


    case class BasicOptionalOpt(nested_optional: BasicOptionalNested_optional) 
    case class Basic(optional: BasicOptional) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val BasicReads: Reads[Basic] = (
            (JsPath \ "optional").readNullable[BasicOptionalOpt]
        ).map(Basic.apply )
    }

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type BasicOptional = Option[BasicOptionalOpt]
    type BasicOptionalNested_optional = Option[String]
    type GetResponses200 = Null



}