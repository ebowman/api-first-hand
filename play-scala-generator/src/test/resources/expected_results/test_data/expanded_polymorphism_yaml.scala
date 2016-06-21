package expanded

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper

object Generators extends JsValueGenerators {
    

    
    def createNullGenerator = _generate(NullGenerator)
    def createLongGenerator = _generate(LongGenerator)
    def createPetsGetLimitGenerator = _generate(PetsGetLimitGenerator)
    def createPetsGetTagsOptGenerator = _generate(PetsGetTagsOptGenerator)
    def createNewPetTagGenerator = _generate(NewPetTagGenerator)
    def createPetsGetResponses200Generator = _generate(PetsGetResponses200Generator)
    def createPetsGetTagsGenerator = _generate(PetsGetTagsGenerator)
    

    
    def NullGenerator = arbitrary[Null]
    def LongGenerator = arbitrary[Long]
    def PetsGetLimitGenerator = Gen.option(arbitrary[Int])
    def PetsGetTagsOptGenerator = _genList(arbitrary[String], "csv")
    def NewPetTagGenerator = Gen.option(arbitrary[String])
    def PetsGetResponses200Generator = Gen.containerOf[List,Pet](PetGenerator)
    def PetsGetTagsGenerator = Gen.option(PetsGetTagsOptGenerator)
    

    def createNewPetGenerator = _generate(NewPetGenerator)
    def createPetGenerator = _generate(PetGenerator)
    def createErrorGenerator = _generate(ErrorGenerator)


    def NewPetGenerator = for {
        name <- arbitrary[String]
        tag <- NewPetTagGenerator
    } yield NewPet(name, tag)
    def PetGenerator = for {
        name <- arbitrary[String]
        tag <- NewPetTagGenerator
        id <- arbitrary[Long]
    } yield Pet(name, tag, id)
    def ErrorGenerator = for {
        code <- arbitrary[Int]
        message <- arbitrary[String]
    } yield Error(code, message)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
}