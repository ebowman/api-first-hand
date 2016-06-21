package nested_objects_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createNestedObjectsNestedGenerator = _generate(NestedObjectsNestedGenerator)
    def createNestedObjectsNestedNested2Nested3BottomGenerator = _generate(NestedObjectsNestedNested2Nested3BottomGenerator)
    def createNestedObjectsNestedNested2Nested3Generator = _generate(NestedObjectsNestedNested2Nested3Generator)
    def createNestedObjectsPlainGenerator = _generate(NestedObjectsPlainGenerator)
    

    
    def NestedObjectsNestedGenerator = Gen.option(NestedObjectsNestedOptGenerator)
    def NestedObjectsNestedNested2Nested3BottomGenerator = Gen.option(arbitrary[String])
    def NestedObjectsNestedNested2Nested3Generator = Gen.option(NestedObjectsNestedNested2Nested3OptGenerator)
    def NestedObjectsPlainGenerator = Gen.option(NestedObjectsPlainOptGenerator)
    

    def createNestedObjectsNestedOptGenerator = _generate(NestedObjectsNestedOptGenerator)
    def createNestedObjectsNestedNested2Nested3OptGenerator = _generate(NestedObjectsNestedNested2Nested3OptGenerator)
    def createNestedObjectsGenerator = _generate(NestedObjectsGenerator)
    def createNestedObjectsPlainOptGenerator = _generate(NestedObjectsPlainOptGenerator)
    def createNestedObjectsNestedNested2Generator = _generate(NestedObjectsNestedNested2Generator)


    def NestedObjectsNestedOptGenerator = for {
        nested2 <- NestedObjectsNestedNested2Generator
    } yield NestedObjectsNestedOpt(nested2)
    def NestedObjectsNestedNested2Nested3OptGenerator = for {
        bottom <- NestedObjectsNestedNested2Nested3BottomGenerator
    } yield NestedObjectsNestedNested2Nested3Opt(bottom)
    def NestedObjectsGenerator = for {
        plain <- NestedObjectsPlainGenerator
        nested <- NestedObjectsNestedGenerator
    } yield NestedObjects(plain, nested)
    def NestedObjectsPlainOptGenerator = for {
        simple <- arbitrary[String]
    } yield NestedObjectsPlainOpt(simple)
    def NestedObjectsNestedNested2Generator = for {
        nested3 <- NestedObjectsNestedNested2Nested3Generator
    } yield NestedObjectsNestedNested2(nested3)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
}