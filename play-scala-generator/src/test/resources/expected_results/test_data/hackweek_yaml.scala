package hackweek.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper
import scala.math.BigInt

object Generators extends JsValueGenerators {
    

    
    def createModelSchemaSpecialDescriptionsOptGenerator = _generate(ModelSchemaSpecialDescriptionsOptGenerator)
    def createMetaCopyrightGenerator = _generate(MetaCopyrightGenerator)
    def createModelSchemaKeywordsGenerator = _generate(ModelSchemaKeywordsGenerator)
    def createModelSchemaSpecialDescriptionsGenerator = _generate(ModelSchemaSpecialDescriptionsGenerator)
    def createErrorsErrorsOptGenerator = _generate(ErrorsErrorsOptGenerator)
    def createModelSchemaRootDataGenerator = _generate(ModelSchemaRootDataGenerator)
    def createErrorSourceGenerator = _generate(ErrorSourceGenerator)
    def createModelSchemaArticleModelAttributesOptGenerator = _generate(ModelSchemaArticleModelAttributesOptGenerator)
    def createModelSchemaRootLinksGenerator = _generate(ModelSchemaRootLinksGenerator)
    def createModelSchemaAgeGroupsArrGenerator = _generate(ModelSchemaAgeGroupsArrGenerator)
    def createModelSchemaSilhouetteIdGenerator = _generate(ModelSchemaSilhouetteIdGenerator)
    def createModelSchemaArticleModelAttributesGenerator = _generate(ModelSchemaArticleModelAttributesGenerator)
    def createModelSchemaLengthRegisterGenerator = _generate(ModelSchemaLengthRegisterGenerator)
    def createErrorsErrorsGenerator = _generate(ErrorsErrorsGenerator)
    def createModelSchemaAgeGroupsGenerator = _generate(ModelSchemaAgeGroupsGenerator)
    def createModelSchemaRootMetaGenerator = _generate(ModelSchemaRootMetaGenerator)
    

    
    def ModelSchemaSpecialDescriptionsOptGenerator = Gen.containerOf[List,String](arbitrary[String])
    def MetaCopyrightGenerator = Gen.option(arbitrary[String])
    def ModelSchemaKeywordsGenerator = Gen.option(arbitrary[String])
    def ModelSchemaSpecialDescriptionsGenerator = Gen.option(ModelSchemaSpecialDescriptionsOptGenerator)
    def ErrorsErrorsOptGenerator = _genList(ErrorGenerator, "csv")
    def ModelSchemaRootDataGenerator = Gen.option(ModelSchemaGenerator)
    def ErrorSourceGenerator = Gen.option(ErrorSourceNameClashGenerator)
    def ModelSchemaArticleModelAttributesOptGenerator = _genList(arbitrary[String], "csv")
    def ModelSchemaRootLinksGenerator = Gen.option(LinksGenerator)
    def ModelSchemaAgeGroupsArrGenerator = Gen.oneOf(Seq(Baby, Kid, Teen, Adult))
    def ModelSchemaSilhouetteIdGenerator = Gen.oneOf(Seq(Kitchen, Bikini_top, Toys, Nightwear_combination, Bra, One_piece_underwear, Ball, Cleansing, Skincare, Jewellery, Headgear, Bustier, Beach_trouser, Bedroom, Lounge, Nail, Undershirt, Combination_clothing, Gloves, Fragrance, Other_equipment, Fitness, Bathroom, One_piece_nightwear, Sleeping_bag, Coat, Case, Sandals, Ankle_boots, Stocking, Shirt, Backpack, Face_cosmetic, Travel_equipment, Hair, Sneaker, Beauty_equipment, Bikini_combination, Backless_slipper, Beach_accessoires, Scarf, First_shoe, Voucher, Wallet, Peeling, Glasses, Boards, Sun, Shave, Low_shoe, Underwear_combination, Nightdress, Suit_accessoires, Watch, Headphones, Skates, Boots, Jacket, Etui, Night_shirt, Other_accessoires, Vest, Bag, System, Racket, Trouser, Lip_cosmetic, Keychain, Belt, Ballerina_shoe, One_piece_suit, Night_trouser, Skirt, Tights, Beach_shirt, Dress, Bicycle, Protector, Eye_cosmetic, Bathrobe, Bicycle_equipment, Pullover, One_piece_beachwear, Underpant, Living, Cardigan, Corsage, Shoe_accessoires, Umbrella, Pumps, Tent, T_shirt_top, Ski))
    def ModelSchemaArticleModelAttributesGenerator = Gen.option(ModelSchemaArticleModelAttributesOptGenerator)
    def ModelSchemaLengthRegisterGenerator = Gen.option(arbitrary[String])
    def ErrorsErrorsGenerator = Gen.option(ErrorsErrorsOptGenerator)
    def ModelSchemaAgeGroupsGenerator = _genList(ModelSchemaAgeGroupsArrGenerator, "csv")
    def ModelSchemaRootMetaGenerator = Gen.option(MetaGenerator)
    

    def createModelSchemaRootGenerator = _generate(ModelSchemaRootGenerator)
    def createErrorsGenerator = _generate(ErrorsGenerator)
    def createErrorSourceNameClashGenerator = _generate(ErrorSourceNameClashGenerator)
    def createMetaGenerator = _generate(MetaGenerator)
    def createModelSchemaGenerator = _generate(ModelSchemaGenerator)
    def createErrorGenerator = _generate(ErrorGenerator)
    def createLinksGenerator = _generate(LinksGenerator)


    def ModelSchemaRootGenerator = for {
        data <- ModelSchemaRootDataGenerator
        meta <- ModelSchemaRootMetaGenerator
        links <- ModelSchemaRootLinksGenerator
    } yield ModelSchemaRoot(data, meta, links)
    def ErrorsGenerator = for {
        errors <- ErrorsErrorsGenerator
    } yield Errors(errors)
    def ErrorSourceNameClashGenerator = for {
        pointer <- MetaCopyrightGenerator
        parameter <- MetaCopyrightGenerator
    } yield ErrorSourceNameClash(pointer, parameter)
    def MetaGenerator = for {
        copyright <- MetaCopyrightGenerator
    } yield Meta(copyright)
    def ModelSchemaGenerator = for {
        name <- arbitrary[String]
        sizeRegister <- arbitrary[String]
        brand <- arbitrary[String]
        partnerArticleModelId <- arbitrary[BigInt]
        description <- MetaCopyrightGenerator
        ageGroups <- ModelSchemaAgeGroupsGenerator
        keywords <- ModelSchemaKeywordsGenerator
        lengthRegister <- ModelSchemaLengthRegisterGenerator
        silhouetteId <- ModelSchemaSilhouetteIdGenerator
        specialDescriptions <- ModelSchemaSpecialDescriptionsGenerator
        articleModelAttributes <- ModelSchemaArticleModelAttributesGenerator
    } yield ModelSchema(name, sizeRegister, brand, partnerArticleModelId, description, ageGroups, keywords, lengthRegister, silhouetteId, specialDescriptions, articleModelAttributes)
    def ErrorGenerator = for {
        source <- ErrorSourceGenerator
        code <- MetaCopyrightGenerator
        status <- MetaCopyrightGenerator
        detail <- MetaCopyrightGenerator
        title <- MetaCopyrightGenerator
    } yield Error(source, code, status, detail, title)
    def LinksGenerator = for {
        self <- MetaCopyrightGenerator
        related <- MetaCopyrightGenerator
    } yield Links(self, related)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
}