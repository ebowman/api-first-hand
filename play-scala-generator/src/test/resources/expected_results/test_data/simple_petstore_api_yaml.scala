package simple_petstore_api_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper

object Generators extends JsValueGenerators {
    

    
    def createNullGenerator = _generate(NullGenerator)
    def createNewPetTagGenerator = _generate(NewPetTagGenerator)
    def createLongGenerator = _generate(LongGenerator)
    def createPetsGetLimitGenerator = _generate(PetsGetLimitGenerator)
    def createNewPetIdGenerator = _generate(NewPetIdGenerator)
    def createPetsGetTagsOptGenerator = _generate(PetsGetTagsOptGenerator)
    def createPetsGetResponses200Generator = _generate(PetsGetResponses200Generator)
    def createPetsGetTagsGenerator = _generate(PetsGetTagsGenerator)
    

    
    def NullGenerator = arbitrary[Null]
    def NewPetTagGenerator = Gen.option(arbitrary[String])
    def LongGenerator = arbitrary[Long]
    def PetsGetLimitGenerator = Gen.option(arbitrary[Int])
    def NewPetIdGenerator = Gen.option(arbitrary[Long])
    def PetsGetTagsOptGenerator = _genList(arbitrary[String], "csv")
    def PetsGetResponses200Generator = Gen.containerOf[List,Pet](PetGenerator)
    def PetsGetTagsGenerator = Gen.option(PetsGetTagsOptGenerator)
    

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
        tag <- NewPetTagGenerator
    } yield Pet(id, name, tag)
    def NewPetGenerator = for {
        name <- arbitrary[String]
        id <- NewPetIdGenerator
        tag <- NewPetTagGenerator
    } yield NewPet(name, id, tag)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
}