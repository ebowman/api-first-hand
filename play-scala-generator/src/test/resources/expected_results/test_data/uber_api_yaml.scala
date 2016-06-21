package uber.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import java.util.UUID
import scala.math.BigDecimal
import de.zalando.play.controllers.ArrayWrapper

object Generators extends JsValueGenerators {
    

    
    def createDoubleGenerator = _generate(DoubleGenerator)
    def createActivitiesHistoryGenerator = _generate(ActivitiesHistoryGenerator)
    def createProfilePictureGenerator = _generate(ProfilePictureGenerator)
    def createErrorCodeGenerator = _generate(ErrorCodeGenerator)
    def createEstimatesTimeGetCustomer_uuidGenerator = _generate(EstimatesTimeGetCustomer_uuidGenerator)
    def createProductsGetResponses200Generator = _generate(ProductsGetResponses200Generator)
    def createPriceEstimateHigh_estimateGenerator = _generate(PriceEstimateHigh_estimateGenerator)
    def createEstimatesPriceGetResponses200Generator = _generate(EstimatesPriceGetResponses200Generator)
    def createActivitiesHistoryOptGenerator = _generate(ActivitiesHistoryOptGenerator)
    

    
    def DoubleGenerator = arbitrary[Double]
    def ActivitiesHistoryGenerator = Gen.option(ActivitiesHistoryOptGenerator)
    def ProfilePictureGenerator = Gen.option(arbitrary[String])
    def ErrorCodeGenerator = Gen.option(arbitrary[Int])
    def EstimatesTimeGetCustomer_uuidGenerator = Gen.option(arbitrary[UUID])
    def ProductsGetResponses200Generator = Gen.containerOf[List,Product](ProductGenerator)
    def PriceEstimateHigh_estimateGenerator = Gen.option(arbitrary[BigDecimal])
    def EstimatesPriceGetResponses200Generator = Gen.containerOf[List,PriceEstimate](PriceEstimateGenerator)
    def ActivitiesHistoryOptGenerator = _genList(ActivityGenerator, "csv")
    

    def createActivityGenerator = _generate(ActivityGenerator)
    def createPriceEstimateGenerator = _generate(PriceEstimateGenerator)
    def createProductGenerator = _generate(ProductGenerator)
    def createProfileGenerator = _generate(ProfileGenerator)
    def createActivitiesGenerator = _generate(ActivitiesGenerator)
    def createErrorGenerator = _generate(ErrorGenerator)


    def ActivityGenerator = for {
        uuid <- ProfilePictureGenerator
    } yield Activity(uuid)
    def PriceEstimateGenerator = for {
        low_estimate <- PriceEstimateHigh_estimateGenerator
        display_name <- ProfilePictureGenerator
        estimate <- ProfilePictureGenerator
        high_estimate <- PriceEstimateHigh_estimateGenerator
        product_id <- ProfilePictureGenerator
        currency_code <- ProfilePictureGenerator
        surge_multiplier <- PriceEstimateHigh_estimateGenerator
    } yield PriceEstimate(low_estimate, display_name, estimate, high_estimate, product_id, currency_code, surge_multiplier)
    def ProductGenerator = for {
        image <- ProfilePictureGenerator
        description <- ProfilePictureGenerator
        display_name <- ProfilePictureGenerator
        product_id <- ProfilePictureGenerator
        capacity <- ProfilePictureGenerator
    } yield Product(image, description, display_name, product_id, capacity)
    def ProfileGenerator = for {
        first_name <- ProfilePictureGenerator
        email <- ProfilePictureGenerator
        promo_code <- ProfilePictureGenerator
        last_name <- ProfilePictureGenerator
        picture <- ProfilePictureGenerator
    } yield Profile(first_name, email, promo_code, last_name, picture)
    def ActivitiesGenerator = for {
        offset <- ErrorCodeGenerator
        limit <- ErrorCodeGenerator
        count <- ErrorCodeGenerator
        history <- ActivitiesHistoryGenerator
    } yield Activities(offset, limit, count, history)
    def ErrorGenerator = for {
        code <- ErrorCodeGenerator
        message <- ProfilePictureGenerator
        fields <- ProfilePictureGenerator
    } yield Error(code, message, fields)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
    
    implicit lazy val arbUUID: Arbitrary[UUID] = Arbitrary(Gen.uuid)
    

}