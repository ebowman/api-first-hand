package options_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper

object Generators extends JsValueGenerators {
    

    
    def createBasicRequiredGenerator = _generate(BasicRequiredGenerator)
    def createBasicOptionalGenerator = _generate(BasicOptionalGenerator)
    

    
    def BasicRequiredGenerator = _genList(arbitrary[String], "csv")
    def BasicOptionalGenerator = Gen.option(BasicRequiredGenerator)
    

    def createBasicGenerator = _generate(BasicGenerator)


    def BasicGenerator = for {
        id <- arbitrary[Long]
        required <- BasicRequiredGenerator
        optional <- BasicOptionalGenerator
    } yield Basic(id, required, optional)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
}