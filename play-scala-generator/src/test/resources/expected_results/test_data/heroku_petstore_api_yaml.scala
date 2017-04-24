package heroku.petstore.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createBigIntGenerator = _generate(BigIntGenerator)
    def createOptionPetGenerator = _generate(OptionPetGenerator)
    def createSeqPetGenerator = _generate(SeqPetGenerator)
    

    
    def StringGenerator = arbitrary[String]
    def NullGenerator = arbitrary[Null]
    def BigIntGenerator = arbitrary[BigInt]
    def OptionPetGenerator = Gen.option(PetGenerator)
    def SeqPetGenerator: Gen[List[Pet]] = Gen.containerOf[List,Pet](PetGenerator)
    

    def createPetGenerator = _generate(PetGenerator)


    def PetGenerator = for {
        name <- Gen.option(arbitrary[String])
        birthday <- Gen.option(arbitrary[Int])
    } yield Pet(name, birthday)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}