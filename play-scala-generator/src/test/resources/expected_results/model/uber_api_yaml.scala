package uber.api


    import scala.math.BigDecimal
    import java.util.UUID

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class Activity(uuid: Option[String]) 
    case class PriceEstimate(low_estimate: Option[BigDecimal], display_name: Option[String], estimate: Option[String], high_estimate: Option[BigDecimal], product_id: Option[String], currency_code: Option[String], surge_multiplier: Option[BigDecimal]) 
    case class Product(image: Option[String], description: Option[String], display_name: Option[String], product_id: Option[String], capacity: Option[String]) 
    case class Profile(first_name: Option[String], email: Option[String], promo_code: Option[String], last_name: Option[String], picture: Option[String]) 
    case class Activities(offset: Option[Int], limit: Option[Int], count: Option[Int], history: Option[Seq[Activity]]) 
    case class Error(code: Option[Int], message: Option[String], fields: Option[String]) 


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

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {



import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_UUIDQuery = PlayPathBindables.queryBindableUUID
    implicit val bindable_OptionIntQuery: QueryStringBindable[Option[Int]] = PlayPathBindables.createOptionQueryBindable[Int]
    implicit val bindable_OptionStringQuery: QueryStringBindable[Option[String]] = PlayPathBindables.createOptionQueryBindable[String]
    implicit val bindable_OptionUUIDQuery: QueryStringBindable[Option[UUID]] = PlayPathBindables.createOptionQueryBindable[UUID]

}