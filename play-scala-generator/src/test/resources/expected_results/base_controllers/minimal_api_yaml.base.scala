package admin

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._



import de.zalando.play.controllers.ResponseWriters




//noinspection ScalaStyle
trait DashboardBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait IndexType[T] extends ResultWrapper[T]
    
    def Index200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type indexActionRequestType       = (Unit)
    private type indexActionType[T]            = indexActionRequestType => Future[IndexType[T] forSome { type T }]


    val indexActionConstructor  = Action

def indexAction[T] = (f: indexActionType[T]) => indexActionConstructor.async { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { indexResponseMimeType =>
            
            

                val result = processValidindexRequest(f)()(indexResponseMimeType)
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidindexRequest[T](f: indexActionType[T])(request: indexActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with IndexType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with IndexType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
