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
class SigninDataUsernameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class SigninDataUsernameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new SigninDataUsernameOptConstraints(instance))
}
class SigninDataPasswordOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class SigninDataPasswordOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new SigninDataPasswordOptConstraints(instance))
}
class SigninDataEmailOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class SigninDataEmailOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new SigninDataEmailOptConstraints(instance))
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
class WaterNeedsAmountOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class WaterNeedsAmountOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new WaterNeedsAmountOptConstraints(instance))
}
class WaterNeedsPeriodOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class WaterNeedsPeriodOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new WaterNeedsPeriodOptConstraints(instance))
}
class PlantsPlant_idSunlight_needsGetPlant_idConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantsPlant_idSunlight_needsGetPlant_idValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantsPlant_idSunlight_needsGetPlant_idConstraints(instance))
}
class PlantSpeciesOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantSpeciesOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantSpeciesOptConstraints(instance))
}
class PlantNameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantNameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantNameOptConstraints(instance))
}
class PlantDescriptionOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantDescriptionOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantDescriptionOptConstraints(instance))
}
class PlantOwner_idOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantOwner_idOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantOwner_idOptConstraints(instance))
}
class PlantPlant_idOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantPlant_idOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantPlant_idOptConstraints(instance))
}
class PlantGodparentOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PlantGodparentOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PlantGodparentOptConstraints(instance))
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
class UserUser_idOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserUser_idOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserUser_idOptConstraints(instance))
}
class UserNameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserNameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserNameOptConstraints(instance))
}
class UserArea_idOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserArea_idOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserArea_idOptConstraints(instance))
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
class LocationArea_idOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class LocationArea_idOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new LocationArea_idOptConstraints(instance))
}
class LocationDetailsOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class LocationDetailsOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new LocationDetailsOptConstraints(instance))
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
class SunlightNeedsAmountOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class SunlightNeedsAmountOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new SunlightNeedsAmountOptConstraints(instance))
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
        new SigninDataUsernameValidator(instance.username), 
        new SigninDataPasswordValidator(instance.password), 
        new SigninDataEmailValidator(instance.email)
    )
}
class WaterNeedsValidator(instance: WaterNeeds) extends RecursiveValidator {
    override val validators = Seq(
        new WaterNeedsAmountValidator(instance.amount), 
        new WaterNeedsPeriodValidator(instance.period)
    )
}
class PlantValidator(instance: Plant) extends RecursiveValidator {
    override val validators = Seq(
        new PlantSpeciesValidator(instance.species), 
        new PlantNameValidator(instance.name), 
        new PlantDescriptionValidator(instance.description), 
        new PlantOwner_idValidator(instance.owner_id), 
        new PlantPlant_idValidator(instance.plant_id), 
        new PlantGodparentValidator(instance.godparent)
    )
}
class UserValidator(instance: User) extends RecursiveValidator {
    override val validators = Seq(
        new UserUser_idValidator(instance.user_id), 
        new UserNameValidator(instance.name), 
        new UserArea_idValidator(instance.area_id)
    )
}
class LocationValidator(instance: Location) extends RecursiveValidator {
    override val validators = Seq(
        new LocationArea_idValidator(instance.area_id), 
        new LocationDetailsValidator(instance.details)
    )
}
class SunlightNeedsValidator(instance: SunlightNeeds) extends RecursiveValidator {
    override val validators = Seq(
        new SunlightNeedsAmountValidator(instance.amount)
    )
}

// ----- option delegating validators -----
class UsersGetLimitValidator(instance: Option[BigInt]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersGetLimitOptValidator(_) }
}
class PlantsGetLimitValidator(instance: Option[BigInt]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantsGetLimitOptValidator(_) }
}
class SigninDataUsernameValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new SigninDataUsernameOptValidator(_) }
}
class SigninDataPasswordValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new SigninDataPasswordOptValidator(_) }
}
class SigninDataEmailValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new SigninDataEmailOptValidator(_) }
}
class WaterNeedsAmountValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new WaterNeedsAmountOptValidator(_) }
}
class WaterNeedsPeriodValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new WaterNeedsPeriodOptValidator(_) }
}
class PlantSpeciesValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantSpeciesOptValidator(_) }
}
class PlantNameValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantNameOptValidator(_) }
}
class PlantDescriptionValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantDescriptionOptValidator(_) }
}
class PlantOwner_idValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantOwner_idOptValidator(_) }
}
class PlantPlant_idValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantPlant_idOptValidator(_) }
}
class PlantGodparentValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantGodparentOptValidator(_) }
}
class UserUser_idValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserUser_idOptValidator(_) }
}
class UserNameValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserNameOptValidator(_) }
}
class UserArea_idValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserArea_idOptValidator(_) }
}
class PlantsGetOffsetValidator(instance: Option[BigInt]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PlantsGetOffsetOptValidator(_) }
}
class LocationArea_idValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new LocationArea_idOptValidator(_) }
}
class LocationDetailsValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new LocationDetailsOptValidator(_) }
}
class SunlightNeedsAmountValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new SunlightNeedsAmountOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
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
class UserUser_idPlantsGetValidator(user_id: String, limit: Option[BigInt], offset: Option[BigInt]) extends RecursiveValidator {
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
class PlantsPlant_idWateringsGetValidator(plant_id: String, limit: Option[BigInt], offset: Option[BigInt]) extends RecursiveValidator {
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
class UsersGetValidator(limit: Option[BigInt], offset: Option[BigInt]) extends RecursiveValidator {
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
class PlantsGetValidator(limit: Option[BigInt], offset: Option[BigInt]) extends RecursiveValidator {
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
class AreasGetValidator(limit: Option[BigInt], offset: Option[BigInt]) extends RecursiveValidator {
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
class PlantsPlant_idPicturesGetValidator(plant_id: String, limit: Option[BigInt], offset: Option[BigInt]) extends RecursiveValidator {
    override val validators = Seq(
        new PlantsPlant_idPicturesGetPlant_idValidator(plant_id), 
    
        new UsersGetLimitValidator(limit), 
    
        new UsersGetLimitValidator(offset)
    
    )
}
