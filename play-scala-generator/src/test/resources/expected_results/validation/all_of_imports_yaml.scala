package all_of_imports.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import java.time.ZonedDateTime
// ----- constraints and wrapper validations -----
class ValueTypeConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿Value⌿type"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ValueTypeValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Value⌿type"
    override val validators = Seq(new ValueTypeConstraints(instance))
}
class DatetimeValueValueConstraints(override val instance: ZonedDateTime) extends ValidationBase[ZonedDateTime] {
    override val reference = "⌿definitions⌿DatetimeValue⌿value"
    override def constraints: Seq[Constraint[ZonedDateTime]] =
        Seq()
}
class DatetimeValueValueValidator(instance: ZonedDateTime) extends RecursiveValidator {
    override val reference = "⌿definitions⌿DatetimeValue⌿value"
    override val validators = Seq(new DatetimeValueValueConstraints(instance))
}
// ----- complex type validators -----

// ----- option delegating validators -----
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
class DatetimeValueValidator(instance: DatetimeValue) extends RecursiveValidator {
    override val reference = "⌿definitions⌿DatetimeValue"
    override val validators = Seq(
        new ValueTypeValidator(instance.`type`), 
        new DatetimeValueValueValidator(instance.value)
    )
}
// ----- call validations -----
class PostValidator(body: DatetimeValue) extends RecursiveValidator {
    override val reference = "⌿paths⌿⌿post"
    override val validators = Seq(
        new DatetimeValueValidator(body)
    
    )
}
