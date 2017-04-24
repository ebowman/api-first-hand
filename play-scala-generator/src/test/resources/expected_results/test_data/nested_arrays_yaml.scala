package nested_arrays_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    
    
    

    def createActivityGenerator = _generate(ActivityGenerator)
    def createExampleGenerator = _generate(ExampleGenerator)


    def ActivityGenerator = for {
        actions <- Gen.option(arbitrary[String])
    } yield Activity(actions)
    def ExampleGenerator = for {
        nestedArrays <- Gen.option(Gen.containerOf[List,Seq](Gen.containerOf[List,Seq](Gen.containerOf[List,Seq](Gen.containerOf[List,String](arbitrary[String])))))
        messages <- Gen.option(Gen.containerOf[List,Seq](Gen.containerOf[List,Activity](ActivityGenerator)))
    } yield Example(nestedArrays, messages)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}