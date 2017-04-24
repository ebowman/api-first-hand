package nested_arrays_validation




//noinspection ScalaStyle
package yaml {


    case class Activity(actions: Option[String]) 
    case class Example(nestedArrays: Option[Seq[Seq[Seq[Seq[String]]]]], messages: Option[Seq[Seq[Activity]]]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val ExampleReads: Reads[Example] = (
            (JsPath \ "nestedArrays").read[Option[Seq[Seq[Seq[Seq[String]]]]]] and (JsPath \ "messages").read[Option[Seq[Seq[Activity]]]]
        )(Example.apply _)
        implicit val ActivityReads: Reads[Activity] = (
            (JsPath \ "actions").read[Option[String]]
        ).map(Activity.apply )
    }

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type AnotherPostResponses200 = Null



}