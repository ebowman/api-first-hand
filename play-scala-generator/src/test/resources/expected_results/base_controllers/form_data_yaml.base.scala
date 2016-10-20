package form_data.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
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
    sealed trait PostmultipartType[T] extends ResultWrapper[T]
    def Postmultipart200(resultP: MultipartPostResponses200)(implicit writerP: String => Option[Writeable[MultipartPostResponses200]]) = success(new PostmultipartType[MultipartPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Postmultipart200(resultF: Future[MultipartPostResponses200])(implicit writerP: String => Option[Writeable[MultipartPostResponses200]]) = resultF map { resultP => (new PostmultipartType[MultipartPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postmultipartActionRequestType       = (String, BothPostYear, MultipartPostAvatar)
    private type postmultipartActionType[T]            = postmultipartActionRequestType => Future[PostmultipartType[T] forSome { type T }]


    val postmultipartActionConstructor  = Action

def postmultipartAction[T] = (f: postmultipartActionType[T]) => postmultipartActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidpostmultipartRequest(name: String, year: BothPostYear, avatar: MultipartPostAvatar): Either[Result, Future[PostmultipartType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((name, year, avatar)))
            
            new MultipartPostValidator(name, year, avatar).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            val eitherFormParameters = FormDataParser.postmultipartParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { (postmultipartResponseMimeType =>
                        val msg = problem.mkString("\n")
                        implicit val marshaller: Writeable[String] = anyToWritable(postmultipartResponseMimeType)
                        success(BadRequest(msg))
                    }
                    result.getOrElse(success(Results.NotAcceptable))

                case Right((name, year, avatar)) =>
            

            processValidpostmultipartRequest(name, year, avatar) match {
                case Left(l) => success(l)
                case Right(r: Future[PostmultipartType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { postmultipartResponseMimeType =>
                        import MissingDefaultWrites._
                        r.map(_.toResult(postmultipartResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
            }
        
    }

    sealed trait Posturl_encodedType[T] extends ResultWrapper[T]
    def Posturl_encoded200(resultP: MultipartPostResponses200)(implicit writerP: String => Option[Writeable[MultipartPostResponses200]]) = success(new Posturl_encodedType[MultipartPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Posturl_encoded200(resultF: Future[MultipartPostResponses200])(implicit writerP: String => Option[Writeable[MultipartPostResponses200]]) = resultF map { resultP => (new Posturl_encodedType[MultipartPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type posturl_encodedActionRequestType       = (String, BothPostYear, File)
    private type posturl_encodedActionType[T]            = posturl_encodedActionRequestType => Future[Posturl_encodedType[T] forSome { type T }]


    val posturl_encodedActionConstructor  = Action

def posturl_encodedAction[T] = (f: posturl_encodedActionType[T]) => posturl_encodedActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidposturl_encodedRequest(name: String, year: BothPostYear, avatar: File): Either[Result, Future[Posturl_encodedType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((name, year, avatar)))
            
            new Url_encodedPostValidator(name, year, avatar).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            val eitherFormParameters = FormDataParser.posturl_encodedParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { (posturl_encodedResponseMimeType =>
                        val msg = problem.mkString("\n")
                        implicit val marshaller: Writeable[String] = anyToWritable(posturl_encodedResponseMimeType)
                        success(BadRequest(msg))
                    }
                    result.getOrElse(success(Results.NotAcceptable))

                case Right((name, year, avatar)) =>
            

            processValidposturl_encodedRequest(name, year, avatar) match {
                case Left(l) => success(l)
                case Right(r: Future[Posturl_encodedType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { posturl_encodedResponseMimeType =>
                        import MissingDefaultWrites._
                        r.map(_.toResult(posturl_encodedResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
            }
        
    }

    sealed trait PostbothType[T] extends ResultWrapper[T]
    def Postboth200(resultP: BothPostResponses200)(implicit writerP: String => Option[Writeable[BothPostResponses200]]) = success(new PostbothType[BothPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Postboth200(resultF: Future[BothPostResponses200])(implicit writerP: String => Option[Writeable[BothPostResponses200]]) = resultF map { resultP => (new PostbothType[BothPostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postbothActionRequestType       = (String, BothPostYear, MultipartPostAvatar, File)
    private type postbothActionType[T]            = postbothActionRequestType => Future[PostbothType[T] forSome { type T }]


    val postbothActionConstructor  = Action

def postbothAction[T] = (f: postbothActionType[T]) => postbothActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidpostbothRequest(name: String, year: BothPostYear, avatar: MultipartPostAvatar, ringtone: File): Either[Result, Future[PostbothType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((name, year, avatar, ringtone)))
            
            new BothPostValidator(name, year, avatar, ringtone).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            val eitherFormParameters = FormDataParser.postbothParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { (postbothResponseMimeType =>
                        val msg = problem.mkString("\n")
                        implicit val marshaller: Writeable[String] = anyToWritable(postbothResponseMimeType)
                        success(BadRequest(msg))
                    }
                    result.getOrElse(success(Results.NotAcceptable))

                case Right((name, year, avatar, ringtone)) =>
            

            processValidpostbothRequest(name, year, avatar, ringtone) match {
                case Left(l) => success(l)
                case Right(r: Future[PostbothType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { postbothResponseMimeType =>
                        import MissingDefaultWrites._
                        r.map(_.toResult(postbothResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
            }
        
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PostmultipartType[Result] with Posturl_encodedType[Result] with PostbothType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with PostmultipartType[Results.EmptyContent] with Posturl_encodedType[Results.EmptyContent] with PostbothType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
