package error_in_array

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

    case class ModelSchemaAgeGroupsArr(value: String) extends AnyVal
    val Baby = ModelSchemaAgeGroupsArr("baby")
    val Kid = ModelSchemaAgeGroupsArr("kid")
    val Teen = ModelSchemaAgeGroupsArr("teen")
    val Adult = ModelSchemaAgeGroupsArr("adult")
    implicit def stringToModelSchemaAgeGroupsArr(in: String): ModelSchemaAgeGroupsArr = in match {
        case "baby" => Baby
        case "kid" => Kid
        case "teen" => Teen
        case "adult" => Adult
    }
    case class ModelSchemaSilhouetteId(value: String) extends AnyVal
    val Backpack = ModelSchemaSilhouetteId("backpack")
    val Keychain = ModelSchemaSilhouetteId("keychain")
    val Bra = ModelSchemaSilhouetteId("bra")
    val Skincare = ModelSchemaSilhouetteId("skincare")
    val One_piece_beachwear = ModelSchemaSilhouetteId("one_piece_beachwear")
    val Dress = ModelSchemaSilhouetteId("dress")
    val Sleeping_bag = ModelSchemaSilhouetteId("sleeping_bag")
    val Eye_cosmetic = ModelSchemaSilhouetteId("eye_cosmetic")
    val Fitness = ModelSchemaSilhouetteId("fitness")
    val T_shirt_top = ModelSchemaSilhouetteId("t_shirt_top")
    val Night_trouser = ModelSchemaSilhouetteId("night_trouser")
    val Umbrella = ModelSchemaSilhouetteId("umbrella")
    val Living = ModelSchemaSilhouetteId("living")
    val Beach_shirt = ModelSchemaSilhouetteId("beach_shirt")
    val Lip_cosmetic = ModelSchemaSilhouetteId("lip_cosmetic")
    val Hair = ModelSchemaSilhouetteId("hair")
    val One_piece_underwear = ModelSchemaSilhouetteId("one_piece_underwear")
    val Bicycle = ModelSchemaSilhouetteId("bicycle")
    val Skirt = ModelSchemaSilhouetteId("skirt")
    val Other_accessoires = ModelSchemaSilhouetteId("other_accessoires")
    val Travel_equipment = ModelSchemaSilhouetteId("travel_equipment")
    val Bikini_combination = ModelSchemaSilhouetteId("bikini_combination")
    val Tent = ModelSchemaSilhouetteId("tent")
    val Wallet = ModelSchemaSilhouetteId("wallet")
    val Sandals = ModelSchemaSilhouetteId("sandals")
    val Boots = ModelSchemaSilhouetteId("boots")
    val Underpant = ModelSchemaSilhouetteId("underpant")
    val Bathrobe = ModelSchemaSilhouetteId("bathrobe")
    val Undershirt = ModelSchemaSilhouetteId("undershirt")
    val Headphones = ModelSchemaSilhouetteId("headphones")
    val Underwear_combination = ModelSchemaSilhouetteId("underwear_combination")
    val Tights = ModelSchemaSilhouetteId("tights")
    val Sneaker = ModelSchemaSilhouetteId("sneaker")
    val Lounge = ModelSchemaSilhouetteId("lounge")
    val First_shoe = ModelSchemaSilhouetteId("first_shoe")
    val Cardigan = ModelSchemaSilhouetteId("cardigan")
    val Beauty_equipment = ModelSchemaSilhouetteId("beauty_equipment")
    val Boards = ModelSchemaSilhouetteId("boards")
    val Backless_slipper = ModelSchemaSilhouetteId("backless_slipper")
    val Beach_accessoires = ModelSchemaSilhouetteId("beach_accessoires")
    val Nightdress = ModelSchemaSilhouetteId("nightdress")
    val Nightwear_combination = ModelSchemaSilhouetteId("nightwear_combination")
    val Shirt = ModelSchemaSilhouetteId("shirt")
    val Bag = ModelSchemaSilhouetteId("bag")
    val Headgear = ModelSchemaSilhouetteId("headgear")
    val Suit_accessoires = ModelSchemaSilhouetteId("suit_accessoires")
    val Low_shoe = ModelSchemaSilhouetteId("low_shoe")
    val Protector = ModelSchemaSilhouetteId("protector")
    val Ball = ModelSchemaSilhouetteId("ball")
    val Fragrance = ModelSchemaSilhouetteId("fragrance")
    val Toys = ModelSchemaSilhouetteId("toys")
    val Corsage = ModelSchemaSilhouetteId("corsage")
    val System = ModelSchemaSilhouetteId("system")
    val Vest = ModelSchemaSilhouetteId("vest")
    val Bustier = ModelSchemaSilhouetteId("bustier")
    val Pumps = ModelSchemaSilhouetteId("pumps")
    val Jacket = ModelSchemaSilhouetteId("jacket")
    val Coat = ModelSchemaSilhouetteId("coat")
    val Skates = ModelSchemaSilhouetteId("skates")
    val Kitchen = ModelSchemaSilhouetteId("kitchen")
    val Ballerina_shoe = ModelSchemaSilhouetteId("ballerina_shoe")
    val Gloves = ModelSchemaSilhouetteId("gloves")
    val One_piece_nightwear = ModelSchemaSilhouetteId("one_piece_nightwear")
    val One_piece_suit = ModelSchemaSilhouetteId("one_piece_suit")
    val Glasses = ModelSchemaSilhouetteId("glasses")
    val Bikini_top = ModelSchemaSilhouetteId("bikini_top")
    val Nail = ModelSchemaSilhouetteId("nail")
    val Trouser = ModelSchemaSilhouetteId("trouser")
    val Peeling = ModelSchemaSilhouetteId("peeling")
    val Bedroom = ModelSchemaSilhouetteId("bedroom")
    val Scarf = ModelSchemaSilhouetteId("scarf")
    val Other_equipment = ModelSchemaSilhouetteId("other_equipment")
    val Beach_trouser = ModelSchemaSilhouetteId("beach_trouser")
    val Shave = ModelSchemaSilhouetteId("shave")
    val Ankle_boots = ModelSchemaSilhouetteId("ankle_boots")
    val Bathroom = ModelSchemaSilhouetteId("bathroom")
    val Face_cosmetic = ModelSchemaSilhouetteId("face_cosmetic")
    val Belt = ModelSchemaSilhouetteId("belt")
    val Cleansing = ModelSchemaSilhouetteId("cleansing")
    val Voucher = ModelSchemaSilhouetteId("voucher")
    val Bicycle_equipment = ModelSchemaSilhouetteId("bicycle_equipment")
    val Night_shirt = ModelSchemaSilhouetteId("night_shirt")
    val Combination_clothing = ModelSchemaSilhouetteId("combination_clothing")
    val Racket = ModelSchemaSilhouetteId("racket")
    val Ski = ModelSchemaSilhouetteId("ski")
    val Shoe_accessoires = ModelSchemaSilhouetteId("shoe_accessoires")
    val Jewellery = ModelSchemaSilhouetteId("jewellery")
    val Watch = ModelSchemaSilhouetteId("watch")
    val Sun = ModelSchemaSilhouetteId("sun")
    val Pullover = ModelSchemaSilhouetteId("pullover")
    val Stocking = ModelSchemaSilhouetteId("stocking")
    val Case = ModelSchemaSilhouetteId("case")
    val Etui = ModelSchemaSilhouetteId("etui")
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
