package type_deduplication.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createUsersGetLimitGenerator = _generate(UsersGetLimitGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createPlantPlant_idGenerator = _generate(PlantPlant_idGenerator)
    def createPlantsGetLimitGenerator = _generate(PlantsGetLimitGenerator)
    def createUsersGetResponses200Generator = _generate(UsersGetResponses200Generator)
    def createErrorCodeGenerator = _generate(ErrorCodeGenerator)
    def createPlantsGetResponses200Generator = _generate(PlantsGetResponses200Generator)
    def createAreasGetResponses200Generator = _generate(AreasGetResponses200Generator)
    def createPlantsGetOffsetGenerator = _generate(PlantsGetOffsetGenerator)
    def createPlantsPlant_idPicturesGetResponses200Generator = _generate(PlantsPlant_idPicturesGetResponses200Generator)
    def createPlantsPlant_idWateringsGetResponses200Generator = _generate(PlantsPlant_idWateringsGetResponses200Generator)
    

    
    def StringGenerator = arbitrary[String]
    def UsersGetLimitGenerator = Gen.option(arbitrary[BigInt])
    def NullGenerator = arbitrary[Null]
    def PlantPlant_idGenerator = Gen.option(arbitrary[String])
    def PlantsGetLimitGenerator = Gen.option(arbitrary[BigInt])
    def UsersGetResponses200Generator = Gen.containerOf[List,User](UserGenerator)
    def ErrorCodeGenerator = Gen.option(arbitrary[Int])
    def PlantsGetResponses200Generator = Gen.containerOf[List,Plant](PlantGenerator)
    def AreasGetResponses200Generator = Gen.containerOf[List,Area](AreaGenerator)
    def PlantsGetOffsetGenerator = Gen.option(arbitrary[BigInt])
    def PlantsPlant_idPicturesGetResponses200Generator = Gen.containerOf[List,String](arbitrary[String])
    def PlantsPlant_idWateringsGetResponses200Generator = Gen.containerOf[List,Watering](WateringGenerator)
    

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
        amount <- PlantPlant_idGenerator
    } yield SunlightNeeds(amount)
    def PlantGenerator = for {
        species <- PlantPlant_idGenerator
        name <- PlantPlant_idGenerator
        description <- PlantPlant_idGenerator
        owner_id <- PlantPlant_idGenerator
        plant_id <- PlantPlant_idGenerator
        godparent <- PlantPlant_idGenerator
    } yield Plant(species, name, description, owner_id, plant_id, godparent)
    def UserGenerator = for {
        user_id <- PlantPlant_idGenerator
        name <- PlantPlant_idGenerator
        area_id <- PlantPlant_idGenerator
    } yield User(user_id, name, area_id)
    def SigninDataGenerator = for {
        username <- PlantPlant_idGenerator
        password <- PlantPlant_idGenerator
        email <- PlantPlant_idGenerator
    } yield SigninData(username, password, email)
    def WateringGenerator = for {
        watering_id <- PlantPlant_idGenerator
        user_id <- PlantPlant_idGenerator
        date <- PlantPlant_idGenerator
    } yield Watering(watering_id, user_id, date)
    def AreaGenerator = for {
        area_id <- PlantPlant_idGenerator
        building <- PlantPlant_idGenerator
        floor <- PlantPlant_idGenerator
        room <- PlantPlant_idGenerator
    } yield Area(area_id, building, floor, room)
    def LocationGenerator = for {
        area_id <- PlantPlant_idGenerator
        details <- PlantPlant_idGenerator
    } yield Location(area_id, details)
    def ErrorGenerator = for {
        code <- ErrorCodeGenerator
        message <- PlantPlant_idGenerator
        fields <- PlantPlant_idGenerator
    } yield Error(code, message, fields)
    def WaterNeedsGenerator = for {
        amount <- PlantPlant_idGenerator
        period <- PlantPlant_idGenerator
    } yield WaterNeeds(amount, period)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
}