package wrong_field_name.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createGetCodesGenerator = _generate(GetCodesGenerator)
    def createGetOptCodesGenerator = _generate(GetOptCodesGenerator)
    def createGetOptCodesOptGenerator = _generate(GetOptCodesOptGenerator)
    def createStatusGenerator = _generate(StatusGenerator)
    

    
    def GetCodesGenerator = Gen.oneOf(Seq(Get, GET))
    def GetOptCodesGenerator = Gen.option(GetOptCodesOptGenerator)
    def GetOptCodesOptGenerator = Gen.oneOf(Seq(Put, PUT))
    def StatusGenerator = Gen.oneOf(Seq(OK, WARNING, ERROR))
    

    def createStatusAndCodeGenerator = _generate(StatusAndCodeGenerator)


    def StatusAndCodeGenerator = for {
        message <- arbitrary[String]
        code <- StatusGenerator
    } yield StatusAndCode(message, code)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}