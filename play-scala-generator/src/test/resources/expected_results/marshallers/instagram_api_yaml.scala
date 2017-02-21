
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
    val writable_application_json_MediaMedia_idLikesGetResponses200: Writeable[MediaMedia_idLikesGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_MediaMedia_idCommentsDeleteResponses200: Writeable[MediaMedia_idCommentsDeleteResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersUser_idFollowsGetResponses200: Writeable[UsersUser_idFollowsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_LocationsLocation_idGetResponses200: Writeable[LocationsLocation_idGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersSelfFeedGetResponses200: Writeable[UsersSelfFeedGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Tag: Writeable[Tag] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_TagsSearchGetResponses200: Writeable[TagsSearchGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_MediaMedia_idCommentsGetResponses200: Writeable[MediaMedia_idCommentsGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_TagsTag_nameMediaRecentGetResponses200: Writeable[TagsTag_nameMediaRecentGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersUser_idGetResponses200: Writeable[UsersUser_idGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_MediaSearchGetResponses200: Writeable[MediaSearchGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Null: Writeable[Null] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_Media: Writeable[Media] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_LocationsSearchGetResponses200: Writeable[LocationsSearchGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    val writable_application_json_UsersSelfRequested_byGetResponses200: Writeable[UsersSelfRequested_byGetResponses200] =
        Writeable(a => ???, Some("application/json"))

    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[WriteableWrapper[_]] = Seq(
        writable_application_json_MediaMedia_idLikesGetResponses200, 
        writable_application_json_MediaMedia_idCommentsDeleteResponses200, 
        writable_application_json_UsersUser_idFollowsGetResponses200, 
        writable_application_json_LocationsLocation_idGetResponses200, 
        writable_application_json_UsersSelfFeedGetResponses200, 
        writable_application_json_Tag, 
        writable_application_json_TagsSearchGetResponses200, 
        writable_application_json_MediaMedia_idCommentsGetResponses200, 
        writable_application_json_TagsTag_nameMediaRecentGetResponses200, 
        writable_application_json_UsersUser_idGetResponses200, 
        writable_application_json_MediaSearchGetResponses200, 
        writable_application_json_Null, 
        writable_application_json_Media, 
        writable_application_json_LocationsSearchGetResponses200, 
        writable_application_json_UsersSelfRequested_byGetResponses200
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
    val reader_application_json_Option_BigDecimal__esc: Parser[Option[BigDecimal]] =
        (content: ByteString) => ???
    val reader_application_json_Option_UsersUser_idRelationshipPostActionOptionEnum__esc: Parser[Option[UsersUser_idRelationshipPostActionOptionEnum]] =
        (content: ByteString) => ???
    /**
    * This collection contains all {@Writeable}s which could be used in
    * as a marshaller for different mime types and types of response
    */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
            "application/json" -> reader_application_json_Option_BigDecimal__esc, 
            "application/json" -> reader_application_json_Option_UsersUser_idRelationshipPostActionOptionEnum__esc
    )
}
