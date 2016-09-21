package nested_arrays_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createExampleNestedArraysOptArrResultArrResultGenerator = _generate(ExampleNestedArraysOptArrResultArrResultGenerator)
    def createExampleNestedArraysOptGenerator = _generate(ExampleNestedArraysOptGenerator)
    def createExampleMessagesOptGenerator = _generate(ExampleMessagesOptGenerator)
    def createExampleMessagesGenerator = _generate(ExampleMessagesGenerator)
    def createExampleNestedArraysOptArrResultArrResultArrResultGenerator = _generate(ExampleNestedArraysOptArrResultArrResultArrResultGenerator)
    def createExampleNestedArraysOptArrResultGenerator = _generate(ExampleNestedArraysOptArrResultGenerator)
    def createExampleNestedArraysGenerator = _generate(ExampleNestedArraysGenerator)
    def createExampleMessagesOptArrResultGenerator = _generate(ExampleMessagesOptArrResultGenerator)
    def createActivityActionsGenerator = _generate(ActivityActionsGenerator)
    

    
    def ExampleNestedArraysOptArrResultArrResultGenerator: Gen[List[ExampleNestedArraysOptArrResultArrResultArrResult]] = Gen.containerOf[List,ExampleNestedArraysOptArrResultArrResultArrResult](ExampleNestedArraysOptArrResultArrResultArrResultGenerator)
    def ExampleNestedArraysOptGenerator: Gen[List[ExampleNestedArraysOptArrResult]] = Gen.containerOf[List,ExampleNestedArraysOptArrResult](ExampleNestedArraysOptArrResultGenerator)
    def ExampleMessagesOptGenerator: Gen[List[ExampleMessagesOptArrResult]] = Gen.containerOf[List,ExampleMessagesOptArrResult](ExampleMessagesOptArrResultGenerator)
    def ExampleMessagesGenerator = Gen.option(ExampleMessagesOptGenerator)
    def ExampleNestedArraysOptArrResultArrResultArrResultGenerator: Gen[List[String]] = Gen.containerOf[List,String](arbitrary[String])
    def ExampleNestedArraysOptArrResultGenerator: Gen[List[ExampleNestedArraysOptArrResultArrResult]] = Gen.containerOf[List,ExampleNestedArraysOptArrResultArrResult](ExampleNestedArraysOptArrResultArrResultGenerator)
    def ExampleNestedArraysGenerator = Gen.option(ExampleNestedArraysOptGenerator)
    def ExampleMessagesOptArrResultGenerator: Gen[List[Activity]] = Gen.containerOf[List,Activity](ActivityGenerator)
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

    
    
    
    

}