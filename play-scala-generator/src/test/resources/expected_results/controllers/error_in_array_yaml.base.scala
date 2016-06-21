package error_in_array.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._

import scala.util._
import de.zalando.play.controllers.ArrayWrapper
import scala.math.BigInt



import de.zalando.play.controllers.ResponseWriters




trait Error_in_arrayYamlBase extends Controller with PlayBodyParsing {
    sealed trait GetschemaModelType[T] extends ResultWrapper[T]
    case class GetschemaModel200(result: ModelSchemaRoot)(implicit val writer: String => Option[Writeable[ModelSchemaRoot]]) extends GetschemaModelType[ModelSchemaRoot] { val statusCode = 200 }
    

    private type getschemaModelActionRequestType       = (ModelSchemaRoot)
    private type getschemaModelActionType[T]            = getschemaModelActionRequestType => GetschemaModelType[T] forSome { type T }

        private def getschemaModelParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            import de.zalando.play.controllers.WrappedBodyParsers
            
            val customParsers = WrappedBodyParsers.anyParser[ModelSchemaRoot]
            anyParser[ModelSchemaRoot](bodyMimeType, customParsers, "Invalid ModelSchemaRoot", maxLength) _
        }

    val getschemaModelActionConstructor  = Action

def getschemaModelAction[T] = (f: getschemaModelActionType[T]) => getschemaModelActionConstructor(BodyParsers.parse.using(getschemaModelParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { getschemaModelResponseMimeType =>
            val root = request.body
            
            

                val result =
                        new SchemaModelGetValidator(root).errors match {
                            case e if e.isEmpty => processValidgetschemaModelRequest(f)((root))(getschemaModelResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getschemaModelResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetschemaModelRequest[T](f: getschemaModelActionType[T])(request: getschemaModelActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetschemaModelType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with GetschemaModelType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
