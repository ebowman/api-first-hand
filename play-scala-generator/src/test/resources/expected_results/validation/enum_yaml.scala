package enum.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class TestGetIncludesOptionEnumEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("option_one,option_two"))
}
class TestGetIncludesOptionEnumEnumValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TestGetIncludesOptionEnumEnumConstraints(instance))
}
// ----- complex type validators -----

// ----- enum delegating validators -----
class TestGetIncludesOptionEnumValidator(instance: TestGetIncludesOptionEnum) extends RecursiveValidator {
    override val validators = Seq(new TestGetIncludesOptionEnumEnumValidator(instance.value))
}

// ----- option delegating validators -----
class TestGetIncludesValidator(instance: Option[TestGetIncludesOptionEnum]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new TestGetIncludesOptionEnumValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class TestGetValidator(includes: Option[TestGetIncludesOptionEnum]) extends RecursiveValidator {
    override val validators = Seq(
        new TestGetIncludesValidator(includes)
    
    )
}
