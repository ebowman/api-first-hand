package nested_arrays_validation

package object yaml {

    import de.zalando.play.controllers.ArrayWrapper



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


    case class Activity(actions: ActivityActions) 
    case class Example(messages: ExampleMessages, nestedArrays: ExampleNestedArrays) 



}
