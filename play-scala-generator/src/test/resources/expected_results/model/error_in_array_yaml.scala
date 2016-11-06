package error_in_array


    import scala.math.BigInt


//noinspection ScalaStyle
package yaml {


    case class ModelSchemaRoot(data: ModelSchemaRootData, meta: ModelSchemaRootMeta, links: ModelSchemaRootLinks) 
    case class Errors(errors: ErrorsErrors) 
    case class ErrorSourceNameClash(pointer: MetaCopyright, parameter: MetaCopyright) 
    case class Meta(copyright: MetaCopyright) 
    case class ModelSchema(name: String, sizeRegister: String, brand: String, partnerArticleModelId: BigInt, description: MetaCopyright, ageGroups: ModelSchemaAgeGroups, keywords: ModelSchemaKeywords, lengthRegister: ModelSchemaLengthRegister, silhouetteId: ModelSchemaSilhouetteId, specialDescriptions: ModelSchemaSpecialDescriptions, articleModelAttributes: ModelSchemaSpecialDescriptions) 
    case class Error(source: ErrorSource, code: MetaCopyright, status: MetaCopyright, detail: MetaCopyright, title: MetaCopyright) 
    case class Links(self: MetaCopyright, related: MetaCopyright) 

    case class ModelSchemaSilhouetteId(override val value: String) extends AnyVal with de.zalando.play.controllers.StringAnyVal
    case class ModelSchemaAgeGroupsArrResult(override val value: String) extends AnyVal with de.zalando.play.controllers.StringAnyVal

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val LinksReads: Reads[Links] = (
            (JsPath \ "self").readNullable[String] and (JsPath \ "related").readNullable[String]
        )(Links.apply _)
        implicit val MetaReads: Reads[Meta] = (
            (JsPath \ "copyright").readNullable[String]
        ).map(Meta.apply )
        implicit val ModelSchemaReads: Reads[ModelSchema] = (
            (JsPath \ "name").read[String] and (JsPath \ "sizeRegister").read[String] and (JsPath \ "brand").read[String] and (JsPath \ "partnerArticleModelId").read[BigInt] and (JsPath \ "description").readNullable[String] and (JsPath \ "ageGroups").read[ModelSchemaAgeGroups] and (JsPath \ "keywords").readNullable[String] and (JsPath \ "lengthRegister").readNullable[String] and (JsPath \ "silhouetteId").read[ModelSchemaSilhouetteId] and (JsPath \ "specialDescriptions").readNullable[ModelSchemaSpecialDescriptionsOpt] and (JsPath \ "articleModelAttributes").readNullable[ModelSchemaSpecialDescriptionsOpt]
        )(ModelSchema.apply _)
        implicit val ModelSchemaRootReads: Reads[ModelSchemaRoot] = (
            (JsPath \ "data").readNullable[ModelSchema] and (JsPath \ "meta").readNullable[Meta] and (JsPath \ "links").readNullable[Links]
        )(ModelSchemaRoot.apply _)
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val LinksWrites: Writes[Links] = new Writes[Links] {
        def writes(ss: Links) =
          Json.obj(
            "self" -> ss.self, 
            "related" -> ss.related
          )
        }
    implicit val MetaWrites: Writes[Meta] = new Writes[Meta] {
        def writes(ss: Meta) =
          Json.obj(
            "copyright" -> ss.copyright
          )
        }
    implicit val ModelSchemaWrites: Writes[ModelSchema] = new Writes[ModelSchema] {
        def writes(ss: ModelSchema) =
          Json.obj(
            "name" -> ss.name, 
            "sizeRegister" -> ss.sizeRegister, 
            "brand" -> ss.brand, 
            "partnerArticleModelId" -> ss.partnerArticleModelId, 
            "description" -> ss.description, 
            "ageGroups" -> ss.ageGroups, 
            "keywords" -> ss.keywords, 
            "lengthRegister" -> ss.lengthRegister, 
            "silhouetteId" -> ss.silhouetteId, 
            "specialDescriptions" -> ss.specialDescriptions, 
            "articleModelAttributes" -> ss.articleModelAttributes
          )
        }
    implicit val ModelSchemaRootWrites: Writes[ModelSchemaRoot] = new Writes[ModelSchemaRoot] {
        def writes(ss: ModelSchemaRoot) =
          Json.obj(
            "data" -> ss.data, 
            "meta" -> ss.meta, 
            "links" -> ss.links
          )
        }
    }
}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type ModelSchemaSpecialDescriptionsOpt = Seq[String]
    type MetaCopyright = Option[String]
    type ModelSchemaKeywords = Option[String]
    type ModelSchemaSpecialDescriptions = Option[ModelSchemaSpecialDescriptionsOpt]
    type ErrorsErrorsOpt = Seq[Error]
    type ModelSchemaRootData = Option[ModelSchema]
    type ErrorSource = Option[ErrorSourceNameClash]
    type ModelSchemaRootLinks = Option[Links]
    type ModelSchemaLengthRegister = Option[String]
    type ErrorsErrors = Option[ErrorsErrorsOpt]
    type ModelSchemaAgeGroups = Seq[ModelSchemaAgeGroupsArrResult]
    type ModelSchemaRootMeta = Option[Meta]

    object ModelSchemaSilhouetteId {
        
        val Backpack = new ModelSchemaSilhouetteId("backpack")
        val Keychain = new ModelSchemaSilhouetteId("keychain")
        val Bra = new ModelSchemaSilhouetteId("bra")
        val Skincare = new ModelSchemaSilhouetteId("skincare")
        val One_piece_beachwear = new ModelSchemaSilhouetteId("one_piece_beachwear")
        val Dress = new ModelSchemaSilhouetteId("dress")
        val Sleeping_bag = new ModelSchemaSilhouetteId("sleeping_bag")
        val Eye_cosmetic = new ModelSchemaSilhouetteId("eye_cosmetic")
        val Fitness = new ModelSchemaSilhouetteId("fitness")
        val T_shirt_top = new ModelSchemaSilhouetteId("t_shirt_top")
        val Night_trouser = new ModelSchemaSilhouetteId("night_trouser")
        val Umbrella = new ModelSchemaSilhouetteId("umbrella")
        val Living = new ModelSchemaSilhouetteId("living")
        val Beach_shirt = new ModelSchemaSilhouetteId("beach_shirt")
        val Lip_cosmetic = new ModelSchemaSilhouetteId("lip_cosmetic")
        val Hair = new ModelSchemaSilhouetteId("hair")
        val One_piece_underwear = new ModelSchemaSilhouetteId("one_piece_underwear")
        val Bicycle = new ModelSchemaSilhouetteId("bicycle")
        val Skirt = new ModelSchemaSilhouetteId("skirt")
        val Other_accessoires = new ModelSchemaSilhouetteId("other_accessoires")
        val Travel_equipment = new ModelSchemaSilhouetteId("travel_equipment")
        val Bikini_combination = new ModelSchemaSilhouetteId("bikini_combination")
        val Tent = new ModelSchemaSilhouetteId("tent")
        val Wallet = new ModelSchemaSilhouetteId("wallet")
        val Sandals = new ModelSchemaSilhouetteId("sandals")
        val Boots = new ModelSchemaSilhouetteId("boots")
        val Underpant = new ModelSchemaSilhouetteId("underpant")
        val Bathrobe = new ModelSchemaSilhouetteId("bathrobe")
        val Undershirt = new ModelSchemaSilhouetteId("undershirt")
        val Headphones = new ModelSchemaSilhouetteId("headphones")
        val Underwear_combination = new ModelSchemaSilhouetteId("underwear_combination")
        val Tights = new ModelSchemaSilhouetteId("tights")
        val Sneaker = new ModelSchemaSilhouetteId("sneaker")
        val Lounge = new ModelSchemaSilhouetteId("lounge")
        val First_shoe = new ModelSchemaSilhouetteId("first_shoe")
        val Cardigan = new ModelSchemaSilhouetteId("cardigan")
        val Beauty_equipment = new ModelSchemaSilhouetteId("beauty_equipment")
        val Boards = new ModelSchemaSilhouetteId("boards")
        val Backless_slipper = new ModelSchemaSilhouetteId("backless_slipper")
        val Beach_accessoires = new ModelSchemaSilhouetteId("beach_accessoires")
        val Nightdress = new ModelSchemaSilhouetteId("nightdress")
        val Nightwear_combination = new ModelSchemaSilhouetteId("nightwear_combination")
        val Shirt = new ModelSchemaSilhouetteId("shirt")
        val Bag = new ModelSchemaSilhouetteId("bag")
        val Headgear = new ModelSchemaSilhouetteId("headgear")
        val Suit_accessoires = new ModelSchemaSilhouetteId("suit_accessoires")
        val Low_shoe = new ModelSchemaSilhouetteId("low_shoe")
        val Protector = new ModelSchemaSilhouetteId("protector")
        val Ball = new ModelSchemaSilhouetteId("ball")
        val Fragrance = new ModelSchemaSilhouetteId("fragrance")
        val Toys = new ModelSchemaSilhouetteId("toys")
        val Corsage = new ModelSchemaSilhouetteId("corsage")
        val System = new ModelSchemaSilhouetteId("system")
        val Vest = new ModelSchemaSilhouetteId("vest")
        val Bustier = new ModelSchemaSilhouetteId("bustier")
        val Pumps = new ModelSchemaSilhouetteId("pumps")
        val Jacket = new ModelSchemaSilhouetteId("jacket")
        val Coat = new ModelSchemaSilhouetteId("coat")
        val Skates = new ModelSchemaSilhouetteId("skates")
        val Kitchen = new ModelSchemaSilhouetteId("kitchen")
        val Ballerina_shoe = new ModelSchemaSilhouetteId("ballerina_shoe")
        val Gloves = new ModelSchemaSilhouetteId("gloves")
        val One_piece_nightwear = new ModelSchemaSilhouetteId("one_piece_nightwear")
        val One_piece_suit = new ModelSchemaSilhouetteId("one_piece_suit")
        val Glasses = new ModelSchemaSilhouetteId("glasses")
        val Bikini_top = new ModelSchemaSilhouetteId("bikini_top")
        val Nail = new ModelSchemaSilhouetteId("nail")
        val Trouser = new ModelSchemaSilhouetteId("trouser")
        val Peeling = new ModelSchemaSilhouetteId("peeling")
        val Bedroom = new ModelSchemaSilhouetteId("bedroom")
        val Scarf = new ModelSchemaSilhouetteId("scarf")
        val Other_equipment = new ModelSchemaSilhouetteId("other_equipment")
        val Beach_trouser = new ModelSchemaSilhouetteId("beach_trouser")
        val Shave = new ModelSchemaSilhouetteId("shave")
        val Ankle_boots = new ModelSchemaSilhouetteId("ankle_boots")
        val Bathroom = new ModelSchemaSilhouetteId("bathroom")
        val Face_cosmetic = new ModelSchemaSilhouetteId("face_cosmetic")
        val Belt = new ModelSchemaSilhouetteId("belt")
        val Cleansing = new ModelSchemaSilhouetteId("cleansing")
        val Voucher = new ModelSchemaSilhouetteId("voucher")
        val Bicycle_equipment = new ModelSchemaSilhouetteId("bicycle_equipment")
        val Night_shirt = new ModelSchemaSilhouetteId("night_shirt")
        val Combination_clothing = new ModelSchemaSilhouetteId("combination_clothing")
        val Racket = new ModelSchemaSilhouetteId("racket")
        val Ski = new ModelSchemaSilhouetteId("ski")
        val Shoe_accessoires = new ModelSchemaSilhouetteId("shoe_accessoires")
        val Jewellery = new ModelSchemaSilhouetteId("jewellery")
        val Watch = new ModelSchemaSilhouetteId("watch")
        val Sun = new ModelSchemaSilhouetteId("sun")
        val Pullover = new ModelSchemaSilhouetteId("pullover")
        val Stocking = new ModelSchemaSilhouetteId("stocking")
        val Case = new ModelSchemaSilhouetteId("case")
        val Etui = new ModelSchemaSilhouetteId("etui")

        implicit def stringToModelSchemaSilhouetteId: String => ModelSchemaSilhouetteId = {
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
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }
    object ModelSchemaAgeGroupsArrResult {
        
        val Baby = new ModelSchemaAgeGroupsArrResult("baby")
        val Kid = new ModelSchemaAgeGroupsArrResult("kid")
        val Teen = new ModelSchemaAgeGroupsArrResult("teen")
        val Adult = new ModelSchemaAgeGroupsArrResult("adult")

        implicit def stringToModelSchemaAgeGroupsArrResult: String => ModelSchemaAgeGroupsArrResult = {
            case "baby" => Baby
            case "kid" => Kid
            case "teen" => Teen
            case "adult" => Adult
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }


}