package basic_extension_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt

object Generators extends JsValueGenerators {
    
    
    

    def createErrorModelGenerator = _generate(ErrorModelGenerator)
    def createExtendedErrorModelGenerator = _generate(ExtendedErrorModelGenerator)


    def ErrorModelGenerator = for {
        message <- arbitrary[String]
        code <- arbitrary[BigInt]
    } yield ErrorModel(message, code)
    def ExtendedErrorModelGenerator = for {
        message <- arbitrary[String]
        code <- arbitrary[BigInt]
        rootCause <- arbitrary[String]
    } yield ExtendedErrorModel(message, code, rootCause)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
}