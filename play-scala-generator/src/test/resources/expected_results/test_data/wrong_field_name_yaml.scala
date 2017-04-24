package wrong_field_name.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createGetCodesGenerator = _generate(GetCodesGenerator)
    def createOptionGetOptCodesOptGenerator = _generate(OptionGetOptCodesOptGenerator)
    def createGetOptCodesOptGenerator = _generate(GetOptCodesOptGenerator)
    def createStatusGenerator = _generate(StatusGenerator)
    

    
    def GetCodesGenerator = { import GetCodes._ ; Gen.oneOf(Seq(Get, GET)) }
    def OptionGetOptCodesOptGenerator = Gen.option(GetOptCodesOptGenerator)
    def GetOptCodesOptGenerator = { import GetOptCodesOpt._ ; Gen.oneOf(Seq(Put, PUT)) }
    def StatusGenerator = { import Status._ ; Gen.oneOf(Seq(OK, WARNING, ERROR)) }
    

    def createStatusAndCodeGenerator = _generate(StatusAndCodeGenerator)


    def StatusAndCodeGenerator = for {
        message <- arbitrary[String]
        Status <- StatusGenerator
    } yield StatusAndCode(message, Status)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}