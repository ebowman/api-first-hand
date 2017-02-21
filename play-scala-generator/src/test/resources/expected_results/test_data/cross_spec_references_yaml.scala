package cross_spec_references.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt

object Generators extends JsValueGenerators {
    

    
    def createModelSchemaSilhouetteIdGenerator = _generate(ModelSchemaSilhouetteIdGenerator)
    def createModelSchemaAgeGroupsSeqEnumGenerator = _generate(ModelSchemaAgeGroupsSeqEnumGenerator)
    

    
    def ModelSchemaSilhouetteIdGenerator = { import ModelSchemaSilhouetteId._ ; Gen.oneOf(Seq(Kitchen, Bikini_top, Toys, Nightwear_combination, Bra, One_piece_underwear, Ball, Cleansing, Skincare, Jewellery, Headgear, Bustier, Beach_trouser, Bedroom, Lounge, Nail, Undershirt, Combination_clothing, Gloves, Fragrance, Other_equipment, Fitness, Bathroom, One_piece_nightwear, Sleeping_bag, Coat, Case, Sandals, Ankle_boots, Stocking, Shirt, Backpack, Face_cosmetic, Travel_equipment, Hair, Sneaker, Beauty_equipment, Bikini_combination, Backless_slipper, Beach_accessoires, Scarf, First_shoe, Voucher, Wallet, Peeling, Glasses, Boards, Sun, Shave, Low_shoe, Underwear_combination, Nightdress, Suit_accessoires, Watch, Headphones, Skates, Boots, Jacket, Etui, Night_shirt, Other_accessoires, Vest, Bag, System, Racket, Trouser, Lip_cosmetic, Keychain, Belt, Ballerina_shoe, One_piece_suit, Night_trouser, Skirt, Tights, Beach_shirt, Dress, Bicycle, Protector, Eye_cosmetic, Bathrobe, Bicycle_equipment, Pullover, One_piece_beachwear, Underpant, Living, Cardigan, Corsage, Shoe_accessoires, Umbrella, Pumps, Tent, T_shirt_top, Ski)) }
    def ModelSchemaAgeGroupsSeqEnumGenerator = { import ModelSchemaAgeGroupsSeqEnum._ ; Gen.oneOf(Seq(Baby, Kid, Teen, Adult)) }
    

    def createModelSchemaRootMetaOptionMetaGenerator = _generate(ModelSchemaRootMetaOptionMetaGenerator)
    def createPetCategoryOptionCategoryGenerator = _generate(PetCategoryOptionCategoryGenerator)
    def createModelSchemaRootGenerator = _generate(ModelSchemaRootGenerator)
    def createModelSchemaRootLinksOptionLinksGenerator = _generate(ModelSchemaRootLinksOptionLinksGenerator)
    def createPetGenerator = _generate(PetGenerator)
    def createModelSchemaRootDataOptionModelSchemaGenerator = _generate(ModelSchemaRootDataOptionModelSchemaGenerator)


    def ModelSchemaRootMetaOptionMetaGenerator = for {
        copyright <- Gen.option(arbitrary[String])
    } yield ModelSchemaRootMetaOptionMeta(copyright)
    def PetCategoryOptionCategoryGenerator = for {
        id <- Gen.option(arbitrary[Long])
        name <- Gen.option(arbitrary[String])
    } yield PetCategoryOptionCategory(id, name)
    def ModelSchemaRootGenerator = for {
        data <- Gen.option(ModelSchemaRootDataOptionModelSchemaGenerator)
        meta <- Gen.option(ModelSchemaRootMetaOptionMetaGenerator)
        links <- Gen.option(ModelSchemaRootLinksOptionLinksGenerator)
    } yield ModelSchemaRoot(data, meta, links)
    def ModelSchemaRootLinksOptionLinksGenerator = for {
        self <- Gen.option(arbitrary[String])
        related <- Gen.option(arbitrary[String])
    } yield ModelSchemaRootLinksOptionLinks(self, related)
    def PetGenerator = for {
        name <- arbitrary[String]
        photoUrls <- Gen.containerOf[List,String](arbitrary[String])
        id <- Gen.option(arbitrary[Long])
        status <- Gen.option(arbitrary[String])
        tags <- Gen.option(Gen.containerOf[List,PetCategoryOptionCategory](PetCategoryOptionCategoryGenerator))
        category <- Gen.option(PetCategoryOptionCategoryGenerator)
    } yield Pet(name, photoUrls, id, status, tags, category)
    def ModelSchemaRootDataOptionModelSchemaGenerator = for {
        name <- arbitrary[String]
        description <- Gen.option(arbitrary[String])
        sizeRegister <- arbitrary[String]
        brand <- arbitrary[String]
        partnerArticleModelId <- arbitrary[BigInt]
        keywords <- Gen.option(arbitrary[String])
        lengthRegister <- Gen.option(arbitrary[String])
        specialDescriptions <- Gen.option(Gen.containerOf[List,String](arbitrary[String]))
        articleModelAttributes <- Gen.option(Gen.containerOf[List,String](arbitrary[String]))
        silhouetteId <- ModelSchemaSilhouetteIdGenerator
        ageGroups <- Gen.containerOf[List,ModelSchemaAgeGroupsSeqEnum](ModelSchemaAgeGroupsSeqEnumGenerator)
    } yield ModelSchemaRootDataOptionModelSchema(name, description, sizeRegister, brand, partnerArticleModelId, keywords, lengthRegister, specialDescriptions, articleModelAttributes, silhouetteId, ageGroups)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}