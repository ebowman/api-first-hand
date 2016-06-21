package nested_objects_validation

package object yaml {




    type NestedObjectsNested = Option[NestedObjectsNestedOpt]
    type NestedObjectsNestedNested2Nested3Bottom = Option[String]
    type NestedObjectsNestedNested2Nested3 = Option[NestedObjectsNestedNested2Nested3Opt]
    type NestedObjectsPlain = Option[NestedObjectsPlainOpt]
    type GetResponses200 = Null


    case class NestedObjectsNestedOpt(nested2: NestedObjectsNestedNested2) 
    case class NestedObjectsNestedNested2Nested3Opt(bottom: NestedObjectsNestedNested2Nested3Bottom) 
    case class NestedObjects(plain: NestedObjectsPlain, nested: NestedObjectsNested) 
    case class NestedObjectsPlainOpt(simple: String) 
    case class NestedObjectsNestedNested2(nested3: NestedObjectsNestedNested2Nested3) 



}
