package full.petstore.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import java.time.ZonedDateTime
import de.zalando.play.controllers.ArrayWrapper

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createOptionStringGenerator = _generate(OptionStringGenerator)
    def createSeqPetGenerator = _generate(SeqPetGenerator)
    def createOptionPetGenerator = _generate(OptionPetGenerator)
    def createOptionUserGenerator = _generate(OptionUserGenerator)
    def createOptionOrderGenerator = _generate(OptionOrderGenerator)
    def createLongGenerator = _generate(LongGenerator)
    def createOptionSeqUserGenerator = _generate(OptionSeqUserGenerator)
    def createOptionArrayWrapperStringGenerator = _generate(OptionArrayWrapperStringGenerator)
    

    
    def StringGenerator = arbitrary[String]
    def NullGenerator = arbitrary[Null]
    def OptionStringGenerator = Gen.option(arbitrary[String])
    def SeqPetGenerator: Gen[List[Pet]] = Gen.containerOf[List,Pet](PetGenerator)
    def OptionPetGenerator = Gen.option(PetGenerator)
    def OptionUserGenerator = Gen.option(UserGenerator)
    def OptionOrderGenerator = Gen.option(OrderGenerator)
    def LongGenerator = arbitrary[Long]
    def OptionSeqUserGenerator = Gen.option(Gen.containerOf[List,User](UserGenerator))
    def OptionArrayWrapperStringGenerator = Gen.option(_genList(arbitrary[String], "multi"))
    

    def createUserGenerator = _generate(UserGenerator)
    def createOrderGenerator = _generate(OrderGenerator)
    def createTagGenerator = _generate(TagGenerator)
    def createPetGenerator = _generate(PetGenerator)


    def UserGenerator = for {
        email <- Gen.option(arbitrary[String])
        username <- Gen.option(arbitrary[String])
        userStatus <- Gen.option(arbitrary[Int])
        lastName <- Gen.option(arbitrary[String])
        firstName <- Gen.option(arbitrary[String])
        id <- Gen.option(arbitrary[Long])
        phone <- Gen.option(arbitrary[String])
        password <- Gen.option(arbitrary[String])
    } yield User(email, username, userStatus, lastName, firstName, id, phone, password)
    def OrderGenerator = for {
        shipDate <- Gen.option(arbitrary[ZonedDateTime])
        quantity <- Gen.option(arbitrary[Int])
        petId <- Gen.option(arbitrary[Long])
        id <- Gen.option(arbitrary[Long])
        complete <- Gen.option(arbitrary[Boolean])
        status <- Gen.option(arbitrary[String])
    } yield Order(shipDate, quantity, petId, id, complete, status)
    def TagGenerator = for {
        id <- Gen.option(arbitrary[Long])
        name <- Gen.option(arbitrary[String])
    } yield Tag(id, name)
    def PetGenerator = for {
        name <- arbitrary[String]
        photoUrls <- Gen.containerOf[List,String](arbitrary[String])
        id <- Gen.option(arbitrary[Long])
        status <- Gen.option(arbitrary[String])
        tags <- Gen.option(Gen.containerOf[List,Tag](TagGenerator))
        category <- Gen.option(TagGenerator)
    } yield Pet(name, photoUrls, id, status, tags, category)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    implicit lazy val arbDateTime: Arbitrary[ZonedDateTime] = Arbitrary(for {
        d <- arbitrary[java.util.Date]
    } yield ZonedDateTime.ofInstant(d.toInstant, java.time.ZoneId.systemDefault()))
    
    
    

}