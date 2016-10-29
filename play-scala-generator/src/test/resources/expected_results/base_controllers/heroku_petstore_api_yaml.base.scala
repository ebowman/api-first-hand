package heroku.petstore.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
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
    sealed trait GetType[T] extends ResultWrapper[T]
    def Get200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new GetType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Get200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new GetType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getActionRequestType       = (BigInt)
    private type getActionType[T]            = getActionRequestType => Future[GetType[T] forSome { type T }]


    val getActionConstructor  = Action

def getAction[T] = (f: getActionType[T]) => (limit: BigInt) => getActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetRequest(limit: BigInt): Either[Result, Future[GetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((limit)))
            
            new GetValidator(limit).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetRequest(limit) match {
                case Left(l) => success(l)
                case Right(r: Future[GetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "text/html")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutType[T] extends ResultWrapper[T]
    
    def Put200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type putActionRequestType       = (PutPet)
    private type putActionType[T]            = putActionRequestType => Future[PutType[T] forSome { type T }]

        
        import BodyReads._
        
        val putParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.asOpt[Pet])
                case Some("text/xml") => play.api.mvc.BodyParsers.parse.tolerantXml.map(_.asOpt[Pet])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val putActionConstructor  = Action

def putAction[T] = (f: putActionType[T]) => putActionConstructor.async(putParser) { implicit request: Request[PutPet] =>

        def processValidputRequest(pet: PutPet): Either[Result, Future[PutType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((pet)))
            
            new PutValidator(pet).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val pet: PutPet = request.body
            
            

            processValidputRequest(pet) match {
                case Left(l) => success(l)
                case Right(r: Future[PutType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "text/html")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PostType[T] extends ResultWrapper[T]
    
    def Post200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type postActionRequestType       = (Pet)
    private type postActionType[T]            = postActionRequestType => Future[PostType[T] forSome { type T }]

        
        import BodyReads._
        
        val postParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.as[Pet])
                case Some("text/xml") => play.api.mvc.BodyParsers.parse.tolerantXml.map(_.as[Pet])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val postActionConstructor  = Action

def postAction[T] = (f: postActionType[T]) => postActionConstructor.async(postParser) { implicit request: Request[Pet] =>

        def processValidpostRequest(pet: Pet): Either[Result, Future[PostType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((pet)))
            
            new PostValidator(pet).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val pet: Pet = request.body
            
            

            processValidpostRequest(pet) match {
                case Left(l) => success(l)
                case Right(r: Future[PostType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "text/html")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { postResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(postResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetbyPetIdType[T] extends ResultWrapper[T]
    
    def GetbyPetId200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type getbyPetIdActionRequestType       = (String)
    private type getbyPetIdActionType[T]            = getbyPetIdActionRequestType => Future[GetbyPetIdType[T] forSome { type T }]


    val getbyPetIdActionConstructor  = Action

def getbyPetIdAction[T] = (f: getbyPetIdActionType[T]) => (petId: String) => getbyPetIdActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetbyPetIdRequest(petId: String): Either[Result, Future[GetbyPetIdType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((petId)))
            
            new PetIdGetValidator(petId).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetbyPetIdRequest(petId) match {
                case Left(l) => success(l)
                case Right(r: Future[GetbyPetIdType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "text/html")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getbyPetIdResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getbyPetIdResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetType[Result] with PutType[Result] with PostType[Result] with GetbyPetIdType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetType[Results.EmptyContent] with PutType[Results.EmptyContent] with PostType[Results.EmptyContent] with GetbyPetIdType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
