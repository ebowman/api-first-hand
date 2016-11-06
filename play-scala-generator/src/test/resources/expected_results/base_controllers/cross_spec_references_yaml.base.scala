package cross_spec_references.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import scala.math.BigInt






//noinspection ScalaStyle
trait Cross_spec_referencesYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait PostType[T] extends ResultWrapper[T]
    def Post200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new PostType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Post200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new PostType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postActionRequestType       = (ModelSchemaRoot)
    private type postActionType[T]            = postActionRequestType => Future[PostType[T] forSome { type T }]

        
        import MissingDefaultReads._
        
        val postParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val postActionConstructor  = Action

def postAction[T] = (f: postActionType[T]) => postActionConstructor.async(postParser) { implicit request: Request[ModelSchemaRoot] =>

        def processValidpostRequest(root: ModelSchemaRoot): Either[Result, Future[PostType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((root)))
            
            new PostValidator(root).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val root: ModelSchemaRoot = request.body
            
            

            processValidpostRequest(root) match {
                case Left(l) => success(l)
                case Right(r: Future[PostType[_] @unchecked]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { postResponseMimeType =>
                        import MissingDefaultWrites._
                        r.map(_.toResult(postResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PostType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with PostType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
