package heroku.petstore.api.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import scala.math.BigInt
// ----- constraints and wrapper validations -----
class PetIdGetPetIdConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetIdGetPetIdValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetIdGetPetIdConstraints(instance))
}
class PetNameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(100), minLength(3))
}
class PetNameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetNameOptConstraints(instance))
}
class PetBirthdayOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq(max(100.toInt, false), min(1.toInt, false))
}
class PetBirthdayOptValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new PetBirthdayOptConstraints(instance))
}
class GetLimitConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq(max(BigInt("10000"), false), min(BigInt("11"), false))
}
class GetLimitValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new GetLimitConstraints(instance))
}
// ----- complex type validators -----
class PetValidator(instance: Pet) extends RecursiveValidator {
    override val validators = Seq(
        new PetNameValidator(instance.name), 
        new PetBirthdayValidator(instance.birthday)
    )
}
// ----- option delegating validators -----
class PetNameValidator(instance: PetName) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetNameOptValidator(_) }
}
class PetBirthdayValidator(instance: PetBirthday) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetBirthdayOptValidator(_) }
}
class PutPetValidator(instance: PutPet) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- call validations -----
class PutValidator(pet: PutPet) extends RecursiveValidator {
    override val validators = Seq(
        new PutPetValidator(pet)
    
    )
}
class GetValidator(limit: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new GetLimitValidator(limit)
    
    )
}
class PetIdGetValidator(petId: String) extends RecursiveValidator {
    override val validators = Seq(
        new PetIdGetPetIdValidator(petId)
    
    )
}
class PostValidator(pet: Pet) extends RecursiveValidator {
    override val validators = Seq(
        new PetValidator(pet)
    
    )
}
