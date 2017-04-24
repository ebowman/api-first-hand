package string_formats.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.Base64String
import Base64String._
import java.time.ZonedDateTime
import java.util.UUID
import java.time.LocalDate
import de.zalando.play.controllers.BinaryString
import BinaryString._
// ----- constraints and wrapper validations -----
class GetBase64OptConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿paths⌿/⌿get⌿base64⌿Opt"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class GetBase64OptValidator(instance: Base64String) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿base64⌿Opt"
    override val validators = Seq(new GetBase64OptConstraints(instance))
}
class GetPetIdConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿paths⌿/⌿get⌿petId"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class GetPetIdValidator(instance: BinaryString) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿petId"
    override val validators = Seq(new GetPetIdConstraints(instance))
}
class GetDate_timeOptConstraints(override val instance: ZonedDateTime) extends ValidationBase[ZonedDateTime] {
    override val reference = "⌿paths⌿/⌿get⌿date_time⌿Opt"
    override def constraints: Seq[Constraint[ZonedDateTime]] =
        Seq()
}
class GetDate_timeOptValidator(instance: ZonedDateTime) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿date_time⌿Opt"
    override val validators = Seq(new GetDate_timeOptConstraints(instance))
}
class GetUuidOptConstraints(override val instance: UUID) extends ValidationBase[UUID] {
    override val reference = "⌿paths⌿/⌿get⌿uuid⌿Opt"
    override def constraints: Seq[Constraint[UUID]] =
        Seq()
}
class GetUuidOptValidator(instance: UUID) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿uuid⌿Opt"
    override val validators = Seq(new GetUuidOptConstraints(instance))
}
class GetDateOptConstraints(override val instance: LocalDate) extends ValidationBase[LocalDate] {
    override val reference = "⌿paths⌿/⌿get⌿date⌿Opt"
    override def constraints: Seq[Constraint[LocalDate]] =
        Seq()
}
class GetDateOptValidator(instance: LocalDate) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿date⌿Opt"
    override val validators = Seq(new GetDateOptConstraints(instance))
}
// ----- complex type validators -----

// ----- option delegating validators -----
class GetBase64Validator(instance: GetBase64) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿base64"
    override val validators = instance.toSeq.map { new GetBase64OptValidator(_) }
}
class GetDate_timeValidator(instance: GetDate_time) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿date_time"
    override val validators = instance.toSeq.map { new GetDate_timeOptValidator(_) }
}
class GetUuidValidator(instance: GetUuid) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿uuid"
    override val validators = instance.toSeq.map { new GetUuidOptValidator(_) }
}
class GetDateValidator(instance: GetDate) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿date"
    override val validators = instance.toSeq.map { new GetDateOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class GetValidator(date_time: GetDate_time, date: GetDate, base64: GetBase64, uuid: GetUuid, petId: BinaryString) extends RecursiveValidator {
    override val reference = "⌿paths⌿⌿get"
    override val validators = Seq(
        new GetDate_timeValidator(date_time), 
    
        new GetDateValidator(date), 
    
        new GetBase64Validator(base64), 
    
        new GetUuidValidator(uuid), 
    
        new GetPetIdValidator(petId)
    
    )
}
