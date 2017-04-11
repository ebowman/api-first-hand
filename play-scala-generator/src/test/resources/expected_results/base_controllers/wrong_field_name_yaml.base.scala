package wrong_field_name.yaml

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

import de.zalando.play.controllers.PlayPathBindables



import de.zalando.play.controllers.ResponseWriters



//noinspection ScalaStyle
trait Wrong_field_nameYamlBase extends Controller with PlayBodyParsing with I18nSupport with ValidationTranslator {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait GetType[T] extends ResultWrapper[T]
    def Get200(resultP: StatusAndCode)(implicit writerP: String => Option[Writeable[StatusAndCode]]) = success(new GetType[StatusAndCode] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Get200(resultF: Future[StatusAndCode])(implicit writerP: String => Option[Writeable[StatusAndCode]]) = resultF map { resultP => (new GetType[StatusAndCode] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getActionRequestType       = (GetOptCodes, GetCodes)
    private type getActionType[T]            = getActionRequestType => Future[GetType[T] forSome { type T }]


    val getActionConstructor  = Action

def getAction[T] = (f: getActionType[T]) => getActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetRequest(optCodes: GetOptCodes, codes: GetCodes): Either[Result, Future[GetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((optCodes, codes)))
            
            new GetValidator(optCodes, codes).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case parsingErrors: Seq[ParsingError] =>
                    import ResponseWriters.jsonTranslatedParsingErrorsContainerWrites
                    Left(BadRequest(Json.toJson(translateParsingErrors(parsingErrors))))
            }
            
          
        }

            
            val optCodes: Either[String,GetOptCodes] = fromParametersOptional[GetOptCodes]("header")("optCodes", request.headers.toMap, None)
            
            val codes: Either[String,GetCodes] = fromParameters[GetCodes]("header")("codes", request.headers.toMap, None)
            
            
                (optCodes, codes) match {
                    case (Right(optCodes), Right(codes)) =>
            

            processValidgetRequest(optCodes, codes) match {
                case Left(l) => success(l)
                case Right(r: Future[GetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
                case (_, _) =>
                    val problem: Seq[String] = Seq(optCodes, codes).filter{_.isLeft}.map(_.left.get)
                    val msg = problem.mkString("\n")
                    success(BadRequest(msg))
                }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
