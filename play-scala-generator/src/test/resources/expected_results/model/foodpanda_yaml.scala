package com.foodpanda.popsey




//noinspection ScalaStyle
package api {


    case class VendorsResponse(total_hits: Int, vendors: Option[Seq[Vendor]]) 
    case class Location(latitude: Float, longitude: Float) 
    case class Vendor(id: Long, location: Option[Location]) 
    case class VendorQuery(vendor_codes: Option[Seq[String]], includes: Option[VendorQueryIncludesOptionEnum]) 

    case class VendorQueryIncludesOptionEnum(override val value: String) extends AnyVal with de.zalando.play.controllers.StringAnyVal

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val VendorQueryReads: Reads[VendorQuery] = (
            (JsPath \ "vendor_codes").read[Option[Seq[String]]] and (JsPath \ "includes").read[Option[VendorQueryIncludesOptionEnum]]
        )(VendorQuery.apply _)
    }

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object api {


    object VendorQueryIncludesOptionEnum {
        
        val Menus = new VendorQueryIncludesOptionEnum("menus")
        val Payments = new VendorQueryIncludesOptionEnum("payments")

        implicit def stringToVendorQueryIncludesOptionEnum: String => VendorQueryIncludesOptionEnum = {
            case "menus" => Menus
            case "payments" => Payments
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }


}