package form_data.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import java.io.File
import scala.math.BigInt

import de.zalando.play.controllers.PlayPathBindables





//noinspection ScalaStyle
trait Form_dataYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait PostmultipartType[T] extends ResultWrapper[T]
    def Postmultipart200(resultP: MultipartPostResponses200)(implicit writerP: String => Option[Writeable[MultipartPostResponses200]]) = success(new PostmultipartType[MultipartPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Postmultipart200(resultF: Future[MultipartPostResponses200])(implicit writerP: String => Option[Writeable[MultipartPostResponses200]]) = resultF map { resultP => (new PostmultipartType[MultipartPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postmultipartActionRequestType       = (String, BothPostYear, MultipartPostAvatar)
    private type postmultipartActionType[T]            = postmultipartActionRequestType => Future[PostmultipartType[T] forSome { type T }]


    val postmultipartActionConstructor  = Action

def postmultipartAction[T] = (f: postmultipartActionType[T]) => postmultipartActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { postmultipartResponseMimeType =>
            
            val eitherFormParameters = FormDataParser.postmultipartParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val msg = problem.mkString("\n")
                    implicit val marshaller: Writeable[String] = anyToWritable(postmultipartResponseMimeType)
                    success(BadRequest(msg))

                case Right((name, year, avatar)) =>
            

                val result =
                        new MultipartPostValidator(name, year, avatar).errors match {
                            case e if e.isEmpty => processValidpostmultipartRequest(f)((name, year, avatar))(postmultipartResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postmultipartResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
            }
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpostmultipartRequest[T](f: postmultipartActionType[T])(request: postmultipartActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait Posturl_encodedType[T] extends ResultWrapper[T]
    def Posturl_encoded200(resultP: MultipartPostResponses200)(implicit writerP: String => Option[Writeable[MultipartPostResponses200]]) = success(new Posturl_encodedType[MultipartPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Posturl_encoded200(resultF: Future[MultipartPostResponses200])(implicit writerP: String => Option[Writeable[MultipartPostResponses200]]) = resultF map { resultP => (new Posturl_encodedType[MultipartPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type posturl_encodedActionRequestType       = (String, BothPostYear, File)
    private type posturl_encodedActionType[T]            = posturl_encodedActionRequestType => Future[Posturl_encodedType[T] forSome { type T }]


    val posturl_encodedActionConstructor  = Action

def posturl_encodedAction[T] = (f: posturl_encodedActionType[T]) => posturl_encodedActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { posturl_encodedResponseMimeType =>
            
            val eitherFormParameters = FormDataParser.posturl_encodedParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val msg = problem.mkString("\n")
                    implicit val marshaller: Writeable[String] = anyToWritable(posturl_encodedResponseMimeType)
                    success(BadRequest(msg))

                case Right((name, year, avatar)) =>
            

                val result =
                        new Url_encodedPostValidator(name, year, avatar).errors match {
                            case e if e.isEmpty => processValidposturl_encodedRequest(f)((name, year, avatar))(posturl_encodedResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(posturl_encodedResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
            }
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidposturl_encodedRequest[T](f: posturl_encodedActionType[T])(request: posturl_encodedActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PostbothType[T] extends ResultWrapper[T]
    def Postboth200(resultP: BothPostResponses200)(implicit writerP: String => Option[Writeable[BothPostResponses200]]) = success(new PostbothType[BothPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Postboth200(resultF: Future[BothPostResponses200])(implicit writerP: String => Option[Writeable[BothPostResponses200]]) = resultF map { resultP => (new PostbothType[BothPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postbothActionRequestType       = (String, BothPostYear, MultipartPostAvatar, File)
    private type postbothActionType[T]            = postbothActionRequestType => Future[PostbothType[T] forSome { type T }]


    val postbothActionConstructor  = Action

def postbothAction[T] = (f: postbothActionType[T]) => postbothActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { postbothResponseMimeType =>
            
            val eitherFormParameters = FormDataParser.postbothParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val msg = problem.mkString("\n")
                    implicit val marshaller: Writeable[String] = anyToWritable(postbothResponseMimeType)
                    success(BadRequest(msg))

                case Right((name, year, avatar, ringtone)) =>
            

                val result =
                        new BothPostValidator(name, year, avatar, ringtone).errors match {
                            case e if e.isEmpty => processValidpostbothRequest(f)((name, year, avatar, ringtone))(postbothResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postbothResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
            }
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpostbothRequest[T](f: postbothActionType[T])(request: postbothActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PostmultipartType[Result] with Posturl_encodedType[Result] with PostbothType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with PostmultipartType[Results.EmptyContent] with Posturl_encodedType[Results.EmptyContent] with PostbothType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
