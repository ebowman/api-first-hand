package nested_arrays_validation.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.ArrayWrapper
// ----- constraints and wrapper validations -----
class ActivityActionsOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(pattern("""the pattern to validate""".r))
}
class ActivityActionsOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ActivityActionsOptConstraints(instance))
}
class ExampleNestedArraysOptArrArrArrArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(6), minLength(5))
}
class ExampleNestedArraysOptArrArrArrArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ExampleNestedArraysOptArrArrArrArrConstraints(instance))
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
    override val validators = new ExampleMessagesOptConstraints(instance) +: instance.map { new ExampleMessagesOptArrValidator(_)}
}
class ExampleMessagesOptArrConstraints(override val instance: ExampleMessagesOptArr) extends ValidationBase[ExampleMessagesOptArr] {
    override def constraints: Seq[Constraint[ExampleMessagesOptArr]] =
        Seq(maxItems(6), minItems(5))
}
class ExampleMessagesOptArrValidator(instance: ExampleMessagesOptArr) extends RecursiveValidator {
    override val validators = new ExampleMessagesOptArrConstraints(instance) +: instance.map { new ActivityValidator(_)}
}
class ExampleNestedArraysOptConstraints(override val instance: ExampleNestedArraysOpt) extends ValidationBase[ExampleNestedArraysOpt] {
    override def constraints: Seq[Constraint[ExampleNestedArraysOpt]] =
        Seq(maxItems(6), minItems(5))
}
class ExampleNestedArraysOptValidator(instance: ExampleNestedArraysOpt) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrValidator(_)}
}
class ExampleNestedArraysOptArrConstraints(override val instance: ExampleNestedArraysOptArr) extends ValidationBase[ExampleNestedArraysOptArr] {
    override def constraints: Seq[Constraint[ExampleNestedArraysOptArr]] =
        Seq(maxItems(16), minItems(15))
}
class ExampleNestedArraysOptArrValidator(instance: ExampleNestedArraysOptArr) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrArrValidator(_)}
}
class ExampleNestedArraysOptArrArrConstraints(override val instance: ExampleNestedArraysOptArrArr) extends ValidationBase[ExampleNestedArraysOptArrArr] {
    override def constraints: Seq[Constraint[ExampleNestedArraysOptArrArr]] =
        Seq(maxItems(26), minItems(25))
}
class ExampleNestedArraysOptArrArrValidator(instance: ExampleNestedArraysOptArrArr) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrArrConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrArrArrValidator(_)}
}
class ExampleNestedArraysOptArrArrArrConstraints(override val instance: ExampleNestedArraysOptArrArrArr) extends ValidationBase[ExampleNestedArraysOptArrArrArr] {
    override def constraints: Seq[Constraint[ExampleNestedArraysOptArrArrArr]] =
        Seq(maxItems(36), minItems(35))
}
class ExampleNestedArraysOptArrArrArrValidator(instance: ExampleNestedArraysOptArrArrArr) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrArrArrConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrArrArrArrValidator(_)}
}
// ----- catch all simple validators -----
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
