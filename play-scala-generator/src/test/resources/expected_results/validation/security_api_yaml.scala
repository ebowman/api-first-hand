package security.api.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.ArrayWrapper
// ----- constraints and wrapper validations -----
class PetsIdGetIdArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetsIdGetIdArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetsIdGetIdArrConstraints(instance))
}
// ----- complex type validators -----
// ----- option delegating validators -----
// ----- array delegating validators -----
class PetsIdGetIdConstraints(override val instance: PetsIdGetId) extends ValidationBase[PetsIdGetId] {
    override def constraints: Seq[Constraint[PetsIdGetId]] =
        Seq()
}
class PetsIdGetIdValidator(instance: PetsIdGetId) extends RecursiveValidator {
    override val validators = new PetsIdGetIdConstraints(instance) +: instance.map { new PetsIdGetIdArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- call validations -----
class PetsIdGetValidator(id: PetsIdGetId) extends RecursiveValidator {
    override val validators = Seq(
        new PetsIdGetIdValidator(id)
    
    )
}
