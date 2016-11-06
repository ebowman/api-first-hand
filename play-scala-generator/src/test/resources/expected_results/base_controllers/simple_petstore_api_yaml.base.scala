package simple_petstore_api_yaml

import scala.language.existentials
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
trait SimplePetstoreApiYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait AddPetType[T] extends ResultWrapper[T]
    def AddPet200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new AddPetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def AddPet200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new AddPetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type addPetActionRequestType       = (NewPet)
    private type addPetActionType[T]            = addPetActionRequestType => Future[AddPetType[T] forSome { type T }]

        
        import BodyReads._
        
        val addPetParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.as[NewPet])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val addPetActionConstructor  = Action

def addPetAction[T] = (f: addPetActionType[T]) => addPetActionConstructor.async(addPetParser) { implicit request: Request[NewPet] =>

        def processValidaddPetRequest(pet: NewPet): Either[Result, Future[AddPetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((pet)))
            
            new PetsPostValidator(pet).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val pet: NewPet = request.body
            
            

            processValidaddPetRequest(pet) match {
                case Left(l) => success(l)
                case Right(r: Future[AddPetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { addPetResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(addPetResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with AddPetType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with AddPetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
//noinspection ScalaStyle
trait DashboardBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait MethodLevelType[T] extends ResultWrapper[T]
    def MethodLevel200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new MethodLevelType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def MethodLevel200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new MethodLevelType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type methodLevelActionRequestType       = (PetsGetTags, PetsGetLimit)
    private type methodLevelActionType[T]            = methodLevelActionRequestType => Future[MethodLevelType[T] forSome { type T }]


    val methodLevelActionConstructor  = Action

def methodLevelAction[T] = (f: methodLevelActionType[T]) => (tags: PetsGetTags, limit: PetsGetLimit) => methodLevelActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidmethodLevelRequest(tags: PetsGetTags, limit: PetsGetLimit): Either[Result, Future[MethodLevelType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((tags, limit)))
            
            new PetsGetValidator(tags, limit).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidmethodLevelRequest(tags, limit) match {
                case Left(l) => success(l)
                case Right(r: Future[MethodLevelType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml", "text/xml", "text/html")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { methodLevelResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(methodLevelResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PathLevelGetType[T] extends ResultWrapper[T]
    def PathLevelGet200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new PathLevelGetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def PathLevelGet200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new PathLevelGetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type pathLevelGetActionRequestType       = (Long)
    private type pathLevelGetActionType[T]            = pathLevelGetActionRequestType => Future[PathLevelGetType[T] forSome { type T }]


    val pathLevelGetActionConstructor  = Action

def pathLevelGetAction[T] = (f: pathLevelGetActionType[T]) => (id: Long) => pathLevelGetActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidpathLevelGetRequest(id: Long): Either[Result, Future[PathLevelGetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((id)))
            
            new PetsIdGetValidator(id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidpathLevelGetRequest(id) match {
                case Left(l) => success(l)
                case Right(r: Future[PathLevelGetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml", "text/xml", "text/html")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { pathLevelGetResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(pathLevelGetResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PathLevelDeleteType[T] extends ResultWrapper[T]
    
    def PathLevelDelete204(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(204, headers){})
    

    private type pathLevelDeleteActionRequestType       = (Long)
    private type pathLevelDeleteActionType[T]            = pathLevelDeleteActionRequestType => Future[PathLevelDeleteType[T] forSome { type T }]


    val pathLevelDeleteActionConstructor  = Action

def pathLevelDeleteAction[T] = (f: pathLevelDeleteActionType[T]) => (id: Long) => pathLevelDeleteActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidpathLevelDeleteRequest(id: Long): Either[Result, Future[PathLevelDeleteType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((id)))
            
            new PetsIdDeleteValidator(id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidpathLevelDeleteRequest(id) match {
                case Left(l) => success(l)
                case Right(r: Future[PathLevelDeleteType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { pathLevelDeleteResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(pathLevelDeleteResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with MethodLevelType[Result] with PathLevelGetType[Result] with PathLevelDeleteType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with MethodLevelType[Results.EmptyContent] with PathLevelGetType[Results.EmptyContent] with PathLevelDeleteType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
