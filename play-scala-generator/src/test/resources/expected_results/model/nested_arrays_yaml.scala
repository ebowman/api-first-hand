



//noinspection ScalaStyle
package nested_arrays_yaml {


    case class Activity(actions: Option[String]) 
    case class Example(nestedArrays: Option[Seq[Seq[Seq[Seq[String]]]]], messages: Option[Seq[Seq[Activity]]]) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object nested_arrays_yaml {




}