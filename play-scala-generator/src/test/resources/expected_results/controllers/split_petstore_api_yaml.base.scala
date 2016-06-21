package split.petstore.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._

import scala.util._
import de.zalando.play.controllers.ArrayWrapper
import org.joda.time.DateTime

import de.zalando.play.controllers.PlayPathBindables





trait SplitPetstoreApiYamlBase extends Controller with PlayBodyParsing  with SplitPetstoreApiYamlSecurity {
    sealed trait FindPetsByTagsType[T] extends ResultWrapper[T]
    case class FindPetsByTags200(result: Seq[Pet])(implicit val writer: String => Option[Writeable[Seq[Pet]]]) extends FindPetsByTagsType[Seq[Pet]] { val statusCode = 200 }
    
    case class FindPetsByTags400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class FindPetsByTagsIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends FindPetsByTagsType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class FindPetsByTagsIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends FindPetsByTagsType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type findPetsByTagsActionRequestType       = (PetsFindByStatusGetStatus)
    private type findPetsByTagsActionType[T]            = findPetsByTagsActionRequestType => FindPetsByTagsType[T] forSome { type T }


    val findPetsByTagsActionConstructor  = new findPetsByTagsSecureAction("write_pets", "read_pets")

def findPetsByTagsAction[T] = (f: findPetsByTagsActionType[T]) => (tags: PetsFindByStatusGetStatus) => findPetsByTagsActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { findPetsByTagsResponseMimeType =>
            
            

                val result =
                        new PetsFindByTagsGetValidator(tags).errors match {
                            case e if e.isEmpty => processValidfindPetsByTagsRequest(f)((tags))(findPetsByTagsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(findPetsByTagsResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidfindPetsByTagsRequest[T](f: findPetsByTagsActionType[T])(request: findPetsByTagsActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait PlaceOrderType[T] extends ResultWrapper[T]
    case class PlaceOrder200(result: Order)(implicit val writer: String => Option[Writeable[Order]]) extends PlaceOrderType[Order] { val statusCode = 200 }
    
    case class PlaceOrder400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class PlaceOrderIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends PlaceOrderType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class PlaceOrderIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends PlaceOrderType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type placeOrderActionRequestType       = (StoresOrderPostBody)
    private type placeOrderActionType[T]            = placeOrderActionRequestType => PlaceOrderType[T] forSome { type T }

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

def placeOrderAction[T] = (f: placeOrderActionType[T]) => placeOrderActionConstructor(BodyParsers.parse.using(placeOrderParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { placeOrderResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new StoresOrderPostValidator(body).errors match {
                            case e if e.isEmpty => processValidplaceOrderRequest(f)((body))(placeOrderResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(placeOrderResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidplaceOrderRequest[T](f: placeOrderActionType[T])(request: placeOrderActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait CreateUserType[T] extends ResultWrapper[T]
    
    case class CreateUserIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends CreateUserType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class CreateUserIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends CreateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type createUserActionRequestType       = (UsersUsernamePutBody)
    private type createUserActionType[T]            = createUserActionRequestType => CreateUserType[T] forSome { type T }

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

def createUserAction[T] = (f: createUserActionType[T]) => createUserActionConstructor(BodyParsers.parse.using(createUserParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { createUserResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new UsersPostValidator(body).errors match {
                            case e if e.isEmpty => processValidcreateUserRequest(f)((body))(createUserResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(createUserResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidcreateUserRequest[T](f: createUserActionType[T])(request: createUserActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait CreateUsersWithListInputType[T] extends ResultWrapper[T]
    
    case class CreateUsersWithListInputIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends CreateUsersWithListInputType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class CreateUsersWithListInputIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends CreateUsersWithListInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type createUsersWithListInputActionRequestType       = (UsersCreateWithListPostBody)
    private type createUsersWithListInputActionType[T]            = createUsersWithListInputActionRequestType => CreateUsersWithListInputType[T] forSome { type T }

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

def createUsersWithListInputAction[T] = (f: createUsersWithListInputActionType[T]) => createUsersWithListInputActionConstructor(BodyParsers.parse.using(createUsersWithListInputParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { createUsersWithListInputResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new UsersCreateWithListPostValidator(body).errors match {
                            case e if e.isEmpty => processValidcreateUsersWithListInputRequest(f)((body))(createUsersWithListInputResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(createUsersWithListInputResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidcreateUsersWithListInputRequest[T](f: createUsersWithListInputActionType[T])(request: createUsersWithListInputActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetUserByNameType[T] extends ResultWrapper[T]
    case class GetUserByName200(result: User)(implicit val writer: String => Option[Writeable[User]]) extends GetUserByNameType[User] { val statusCode = 200 }
    
    case class GetUserByName404(headers: Seq[(String, String)] = Nil) extends EmptyReturn(404, headers)
    
    case class GetUserByName400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class GetUserByNameIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends GetUserByNameType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class GetUserByNameIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends GetUserByNameType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type getUserByNameActionRequestType       = (String)
    private type getUserByNameActionType[T]            = getUserByNameActionRequestType => GetUserByNameType[T] forSome { type T }


    val getUserByNameActionConstructor  = Action

def getUserByNameAction[T] = (f: getUserByNameActionType[T]) => (username: String) => getUserByNameActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { getUserByNameResponseMimeType =>
            
            

                val result =
                        new UsersUsernameGetValidator(username).errors match {
                            case e if e.isEmpty => processValidgetUserByNameRequest(f)((username))(getUserByNameResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getUserByNameResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetUserByNameRequest[T](f: getUserByNameActionType[T])(request: getUserByNameActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait UpdateUserType[T] extends ResultWrapper[T]
    
    case class UpdateUser404(headers: Seq[(String, String)] = Nil) extends EmptyReturn(404, headers)
    
    case class UpdateUser400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class UpdateUserIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends UpdateUserType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class UpdateUserIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends UpdateUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type updateUserActionRequestType       = (String, UsersUsernamePutBody)
    private type updateUserActionType[T]            = updateUserActionRequestType => UpdateUserType[T] forSome { type T }

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

def updateUserAction[T] = (f: updateUserActionType[T]) => (username: String) => updateUserActionConstructor(BodyParsers.parse.using(updateUserParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { updateUserResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new UsersUsernamePutValidator(username, body).errors match {
                            case e if e.isEmpty => processValidupdateUserRequest(f)((username, body))(updateUserResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(updateUserResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidupdateUserRequest[T](f: updateUserActionType[T])(request: updateUserActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait DeleteUserType[T] extends ResultWrapper[T]
    
    case class DeleteUser404(headers: Seq[(String, String)] = Nil) extends EmptyReturn(404, headers)
    
    case class DeleteUser400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class DeleteUserIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends DeleteUserType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class DeleteUserIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends DeleteUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type deleteUserActionRequestType       = (String)
    private type deleteUserActionType[T]            = deleteUserActionRequestType => DeleteUserType[T] forSome { type T }


    val deleteUserActionConstructor  = Action

def deleteUserAction[T] = (f: deleteUserActionType[T]) => (username: String) => deleteUserActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { deleteUserResponseMimeType =>
            
            

                val result =
                        new UsersUsernameDeleteValidator(username).errors match {
                            case e if e.isEmpty => processValiddeleteUserRequest(f)((username))(deleteUserResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deleteUserResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValiddeleteUserRequest[T](f: deleteUserActionType[T])(request: deleteUserActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait UpdatePetType[T] extends ResultWrapper[T]
    
    case class UpdatePet405(headers: Seq[(String, String)] = Nil) extends EmptyReturn(405, headers)
    
    case class UpdatePet404(headers: Seq[(String, String)] = Nil) extends EmptyReturn(404, headers)
    
    case class UpdatePet400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class UpdatePetIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends UpdatePetType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class UpdatePetIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends UpdatePetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type updatePetActionRequestType       = (PetsPostBody)
    private type updatePetActionType[T]            = updatePetActionRequestType => UpdatePetType[T] forSome { type T }

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

def updatePetAction[T] = (f: updatePetActionType[T]) => updatePetActionConstructor(BodyParsers.parse.using(updatePetParser(Seq[String]("application/json", "application/xml")))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { updatePetResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new PetsPutValidator(body).errors match {
                            case e if e.isEmpty => processValidupdatePetRequest(f)((body))(updatePetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(updatePetResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidupdatePetRequest[T](f: updatePetActionType[T])(request: updatePetActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait AddPetType[T] extends ResultWrapper[T]
    
    case class AddPet405(headers: Seq[(String, String)] = Nil) extends EmptyReturn(405, headers)
    
    case class AddPetIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends AddPetType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class AddPetIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends AddPetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type addPetActionRequestType       = (PetsPostBody)
    private type addPetActionType[T]            = addPetActionRequestType => AddPetType[T] forSome { type T }

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

def addPetAction[T] = (f: addPetActionType[T]) => addPetActionConstructor(BodyParsers.parse.using(addPetParser(Seq[String]("application/json", "application/xml")))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { addPetResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new PetsPostValidator(body).errors match {
                            case e if e.isEmpty => processValidaddPetRequest(f)((body))(addPetResponseMimeType)
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
    sealed trait CreateUsersWithArrayInputType[T] extends ResultWrapper[T]
    
    case class CreateUsersWithArrayInputIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends CreateUsersWithArrayInputType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class CreateUsersWithArrayInputIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends CreateUsersWithArrayInputType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type createUsersWithArrayInputActionRequestType       = (UsersCreateWithListPostBody)
    private type createUsersWithArrayInputActionType[T]            = createUsersWithArrayInputActionRequestType => CreateUsersWithArrayInputType[T] forSome { type T }

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

def createUsersWithArrayInputAction[T] = (f: createUsersWithArrayInputActionType[T]) => createUsersWithArrayInputActionConstructor(BodyParsers.parse.using(createUsersWithArrayInputParser(Seq[String]()))) { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { createUsersWithArrayInputResponseMimeType =>
            val body = request.body
            
            

                val result =
                        new UsersCreateWithArrayPostValidator(body).errors match {
                            case e if e.isEmpty => processValidcreateUsersWithArrayInputRequest(f)((body))(createUsersWithArrayInputResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(createUsersWithArrayInputResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidcreateUsersWithArrayInputRequest[T](f: createUsersWithArrayInputActionType[T])(request: createUsersWithArrayInputActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetOrderByIdType[T] extends ResultWrapper[T]
    case class GetOrderById200(result: Order)(implicit val writer: String => Option[Writeable[Order]]) extends GetOrderByIdType[Order] { val statusCode = 200 }
    
    case class GetOrderById404(headers: Seq[(String, String)] = Nil) extends EmptyReturn(404, headers)
    
    case class GetOrderById400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class GetOrderByIdIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends GetOrderByIdType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class GetOrderByIdIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends GetOrderByIdType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type getOrderByIdActionRequestType       = (String)
    private type getOrderByIdActionType[T]            = getOrderByIdActionRequestType => GetOrderByIdType[T] forSome { type T }


    val getOrderByIdActionConstructor  = Action

def getOrderByIdAction[T] = (f: getOrderByIdActionType[T]) => (orderId: String) => getOrderByIdActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { getOrderByIdResponseMimeType =>
            
            

                val result =
                        new StoresOrderOrderIdGetValidator(orderId).errors match {
                            case e if e.isEmpty => processValidgetOrderByIdRequest(f)((orderId))(getOrderByIdResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getOrderByIdResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetOrderByIdRequest[T](f: getOrderByIdActionType[T])(request: getOrderByIdActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait DeleteOrderType[T] extends ResultWrapper[T]
    
    case class DeleteOrder404(headers: Seq[(String, String)] = Nil) extends EmptyReturn(404, headers)
    
    case class DeleteOrder400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class DeleteOrderIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends DeleteOrderType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class DeleteOrderIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends DeleteOrderType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type deleteOrderActionRequestType       = (String)
    private type deleteOrderActionType[T]            = deleteOrderActionRequestType => DeleteOrderType[T] forSome { type T }


    val deleteOrderActionConstructor  = Action

def deleteOrderAction[T] = (f: deleteOrderActionType[T]) => (orderId: String) => deleteOrderActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { deleteOrderResponseMimeType =>
            
            

                val result =
                        new StoresOrderOrderIdDeleteValidator(orderId).errors match {
                            case e if e.isEmpty => processValiddeleteOrderRequest(f)((orderId))(deleteOrderResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deleteOrderResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValiddeleteOrderRequest[T](f: deleteOrderActionType[T])(request: deleteOrderActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait LogoutUserType[T] extends ResultWrapper[T]
    
    case class LogoutUserIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends LogoutUserType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class LogoutUserIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends LogoutUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type logoutUserActionRequestType       = (Unit)
    private type logoutUserActionType[T]            = logoutUserActionRequestType => LogoutUserType[T] forSome { type T }


    val logoutUserActionConstructor  = Action

def logoutUserAction[T] = (f: logoutUserActionType[T]) => logoutUserActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { logoutUserResponseMimeType =>
            
            

                val result = processValidlogoutUserRequest(f)()(logoutUserResponseMimeType)
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidlogoutUserRequest[T](f: logoutUserActionType[T])(request: logoutUserActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetPetByIdType[T] extends ResultWrapper[T]
    case class GetPetById200(result: Pet)(implicit val writer: String => Option[Writeable[Pet]]) extends GetPetByIdType[Pet] { val statusCode = 200 }
    
    case class GetPetById404(headers: Seq[(String, String)] = Nil) extends EmptyReturn(404, headers)
    
    case class GetPetById400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class GetPetByIdIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends GetPetByIdType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class GetPetByIdIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends GetPetByIdType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type getPetByIdActionRequestType       = (Long)
    private type getPetByIdActionType[T]            = getPetByIdActionRequestType => GetPetByIdType[T] forSome { type T }


    val getPetByIdActionConstructor  = new getPetByIdSecureAction("write_pets", "read_pets")

def getPetByIdAction[T] = (f: getPetByIdActionType[T]) => (petId: Long) => getPetByIdActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { getPetByIdResponseMimeType =>
            
            

                val result =
                        new PetsPetIdGetValidator(petId).errors match {
                            case e if e.isEmpty => processValidgetPetByIdRequest(f)((petId))(getPetByIdResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getPetByIdResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetPetByIdRequest[T](f: getPetByIdActionType[T])(request: getPetByIdActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait UpdatePetWithFormType[T] extends ResultWrapper[T]
    
    case class UpdatePetWithForm405(headers: Seq[(String, String)] = Nil) extends EmptyReturn(405, headers)
    
    case class UpdatePetWithFormIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends UpdatePetWithFormType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class UpdatePetWithFormIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends UpdatePetWithFormType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type updatePetWithFormActionRequestType       = (String, String, String)
    private type updatePetWithFormActionType[T]            = updatePetWithFormActionRequestType => UpdatePetWithFormType[T] forSome { type T }


    val updatePetWithFormActionConstructor  = new updatePetWithFormSecureAction("write_pets", "read_pets")

def updatePetWithFormAction[T] = (f: updatePetWithFormActionType[T]) => (petId: String) => updatePetWithFormActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { updatePetWithFormResponseMimeType =>
            
            val eitherFormParameters = FormDataParser.updatePetWithFormParseForm(request)
            eitherFormParameters match {
                case Left(problem: Seq[String]) =>
                    val msg = problem.mkString("\n")
                    implicit val marshaller: Writeable[String] = anyToWritable(updatePetWithFormResponseMimeType)
                    BadRequest(msg)

                case Right((name, status)) =>
            

                val result =
                        new PetsPetIdPostValidator(petId, name, status).errors match {
                            case e if e.isEmpty => processValidupdatePetWithFormRequest(f)((petId, name, status))(updatePetWithFormResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(updatePetWithFormResponseMimeType)
                                BadRequest(l)
                        }
                result
            
            }
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidupdatePetWithFormRequest[T](f: updatePetWithFormActionType[T])(request: updatePetWithFormActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait DeletePetType[T] extends ResultWrapper[T]
    
    case class DeletePet400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class DeletePetIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends DeletePetType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class DeletePetIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends DeletePetType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type deletePetActionRequestType       = (String, Long)
    private type deletePetActionType[T]            = deletePetActionRequestType => DeletePetType[T] forSome { type T }


    val deletePetActionConstructor  = new deletePetSecureAction("write_pets", "read_pets")

def deletePetAction[T] = (f: deletePetActionType[T]) => (petId: Long) => deletePetActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { deletePetResponseMimeType =>
            
            val api_key: Either[String,String] =
                fromParameters[String]("header")("api_key", request.headers.toMap)
            
            
                (api_key) match {
                    case (Right(api_key)) =>
            

                val result =
                        new PetsPetIdDeleteValidator(api_key, petId).errors match {
                            case e if e.isEmpty => processValiddeletePetRequest(f)((api_key, petId))(deletePetResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deletePetResponseMimeType)
                                BadRequest(l)
                        }
                result
                case (_) =>
                    val problem: Seq[String] = Seq(api_key).filter{_.isLeft}.map(_.left.get)
                    val msg = problem.mkString("\n")
                    BadRequest(msg)
                }
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValiddeletePetRequest[T](f: deletePetActionType[T])(request: deletePetActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait FindPetsByStatusType[T] extends ResultWrapper[T]
    case class FindPetsByStatus200(result: Seq[Pet])(implicit val writer: String => Option[Writeable[Seq[Pet]]]) extends FindPetsByStatusType[Seq[Pet]] { val statusCode = 200 }
    
    case class FindPetsByStatus400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class FindPetsByStatusIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends FindPetsByStatusType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class FindPetsByStatusIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends FindPetsByStatusType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type findPetsByStatusActionRequestType       = (PetsFindByStatusGetStatus)
    private type findPetsByStatusActionType[T]            = findPetsByStatusActionRequestType => FindPetsByStatusType[T] forSome { type T }


    val findPetsByStatusActionConstructor  = new findPetsByStatusSecureAction("write_pets", "read_pets")

def findPetsByStatusAction[T] = (f: findPetsByStatusActionType[T]) => (status: PetsFindByStatusGetStatus) => findPetsByStatusActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { findPetsByStatusResponseMimeType =>
            
            

                val result =
                        new PetsFindByStatusGetValidator(status).errors match {
                            case e if e.isEmpty => processValidfindPetsByStatusRequest(f)((status))(findPetsByStatusResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(findPetsByStatusResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidfindPetsByStatusRequest[T](f: findPetsByStatusActionType[T])(request: findPetsByStatusActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait LoginUserType[T] extends ResultWrapper[T]
    case class LoginUser200(result: String)(implicit val writer: String => Option[Writeable[String]]) extends LoginUserType[String] { val statusCode = 200 }
    
    case class LoginUser400(headers: Seq[(String, String)] = Nil) extends EmptyReturn(400, headers)
    
    case class LoginUserIllegalArgumentException(result: java.lang.IllegalArgumentException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends LoginUserType[java.lang.IllegalArgumentException] { val statusCode = 405 }
    case class LoginUserIndexOutOfBoundsException(result: java.lang.IndexOutOfBoundsException)(implicit val writer: String => Option[Writeable[java.lang.Exception]]) extends LoginUserType[java.lang.IndexOutOfBoundsException] { val statusCode = 405 }

    private type loginUserActionRequestType       = (OrderStatus, OrderStatus)
    private type loginUserActionType[T]            = loginUserActionRequestType => LoginUserType[T] forSome { type T }


    val loginUserActionConstructor  = Action

def loginUserAction[T] = (f: loginUserActionType[T]) => (username: OrderStatus, password: OrderStatus) => loginUserActionConstructor { request =>
        val providedTypes = Seq[String]("application/json", "application/xml")

        negotiateContent(request.acceptedTypes, providedTypes).map { loginUserResponseMimeType =>
            
            

                val result =
                        new UsersLoginGetValidator(username, password).errors match {
                            case e if e.isEmpty => processValidloginUserRequest(f)((username, password))(loginUserResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(loginUserResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidloginUserRequest[T](f: loginUserActionType[T])(request: loginUserActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with FindPetsByTagsType[Result] with PlaceOrderType[Result] with CreateUserType[Result] with CreateUsersWithListInputType[Result] with GetUserByNameType[Result] with UpdateUserType[Result] with DeleteUserType[Result] with UpdatePetType[Result] with AddPetType[Result] with CreateUsersWithArrayInputType[Result] with GetOrderByIdType[Result] with DeleteOrderType[Result] with LogoutUserType[Result] with GetPetByIdType[Result] with UpdatePetWithFormType[Result] with DeletePetType[Result] with FindPetsByStatusType[Result] with LoginUserType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with FindPetsByTagsType[Results.EmptyContent] with PlaceOrderType[Results.EmptyContent] with CreateUserType[Results.EmptyContent] with CreateUsersWithListInputType[Results.EmptyContent] with GetUserByNameType[Results.EmptyContent] with UpdateUserType[Results.EmptyContent] with DeleteUserType[Results.EmptyContent] with UpdatePetType[Results.EmptyContent] with AddPetType[Results.EmptyContent] with CreateUsersWithArrayInputType[Results.EmptyContent] with GetOrderByIdType[Results.EmptyContent] with DeleteOrderType[Results.EmptyContent] with LogoutUserType[Results.EmptyContent] with GetPetByIdType[Results.EmptyContent] with UpdatePetWithFormType[Results.EmptyContent] with DeletePetType[Results.EmptyContent] with FindPetsByStatusType[Results.EmptyContent] with LoginUserType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
