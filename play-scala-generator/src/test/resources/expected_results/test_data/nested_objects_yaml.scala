package nested_objects_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    
    
    

    def createNestedObjectsNestedNested2Nested3OptionNested3Generator = _generate(NestedObjectsNestedNested2Nested3OptionNested3Generator)
    def createNestedObjectsPlainOptionPlainGenerator = _generate(NestedObjectsPlainOptionPlainGenerator)
    def createNestedObjectsGenerator = _generate(NestedObjectsGenerator)
    def createNestedObjectsNestedOptionNestedGenerator = _generate(NestedObjectsNestedOptionNestedGenerator)
    def createNestedObjectsNestedNested2Generator = _generate(NestedObjectsNestedNested2Generator)


    def NestedObjectsNestedNested2Nested3OptionNested3Generator = for {
        bottom <- Gen.option(arbitrary[String])
    } yield NestedObjectsNestedNested2Nested3OptionNested3(bottom)
    def NestedObjectsPlainOptionPlainGenerator = for {
        simple <- arbitrary[String]
    } yield NestedObjectsPlainOptionPlain(simple)
    def NestedObjectsGenerator = for {
        plain <- Gen.option(NestedObjectsPlainOptionPlainGenerator)
        nested <- Gen.option(NestedObjectsNestedOptionNestedGenerator)
    } yield NestedObjects(plain, nested)
    def NestedObjectsNestedOptionNestedGenerator = for {
        nested2 <- NestedObjectsNestedNested2Generator
    } yield NestedObjectsNestedOptionNested(nested2)
    def NestedObjectsNestedNested2Generator = for {
        nested3 <- Gen.option(NestedObjectsNestedNested2Nested3OptionNested3Generator)
    } yield NestedObjectsNestedNested2(nested3)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}