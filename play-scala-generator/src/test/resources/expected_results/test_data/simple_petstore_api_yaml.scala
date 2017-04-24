package simple_petstore_api_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper

object Generators extends JsValueGenerators {
    

    
    def createNullGenerator = _generate(NullGenerator)
    def createLongGenerator = _generate(LongGenerator)
    def createOptionIntGenerator = _generate(OptionIntGenerator)
    def createSeqPetGenerator = _generate(SeqPetGenerator)
    def createOptionArrayWrapperStringGenerator = _generate(OptionArrayWrapperStringGenerator)
    

    
    def NullGenerator = arbitrary[Null]
    def LongGenerator = arbitrary[Long]
    def OptionIntGenerator = Gen.option(arbitrary[Int])
    def SeqPetGenerator: Gen[List[Pet]] = Gen.containerOf[List,Pet](PetGenerator)
    def OptionArrayWrapperStringGenerator = Gen.option(_genList(arbitrary[String], "csv"))
    

    def createErrorModelGenerator = _generate(ErrorModelGenerator)
    def createPetGenerator = _generate(PetGenerator)
    def createNewPetGenerator = _generate(NewPetGenerator)


    def ErrorModelGenerator = for {
        code <- arbitrary[Int]
        message <- arbitrary[String]
    } yield ErrorModel(code, message)
    def PetGenerator = for {
        id <- arbitrary[Long]
        name <- arbitrary[String]
        tag <- Gen.option(arbitrary[String])
    } yield Pet(id, name, tag)
    def NewPetGenerator = for {
        id <- Gen.option(arbitrary[Long])
        name <- arbitrary[String]
        tag <- Gen.option(arbitrary[String])
    } yield NewPet(id, name, tag)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
    

}