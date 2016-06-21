package nested_options_validation.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class BasicOptionalNested_optionalOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(6), minLength(5))
}
class BasicOptionalNested_optionalOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new BasicOptionalNested_optionalOptConstraints(instance))
}
// ----- complex type validators -----
class BasicValidator(instance: Basic) extends RecursiveValidator {
    override val validators = Seq(
        new BasicOptionalValidator(instance.optional)
    )
}
class BasicOptionalOptValidator(instance: BasicOptionalOpt) extends RecursiveValidator {
    override val validators = Seq(
        new BasicOptionalNested_optionalValidator(instance.nested_optional)
    )
}
// ----- option delegating validators -----
class BasicOptionalValidator(instance: BasicOptional) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new BasicOptionalOptValidator(_) }
}
class BasicOptionalNested_optionalValidator(instance: BasicOptionalNested_optional) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new BasicOptionalNested_optionalOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- call validations -----
class GetValidator(basic: Basic) extends RecursiveValidator {
    override val validators = Seq(
        new BasicValidator(basic)
    
    )
}
