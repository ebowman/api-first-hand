package basic_polymorphism.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.ArrayWrapper
// ----- constraints and wrapper validations -----
class PetNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(100), minLength(1))
}
class PetNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetNameConstraints(instance))
}
class PetPetTypeConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetPetTypeValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetPetTypeConstraints(instance))
}
// ----- complex type validators -----
class PetValidator(instance: IPet) extends RecursiveValidator {
    override val validators = Seq(
        new PetNameValidator(instance.name), 
        new PetPetTypeValidator(instance.petType)
    )
}

// ----- option delegating validators -----
class PutDummyValidator(instance: PutDummy) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- call validations -----
class PutValidator(dummy: PutDummy) extends RecursiveValidator {
    override val validators = Seq(
        new PutDummyValidator(dummy)
    
    )
}
