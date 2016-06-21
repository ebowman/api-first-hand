package basic_polymorphism_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createCatHuntingSkillGenerator = _generate(CatHuntingSkillGenerator)
    

    
    def CatHuntingSkillGenerator = Gen.oneOf(Seq(Clueless, Lazy, Adventurous, Aggressive))
    

    def createCatGenerator = _generate(CatGenerator)
    def createDogGenerator = _generate(DogGenerator)
    def createCatNDogGenerator = _generate(CatNDogGenerator)
    def createPetGenerator = _generate(PetGenerator)
    def createLabradorGenerator = _generate(LabradorGenerator)


    def CatGenerator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        huntingSkill <- CatHuntingSkillGenerator
    } yield Cat(name, petType, huntingSkill)
    def DogGenerator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        packSize <- arbitrary[Int]
    } yield Dog(name, petType, packSize)
    def CatNDogGenerator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        packSize <- arbitrary[Int]
        huntingSkill <- CatHuntingSkillGenerator
    } yield CatNDog(name, petType, packSize, huntingSkill)
    def PetGenerator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
    } yield Pet(name, petType)
    def LabradorGenerator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        packSize <- arbitrary[Int]
        cuteness <- arbitrary[Int]
    } yield Labrador(name, petType, packSize, cuteness)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
}