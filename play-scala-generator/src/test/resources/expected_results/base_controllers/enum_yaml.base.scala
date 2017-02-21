package enum.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._

import de.zalando.play.controllers.PlayPathBindables



import de.zalando.play.controllers.ResponseWriters



//noinspection ScalaStyle
trait EnumYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait GettestType[T] extends ResultWrapper[T]
    def Gettest200(resultP: String)(implicit writerP: String => Option[Writeable[String]]) = success(new GettestType[String] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Gettest200(resultF: Future[String])(implicit writerP: String => Option[Writeable[String]]) = resultF map { resultP => (new GettestType[String] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type gettestActionRequestType       = (Option[TestGetIncludesOptionEnum])
    private type gettestActionType[T]            = gettestActionRequestType => Future[GettestType[T] forSome { type T }]


    val gettestActionConstructor  = Action

def gettestAction[T] = (f: gettestActionType[T]) => (includes: Option[TestGetIncludesOptionEnum]) => gettestActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgettestRequest(includes: Option[TestGetIncludesOptionEnum]): Either[Result, Future[GettestType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((includes)))
            
            new TestGetValidator(includes).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgettestRequest(includes) match {
                case Left(l) => success(l)
                case Right(r: Future[GettestType[_] @unchecked]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { gettestResponseMimeType =>
                        import MissingDefaultWrites._
                        r.map(_.toResult(gettestResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GettestType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GettestType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
