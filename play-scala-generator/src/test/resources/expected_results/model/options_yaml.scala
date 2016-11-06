



//noinspection ScalaStyle
package options_yaml {


    case class Basic(id: Long, required: BasicRequired, optional: BasicOptional) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object options_yaml {

    type BasicRequired = Seq[String]
    type BasicOptional = Option[BasicRequired]



}