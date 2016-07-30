package split.petstore.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import de.zalando.play.controllers.ArrayWrapper
import java.time.ZonedDateTime

import de.zalando.play.controllers.PlayPathBindables





//noinspection ScalaStyle
trait SplitPetstoreApiYamlBase extends Controller with PlayBodyParsing  with SplitPetstoreApiYamlSecurity {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait FindPetsByTagsType[T] extends ResultWrapper[T]
    def FindPetsByTags200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new FindPetsByTagsType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def FindPetsByTags200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new FindPetsByTagsType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def FindPetsByTags400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def FindPetsByTagsIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsByTagsType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def FindPetsByTagsIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsByTagsType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def FindPetsByTagsIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsByTagsType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def FindPetsByTagsIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsByTagsType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type findPetsByTagsActionRequestType       = (PetsFindByStatusGetStatus)
    private type findPetsByTagsActionType[T]            = findPetsByTagsActionRequestType => Future[FindPetsByTagsType[T] forSome { type T }]


    val findPetsByTagsActionConstructor  = new findPetsByTagsSecureAction("write_pets", "read_pets")

def findPetsByTagsAction[T] = (f: findPetsByTagsActionType[T]) => (tags: PetsFindByStatusGetStatus) => findPetsByTagsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { findPetsByTagsResponseMimeType =>
            
            

                val result =
                        new PetsFindByTagsGetValidator(tags).errors match {
                            case e if e.isEmpty => processValidfindPetsByTagsRequest(f)((tags))(findPetsByTagsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(findPetsByTagsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidfindPetsByTagsRequest[T](f: findPetsByTagsActionType[T])(request: findPetsByTagsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PlaceOrderType[T] extends ResultWrapper[T]
    def PlaceOrder200(resultP: Order)(implicit writerP: String => Option[Writeable[Order]]) = success(new PlaceOrderType[Order] { val statusCode = 200; val result = resultP; val writer = writerP })
    def PlaceOrder200(resultF: Future[Order])(implicit writerP: String => Option[Writeable[Order]]) = resultF map { resultP => (new PlaceOrderType[Order] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def PlaceOrder400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def PlaceOrderIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new PlaceOrderType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def PlaceOrderIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new PlaceOrderType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def PlaceOrderIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new PlaceOrderType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def PlaceOrderIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new PlaceOrderType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type placeOrderActionRequestType       = (StoresOrderPostBody)
    private type placeOrderActionType[T]            = placeOrderActionRequestType => Future[PlaceOrderType[T] forSome { type T }]

        private def placeOrderParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            import de.zalando.play.controllers.WrappedBodyParsers
            
            val customParsers = WrappedBodyParsers.optionParser[Order]
            optionParser[Order](bodyMimeType, customParsers, "Invalid StoresOrderPostBody", maxLength) _
        }

    val placeOrderActionConstructor  = Action

def placeOrderAction[T] = (f: placeOrderActionType[T]) => placeOrderActionConstructor.async(BodyParsers.parse.using(placeOrderParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { placeOrderResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new StoresOrderPostValidator(body).errors match {
                            case e if e.isEmpty => processValidplaceOrderRequest(f)((body))(placeOrderResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(placeOrderResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidplaceOrderRequest[T](f: placeOrderActionType[T])(request: placeOrderActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait CreateUserType[T] extends ResultWrapper[T]
    
    def CreateUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def CreateUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type createUserActionRequestType       = (UsersUsernamePutBody)
    private type createUserActionType[T]            = createUserActionRequestType => Future[CreateUserType[T] forSome { type T }]

        private def createUserParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            import de.zalando.play.controllers.WrappedBodyParsers
            
            val customParsers = WrappedBodyParsers.optionParser[User]
            optionParser[User](bodyMimeType, customParsers, "Invalid UsersUsernamePutBody", maxLength) _
        }

    val createUserActionConstructor  = Action

def createUserAction[T] = (f: createUserActionType[T]) => createUserActionConstructor.async(BodyParsers.parse.using(createUserParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { createUserResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new UsersPostValidator(body).errors match {
                            case e if e.isEmpty => processValidcreateUserRequest(f)((body))(createUserResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(createUserResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidcreateUserRequest[T](f: createUserActionType[T])(request: createUserActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait CreateUsersWithListInputType[T] extends ResultWrapper[T]
    
    def CreateUsersWithListInputIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUsersWithListInputType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUsersWithListInputIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUsersWithListInputType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def CreateUsersWithListInputIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUsersWithListInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUsersWithListInputIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUsersWithListInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type createUsersWithListInputActionRequestType       = (UsersCreateWithListPostBody)
    private type createUsersWithListInputActionType[T]            = createUsersWithListInputActionRequestType => Future[CreateUsersWithListInputType[T] forSome { type T }]

        private def createUsersWithListInputParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            import de.zalando.play.controllers.WrappedBodyParsers
            
            val customParsers = WrappedBodyParsers.optionParser[UsersCreateWithListPostBodyOpt]
            optionParser[UsersCreateWithListPostBodyOpt](bodyMimeType, customParsers, "Invalid UsersCreateWithListPostBody", maxLength) _
        }

    val createUsersWithListInputActionConstructor  = Action

def createUsersWithListInputAction[T] = (f: createUsersWithListInputActionType[T]) => createUsersWithListInputActionConstructor.async(BodyParsers.parse.using(createUsersWithListInputParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { createUsersWithListInputResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new UsersCreateWithListPostValidator(body).errors match {
                            case e if e.isEmpty => processValidcreateUsersWithListInputRequest(f)((body))(createUsersWithListInputResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(createUsersWithListInputResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidcreateUsersWithListInputRequest[T](f: createUsersWithListInputActionType[T])(request: createUsersWithListInputActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
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

def getUserByNameAction[T] = (f: getUserByNameActionType[T]) => (username: String) => getUserByNameActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { getUserByNameResponseMimeType =>
            
            

                val result =
                        new UsersUsernameGetValidator(username).errors match {
                            case e if e.isEmpty => processValidgetUserByNameRequest(f)((username))(getUserByNameResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getUserByNameResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetUserByNameRequest[T](f: getUserByNameActionType[T])(request: getUserByNameActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait UpdateUserType[T] extends ResultWrapper[T]
    
    def UpdateUser404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def UpdateUser400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def UpdateUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdateUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdateUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdateUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def UpdateUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdateUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type updateUserActionRequestType       = (String, UsersUsernamePutBody)
    private type updateUserActionType[T]            = updateUserActionRequestType => Future[UpdateUserType[T] forSome { type T }]

        private def updateUserParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            import de.zalando.play.controllers.WrappedBodyParsers
            
            val customParsers = WrappedBodyParsers.optionParser[User]
            optionParser[User](bodyMimeType, customParsers, "Invalid UsersUsernamePutBody", maxLength) _
        }

    val updateUserActionConstructor  = Action

def updateUserAction[T] = (f: updateUserActionType[T]) => (username: String) => updateUserActionConstructor.async(BodyParsers.parse.using(updateUserParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { updateUserResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new UsersUsernamePutValidator(username, body).errors match {
                            case e if e.isEmpty => processValidupdateUserRequest(f)((username, body))(updateUserResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(updateUserResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidupdateUserRequest[T](f: updateUserActionType[T])(request: updateUserActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
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

def deleteUserAction[T] = (f: deleteUserActionType[T]) => (username: String) => deleteUserActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { deleteUserResponseMimeType =>
            
            

                val result =
                        new UsersUsernameDeleteValidator(username).errors match {
                            case e if e.isEmpty => processValiddeleteUserRequest(f)((username))(deleteUserResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deleteUserResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValiddeleteUserRequest[T](f: deleteUserActionType[T])(request: deleteUserActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait UpdatePetType[T] extends ResultWrapper[T]
    
    def UpdatePet405(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(405, headers){})
    
    def UpdatePet404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def UpdatePet400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def UpdatePetIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdatePetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdatePetIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdatePetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def UpdatePetIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new UpdatePetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def UpdatePetIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new UpdatePetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type updatePetActionRequestType       = (PetsPostBody)
    private type updatePetActionType[T]            = updatePetActionRequestType => Future[UpdatePetType[T] forSome { type T }]

        private def updatePetParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.optionParser[Pet]
            optionParser[Pet](bodyMimeType, customParsers, "Invalid PetsPostBody", maxLength) _
        }

    val updatePetActionConstructor  = new updatePetSecureAction("write_pets", "read_pets")

def updatePetAction[T] = (f: updatePetActionType[T]) => updatePetActionConstructor.async(BodyParsers.parse.using(updatePetParser(Seq[String]("application/json", "application/xml")))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { updatePetResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new PetsPutValidator(body).errors match {
                            case e if e.isEmpty => processValidupdatePetRequest(f)((body))(updatePetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(updatePetResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidupdatePetRequest[T](f: updatePetActionType[T])(request: updatePetActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait AddPetType[T] extends ResultWrapper[T]
    
    def AddPet405(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(405, headers){})
    
    def AddPetIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new AddPetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def AddPetIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new AddPetType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def AddPetIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new AddPetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def AddPetIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new AddPetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type addPetActionRequestType       = (PetsPostBody)
    private type addPetActionType[T]            = addPetActionRequestType => Future[AddPetType[T] forSome { type T }]

        private def addPetParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.optionParser[Pet]
            optionParser[Pet](bodyMimeType, customParsers, "Invalid PetsPostBody", maxLength) _
        }

    val addPetActionConstructor  = new addPetSecureAction("write_pets", "read_pets")

def addPetAction[T] = (f: addPetActionType[T]) => addPetActionConstructor.async(BodyParsers.parse.using(addPetParser(Seq[String]("application/json", "application/xml")))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { addPetResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new PetsPostValidator(body).errors match {
                            case e if e.isEmpty => processValidaddPetRequest(f)((body))(addPetResponseMimeType)
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
    sealed trait CreateUsersWithArrayInputType[T] extends ResultWrapper[T]
    
    def CreateUsersWithArrayInputIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUsersWithArrayInputType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUsersWithArrayInputIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUsersWithArrayInputType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def CreateUsersWithArrayInputIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new CreateUsersWithArrayInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def CreateUsersWithArrayInputIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new CreateUsersWithArrayInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type createUsersWithArrayInputActionRequestType       = (UsersCreateWithListPostBody)
    private type createUsersWithArrayInputActionType[T]            = createUsersWithArrayInputActionRequestType => Future[CreateUsersWithArrayInputType[T] forSome { type T }]

        private def createUsersWithArrayInputParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            import de.zalando.play.controllers.WrappedBodyParsers
            
            val customParsers = WrappedBodyParsers.optionParser[UsersCreateWithListPostBodyOpt]
            optionParser[UsersCreateWithListPostBodyOpt](bodyMimeType, customParsers, "Invalid UsersCreateWithListPostBody", maxLength) _
        }

    val createUsersWithArrayInputActionConstructor  = Action

def createUsersWithArrayInputAction[T] = (f: createUsersWithArrayInputActionType[T]) => createUsersWithArrayInputActionConstructor.async(BodyParsers.parse.using(createUsersWithArrayInputParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { createUsersWithArrayInputResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new UsersCreateWithArrayPostValidator(body).errors match {
                            case e if e.isEmpty => processValidcreateUsersWithArrayInputRequest(f)((body))(createUsersWithArrayInputResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(createUsersWithArrayInputResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidcreateUsersWithArrayInputRequest[T](f: createUsersWithArrayInputActionType[T])(request: createUsersWithArrayInputActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
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

def getOrderByIdAction[T] = (f: getOrderByIdActionType[T]) => (orderId: String) => getOrderByIdActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { getOrderByIdResponseMimeType =>
            
            

                val result =
                        new StoresOrderOrderIdGetValidator(orderId).errors match {
                            case e if e.isEmpty => processValidgetOrderByIdRequest(f)((orderId))(getOrderByIdResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getOrderByIdResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetOrderByIdRequest[T](f: getOrderByIdActionType[T])(request: getOrderByIdActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
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

def deleteOrderAction[T] = (f: deleteOrderActionType[T]) => (orderId: String) => deleteOrderActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { deleteOrderResponseMimeType =>
            
            

                val result =
                        new StoresOrderOrderIdDeleteValidator(orderId).errors match {
                            case e if e.isEmpty => processValiddeleteOrderRequest(f)((orderId))(deleteOrderResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deleteOrderResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValiddeleteOrderRequest[T](f: deleteOrderActionType[T])(request: deleteOrderActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait LogoutUserType[T] extends ResultWrapper[T]
    
    def LogoutUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new LogoutUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def LogoutUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new LogoutUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def LogoutUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new LogoutUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def LogoutUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new LogoutUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type logoutUserActionRequestType       = (Unit)
    private type logoutUserActionType[T]            = logoutUserActionRequestType => Future[LogoutUserType[T] forSome { type T }]


    val logoutUserActionConstructor  = Action

def logoutUserAction[T] = (f: logoutUserActionType[T]) => logoutUserActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { logoutUserResponseMimeType =>
            
            

                val result = processValidlogoutUserRequest(f)()(logoutUserResponseMimeType)
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidlogoutUserRequest[T](f: logoutUserActionType[T])(request: logoutUserActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
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

def getPetByIdAction[T] = (f: getPetByIdActionType[T]) => (petId: Long) => getPetByIdActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { getPetByIdResponseMimeType =>
            
            

                val result =
                        new PetsPetIdGetValidator(petId).errors match {
                            case e if e.isEmpty => processValidgetPetByIdRequest(f)((petId))(getPetByIdResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getPetByIdResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetPetByIdRequest[T](f: getPetByIdActionType[T])(request: getPetByIdActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
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

def updatePetWithFormAction[T] = (f: updatePetWithFormActionType[T]) => (petId: String) => updatePetWithFormActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { updatePetWithFormResponseMimeType =>
            
            val eitherFormParameters = FormDataParser.updatePetWithFormParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val msg = problem.mkString("\n")
                    implicit val marshaller: Writeable[String] = anyToWritable(updatePetWithFormResponseMimeType)
                    success(BadRequest(msg))

                case Right((name, status)) =>
            

                val result =
                        new PetsPetIdPostValidator(petId, name, status).errors match {
                            case e if e.isEmpty => processValidupdatePetWithFormRequest(f)((petId, name, status))(updatePetWithFormResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(updatePetWithFormResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
            }
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidupdatePetWithFormRequest[T](f: updatePetWithFormActionType[T])(request: updatePetWithFormActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
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

def deletePetAction[T] = (f: deletePetActionType[T]) => (petId: Long) => deletePetActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { deletePetResponseMimeType =>
            
            val api_key: Either[String,String] =
            fromParameters[String]("header")("api_key", request.headers.toMap, None)
            
            
                (api_key) match {
                    case (Right(api_key)) =>
            

                val result =
                        new PetsPetIdDeleteValidator(api_key, petId).errors match {
                            case e if e.isEmpty => processValiddeletePetRequest(f)((api_key, petId))(deletePetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deletePetResponseMimeType)
                                success(BadRequest(l))
                        }
                result
                case (_) =>
                    val problem: Seq[String] = Seq(api_key).filter{_.isLeft}.map(_.left.get)
                    val msg = problem.mkString("\n")
                    success(BadRequest(msg))
                }
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValiddeletePetRequest[T](f: deletePetActionType[T])(request: deletePetActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait FindPetsByStatusType[T] extends ResultWrapper[T]
    def FindPetsByStatus200(resultP: Seq[Pet])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = success(new FindPetsByStatusType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def FindPetsByStatus200(resultF: Future[Seq[Pet]])(implicit writerP: String => Option[Writeable[Seq[Pet]]]) = resultF map { resultP => (new FindPetsByStatusType[Seq[Pet]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def FindPetsByStatus400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def FindPetsByStatusIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsByStatusType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def FindPetsByStatusIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsByStatusType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def FindPetsByStatusIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new FindPetsByStatusType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def FindPetsByStatusIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new FindPetsByStatusType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type findPetsByStatusActionRequestType       = (PetsFindByStatusGetStatus)
    private type findPetsByStatusActionType[T]            = findPetsByStatusActionRequestType => Future[FindPetsByStatusType[T] forSome { type T }]


    val findPetsByStatusActionConstructor  = new findPetsByStatusSecureAction("write_pets", "read_pets")

def findPetsByStatusAction[T] = (f: findPetsByStatusActionType[T]) => (status: PetsFindByStatusGetStatus) => findPetsByStatusActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { findPetsByStatusResponseMimeType =>
            
            

                val result =
                        new PetsFindByStatusGetValidator(status).errors match {
                            case e if e.isEmpty => processValidfindPetsByStatusRequest(f)((status))(findPetsByStatusResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(findPetsByStatusResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidfindPetsByStatusRequest[T](f: findPetsByStatusActionType[T])(request: findPetsByStatusActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait LoginUserType[T] extends ResultWrapper[T]
    def LoginUser200(resultP: String)(implicit writerP: String => Option[Writeable[String]]) = success(new LoginUserType[String] { val statusCode = 200; val result = resultP; val writer = writerP })
    def LoginUser200(resultF: Future[String])(implicit writerP: String => Option[Writeable[String]]) = resultF map { resultP => (new LoginUserType[String] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def LoginUser400(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(400, headers){})
    
    def LoginUserIllegalArgumentException(resultP: java.lang.IllegalArgumentException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new LoginUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def LoginUserIllegalArgumentException(resultF: Future[java.lang.IllegalArgumentException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new LoginUserType[java.lang.IllegalArgumentException] { val statusCode = 405; val result = resultP; val writer = writerP }) }
    def LoginUserIndexOutOfBoundsException(resultP: java.lang.IndexOutOfBoundsException)(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = success(new LoginUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP })
    def LoginUserIndexOutOfBoundsException(resultF: Future[java.lang.IndexOutOfBoundsException])(implicit writerP: String => Option[Writeable[java.lang.Exception]]) = resultF map { resultP => (new LoginUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405; val result = resultP; val writer = writerP }) }

    private type loginUserActionRequestType       = (OrderStatus, OrderStatus)
    private type loginUserActionType[T]            = loginUserActionRequestType => Future[LoginUserType[T] forSome { type T }]


    val loginUserActionConstructor  = Action

def loginUserAction[T] = (f: loginUserActionType[T]) => (username: OrderStatus, password: OrderStatus) => loginUserActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { loginUserResponseMimeType =>
            
            

                val result =
                        new UsersLoginGetValidator(username, password).errors match {
                            case e if e.isEmpty => processValidloginUserRequest(f)((username, password))(loginUserResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(loginUserResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidloginUserRequest[T](f: loginUserActionType[T])(request: loginUserActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with FindPetsByTagsType[Result] with PlaceOrderType[Result] with CreateUserType[Result] with CreateUsersWithListInputType[Result] with GetUserByNameType[Result] with UpdateUserType[Result] with DeleteUserType[Result] with UpdatePetType[Result] with AddPetType[Result] with CreateUsersWithArrayInputType[Result] with GetOrderByIdType[Result] with DeleteOrderType[Result] with LogoutUserType[Result] with GetPetByIdType[Result] with UpdatePetWithFormType[Result] with DeletePetType[Result] with FindPetsByStatusType[Result] with LoginUserType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with FindPetsByTagsType[Results.EmptyContent] with PlaceOrderType[Results.EmptyContent] with CreateUserType[Results.EmptyContent] with CreateUsersWithListInputType[Results.EmptyContent] with GetUserByNameType[Results.EmptyContent] with UpdateUserType[Results.EmptyContent] with DeleteUserType[Results.EmptyContent] with UpdatePetType[Results.EmptyContent] with AddPetType[Results.EmptyContent] with CreateUsersWithArrayInputType[Results.EmptyContent] with GetOrderByIdType[Results.EmptyContent] with DeleteOrderType[Results.EmptyContent] with LogoutUserType[Results.EmptyContent] with GetPetByIdType[Results.EmptyContent] with UpdatePetWithFormType[Results.EmptyContent] with DeletePetType[Results.EmptyContent] with FindPetsByStatusType[Results.EmptyContent] with LoginUserType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
