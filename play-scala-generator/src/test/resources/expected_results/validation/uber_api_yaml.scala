package uber.api.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import java.util.UUID
import scala.math.BigDecimal
import de.zalando.play.controllers.ArrayWrapper
// ----- constraints and wrapper validations -----
class EstimatesPriceGetEnd_latitudeConstraints(override val instance: Double) extends ValidationBase[Double] {
    override def constraints: Seq[Constraint[Double]] =
        Seq()
}
class EstimatesPriceGetEnd_latitudeValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new EstimatesPriceGetEnd_latitudeConstraints(instance))
}
class EstimatesTimeGetStart_latitudeConstraints(override val instance: Double) extends ValidationBase[Double] {
    override def constraints: Seq[Constraint[Double]] =
        Seq()
}
class EstimatesTimeGetStart_latitudeValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new EstimatesTimeGetStart_latitudeConstraints(instance))
}
class ProductsGetLongitudeConstraints(override val instance: Double) extends ValidationBase[Double] {
    override def constraints: Seq[Constraint[Double]] =
        Seq()
}
class ProductsGetLongitudeValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new ProductsGetLongitudeConstraints(instance))
}
class EstimatesTimeGetStart_longitudeConstraints(override val instance: Double) extends ValidationBase[Double] {
    override def constraints: Seq[Constraint[Double]] =
        Seq()
}
class EstimatesTimeGetStart_longitudeValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new EstimatesTimeGetStart_longitudeConstraints(instance))
}
class ErrorCodeOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq()
}
class ErrorCodeOptValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new ErrorCodeOptConstraints(instance))
}
class EstimatesTimeGetCustomer_uuidOptConstraints(override val instance: UUID) extends ValidationBase[UUID] {
    override def constraints: Seq[Constraint[UUID]] =
        Seq()
}
class EstimatesTimeGetCustomer_uuidOptValidator(instance: UUID) extends RecursiveValidator {
    override val validators = Seq(new EstimatesTimeGetCustomer_uuidOptConstraints(instance))
}
class ProfilePictureOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ProfilePictureOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ProfilePictureOptConstraints(instance))
}
class ProductsGetLatitudeConstraints(override val instance: Double) extends ValidationBase[Double] {
    override def constraints: Seq[Constraint[Double]] =
        Seq()
}
class ProductsGetLatitudeValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new ProductsGetLatitudeConstraints(instance))
}
class EstimatesPriceGetStart_latitudeConstraints(override val instance: Double) extends ValidationBase[Double] {
    override def constraints: Seq[Constraint[Double]] =
        Seq()
}
class EstimatesPriceGetStart_latitudeValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new EstimatesPriceGetStart_latitudeConstraints(instance))
}
class EstimatesPriceGetEnd_longitudeConstraints(override val instance: Double) extends ValidationBase[Double] {
    override def constraints: Seq[Constraint[Double]] =
        Seq()
}
class EstimatesPriceGetEnd_longitudeValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new EstimatesPriceGetEnd_longitudeConstraints(instance))
}
class EstimatesPriceGetStart_longitudeConstraints(override val instance: Double) extends ValidationBase[Double] {
    override def constraints: Seq[Constraint[Double]] =
        Seq()
}
class EstimatesPriceGetStart_longitudeValidator(instance: Double) extends RecursiveValidator {
    override val validators = Seq(new EstimatesPriceGetStart_longitudeConstraints(instance))
}
// ----- complex type validators -----
// ----- option delegating validators -----
class ErrorCodeValidator(instance: ErrorCode) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ErrorCodeOptValidator(_) }
}
class EstimatesTimeGetCustomer_uuidValidator(instance: EstimatesTimeGetCustomer_uuid) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new EstimatesTimeGetCustomer_uuidOptValidator(_) }
}
class ProfilePictureValidator(instance: ProfilePicture) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ProfilePictureOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- call validations -----
class HistoryGetValidator(offset: ErrorCode, limit: ErrorCode) extends RecursiveValidator {
    override val validators = Seq(
        new ErrorCodeValidator(offset), 
    
        new ErrorCodeValidator(limit)
    
    )
}
class EstimatesTimeGetValidator(start_latitude: Double, start_longitude: Double, customer_uuid: EstimatesTimeGetCustomer_uuid, product_id: ProfilePicture) extends RecursiveValidator {
    override val validators = Seq(
        new EstimatesTimeGetStart_latitudeValidator(start_latitude), 
    
        new EstimatesTimeGetStart_longitudeValidator(start_longitude), 
    
        new EstimatesTimeGetCustomer_uuidValidator(customer_uuid), 
    
        new ProfilePictureValidator(product_id)
    
    )
}
class ProductsGetValidator(latitude: Double, longitude: Double) extends RecursiveValidator {
    override val validators = Seq(
        new ProductsGetLatitudeValidator(latitude), 
    
        new ProductsGetLongitudeValidator(longitude)
    
    )
}
class EstimatesPriceGetValidator(start_latitude: Double, start_longitude: Double, end_latitude: Double, end_longitude: Double) extends RecursiveValidator {
    override val validators = Seq(
        new EstimatesPriceGetStart_latitudeValidator(start_latitude), 
    
        new EstimatesPriceGetStart_longitudeValidator(start_longitude), 
    
        new EstimatesPriceGetEnd_latitudeValidator(end_latitude), 
    
        new EstimatesPriceGetEnd_longitudeValidator(end_longitude)
    
    )
}
