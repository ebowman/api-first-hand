package string_formats.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._

import scala.util._
import de.zalando.play.controllers.Base64String
import Base64String._
import de.zalando.play.controllers.BinaryString
import BinaryString._
import org.joda.time.DateTime
import java.util.UUID
import org.joda.time.LocalDate

import de.zalando.play.controllers.PlayPathBindables





trait String_formatsYamlBase extends Controller with PlayBodyParsing {
    sealed trait GetType[T] extends ResultWrapper[T]
    
    case class Get200(headers: Seq[(String, String)] = Nil) extends EmptyReturn(200, headers)
    

    private type getActionRequestType       = (GetDate_time, GetDate, GetBase64, GetUuid, BinaryString)
    private type getActionType[T]            = getActionRequestType => GetType[T] forSome { type T }

        private def getParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            import de.zalando.play.controllers.WrappedBodyParsers
            
            val customParsers = WrappedBodyParsers.anyParser[BinaryString]
            anyParser[BinaryString](bodyMimeType, customParsers, "Invalid BinaryString", maxLength) _
        }

    val getActionConstructor  = Action

def getAction[T] = (f: getActionType[T]) => (date_time: GetDate_time, date: GetDate, base64: GetBase64, uuid: GetUuid) => getActionConstructor(BodyParsers.parse.using(getParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/yaml")

        negotiateContent(request.acceptedTypes, providedTypes).map { getResponseMimeType =>
            val petId = request.body
            
            

                val result =
                        new GetValidator(date_time, date, base64, uuid, petId).errors match {
                            case e if e.isEmpty => processValidgetRequest(f)((date_time, date, base64, uuid, petId))(getResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetRequest[T](f: getActionType[T])(request: getActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with GetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
