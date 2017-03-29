package required_parametrs.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class GetTest1Constraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(notNull)
}
class GetTest1Validator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new GetTest1Constraints(instance))
}
class GetTest2Test3Constraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq(notNull)
}
class GetTest2Test3Validator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new GetTest2Test3Constraints(instance))
}
// ----- complex type validators -----
class GetTest2OptValidator(instance: GetTest2Opt) extends RecursiveValidator {
    override val validators = Seq(
        new GetTest2Test3Validator(instance.test3)
    )
}

// ----- option delegating validators -----
class GetTest2Validator(instance: GetTest2) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetTest2OptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class GetValidator(test1: String, test2: GetTest2) extends RecursiveValidator {
    override val validators = Seq(
        new GetTest1Validator(test1), 
    
        new GetTest2Validator(test2)
    
    )
}
