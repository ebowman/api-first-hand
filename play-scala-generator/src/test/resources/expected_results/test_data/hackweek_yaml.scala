package hackweek.yaml

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
    

    def createModelSchemaRootGenerator = _generate(ModelSchemaRootGenerator)
    def createErrorsGenerator = _generate(ErrorsGenerator)
    def createErrorSourceGenerator = _generate(ErrorSourceGenerator)
    def createMetaGenerator = _generate(MetaGenerator)
    def createModelSchemaGenerator = _generate(ModelSchemaGenerator)
    def createErrorGenerator = _generate(ErrorGenerator)
    def createLinksGenerator = _generate(LinksGenerator)


    def ModelSchemaRootGenerator = for {
        data <- Gen.option(ModelSchemaGenerator)
        meta <- Gen.option(MetaGenerator)
        links <- Gen.option(LinksGenerator)
    } yield ModelSchemaRoot(data, meta, links)
    def ErrorsGenerator = for {
        errors <- Gen.option(Gen.containerOf[List,Error](ErrorGenerator))
    } yield Errors(errors)
    def ErrorSourceGenerator = for {
        pointer <- Gen.option(arbitrary[String])
        parameter <- Gen.option(arbitrary[String])
    } yield ErrorSource(pointer, parameter)
    def MetaGenerator = for {
        copyright <- Gen.option(arbitrary[String])
    } yield Meta(copyright)
    def ModelSchemaGenerator = for {
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
    } yield ModelSchema(name, description, sizeRegister, brand, partnerArticleModelId, keywords, lengthRegister, specialDescriptions, articleModelAttributes, silhouetteId, ageGroups)
    def ErrorGenerator = for {
        code <- Gen.option(arbitrary[String])
        status <- Gen.option(arbitrary[String])
        detail <- Gen.option(arbitrary[String])
        title <- Gen.option(arbitrary[String])
        source <- Gen.option(ErrorSourceGenerator)
    } yield Error(code, status, detail, title, source)
    def LinksGenerator = for {
        self <- Gen.option(arbitrary[String])
        related <- Gen.option(arbitrary[String])
    } yield Links(self, related)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}