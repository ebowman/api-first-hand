package options_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    
    
    

    def createBasicGenerator = _generate(BasicGenerator)


    def BasicGenerator = for {
        id <- arbitrary[Long]
        required <- Gen.containerOf[List,String](arbitrary[String])
        optional <- Gen.option(Gen.containerOf[List,String](arbitrary[String]))
    } yield Basic(id, required, optional)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}