package hackweek

package object yaml {

    import de.zalando.play.controllers.ArrayWrapper
    import scala.math.BigInt



    type ModelSchemaSpecialDescriptionsOpt = Seq[String]
    type MetaCopyright = Option[String]
    type ModelSchemaKeywords = Option[String]
    type ModelSchemaSpecialDescriptions = Option[ModelSchemaSpecialDescriptionsOpt]
    type ErrorsErrorsOpt = ArrayWrapper[Error]
    type ModelSchemaRootData = Option[ModelSchema]
    type ErrorSource = Option[ErrorSourceNameClash]
    type ModelSchemaArticleModelAttributesOpt = ArrayWrapper[String]
    type ModelSchemaRootLinks = Option[Links]
    type ModelSchemaArticleModelAttributes = Option[ModelSchemaArticleModelAttributesOpt]
    type ModelSchemaLengthRegister = Option[String]
    type ErrorsErrors = Option[ErrorsErrorsOpt]
    type ModelSchemaAgeGroups = ArrayWrapper[ModelSchemaAgeGroupsArr]
    type ModelSchemaRootMeta = Option[Meta]


    case class ModelSchemaRoot(data: ModelSchemaRootData, meta: ModelSchemaRootMeta, links: ModelSchemaRootLinks) 
    case class Errors(errors: ErrorsErrors) 
    case class ErrorSourceNameClash(pointer: MetaCopyright, parameter: MetaCopyright) 
    case class Meta(copyright: MetaCopyright) 
    case class ModelSchema(name: String, sizeRegister: String, brand: String, partnerArticleModelId: BigInt, description: MetaCopyright, ageGroups: ModelSchemaAgeGroups, keywords: ModelSchemaKeywords, lengthRegister: ModelSchemaLengthRegister, silhouetteId: ModelSchemaSilhouetteId, specialDescriptions: ModelSchemaSpecialDescriptions, articleModelAttributes: ModelSchemaArticleModelAttributes) 
    case class Error(source: ErrorSource, code: MetaCopyright, status: MetaCopyright, detail: MetaCopyright, title: MetaCopyright) 
    case class Links(self: MetaCopyright, related: MetaCopyright) 

    sealed trait ModelSchemaAgeGroupsArr { def value: String }
    case object Baby extends ModelSchemaAgeGroupsArr { val value = "baby" }
    case object Kid extends ModelSchemaAgeGroupsArr { val value = "kid" }
    case object Teen extends ModelSchemaAgeGroupsArr { val value = "teen" }
    case object Adult extends ModelSchemaAgeGroupsArr { val value = "adult" }
    implicit def stringToModelSchemaAgeGroupsArr(in: String): ModelSchemaAgeGroupsArr = in match {
        case "baby" => Baby
        case "kid" => Kid
        case "teen" => Teen
        case "adult" => Adult
    }
    sealed trait ModelSchemaSilhouetteId { def value: String }
    case object Backpack extends ModelSchemaSilhouetteId { val value = "backpack" }
    case object Keychain extends ModelSchemaSilhouetteId { val value = "keychain" }
    case object Bra extends ModelSchemaSilhouetteId { val value = "bra" }
    case object Skincare extends ModelSchemaSilhouetteId { val value = "skincare" }
    case object One_piece_beachwear extends ModelSchemaSilhouetteId { val value = "one_piece_beachwear" }
    case object Dress extends ModelSchemaSilhouetteId { val value = "dress" }
    case object Sleeping_bag extends ModelSchemaSilhouetteId { val value = "sleeping_bag" }
    case object Eye_cosmetic extends ModelSchemaSilhouetteId { val value = "eye_cosmetic" }
    case object Fitness extends ModelSchemaSilhouetteId { val value = "fitness" }
    case object T_shirt_top extends ModelSchemaSilhouetteId { val value = "t_shirt_top" }
    case object Night_trouser extends ModelSchemaSilhouetteId { val value = "night_trouser" }
    case object Umbrella extends ModelSchemaSilhouetteId { val value = "umbrella" }
    case object Living extends ModelSchemaSilhouetteId { val value = "living" }
    case object Beach_shirt extends ModelSchemaSilhouetteId { val value = "beach_shirt" }
    case object Lip_cosmetic extends ModelSchemaSilhouetteId { val value = "lip_cosmetic" }
    case object Hair extends ModelSchemaSilhouetteId { val value = "hair" }
    case object One_piece_underwear extends ModelSchemaSilhouetteId { val value = "one_piece_underwear" }
    case object Bicycle extends ModelSchemaSilhouetteId { val value = "bicycle" }
    case object Skirt extends ModelSchemaSilhouetteId { val value = "skirt" }
    case object Other_accessoires extends ModelSchemaSilhouetteId { val value = "other_accessoires" }
    case object Travel_equipment extends ModelSchemaSilhouetteId { val value = "travel_equipment" }
    case object Bikini_combination extends ModelSchemaSilhouetteId { val value = "bikini_combination" }
    case object Tent extends ModelSchemaSilhouetteId { val value = "tent" }
    case object Wallet extends ModelSchemaSilhouetteId { val value = "wallet" }
    case object Sandals extends ModelSchemaSilhouetteId { val value = "sandals" }
    case object Boots extends ModelSchemaSilhouetteId { val value = "boots" }
    case object Underpant extends ModelSchemaSilhouetteId { val value = "underpant" }
    case object Bathrobe extends ModelSchemaSilhouetteId { val value = "bathrobe" }
    case object Undershirt extends ModelSchemaSilhouetteId { val value = "undershirt" }
    case object Headphones extends ModelSchemaSilhouetteId { val value = "headphones" }
    case object Underwear_combination extends ModelSchemaSilhouetteId { val value = "underwear_combination" }
    case object Tights extends ModelSchemaSilhouetteId { val value = "tights" }
    case object Sneaker extends ModelSchemaSilhouetteId { val value = "sneaker" }
    case object Lounge extends ModelSchemaSilhouetteId { val value = "lounge" }
    case object First_shoe extends ModelSchemaSilhouetteId { val value = "first_shoe" }
    case object Cardigan extends ModelSchemaSilhouetteId { val value = "cardigan" }
    case object Beauty_equipment extends ModelSchemaSilhouetteId { val value = "beauty_equipment" }
    case object Boards extends ModelSchemaSilhouetteId { val value = "boards" }
    case object Backless_slipper extends ModelSchemaSilhouetteId { val value = "backless_slipper" }
    case object Beach_accessoires extends ModelSchemaSilhouetteId { val value = "beach_accessoires" }
    case object Nightdress extends ModelSchemaSilhouetteId { val value = "nightdress" }
    case object Nightwear_combination extends ModelSchemaSilhouetteId { val value = "nightwear_combination" }
    case object Shirt extends ModelSchemaSilhouetteId { val value = "shirt" }
    case object Bag extends ModelSchemaSilhouetteId { val value = "bag" }
    case object Headgear extends ModelSchemaSilhouetteId { val value = "headgear" }
    case object Suit_accessoires extends ModelSchemaSilhouetteId { val value = "suit_accessoires" }
    case object Low_shoe extends ModelSchemaSilhouetteId { val value = "low_shoe" }
    case object Protector extends ModelSchemaSilhouetteId { val value = "protector" }
    case object Ball extends ModelSchemaSilhouetteId { val value = "ball" }
    case object Fragrance extends ModelSchemaSilhouetteId { val value = "fragrance" }
    case object Toys extends ModelSchemaSilhouetteId { val value = "toys" }
    case object Corsage extends ModelSchemaSilhouetteId { val value = "corsage" }
    case object System extends ModelSchemaSilhouetteId { val value = "system" }
    case object Vest extends ModelSchemaSilhouetteId { val value = "vest" }
    case object Bustier extends ModelSchemaSilhouetteId { val value = "bustier" }
    case object Pumps extends ModelSchemaSilhouetteId { val value = "pumps" }
    case object Jacket extends ModelSchemaSilhouetteId { val value = "jacket" }
    case object Coat extends ModelSchemaSilhouetteId { val value = "coat" }
    case object Skates extends ModelSchemaSilhouetteId { val value = "skates" }
    case object Kitchen extends ModelSchemaSilhouetteId { val value = "kitchen" }
    case object Ballerina_shoe extends ModelSchemaSilhouetteId { val value = "ballerina_shoe" }
    case object Gloves extends ModelSchemaSilhouetteId { val value = "gloves" }
    case object One_piece_nightwear extends ModelSchemaSilhouetteId { val value = "one_piece_nightwear" }
    case object One_piece_suit extends ModelSchemaSilhouetteId { val value = "one_piece_suit" }
    case object Glasses extends ModelSchemaSilhouetteId { val value = "glasses" }
    case object Bikini_top extends ModelSchemaSilhouetteId { val value = "bikini_top" }
    case object Nail extends ModelSchemaSilhouetteId { val value = "nail" }
    case object Trouser extends ModelSchemaSilhouetteId { val value = "trouser" }
    case object Peeling extends ModelSchemaSilhouetteId { val value = "peeling" }
    case object Bedroom extends ModelSchemaSilhouetteId { val value = "bedroom" }
    case object Scarf extends ModelSchemaSilhouetteId { val value = "scarf" }
    case object Other_equipment extends ModelSchemaSilhouetteId { val value = "other_equipment" }
    case object Beach_trouser extends ModelSchemaSilhouetteId { val value = "beach_trouser" }
    case object Shave extends ModelSchemaSilhouetteId { val value = "shave" }
    case object Ankle_boots extends ModelSchemaSilhouetteId { val value = "ankle_boots" }
    case object Bathroom extends ModelSchemaSilhouetteId { val value = "bathroom" }
    case object Face_cosmetic extends ModelSchemaSilhouetteId { val value = "face_cosmetic" }
    case object Belt extends ModelSchemaSilhouetteId { val value = "belt" }
    case object Cleansing extends ModelSchemaSilhouetteId { val value = "cleansing" }
    case object Voucher extends ModelSchemaSilhouetteId { val value = "voucher" }
    case object Bicycle_equipment extends ModelSchemaSilhouetteId { val value = "bicycle_equipment" }
    case object Night_shirt extends ModelSchemaSilhouetteId { val value = "night_shirt" }
    case object Combination_clothing extends ModelSchemaSilhouetteId { val value = "combination_clothing" }
    case object Racket extends ModelSchemaSilhouetteId { val value = "racket" }
    case object Ski extends ModelSchemaSilhouetteId { val value = "ski" }
    case object Shoe_accessoires extends ModelSchemaSilhouetteId { val value = "shoe_accessoires" }
    case object Jewellery extends ModelSchemaSilhouetteId { val value = "jewellery" }
    case object Watch extends ModelSchemaSilhouetteId { val value = "watch" }
    case object Sun extends ModelSchemaSilhouetteId { val value = "sun" }
    case object Pullover extends ModelSchemaSilhouetteId { val value = "pullover" }
    case object Stocking extends ModelSchemaSilhouetteId { val value = "stocking" }
    case object Case extends ModelSchemaSilhouetteId { val value = "case" }
    case object Etui extends ModelSchemaSilhouetteId { val value = "etui" }
    implicit def stringToModelSchemaSilhouetteId(in: String): ModelSchemaSilhouetteId = in match {
        case "backpack" => Backpack
        case "keychain" => Keychain
        case "bra" => Bra
        case "skincare" => Skincare
        case "one_piece_beachwear" => One_piece_beachwear
        case "dress" => Dress
        case "sleeping_bag" => Sleeping_bag
        case "eye_cosmetic" => Eye_cosmetic
        case "fitness" => Fitness
        case "t_shirt_top" => T_shirt_top
        case "night_trouser" => Night_trouser
        case "umbrella" => Umbrella
        case "living" => Living
        case "beach_shirt" => Beach_shirt
        case "lip_cosmetic" => Lip_cosmetic
        case "hair" => Hair
        case "one_piece_underwear" => One_piece_underwear
        case "bicycle" => Bicycle
        case "skirt" => Skirt
        case "other_accessoires" => Other_accessoires
        case "travel_equipment" => Travel_equipment
        case "bikini_combination" => Bikini_combination
        case "tent" => Tent
        case "wallet" => Wallet
        case "sandals" => Sandals
        case "boots" => Boots
        case "underpant" => Underpant
        case "bathrobe" => Bathrobe
        case "undershirt" => Undershirt
        case "headphones" => Headphones
        case "underwear_combination" => Underwear_combination
        case "tights" => Tights
        case "sneaker" => Sneaker
        case "lounge" => Lounge
        case "first_shoe" => First_shoe
        case "cardigan" => Cardigan
        case "beauty_equipment" => Beauty_equipment
        case "boards" => Boards
        case "backless_slipper" => Backless_slipper
        case "beach_accessoires" => Beach_accessoires
        case "nightdress" => Nightdress
        case "nightwear_combination" => Nightwear_combination
        case "shirt" => Shirt
        case "bag" => Bag
        case "headgear" => Headgear
        case "suit_accessoires" => Suit_accessoires
        case "low_shoe" => Low_shoe
        case "protector" => Protector
        case "ball" => Ball
        case "fragrance" => Fragrance
        case "toys" => Toys
        case "corsage" => Corsage
        case "system" => System
        case "vest" => Vest
        case "bustier" => Bustier
        case "pumps" => Pumps
        case "jacket" => Jacket
        case "coat" => Coat
        case "skates" => Skates
        case "kitchen" => Kitchen
        case "ballerina_shoe" => Ballerina_shoe
        case "gloves" => Gloves
        case "one_piece_nightwear" => One_piece_nightwear
        case "one_piece_suit" => One_piece_suit
        case "glasses" => Glasses
        case "bikini_top" => Bikini_top
        case "nail" => Nail
        case "trouser" => Trouser
        case "peeling" => Peeling
        case "bedroom" => Bedroom
        case "scarf" => Scarf
        case "other_equipment" => Other_equipment
        case "beach_trouser" => Beach_trouser
        case "shave" => Shave
        case "ankle_boots" => Ankle_boots
        case "bathroom" => Bathroom
        case "face_cosmetic" => Face_cosmetic
        case "belt" => Belt
        case "cleansing" => Cleansing
        case "voucher" => Voucher
        case "bicycle_equipment" => Bicycle_equipment
        case "night_shirt" => Night_shirt
        case "combination_clothing" => Combination_clothing
        case "racket" => Racket
        case "ski" => Ski
        case "shoe_accessoires" => Shoe_accessoires
        case "jewellery" => Jewellery
        case "watch" => Watch
        case "sun" => Sun
        case "pullover" => Pullover
        case "stocking" => Stocking
        case "case" => Case
        case "etui" => Etui
    }


}
