package basic_polymorphism.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import de.zalando.play.controllers.ArrayWrapper



import de.zalando.play.controllers.ResponseWriters




//noinspection ScalaStyle
trait Basic_polymorphismYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait PutType[T] extends ResultWrapper[T]
    
    def Put200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type putActionRequestType       = (PutDummy)
    private type putActionType[T]            = putActionRequestType => Future[PutType[T] forSome { type T }]

        private def putParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            import de.zalando.play.controllers.WrappedBodyParsers
            
            val customParsers = WrappedBodyParsers.optionParser[Pet]
            optionParser[Pet](bodyMimeType, customParsers, "Invalid PutDummy", maxLength) _
        }

    val putActionConstructor  = Action

def putAction[T] = (f: putActionType[T]) => putActionConstructor.async(BodyParsers.parse.using(putParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { putResponseMimeType =>
            val dummy = request.body
            
            

                val result =
                        new PutValidator(dummy).errors match {
                            case e if e.isEmpty => processValidputRequest(f)((dummy))(putResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(putResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidputRequest[T](f: putActionType[T])(request: putActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PutType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with PutType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
