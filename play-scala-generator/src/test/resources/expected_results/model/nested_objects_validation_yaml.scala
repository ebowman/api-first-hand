package nested_objects_validation




//noinspection ScalaStyle
package yaml {


    case class NestedObjectsNestedOpt(nested2: NestedObjectsNestedNested2) 
    case class NestedObjectsNestedNested2Nested3Opt(bottom: NestedObjectsNestedNested2Nested3Bottom) 
    case class NestedObjects(plain: NestedObjectsPlain, nested: NestedObjectsNested) 
    case class NestedObjectsPlainOpt(simple: String) 
    case class NestedObjectsNestedNested2(nested3: NestedObjectsNestedNested2Nested3) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val NestedObjectsNestedNested2Reads: Reads[NestedObjectsNestedNested2] = (
            (JsPath \ "nested3").readNullable[NestedObjectsNestedNested2Nested3Opt]
        ).map(NestedObjectsNestedNested2.apply )
        implicit val NestedObjectsReads: Reads[NestedObjects] = (
            (JsPath \ "plain").readNullable[NestedObjectsPlainOpt] and (JsPath \ "nested").readNullable[NestedObjectsNestedOpt]
        )(NestedObjects.apply _)
    }

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type NestedObjectsNested = Option[NestedObjectsNestedOpt]
    type NestedObjectsNestedNested2Nested3Bottom = Option[String]
    type NestedObjectsNestedNested2Nested3 = Option[NestedObjectsNestedNested2Nested3Opt]
    type NestedObjectsPlain = Option[NestedObjectsPlainOpt]
    type GetResponses200 = Null



}