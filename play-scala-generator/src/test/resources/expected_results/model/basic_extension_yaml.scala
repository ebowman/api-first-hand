
package object basic_extension_yaml {

    import scala.math.BigInt





    case class ErrorModel(message: String, code: BigInt) 
    case class ExtendedErrorModel(message: String, code: BigInt, rootCause: String) 



}
