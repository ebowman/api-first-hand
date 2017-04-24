package string_formats.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.Base64String
import Base64String._
import de.zalando.play.controllers.BinaryString
import BinaryString._
import java.time.ZonedDateTime
import java.util.UUID
import java.time.LocalDate
// ----- constraints and wrapper validations -----
class GetBase64OptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class GetBase64OptValidator(instance: Base64String) extends RecursiveValidator {
    override val validators = Seq(new GetBase64OptConstraints(instance))
}
class GetPetIdConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class GetPetIdValidator(instance: BinaryString) extends RecursiveValidator {
    override val validators = Seq(new GetPetIdConstraints(instance))
}
class GetDate_timeOptConstraints(override val instance: ZonedDateTime) extends ValidationBase[ZonedDateTime] {
    override def constraints: Seq[Constraint[ZonedDateTime]] =
        Seq()
}
class GetDate_timeOptValidator(instance: ZonedDateTime) extends RecursiveValidator {
    override val validators = Seq(new GetDate_timeOptConstraints(instance))
}
class GetUuidOptConstraints(override val instance: UUID) extends ValidationBase[UUID] {
    override def constraints: Seq[Constraint[UUID]] =
        Seq()
}
class GetUuidOptValidator(instance: UUID) extends RecursiveValidator {
    override val validators = Seq(new GetUuidOptConstraints(instance))
}
class GetDateOptConstraints(override val instance: LocalDate) extends ValidationBase[LocalDate] {
    override def constraints: Seq[Constraint[LocalDate]] =
        Seq()
}
class GetDateOptValidator(instance: LocalDate) extends RecursiveValidator {
    override val validators = Seq(new GetDateOptConstraints(instance))
}
// ----- complex type validators -----

// ----- option delegating validators -----
class GetBase64Validator(instance: Option[Base64String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetBase64OptValidator(_) }
}
class GetDate_timeValidator(instance: Option[ZonedDateTime]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetDate_timeOptValidator(_) }
}
class GetUuidValidator(instance: Option[UUID]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetUuidOptValidator(_) }
}
class GetDateValidator(instance: Option[LocalDate]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetDateOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class GetValidator(date_time: Option[ZonedDateTime], date: Option[LocalDate], base64: Option[Base64String], uuid: Option[UUID], petId: BinaryString) extends RecursiveValidator {
    override val validators = Seq(
        new GetDate_timeValidator(date_time), 
    
        new GetDateValidator(date), 
    
        new GetBase64Validator(base64), 
    
        new GetUuidValidator(uuid), 
    
        new GetPetIdValidator(petId)
    
    )
}
