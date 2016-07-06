package wrong_field_name.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._

import de.zalando.play.controllers.PlayPathBindables



import de.zalando.play.controllers.ResponseWriters




//noinspection ScalaStyle
trait Wrong_field_nameYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait GetType[T] extends ResultWrapper[T]
    def Get200(resultP: StatusAndCode)(implicit writerP: String => Option[Writeable[StatusAndCode]]) = success(new GetType[StatusAndCode] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Get200(resultF: Future[StatusAndCode])(implicit writerP: String => Option[Writeable[StatusAndCode]]) = resultF map { resultP => (new GetType[StatusAndCode] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getActionRequestType       = (GetOptCodes, GetCodes)
    private type getActionType[T]            = getActionRequestType => Future[GetType[T] forSome { type T }]


    val getActionConstructor  = Action

def getAction[T] = (f: getActionType[T]) => getActionConstructor.async { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { getResponseMimeType =>
            
            val optCodes: Either[String,GetOptCodes] =
            fromParametersOptional[GetOptCodes]("header")("optCodes", request.headers.toMap, None)
            
            val codes: Either[String,GetCodes] =
            fromParameters[GetCodes]("header")("codes", request.headers.toMap, None)
            
            
                (optCodes, codes) match {
                    case (Right(optCodes), Right(codes)) =>
            

                val result =
                        new GetValidator(optCodes, codes).errors match {
                            case e if e.isEmpty => processValidgetRequest(f)((optCodes, codes))(getResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getResponseMimeType)
                                success(BadRequest(l))
                        }
                result
                case (_, _) =>
                    val problem: Seq[String] = Seq(optCodes, codes).filter{_.isLeft}.map(_.left.get)
                    val msg = problem.mkString("\n")
                    success(BadRequest(msg))
                }
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetRequest[T](f: getActionType[T])(request: getActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
