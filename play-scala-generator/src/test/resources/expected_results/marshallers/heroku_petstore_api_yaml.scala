
package heroku.petstore.api.yaml

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
    val writable_application_json_SeqPet: Writeable[Seq[Pet]] =
        Writeable(a => ???, Some("application/json"))

    val writable_text_html_SeqPet: Writeable[Seq[Pet]] =
        Writeable(a => ???, Some("text/html"))

    val writable_application_json_Null: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    val writable_text_html_Null: Writeable[Null] =
        Writeable(a => ???, Some("text/html"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_SeqPet, 
        writable_text_html_SeqPet, 
        writable_application_json_Null, 
        writable_text_html_Null
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
    val reader_application_json_Option_Pet__esc: Parser[Option[Pet]] =
        (content: ByteString) => ???
    val reader_text_xml_Option_Pet__esc: Parser[Option[Pet]] =
        (content: ByteString) => ???
    val reader_application_json_Pet_esc: Parser[Pet] =
        (content: ByteString) => ???
    val reader_text_xml_Pet_esc: Parser[Pet] =
        (content: ByteString) => ???
    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
            "application/json" -> reader_application_json_Option_Pet__esc, 
            "text/xml" -> reader_text_xml_Option_Pet__esc, 
            "application/json" -> reader_application_json_Pet_esc, 
            "text/xml" -> reader_text_xml_Pet_esc
    )
}
