package nested_arrays_validation.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class ActivityActionsOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(pattern("""the pattern to validate""".r))
}
class ActivityActionsOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ActivityActionsOptConstraints(instance))
}
class ExampleNestedArraysOptArrResultArrResultArrResultArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(6), minLength(5))
}
class ExampleNestedArraysOptArrResultArrResultArrResultArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ExampleNestedArraysOptArrResultArrResultArrResultArrConstraints(instance))
}
// ----- complex type validators -----
class ExampleValidator(instance: Example) extends RecursiveValidator {
    override val validators = Seq(
        new ExampleMessagesValidator(instance.messages), 
        new ExampleNestedArraysValidator(instance.nestedArrays)
    )
}
class ActivityValidator(instance: Activity) extends RecursiveValidator {
    override val validators = Seq(
        new ActivityActionsValidator(instance.actions)
    )
}

// ----- option delegating validators -----
class AnotherPostExampleValidator(instance: AnotherPostExample) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ExampleValidator(_) }
}
class ExampleMessagesValidator(instance: ExampleMessages) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ExampleMessagesOptValidator(_) }
}
class ActivityActionsValidator(instance: ActivityActions) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ActivityActionsOptValidator(_) }
}
class ExampleNestedArraysValidator(instance: ExampleNestedArrays) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ExampleNestedArraysOptValidator(_) }
}
// ----- array delegating validators -----
class ExampleMessagesOptConstraints(override val instance: ExampleMessagesOpt) extends ValidationBase[ExampleMessagesOpt] {
    override def constraints: Seq[Constraint[ExampleMessagesOpt]] =
        Seq(maxItems(6), minItems(5))
}
class ExampleMessagesOptValidator(instance: ExampleMessagesOpt) extends RecursiveValidator {
    override val validators = new ExampleMessagesOptConstraints(instance) +: instance.map { new ExampleMessagesOptArrResultValidator(_)}
}
class ExampleMessagesOptArrResultConstraints(override val instance: ExampleMessagesOptArrResult) extends ValidationBase[ExampleMessagesOptArrResult] {
    override def constraints: Seq[Constraint[ExampleMessagesOptArrResult]] =
        Seq(maxItems(6), minItems(5))
}
class ExampleMessagesOptArrResultValidator(instance: ExampleMessagesOptArrResult) extends RecursiveValidator {
    override val validators = new ExampleMessagesOptArrResultConstraints(instance) +: instance.map { new ActivityValidator(_)}
}
class ExampleNestedArraysOptConstraints(override val instance: ExampleNestedArraysOpt) extends ValidationBase[ExampleNestedArraysOpt] {
    override def constraints: Seq[Constraint[ExampleNestedArraysOpt]] =
        Seq(maxItems(6), minItems(5))
}
class ExampleNestedArraysOptValidator(instance: ExampleNestedArraysOpt) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrResultValidator(_)}
}
class ExampleNestedArraysOptArrResultConstraints(override val instance: ExampleNestedArraysOptArrResult) extends ValidationBase[ExampleNestedArraysOptArrResult] {
    override def constraints: Seq[Constraint[ExampleNestedArraysOptArrResult]] =
        Seq(maxItems(16), minItems(15))
}
class ExampleNestedArraysOptArrResultValidator(instance: ExampleNestedArraysOptArrResult) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrResultConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrResultArrResultValidator(_)}
}
class ExampleNestedArraysOptArrResultArrResultConstraints(override val instance: ExampleNestedArraysOptArrResultArrResult) extends ValidationBase[ExampleNestedArraysOptArrResultArrResult] {
    override def constraints: Seq[Constraint[ExampleNestedArraysOptArrResultArrResult]] =
        Seq(maxItems(26), minItems(25))
}
class ExampleNestedArraysOptArrResultArrResultValidator(instance: ExampleNestedArraysOptArrResultArrResult) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrResultArrResultConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrResultArrResultArrResultValidator(_)}
}
class ExampleNestedArraysOptArrResultArrResultArrResultConstraints(override val instance: ExampleNestedArraysOptArrResultArrResultArrResult) extends ValidationBase[ExampleNestedArraysOptArrResultArrResultArrResult] {
    override def constraints: Seq[Constraint[ExampleNestedArraysOptArrResultArrResultArrResult]] =
        Seq(maxItems(36), minItems(35))
}
class ExampleNestedArraysOptArrResultArrResultArrResultValidator(instance: ExampleNestedArraysOptArrResultArrResultArrResult) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrResultArrResultArrResultConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrResultArrResultArrResultArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class GetValidator(activity: Activity) extends RecursiveValidator {
    override val validators = Seq(
        new ActivityValidator(activity)
    
    )
}
class AnotherPostValidator(example: AnotherPostExample) extends RecursiveValidator {
    override val validators = Seq(
        new AnotherPostExampleValidator(example)
    
    )
}
