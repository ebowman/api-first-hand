package expanded

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
trait Expanded_polymorphismYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait FindPetsType[T] extends ResultWrapper[T]
    def FindPets200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new FindPetsType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def FindPets200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new FindPetsType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def FindPetsNoSuchElementException(resultP: java.util.NoSuchElementException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP })
    def FindPetsNoSuchElementException(resultF: Future[java.util.NoSuchElementException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP }) }

    private type findPetsActionRequestType       = (PetsGetTags, PetsGetLimit)
    private type findPetsActionType[T]            = findPetsActionRequestType => Future[FindPetsType[T] forSome { type T }]


    val findPetsActionConstructor  = Action

def findPetsAction[T] = (f: findPetsActionType[T]) => (tags: PetsGetTags, limit: PetsGetLimit) => findPetsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { findPetsResponseMimeType =>
            
            

                val result =
                        new PetsGetValidator(tags, limit).errors match {
                            case e if e.isEmpty => processValidfindPetsRequest(f)((tags, limit))(findPetsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(findPetsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidfindPetsRequest[T](f: findPetsActionType[T])(request: findPetsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait AddPetType[T] extends ResultWrapper[T]
    def AddPet200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new AddPetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def AddPet200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new AddPetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def AddPetNoSuchElementException(resultP: java.util.NoSuchElementException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new AddPetType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP })
    def AddPetNoSuchElementException(resultF: Future[java.util.NoSuchElementException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new AddPetType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP }) }

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
    sealed trait DeletePetType[T] extends ResultWrapper[T]
    
    def DeletePet204(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(204, headers){})
    
    def DeletePetNoSuchElementException(resultP: java.util.NoSuchElementException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new DeletePetType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP })
    def DeletePetNoSuchElementException(resultF: Future[java.util.NoSuchElementException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new DeletePetType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP }) }

    private type deletePetActionRequestType       = (Long)
    private type deletePetActionType[T]            = deletePetActionRequestType => Future[DeletePetType[T] forSome { type T }]


    val deletePetActionConstructor  = Action

def deletePetAction[T] = (f: deletePetActionType[T]) => (id: Long) => deletePetActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { deletePetResponseMimeType =>
            
            

                val result =
                        new PetsIdDeleteValidator(id).errors match {
                            case e if e.isEmpty => processValiddeletePetRequest(f)((id))(deletePetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deletePetResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValiddeletePetRequest[T](f: deletePetActionType[T])(request: deletePetActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with FindPetsType[Result] with AddPetType[Result] with DeletePetType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with FindPetsType[Results.EmptyContent] with AddPetType[Results.EmptyContent] with DeletePetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
