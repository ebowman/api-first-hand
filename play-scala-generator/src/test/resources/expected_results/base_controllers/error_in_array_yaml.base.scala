package error_in_array.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import scala.math.BigInt



import de.zalando.play.controllers.ResponseWriters




//noinspection ScalaStyle
trait Error_in_arrayYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait GetschemaModelType[T] extends ResultWrapper[T]
    def GetschemaModel200(resultP: ModelSchemaRoot)(implicit writerP: String => Option[Writeable[ModelSchemaRoot]]) = success(new GetschemaModelType[ModelSchemaRoot] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetschemaModel200(resultF: Future[ModelSchemaRoot])(implicit writerP: String => Option[Writeable[ModelSchemaRoot]]) = resultF map { resultP => (new GetschemaModelType[ModelSchemaRoot] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getschemaModelActionRequestType       = (ModelSchemaRoot)
    private type getschemaModelActionType[T]            = getschemaModelActionRequestType => Future[GetschemaModelType[T] forSome { type T }]

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

def getschemaModelAction[T] = (f: getschemaModelActionType[T]) => getschemaModelActionConstructor.async(BodyParsers.parse.using(getschemaModelParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { getschemaModelResponseMimeType =>
            val root = request.body
            
            

                val result =
                        new SchemaModelGetValidator(root).errors match {
                            case e if e.isEmpty => processValidgetschemaModelRequest(f)((root))(getschemaModelResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getschemaModelResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetschemaModelRequest[T](f: getschemaModelActionType[T])(request: getschemaModelActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetschemaModelType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetschemaModelType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
