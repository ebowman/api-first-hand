package full.petstore.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper
import java.time.ZonedDateTime

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createOrderStatusGenerator = _generate(OrderStatusGenerator)
    def createPetsFindByStatusGetStatusOptGenerator = _generate(PetsFindByStatusGetStatusOptGenerator)
    def createUsersCreateWithListPostBodyOptGenerator = _generate(UsersCreateWithListPostBodyOptGenerator)
    def createOrderPetIdGenerator = _generate(OrderPetIdGenerator)
    def createPetsFindByStatusGetResponses200Generator = _generate(PetsFindByStatusGetResponses200Generator)
    def createPetsPostBodyGenerator = _generate(PetsPostBodyGenerator)
    def createOrderShipDateGenerator = _generate(OrderShipDateGenerator)
    def createUsersUsernamePutBodyGenerator = _generate(UsersUsernamePutBodyGenerator)
    def createStoresOrderPostBodyGenerator = _generate(StoresOrderPostBodyGenerator)
    def createOrderCompleteGenerator = _generate(OrderCompleteGenerator)
    def createPetTagsGenerator = _generate(PetTagsGenerator)
    def createLongGenerator = _generate(LongGenerator)
    def createOrderQuantityGenerator = _generate(OrderQuantityGenerator)
    def createPetPhotoUrlsGenerator = _generate(PetPhotoUrlsGenerator)
    def createUsersCreateWithListPostBodyGenerator = _generate(UsersCreateWithListPostBodyGenerator)
    def createPetsFindByStatusGetStatusGenerator = _generate(PetsFindByStatusGetStatusGenerator)
    def createPetCategoryGenerator = _generate(PetCategoryGenerator)
    def createPetTagsOptGenerator = _generate(PetTagsOptGenerator)
    

    
    def StringGenerator = arbitrary[String]
    def NullGenerator = arbitrary[Null]
    def OrderStatusGenerator = Gen.option(arbitrary[String])
    def PetsFindByStatusGetStatusOptGenerator = _genList(arbitrary[String], "multi")
    def UsersCreateWithListPostBodyOptGenerator: Gen[List[User]] = Gen.containerOf[List,User](UserGenerator)
    def OrderPetIdGenerator = Gen.option(arbitrary[Long])
    def PetsFindByStatusGetResponses200Generator: Gen[List[Pet]] = Gen.containerOf[List,Pet](PetGenerator)
    def PetsPostBodyGenerator = Gen.option(PetGenerator)
    def OrderShipDateGenerator = Gen.option(arbitrary[ZonedDateTime])
    def UsersUsernamePutBodyGenerator = Gen.option(UserGenerator)
    def StoresOrderPostBodyGenerator = Gen.option(OrderGenerator)
    def OrderCompleteGenerator = Gen.option(arbitrary[Boolean])
    def PetTagsGenerator = Gen.option(PetTagsOptGenerator)
    def LongGenerator = arbitrary[Long]
    def OrderQuantityGenerator = Gen.option(arbitrary[Int])
    def PetPhotoUrlsGenerator: Gen[List[String]] = Gen.containerOf[List,String](arbitrary[String])
    def UsersCreateWithListPostBodyGenerator = Gen.option(UsersCreateWithListPostBodyOptGenerator)
    def PetsFindByStatusGetStatusGenerator = Gen.option(PetsFindByStatusGetStatusOptGenerator)
    def PetCategoryGenerator = Gen.option(TagGenerator)
    def PetTagsOptGenerator: Gen[List[Tag]] = Gen.containerOf[List,Tag](TagGenerator)
    

    def createUserGenerator = _generate(UserGenerator)
    def createOrderGenerator = _generate(OrderGenerator)
    def createTagGenerator = _generate(TagGenerator)
    def createPetGenerator = _generate(PetGenerator)


    def UserGenerator = for {
        email <- OrderStatusGenerator
        username <- OrderStatusGenerator
        userStatus <- OrderQuantityGenerator
        lastName <- OrderStatusGenerator
        firstName <- OrderStatusGenerator
        id <- OrderPetIdGenerator
        phone <- OrderStatusGenerator
        password <- OrderStatusGenerator
    } yield User(email, username, userStatus, lastName, firstName, id, phone, password)
    def OrderGenerator = for {
        shipDate <- OrderShipDateGenerator
        quantity <- OrderQuantityGenerator
        petId <- OrderPetIdGenerator
        id <- OrderPetIdGenerator
        complete <- OrderCompleteGenerator
        status <- OrderStatusGenerator
    } yield Order(shipDate, quantity, petId, id, complete, status)
    def TagGenerator = for {
        id <- OrderPetIdGenerator
        name <- OrderStatusGenerator
    } yield Tag(id, name)
    def PetGenerator = for {
        name <- arbitrary[String]
        tags <- PetTagsGenerator
        photoUrls <- PetPhotoUrlsGenerator
        id <- OrderPetIdGenerator
        status <- OrderStatusGenerator
        category <- PetCategoryGenerator
    } yield Pet(name, tags, photoUrls, id, status, category)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    implicit lazy val arbDateTime: Arbitrary[ZonedDateTime] = Arbitrary(for {
        d <- arbitrary[java.util.Date]
    } yield ZonedDateTime.ofInstant(d.toInstant, java.time.ZoneId.systemDefault()))
    
    
    

}