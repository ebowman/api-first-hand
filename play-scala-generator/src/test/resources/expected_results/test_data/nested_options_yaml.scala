package nested_options_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    
    
    

    def createBasicGenerator = _generate(BasicGenerator)
    def createBasicOptionalOptionOptionalGenerator = _generate(BasicOptionalOptionOptionalGenerator)


    def BasicGenerator = for {
        optional <- Gen.option(BasicOptionalOptionOptionalGenerator)
    } yield Basic(optional)
    def BasicOptionalOptionOptionalGenerator = for {
        nested_optional <- Gen.option(arbitrary[String])
    } yield BasicOptionalOptionOptional(nested_optional)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}