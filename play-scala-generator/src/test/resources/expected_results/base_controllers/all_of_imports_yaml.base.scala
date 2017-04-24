package all_of_imports.yaml

import scala.language.existentials
import play.api.i18n.{I18nSupport, Messages}
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import java.time.ZonedDateTime




//noinspection ScalaStyle
trait All_of_importsYamlBase extends Controller with PlayBodyParsing with I18nSupport with ValidationTranslator {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait PostType[T] extends ResultWrapper[T]
    def Post200(resultP: DatetimeValue)(implicit writerP: String => Option[Writeable[DatetimeValue]]) = success(new PostType[DatetimeValue] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Post200(resultF: Future[DatetimeValue])(implicit writerP: String => Option[Writeable[DatetimeValue]]) = resultF map { resultP => (new PostType[DatetimeValue] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def Post400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    

    private type postActionRequestType       = (DatetimeValue)
    private type postActionType[T]            = postActionRequestType => Future[PostType[T] forSome { type T }]

        
        import BodyReads._
        
        val postParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.as[DatetimeValue])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val postActionConstructor  = Action

def postAction[T] = (f: postActionType[T]) => postActionConstructor.async(postParser) { implicit request: Request[DatetimeValue] =>

        def processValidpostRequest(body: DatetimeValue): Either[Result, Future[PostType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((body)))
            
            new PostValidator(body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case parsingErrors: Seq[ParsingError] =>
                    import ResponseWriters.jsonTranslatedParsingErrorsContainerWrites
                    Left(BadRequest(Json.toJson(translateParsingErrors(parsingErrors))))
            }
            
          
        }

            val body: DatetimeValue = request.body
            
            

            processValidpostRequest(body) match {
                case Left(l) => success(l)
                case Right(r: Future[PostType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { postResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(postResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PostType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with PostType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
