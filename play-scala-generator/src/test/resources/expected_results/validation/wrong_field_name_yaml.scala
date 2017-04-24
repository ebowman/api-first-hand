package wrong_field_name.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class GetCodesEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿paths⌿/⌿get⌿codes⌿Enum"
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("Get,GET,get"))
}
class GetCodesEnumValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿codes⌿Enum"
    override val validators = Seq(new GetCodesEnumConstraints(instance))
}
class GetOptCodesOptEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿paths⌿/⌿get⌿optCodes⌿Opt⌿Enum"
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("put,PUT,Put"))
}
class GetOptCodesOptEnumValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿optCodes⌿Opt⌿Enum"
    override val validators = Seq(new GetOptCodesOptEnumConstraints(instance))
}
// ----- complex type validators -----

// ----- enum delegating validators -----
class GetCodesValidator(instance: GetCodes) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿codes"
    override val validators = Seq(new GetCodesEnumValidator(instance.value))
}
class GetOptCodesOptValidator(instance: GetOptCodesOpt) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿optCodes⌿Opt"
    override val validators = Seq(new GetOptCodesOptEnumValidator(instance.value))
}

// ----- option delegating validators -----
class GetOptCodesValidator(instance: GetOptCodes) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿get⌿optCodes"
    override val validators = instance.toSeq.map { new GetOptCodesOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class GetValidator(optCodes: GetOptCodes, codes: GetCodes) extends RecursiveValidator {
    override val reference = "⌿paths⌿⌿get"
    override val validators = Seq(
        new GetOptCodesValidator(optCodes), 
    
        new GetCodesValidator(codes)
    
    )
}
