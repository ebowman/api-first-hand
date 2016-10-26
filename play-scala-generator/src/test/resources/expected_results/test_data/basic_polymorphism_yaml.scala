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
    

    
    def ZooTiersOptGenerator: Gen[List[Pet]] = Gen.containerOf[List,Pet](PetGenerator)
    def ZooTiersGenerator = Gen.option(ZooTiersOptGenerator)
    def CatHuntingSkillGenerator = { import CatHuntingSkill._ ; Gen.oneOf(Seq(Clueless, Lazy, Adventurous, Aggressive)) }
    def PutDummyGenerator = Gen.option(PetGenerator)
    def NullGenerator = arbitrary[Null]
    

    def createZooGenerator = _generate(ZooGenerator)
    def createCatGenerator = _generate(CatGenerator)
    def createLabradorAllOf0Generator = _generate(LabradorAllOf0Generator)
    def createDogGenerator = _generate(DogGenerator)
    def createCatNDogAllOf1Generator = _generate(CatNDogAllOf1Generator)
    def createCatNDogGenerator = _generate(CatNDogGenerator)
    def createPetGenerator = _generate(PetGenerator)
    def createLabradorGenerator = _generate(LabradorGenerator)
    def createCatNDogAllOf0Generator = _generate(CatNDogAllOf0Generator)


    def ZooGenerator = for {
        tiers <- ZooTiersGenerator
    } yield Zoo(tiers)
    def CatGenerator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        huntingSkill <- CatHuntingSkillGenerator
    } yield Cat(name, petType, huntingSkill)
    def LabradorAllOf0Generator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        packSize <- arbitrary[Int]
    } yield LabradorAllOf0(name, petType, packSize)
    def DogGenerator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        packSize <- arbitrary[Int]
    } yield Dog(name, petType, packSize)
    def CatNDogAllOf1Generator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        huntingSkill <- CatHuntingSkillGenerator
    } yield CatNDogAllOf1(name, petType, huntingSkill)
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
    def CatNDogAllOf0Generator = for {
        name <- arbitrary[String]
        petType <- arbitrary[String]
        packSize <- arbitrary[Int]
    } yield CatNDogAllOf0(name, petType, packSize)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}