package expanded

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._

import scala.util._
import de.zalando.play.controllers.ArrayWrapper

import de.zalando.play.controllers.PlayPathBindables





trait Expanded_polymorphismYamlBase extends Controller with PlayBodyParsing {
    sealed trait FindPetsType[T] extends ResultWrapper[T]
    case class FindPets200(result: Seq[Pet])(implicit val writer: String => Option[Writeable[Seq[Pet]]]) extends FindPetsType[Seq[Pet]] { val statusCode = 200 }
    
    case class FindPetsNoSuchElementException(result: java.util.NoSuchElementException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends FindPetsType[java.util.NoSuchElementException] { val statusCode = 404 }

    private type findPetsActionRequestType       = (PetsGetTags, PetsGetLimit)
    private type findPetsActionType[T]            = findPetsActionRequestType => FindPetsType[T] forSome { type T }


    val findPetsActionConstructor  = Action

def findPetsAction[T] = (f: findPetsActionType[T]) => (tags: PetsGetTags, limit: PetsGetLimit) => findPetsActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { findPetsResponseMimeType =>
            
            

                val result =
                        new PetsGetValidator(tags, limit).errors match {
                            case e if e.isEmpty => processValidfindPetsRequest(f)((tags, limit))(findPetsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(findPetsResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidfindPetsRequest[T](f: findPetsActionType[T])(request: findPetsActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait AddPetType[T] extends ResultWrapper[T]
    case class AddPet200(result: Pet)(implicit val writer: String => Option[Writeable[Pet]]) extends AddPetType[Pet] { val statusCode = 200 }
    
    case class AddPetNoSuchElementException(result: java.util.NoSuchElementException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends AddPetType[java.util.NoSuchElementException] { val statusCode = 404 }

    private type addPetActionRequestType       = (NewPet)
    private type addPetActionType[T]            = addPetActionRequestType => AddPetType[T] forSome { type T }

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

def addPetAction[T] = (f: addPetActionType[T]) => addPetActionConstructor(BodyParsers.parse.using(addPetParser(Seq[String]("application/json")))) { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { addPetResponseMimeType =>
            val pet = request.body
            
            

                val result =
                        new PetsPostValidator(pet).errors match {
                            case e if e.isEmpty => processValidaddPetRequest(f)((pet))(addPetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(addPetResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidaddPetRequest[T](f: addPetActionType[T])(request: addPetActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait DeletePetType[T] extends ResultWrapper[T]
    
    case class DeletePet204(headers: Seq[(String, String)] = Nil) extends EmptyReturn(204, headers)
    
    case class DeletePetNoSuchElementException(result: java.util.NoSuchElementException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends DeletePetType[java.util.NoSuchElementException] { val statusCode = 404 }

    private type deletePetActionRequestType       = (Long)
    private type deletePetActionType[T]            = deletePetActionRequestType => DeletePetType[T] forSome { type T }


    val deletePetActionConstructor  = Action

def deletePetAction[T] = (f: deletePetActionType[T]) => (id: Long) => deletePetActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { deletePetResponseMimeType =>
            
            

                val result =
                        new PetsIdDeleteValidator(id).errors match {
                            case e if e.isEmpty => processValiddeletePetRequest(f)((id))(deletePetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deletePetResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValiddeletePetRequest[T](f: deletePetActionType[T])(request: deletePetActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with FindPetsType[Result] with AddPetType[Result] with DeletePetType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with FindPetsType[Results.EmptyContent] with AddPetType[Results.EmptyContent] with DeletePetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
