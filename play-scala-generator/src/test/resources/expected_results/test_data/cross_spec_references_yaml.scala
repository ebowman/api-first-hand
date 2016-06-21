package cross_spec_references.yaml

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
    def createModelSchemaRootDataGenerator = _generate(ModelSchemaRootDataGenerator)
    def createPetIdGenerator = _generate(PetIdGenerator)
    def createModelSchemaRootLinksGenerator = _generate(ModelSchemaRootLinksGenerator)
    def createModelSchemaAgeGroupsArrGenerator = _generate(ModelSchemaAgeGroupsArrGenerator)
    def createPetTagsGenerator = _generate(PetTagsGenerator)
    def createModelSchemaSilhouetteIdGenerator = _generate(ModelSchemaSilhouetteIdGenerator)
    def createPetPhotoUrlsGenerator = _generate(PetPhotoUrlsGenerator)
    def createModelSchemaLengthRegisterGenerator = _generate(ModelSchemaLengthRegisterGenerator)
    def createModelSchemaAgeGroupsGenerator = _generate(ModelSchemaAgeGroupsGenerator)
    def createPetCategoryGenerator = _generate(PetCategoryGenerator)
    def createPetTagsOptGenerator = _generate(PetTagsOptGenerator)
    def createModelSchemaRootMetaGenerator = _generate(ModelSchemaRootMetaGenerator)
    

    
    def ModelSchemaSpecialDescriptionsOptGenerator = _genList(arbitrary[String], "csv")
    def MetaCopyrightGenerator = Gen.option(arbitrary[String])
    def ModelSchemaKeywordsGenerator = Gen.option(arbitrary[String])
    def ModelSchemaSpecialDescriptionsGenerator = Gen.option(ModelSchemaSpecialDescriptionsOptGenerator)
    def ModelSchemaRootDataGenerator = Gen.option(ModelSchemaRootDataOptGenerator)
    def PetIdGenerator = Gen.option(arbitrary[Long])
    def ModelSchemaRootLinksGenerator = Gen.option(ModelSchemaRootLinksOptGenerator)
    def ModelSchemaAgeGroupsArrGenerator = Gen.oneOf(Seq(Baby, Kid, Teen, Adult))
    def PetTagsGenerator = Gen.option(PetTagsOptGenerator)
    def ModelSchemaSilhouetteIdGenerator = Gen.oneOf(Seq(Kitchen, Bikini_top, Toys, Nightwear_combination, Bra, One_piece_underwear, Ball, Cleansing, Skincare, Jewellery, Headgear, Bustier, Beach_trouser, Bedroom, Lounge, Nail, Undershirt, Combination_clothing, Gloves, Fragrance, Other_equipment, Fitness, Bathroom, One_piece_nightwear, Sleeping_bag, Coat, Case, Sandals, Ankle_boots, Stocking, Shirt, Backpack, Face_cosmetic, Travel_equipment, Hair, Sneaker, Beauty_equipment, Bikini_combination, Backless_slipper, Beach_accessoires, Scarf, First_shoe, Voucher, Wallet, Peeling, Glasses, Boards, Sun, Shave, Low_shoe, Underwear_combination, Nightdress, Suit_accessoires, Watch, Headphones, Skates, Boots, Jacket, Etui, Night_shirt, Other_accessoires, Vest, Bag, System, Racket, Trouser, Lip_cosmetic, Keychain, Belt, Ballerina_shoe, One_piece_suit, Night_trouser, Skirt, Tights, Beach_shirt, Dress, Bicycle, Protector, Eye_cosmetic, Bathrobe, Bicycle_equipment, Pullover, One_piece_beachwear, Underpant, Living, Cardigan, Corsage, Shoe_accessoires, Umbrella, Pumps, Tent, T_shirt_top, Ski))
    def PetPhotoUrlsGenerator = Gen.containerOf[List,String](arbitrary[String])
    def ModelSchemaLengthRegisterGenerator = Gen.option(arbitrary[String])
    def ModelSchemaAgeGroupsGenerator = _genList(ModelSchemaAgeGroupsArrGenerator, "csv")
    def PetCategoryGenerator = Gen.option(PetCategoryOptGenerator)
    def PetTagsOptGenerator = Gen.containerOf[List,PetCategoryOpt](PetCategoryOptGenerator)
    def ModelSchemaRootMetaGenerator = Gen.option(ModelSchemaRootMetaOptGenerator)
    

    def createPetCategoryOptGenerator = _generate(PetCategoryOptGenerator)
    def createModelSchemaRootDataOptGenerator = _generate(ModelSchemaRootDataOptGenerator)
    def createModelSchemaRootMetaOptGenerator = _generate(ModelSchemaRootMetaOptGenerator)
    def createModelSchemaRootGenerator = _generate(ModelSchemaRootGenerator)
    def createPetGenerator = _generate(PetGenerator)
    def createModelSchemaRootLinksOptGenerator = _generate(ModelSchemaRootLinksOptGenerator)


    def PetCategoryOptGenerator = for {
        id <- PetIdGenerator
        name <- MetaCopyrightGenerator
    } yield PetCategoryOpt(id, name)
    def ModelSchemaRootDataOptGenerator = for {
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
        articleModelAttributes <- ModelSchemaSpecialDescriptionsGenerator
    } yield ModelSchemaRootDataOpt(name, sizeRegister, brand, partnerArticleModelId, description, ageGroups, keywords, lengthRegister, silhouetteId, specialDescriptions, articleModelAttributes)
    def ModelSchemaRootMetaOptGenerator = for {
        copyright <- MetaCopyrightGenerator
    } yield ModelSchemaRootMetaOpt(copyright)
    def ModelSchemaRootGenerator = for {
        data <- ModelSchemaRootDataGenerator
        meta <- ModelSchemaRootMetaGenerator
        links <- ModelSchemaRootLinksGenerator
    } yield ModelSchemaRoot(data, meta, links)
    def PetGenerator = for {
        name <- arbitrary[String]
        tags <- PetTagsGenerator
        photoUrls <- PetPhotoUrlsGenerator
        id <- PetIdGenerator
        status <- MetaCopyrightGenerator
        category <- PetCategoryGenerator
    } yield Pet(name, tags, photoUrls, id, status, category)
    def ModelSchemaRootLinksOptGenerator = for {
        self <- MetaCopyrightGenerator
        related <- MetaCopyrightGenerator
    } yield ModelSchemaRootLinksOpt(self, related)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
}