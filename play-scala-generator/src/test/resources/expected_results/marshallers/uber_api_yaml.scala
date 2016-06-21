
package uber.api.yaml

import play.api.http.Writeable
import play.api.libs.iteratee.Execution.Implicits.trampoline
import play.api.mvc.RequestHeader
import de.zalando.play.controllers._
import WriteableWrapper.writeable2wrapper
import akka.util.ByteString

import java.util.UUID
import scala.math.BigDecimal
import de.zalando.play.controllers.ArrayWrapper



/**
* This is a place to define definitions of custom serializers for results.
* Serializers are just instances of {@Writeable}s
* They must be placed into the {@custom} field of the ResponseWriters object
*
*/
object ResponseWriters extends ResponseWritersBase {

    /**
    * Transformer instance to be used as logic for {@Writeable}
    * It is important to define the type of {@Writeable} explicit and as narrow as possible
    * in order for play-swagger to be able to provide safety net for
    * different response types
    */
    val writable_application_json_Profile_esc: Writeable[Profile] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Error_esc: Writeable[Error] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_ProductsGetResponses200_esc: Writeable[ProductsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_EstimatesPriceGetResponses200_esc: Writeable[EstimatesPriceGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Activities_esc: Writeable[Activities] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_Profile_esc, 
        writable_application_json_Error_esc, 
        writable_application_json_ProductsGetResponses200_esc, 
        writable_application_json_EstimatesPriceGetResponses200_esc, 
        writable_application_json_Activities_esc
    )
}

