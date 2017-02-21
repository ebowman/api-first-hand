package security.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper

object Generators extends JsValueGenerators {
    

    
    def createArrayWrapperStringGenerator = _generate(ArrayWrapperStringGenerator)
    def createSeqPetGenerator = _generate(SeqPetGenerator)
    

    
    def ArrayWrapperStringGenerator = _genList(arbitrary[String], "csv")
    def SeqPetGenerator: Gen[List[Pet]] = Gen.containerOf[List,Pet](PetGenerator)
    

    def createErrorModelGenerator = _generate(ErrorModelGenerator)
    def createPetGenerator = _generate(PetGenerator)


    def ErrorModelGenerator = for {
        code <- arbitrary[Int]
        message <- arbitrary[String]
    } yield ErrorModel(code, message)
    def PetGenerator = for {
        name <- arbitrary[String]
        tag <- Gen.option(arbitrary[String])
    } yield Pet(name, tag)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
    

}