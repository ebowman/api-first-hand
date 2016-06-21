package nested_arrays_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper

object Generators extends JsValueGenerators {
    

    
    def createExampleNestedArraysOptArrGenerator = _generate(ExampleNestedArraysOptArrGenerator)
    def createExampleNestedArraysOptGenerator = _generate(ExampleNestedArraysOptGenerator)
    def createExampleMessagesOptGenerator = _generate(ExampleMessagesOptGenerator)
    def createExampleMessagesGenerator = _generate(ExampleMessagesGenerator)
    def createExampleMessagesOptArrGenerator = _generate(ExampleMessagesOptArrGenerator)
    def createExampleNestedArraysOptArrArrGenerator = _generate(ExampleNestedArraysOptArrArrGenerator)
    def createExampleNestedArraysGenerator = _generate(ExampleNestedArraysGenerator)
    def createExampleNestedArraysOptArrArrArrGenerator = _generate(ExampleNestedArraysOptArrArrArrGenerator)
    def createActivityActionsGenerator = _generate(ActivityActionsGenerator)
    

    
    def ExampleNestedArraysOptArrGenerator = _genList(ExampleNestedArraysOptArrArrGenerator, "csv")
    def ExampleNestedArraysOptGenerator = _genList(ExampleNestedArraysOptArrGenerator, "csv")
    def ExampleMessagesOptGenerator = _genList(ExampleMessagesOptArrGenerator, "csv")
    def ExampleMessagesGenerator = Gen.option(ExampleMessagesOptGenerator)
    def ExampleMessagesOptArrGenerator = _genList(ActivityGenerator, "csv")
    def ExampleNestedArraysOptArrArrGenerator = _genList(ExampleNestedArraysOptArrArrArrGenerator, "csv")
    def ExampleNestedArraysGenerator = Gen.option(ExampleNestedArraysOptGenerator)
    def ExampleNestedArraysOptArrArrArrGenerator = _genList(arbitrary[String], "csv")
    def ActivityActionsGenerator = Gen.option(arbitrary[String])
    

    def createActivityGenerator = _generate(ActivityGenerator)
    def createExampleGenerator = _generate(ExampleGenerator)


    def ActivityGenerator = for {
        actions <- ActivityActionsGenerator
    } yield Activity(actions)
    def ExampleGenerator = for {
        messages <- ExampleMessagesGenerator
        nestedArrays <- ExampleNestedArraysGenerator
    } yield Example(messages, nestedArrays)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
}