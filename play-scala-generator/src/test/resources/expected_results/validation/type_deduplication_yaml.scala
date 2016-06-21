package type_deduplication.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import scala.math.BigInt
// ----- constraints and wrapper validations -----
class PlantsPlant_idWateringsGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idWateringsGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idWateringsGetPlant_idConstraints(instance))
}
class UsersGetLimitOptConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class UsersGetLimitOptValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new UsersGetLimitOptConstraints(instance))
}
class PlantsPlant_idPicturesPicture_idGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idPicturesPicture_idGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idPicturesPicture_idGetPlant_idConstraints(instance))
}
class AreasArea_idDeleteArea_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class AreasArea_idDeleteArea_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new AreasArea_idDeleteArea_idConstraints(instance))
}
class UsersUser_idPictureGetUser_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUser_idPictureGetUser_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idPictureGetUser_idConstraints(instance))
}
class PlantsPlant_idPicturesGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idPicturesGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idPicturesGetPlant_idConstraints(instance))
}
class PlantsPlant_idLocationDeletePlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idLocationDeletePlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idLocationDeletePlant_idConstraints(instance))
}
class PlantsGetLimitOptConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq(min(BigInt("10"), false))
}
class PlantsGetLimitOptValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new PlantsGetLimitOptConstraints(instance))
}
class PlantPlant_idOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantPlant_idOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantPlant_idOptConstraints(instance))
}
class PlantsPlant_idPicturesPicture_idPutPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idPicturesPicture_idPutPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idPicturesPicture_idPutPlant_idConstraints(instance))
}
class PlantsPlant_idLocationGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idLocationGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idLocationGetPlant_idConstraints(instance))
}
class PlantsPlant_idDeletePlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idDeletePlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idDeletePlant_idConstraints(instance))
}
class PlantsPlant_idSunlight_needsGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idSunlight_needsGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idSunlight_needsGetPlant_idConstraints(instance))
}
class PlantsPlant_idGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idGetPlant_idConstraints(instance))
}
class UsersUser_idPutUser_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUser_idPutUser_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idPutUser_idConstraints(instance))
}
class AreasArea_idGetArea_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class AreasArea_idGetArea_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new AreasArea_idGetArea_idConstraints(instance))
}
class PlantsPlant_idWateringsWatering_idGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idWateringsWatering_idGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idWateringsWatering_idGetPlant_idConstraints(instance))
}
class PlantsPlant_idWateringsWatering_idPutWatering_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idWateringsWatering_idPutWatering_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idWateringsWatering_idPutWatering_idConstraints(instance))
}
class PlantsPlant_idWater_needsPutPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idWater_needsPutPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idWater_needsPutPlant_idConstraints(instance))
}
class PlantsGetOffsetOptConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq(max(BigInt("100"), false))
}
class PlantsGetOffsetOptValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new PlantsGetOffsetOptConstraints(instance))
}
class UserUser_idPlantsGetUser_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserUser_idPlantsGetUser_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserUser_idPlantsGetUser_idConstraints(instance))
}
class PlantsPlant_idPutPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idPutPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idPutPlant_idConstraints(instance))
}
class PlantsPlant_idWateringsWatering_idGetWatering_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idWateringsWatering_idGetWatering_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idWateringsWatering_idGetWatering_idConstraints(instance))
}
class PlantsPlant_idLocationPutPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idLocationPutPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idLocationPutPlant_idConstraints(instance))
}
class PlantsPlant_idPicturesPicture_idDeletePicture_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idPicturesPicture_idDeletePicture_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idPicturesPicture_idDeletePicture_idConstraints(instance))
}
class PlantsPlant_idWateringsWatering_idPutPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idWateringsWatering_idPutPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idWateringsWatering_idPutPlant_idConstraints(instance))
}
class UsersUser_idPicturePutUser_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUser_idPicturePutUser_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idPicturePutUser_idConstraints(instance))
}
class PlantsPlant_idSunlight_needsPutPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idSunlight_needsPutPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idSunlight_needsPutPlant_idConstraints(instance))
}
class PlantsPlant_idPicturesPicture_idGetPicture_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idPicturesPicture_idGetPicture_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idPicturesPicture_idGetPicture_idConstraints(instance))
}
class UsersUser_idDeleteUser_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUser_idDeleteUser_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idDeleteUser_idConstraints(instance))
}
class AreasArea_idPutArea_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class AreasArea_idPutArea_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new AreasArea_idPutArea_idConstraints(instance))
}
class PlantsPlant_idPicturesPicture_idDeletePlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idPicturesPicture_idDeletePlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idPicturesPicture_idDeletePlant_idConstraints(instance))
}
class PlantsPlant_idPicturesPicture_idPutPicture_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idPicturesPicture_idPutPicture_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idPicturesPicture_idPutPicture_idConstraints(instance))
}
class PlantsPlant_idWater_needsGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idWater_needsGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idWater_needsGetPlant_idConstraints(instance))
}
class UsersUser_idPictureDeleteUser_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUser_idPictureDeleteUser_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idPictureDeleteUser_idConstraints(instance))
}
class UsersUser_idGetUser_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUser_idGetUser_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idGetUser_idConstraints(instance))
}
// ----- complex type validators -----
class SigninDataValidator(instance: SigninData) extends RecursiveValidator {
    override val validators = Seq(
        new PlantPlant_idValidator(instance.username), 
        new PlantPlant_idValidator(instance.password), 
        new PlantPlant_idValidator(instance.email)
    )
}
class WaterNeedsValidator(instance: WaterNeeds) extends RecursiveValidator {
    override val validators = Seq(
        new PlantPlant_idValidator(instance.amount), 
        new PlantPlant_idValidator(instance.period)
    )
}
class PlantValidator(instance: Plant) extends RecursiveValidator {
    override val validators = Seq(
        new PlantPlant_idValidator(instance.species), 
        new PlantPlant_idValidator(instance.name), 
        new PlantPlant_idValidator(instance.description), 
        new PlantPlant_idValidator(instance.owner_id), 
        new PlantPlant_idValidator(instance.plant_id), 
        new PlantPlant_idValidator(instance.godparent)
    )
}
class UserValidator(instance: User) extends RecursiveValidator {
    override val validators = Seq(
        new PlantPlant_idValidator(instance.user_id), 
        new PlantPlant_idValidator(instance.name), 
        new PlantPlant_idValidator(instance.area_id)
    )
}
class LocationValidator(instance: Location) extends RecursiveValidator {
    override val validators = Seq(
        new PlantPlant_idValidator(instance.area_id), 
        new PlantPlant_idValidator(instance.details)
    )
}
class SunlightNeedsValidator(instance: SunlightNeeds) extends RecursiveValidator {
    override val validators = Seq(
        new PlantPlant_idValidator(instance.amount)
    )
}
// ----- option delegating validators -----
class UsersGetLimitValidator(instance: UsersGetLimit) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersGetLimitOptValidator(_) }
}
class PlantsGetLimitValidator(instance: PlantsGetLimit) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantsGetLimitOptValidator(_) }
}
class PlantPlant_idValidator(instance: PlantPlant_id) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantPlant_idOptValidator(_) }
}
class PlantsGetOffsetValidator(instance: PlantsGetOffset) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantsGetOffsetOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- call validations -----
class UsersUser_idDeleteValidator(user_id: String, user: User) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idDeleteUser_idValidator(user_id), 
    
        new UserValidator(user)
    
    )
}
class UsersPostValidator(signin_data: SigninData) extends RecursiveValidator {
    override val validators = Seq(
        new SigninDataValidator(signin_data)
    
    )
}
class PlantsPlant_idWater_needsGetValidator(plant_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idWater_needsGetPlant_idValidator(plant_id)
    
    )
}
class PlantsPlant_idSunlight_needsPutValidator(plant_id: String, sunlight_needs: SunlightNeeds) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idSunlight_needsPutPlant_idValidator(plant_id), 
    
        new SunlightNeedsValidator(sunlight_needs)
    
    )
}
class PlantsPlant_idGetValidator(plant_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idGetPlant_idValidator(plant_id)
    
    )
}
class PlantsPlant_idWateringsWatering_idGetValidator(plant_id: String, watering_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idWateringsWatering_idGetPlant_idValidator(plant_id), 
    
        new PlantsPlant_idWateringsWatering_idGetWatering_idValidator(watering_id)
    
    )
}
class PlantsPlant_idWater_needsPutValidator(plant_id: String, water_needs: WaterNeeds) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idWater_needsPutPlant_idValidator(plant_id), 
    
        new WaterNeedsValidator(water_needs)
    
    )
}
class PlantsPlant_idLocationGetValidator(plant_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idLocationGetPlant_idValidator(plant_id)
    
    )
}
class UserUser_idPlantsGetValidator(user_id: String, limit: UsersGetLimit, offset: UsersGetLimit) extends RecursiveValidator {
    override val validators = Seq(
        new UserUser_idPlantsGetUser_idValidator(user_id), 
    
        new UsersGetLimitValidator(limit), 
    
        new UsersGetLimitValidator(offset)
    
    )
}
class UsersUser_idGetValidator(user_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idGetUser_idValidator(user_id)
    
    )
}
class PlantsPlant_idLocationPutValidator(plant_id: String, location: Location) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idLocationPutPlant_idValidator(plant_id), 
    
        new LocationValidator(location)
    
    )
}
class PlantsPlant_idWateringsGetValidator(plant_id: String, limit: UsersGetLimit, offset: UsersGetLimit) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idWateringsGetPlant_idValidator(plant_id), 
    
        new UsersGetLimitValidator(limit), 
    
        new UsersGetLimitValidator(offset)
    
    )
}
class PlantsPlant_idWateringsWatering_idPutValidator(plant_id: String, watering_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idWateringsWatering_idPutPlant_idValidator(plant_id), 
    
        new PlantsPlant_idWateringsWatering_idPutWatering_idValidator(watering_id)
    
    )
}
class UsersGetValidator(limit: UsersGetLimit, offset: UsersGetLimit) extends RecursiveValidator {
    override val validators = Seq(
        new UsersGetLimitValidator(limit), 
    
        new UsersGetLimitValidator(offset)
    
    )
}
class UsersUser_idPictureGetValidator(user_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idPictureGetUser_idValidator(user_id)
    
    )
}
class UsersUser_idPutValidator(user_id: String, user: User) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idPutUser_idValidator(user_id), 
    
        new UserValidator(user)
    
    )
}
class PlantsPlant_idPicturesPicture_idDeleteValidator(plant_id: String, picture_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idPicturesPicture_idDeletePlant_idValidator(plant_id), 
    
        new PlantsPlant_idPicturesPicture_idDeletePicture_idValidator(picture_id)
    
    )
}
class AreasArea_idPutValidator(area_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new AreasArea_idPutArea_idValidator(area_id)
    
    )
}
class PlantsGetValidator(limit: PlantsGetLimit, offset: PlantsGetOffset) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsGetLimitValidator(limit), 
    
        new PlantsGetOffsetValidator(offset)
    
    )
}
class UsersUser_idPicturePutValidator(user_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idPicturePutUser_idValidator(user_id)
    
    )
}
class PlantsPlant_idLocationDeleteValidator(plant_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idLocationDeletePlant_idValidator(plant_id)
    
    )
}
class PlantsPlant_idDeleteValidator(plant_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idDeletePlant_idValidator(plant_id)
    
    )
}
class PlantsPlant_idPutValidator(plant_id: String, plant: Plant) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idPutPlant_idValidator(plant_id), 
    
        new PlantValidator(plant)
    
    )
}
class AreasGetValidator(limit: UsersGetLimit, offset: UsersGetLimit) extends RecursiveValidator {
    override val validators = Seq(
        new UsersGetLimitValidator(limit), 
    
        new UsersGetLimitValidator(offset)
    
    )
}
class PlantsPlant_idPicturesPicture_idPutValidator(plant_id: String, picture_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idPicturesPicture_idPutPlant_idValidator(plant_id), 
    
        new PlantsPlant_idPicturesPicture_idPutPicture_idValidator(picture_id)
    
    )
}
class PlantsPlant_idSunlight_needsGetValidator(plant_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idSunlight_needsGetPlant_idValidator(plant_id)
    
    )
}
class AreasArea_idDeleteValidator(area_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new AreasArea_idDeleteArea_idValidator(area_id)
    
    )
}
class UsersUser_idPictureDeleteValidator(user_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idPictureDeleteUser_idValidator(user_id)
    
    )
}
class PlantsPlant_idPicturesPicture_idGetValidator(plant_id: String, picture_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idPicturesPicture_idGetPlant_idValidator(plant_id), 
    
        new PlantsPlant_idPicturesPicture_idGetPicture_idValidator(picture_id)
    
    )
}
class AreasArea_idGetValidator(area_id: String) extends RecursiveValidator {
    override val validators = Seq(
        new AreasArea_idGetArea_idValidator(area_id)
    
    )
}
class PlantsPlant_idPicturesGetValidator(plant_id: String, limit: UsersGetLimit, offset: UsersGetLimit) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idPicturesGetPlant_idValidator(plant_id), 
    
        new UsersGetLimitValidator(limit), 
    
        new UsersGetLimitValidator(offset)
    
    )
}
