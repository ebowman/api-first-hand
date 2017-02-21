package full.petstore.api.yaml

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
import de.zalando.play.controllers.ArrayWrapper

import de.zalando.play.controllers.PlayPathBindables




//noinspection ScalaStyle
trait FullPetstoreApiYamlBase extends Controller with PlayBodyParsing  with FullPetstoreApiYamlSecurity {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait FindPetsByTagsType[T] extends ResultWrapper[T]
    def FindPetsByTags200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new FindPetsByTagsType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def FindPetsByTags200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new FindPetsByTagsType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def FindPetsByTags400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def FindPetsByTagsIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsByTagsType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def FindPetsByTagsIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsByTagsType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def FindPetsByTagsIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsByTagsType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def FindPetsByTagsIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsByTagsType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type findPetsByTagsActionRequestType       = (Option[ArrayWrapper[String]])
    private type findPetsByTagsActionType[T]            = findPetsByTagsActionRequestType => Future[FindPetsByTagsType[T] forSome { type T }]


    val findPetsByTagsActionConstructor  = new findPetsByTagsSecureAction("write_pets", "read_pets")

def findPetsByTagsAction[T] = (f: findPetsByTagsActionType[T]) => (tags: Option[ArrayWrapper[String]]) => findPetsByTagsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidfindPetsByTagsRequest(tags: Option[ArrayWrapper[String]]): Either[Result, Future[FindPetsByTagsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((tags)))
            
            new PetsFindByTagsGetValidator(tags).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidfindPetsByTagsRequest(tags) match {
                case Left(l) => success(l)
                case Right(r: Future[FindPetsByTagsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { findPetsByTagsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(findPetsByTagsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PlaceOrderType[T] extends ResultWrapper[T]
    def PlaceOrder200(resultP: Order)(implicit writerP: String => Option[Writeable[Order]]) = success(new PlaceOrderType[Order] { val statusCode = 200; val result = resultP; val writer = writerP })
    def PlaceOrder200(resultF: Future[Order])(implicit writerP: String => Option[Writeable[Order]]) = resultF map { resultP => (new PlaceOrderType[Order] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def PlaceOrder400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def PlaceOrderIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new PlaceOrderType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def PlaceOrderIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new PlaceOrderType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def PlaceOrderIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new PlaceOrderType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def PlaceOrderIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new PlaceOrderType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type placeOrderActionRequestType       = (Option[Order])
    private type placeOrderActionType[T]            = placeOrderActionRequestType => Future[PlaceOrderType[T] forSome { type T }]

        
        import BodyReads._
        
        val placeOrderParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val placeOrderActionConstructor  = Action

def placeOrderAction[T] = (f: placeOrderActionType[T]) => placeOrderActionConstructor.async(placeOrderParser) { implicit request: Request[Option[Order]] =>

        def processValidplaceOrderRequest(body: Option[Order]): Either[Result, Future[PlaceOrderType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((body)))
            
            new StoresOrderPostValidator(body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val body: Option[Order] = request.body
            
            

            processValidplaceOrderRequest(body) match {
                case Left(l) => success(l)
                case Right(r: Future[PlaceOrderType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { placeOrderResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(placeOrderResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait CreateUserType[T] extends ResultWrapper[T]
    
    def CreateUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def CreateUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type createUserActionRequestType       = (Option[User])
    private type createUserActionType[T]            = createUserActionRequestType => Future[CreateUserType[T] forSome { type T }]

        
        import BodyReads._
        
        val createUserParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val createUserActionConstructor  = Action

def createUserAction[T] = (f: createUserActionType[T]) => createUserActionConstructor.async(createUserParser) { implicit request: Request[Option[User]] =>

        def processValidcreateUserRequest(body: Option[User]): Either[Result, Future[CreateUserType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((body)))
            
            new UsersPostValidator(body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val body: Option[User] = request.body
            
            

            processValidcreateUserRequest(body) match {
                case Left(l) => success(l)
                case Right(r: Future[CreateUserType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { createUserResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(createUserResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait CreateUsersWithListInputType[T] extends ResultWrapper[T]
    
    def CreateUsersWithListInputIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUsersWithListInputType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUsersWithListInputIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUsersWithListInputType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def CreateUsersWithListInputIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUsersWithListInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUsersWithListInputIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUsersWithListInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type createUsersWithListInputActionRequestType       = (Option[Seq[User]])
    private type createUsersWithListInputActionType[T]            = createUsersWithListInputActionRequestType => Future[CreateUsersWithListInputType[T] forSome { type T }]

        
        import BodyReads._
        
        val createUsersWithListInputParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val createUsersWithListInputActionConstructor  = Action

def createUsersWithListInputAction[T] = (f: createUsersWithListInputActionType[T]) => createUsersWithListInputActionConstructor.async(createUsersWithListInputParser) { implicit request: Request[Option[Seq[User]]] =>

        def processValidcreateUsersWithListInputRequest(body: Option[Seq[User]]): Either[Result, Future[CreateUsersWithListInputType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((body)))
            
            new UsersCreateWithListPostValidator(body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val body: Option[Seq[User]] = request.body
            
            

            processValidcreateUsersWithListInputRequest(body) match {
                case Left(l) => success(l)
                case Right(r: Future[CreateUsersWithListInputType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { createUsersWithListInputResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(createUsersWithListInputResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetUserByNameType[T] extends ResultWrapper[T]
    def GetUserByName200(resultP: User)(implicit writerP: String => Option[Writeable[User]]) = success(new GetUserByNameType[User] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetUserByName200(resultF: Future[User])(implicit writerP: String => Option[Writeable[User]]) = resultF map { resultP => (new GetUserByNameType[User] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def GetUserByName404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def GetUserByName400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def GetUserByNameIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new GetUserByNameType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def GetUserByNameIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new GetUserByNameType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def GetUserByNameIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new GetUserByNameType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def GetUserByNameIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new GetUserByNameType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type getUserByNameActionRequestType       = (String)
    private type getUserByNameActionType[T]            = getUserByNameActionRequestType => Future[GetUserByNameType[T] forSome { type T }]


    val getUserByNameActionConstructor  = Action

def getUserByNameAction[T] = (f: getUserByNameActionType[T]) => (username: String) => getUserByNameActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetUserByNameRequest(username: String): Either[Result, Future[GetUserByNameType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((username)))
            
            new UsersUsernameGetValidator(username).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetUserByNameRequest(username) match {
                case Left(l) => success(l)
                case Right(r: Future[GetUserByNameType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getUserByNameResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getUserByNameResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait UpdateUserType[T] extends ResultWrapper[T]
    
    def UpdateUser404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def UpdateUser400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def UpdateUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdateUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdateUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdateUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def UpdateUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdateUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type updateUserActionRequestType       = (String, Option[User])
    private type updateUserActionType[T]            = updateUserActionRequestType => Future[UpdateUserType[T] forSome { type T }]

        
        import BodyReads._
        
        val updateUserParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val updateUserActionConstructor  = Action

def updateUserAction[T] = (f: updateUserActionType[T]) => (username: String) => updateUserActionConstructor.async(updateUserParser) { implicit request: Request[Option[User]] =>

        def processValidupdateUserRequest(username: String, body: Option[User]): Either[Result, Future[UpdateUserType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((username, body)))
            
            new UsersUsernamePutValidator(username, body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val body: Option[User] = request.body
            
            

            processValidupdateUserRequest(username, body) match {
                case Left(l) => success(l)
                case Right(r: Future[UpdateUserType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { updateUserResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(updateUserResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait DeleteUserType[T] extends ResultWrapper[T]
    
    def DeleteUser404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def DeleteUser400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def DeleteUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new DeleteUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def DeleteUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new DeleteUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def DeleteUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new DeleteUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def DeleteUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new DeleteUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type deleteUserActionRequestType       = (String)
    private type deleteUserActionType[T]            = deleteUserActionRequestType => Future[DeleteUserType[T] forSome { type T }]


    val deleteUserActionConstructor  = Action

def deleteUserAction[T] = (f: deleteUserActionType[T]) => (username: String) => deleteUserActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeleteUserRequest(username: String): Either[Result, Future[DeleteUserType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((username)))
            
            new UsersUsernameDeleteValidator(username).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValiddeleteUserRequest(username) match {
                case Left(l) => success(l)
                case Right(r: Future[DeleteUserType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deleteUserResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deleteUserResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait UpdatePetType[T] extends ResultWrapper[T]
    
    def UpdatePet405(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(405, headers){})
    
    def UpdatePet404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def UpdatePet400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def UpdatePetIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdatePetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdatePetIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdatePetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def UpdatePetIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdatePetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdatePetIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdatePetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type updatePetActionRequestType       = (Option[Pet])
    private type updatePetActionType[T]            = updatePetActionRequestType => Future[UpdatePetType[T] forSome { type T }]

        
        import BodyReads._
        
        val updatePetParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.asOpt[Pet])
                case Some("application/xml") => play.api.mvc.BodyParsers.parse.tolerantXml.map(_.asOpt[Pet])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val updatePetActionConstructor  = new updatePetSecureAction("write_pets", "read_pets")

def updatePetAction[T] = (f: updatePetActionType[T]) => updatePetActionConstructor.async(updatePetParser) { implicit request: Request[Option[Pet]] =>

        def processValidupdatePetRequest(body: Option[Pet]): Either[Result, Future[UpdatePetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((body)))
            
            new PetsPutValidator(body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val body: Option[Pet] = request.body
            
            

            processValidupdatePetRequest(body) match {
                case Left(l) => success(l)
                case Right(r: Future[UpdatePetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { updatePetResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(updatePetResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait AddPetType[T] extends ResultWrapper[T]
    
    def AddPet405(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(405, headers){})
    
    def AddPetIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new AddPetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def AddPetIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new AddPetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def AddPetIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new AddPetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def AddPetIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new AddPetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type addPetActionRequestType       = (Option[Pet])
    private type addPetActionType[T]            = addPetActionRequestType => Future[AddPetType[T] forSome { type T }]

        
        import BodyReads._
        
        val addPetParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.asOpt[Pet])
                case Some("application/xml") => play.api.mvc.BodyParsers.parse.tolerantXml.map(_.asOpt[Pet])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val addPetActionConstructor  = new addPetSecureAction("write_pets", "read_pets")

def addPetAction[T] = (f: addPetActionType[T]) => addPetActionConstructor.async(addPetParser) { implicit request: Request[Option[Pet]] =>

        def processValidaddPetRequest(body: Option[Pet]): Either[Result, Future[AddPetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((body)))
            
            new PetsPostValidator(body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val body: Option[Pet] = request.body
            
            

            processValidaddPetRequest(body) match {
                case Left(l) => success(l)
                case Right(r: Future[AddPetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { addPetResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(addPetResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait CreateUsersWithArrayInputType[T] extends ResultWrapper[T]
    
    def CreateUsersWithArrayInputIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUsersWithArrayInputType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUsersWithArrayInputIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUsersWithArrayInputType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def CreateUsersWithArrayInputIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUsersWithArrayInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUsersWithArrayInputIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUsersWithArrayInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type createUsersWithArrayInputActionRequestType       = (Option[Seq[User]])
    private type createUsersWithArrayInputActionType[T]            = createUsersWithArrayInputActionRequestType => Future[CreateUsersWithArrayInputType[T] forSome { type T }]

        
        import BodyReads._
        
        val createUsersWithArrayInputParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val createUsersWithArrayInputActionConstructor  = Action

def createUsersWithArrayInputAction[T] = (f: createUsersWithArrayInputActionType[T]) => createUsersWithArrayInputActionConstructor.async(createUsersWithArrayInputParser) { implicit request: Request[Option[Seq[User]]] =>

        def processValidcreateUsersWithArrayInputRequest(body: Option[Seq[User]]): Either[Result, Future[CreateUsersWithArrayInputType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((body)))
            
            new UsersCreateWithArrayPostValidator(body).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val body: Option[Seq[User]] = request.body
            
            

            processValidcreateUsersWithArrayInputRequest(body) match {
                case Left(l) => success(l)
                case Right(r: Future[CreateUsersWithArrayInputType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { createUsersWithArrayInputResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(createUsersWithArrayInputResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetOrderByIdType[T] extends ResultWrapper[T]
    def GetOrderById200(resultP: Order)(implicit writerP: String => Option[Writeable[Order]]) = success(new GetOrderByIdType[Order] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetOrderById200(resultF: Future[Order])(implicit writerP: String => Option[Writeable[Order]]) = resultF map { resultP => (new GetOrderByIdType[Order] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def GetOrderById404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def GetOrderById400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def GetOrderByIdIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new GetOrderByIdType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def GetOrderByIdIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new GetOrderByIdType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def GetOrderByIdIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new GetOrderByIdType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def GetOrderByIdIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new GetOrderByIdType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type getOrderByIdActionRequestType       = (String)
    private type getOrderByIdActionType[T]            = getOrderByIdActionRequestType => Future[GetOrderByIdType[T] forSome { type T }]


    val getOrderByIdActionConstructor  = Action

def getOrderByIdAction[T] = (f: getOrderByIdActionType[T]) => (orderId: String) => getOrderByIdActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetOrderByIdRequest(orderId: String): Either[Result, Future[GetOrderByIdType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((orderId)))
            
            new StoresOrderOrderIdGetValidator(orderId).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetOrderByIdRequest(orderId) match {
                case Left(l) => success(l)
                case Right(r: Future[GetOrderByIdType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getOrderByIdResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getOrderByIdResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait DeleteOrderType[T] extends ResultWrapper[T]
    
    def DeleteOrder404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def DeleteOrder400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def DeleteOrderIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new DeleteOrderType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def DeleteOrderIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new DeleteOrderType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def DeleteOrderIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new DeleteOrderType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def DeleteOrderIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new DeleteOrderType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type deleteOrderActionRequestType       = (String)
    private type deleteOrderActionType[T]            = deleteOrderActionRequestType => Future[DeleteOrderType[T] forSome { type T }]


    val deleteOrderActionConstructor  = Action

def deleteOrderAction[T] = (f: deleteOrderActionType[T]) => (orderId: String) => deleteOrderActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeleteOrderRequest(orderId: String): Either[Result, Future[DeleteOrderType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((orderId)))
            
            new StoresOrderOrderIdDeleteValidator(orderId).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValiddeleteOrderRequest(orderId) match {
                case Left(l) => success(l)
                case Right(r: Future[DeleteOrderType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deleteOrderResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deleteOrderResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait LogoutUserType[T] extends ResultWrapper[T]
    
    def LogoutUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new LogoutUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def LogoutUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new LogoutUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def LogoutUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new LogoutUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def LogoutUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new LogoutUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type logoutUserActionRequestType       = (Unit)
    private type logoutUserActionType[T]            = logoutUserActionRequestType => Future[LogoutUserType[T] forSome { type T }]


    val logoutUserActionConstructor  = Action

def logoutUserAction[T] = (f: logoutUserActionType[T]) => logoutUserActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidlogoutUserRequest(): Either[Result, Future[LogoutUserType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f(()))
            apiFirstTempResultHolder
        }

            
            

            processValidlogoutUserRequest() match {
                case Left(l) => success(l)
                case Right(r: Future[LogoutUserType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { logoutUserResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(logoutUserResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetPetByIdType[T] extends ResultWrapper[T]
    def GetPetById200(resultP: Pet)(implicit writerP: String => Option[Writeable[Pet]]) = success(new GetPetByIdType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetPetById200(resultF: Future[Pet])(implicit writerP: String => Option[Writeable[Pet]]) = resultF map { resultP => (new GetPetByIdType[Pet] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def GetPetById404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def GetPetById400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def GetPetByIdIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new GetPetByIdType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def GetPetByIdIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new GetPetByIdType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def GetPetByIdIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new GetPetByIdType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def GetPetByIdIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new GetPetByIdType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type getPetByIdActionRequestType       = (Long)
    private type getPetByIdActionType[T]            = getPetByIdActionRequestType => Future[GetPetByIdType[T] forSome { type T }]


    val getPetByIdActionConstructor  = new getPetByIdSecureAction("write_pets", "read_pets")

def getPetByIdAction[T] = (f: getPetByIdActionType[T]) => (petId: Long) => getPetByIdActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetPetByIdRequest(petId: Long): Either[Result, Future[GetPetByIdType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((petId)))
            
            new PetsPetIdGetValidator(petId).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetPetByIdRequest(petId) match {
                case Left(l) => success(l)
                case Right(r: Future[GetPetByIdType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getPetByIdResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getPetByIdResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait UpdatePetWithFormType[T] extends ResultWrapper[T]
    
    def UpdatePetWithForm405(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(405, headers){})
    
    def UpdatePetWithFormIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdatePetWithFormType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdatePetWithFormIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdatePetWithFormType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def UpdatePetWithFormIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdatePetWithFormType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdatePetWithFormIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdatePetWithFormType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type updatePetWithFormActionRequestType       = (String, String, String)
    private type updatePetWithFormActionType[T]            = updatePetWithFormActionRequestType => Future[UpdatePetWithFormType[T] forSome { type T }]


    val updatePetWithFormActionConstructor  = new updatePetWithFormSecureAction("write_pets", "read_pets")

def updatePetWithFormAction[T] = (f: updatePetWithFormActionType[T]) => (petId: String) => updatePetWithFormActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidupdatePetWithFormRequest(petId: String, name: String, status: String): Either[Result, Future[UpdatePetWithFormType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((petId, name, status)))
            
            new PetsPetIdPostValidator(petId, name, status).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            val eitherFormParameters = FormDataParser.updatePetWithFormParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val providedTypes = Seq[String]()
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { updatePetWithFormResponseMimeType =>
                        val msg = problem.mkString("\n")
                        implicit val marshaller: Writeable[String] = anyToWritable(updatePetWithFormResponseMimeType)
                        success(BadRequest(msg))
                    }
                    result.getOrElse(success(Results.NotAcceptable))

                case Right((name, status)) =>
            

            processValidupdatePetWithFormRequest(petId, name, status) match {
                case Left(l) => success(l)
                case Right(r: Future[UpdatePetWithFormType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { updatePetWithFormResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(updatePetWithFormResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
            }
        
    }

    sealed trait DeletePetType[T] extends ResultWrapper[T]
    
    def DeletePet400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def DeletePetIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new DeletePetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def DeletePetIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new DeletePetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def DeletePetIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new DeletePetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def DeletePetIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new DeletePetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type deletePetActionRequestType       = (String, Long)
    private type deletePetActionType[T]            = deletePetActionRequestType => Future[DeletePetType[T] forSome { type T }]


    val deletePetActionConstructor  = new deletePetSecureAction("write_pets", "read_pets")

def deletePetAction[T] = (f: deletePetActionType[T]) => (petId: Long) => deletePetActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeletePetRequest(api_key: String, petId: Long): Either[Result, Future[DeletePetType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((api_key, petId)))
            
            new PetsPetIdDeleteValidator(api_key, petId).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            val api_key: Either[String,String] = fromParameters[String]("header")("api_key", request.headers.toMap, None)
            
            
                (api_key) match {
                    case (Right(api_key)) =>
            

            processValiddeletePetRequest(api_key, petId) match {
                case Left(l) => success(l)
                case Right(r: Future[DeletePetType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deletePetResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deletePetResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
                case (_) =>
                    val problem: Seq[String] = Seq(api_key).filter{_.isLeft}.map(_.left.get)
                    val msg = problem.mkString("\n")
                    success(BadRequest(msg))
                }
            
    }

    sealed trait FindPetsByStatusType[T] extends ResultWrapper[T]
    def FindPetsByStatus200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new FindPetsByStatusType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def FindPetsByStatus200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new FindPetsByStatusType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def FindPetsByStatus400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def FindPetsByStatusIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsByStatusType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def FindPetsByStatusIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsByStatusType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def FindPetsByStatusIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsByStatusType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def FindPetsByStatusIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsByStatusType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type findPetsByStatusActionRequestType       = (Option[ArrayWrapper[String]])
    private type findPetsByStatusActionType[T]            = findPetsByStatusActionRequestType => Future[FindPetsByStatusType[T] forSome { type T }]


    val findPetsByStatusActionConstructor  = new findPetsByStatusSecureAction("write_pets", "read_pets")

def findPetsByStatusAction[T] = (f: findPetsByStatusActionType[T]) => (status: Option[ArrayWrapper[String]]) => findPetsByStatusActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidfindPetsByStatusRequest(status: Option[ArrayWrapper[String]]): Either[Result, Future[FindPetsByStatusType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((status)))
            
            new PetsFindByStatusGetValidator(status).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidfindPetsByStatusRequest(status) match {
                case Left(l) => success(l)
                case Right(r: Future[FindPetsByStatusType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { findPetsByStatusResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(findPetsByStatusResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait LoginUserType[T] extends ResultWrapper[T]
    def LoginUser200(resultP: String)(implicit writerP: String => Option[Writeable[String]]) = success(new LoginUserType[String] { val statusCode = 200; val result = resultP; val writer = writerP })
    def LoginUser200(resultF: Future[String])(implicit writerP: String => Option[Writeable[String]]) = resultF map { resultP => (new LoginUserType[String] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def LoginUser400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def LoginUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new LoginUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def LoginUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new LoginUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def LoginUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new LoginUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def LoginUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new LoginUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type loginUserActionRequestType       = (Option[String], Option[String])
    private type loginUserActionType[T]            = loginUserActionRequestType => Future[LoginUserType[T] forSome { type T }]


    val loginUserActionConstructor  = Action

def loginUserAction[T] = (f: loginUserActionType[T]) => (username: Option[String], password: Option[String]) => loginUserActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidloginUserRequest(username: Option[String], password: Option[String]): Either[Result, Future[LoginUserType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((username, password)))
            
            new UsersLoginGetValidator(username, password).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidloginUserRequest(username, password) match {
                case Left(l) => success(l)
                case Right(r: Future[LoginUserType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json", "application/xml")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { loginUserResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(loginUserResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with FindPetsByTagsType[Result] with PlaceOrderType[Result] with CreateUserType[Result] with CreateUsersWithListInputType[Result] with GetUserByNameType[Result] with UpdateUserType[Result] with DeleteUserType[Result] with UpdatePetType[Result] with AddPetType[Result] with CreateUsersWithArrayInputType[Result] with GetOrderByIdType[Result] with DeleteOrderType[Result] with LogoutUserType[Result] with GetPetByIdType[Result] with UpdatePetWithFormType[Result] with DeletePetType[Result] with FindPetsByStatusType[Result] with LoginUserType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with FindPetsByTagsType[Results.EmptyContent] with PlaceOrderType[Results.EmptyContent] with CreateUserType[Results.EmptyContent] with CreateUsersWithListInputType[Results.EmptyContent] with GetUserByNameType[Results.EmptyContent] with UpdateUserType[Results.EmptyContent] with DeleteUserType[Results.EmptyContent] with UpdatePetType[Results.EmptyContent] with AddPetType[Results.EmptyContent] with CreateUsersWithArrayInputType[Results.EmptyContent] with GetOrderByIdType[Results.EmptyContent] with DeleteOrderType[Results.EmptyContent] with LogoutUserType[Results.EmptyContent] with GetPetByIdType[Results.EmptyContent] with UpdatePetWithFormType[Results.EmptyContent] with DeletePetType[Results.EmptyContent] with FindPetsByStatusType[Results.EmptyContent] with LoginUserType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
