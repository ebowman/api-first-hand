
package simple_petstore_api_yaml

import play.api.http.Writeable
import play.api.libs.iteratee.Execution.Implicits.trampoline
import play.api.mvc.RequestHeader
import de.zalando.play.controllers._
import WriteableWrapper.writeable2wrapper
import akka.util.ByteString

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
    val writable_application_json_PetsGetResponses200_esc: Writeable[PetsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_ErrorModel_esc: Writeable[ErrorModel] =
        Writeable(a => ???, Some("application/json"))

    val writable_text_xml_PetsGetResponses200_esc: Writeable[PetsGetResponses200] =
        Writeable(a => ???, Some("text/xml"))

    val writable_text_html_PetsGetResponses200_esc: Writeable[PetsGetResponses200] =
        Writeable(a => ???, Some("text/html"))

    val writable_application_xml_PetsGetResponses200_esc: Writeable[PetsGetResponses200] =
        Writeable(a => ???, Some("application/xml"))

    val writable_application_xml_ErrorModel_esc: Writeable[ErrorModel] =
        Writeable(a => ???, Some("application/xml"))

    val writable_text_html_ErrorModel_esc: Writeable[ErrorModel] =
        Writeable(a => ???, Some("text/html"))

    val writable_text_xml_ErrorModel_esc: Writeable[ErrorModel] =
        Writeable(a => ???, Some("text/xml"))

    val writable_application_json_Pet_esc: Writeable[Pet] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_xml_Pet_esc: Writeable[Pet] =
        Writeable(a => ???, Some("application/xml"))

    val writable_text_html_Pet_esc: Writeable[Pet] =
        Writeable(a => ???, Some("text/html"))

    val writable_text_xml_Pet_esc: Writeable[Pet] =
        Writeable(a => ???, Some("text/xml"))

    val writable_application_json_Null_esc: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_PetsGetResponses200_esc, 
        writable_application_json_ErrorModel_esc, 
        writable_text_xml_PetsGetResponses200_esc, 
        writable_text_html_PetsGetResponses200_esc, 
        writable_application_xml_PetsGetResponses200_esc, 
        writable_application_xml_ErrorModel_esc, 
        writable_text_html_ErrorModel_esc, 
        writable_text_xml_ErrorModel_esc, 
        writable_application_json_Pet_esc, 
        writable_application_xml_Pet_esc, 
        writable_text_html_Pet_esc, 
        writable_text_xml_Pet_esc, 
        writable_application_json_Null_esc
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
    val reader_application_json_NewPet_esc: Parser[NewPet] =
        (content: ByteString) => ???
    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
            "application/json" -> reader_application_json_NewPet_esc
    )
}
