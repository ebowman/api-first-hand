package echo

import scala.language.existentials
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
trait EchoHandlerBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait MethodType[T] extends ResultWrapper[T]
    
    def Method200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type methodActionRequestType       = (Unit)
    private type methodActionType[T]            = methodActionRequestType => Future[MethodType[T] forSome { type T }]


    val methodActionConstructor  = Action

def methodAction[T] = (f: methodActionType[T]) => methodActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidmethodRequest(): Either[Result, Future[MethodType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f(()))
            apiFirstTempResultHolder
        }

            
            

            processValidmethodRequest() match {
                case Left(l) => success(l)
                case Right(r: Future[MethodType[_] @unchecked]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { methodResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(methodResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with MethodType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with MethodType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
//noinspection ScalaStyle
trait EchoApiYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait PostType[T] extends ResultWrapper[T]
    def Post200(resultP: PostResponses200)(implicit writerP: String => Option[Writeable[PostResponses200]]) = success(new PostType[PostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Post200(resultF: Future[PostResponses200])(implicit writerP: String => Option[Writeable[PostResponses200]]) = resultF map { resultP => (new PostType[PostResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postActionRequestType       = (Option[String], Option[String])
    private type postActionType[T]            = postActionRequestType => Future[PostType[T] forSome { type T }]


    val postActionConstructor  = Action

def postAction[T] = (f: postActionType[T]) => postActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidpostRequest(name: Option[String], year: Option[String]): Either[Result, Future[PostType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((name, year)))
            
            new PostValidator(name, year).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            val eitherFormParameters = FormDataParser.postParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { postResponseMimeType =>
                        val msg = problem.mkString("\n")
                        implicit val marshaller: Writeable[String] = anyToWritable(postResponseMimeType)
                        success(BadRequest(msg))
                    }
                    result.getOrElse(success(Results.NotAcceptable))

                case Right((name, year)) =>
            

            processValidpostRequest(name, year) match {
                case Left(l) => success(l)
                case Right(r: Future[PostType[_] @unchecked]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { postResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(postResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
            }
        
    }

    sealed trait Gettest_pathByIdType[T] extends ResultWrapper[T]
    
    def Gettest_pathById200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type gettest_pathByIdActionRequestType       = (String)
    private type gettest_pathByIdActionType[T]            = gettest_pathByIdActionRequestType => Future[Gettest_pathByIdType[T] forSome { type T }]


    val gettest_pathByIdActionConstructor  = Action

def gettest_pathByIdAction[T] = (f: gettest_pathByIdActionType[T]) => (id: String) => gettest_pathByIdActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgettest_pathByIdRequest(id: String): Either[Result, Future[Gettest_pathByIdType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((id)))
            
            new Test_pathIdGetValidator(id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgettest_pathByIdRequest(id) match {
                case Left(l) => success(l)
                case Right(r: Future[Gettest_pathByIdType[_] @unchecked]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { gettest_pathByIdResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(gettest_pathByIdResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with PostType[Result] with Gettest_pathByIdType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with PostType[Results.EmptyContent] with Gettest_pathByIdType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
