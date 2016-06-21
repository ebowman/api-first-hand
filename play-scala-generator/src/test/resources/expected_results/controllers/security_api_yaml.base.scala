package security.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._

import scala.util._
import de.zalando.play.controllers.ArrayWrapper

import de.zalando.play.controllers.PlayPathBindables





trait SecurityApiYamlBase extends Controller with PlayBodyParsing  with SecurityApiYamlSecurity {
    sealed trait GetPetsByIdType[T] extends ResultWrapper[T]
    case class GetPetsById200(result: Seq[Pet])(implicit val writer: String => Option[Writeable[Seq[Pet]]]) extends GetPetsByIdType[Seq[Pet]] { val statusCode = 200 }
    

    private type getPetsByIdActionRequestType       = (PetsIdGetId)
    private type getPetsByIdActionType[T]            = getPetsByIdActionRequestType => GetPetsByIdType[T] forSome { type T }


    val getPetsByIdActionConstructor  = new getPetsByIdSecureAction("user")

def getPetsByIdAction[T] = (f: getPetsByIdActionType[T]) => (id: PetsIdGetId) => getPetsByIdActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "text/html")

        negotiateContent(request.acceptedTypes, providedTypes).map { getPetsByIdResponseMimeType =>
            
            

                val result =
                        new PetsIdGetValidator(id).errors match {
                            case e if e.isEmpty => processValidgetPetsByIdRequest(f)((id))(getPetsByIdResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getPetsByIdResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetPetsByIdRequest[T](f: getPetsByIdActionType[T])(request: getPetsByIdActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetPetsByIdType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with GetPetsByIdType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
