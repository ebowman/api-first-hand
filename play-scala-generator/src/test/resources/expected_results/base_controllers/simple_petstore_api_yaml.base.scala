package simple_petstore_api_yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
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
    def success[T](t: => T) = Future.successful(t)
    sealed trait AddPetType[T] extends ResultWrapper[T]
    def AddPet200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new AddPetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def AddPet200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new AddPetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type addPetActionRequestType       = (NewPet)
    private type addPetActionType[T]            = addPetActionRequestType => Future[AddPetType[T] forSome { type T }]

        private def addPetParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.anyParser[NewPet]
            anyParser[NewPet](bodyMimeType, customParsers, "Invalid NewPet", maxLength) _
        }

    val addPetActionConstructor  = Action

def addPetAction[T] = (f: addPetActionType[T]) => addPetActionConstructor.async(BodyParsers.parse.using(addPetParser(Seq[String]("application/json")))) { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { addPetResponseMimeType =>
            val pet = request.body
            
            

                val result =
                        new PetsPostValidator(pet).errors match {
                            case e if e.isEmpty => processValidaddPetRequest(f)((pet))(addPetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(addPetResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidaddPetRequest[T](f: addPetActionType[T])(request: addPetActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with AddPetType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with AddPetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
//noinspection ScalaStyle
trait DashboardBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait MethodLevelType[T] extends ResultWrapper[T]
    def MethodLevel200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new MethodLevelType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def MethodLevel200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new MethodLevelType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type methodLevelActionRequestType       = (PetsGetTags, PetsGetLimit)
    private type methodLevelActionType[T]            = methodLevelActionRequestType => Future[MethodLevelType[T] forSome { type T }]


    val methodLevelActionConstructor  = Action

def methodLevelAction[T] = (f: methodLevelActionType[T]) => (tags: PetsGetTags, limit: PetsGetLimit) => methodLevelActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml", "text/xml", "text/html")

        negotiateContent(request.acceptedTypes, providedTypes).map { methodLevelResponseMimeType =>
            
            

                val result =
                        new PetsGetValidator(tags, limit).errors match {
                            case e if e.isEmpty => processValidmethodLevelRequest(f)((tags, limit))(methodLevelResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(methodLevelResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidmethodLevelRequest[T](f: methodLevelActionType[T])(request: methodLevelActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PathLevelGetType[T] extends ResultWrapper[T]
    def PathLevelGet200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new PathLevelGetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def PathLevelGet200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new PathLevelGetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type pathLevelGetActionRequestType       = (Long)
    private type pathLevelGetActionType[T]            = pathLevelGetActionRequestType => Future[PathLevelGetType[T] forSome { type T }]


    val pathLevelGetActionConstructor  = Action

def pathLevelGetAction[T] = (f: pathLevelGetActionType[T]) => (id: Long) => pathLevelGetActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml", "text/xml", "text/html")

        negotiateContent(request.acceptedTypes, providedTypes).map { pathLevelGetResponseMimeType =>
            
            

                val result =
                        new PetsIdGetValidator(id).errors match {
                            case e if e.isEmpty => processValidpathLevelGetRequest(f)((id))(pathLevelGetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(pathLevelGetResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpathLevelGetRequest[T](f: pathLevelGetActionType[T])(request: pathLevelGetActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PathLevelDeleteType[T] extends ResultWrapper[T]
    
    def PathLevelDelete204(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(204, headers){})
    

    private type pathLevelDeleteActionRequestType       = (Long)
    private type pathLevelDeleteActionType[T]            = pathLevelDeleteActionRequestType => Future[PathLevelDeleteType[T] forSome { type T }]


    val pathLevelDeleteActionConstructor  = Action

def pathLevelDeleteAction[T] = (f: pathLevelDeleteActionType[T]) => (id: Long) => pathLevelDeleteActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { pathLevelDeleteResponseMimeType =>
            
            

                val result =
                        new PetsIdDeleteValidator(id).errors match {
                            case e if e.isEmpty => processValidpathLevelDeleteRequest(f)((id))(pathLevelDeleteResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(pathLevelDeleteResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpathLevelDeleteRequest[T](f: pathLevelDeleteActionType[T])(request: pathLevelDeleteActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with MethodLevelType[Result] with PathLevelGetType[Result] with PathLevelDeleteType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with MethodLevelType[Results.EmptyContent] with PathLevelGetType[Results.EmptyContent] with PathLevelDeleteType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
