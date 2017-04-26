package required_parametrs.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createGetTest2Generator = _generate(GetTest2Generator)
    def createNullGenerator = _generate(NullGenerator)
    

    
    def StringGenerator = arbitrary[String]
    def GetTest2Generator = Gen.option(GetTest2OptGenerator)
    def NullGenerator = arbitrary[Null]
    

    def createGetTest2OptGenerator = _generate(GetTest2OptGenerator)


    def GetTest2OptGenerator = for {
        test3 <- arbitrary[Int]
    } yield GetTest2Opt(test3)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}