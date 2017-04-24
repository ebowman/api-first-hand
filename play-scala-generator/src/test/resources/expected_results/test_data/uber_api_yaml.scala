package uber.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigDecimal
import java.util.UUID

object Generators extends JsValueGenerators {
    

    
    def createDoubleGenerator = _generate(DoubleGenerator)
    def createOptionIntGenerator = _generate(OptionIntGenerator)
    def createOptionUUIDGenerator = _generate(OptionUUIDGenerator)
    def createSeqProductGenerator = _generate(SeqProductGenerator)
    def createOptionStringGenerator = _generate(OptionStringGenerator)
    def createSeqPriceEstimateGenerator = _generate(SeqPriceEstimateGenerator)
    

    
    def DoubleGenerator = arbitrary[Double]
    def OptionIntGenerator = Gen.option(arbitrary[Int])
    def OptionUUIDGenerator = Gen.option(arbitrary[UUID])
    def SeqProductGenerator: Gen[List[Product]] = Gen.containerOf[List,Product](ProductGenerator)
    def OptionStringGenerator = Gen.option(arbitrary[String])
    def SeqPriceEstimateGenerator: Gen[List[PriceEstimate]] = Gen.containerOf[List,PriceEstimate](PriceEstimateGenerator)
    

    def createActivityGenerator = _generate(ActivityGenerator)
    def createPriceEstimateGenerator = _generate(PriceEstimateGenerator)
    def createProductGenerator = _generate(ProductGenerator)
    def createProfileGenerator = _generate(ProfileGenerator)
    def createActivitiesGenerator = _generate(ActivitiesGenerator)
    def createErrorGenerator = _generate(ErrorGenerator)


    def ActivityGenerator = for {
        uuid <- Gen.option(arbitrary[String])
    } yield Activity(uuid)
    def PriceEstimateGenerator = for {
        low_estimate <- Gen.option(arbitrary[BigDecimal])
        display_name <- Gen.option(arbitrary[String])
        estimate <- Gen.option(arbitrary[String])
        high_estimate <- Gen.option(arbitrary[BigDecimal])
        product_id <- Gen.option(arbitrary[String])
        currency_code <- Gen.option(arbitrary[String])
        surge_multiplier <- Gen.option(arbitrary[BigDecimal])
    } yield PriceEstimate(low_estimate, display_name, estimate, high_estimate, product_id, currency_code, surge_multiplier)
    def ProductGenerator = for {
        image <- Gen.option(arbitrary[String])
        description <- Gen.option(arbitrary[String])
        display_name <- Gen.option(arbitrary[String])
        product_id <- Gen.option(arbitrary[String])
        capacity <- Gen.option(arbitrary[String])
    } yield Product(image, description, display_name, product_id, capacity)
    def ProfileGenerator = for {
        first_name <- Gen.option(arbitrary[String])
        email <- Gen.option(arbitrary[String])
        promo_code <- Gen.option(arbitrary[String])
        last_name <- Gen.option(arbitrary[String])
        picture <- Gen.option(arbitrary[String])
    } yield Profile(first_name, email, promo_code, last_name, picture)
    def ActivitiesGenerator = for {
        offset <- Gen.option(arbitrary[Int])
        limit <- Gen.option(arbitrary[Int])
        count <- Gen.option(arbitrary[Int])
        history <- Gen.option(Gen.containerOf[List,Activity](ActivityGenerator))
    } yield Activities(offset, limit, count, history)
    def ErrorGenerator = for {
        code <- Gen.option(arbitrary[Int])
        message <- Gen.option(arbitrary[String])
        fields <- Gen.option(arbitrary[String])
    } yield Error(code, message, fields)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    
    implicit lazy val arbUUID: Arbitrary[UUID] = Arbitrary(Gen.uuid)
    

}