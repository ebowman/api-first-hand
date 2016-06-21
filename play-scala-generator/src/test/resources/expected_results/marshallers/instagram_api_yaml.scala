
package instagram.api.yaml

import play.api.http.Writeable
import play.api.libs.iteratee.Execution.Implicits.trampoline
import play.api.mvc.RequestHeader
import de.zalando.play.controllers._
import WriteableWrapper.writeable2wrapper
import akka.util.ByteString

import scala.math.BigInt
import scala.math.BigDecimal



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
    val writable_application_json_MediaMedia_idLikesGetResponses200_esc: Writeable[MediaMedia_idLikesGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_MediaMedia_idCommentsDeleteResponses200_esc: Writeable[MediaMedia_idCommentsDeleteResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersUser_idFollowsGetResponses200_esc: Writeable[UsersUser_idFollowsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_LocationsLocation_idGetResponses200_esc: Writeable[LocationsLocation_idGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersSelfFeedGetResponses200_esc: Writeable[UsersSelfFeedGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Tag_esc: Writeable[Tag] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_TagsSearchGetResponses200_esc: Writeable[TagsSearchGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_MediaMedia_idCommentsGetResponses200_esc: Writeable[MediaMedia_idCommentsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_TagsTag_nameMediaRecentGetResponses200_esc: Writeable[TagsTag_nameMediaRecentGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersUser_idGetResponses200_esc: Writeable[UsersUser_idGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_MediaSearchGetResponses200_esc: Writeable[MediaSearchGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Null_esc: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Media_esc: Writeable[Media] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_LocationsSearchGetResponses200_esc: Writeable[LocationsSearchGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersSelfRequested_byGetResponses200_esc: Writeable[UsersSelfRequested_byGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_MediaMedia_idLikesGetResponses200_esc, 
        writable_application_json_MediaMedia_idCommentsDeleteResponses200_esc, 
        writable_application_json_UsersUser_idFollowsGetResponses200_esc, 
        writable_application_json_LocationsLocation_idGetResponses200_esc, 
        writable_application_json_UsersSelfFeedGetResponses200_esc, 
        writable_application_json_Tag_esc, 
        writable_application_json_TagsSearchGetResponses200_esc, 
        writable_application_json_MediaMedia_idCommentsGetResponses200_esc, 
        writable_application_json_TagsTag_nameMediaRecentGetResponses200_esc, 
        writable_application_json_UsersUser_idGetResponses200_esc, 
        writable_application_json_MediaSearchGetResponses200_esc, 
        writable_application_json_Null_esc, 
        writable_application_json_Media_esc, 
        writable_application_json_LocationsSearchGetResponses200_esc, 
        writable_application_json_UsersSelfRequested_byGetResponses200_esc
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
    val reader_application_json_LocationLatitude_esc: Parser[LocationLatitude] =
        (content: ByteString) => ???
    val reader_application_json_UsersUser_idRelationshipPostAction_esc: Parser[UsersUser_idRelationshipPostAction] =
        (content: ByteString) => ???
    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
            "application/json" -> reader_application_json_LocationLatitude_esc, 
            "application/json" -> reader_application_json_UsersUser_idRelationshipPostAction_esc
    )
}
