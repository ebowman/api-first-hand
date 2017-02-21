package error_in_array.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import scala.math.BigInt
// ----- constraints and wrapper validations -----
class ModelSchemaNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ModelSchemaNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaNameConstraints(instance))
}
class ModelSchemaDescriptionOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ModelSchemaDescriptionOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaDescriptionOptConstraints(instance))
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
class ModelSchemaArticleModelAttributesOptArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ModelSchemaArticleModelAttributesOptArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaArticleModelAttributesOptArrConstraints(instance))
}
class ModelSchemaSilhouetteIdEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("ankle_boots,nightdress,low_shoe,ballerina_shoe,voucher,belt,skates,eye_cosmetic,dress,sleeping_bag,system,other_accessoires,bag,etui,bikini_top,hair,undershirt,bathroom,bedroom,one_piece_nightwear,combination_clothing,sun,t_shirt_top,watch,night_shirt,pumps,stocking,boots,beach_trouser,tent,lip_cosmetic,underpant,skincare,backpack,pullover,lounge,sandals,suit_accessoires,coat,other_equipment,beach_shirt,bicycle,ski,cardigan,protector,beach_accessoires,jacket,one_piece_beachwear,headgear,shoe_accessoires,sneaker,headphones,kitchen,bicycle_equipment,ball,nightwear_combination,fitness,tights,one_piece_suit,vest,bustier,first_shoe,one_piece_underwear,bikini_combination,face_cosmetic,fragrance,glasses,shirt,trouser,racket,travel_equipment,case,backless_slipper,umbrella,underwear_combination,jewellery,shave,skirt,bathrobe,wallet,cleansing,night_trouser,corsage,peeling,beauty_equipment,nail,toys,bra,gloves,living,keychain,scarf,boards"))
}
class ModelSchemaSilhouetteIdEnumValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaSilhouetteIdEnumConstraints(instance))
}
class ModelSchemaAgeGroupsSeqEnumEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("baby,kid,teen,adult"))
}
class ModelSchemaAgeGroupsSeqEnumEnumValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaAgeGroupsSeqEnumEnumConstraints(instance))
}
class MetaCopyrightOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class MetaCopyrightOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new MetaCopyrightOptConstraints(instance))
}
class LinksSelfOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class LinksSelfOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new LinksSelfOptConstraints(instance))
}
class LinksRelatedOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class LinksRelatedOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new LinksRelatedOptConstraints(instance))
}
// ----- complex type validators -----
class ModelSchemaRootValidator(instance: ModelSchemaRoot) extends RecursiveValidator {
    override val validators = Seq(
        new ModelSchemaRootDataValidator(instance.data), 
        new ModelSchemaRootMetaValidator(instance.meta), 
        new ModelSchemaRootLinksValidator(instance.links)
    )
}
class ModelSchemaValidator(instance: ModelSchema) extends RecursiveValidator {
    override val validators = Seq(
        new ModelSchemaNameValidator(instance.name), 
        new ModelSchemaDescriptionValidator(instance.description), 
        new ModelSchemaSizeRegisterValidator(instance.sizeRegister), 
        new ModelSchemaBrandValidator(instance.brand), 
        new ModelSchemaPartnerArticleModelIdValidator(instance.partnerArticleModelId), 
        new ModelSchemaKeywordsValidator(instance.keywords), 
        new ModelSchemaLengthRegisterValidator(instance.lengthRegister), 
        new ModelSchemaSpecialDescriptionsValidator(instance.specialDescriptions), 
        new ModelSchemaArticleModelAttributesValidator(instance.articleModelAttributes), 
        new ModelSchemaSilhouetteIdValidator(instance.silhouetteId), 
        new ModelSchemaAgeGroupsValidator(instance.ageGroups)
    )
}
class MetaValidator(instance: Meta) extends RecursiveValidator {
    override val validators = Seq(
        new MetaCopyrightValidator(instance.copyright)
    )
}
class LinksValidator(instance: Links) extends RecursiveValidator {
    override val validators = Seq(
        new LinksSelfValidator(instance.self), 
        new LinksRelatedValidator(instance.related)
    )
}

// ----- enum delegating validators -----
class ModelSchemaSilhouetteIdValidator(instance: ModelSchemaSilhouetteId) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaSilhouetteIdEnumValidator(instance.value))
}
class ModelSchemaAgeGroupsSeqEnumValidator(instance: ModelSchemaAgeGroupsSeqEnum) extends RecursiveValidator {
    override val validators = Seq(new ModelSchemaAgeGroupsSeqEnumEnumValidator(instance.value))
}

// ----- option delegating validators -----
class ModelSchemaRootDataValidator(instance: Option[ModelSchema]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaValidator(_) }
}
class ModelSchemaDescriptionValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaDescriptionOptValidator(_) }
}
class ModelSchemaKeywordsValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaKeywordsOptValidator(_) }
}
class ModelSchemaLengthRegisterValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaLengthRegisterOptValidator(_) }
}
class ModelSchemaSpecialDescriptionsValidator(instance: Option[Seq[String]]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaSpecialDescriptionsOptValidator(_) }
}
class ModelSchemaArticleModelAttributesValidator(instance: Option[Seq[String]]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new ModelSchemaArticleModelAttributesOptValidator(_) }
}
class ModelSchemaRootMetaValidator(instance: Option[Meta]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new MetaValidator(_) }
}
class MetaCopyrightValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new MetaCopyrightOptValidator(_) }
}
class ModelSchemaRootLinksValidator(instance: Option[Links]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new LinksValidator(_) }
}
class LinksSelfValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new LinksSelfOptValidator(_) }
}
class LinksRelatedValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new LinksRelatedOptValidator(_) }
}
// ----- array delegating validators -----
class ModelSchemaSpecialDescriptionsOptConstraints(override val instance: Seq[String]) extends ValidationBase[Seq[String]] {
    override def constraints: Seq[Constraint[Seq[String]]] =
        Seq()
}
class ModelSchemaSpecialDescriptionsOptValidator(instance: Seq[String]) extends RecursiveValidator {
    override val validators = new ModelSchemaSpecialDescriptionsOptConstraints(instance) +: instance.map { new ModelSchemaSpecialDescriptionsOptArrValidator(_)}
}
class ModelSchemaArticleModelAttributesOptConstraints(override val instance: Seq[String]) extends ValidationBase[Seq[String]] {
    override def constraints: Seq[Constraint[Seq[String]]] =
        Seq(minItems(1))
}
class ModelSchemaArticleModelAttributesOptValidator(instance: Seq[String]) extends RecursiveValidator {
    override val validators = new ModelSchemaArticleModelAttributesOptConstraints(instance) +: instance.map { new ModelSchemaArticleModelAttributesOptArrValidator(_)}
}
class ModelSchemaAgeGroupsConstraints(override val instance: Seq[ModelSchemaAgeGroupsSeqEnum]) extends ValidationBase[Seq[ModelSchemaAgeGroupsSeqEnum]] {
    override def constraints: Seq[Constraint[Seq[ModelSchemaAgeGroupsSeqEnum]]] =
        Seq(maxItems(4))
}
class ModelSchemaAgeGroupsValidator(instance: Seq[ModelSchemaAgeGroupsSeqEnum]) extends RecursiveValidator {
    override val validators = new ModelSchemaAgeGroupsConstraints(instance) +: instance.map { new ModelSchemaAgeGroupsSeqEnumValidator(_)}
}
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class SchemaModelGetValidator(root: ModelSchemaRoot) extends RecursiveValidator {
    override val validators = Seq(
        new ModelSchemaRootValidator(root)
    
    )
}
