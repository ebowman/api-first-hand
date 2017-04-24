package i038_invalid_enum_members.yaml

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




//noinspection ScalaStyle
trait I038_invalid_enum_membersYamlBase extends Controller with PlayBodyParsing with I18nSupport with ValidationTranslator {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait GetType[T] extends ResultWrapper[T]
    def Get200(resultP: GetResponses200)(implicit writerP: String => Option[Writeable[GetResponses200]]) = success(new GetType[GetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Get200(resultF: Future[GetResponses200])(implicit writerP: String => Option[Writeable[GetResponses200]]) = resultF map { resultP => (new GetType[GetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getActionRequestType       = (Unit)
    private type getActionType[T]            = getActionRequestType => Future[GetType[T] forSome { type T }]


    val getActionConstructor  = Action

def getAction[T] = (f: getActionType[T]) => getActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetRequest(): Either[Result, Future[GetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f(()))
            apiFirstTempResultHolder
        }

            
            

            processValidgetRequest() match {
                case Left(l) => success(l)
                case Right(r: Future[GetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getResponseMimeType =>
                        import MissingDefaultWrites._
                        r.map(_.toResult(getResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
