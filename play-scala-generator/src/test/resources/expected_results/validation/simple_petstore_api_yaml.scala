package simple_petstore_api_yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.ArrayWrapper
// ----- constraints and wrapper validations -----
class PetsIdDeleteIdConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class PetsIdDeleteIdValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new PetsIdDeleteIdConstraints(instance))
}
class PetsGetLimitOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq(max(200.toInt, false), min(1.toInt, false))
}
class PetsGetLimitOptValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new PetsGetLimitOptConstraints(instance))
}
class PetsIdGetIdConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class PetsIdGetIdValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new PetsIdGetIdConstraints(instance))
}
class PetsGetTagsOptArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetsGetTagsOptArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetsGetTagsOptArrConstraints(instance))
}
class NewPetNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class NewPetNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new NewPetNameConstraints(instance))
}
class NewPetIdOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class NewPetIdOptValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new NewPetIdOptConstraints(instance))
}
class NewPetTagOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class NewPetTagOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new NewPetTagOptConstraints(instance))
}
// ----- complex type validators -----
class NewPetValidator(instance: NewPet) extends RecursiveValidator {
    override val validators = Seq(
        new NewPetNameValidator(instance.name), 
        new NewPetIdValidator(instance.id), 
        new NewPetTagValidator(instance.tag)
    )
}
// ----- option delegating validators -----
class PetsGetLimitValidator(instance: PetsGetLimit) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetsGetLimitOptValidator(_) }
}
class PetsGetTagsValidator(instance: PetsGetTags) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetsGetTagsOptValidator(_) }
}
class NewPetIdValidator(instance: NewPetId) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new NewPetIdOptValidator(_) }
}
class NewPetTagValidator(instance: NewPetTag) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new NewPetTagOptValidator(_) }
}
// ----- array delegating validators -----
class PetsGetTagsOptConstraints(override val instance: PetsGetTagsOpt) extends ValidationBase[PetsGetTagsOpt] {
    override def constraints: Seq[Constraint[PetsGetTagsOpt]] =
        Seq()
}
class PetsGetTagsOptValidator(instance: PetsGetTagsOpt) extends RecursiveValidator {
    override val validators = new PetsGetTagsOptConstraints(instance) +: instance.map { new PetsGetTagsOptArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- call validations -----
class PetsPostValidator(pet: NewPet) extends RecursiveValidator {
    override val validators = Seq(
        new NewPetValidator(pet)
    
    )
}
class PetsGetValidator(tags: PetsGetTags, limit: PetsGetLimit) extends RecursiveValidator {
    override val validators = Seq(
        new PetsGetTagsValidator(tags), 
    
        new PetsGetLimitValidator(limit)
    
    )
}
class PetsIdGetValidator(id: Long) extends RecursiveValidator {
    override val validators = Seq(
        new PetsIdGetIdValidator(id)
    
    )
}
class PetsIdDeleteValidator(id: Long) extends RecursiveValidator {
    override val validators = Seq(
        new PetsIdDeleteIdValidator(id)
    
    )
}
