package security.api.yaml

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
import de.zalando.play.controllers.ArrayWrapper

import de.zalando.play.controllers.PlayPathBindables




//noinspection ScalaStyle
trait SecurityApiYamlBase extends Controller with PlayBodyParsing with I18nSupport with ValidationTranslator  with SecurityApiYamlSecurity {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait GetPetsByIdType[T] extends ResultWrapper[T]
    def GetPetsById200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new GetPetsByIdType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetPetsById200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new GetPetsByIdType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getPetsByIdActionRequestType       = (PetsIdGetId)
    private type getPetsByIdActionType[T]            = getPetsByIdActionRequestType => Future[GetPetsByIdType[T] forSome { type T }]


    val getPetsByIdActionConstructor  = new getPetsByIdSecureAction("user")

def getPetsByIdAction[T] = (f: getPetsByIdActionType[T]) => (id: PetsIdGetId) => getPetsByIdActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetPetsByIdRequest(id: PetsIdGetId): Either[Result, Future[GetPetsByIdType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((id)))
            
            new PetsIdGetValidator(id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case parsingErrors: Seq[ParsingError] =>
                    import ResponseWriters.jsonTranslatedParsingErrorsContainerWrites
                    Left(BadRequest(Json.toJson(translateParsingErrors(parsingErrors))))
            }
            
          
        }

            
            

            processValidgetPetsByIdRequest(id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetPetsByIdType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "text/html")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getPetsByIdResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getPetsByIdResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetPetsByIdType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetPetsByIdType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
