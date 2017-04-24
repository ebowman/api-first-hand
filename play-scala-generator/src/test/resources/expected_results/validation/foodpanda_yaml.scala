package com.foodpanda.popsey.api
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class VendorQueryVendor_codesOptArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class VendorQueryVendor_codesOptArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new VendorQueryVendor_codesOptArrConstraints(instance))
}
class VendorQueryIncludesOptionEnumEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("menus,payments"))
}
class VendorQueryIncludesOptionEnumEnumValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new VendorQueryIncludesOptionEnumEnumConstraints(instance))
}
// ----- complex type validators -----
class VendorQueryValidator(instance: VendorQuery) extends RecursiveValidator {
    override val validators = Seq(
        new VendorQueryVendor_codesValidator(instance.vendor_codes), 
        new VendorQueryIncludesValidator(instance.includes)
    )
}

// ----- enum delegating validators -----
class VendorQueryIncludesOptionEnumValidator(instance: VendorQueryIncludesOptionEnum) extends RecursiveValidator {
    override val validators = Seq(new VendorQueryIncludesOptionEnumEnumValidator(instance.value))
}

// ----- option delegating validators -----
class VendorQueryVendor_codesValidator(instance: Option[Seq[String]]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new VendorQueryVendor_codesOptValidator(_) }
}
class VendorQueryIncludesValidator(instance: Option[VendorQueryIncludesOptionEnum]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new VendorQueryIncludesOptionEnumValidator(_) }
}
// ----- array delegating validators -----
class VendorQueryVendor_codesOptConstraints(override val instance: Seq[String]) extends ValidationBase[Seq[String]] {
    override def constraints: Seq[Constraint[Seq[String]]] =
        Seq()
}
class VendorQueryVendor_codesOptValidator(instance: Seq[String]) extends RecursiveValidator {
    override val validators = new VendorQueryVendor_codesOptConstraints(instance) +: instance.map { new VendorQueryVendor_codesOptArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class VendorsGetValidator(query: VendorQuery) extends RecursiveValidator {
    override val validators = Seq(
        new VendorQueryValidator(query)
    
    )
}
