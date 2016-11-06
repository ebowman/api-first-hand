package nested_arrays_validation




//noinspection ScalaStyle
package yaml {


    case class Activity(actions: ActivityActions) 
    case class Example(messages: ExampleMessages, nestedArrays: ExampleNestedArrays) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val ExampleReads: Reads[Example] = (
            (JsPath \ "messages").readNullable[ExampleMessagesOpt] and (JsPath \ "nestedArrays").readNullable[ExampleNestedArraysOpt]
        )(Example.apply _)
        implicit val ActivityReads: Reads[Activity] = (
            (JsPath \ "actions").readNullable[String]
        ).map(Activity.apply )
    }

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type ExampleNestedArraysOptArrResultArrResult = Seq[ExampleNestedArraysOptArrResultArrResultArrResult]
    type ExampleNestedArraysOpt = Seq[ExampleNestedArraysOptArrResult]
    type ExampleMessagesOpt = Seq[ExampleMessagesOptArrResult]
    type AnotherPostExample = Option[Example]
    type ExampleMessages = Option[ExampleMessagesOpt]
    type ExampleNestedArraysOptArrResultArrResultArrResult = Seq[String]
    type AnotherPostResponses200 = Null
    type ExampleNestedArraysOptArrResult = Seq[ExampleNestedArraysOptArrResultArrResult]
    type ExampleNestedArrays = Option[ExampleNestedArraysOpt]
    type ExampleMessagesOptArrResult = Seq[Activity]
    type ActivityActions = Option[String]



}