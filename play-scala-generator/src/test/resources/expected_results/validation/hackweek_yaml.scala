package hackweek.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import scala.math.BigInt
// ----- constraints and wrapper validations -----
class ModelSchemaNameConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿ModelSchema⌿name"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ModelSchemaNameValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿name"
    override val validators = Seq(new ModelSchemaNameConstraints(instance))
}
class ModelSchemaSizeRegisterConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿ModelSchema⌿sizeRegister"
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(10), minLength(10), pattern("""/[1-9][A-Z0-9]*/""".r))
}
class ModelSchemaSizeRegisterValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿sizeRegister"
    override val validators = Seq(new ModelSchemaSizeRegisterConstraints(instance))
}
class ModelSchemaBrandConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿ModelSchema⌿brand"
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(3), minLength(3), pattern("""/[A-Z0-9]{3,3}/""".r))
}
class ModelSchemaBrandValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿brand"
    override val validators = Seq(new ModelSchemaBrandConstraints(instance))
}
class ModelSchemaPartnerArticleModelIdConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override val reference = "⌿definitions⌿ModelSchema⌿partnerArticleModelId"
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class ModelSchemaPartnerArticleModelIdValidator(instance: BigInt) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿partnerArticleModelId"
    override val validators = Seq(new ModelSchemaPartnerArticleModelIdConstraints(instance))
}
class MetaCopyrightOptConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿Meta⌿copyright⌿Opt"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class MetaCopyrightOptValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Meta⌿copyright⌿Opt"
    override val validators = Seq(new MetaCopyrightOptConstraints(instance))
}
class ModelSchemaAgeGroupsArrResultEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿ModelSchema⌿ageGroups⌿ArrResult⌿Enum"
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("baby,kid,teen,adult"))
}
class ModelSchemaAgeGroupsArrResultEnumValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿ageGroups⌿ArrResult⌿Enum"
    override val validators = Seq(new ModelSchemaAgeGroupsArrResultEnumConstraints(instance))
}
class ModelSchemaKeywordsOptConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿ModelSchema⌿keywords⌿Opt"
    override def constraints: Seq[Constraint[String]] =
        Seq(pattern("""/([\w\s]{1,255}|([\w\s]{1,255}, )+[\w\s]{1,255})/""".r))
}
class ModelSchemaKeywordsOptValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿keywords⌿Opt"
    override val validators = Seq(new ModelSchemaKeywordsOptConstraints(instance))
}
class ModelSchemaLengthRegisterOptConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿ModelSchema⌿lengthRegister⌿Opt"
    override def constraints: Seq[Constraint[String]] =
        Seq(maxLength(10), minLength(10), pattern("""/[1-9][A-Z0-9]*/""".r))
}
class ModelSchemaLengthRegisterOptValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿lengthRegister⌿Opt"
    override val validators = Seq(new ModelSchemaLengthRegisterOptConstraints(instance))
}
class ModelSchemaSilhouetteIdEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿ModelSchema⌿silhouetteId⌿Enum"
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("ankle_boots,nightdress,low_shoe,ballerina_shoe,voucher,belt,skates,eye_cosmetic,dress,sleeping_bag,system,other_accessoires,bag,etui,bikini_top,hair,undershirt,bathroom,bedroom,one_piece_nightwear,combination_clothing,sun,t_shirt_top,watch,night_shirt,pumps,stocking,boots,beach_trouser,tent,lip_cosmetic,underpant,skincare,backpack,pullover,lounge,sandals,suit_accessoires,coat,other_equipment,beach_shirt,bicycle,ski,cardigan,protector,beach_accessoires,jacket,one_piece_beachwear,headgear,shoe_accessoires,sneaker,headphones,kitchen,bicycle_equipment,ball,nightwear_combination,fitness,tights,one_piece_suit,vest,bustier,first_shoe,one_piece_underwear,bikini_combination,face_cosmetic,fragrance,glasses,shirt,trouser,racket,travel_equipment,case,backless_slipper,umbrella,underwear_combination,jewellery,shave,skirt,bathrobe,wallet,cleansing,night_trouser,corsage,peeling,beauty_equipment,nail,toys,bra,gloves,living,keychain,scarf,boards"))
}
class ModelSchemaSilhouetteIdEnumValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿silhouetteId⌿Enum"
    override val validators = Seq(new ModelSchemaSilhouetteIdEnumConstraints(instance))
}
class ModelSchemaSpecialDescriptionsOptArrConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿ModelSchema⌿specialDescriptions⌿Opt⌿Arr"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class ModelSchemaSpecialDescriptionsOptArrValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿specialDescriptions⌿Opt⌿Arr"
    override val validators = Seq(new ModelSchemaSpecialDescriptionsOptArrConstraints(instance))
}
// ----- complex type validators -----
class ModelSchemaRootValidator(instance: ModelSchemaRoot) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchemaRoot"
    override val validators = Seq(
        new ModelSchemaRootDataValidator(instance.data), 
        new ModelSchemaRootMetaValidator(instance.meta), 
        new ModelSchemaRootLinksValidator(instance.links)
    )
}
class ModelSchemaValidator(instance: ModelSchema) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema"
    override val validators = Seq(
        new ModelSchemaNameValidator(instance.name), 
        new ModelSchemaSizeRegisterValidator(instance.sizeRegister), 
        new ModelSchemaBrandValidator(instance.brand), 
        new ModelSchemaPartnerArticleModelIdValidator(instance.partnerArticleModelId), 
        new MetaCopyrightValidator(instance.description), 
        new ModelSchemaAgeGroupsValidator(instance.ageGroups), 
        new ModelSchemaKeywordsValidator(instance.keywords), 
        new ModelSchemaLengthRegisterValidator(instance.lengthRegister), 
        new ModelSchemaSilhouetteIdValidator(instance.silhouetteId), 
        new ModelSchemaSpecialDescriptionsValidator(instance.specialDescriptions), 
        new ModelSchemaSpecialDescriptionsValidator(instance.articleModelAttributes)
    )
}
class MetaValidator(instance: Meta) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Meta"
    override val validators = Seq(
        new MetaCopyrightValidator(instance.copyright)
    )
}
class LinksValidator(instance: Links) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Links"
    override val validators = Seq(
        new MetaCopyrightValidator(instance.self), 
        new MetaCopyrightValidator(instance.related)
    )
}

// ----- enum delegating validators -----
class ModelSchemaAgeGroupsArrResultValidator(instance: ModelSchemaAgeGroupsArrResult) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿ageGroups⌿ArrResult"
    override val validators = Seq(new ModelSchemaAgeGroupsArrResultEnumValidator(instance.value))
}
class ModelSchemaSilhouetteIdValidator(instance: ModelSchemaSilhouetteId) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿silhouetteId"
    override val validators = Seq(new ModelSchemaSilhouetteIdEnumValidator(instance.value))
}

// ----- option delegating validators -----
class ModelSchemaRootDataValidator(instance: ModelSchemaRootData) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchemaRoot⌿data"
    override val validators = instance.toSeq.map { new ModelSchemaValidator(_) }
}
class MetaCopyrightValidator(instance: MetaCopyright) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Meta⌿copyright"
    override val validators = instance.toSeq.map { new MetaCopyrightOptValidator(_) }
}
class ModelSchemaKeywordsValidator(instance: ModelSchemaKeywords) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿keywords"
    override val validators = instance.toSeq.map { new ModelSchemaKeywordsOptValidator(_) }
}
class ModelSchemaLengthRegisterValidator(instance: ModelSchemaLengthRegister) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿lengthRegister"
    override val validators = instance.toSeq.map { new ModelSchemaLengthRegisterOptValidator(_) }
}
class ModelSchemaSpecialDescriptionsValidator(instance: ModelSchemaSpecialDescriptions) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿specialDescriptions"
    override val validators = instance.toSeq.map { new ModelSchemaSpecialDescriptionsOptValidator(_) }
}
class ModelSchemaRootMetaValidator(instance: ModelSchemaRootMeta) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchemaRoot⌿meta"
    override val validators = instance.toSeq.map { new MetaValidator(_) }
}
class ModelSchemaRootLinksValidator(instance: ModelSchemaRootLinks) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchemaRoot⌿links"
    override val validators = instance.toSeq.map { new LinksValidator(_) }
}
// ----- array delegating validators -----
class ModelSchemaAgeGroupsConstraints(override val instance: ModelSchemaAgeGroups) extends ValidationBase[ModelSchemaAgeGroups] {
    override val reference = "⌿definitions⌿ModelSchema⌿ageGroups"
    override def constraints: Seq[Constraint[ModelSchemaAgeGroups]] =
        Seq(maxItems(4))
}
class ModelSchemaAgeGroupsValidator(instance: ModelSchemaAgeGroups) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿ageGroups"
    override val validators = new ModelSchemaAgeGroupsConstraints(instance) +: instance.map { new ModelSchemaAgeGroupsArrResultValidator(_)}
}
class ModelSchemaSpecialDescriptionsOptConstraints(override val instance: ModelSchemaSpecialDescriptionsOpt) extends ValidationBase[ModelSchemaSpecialDescriptionsOpt] {
    override val reference = "⌿definitions⌿ModelSchema⌿specialDescriptions⌿Opt"
    override def constraints: Seq[Constraint[ModelSchemaSpecialDescriptionsOpt]] =
        Seq()
}
class ModelSchemaSpecialDescriptionsOptValidator(instance: ModelSchemaSpecialDescriptionsOpt) extends RecursiveValidator {
    override val reference = "⌿definitions⌿ModelSchema⌿specialDescriptions⌿Opt"
    override val validators = new ModelSchemaSpecialDescriptionsOptConstraints(instance) +: instance.map { new ModelSchemaSpecialDescriptionsOptArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class SchemaModelGetValidator(root: ModelSchemaRoot) extends RecursiveValidator {
    override val reference = "⌿paths⌿schema⌿model⌿get"
    override val validators = Seq(
        new ModelSchemaRootValidator(root)
    
    )
}
