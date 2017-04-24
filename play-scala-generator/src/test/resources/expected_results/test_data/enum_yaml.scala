package enum.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createOptionTestGetIncludesOptionEnumGenerator = _generate(OptionTestGetIncludesOptionEnumGenerator)
    def createTestGetIncludesOptionEnumGenerator = _generate(TestGetIncludesOptionEnumGenerator)
    

    
    def StringGenerator = arbitrary[String]
    def OptionTestGetIncludesOptionEnumGenerator = Gen.option(TestGetIncludesOptionEnumGenerator)
    def TestGetIncludesOptionEnumGenerator = { import TestGetIncludesOptionEnum._ ; Gen.oneOf(Seq(Option_one, Option_two)) }
    


    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}