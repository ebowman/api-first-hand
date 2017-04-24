package error_in_array.yaml

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
import scala.math.BigInt






//noinspection ScalaStyle
trait Error_in_arrayYamlBase extends Controller with PlayBodyParsing with I18nSupport with ValidationTranslator {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait GetschemaModelType[T] extends ResultWrapper[T]
    def GetschemaModel200(resultP: ModelSchemaRoot)(implicit writerP: String => Option[Writeable[ModelSchemaRoot]]) = success(new GetschemaModelType[ModelSchemaRoot] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetschemaModel200(resultF: Future[ModelSchemaRoot])(implicit writerP: String => Option[Writeable[ModelSchemaRoot]]) = resultF map { resultP => (new GetschemaModelType[ModelSchemaRoot] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getschemaModelActionRequestType       = (ModelSchemaRoot)
    private type getschemaModelActionType[T]            = getschemaModelActionRequestType => Future[GetschemaModelType[T] forSome { type T }]

        
        import BodyReads._
        
        val getschemaModelParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val getschemaModelActionConstructor  = Action

def getschemaModelAction[T] = (f: getschemaModelActionType[T]) => getschemaModelActionConstructor.async(getschemaModelParser) { implicit request: Request[ModelSchemaRoot] =>

        def processValidgetschemaModelRequest(root: ModelSchemaRoot): Either[Result, Future[GetschemaModelType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((root)))
            
            new SchemaModelGetValidator(root).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case parsingErrors: Seq[ParsingError] =>
                    import ResponseWriters.jsonTranslatedParsingErrorsContainerWrites
                    Left(BadRequest(Json.toJson(translateParsingErrors(parsingErrors))))
            }
            
          
        }

            val root: ModelSchemaRoot = request.body
            
            

            processValidgetschemaModelRequest(root) match {
                case Left(l) => success(l)
                case Right(r: Future[GetschemaModelType[_] @unchecked]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getschemaModelResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getschemaModelResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetschemaModelType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetschemaModelType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
