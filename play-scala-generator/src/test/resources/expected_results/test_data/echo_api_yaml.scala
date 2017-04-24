package echo

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createNullGenerator = _generate(NullGenerator)
    def createOptionStringGenerator = _generate(OptionStringGenerator)
    def createStringGenerator = _generate(StringGenerator)
    

    
    def NullGenerator = arbitrary[Null]
    def OptionStringGenerator = Gen.option(arbitrary[String])
    def StringGenerator = arbitrary[String]
    

    def createPostResponses200Generator = _generate(PostResponses200Generator)


    def PostResponses200Generator = for {
        name <- Gen.option(arbitrary[String])
        year <- Gen.option(arbitrary[String])
    } yield PostResponses200(name, year)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}