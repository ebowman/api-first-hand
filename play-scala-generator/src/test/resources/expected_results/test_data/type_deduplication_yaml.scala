package type_deduplication.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createOptionBigIntGenerator = _generate(OptionBigIntGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createOptionBigIntNameClashGenerator = _generate(OptionBigIntNameClashGenerator)
    def createSeqUserGenerator = _generate(SeqUserGenerator)
    def createSeqPlantGenerator = _generate(SeqPlantGenerator)
    def createSeqAreaGenerator = _generate(SeqAreaGenerator)
    def createOptionBigIntNameClashNameClashGenerator = _generate(OptionBigIntNameClashNameClashGenerator)
    def createSeqStringGenerator = _generate(SeqStringGenerator)
    def createSeqWateringGenerator = _generate(SeqWateringGenerator)
    

    
    def StringGenerator = arbitrary[String]
    def OptionBigIntGenerator = Gen.option(arbitrary[BigInt])
    def NullGenerator = arbitrary[Null]
    def OptionBigIntNameClashGenerator = Gen.option(arbitrary[BigInt])
    def SeqUserGenerator: Gen[List[User]] = Gen.containerOf[List,User](UserGenerator)
    def SeqPlantGenerator: Gen[List[Plant]] = Gen.containerOf[List,Plant](PlantGenerator)
    def SeqAreaGenerator: Gen[List[Area]] = Gen.containerOf[List,Area](AreaGenerator)
    def OptionBigIntNameClashNameClashGenerator = Gen.option(arbitrary[BigInt])
    def SeqStringGenerator: Gen[List[String]] = Gen.containerOf[List,String](arbitrary[String])
    def SeqWateringGenerator: Gen[List[Watering]] = Gen.containerOf[List,Watering](WateringGenerator)
    

    def createSunlightNeedsGenerator = _generate(SunlightNeedsGenerator)
    def createPlantGenerator = _generate(PlantGenerator)
    def createUserGenerator = _generate(UserGenerator)
    def createSigninDataGenerator = _generate(SigninDataGenerator)
    def createWateringGenerator = _generate(WateringGenerator)
    def createAreaGenerator = _generate(AreaGenerator)
    def createLocationGenerator = _generate(LocationGenerator)
    def createErrorGenerator = _generate(ErrorGenerator)
    def createWaterNeedsGenerator = _generate(WaterNeedsGenerator)


    def SunlightNeedsGenerator = for {
        amount <- Gen.option(arbitrary[String])
    } yield SunlightNeeds(amount)
    def PlantGenerator = for {
        species <- Gen.option(arbitrary[String])
        name <- Gen.option(arbitrary[String])
        description <- Gen.option(arbitrary[String])
        owner_id <- Gen.option(arbitrary[String])
        plant_id <- Gen.option(arbitrary[String])
        godparent <- Gen.option(arbitrary[String])
    } yield Plant(species, name, description, owner_id, plant_id, godparent)
    def UserGenerator = for {
        user_id <- Gen.option(arbitrary[String])
        name <- Gen.option(arbitrary[String])
        area_id <- Gen.option(arbitrary[String])
    } yield User(user_id, name, area_id)
    def SigninDataGenerator = for {
        username <- Gen.option(arbitrary[String])
        password <- Gen.option(arbitrary[String])
        email <- Gen.option(arbitrary[String])
    } yield SigninData(username, password, email)
    def WateringGenerator = for {
        watering_id <- Gen.option(arbitrary[String])
        user_id <- Gen.option(arbitrary[String])
        date <- Gen.option(arbitrary[String])
    } yield Watering(watering_id, user_id, date)
    def AreaGenerator = for {
        area_id <- Gen.option(arbitrary[String])
        building <- Gen.option(arbitrary[String])
        floor <- Gen.option(arbitrary[String])
        room <- Gen.option(arbitrary[String])
    } yield Area(area_id, building, floor, room)
    def LocationGenerator = for {
        area_id <- Gen.option(arbitrary[String])
        details <- Gen.option(arbitrary[String])
    } yield Location(area_id, details)
    def ErrorGenerator = for {
        code <- Gen.option(arbitrary[Int])
        message <- Gen.option(arbitrary[String])
        fields <- Gen.option(arbitrary[String])
    } yield Error(code, message, fields)
    def WaterNeedsGenerator = for {
        amount <- Gen.option(arbitrary[String])
        period <- Gen.option(arbitrary[String])
    } yield WaterNeeds(amount, period)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}