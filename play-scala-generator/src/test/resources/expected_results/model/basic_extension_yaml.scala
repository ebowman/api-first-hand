

    import scala.math.BigInt


//noinspection ScalaStyle
package basic_extension_yaml {


    case class ErrorModel(message: String, code: BigInt) 
    case class ExtendedErrorModel(message: String, code: BigInt, rootCause: String) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object basic_extension_yaml {




}