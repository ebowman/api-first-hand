package basic_polymorphism.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

// ----- constraints and wrapper validations -----
class PetNameConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿Pet⌿name"
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(100), minLength(1))
}
class PetNameValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Pet⌿name"
    override val validators = Seq(new PetNameConstraints(instance))
}
class PetPetTypeConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿Pet⌿petType"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetPetTypeValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Pet⌿petType"
    override val validators = Seq(new PetPetTypeConstraints(instance))
}
// ----- complex type validators -----
class PetValidator(instance: IPet) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Pet"
    override val validators = Seq(
        new PetNameValidator(instance.name), 
        new PetPetTypeValidator(instance.petType)
    )
}

// ----- option delegating validators -----
class PutDummyValidator(instance: PutDummy) extends RecursiveValidator {
    override val reference = "⌿paths⌿/⌿put⌿dummy"
    override val validators = instance.toSeq.map { new PetValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class PutValidator(dummy: PutDummy) extends RecursiveValidator {
    override val reference = "⌿paths⌿⌿put"
    override val validators = Seq(
        new PutDummyValidator(dummy)
    
    )
}
