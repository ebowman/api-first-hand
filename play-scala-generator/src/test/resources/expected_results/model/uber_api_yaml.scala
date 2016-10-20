package uber.api


    import java.util.UUID
    import scala.math.BigDecimal

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type ActivitiesHistory = Option[ActivitiesHistoryOpt]
    type ProfilePicture = Option[String]
    type ErrorCode = Option[Int]
    type EstimatesTimeGetCustomer_uuid = Option[UUID]
    type ProductsGetResponses200 = Seq[Product]
    type PriceEstimateHigh_estimate = Option[BigDecimal]
    type EstimatesPriceGetResponses200 = Seq[PriceEstimate]
    type ActivitiesHistoryOpt = Seq[Activity]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_UUIDQuery = PlayPathBindables.queryBindableUUID
    implicit val bindable_OptionIntQuery: QueryStringBindable[Option[Int]] = PlayPathBindables.createOptionQueryBindable[Int]
    implicit val bindable_OptionStringQuery: QueryStringBindable[Option[String]] = PlayPathBindables.createOptionQueryBindable[String]
    implicit val bindable_OptionUUIDQuery: QueryStringBindable[Option[UUID]] = PlayPathBindables.createOptionQueryBindable[UUID]

}
//noinspection ScalaStyle
package yaml {


    case class Activity(uuid: ProfilePicture) 
    case class PriceEstimate(low_estimate: PriceEstimateHigh_estimate, display_name: ProfilePicture, estimate: ProfilePicture, high_estimate: PriceEstimateHigh_estimate, product_id: ProfilePicture, currency_code: ProfilePicture, surge_multiplier: PriceEstimateHigh_estimate) 
    case class Product(image: ProfilePicture, description: ProfilePicture, display_name: ProfilePicture, product_id: ProfilePicture, capacity: ProfilePicture) 
    case class Profile(first_name: ProfilePicture, email: ProfilePicture, promo_code: ProfilePicture, last_name: ProfilePicture, picture: ProfilePicture) 
    case class Activities(offset: ErrorCode, limit: ErrorCode, count: ErrorCode, history: ActivitiesHistory) 
    case class Error(code: ErrorCode, message: ProfilePicture, fields: ProfilePicture) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val ActivityWrites: Writes[Activity] = new Writes[Activity] {
        def writes(ss: Activity) =
          Json.obj(
            "uuid" -> ss.uuid
          )
        }
    implicit val ActivitiesWrites: Writes[Activities] = new Writes[Activities] {
        def writes(ss: Activities) =
          Json.obj(
            "offset" -> ss.offset, 
            "limit" -> ss.limit, 
            "count" -> ss.count, 
            "history" -> ss.history
          )
        }
    implicit val PriceEstimateWrites: Writes[PriceEstimate] = new Writes[PriceEstimate] {
        def writes(ss: PriceEstimate) =
          Json.obj(
            "low_estimate" -> ss.low_estimate, 
            "display_name" -> ss.display_name, 
            "estimate" -> ss.estimate, 
            "high_estimate" -> ss.high_estimate, 
            "product_id" -> ss.product_id, 
            "currency_code" -> ss.currency_code, 
            "surge_multiplier" -> ss.surge_multiplier
          )
        }
    implicit val ProductWrites: Writes[Product] = new Writes[Product] {
        def writes(ss: Product) =
          Json.obj(
            "image" -> ss.image, 
            "description" -> ss.description, 
            "display_name" -> ss.display_name, 
            "product_id" -> ss.product_id, 
            "capacity" -> ss.capacity
          )
        }
    implicit val ProfileWrites: Writes[Profile] = new Writes[Profile] {
        def writes(ss: Profile) =
          Json.obj(
            "first_name" -> ss.first_name, 
            "email" -> ss.email, 
            "promo_code" -> ss.promo_code, 
            "last_name" -> ss.last_name, 
            "picture" -> ss.picture
          )
        }
    }
}
