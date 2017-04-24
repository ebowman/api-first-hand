



//noinspection ScalaStyle
package nested_objects_yaml {


    case class NestedObjectsNestedNested2Nested3OptionNested3(bottom: Option[String]) 
    case class NestedObjectsPlainOptionPlain(simple: String) 
    case class NestedObjects(plain: Option[NestedObjectsPlainOptionPlain], nested: Option[NestedObjectsNestedOptionNested]) 
    case class NestedObjectsNestedOptionNested(nested2: NestedObjectsNestedNested2) 
    case class NestedObjectsNestedNested2(nested3: Option[NestedObjectsNestedNested2Nested3OptionNested3]) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object nested_objects_yaml {




}