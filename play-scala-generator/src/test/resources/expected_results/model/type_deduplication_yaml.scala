package type_deduplication


    import scala.math.BigInt

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

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


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt
    implicit val bindable_OptionBigIntQuery: QueryStringBindable[Option[BigInt]] = PlayPathBindables.createOptionQueryBindable[BigInt]

}
//noinspection ScalaStyle
package yaml {


    case class SunlightNeeds(amount: PlantPlant_id) 
    case class Plant(species: PlantPlant_id, name: PlantPlant_id, description: PlantPlant_id, owner_id: PlantPlant_id, plant_id: PlantPlant_id, godparent: PlantPlant_id) 
    case class User(user_id: PlantPlant_id, name: PlantPlant_id, area_id: PlantPlant_id) 
    case class SigninData(username: PlantPlant_id, password: PlantPlant_id, email: PlantPlant_id) 
    case class Watering(watering_id: PlantPlant_id, user_id: PlantPlant_id, date: PlantPlant_id) 
    case class Area(area_id: PlantPlant_id, building: PlantPlant_id, floor: PlantPlant_id, room: PlantPlant_id) 
    case class Location(area_id: PlantPlant_id, details: PlantPlant_id) 
    case class Error(code: ErrorCode, message: PlantPlant_id, fields: PlantPlant_id) 
    case class WaterNeeds(amount: PlantPlant_id, period: PlantPlant_id) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val WaterNeedsReads: Reads[WaterNeeds] = (
            (JsPath \ "amount").readNullable[String] and (JsPath \ "period").readNullable[String]
        )(WaterNeeds.apply _)
        implicit val PlantReads: Reads[Plant] = (
            (JsPath \ "species").readNullable[String] and (JsPath \ "name").readNullable[String] and (JsPath \ "description").readNullable[String] and (JsPath \ "owner_id").readNullable[String] and (JsPath \ "plant_id").readNullable[String] and (JsPath \ "godparent").readNullable[String]
        )(Plant.apply _)
        implicit val LocationReads: Reads[Location] = (
            (JsPath \ "area_id").readNullable[String] and (JsPath \ "details").readNullable[String]
        )(Location.apply _)
        implicit val UserReads: Reads[User] = (
            (JsPath \ "user_id").readNullable[String] and (JsPath \ "name").readNullable[String] and (JsPath \ "area_id").readNullable[String]
        )(User.apply _)
        implicit val SigninDataReads: Reads[SigninData] = (
            (JsPath \ "username").readNullable[String] and (JsPath \ "password").readNullable[String] and (JsPath \ "email").readNullable[String]
        )(SigninData.apply _)
        implicit val SunlightNeedsReads: Reads[SunlightNeeds] = (
            (JsPath \ "amount").readNullable[String]
        )(SunlightNeeds.apply _)
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val WaterNeedsWrites: Writes[WaterNeeds] = new Writes[WaterNeeds] {
        def writes(ss: WaterNeeds) =
          Json.obj(
            "amount" -> ss.amount, 
            "period" -> ss.period
          )
        }
    implicit val LocationWrites: Writes[Location] = new Writes[Location] {
        def writes(ss: Location) =
          Json.obj(
            "area_id" -> ss.area_id, 
            "details" -> ss.details
          )
        }
    implicit val PlantWrites: Writes[Plant] = new Writes[Plant] {
        def writes(ss: Plant) =
          Json.obj(
            "species" -> ss.species, 
            "name" -> ss.name, 
            "description" -> ss.description, 
            "owner_id" -> ss.owner_id, 
            "plant_id" -> ss.plant_id, 
            "godparent" -> ss.godparent
          )
        }
    implicit val AreaWrites: Writes[Area] = new Writes[Area] {
        def writes(ss: Area) =
          Json.obj(
            "area_id" -> ss.area_id, 
            "building" -> ss.building, 
            "floor" -> ss.floor, 
            "room" -> ss.room
          )
        }
    implicit val SunlightNeedsWrites: Writes[SunlightNeeds] = new Writes[SunlightNeeds] {
        def writes(ss: SunlightNeeds) =
          Json.obj(
            "amount" -> ss.amount
          )
        }
    implicit val UserWrites: Writes[User] = new Writes[User] {
        def writes(ss: User) =
          Json.obj(
            "user_id" -> ss.user_id, 
            "name" -> ss.name, 
            "area_id" -> ss.area_id
          )
        }
    implicit val WateringWrites: Writes[Watering] = new Writes[Watering] {
        def writes(ss: Watering) =
          Json.obj(
            "watering_id" -> ss.watering_id, 
            "user_id" -> ss.user_id, 
            "date" -> ss.date
          )
        }
    implicit val ErrorWrites: Writes[Error] = new Writes[Error] {
        def writes(ss: Error) =
          Json.obj(
            "code" -> ss.code, 
            "message" -> ss.message, 
            "fields" -> ss.fields
          )
        }
    }
}
