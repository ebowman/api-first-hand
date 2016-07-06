package heroku.petstore.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import scala.math.BigInt

import de.zalando.play.controllers.PlayPathBindables





//noinspection ScalaStyle
trait HerokuPetstoreApiYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait GetType[T] extends ResultWrapper[T]
    def Get200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new GetType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Get200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new GetType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getActionRequestType       = (BigInt)
    private type getActionType[T]            = getActionRequestType => Future[GetType[T] forSome { type T }]


    val getActionConstructor  = Action

def getAction[T] = (f: getActionType[T]) => (limit: BigInt) => getActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "text/html")

        negotiateContent(request.acceptedTypes, providedTypes).map { getResponseMimeType =>
            
            

                val result =
                        new GetValidator(limit).errors match {
                            case e if e.isEmpty => processValidgetRequest(f)((limit))(getResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetRequest[T](f: getActionType[T])(request: getActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PutType[T] extends ResultWrapper[T]
    
    def Put200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type putActionRequestType       = (PutPet)
    private type putActionType[T]            = putActionRequestType => Future[PutType[T] forSome { type T }]

        private def putParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.optionParser[Pet]
            optionParser[Pet](bodyMimeType, customParsers, "Invalid PutPet", maxLength) _
        }

    val putActionConstructor  = Action

def putAction[T] = (f: putActionType[T]) => putActionConstructor.async(BodyParsers.parse.using(putParser(Seq[String]("application/json", "text/xml")))) { request =>
        val providedTypes = Seq[String]("application/json", "text/html")

        negotiateContent(request.acceptedTypes, providedTypes).map { putResponseMimeType =>
            val pet = request.body
            
            

                val result =
                        new PutValidator(pet).errors match {
                            case e if e.isEmpty => processValidputRequest(f)((pet))(putResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(putResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidputRequest[T](f: putActionType[T])(request: putActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PostType[T] extends ResultWrapper[T]
    
    def Post200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type postActionRequestType       = (Pet)
    private type postActionType[T]            = postActionRequestType => Future[PostType[T] forSome { type T }]

        private def postParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.anyParser[Pet]
            anyParser[Pet](bodyMimeType, customParsers, "Invalid Pet", maxLength) _
        }

    val postActionConstructor  = Action

def postAction[T] = (f: postActionType[T]) => postActionConstructor.async(BodyParsers.parse.using(postParser(Seq[String]("application/json", "text/xml")))) { request =>
        val providedTypes = Seq[String]("application/json", "text/html")

        negotiateContent(request.acceptedTypes, providedTypes).map { postResponseMimeType =>
            val pet = request.body
            
            

                val result =
                        new PostValidator(pet).errors match {
                            case e if e.isEmpty => processValidpostRequest(f)((pet))(postResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpostRequest[T](f: postActionType[T])(request: postActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetbyPetIdType[T] extends ResultWrapper[T]
    
    def GetbyPetId200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type getbyPetIdActionRequestType       = (String)
    private type getbyPetIdActionType[T]            = getbyPetIdActionRequestType => Future[GetbyPetIdType[T] forSome { type T }]


    val getbyPetIdActionConstructor  = Action

def getbyPetIdAction[T] = (f: getbyPetIdActionType[T]) => (petId: String) => getbyPetIdActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "text/html")

        negotiateContent(request.acceptedTypes, providedTypes).map { getbyPetIdResponseMimeType =>
            
            

                val result =
                        new PetIdGetValidator(petId).errors match {
                            case e if e.isEmpty => processValidgetbyPetIdRequest(f)((petId))(getbyPetIdResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getbyPetIdResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetbyPetIdRequest[T](f: getbyPetIdActionType[T])(request: getbyPetIdActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetType[Result] with PutType[Result] with PostType[Result] with GetbyPetIdType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetType[Results.EmptyContent] with PutType[Results.EmptyContent] with PostType[Results.EmptyContent] with GetbyPetIdType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
