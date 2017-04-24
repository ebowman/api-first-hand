



//noinspection ScalaStyle
package options_yaml {


    case class Basic(id: Long, required: Seq[String], optional: Option[Seq[String]]) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object options_yaml {




}