package numbers_validation.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class GetDouble_optionalOptConstraints(override val instance: Double) extends ValidationBase[Double] {
        implicit val doubleIntegral = scala.math.Numeric.DoubleAsIfIntegral
    override def constraints: Seq[Constraint[Double]] =
        Seq(max(10.toDouble, true), min(0.toDouble, true), multipleOf(5.toDouble))
}
class GetDouble_optionalOptValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new GetDouble_optionalOptConstraints(instance))
}
class GetInteger_requiredConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq(max(10.toInt, false), min(0.toInt, false), multipleOf(5.toInt))
}
class GetInteger_requiredValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new GetInteger_requiredConstraints(instance))
}
class GetInteger_optionalOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq(max(10.toInt, true), min(-10.toInt, true), multipleOf(5.toInt))
}
class GetInteger_optionalOptValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new GetInteger_optionalOptConstraints(instance))
}
class GetDouble_requiredConstraints(override val instance: Double) extends ValidationBase[Double] {
        implicit val doubleIntegral = scala.math.Numeric.DoubleAsIfIntegral
    override def constraints: Seq[Constraint[Double]] =
        Seq(max(10.toDouble, false), min(2.toDouble, false), multipleOf(5.toDouble))
}
class GetDouble_requiredValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new GetDouble_requiredConstraints(instance))
}
class GetLong_optionalOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq(max(10.toLong, true), min(10.toLong, true), multipleOf(10.toLong))
}
class GetLong_optionalOptValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new GetLong_optionalOptConstraints(instance))
}
class GetFloat_requiredConstraints(override val instance: Float) extends ValidationBase[Float] {
        implicit val floatIntegral = scala.math.Numeric.FloatAsIfIntegral
    override def constraints: Seq[Constraint[Float]] =
        Seq(max(10.toFloat, true), min(10.toFloat, true), multipleOf(5.toFloat))
}
class GetFloat_requiredValidator(instance: Float) extends RecursiveValidator {
    override val validators = Seq(new GetFloat_requiredConstraints(instance))
}
class GetFloat_optionalOptConstraints(override val instance: Float) extends ValidationBase[Float] {
        implicit val floatIntegral = scala.math.Numeric.FloatAsIfIntegral
    override def constraints: Seq[Constraint[Float]] =
        Seq(max(10.toFloat, false), min(1.toFloat, false), multipleOf(5.toFloat))
}
class GetFloat_optionalOptValidator(instance: Float) extends RecursiveValidator {
    override val validators = Seq(new GetFloat_optionalOptConstraints(instance))
}
class GetLong_requiredConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq(max(10.toLong, true), min(0.toLong, true), multipleOf(5.toLong))
}
class GetLong_requiredValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new GetLong_requiredConstraints(instance))
}
// ----- complex type validators -----
// ----- option delegating validators -----
class GetDouble_optionalValidator(instance: GetDouble_optional) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetDouble_optionalOptValidator(_) }
}
class GetInteger_optionalValidator(instance: GetInteger_optional) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetInteger_optionalOptValidator(_) }
}
class GetLong_optionalValidator(instance: GetLong_optional) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetLong_optionalOptValidator(_) }
}
class GetFloat_optionalValidator(instance: GetFloat_optional) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new GetFloat_optionalOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- call validations -----
class GetValidator(float_required: Float, double_required: Double, integer_optional: GetInteger_optional, long_required: Long, integer_required: Int, float_optional: GetFloat_optional, double_optional: GetDouble_optional, long_optional: GetLong_optional) extends RecursiveValidator {
    override val validators = Seq(
        new GetFloat_requiredValidator(float_required), 
    
        new GetDouble_requiredValidator(double_required), 
    
        new GetInteger_optionalValidator(integer_optional), 
    
        new GetLong_requiredValidator(long_required), 
    
        new GetInteger_requiredValidator(integer_required), 
    
        new GetFloat_optionalValidator(float_optional), 
    
        new GetDouble_optionalValidator(double_optional), 
    
        new GetLong_optionalValidator(long_optional)
    
    )
}
