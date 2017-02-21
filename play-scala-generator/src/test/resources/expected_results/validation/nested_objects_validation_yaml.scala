package nested_objects_validation.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class NestedObjectsPlainSimpleConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(pattern("""the pattern""".r))
}
class NestedObjectsPlainSimpleValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new NestedObjectsPlainSimpleConstraints(instance))
}
class NestedObjectsNestedNested2Nested3BottomOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(30), minLength(3))
}
class NestedObjectsNestedNested2Nested3BottomOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new NestedObjectsNestedNested2Nested3BottomOptConstraints(instance))
}
// ----- complex type validators -----
class NestedObjectsValidator(instance: NestedObjects) extends RecursiveValidator {
    override val validators = Seq(
        new NestedObjectsPlainValidator(instance.plain), 
        new NestedObjectsNestedValidator(instance.nested)
    )
}
class NestedObjectsPlainOptionPlainValidator(instance: NestedObjectsPlainOptionPlain) extends RecursiveValidator {
    override val validators = Seq(
        new NestedObjectsPlainSimpleValidator(instance.simple)
    )
}
class NestedObjectsNestedOptionNestedValidator(instance: NestedObjectsNestedOptionNested) extends RecursiveValidator {
    override val validators = Seq(
        new NestedObjectsNestedNested2Validator(instance.nested2)
    )
}
class NestedObjectsNestedNested2Validator(instance: NestedObjectsNestedNested2) extends RecursiveValidator {
    override val validators = Seq(
        new NestedObjectsNestedNested2Nested3Validator(instance.nested3)
    )
}
class NestedObjectsNestedNested2Nested3OptionNested3Validator(instance: NestedObjectsNestedNested2Nested3OptionNested3) extends RecursiveValidator {
    override val validators = Seq(
        new NestedObjectsNestedNested2Nested3BottomValidator(instance.bottom)
    )
}

// ----- option delegating validators -----
class NestedObjectsPlainValidator(instance: Option[NestedObjectsPlain]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new NestedObjectsPlainOptionPlainValidator(_) }
}
class NestedObjectsNestedValidator(instance: Option[NestedObjectsNested]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new NestedObjectsNestedOptionNestedValidator(_) }
}
class NestedObjectsNestedNested2Nested3Validator(instance: Option[NestedObjectsNestedNested2Nested3]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new NestedObjectsNestedNested2Nested3OptionNested3Validator(_) }
}
class NestedObjectsNestedNested2Nested3BottomValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new NestedObjectsNestedNested2Nested3BottomOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class GetValidator(nestedObject: NestedObjects) extends RecursiveValidator {
    override val validators = Seq(
        new NestedObjectsValidator(nestedObject)
    
    )
}
