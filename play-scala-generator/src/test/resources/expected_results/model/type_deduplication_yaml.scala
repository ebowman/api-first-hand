package type_deduplication


    import scala.math.BigInt

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class SunlightNeeds(amount: Option[String]) 
    case class Plant(species: Option[String], name: Option[String], description: Option[String], owner_id: Option[String], plant_id: Option[String], godparent: Option[String]) 
    case class User(user_id: Option[String], name: Option[String], area_id: Option[String]) 
    case class SigninData(username: Option[String], password: Option[String], email: Option[String]) 
    case class Watering(watering_id: Option[String], user_id: Option[String], date: Option[String]) 
    case class Area(area_id: Option[String], building: Option[String], floor: Option[String], room: Option[String]) 
    case class Location(area_id: Option[String], details: Option[String]) 
    case class Error(code: Option[Int], message: Option[String], fields: Option[String]) 
    case class WaterNeeds(amount: Option[String], period: Option[String]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val WaterNeedsReads: Reads[WaterNeeds] = (
            (JsPath \ "amount").read[Option[String]] and (JsPath \ "period").read[Option[String]]
        )(WaterNeeds.apply _)
        implicit val PlantReads: Reads[Plant] = (
            (JsPath \ "species").read[Option[String]] and (JsPath \ "name").read[Option[String]] and (JsPath \ "description").read[Option[String]] and (JsPath \ "owner_id").read[Option[String]] and (JsPath \ "plant_id").read[Option[String]] and (JsPath \ "godparent").read[Option[String]]
        )(Plant.apply _)
        implicit val LocationReads: Reads[Location] = (
            (JsPath \ "area_id").read[Option[String]] and (JsPath \ "details").read[Option[String]]
        )(Location.apply _)
        implicit val UserReads: Reads[User] = (
            (JsPath \ "user_id").read[Option[String]] and (JsPath \ "name").read[Option[String]] and (JsPath \ "area_id").read[Option[String]]
        )(User.apply _)
        implicit val SigninDataReads: Reads[SigninData] = (
            (JsPath \ "username").read[Option[String]] and (JsPath \ "password").read[Option[String]] and (JsPath \ "email").read[Option[String]]
        )(SigninData.apply _)
        implicit val SunlightNeedsReads: Reads[SunlightNeeds] = (
            (JsPath \ "amount").read[Option[String]]
        ).map(SunlightNeeds.apply )
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

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type PlantsPlant_idPicturesPicture_idPutResponses404 = Null


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt
    implicit val bindable_OptionBigIntQuery: QueryStringBindable[Option[BigInt]] = PlayPathBindables.createOptionQueryBindable[BigInt]

}