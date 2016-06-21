package cross_spec_references.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.ArrayWrapper
import scala.math.BigInt
// ----- constraints and wrapper validations -----
class ModelSchemaNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ModelSchemaNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaNameConstraints(instance))
}
class ModelSchemaSizeRegisterConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(10), minLength(10), pattern("""/[1-9][A-Z0-9]*/""".r))
}
class ModelSchemaSizeRegisterValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaSizeRegisterConstraints(instance))
}
class ModelSchemaBrandConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(3), minLength(3), pattern("""/[A-Z0-9]{3,3}/""".r))
}
class ModelSchemaBrandValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaBrandConstraints(instance))
}
class ModelSchemaPartnerArticleModelIdConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class ModelSchemaPartnerArticleModelIdValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaPartnerArticleModelIdConstraints(instance))
}
class MetaCopyrightOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class MetaCopyrightOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new MetaCopyrightOptConstraints(instance))
}
class ModelSchemaKeywordsOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(pattern("""/([\w\s]{1,255}|([\w\s]{1,255}, )+[\w\s]{1,255})/""".r))
}
class ModelSchemaKeywordsOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaKeywordsOptConstraints(instance))
}
class ModelSchemaLengthRegisterOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(10), minLength(10), pattern("""/[1-9][A-Z0-9]*/""".r))
}
class ModelSchemaLengthRegisterOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaLengthRegisterOptConstraints(instance))
}
class ModelSchemaSpecialDescriptionsOptArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ModelSchemaSpecialDescriptionsOptArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaSpecialDescriptionsOptArrConstraints(instance))
}
// ----- complex type validators -----
class PostRootValidator(instance: ModelSchemaRoot) extends RecursiveValidator {
    override val validators = Seq(
        new ModelSchemaRootDataValidator(instance.data), 
        new ModelSchemaRootMetaValidator(instance.meta), 
        new ModelSchemaRootLinksValidator(instance.links)
    )
}
class ModelSchemaRootDataOptValidator(instance: ModelSchemaRootDataOpt) extends RecursiveValidator {
    override val validators = Seq(
        new ModelSchemaNameValidator(instance.name), 
        new ModelSchemaSizeRegisterValidator(instance.sizeRegister), 
        new ModelSchemaBrandValidator(instance.brand), 
        new ModelSchemaPartnerArticleModelIdValidator(instance.partnerArticleModelId), 
        new MetaCopyrightValidator(instance.description), 
        new ModelSchemaKeywordsValidator(instance.keywords), 
        new ModelSchemaLengthRegisterValidator(instance.lengthRegister), 
        new ModelSchemaSpecialDescriptionsValidator(instance.specialDescriptions), 
        new ModelSchemaSpecialDescriptionsValidator(instance.articleModelAttributes)
    )
}
class ModelSchemaRootMetaOptValidator(instance: ModelSchemaRootMetaOpt) extends RecursiveValidator {
    override val validators = Seq(
        new MetaCopyrightValidator(instance.copyright)
    )
}
class ModelSchemaRootLinksOptValidator(instance: ModelSchemaRootLinksOpt) extends RecursiveValidator {
    override val validators = Seq(
        new MetaCopyrightValidator(instance.self), 
        new MetaCopyrightValidator(instance.related)
    )
}
// ----- option delegating validators -----
class ModelSchemaRootDataValidator(instance: ModelSchemaRootData) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaRootDataOptValidator(_) }
}
class MetaCopyrightValidator(instance: MetaCopyright) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new MetaCopyrightOptValidator(_) }
}
class ModelSchemaKeywordsValidator(instance: ModelSchemaKeywords) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaKeywordsOptValidator(_) }
}
class ModelSchemaLengthRegisterValidator(instance: ModelSchemaLengthRegister) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaLengthRegisterOptValidator(_) }
}
class ModelSchemaSpecialDescriptionsValidator(instance: ModelSchemaSpecialDescriptions) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaSpecialDescriptionsOptValidator(_) }
}
class ModelSchemaRootMetaValidator(instance: ModelSchemaRootMeta) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaRootMetaOptValidator(_) }
}
class ModelSchemaRootLinksValidator(instance: ModelSchemaRootLinks) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaRootLinksOptValidator(_) }
}
// ----- array delegating validators -----
class ModelSchemaSpecialDescriptionsOptConstraints(override val instance: ModelSchemaSpecialDescriptionsOpt) extends ValidationBase[ModelSchemaSpecialDescriptionsOpt] {
    override def constraints: Seq[Constraint[ModelSchemaSpecialDescriptionsOpt]] =
        Seq()
}
class ModelSchemaSpecialDescriptionsOptValidator(instance: ModelSchemaSpecialDescriptionsOpt) extends RecursiveValidator {
    override val validators = new ModelSchemaSpecialDescriptionsOptConstraints(instance) +: instance.map { new ModelSchemaSpecialDescriptionsOptArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- call validations -----
class PostValidator(root: ModelSchemaRoot) extends RecursiveValidator {
    override val validators = Seq(
        new PostRootValidator(root)
    
    )
}
