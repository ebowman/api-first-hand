package nested_arrays_validation




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
//noinspection ScalaStyle
package yaml {


    case class Activity(actions: ActivityActions) 
    case class Example(messages: ExampleMessages, nestedArrays: ExampleNestedArrays) 


}
