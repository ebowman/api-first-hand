

    import de.zalando.play.controllers.ArrayWrapper


//noinspection ScalaStyle
package object nested_arrays_yaml {

    type ExampleNestedArraysOptArr = ArrayWrapper[ExampleNestedArraysOptArrArr]
    type ExampleNestedArraysOpt = ArrayWrapper[ExampleNestedArraysOptArr]
    type ExampleMessagesOpt = ArrayWrapper[ExampleMessagesOptArr]
    type ExampleMessages = Option[ExampleMessagesOpt]
    type ExampleMessagesOptArr = ArrayWrapper[Activity]
    type ExampleNestedArraysOptArrArr = ArrayWrapper[ExampleNestedArraysOptArrArrArr]
    type ExampleNestedArrays = Option[ExampleNestedArraysOpt]
    type ExampleNestedArraysOptArrArrArr = ArrayWrapper[String]
    type ActivityActions = Option[String]



}
//noinspection ScalaStyle
package nested_arrays_yaml {


    case class Activity(actions: ActivityActions) 
    case class Example(messages: ExampleMessages, nestedArrays: ExampleNestedArrays) 


}
