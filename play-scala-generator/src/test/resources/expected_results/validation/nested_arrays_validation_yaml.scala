package nested_arrays_validation.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class ExampleNestedArraysOptArrArrArrArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(6), minLength(5))
}
class ExampleNestedArraysOptArrArrArrArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ExampleNestedArraysOptArrArrArrArrConstraints(instance))
}
class ActivityActionsOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(pattern("""the pattern to validate""".r))
}
class ActivityActionsOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ActivityActionsOptConstraints(instance))
}
// ----- complex type validators -----
class ExampleValidator(instance: Example) extends RecursiveValidator {
    override val validators = Seq(
        new ExampleNestedArraysValidator(instance.nestedArrays), 
        new ExampleMessagesValidator(instance.messages)
    )
}
class ActivityValidator(instance: Activity) extends RecursiveValidator {
    override val validators = Seq(
        new ActivityActionsValidator(instance.actions)
    )
}

// ----- option delegating validators -----
class AnotherPostExampleValidator(instance: Option[Example]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ExampleValidator(_) }
}
class ExampleNestedArraysValidator(instance: Option[Seq[Seq[Seq[Seq[String]]]]]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ExampleNestedArraysOptValidator(_) }
}
class ExampleMessagesValidator(instance: Option[Seq[Seq[Activity]]]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ExampleMessagesOptValidator(_) }
}
class ActivityActionsValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ActivityActionsOptValidator(_) }
}
// ----- array delegating validators -----
class ExampleNestedArraysOptConstraints(override val instance: Seq[Seq[Seq[Seq[String]]]]) extends ValidationBase[Seq[Seq[Seq[Seq[String]]]]] {
    override def constraints: Seq[Constraint[Seq[Seq[Seq[Seq[String]]]]]] =
        Seq(maxItems(6), minItems(5))
}
class ExampleNestedArraysOptValidator(instance: Seq[Seq[Seq[Seq[String]]]]) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrValidator(_)}
}
class ExampleNestedArraysOptArrConstraints(override val instance: Seq[Seq[Seq[String]]]) extends ValidationBase[Seq[Seq[Seq[String]]]] {
    override def constraints: Seq[Constraint[Seq[Seq[Seq[String]]]]] =
        Seq(maxItems(16), minItems(15))
}
class ExampleNestedArraysOptArrValidator(instance: Seq[Seq[Seq[String]]]) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrArrValidator(_)}
}
class ExampleNestedArraysOptArrArrConstraints(override val instance: Seq[Seq[String]]) extends ValidationBase[Seq[Seq[String]]] {
    override def constraints: Seq[Constraint[Seq[Seq[String]]]] =
        Seq(maxItems(26), minItems(25))
}
class ExampleNestedArraysOptArrArrValidator(instance: Seq[Seq[String]]) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrArrConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrArrArrValidator(_)}
}
class ExampleNestedArraysOptArrArrArrConstraints(override val instance: Seq[String]) extends ValidationBase[Seq[String]] {
    override def constraints: Seq[Constraint[Seq[String]]] =
        Seq(maxItems(36), minItems(35))
}
class ExampleNestedArraysOptArrArrArrValidator(instance: Seq[String]) extends RecursiveValidator {
    override val validators = new ExampleNestedArraysOptArrArrArrConstraints(instance) +: instance.map { new ExampleNestedArraysOptArrArrArrArrValidator(_)}
}
class ExampleMessagesOptConstraints(override val instance: Seq[Seq[Activity]]) extends ValidationBase[Seq[Seq[Activity]]] {
    override def constraints: Seq[Constraint[Seq[Seq[Activity]]]] =
        Seq(maxItems(6), minItems(5))
}
class ExampleMessagesOptValidator(instance: Seq[Seq[Activity]]) extends RecursiveValidator {
    override val validators = new ExampleMessagesOptConstraints(instance) +: instance.map { new ExampleMessagesOptArrValidator(_)}
}
class ExampleMessagesOptArrConstraints(override val instance: Seq[Activity]) extends ValidationBase[Seq[Activity]] {
    override def constraints: Seq[Constraint[Seq[Activity]]] =
        Seq(maxItems(6), minItems(5))
}
class ExampleMessagesOptArrValidator(instance: Seq[Activity]) extends RecursiveValidator {
    override val validators = new ExampleMessagesOptArrConstraints(instance) +: instance.map { new ActivityValidator(_)}
}
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class GetValidator(activity: Activity) extends RecursiveValidator {
    override val validators = Seq(
        new ActivityValidator(activity)
    
    )
}
class AnotherPostValidator(example: Option[Example]) extends RecursiveValidator {
    override val validators = Seq(
        new AnotherPostExampleValidator(example)
    
    )
}
