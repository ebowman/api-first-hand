package form_data.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import java.io.File
import scala.math.BigInt
// ----- constraints and wrapper validations -----
class MultipartPostAvatarOptConstraints(override val instance: File) extends ValidationBase[File] {
    override def constraints: Seq[Constraint[File]] =
        Seq()
}
class MultipartPostAvatarOptValidator(instance: File) extends RecursiveValidator {
    override val validators = Seq(new MultipartPostAvatarOptConstraints(instance))
}
class MultipartPostNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class MultipartPostNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new MultipartPostNameConstraints(instance))
}
class Url_encodedPostNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class Url_encodedPostNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new Url_encodedPostNameConstraints(instance))
}
class BothPostNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class BothPostNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new BothPostNameConstraints(instance))
}
class BothPostRingtoneConstraints(override val instance: File) extends ValidationBase[File] {
    override def constraints: Seq[Constraint[File]] =
        Seq()
}
class BothPostRingtoneValidator(instance: File) extends RecursiveValidator {
    override val validators = Seq(new BothPostRingtoneConstraints(instance))
}
class BothPostYearOptConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class BothPostYearOptValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new BothPostYearOptConstraints(instance))
}
class Url_encodedPostAvatarConstraints(override val instance: File) extends ValidationBase[File] {
    override def constraints: Seq[Constraint[File]] =
        Seq()
}
class Url_encodedPostAvatarValidator(instance: File) extends RecursiveValidator {
    override val validators = Seq(new Url_encodedPostAvatarConstraints(instance))
}
// ----- complex type validators -----
// ----- option delegating validators -----
class MultipartPostAvatarValidator(instance: MultipartPostAvatar) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new MultipartPostAvatarOptValidator(_) }
}
class BothPostYearValidator(instance: BothPostYear) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new BothPostYearOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- call validations -----
class MultipartPostValidator(name: String, year: BothPostYear, avatar: MultipartPostAvatar) extends RecursiveValidator {
    override val validators = Seq(
        new MultipartPostNameValidator(name), 
    
        new BothPostYearValidator(year), 
    
        new MultipartPostAvatarValidator(avatar)
    
    )
}
class BothPostValidator(name: String, year: BothPostYear, avatar: MultipartPostAvatar, ringtone: File) extends RecursiveValidator {
    override val validators = Seq(
        new BothPostNameValidator(name), 
    
        new BothPostYearValidator(year), 
    
        new MultipartPostAvatarValidator(avatar), 
    
        new BothPostRingtoneValidator(ringtone)
    
    )
}
class Url_encodedPostValidator(name: String, year: BothPostYear, avatar: File) extends RecursiveValidator {
    override val validators = Seq(
        new Url_encodedPostNameValidator(name), 
    
        new BothPostYearValidator(year), 
    
        new Url_encodedPostAvatarValidator(avatar)
    
    )
}
