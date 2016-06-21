package cross_spec_references.yaml

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




trait Cross_spec_referencesYamlBase extends Controller with PlayBodyParsing {
    sealed trait PostType[T] extends ResultWrapper[T]
    case class Post200(result: Pet)(implicit val writer: String => Option[Writeable[Pet]]) extends PostType[Pet] { val statusCode = 200 }
    

    private type postActionRequestType       = (ModelSchemaRoot)
    private type postActionType[T]            = postActionRequestType => PostType[T] forSome { type T }

        private def postParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
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

    val postActionConstructor  = Action

def postAction[T] = (f: postActionType[T]) => postActionConstructor(BodyParsers.parse.using(postParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { postResponseMimeType =>
            val root = request.body
            
            

                val result =
                        new PostValidator(root).errors match {
                            case e if e.isEmpty => processValidpostRequest(f)((root))(postResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidpostRequest[T](f: postActionType[T])(request: postActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PostType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with PostType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
