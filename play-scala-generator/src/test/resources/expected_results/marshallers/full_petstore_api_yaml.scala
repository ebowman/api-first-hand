
package full.petstore.api.yaml

import play.api.http.Writeable
import play.api.libs.iteratee.Execution.Implicits.trampoline
import play.api.mvc.RequestHeader
import de.zalando.play.controllers._
import WriteableWrapper.writeable2wrapper
import akka.util.ByteString

import java.time.ZonedDateTime
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
    val writable_application_json_Null: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqPet: Writeable[Seq[Pet]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_xml_Null: Writeable[Null] =
        Writeable(a => ???, Some("application/xml"))

    val writable_application_xml_SeqPet: Writeable[Seq[Pet]] =
        Writeable(a => ???, Some("application/xml"))

    val writable_application_json_Order: Writeable[Order] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_xml_Order: Writeable[Order] =
        Writeable(a => ???, Some("application/xml"))

    val writable_application_json_User: Writeable[User] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_xml_User: Writeable[User] =
        Writeable(a => ???, Some("application/xml"))

    val writable_application_json_Pet: Writeable[Pet] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_xml_Pet: Writeable[Pet] =
        Writeable(a => ???, Some("application/xml"))

    val writable_application_json_String: Writeable[String] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_xml_String: Writeable[String] =
        Writeable(a => ???, Some("application/xml"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_Null, 
        writable_application_json_SeqPet, 
        writable_application_xml_Null, 
        writable_application_xml_SeqPet, 
        writable_application_json_Order, 
        writable_application_xml_Order, 
        writable_application_json_User, 
        writable_application_xml_User, 
        writable_application_json_Pet, 
        writable_application_xml_Pet, 
        writable_application_json_String, 
        writable_application_xml_String
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
    val reader_application_json_Option_Order__esc: Parser[Option[Order]] =
        (content: ByteString) => ???
    val reader_application_json_Option_User__esc: Parser[Option[User]] =
        (content: ByteString) => ???
    val reader_application_json_Option_Seq_User___esc: Parser[Option[Seq[User]]] =
        (content: ByteString) => ???
    val reader_application_json_Option_Pet__esc: Parser[Option[Pet]] =
        (content: ByteString) => ???
    val reader_application_xml_Option_Pet__esc: Parser[Option[Pet]] =
        (content: ByteString) => ???
    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
            "application/json" -> reader_application_json_Option_Order__esc, 
            "application/json" -> reader_application_json_Option_User__esc, 
            "application/json" -> reader_application_json_Option_Seq_User___esc, 
            "application/json" -> reader_application_json_Option_Pet__esc, 
            "application/xml" -> reader_application_xml_Option_Pet__esc
    )
}
