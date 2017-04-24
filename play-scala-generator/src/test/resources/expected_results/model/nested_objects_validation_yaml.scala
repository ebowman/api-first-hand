package nested_objects_validation




//noinspection ScalaStyle
package yaml {


    case class NestedObjectsNestedNested2Nested3OptionNested3(bottom: Option[String]) 
    case class NestedObjectsPlainOptionPlain(simple: String) 
    case class NestedObjects(plain: Option[NestedObjectsPlainOptionPlain], nested: Option[NestedObjectsNestedOptionNested]) 
    case class NestedObjectsNestedOptionNested(nested2: NestedObjectsNestedNested2) 
    case class NestedObjectsNestedNested2(nested3: Option[NestedObjectsNestedNested2Nested3OptionNested3]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val NestedObjectsNestedNested2Reads: Reads[NestedObjectsNestedNested2] = (
            (JsPath \ "nested3").read[Option[NestedObjectsNestedNested2Nested3OptionNested3]]
        ).map(NestedObjectsNestedNested2.apply )
        implicit val NestedObjectsReads: Reads[NestedObjects] = (
            (JsPath \ "plain").read[Option[NestedObjectsPlainOptionPlain]] and (JsPath \ "nested").read[Option[NestedObjectsNestedOptionNested]]
        )(NestedObjects.apply _)
    }

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type GetResponses200 = Null



}