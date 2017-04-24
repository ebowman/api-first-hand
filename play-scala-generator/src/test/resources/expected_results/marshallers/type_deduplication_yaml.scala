
package type_deduplication.yaml

import play.api.http.Writeable
import play.api.libs.iteratee.Execution.Implicits.trampoline
import play.api.mvc.RequestHeader
import de.zalando.play.controllers._
import WriteableWrapper.writeable2wrapper
import akka.util.ByteString

import scala.math.BigInt



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
    val writable_application_json_Error: Writeable[Error] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Watering: Writeable[Watering] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Null: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_User: Writeable[User] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SunlightNeeds: Writeable[SunlightNeeds] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqUser: Writeable[Seq[User]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Area: Writeable[Area] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqPlant: Writeable[Seq[Plant]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqArea: Writeable[Seq[Area]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Location: Writeable[Location] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqString: Writeable[Seq[String]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Plant: Writeable[Plant] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqWatering: Writeable[Seq[Watering]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_WaterNeeds: Writeable[WaterNeeds] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_Error, 
        writable_application_json_Watering, 
        writable_application_json_Null, 
        writable_application_json_User, 
        writable_application_json_SunlightNeeds, 
        writable_application_json_SeqUser, 
        writable_application_json_Area, 
        writable_application_json_SeqPlant, 
        writable_application_json_SeqArea, 
        writable_application_json_Location, 
        writable_application_json_SeqString, 
        writable_application_json_Plant, 
        writable_application_json_SeqWatering, 
        writable_application_json_WaterNeeds
    )
}

/**
* This is a place to define definitions of custom deserializers for request body.
* They must be places into the {@custom} field of the ResponseWriters object
*
*/
object WrappedBodyParsers extends WrappedBodyParsersBase {

    /**
    * Transformer instance to be used as logic for {@Writeable}
    * It is important to define the type of {@Writeable} explicit and as narrow as possible
    * in order for play-swagger to be able to provide safety net for
    * different response types
    */
    val reader_application_json_SunlightNeeds_esc: Parser[SunlightNeeds] =
        (content: ByteString) => ???
    val reader_application_json_SigninData_esc: Parser[SigninData] =
        (content: ByteString) => ???
    val reader_application_json_User_esc: Parser[User] =
        (content: ByteString) => ???
    val reader_application_json_Location_esc: Parser[Location] =
        (content: ByteString) => ???
    val reader_application_json_Plant_esc: Parser[Plant] =
        (content: ByteString) => ???
    val reader_application_json_WaterNeeds_esc: Parser[WaterNeeds] =
        (content: ByteString) => ???
    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
            "application/json" -> reader_application_json_SunlightNeeds_esc, 
            "application/json" -> reader_application_json_SigninData_esc, 
            "application/json" -> reader_application_json_User_esc, 
            "application/json" -> reader_application_json_Location_esc, 
            "application/json" -> reader_application_json_Plant_esc, 
            "application/json" -> reader_application_json_WaterNeeds_esc
    )
}
