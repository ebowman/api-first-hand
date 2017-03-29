package required_parametrs.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._






//noinspection ScalaStyle
trait Required_parametrsYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait GetType[T] extends ResultWrapper[T]
    
    def Get200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type getActionRequestType       = (String, GetTest2)
    private type getActionType[T]            = getActionRequestType => Future[GetType[T] forSome { type T }]

        
        import MissingDefaultReads._
        
        val getParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val getActionConstructor  = Action

def getAction[T] = (f: getActionType[T]) => (test1: String) => getActionConstructor.async(getParser) { implicit request: Request[GetTest2] =>

        def processValidgetRequest(test1: String, test2: GetTest2): Either[Result, Future[GetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((test1, test2)))
            
            new GetValidator(test1, test2).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val test2: GetTest2 = request.body
            
            

            processValidgetRequest(test1, test2) match {
                case Left(l) => success(l)
                case Right(r: Future[GetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]()
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
