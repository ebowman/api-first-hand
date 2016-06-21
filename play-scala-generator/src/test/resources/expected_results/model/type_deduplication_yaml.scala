package type_deduplication

package object yaml {

    import scala.math.BigInt

    import de.zalando.play.controllers.PlayPathBindables



    type PlantsPlant_idWateringsGetPlant_id = String
    type UsersGetLimit = Option[BigInt]
    type PlantsPlant_idPicturesPicture_idPutResponses404 = Null
    type PlantPlant_id = Option[String]
    type PlantsGetLimit = Option[BigInt]
    type UsersGetResponses200 = Seq[User]
    type ErrorCode = Option[Int]
    type PlantsGetResponses200 = Seq[Plant]
    type AreasGetResponses200 = Seq[Area]
    type PlantsGetOffset = Option[BigInt]
    type PlantsPlant_idPicturesGetResponses200 = Seq[String]
    type PlantsPlant_idWateringsGetResponses200 = Seq[Watering]


    case class SunlightNeeds(amount: PlantPlant_id) 
    case class Plant(species: PlantPlant_id, name: PlantPlant_id, description: PlantPlant_id, owner_id: PlantPlant_id, plant_id: PlantPlant_id, godparent: PlantPlant_id) 
    case class User(user_id: PlantPlant_id, name: PlantPlant_id, area_id: PlantPlant_id) 
    case class SigninData(username: PlantPlant_id, password: PlantPlant_id, email: PlantPlant_id) 
    case class Watering(watering_id: PlantPlant_id, user_id: PlantPlant_id, date: PlantPlant_id) 
    case class Area(area_id: PlantPlant_id, building: PlantPlant_id, floor: PlantPlant_id, room: PlantPlant_id) 
    case class Location(area_id: PlantPlant_id, details: PlantPlant_id) 
    case class Error(code: ErrorCode, message: PlantPlant_id, fields: PlantPlant_id) 
    case class WaterNeeds(amount: PlantPlant_id, period: PlantPlant_id) 


    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt

    implicit val bindable_OptionBigIntQuery = PlayPathBindables.createOptionQueryBindable[BigInt]


}
