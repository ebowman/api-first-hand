



//noinspection ScalaStyle
package nested_arrays_yaml {


    case class Activity(actions: ActivityActions) 
    case class Example(messages: ExampleMessages, nestedArrays: ExampleNestedArrays) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object nested_arrays_yaml {

    type ExampleNestedArraysOptArrResultArrResult = Seq[ExampleNestedArraysOptArrResultArrResultArrResult]
    type ExampleNestedArraysOpt = Seq[ExampleNestedArraysOptArrResult]
    type ExampleMessagesOpt = Seq[ExampleMessagesOptArrResult]
    type ExampleMessages = Option[ExampleMessagesOpt]
    type ExampleNestedArraysOptArrResultArrResultArrResult = Seq[String]
    type ExampleNestedArraysOptArrResult = Seq[ExampleNestedArraysOptArrResultArrResult]
    type ExampleNestedArrays = Option[ExampleNestedArraysOpt]
    type ExampleMessagesOptArrResult = Seq[Activity]
    type ActivityActions = Option[String]



}