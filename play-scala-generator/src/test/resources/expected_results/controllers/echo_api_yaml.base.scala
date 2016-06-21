package echo

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._

import scala.util._

import de.zalando.play.controllers.PlayPathBindables



import de.zalando.play.controllers.ResponseWriters




trait EchoHandlerBase extends Controller with PlayBodyParsing {
    sealed trait MethodType[T] extends ResultWrapper[T]
    
    case class Method200(headers: Seq[(String, String)] = Nil) extends EmptyReturn(200, headers)
    

    private type methodActionRequestType       = (Unit)
    private type methodActionType[T]            = methodActionRequestType => MethodType[T] forSome { type T }


    val methodActionConstructor  = Action

def methodAction[T] = (f: methodActionType[T]) => methodActionConstructor { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { methodResponseMimeType =>
            
            

                val result = processValidmethodRequest(f)()(methodResponseMimeType)
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidmethodRequest[T](f: methodActionType[T])(request: methodActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with MethodType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with MethodType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
trait EchoApiYamlBase extends Controller with PlayBodyParsing {
    sealed trait PostType[T] extends ResultWrapper[T]
    case class Post200(result: PostResponses200)(implicit val writer: String => Option[Writeable[PostResponses200]]) extends PostType[PostResponses200] { val statusCode = 200 }
    

    private type postActionRequestType       = (PostName, PostName)
    private type postActionType[T]            = postActionRequestType => PostType[T] forSome { type T }


    val postActionConstructor  = Action

def postAction[T] = (f: postActionType[T]) => postActionConstructor { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { postResponseMimeType =>
            
            val eitherFormParameters = FormDataParser.postParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val msg = problem.mkString("\n")
                    implicit val marshaller: Writeable[String] = anyToWritable(postResponseMimeType)
                    BadRequest(msg)

                case Right((name, year)) =>
            

                val result =
                        new PostValidator(name, year).errors match {
                            case e if e.isEmpty => processValidpostRequest(f)((name, year))(postResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postResponseMimeType)
                                BadRequest(l)
                        }
                result
            
            }
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidpostRequest[T](f: postActionType[T])(request: postActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait Gettest_pathByIdType[T] extends ResultWrapper[T]
    
    case class Gettest_pathById200(headers: Seq[(String, String)] = Nil) extends EmptyReturn(200, headers)
    

    private type gettest_pathByIdActionRequestType       = (String)
    private type gettest_pathByIdActionType[T]            = gettest_pathByIdActionRequestType => Gettest_pathByIdType[T] forSome { type T }


    val gettest_pathByIdActionConstructor  = Action

def gettest_pathByIdAction[T] = (f: gettest_pathByIdActionType[T]) => (id: String) => gettest_pathByIdActionConstructor { request =>
        val providedTypes = Seq[String]()

        negotiateContent(request.acceptedTypes, providedTypes).map { gettest_pathByIdResponseMimeType =>
            
            

                val result =
                        new Test_pathIdGetValidator(id).errors match {
                            case e if e.isEmpty => processValidgettest_pathByIdRequest(f)((id))(gettest_pathByIdResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gettest_pathByIdResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgettest_pathByIdRequest[T](f: gettest_pathByIdActionType[T])(request: gettest_pathByIdActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PostType[Result] with Gettest_pathByIdType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with PostType[Results.EmptyContent] with Gettest_pathByIdType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
