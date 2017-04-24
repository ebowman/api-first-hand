package expanded

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
trait Expanded_polymorphismYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait FindPetsType[T] extends ResultWrapper[T]
    def FindPets200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new FindPetsType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def FindPets200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new FindPetsType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def FindPetsNoSuchElementException(resultP: java.util.NoSuchElementException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP })
    def FindPetsNoSuchElementException(resultF: Future[java.util.NoSuchElementException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP }) }

    private type findPetsActionRequestType       = (Option[ArrayWrapper[String]], Option[Int])
    private type findPetsActionType[T]            = findPetsActionRequestType => Future[FindPetsType[T] forSome { type T }]


    val findPetsActionConstructor  = Action

def findPetsAction[T] = (f: findPetsActionType[T]) => (tags: Option[ArrayWrapper[String]], limit: Option[Int]) => findPetsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidfindPetsRequest(tags: Option[ArrayWrapper[String]], limit: Option[Int]): Either[Result, Future[FindPetsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((tags, limit)))
            
            new PetsGetValidator(tags, limit).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidfindPetsRequest(tags, limit) match {
                case Left(l) => success(l)
                case Right(r: Future[FindPetsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { findPetsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(findPetsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait AddPetType[T] extends ResultWrapper[T]
    def AddPet200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new AddPetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def AddPet200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new AddPetType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def AddPetNoSuchElementException(resultP: java.util.NoSuchElementException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new AddPetType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP })
    def AddPetNoSuchElementException(resultF: Future[java.util.NoSuchElementException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new AddPetType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP }) }

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

    sealed trait DeletePetType[T] extends ResultWrapper[T]
    
    def DeletePet204(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(204, headers){})
    
    def DeletePetNoSuchElementException(resultP: java.util.NoSuchElementException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new DeletePetType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP })
    def DeletePetNoSuchElementException(resultF: Future[java.util.NoSuchElementException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new DeletePetType[java.util.NoSuchElementException] { val statusCode = 404; val result = resultP; val writer = writerP }) }

    private type deletePetActionRequestType       = (Long)
    private type deletePetActionType[T]            = deletePetActionRequestType => Future[DeletePetType[T] forSome { type T }]


    val deletePetActionConstructor  = Action

def deletePetAction[T] = (f: deletePetActionType[T]) => (id: Long) => deletePetActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeletePetRequest(id: Long): Either[Result, Future[DeletePetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((id)))
            
            new PetsIdDeleteValidator(id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValiddeletePetRequest(id) match {
                case Left(l) => success(l)
                case Right(r: Future[DeletePetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deletePetResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deletePetResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with FindPetsType[Result] with AddPetType[Result] with DeletePetType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with FindPetsType[Results.EmptyContent] with AddPetType[Results.EmptyContent] with DeletePetType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
