package i041_no_json_deserialiser.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import java.time.ZonedDateTime
import scala.math.BigDecimal
import scala.math.BigInt

import de.zalando.play.controllers.PlayPathBindables




//noinspection ScalaStyle
trait I041_no_json_deserialiserYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait ListUserType[T] extends ResultWrapper[T]
    def ListUser200(resultP: Seq[User])(implicit writerP: String => Option[Writeable[Seq[User]]]) = success(new ListUserType[Seq[User]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def ListUser200(resultF: Future[Seq[User]])(implicit writerP: String => Option[Writeable[Seq[User]]]) = resultF map { resultP => (new ListUserType[Seq[User]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type listUserActionRequestType       = (Unit)
    private type listUserActionType[T]            = listUserActionRequestType => Future[ListUserType[T] forSome { type T }]


    val listUserActionConstructor  = Action

def listUserAction[T] = (f: listUserActionType[T]) => listUserActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidlistUserRequest(): Either[Result, Future[ListUserType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f(()))
            apiFirstTempResultHolder
        }

            
            

            processValidlistUserRequest() match {
                case Left(l) => success(l)
                case Right(r: Future[ListUserType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { listUserResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(listUserResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait CreateUserType[T] extends ResultWrapper[T]
    def CreateUser200(resultP: User)(implicit writerP: String => Option[Writeable[User]]) = success(new CreateUserType[User] { val statusCode = 200; val result = resultP; val writer = writerP })
    def CreateUser200(resultF: Future[User])(implicit writerP: String => Option[Writeable[User]]) = resultF map { resultP => (new CreateUserType[User] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type createUserActionRequestType       = (String)
    private type createUserActionType[T]            = createUserActionRequestType => Future[CreateUserType[T] forSome { type T }]


    val createUserActionConstructor  = Action

def createUserAction[T] = (f: createUserActionType[T]) => (name: String) => createUserActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidcreateUserRequest(name: String): Either[Result, Future[CreateUserType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((name)))
            
            new UserPostValidator(name).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidcreateUserRequest(name) match {
                case Left(l) => success(l)
                case Right(r: Future[CreateUserType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { createUserResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(createUserResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait ShowUserByIdType[T] extends ResultWrapper[T]
    def ShowUserById200(resultP: User)(implicit writerP: String => Option[Writeable[User]]) = success(new ShowUserByIdType[User] { val statusCode = 200; val result = resultP; val writer = writerP })
    def ShowUserById200(resultF: Future[User])(implicit writerP: String => Option[Writeable[User]]) = resultF map { resultP => (new ShowUserByIdType[User] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type showUserByIdActionRequestType       = (BigInt)
    private type showUserByIdActionType[T]            = showUserByIdActionRequestType => Future[ShowUserByIdType[T] forSome { type T }]


    val showUserByIdActionConstructor  = Action

def showUserByIdAction[T] = (f: showUserByIdActionType[T]) => (id: BigInt) => showUserByIdActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidshowUserByIdRequest(id: BigInt): Either[Result, Future[ShowUserByIdType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((id)))
            
            new UserIdGetValidator(id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidshowUserByIdRequest(id) match {
                case Left(l) => success(l)
                case Right(r: Future[ShowUserByIdType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { showUserByIdResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(showUserByIdResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutUserType[T] extends ResultWrapper[T]
    def PutUser200(resultP: User)(implicit writerP: String => Option[Writeable[User]]) = success(new PutUserType[User] { val statusCode = 200; val result = resultP; val writer = writerP })
    def PutUser200(resultF: Future[User])(implicit writerP: String => Option[Writeable[User]]) = resultF map { resultP => (new PutUserType[User] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type putUserActionRequestType       = (BigInt, User)
    private type putUserActionType[T]            = putUserActionRequestType => Future[PutUserType[T] forSome { type T }]

        
        import BodyReads._
        
        val putUserParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.as[User])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val putUserActionConstructor  = Action

def putUserAction[T] = (f: putUserActionType[T]) => (id: BigInt) => putUserActionConstructor.async(putUserParser) { implicit request: Request[User] =>

        def processValidputUserRequest(id: BigInt, body: User): Either[Result, Future[PutUserType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((id, body)))
            
            new UserIdPutValidator(id, body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val body: User = request.body
            
            

            processValidputUserRequest(id, body) match {
                case Left(l) => success(l)
                case Right(r: Future[PutUserType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putUserResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putUserResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with ListUserType[Result] with CreateUserType[Result] with ShowUserByIdType[Result] with PutUserType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with ListUserType[Results.EmptyContent] with CreateUserType[Results.EmptyContent] with ShowUserByIdType[Results.EmptyContent] with PutUserType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
