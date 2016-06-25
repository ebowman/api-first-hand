package nested_arrays_validation


    import de.zalando.play.controllers.ArrayWrapper


//noinspection ScalaStyle
package object yaml {

    type ExampleNestedArraysOptArr = ArrayWrapper[ExampleNestedArraysOptArrArr]
    type ExampleNestedArraysOpt = ArrayWrapper[ExampleNestedArraysOptArr]
    type ExampleMessagesOpt = ArrayWrapper[ExampleMessagesOptArr]
    type AnotherPostExample = Option[Example]
    type ExampleMessages = Option[ExampleMessagesOpt]
    type ExampleMessagesOptArr = ArrayWrapper[Activity]
    type AnotherPostResponses200 = Null
    type ExampleNestedArraysOptArrArr = ArrayWrapper[ExampleNestedArraysOptArrArrArr]
    type ExampleNestedArrays = Option[ExampleNestedArraysOpt]
    type ExampleNestedArraysOptArrArrArr = ArrayWrapper[String]
    type ActivityActions = Option[String]



}
//noinspection ScalaStyle
package yaml {


    case class Activity(actions: ActivityActions) 
    case class Example(messages: ExampleMessages, nestedArrays: ExampleNestedArrays) 


}
