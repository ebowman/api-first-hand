package heroku.petstore.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt

object Generators extends JsValueGenerators {
    

    
    def createPetNameGenerator = _generate(PetNameGenerator)
    def createStringGenerator = _generate(StringGenerator)
    def createPetBirthdayGenerator = _generate(PetBirthdayGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createBigIntGenerator = _generate(BigIntGenerator)
    def createPutPetGenerator = _generate(PutPetGenerator)
    def createGetResponses200Generator = _generate(GetResponses200Generator)
    

    
    def PetNameGenerator = Gen.option(arbitrary[String])
    def StringGenerator = arbitrary[String]
    def PetBirthdayGenerator = Gen.option(arbitrary[Int])
    def NullGenerator = arbitrary[Null]
    def BigIntGenerator = arbitrary[BigInt]
    def PutPetGenerator = Gen.option(PetGenerator)
    def GetResponses200Generator = Gen.containerOf[List,Pet](PetGenerator)
    

    def createPetGenerator = _generate(PetGenerator)


    def PetGenerator = for {
        name <- PetNameGenerator
        birthday <- PetBirthdayGenerator
    } yield Pet(name, birthday)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
}