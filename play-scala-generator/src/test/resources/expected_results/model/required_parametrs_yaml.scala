package required_parametrs




//noinspection ScalaStyle
package yaml {


    case class GetTest2Opt(test3: Int) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type GetTest2 = Option[GetTest2Opt]
    type GetResponses200 = Null



}