

    import scala.math.BigInt


//noinspection ScalaStyle
package object basic_extension_yaml {




}
//noinspection ScalaStyle
package basic_extension_yaml {


    case class ErrorModel(message: String, code: BigInt) 
    case class ExtendedErrorModel(message: String, code: BigInt, rootCause: String) 


}
