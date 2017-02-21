
package nakadi.yaml

import play.api.http.Writeable
import play.api.libs.iteratee.Execution.Implicits.trampoline
import play.api.mvc.RequestHeader
import de.zalando.play.controllers._
import WriteableWrapper.writeable2wrapper
import akka.util.ByteString

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
    val writable_application_json_Problem: Writeable[Problem] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Metrics: Writeable[Metrics] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SimpleStreamEvent: Writeable[SimpleStreamEvent] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_TopicPartition: Writeable[TopicPartition] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqTopic: Writeable[Seq[Topic]] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Null: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SeqTopicPartition: Writeable[Seq[TopicPartition]] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_Problem, 
        writable_application_json_Metrics, 
        writable_application_json_SimpleStreamEvent, 
        writable_application_json_TopicPartition, 
        writable_application_json_SeqTopic, 
        writable_application_json_Null, 
        writable_application_json_SeqTopicPartition
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
    val reader_application_json_Option_Event__esc: Parser[Option[Event]] =
        (content: ByteString) => ???
    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
            "application/json" -> reader_application_json_Option_Event__esc
    )
}
