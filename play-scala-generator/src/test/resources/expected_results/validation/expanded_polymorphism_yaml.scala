package expanded
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.ArrayWrapper
// ----- constraints and wrapper validations -----
class PetsIdDeleteIdConstraints(override val instance: Long) extends ValidationBase[Long] {
    override val reference = "⌿paths⌿/pets/{id}⌿delete⌿id"
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class PetsIdDeleteIdValidator(instance: Long) extends RecursiveValidator {
    override val reference = "⌿paths⌿/pets/{id}⌿delete⌿id"
    override val validators = Seq(new PetsIdDeleteIdConstraints(instance))
}
class PetsGetLimitOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override val reference = "⌿paths⌿/pets⌿get⌿limit⌿Opt"
    override def constraints: Seq[Constraint[Int]] =
        Seq(max(10.toInt, false), min(1.toInt, false))
}
class PetsGetLimitOptValidator(instance: Int) extends RecursiveValidator {
    override val reference = "⌿paths⌿/pets⌿get⌿limit⌿Opt"
    override val validators = Seq(new PetsGetLimitOptConstraints(instance))
}
class PetsIdGetIdConstraints(override val instance: Long) extends ValidationBase[Long] {
    override val reference = "⌿paths⌿/pets/{id}⌿get⌿id"
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class PetsIdGetIdValidator(instance: Long) extends RecursiveValidator {
    override val reference = "⌿paths⌿/pets/{id}⌿get⌿id"
    override val validators = Seq(new PetsIdGetIdConstraints(instance))
}
class PetsGetTagsOptArrConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿paths⌿/pets⌿get⌿tags⌿Opt⌿Arr"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetsGetTagsOptArrValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿paths⌿/pets⌿get⌿tags⌿Opt⌿Arr"
    override val validators = Seq(new PetsGetTagsOptArrConstraints(instance))
}
class NewPetNameConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿NewPet⌿name"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class NewPetNameValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿NewPet⌿name"
    override val validators = Seq(new NewPetNameConstraints(instance))
}
class NewPetTagOptConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿NewPet⌿tag⌿Opt"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class NewPetTagOptValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿NewPet⌿tag⌿Opt"
    override val validators = Seq(new NewPetTagOptConstraints(instance))
}
// ----- complex type validators -----
class NewPetValidator(instance: NewPet) extends RecursiveValidator {
    override val reference = "⌿definitions⌿NewPet"
    override val validators = Seq(
        new NewPetNameValidator(instance.name), 
        new NewPetTagValidator(instance.tag)
    )
}

// ----- option delegating validators -----
class PetsGetLimitValidator(instance: PetsGetLimit) extends RecursiveValidator {
    override val reference = "⌿paths⌿/pets⌿get⌿limit"
    override val validators = instance.toSeq.map { new PetsGetLimitOptValidator(_) }
}
class PetsGetTagsValidator(instance: PetsGetTags) extends RecursiveValidator {
    override val reference = "⌿paths⌿/pets⌿get⌿tags"
    override val validators = instance.toSeq.map { new PetsGetTagsOptValidator(_) }
}
class NewPetTagValidator(instance: NewPetTag) extends RecursiveValidator {
    override val reference = "⌿definitions⌿NewPet⌿tag"
    override val validators = instance.toSeq.map { new NewPetTagOptValidator(_) }
}
// ----- array delegating validators -----
class PetsGetTagsOptConstraints(override val instance: PetsGetTagsOpt) extends ValidationBase[PetsGetTagsOpt] {
    override val reference = "⌿paths⌿/pets⌿get⌿tags⌿Opt"
    override def constraints: Seq[Constraint[PetsGetTagsOpt]] =
        Seq()
}
class PetsGetTagsOptValidator(instance: PetsGetTagsOpt) extends RecursiveValidator {
    override val reference = "⌿paths⌿/pets⌿get⌿tags⌿Opt"
    override val validators = new PetsGetTagsOptConstraints(instance) +: instance.map { new PetsGetTagsOptArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class PetsPostValidator(pet: NewPet) extends RecursiveValidator {
    override val reference = "⌿paths⌿pets⌿post"
    override val validators = Seq(
        new NewPetValidator(pet)
    
    )
}
class PetsGetValidator(tags: PetsGetTags, limit: PetsGetLimit) extends RecursiveValidator {
    override val reference = "⌿paths⌿pets⌿get"
    override val validators = Seq(
        new PetsGetTagsValidator(tags), 
    
        new PetsGetLimitValidator(limit)
    
    )
}
class PetsIdDeleteValidator(id: Long) extends RecursiveValidator {
    override val reference = "⌿paths⌿pets⌿{id}⌿delete"
    override val validators = Seq(
        new PetsIdDeleteIdValidator(id)
    
    )
}
