
package uber.api.yaml

import play.api.http.Writeable
import play.api.libs.iteratee.Execution.Implicits.trampoline
import play.api.mvc.RequestHeader
import de.zalando.play.controllers._
import WriteableWrapper.writeable2wrapper
import akka.util.ByteString

import scala.math.BigDecimal
import java.util.UUID



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
    val writable_application_json_Profile: Writeable[Profile] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Error: Writeable[Error] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqProduct: Writeable[Seq[Product]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqPriceEstimate: Writeable[Seq[PriceEstimate]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Activities: Writeable[Activities] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_Profile, 
        writable_application_json_Error, 
        writable_application_json_SeqProduct, 
        writable_application_json_SeqPriceEstimate, 
        writable_application_json_Activities
    )
}

