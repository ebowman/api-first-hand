
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
    val writable_application_json_Error_esc: Writeable[Error] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Watering_esc: Writeable[Watering] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Null_esc: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_User_esc: Writeable[User] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SunlightNeeds_esc: Writeable[SunlightNeeds] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersGetResponses200_esc: Writeable[UsersGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Area_esc: Writeable[Area] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_PlantsGetResponses200_esc: Writeable[PlantsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_AreasGetResponses200_esc: Writeable[AreasGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Location_esc: Writeable[Location] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_PlantsPlant_idPicturesGetResponses200_esc: Writeable[PlantsPlant_idPicturesGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Plant_esc: Writeable[Plant] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_PlantsPlant_idWateringsGetResponses200_esc: Writeable[PlantsPlant_idWateringsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_WaterNeeds_esc: Writeable[WaterNeeds] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_Error_esc, 
        writable_application_json_Watering_esc, 
        writable_application_json_Null_esc, 
        writable_application_json_User_esc, 
        writable_application_json_SunlightNeeds_esc, 
        writable_application_json_UsersGetResponses200_esc, 
        writable_application_json_Area_esc, 
        writable_application_json_PlantsGetResponses200_esc, 
        writable_application_json_AreasGetResponses200_esc, 
        writable_application_json_Location_esc, 
        writable_application_json_PlantsPlant_idPicturesGetResponses200_esc, 
        writable_application_json_Plant_esc, 
        writable_application_json_PlantsPlant_idWateringsGetResponses200_esc, 
        writable_application_json_WaterNeeds_esc
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
