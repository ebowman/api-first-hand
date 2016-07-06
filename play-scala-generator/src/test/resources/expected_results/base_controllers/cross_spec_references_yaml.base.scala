package cross_spec_references.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import de.zalando.play.controllers.ArrayWrapper
import scala.math.BigInt



import de.zalando.play.controllers.ResponseWriters




//noinspection ScalaStyle
trait Cross_spec_referencesYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait PostType[T] extends ResultWrapper[T]
    def Post200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new PostType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Post200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new PostType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postActionRequestType       = (ModelSchemaRoot)
    private type postActionType[T]            = postActionRequestType => Future[PostType[T] forSome { type T }]

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

def postAction[T] = (f: postActionType[T]) => postActionConstructor.async(BodyParsers.parse.using(postParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { postResponseMimeType =>
            val root = request.body
            
            

                val result =
                        new PostValidator(root).errors match {
                            case e if e.isEmpty => processValidpostRequest(f)((root))(postResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpostRequest[T](f: postActionType[T])(request: postActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PostType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with PostType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
