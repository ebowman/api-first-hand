package basic_polymorphism.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createZooTiersOptGenerator = _generate(ZooTiersOptGenerator)
    def createZooTiersGenerator = _generate(ZooTiersGenerator)
    def createCatHuntingSkillGenerator = _generate(CatHuntingSkillGenerator)
    def createPutDummyGenerator = _generate(PutDummyGenerator)
    def createNullGenerator = _generate(NullGenerator)
    

    
    def ZooTiersOptGenerator = Gen.containerOf[List,Pet](PetGenerator)
    def ZooTiersGenerator = Gen.option(ZooTiersOptGenerator)
    def CatHuntingSkillGenerator = Gen.oneOf(Seq(Clueless, Lazy, Adventurous, Aggressive))
    def PutDummyGenerator = Gen.option(PetGenerator)
    def NullGenerator = arbitrary[Null]
    

    def createZooGenerator = _generate(ZooGenerator)
    def createCatGenerator = _generate(CatGenerator)
    def createDogGenerator = _generate(DogGenerator)
    def createCatNDogGenerator = _generate(CatNDogGenerator)
    def createPetGenerator = _generate(PetGenerator)
    def createLabradorGenerator = _generate(LabradorGenerator)


    def ZooGenerator = for {
        tiers <- ZooTiersGenerator
    } yield Zoo(tiers)
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