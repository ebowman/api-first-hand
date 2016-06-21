
package nakadi.yaml

import play.api.http.Writeable
import play.api.libs.iteratee.Execution.Implicits.trampoline
import play.api.mvc.RequestHeader
import de.zalando.play.controllers._
import WriteableWrapper.writeable2wrapper
import akka.util.ByteString

import de.zalando.play.controllers.ArrayWrapper
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
    val writable_application_json_Problem_esc: Writeable[Problem] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Metrics_esc: Writeable[Metrics] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_SimpleStreamEvent_esc: Writeable[SimpleStreamEvent] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_TopicPartition_esc: Writeable[TopicPartition] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_TopicsGetResponses200_esc: Writeable[TopicsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Null_esc: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_TopicsTopicPartitionsGetResponses200_esc: Writeable[TopicsTopicPartitionsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_Problem_esc, 
        writable_application_json_Metrics_esc, 
        writable_application_json_SimpleStreamEvent_esc, 
        writable_application_json_TopicPartition_esc, 
        writable_application_json_TopicsGetResponses200_esc, 
        writable_application_json_Null_esc, 
        writable_application_json_TopicsTopicPartitionsGetResponses200_esc
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
    val reader_application_json_TopicsTopicEventsBatchPostEvent_esc: Parser[TopicsTopicEventsBatchPostEvent] =
        (content: ByteString) => ???
    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
            "application/json" -> reader_application_json_TopicsTopicEventsBatchPostEvent_esc
    )
}
