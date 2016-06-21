package uber.api

package object yaml {

    import java.util.UUID
    import scala.math.BigDecimal
    import de.zalando.play.controllers.ArrayWrapper

    import de.zalando.play.controllers.PlayPathBindables



    type EstimatesPriceGetEnd_latitude = Double
    type ActivitiesHistory = Option[ActivitiesHistoryOpt]
    type ProfilePicture = Option[String]
    type ErrorCode = Option[Int]
    type EstimatesTimeGetCustomer_uuid = Option[UUID]
    type ProductsGetResponses200 = Seq[Product]
    type PriceEstimateHigh_estimate = Option[BigDecimal]
    type EstimatesPriceGetResponses200 = Seq[PriceEstimate]
    type ActivitiesHistoryOpt = ArrayWrapper[Activity]


    case class Activity(uuid: ProfilePicture) 
    case class PriceEstimate(low_estimate: PriceEstimateHigh_estimate, display_name: ProfilePicture, estimate: ProfilePicture, high_estimate: PriceEstimateHigh_estimate, product_id: ProfilePicture, currency_code: ProfilePicture, surge_multiplier: PriceEstimateHigh_estimate) 
    case class Product(image: ProfilePicture, description: ProfilePicture, display_name: ProfilePicture, product_id: ProfilePicture, capacity: ProfilePicture) 
    case class Profile(first_name: ProfilePicture, email: ProfilePicture, promo_code: ProfilePicture, last_name: ProfilePicture, picture: ProfilePicture) 
    case class Activities(offset: ErrorCode, limit: ErrorCode, count: ErrorCode, history: ActivitiesHistory) 
    case class Error(code: ErrorCode, message: ProfilePicture, fields: ProfilePicture) 


    implicit val bindable_UUIDQuery = PlayPathBindables.queryBindableUUID

    implicit val bindable_OptionIntQuery = PlayPathBindables.createOptionQueryBindable[Int]

    implicit val bindable_OptionStringQuery = PlayPathBindables.createOptionQueryBindable[String]

    implicit val bindable_OptionUUIDQuery = PlayPathBindables.createOptionQueryBindable[UUID]


}
