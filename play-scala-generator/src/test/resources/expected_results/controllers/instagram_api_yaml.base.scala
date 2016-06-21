package instagram.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._

import scala.util._
import scala.math.BigInt
import scala.math.BigDecimal

import de.zalando.play.controllers.PlayPathBindables





trait InstagramApiYamlBase extends Controller with PlayBodyParsing  with InstagramApiYamlSecurity {
    sealed trait GetmediaByMedia_idLikesType[T] extends ResultWrapper[T]
    case class GetmediaByMedia_idLikes200(result: MediaMedia_idLikesGetResponses200)(implicit val writer: String => Option[Writeable[MediaMedia_idLikesGetResponses200]]) extends GetmediaByMedia_idLikesType[MediaMedia_idLikesGetResponses200] { val statusCode = 200 }
    

    private type getmediaByMedia_idLikesActionRequestType       = (BigInt)
    private type getmediaByMedia_idLikesActionType[T]            = getmediaByMedia_idLikesActionRequestType => GetmediaByMedia_idLikesType[T] forSome { type T }


    val getmediaByMedia_idLikesActionConstructor  = new getmediaByMedia_idLikesSecureAction("basic", "comments", "relationships", "likes")

def getmediaByMedia_idLikesAction[T] = (f: getmediaByMedia_idLikesActionType[T]) => (media_id: BigInt) => getmediaByMedia_idLikesActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaByMedia_idLikesResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idLikesGetValidator(media_id).errors match {
                            case e if e.isEmpty => processValidgetmediaByMedia_idLikesRequest(f)((media_id))(getmediaByMedia_idLikesResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaByMedia_idLikesResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetmediaByMedia_idLikesRequest[T](f: getmediaByMedia_idLikesActionType[T])(request: getmediaByMedia_idLikesActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait PostmediaByMedia_idLikesType[T] extends ResultWrapper[T]
    case class PostmediaByMedia_idLikes200(result: MediaMedia_idCommentsDeleteResponses200)(implicit val writer: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) extends PostmediaByMedia_idLikesType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200 }
    

    private type postmediaByMedia_idLikesActionRequestType       = (BigInt)
    private type postmediaByMedia_idLikesActionType[T]            = postmediaByMedia_idLikesActionRequestType => PostmediaByMedia_idLikesType[T] forSome { type T }


    val postmediaByMedia_idLikesActionConstructor  = new postmediaByMedia_idLikesSecureAction("comments")

def postmediaByMedia_idLikesAction[T] = (f: postmediaByMedia_idLikesActionType[T]) => (media_id: BigInt) => postmediaByMedia_idLikesActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { postmediaByMedia_idLikesResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idLikesPostValidator(media_id).errors match {
                            case e if e.isEmpty => processValidpostmediaByMedia_idLikesRequest(f)((media_id))(postmediaByMedia_idLikesResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postmediaByMedia_idLikesResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidpostmediaByMedia_idLikesRequest[T](f: postmediaByMedia_idLikesActionType[T])(request: postmediaByMedia_idLikesActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait DeletemediaByMedia_idLikesType[T] extends ResultWrapper[T]
    case class DeletemediaByMedia_idLikes200(result: MediaMedia_idCommentsDeleteResponses200)(implicit val writer: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) extends DeletemediaByMedia_idLikesType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200 }
    

    private type deletemediaByMedia_idLikesActionRequestType       = (BigInt)
    private type deletemediaByMedia_idLikesActionType[T]            = deletemediaByMedia_idLikesActionRequestType => DeletemediaByMedia_idLikesType[T] forSome { type T }


    val deletemediaByMedia_idLikesActionConstructor  = new deletemediaByMedia_idLikesSecureAction("basic", "comments", "relationships", "likes")

def deletemediaByMedia_idLikesAction[T] = (f: deletemediaByMedia_idLikesActionType[T]) => (media_id: BigInt) => deletemediaByMedia_idLikesActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { deletemediaByMedia_idLikesResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idLikesDeleteValidator(media_id).errors match {
                            case e if e.isEmpty => processValiddeletemediaByMedia_idLikesRequest(f)((media_id))(deletemediaByMedia_idLikesResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deletemediaByMedia_idLikesResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValiddeletemediaByMedia_idLikesRequest[T](f: deletemediaByMedia_idLikesActionType[T])(request: deletemediaByMedia_idLikesActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetusersByUser_idFollowsType[T] extends ResultWrapper[T]
    case class GetusersByUser_idFollows200(result: UsersUser_idFollowsGetResponses200)(implicit val writer: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) extends GetusersByUser_idFollowsType[UsersUser_idFollowsGetResponses200] { val statusCode = 200 }
    

    private type getusersByUser_idFollowsActionRequestType       = (BigDecimal)
    private type getusersByUser_idFollowsActionType[T]            = getusersByUser_idFollowsActionRequestType => GetusersByUser_idFollowsType[T] forSome { type T }


    val getusersByUser_idFollowsActionConstructor  = new getusersByUser_idFollowsSecureAction("basic", "comments", "relationships", "likes")

def getusersByUser_idFollowsAction[T] = (f: getusersByUser_idFollowsActionType[T]) => (user_id: BigDecimal) => getusersByUser_idFollowsActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersByUser_idFollowsResponseMimeType =>
            
            

                val result =
                        new UsersUser_idFollowsGetValidator(user_id).errors match {
                            case e if e.isEmpty => processValidgetusersByUser_idFollowsRequest(f)((user_id))(getusersByUser_idFollowsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersByUser_idFollowsResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetusersByUser_idFollowsRequest[T](f: getusersByUser_idFollowsActionType[T])(request: getusersByUser_idFollowsActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetlocationsByLocation_idType[T] extends ResultWrapper[T]
    case class GetlocationsByLocation_id200(result: LocationsLocation_idGetResponses200)(implicit val writer: String => Option[Writeable[LocationsLocation_idGetResponses200]]) extends GetlocationsByLocation_idType[LocationsLocation_idGetResponses200] { val statusCode = 200 }
    

    private type getlocationsByLocation_idActionRequestType       = (BigInt)
    private type getlocationsByLocation_idActionType[T]            = getlocationsByLocation_idActionRequestType => GetlocationsByLocation_idType[T] forSome { type T }


    val getlocationsByLocation_idActionConstructor  = new getlocationsByLocation_idSecureAction("basic", "comments", "relationships", "likes")

def getlocationsByLocation_idAction[T] = (f: getlocationsByLocation_idActionType[T]) => (location_id: BigInt) => getlocationsByLocation_idActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getlocationsByLocation_idResponseMimeType =>
            
            

                val result =
                        new LocationsLocation_idGetValidator(location_id).errors match {
                            case e if e.isEmpty => processValidgetlocationsByLocation_idRequest(f)((location_id))(getlocationsByLocation_idResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getlocationsByLocation_idResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetlocationsByLocation_idRequest[T](f: getlocationsByLocation_idActionType[T])(request: getlocationsByLocation_idActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetusersSearchType[T] extends ResultWrapper[T]
    case class GetusersSearch200(result: UsersUser_idFollowsGetResponses200)(implicit val writer: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) extends GetusersSearchType[UsersUser_idFollowsGetResponses200] { val statusCode = 200 }
    

    private type getusersSearchActionRequestType       = (String, MediaFilter)
    private type getusersSearchActionType[T]            = getusersSearchActionRequestType => GetusersSearchType[T] forSome { type T }


    val getusersSearchActionConstructor  = new getusersSearchSecureAction("basic", "comments", "relationships", "likes")

def getusersSearchAction[T] = (f: getusersSearchActionType[T]) => (q: String, count: MediaFilter) => getusersSearchActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersSearchResponseMimeType =>
            
            

                val result =
                        new UsersSearchGetValidator(q, count).errors match {
                            case e if e.isEmpty => processValidgetusersSearchRequest(f)((q, count))(getusersSearchResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersSearchResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetusersSearchRequest[T](f: getusersSearchActionType[T])(request: getusersSearchActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetusersSelfMediaLikedType[T] extends ResultWrapper[T]
    case class GetusersSelfMediaLiked200(result: UsersSelfFeedGetResponses200)(implicit val writer: String => Option[Writeable[UsersSelfFeedGetResponses200]]) extends GetusersSelfMediaLikedType[UsersSelfFeedGetResponses200] { val statusCode = 200 }
    

    private type getusersSelfMediaLikedActionRequestType       = (MediaId, MediaId)
    private type getusersSelfMediaLikedActionType[T]            = getusersSelfMediaLikedActionRequestType => GetusersSelfMediaLikedType[T] forSome { type T }


    val getusersSelfMediaLikedActionConstructor  = new getusersSelfMediaLikedSecureAction("basic", "comments", "relationships", "likes")

def getusersSelfMediaLikedAction[T] = (f: getusersSelfMediaLikedActionType[T]) => (count: MediaId, max_like_id: MediaId) => getusersSelfMediaLikedActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersSelfMediaLikedResponseMimeType =>
            
            

                val result =
                        new UsersSelfMediaLikedGetValidator(count, max_like_id).errors match {
                            case e if e.isEmpty => processValidgetusersSelfMediaLikedRequest(f)((count, max_like_id))(getusersSelfMediaLikedResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersSelfMediaLikedResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetusersSelfMediaLikedRequest[T](f: getusersSelfMediaLikedActionType[T])(request: getusersSelfMediaLikedActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GettagsByTag_nameType[T] extends ResultWrapper[T]
    case class GettagsByTag_name200(result: Tag)(implicit val writer: String => Option[Writeable[Tag]]) extends GettagsByTag_nameType[Tag] { val statusCode = 200 }
    

    private type gettagsByTag_nameActionRequestType       = (String)
    private type gettagsByTag_nameActionType[T]            = gettagsByTag_nameActionRequestType => GettagsByTag_nameType[T] forSome { type T }


    val gettagsByTag_nameActionConstructor  = new gettagsByTag_nameSecureAction("basic", "comments", "relationships", "likes")

def gettagsByTag_nameAction[T] = (f: gettagsByTag_nameActionType[T]) => (tag_name: String) => gettagsByTag_nameActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { gettagsByTag_nameResponseMimeType =>
            
            

                val result =
                        new TagsTag_nameGetValidator(tag_name).errors match {
                            case e if e.isEmpty => processValidgettagsByTag_nameRequest(f)((tag_name))(gettagsByTag_nameResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gettagsByTag_nameResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgettagsByTag_nameRequest[T](f: gettagsByTag_nameActionType[T])(request: gettagsByTag_nameActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GettagsSearchType[T] extends ResultWrapper[T]
    case class GettagsSearch200(result: TagsSearchGetResponses200)(implicit val writer: String => Option[Writeable[TagsSearchGetResponses200]]) extends GettagsSearchType[TagsSearchGetResponses200] { val statusCode = 200 }
    

    private type gettagsSearchActionRequestType       = (MediaFilter)
    private type gettagsSearchActionType[T]            = gettagsSearchActionRequestType => GettagsSearchType[T] forSome { type T }


    val gettagsSearchActionConstructor  = new gettagsSearchSecureAction("basic", "comments", "relationships", "likes")

def gettagsSearchAction[T] = (f: gettagsSearchActionType[T]) => (q: MediaFilter) => gettagsSearchActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { gettagsSearchResponseMimeType =>
            
            

                val result =
                        new TagsSearchGetValidator(q).errors match {
                            case e if e.isEmpty => processValidgettagsSearchRequest(f)((q))(gettagsSearchResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gettagsSearchResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgettagsSearchRequest[T](f: gettagsSearchActionType[T])(request: gettagsSearchActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetusersByUser_idFollowed_byType[T] extends ResultWrapper[T]
    case class GetusersByUser_idFollowed_by200(result: UsersUser_idFollowsGetResponses200)(implicit val writer: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) extends GetusersByUser_idFollowed_byType[UsersUser_idFollowsGetResponses200] { val statusCode = 200 }
    

    private type getusersByUser_idFollowed_byActionRequestType       = (BigDecimal)
    private type getusersByUser_idFollowed_byActionType[T]            = getusersByUser_idFollowed_byActionRequestType => GetusersByUser_idFollowed_byType[T] forSome { type T }


    val getusersByUser_idFollowed_byActionConstructor  = new getusersByUser_idFollowed_bySecureAction("basic", "comments", "relationships", "likes")

def getusersByUser_idFollowed_byAction[T] = (f: getusersByUser_idFollowed_byActionType[T]) => (user_id: BigDecimal) => getusersByUser_idFollowed_byActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersByUser_idFollowed_byResponseMimeType =>
            
            

                val result =
                        new UsersUser_idFollowed_byGetValidator(user_id).errors match {
                            case e if e.isEmpty => processValidgetusersByUser_idFollowed_byRequest(f)((user_id))(getusersByUser_idFollowed_byResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersByUser_idFollowed_byResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetusersByUser_idFollowed_byRequest[T](f: getusersByUser_idFollowed_byActionType[T])(request: getusersByUser_idFollowed_byActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetmediaByMedia_idCommentsType[T] extends ResultWrapper[T]
    case class GetmediaByMedia_idComments200(result: MediaMedia_idCommentsGetResponses200)(implicit val writer: String => Option[Writeable[MediaMedia_idCommentsGetResponses200]]) extends GetmediaByMedia_idCommentsType[MediaMedia_idCommentsGetResponses200] { val statusCode = 200 }
    

    private type getmediaByMedia_idCommentsActionRequestType       = (BigInt)
    private type getmediaByMedia_idCommentsActionType[T]            = getmediaByMedia_idCommentsActionRequestType => GetmediaByMedia_idCommentsType[T] forSome { type T }


    val getmediaByMedia_idCommentsActionConstructor  = new getmediaByMedia_idCommentsSecureAction("basic", "comments", "relationships", "likes")

def getmediaByMedia_idCommentsAction[T] = (f: getmediaByMedia_idCommentsActionType[T]) => (media_id: BigInt) => getmediaByMedia_idCommentsActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaByMedia_idCommentsResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idCommentsGetValidator(media_id).errors match {
                            case e if e.isEmpty => processValidgetmediaByMedia_idCommentsRequest(f)((media_id))(getmediaByMedia_idCommentsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaByMedia_idCommentsResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetmediaByMedia_idCommentsRequest[T](f: getmediaByMedia_idCommentsActionType[T])(request: getmediaByMedia_idCommentsActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait PostmediaByMedia_idCommentsType[T] extends ResultWrapper[T]
    case class PostmediaByMedia_idComments200(result: MediaMedia_idCommentsDeleteResponses200)(implicit val writer: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) extends PostmediaByMedia_idCommentsType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200 }
    

    private type postmediaByMedia_idCommentsActionRequestType       = (BigInt, LocationLatitude)
    private type postmediaByMedia_idCommentsActionType[T]            = postmediaByMedia_idCommentsActionRequestType => PostmediaByMedia_idCommentsType[T] forSome { type T }

        private def postmediaByMedia_idCommentsParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.optionParser[BigDecimal]
            optionParser[BigDecimal](bodyMimeType, customParsers, "Invalid LocationLatitude", maxLength) _
        }

    val postmediaByMedia_idCommentsActionConstructor  = new postmediaByMedia_idCommentsSecureAction("comments")

def postmediaByMedia_idCommentsAction[T] = (f: postmediaByMedia_idCommentsActionType[T]) => (media_id: BigInt) => postmediaByMedia_idCommentsActionConstructor(BodyParsers.parse.using(postmediaByMedia_idCommentsParser(Seq[String]("application/json")))) { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { postmediaByMedia_idCommentsResponseMimeType =>
            val tEXT = request.body
            
            

                val result =
                        new MediaMedia_idCommentsPostValidator(media_id, tEXT).errors match {
                            case e if e.isEmpty => processValidpostmediaByMedia_idCommentsRequest(f)((media_id, tEXT))(postmediaByMedia_idCommentsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postmediaByMedia_idCommentsResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidpostmediaByMedia_idCommentsRequest[T](f: postmediaByMedia_idCommentsActionType[T])(request: postmediaByMedia_idCommentsActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait DeletemediaByMedia_idCommentsType[T] extends ResultWrapper[T]
    case class DeletemediaByMedia_idComments200(result: MediaMedia_idCommentsDeleteResponses200)(implicit val writer: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) extends DeletemediaByMedia_idCommentsType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200 }
    

    private type deletemediaByMedia_idCommentsActionRequestType       = (BigInt)
    private type deletemediaByMedia_idCommentsActionType[T]            = deletemediaByMedia_idCommentsActionRequestType => DeletemediaByMedia_idCommentsType[T] forSome { type T }


    val deletemediaByMedia_idCommentsActionConstructor  = new deletemediaByMedia_idCommentsSecureAction("basic", "comments", "relationships", "likes")

def deletemediaByMedia_idCommentsAction[T] = (f: deletemediaByMedia_idCommentsActionType[T]) => (media_id: BigInt) => deletemediaByMedia_idCommentsActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { deletemediaByMedia_idCommentsResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idCommentsDeleteValidator(media_id).errors match {
                            case e if e.isEmpty => processValiddeletemediaByMedia_idCommentsRequest(f)((media_id))(deletemediaByMedia_idCommentsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deletemediaByMedia_idCommentsResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValiddeletemediaByMedia_idCommentsRequest[T](f: deletemediaByMedia_idCommentsActionType[T])(request: deletemediaByMedia_idCommentsActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GettagsByTag_nameMediaRecentType[T] extends ResultWrapper[T]
    case class GettagsByTag_nameMediaRecent200(result: TagsTag_nameMediaRecentGetResponses200)(implicit val writer: String => Option[Writeable[TagsTag_nameMediaRecentGetResponses200]]) extends GettagsByTag_nameMediaRecentType[TagsTag_nameMediaRecentGetResponses200] { val statusCode = 200 }
    

    private type gettagsByTag_nameMediaRecentActionRequestType       = (String)
    private type gettagsByTag_nameMediaRecentActionType[T]            = gettagsByTag_nameMediaRecentActionRequestType => GettagsByTag_nameMediaRecentType[T] forSome { type T }


    val gettagsByTag_nameMediaRecentActionConstructor  = new gettagsByTag_nameMediaRecentSecureAction("basic", "comments", "relationships", "likes")

def gettagsByTag_nameMediaRecentAction[T] = (f: gettagsByTag_nameMediaRecentActionType[T]) => (tag_name: String) => gettagsByTag_nameMediaRecentActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { gettagsByTag_nameMediaRecentResponseMimeType =>
            
            

                val result =
                        new TagsTag_nameMediaRecentGetValidator(tag_name).errors match {
                            case e if e.isEmpty => processValidgettagsByTag_nameMediaRecentRequest(f)((tag_name))(gettagsByTag_nameMediaRecentResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gettagsByTag_nameMediaRecentResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgettagsByTag_nameMediaRecentRequest[T](f: gettagsByTag_nameMediaRecentActionType[T])(request: gettagsByTag_nameMediaRecentActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait PostusersByUser_idRelationshipType[T] extends ResultWrapper[T]
    case class PostusersByUser_idRelationship200(result: UsersUser_idFollowsGetResponses200)(implicit val writer: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) extends PostusersByUser_idRelationshipType[UsersUser_idFollowsGetResponses200] { val statusCode = 200 }
    

    private type postusersByUser_idRelationshipActionRequestType       = (BigDecimal, UsersUser_idRelationshipPostAction)
    private type postusersByUser_idRelationshipActionType[T]            = postusersByUser_idRelationshipActionRequestType => PostusersByUser_idRelationshipType[T] forSome { type T }

        private def postusersByUser_idRelationshipParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.optionParser[UsersUser_idRelationshipPostActionOpt]
            optionParser[UsersUser_idRelationshipPostActionOpt](bodyMimeType, customParsers, "Invalid UsersUser_idRelationshipPostAction", maxLength) _
        }

    val postusersByUser_idRelationshipActionConstructor  = new postusersByUser_idRelationshipSecureAction("relationships")

def postusersByUser_idRelationshipAction[T] = (f: postusersByUser_idRelationshipActionType[T]) => (user_id: BigDecimal) => postusersByUser_idRelationshipActionConstructor(BodyParsers.parse.using(postusersByUser_idRelationshipParser(Seq[String]("application/json")))) { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { postusersByUser_idRelationshipResponseMimeType =>
            val action = request.body
            
            

                val result =
                        new UsersUser_idRelationshipPostValidator(user_id, action).errors match {
                            case e if e.isEmpty => processValidpostusersByUser_idRelationshipRequest(f)((user_id, action))(postusersByUser_idRelationshipResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postusersByUser_idRelationshipResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidpostusersByUser_idRelationshipRequest[T](f: postusersByUser_idRelationshipActionType[T])(request: postusersByUser_idRelationshipActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetusersSelfFeedType[T] extends ResultWrapper[T]
    case class GetusersSelfFeed200(result: UsersSelfFeedGetResponses200)(implicit val writer: String => Option[Writeable[UsersSelfFeedGetResponses200]]) extends GetusersSelfFeedType[UsersSelfFeedGetResponses200] { val statusCode = 200 }
    

    private type getusersSelfFeedActionRequestType       = (MediaId, MediaId, MediaId)
    private type getusersSelfFeedActionType[T]            = getusersSelfFeedActionRequestType => GetusersSelfFeedType[T] forSome { type T }


    val getusersSelfFeedActionConstructor  = new getusersSelfFeedSecureAction("basic", "comments", "relationships", "likes")

def getusersSelfFeedAction[T] = (f: getusersSelfFeedActionType[T]) => (count: MediaId, max_id: MediaId, min_id: MediaId) => getusersSelfFeedActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersSelfFeedResponseMimeType =>
            
            

                val result =
                        new UsersSelfFeedGetValidator(count, max_id, min_id).errors match {
                            case e if e.isEmpty => processValidgetusersSelfFeedRequest(f)((count, max_id, min_id))(getusersSelfFeedResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersSelfFeedResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetusersSelfFeedRequest[T](f: getusersSelfFeedActionType[T])(request: getusersSelfFeedActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetusersByUser_idType[T] extends ResultWrapper[T]
    case class GetusersByUser_id200(result: UsersUser_idGetResponses200)(implicit val writer: String => Option[Writeable[UsersUser_idGetResponses200]]) extends GetusersByUser_idType[UsersUser_idGetResponses200] { val statusCode = 200 }
    

    private type getusersByUser_idActionRequestType       = (BigDecimal)
    private type getusersByUser_idActionType[T]            = getusersByUser_idActionRequestType => GetusersByUser_idType[T] forSome { type T }


    val getusersByUser_idActionConstructor  = new getusersByUser_idSecureAction("basic")

def getusersByUser_idAction[T] = (f: getusersByUser_idActionType[T]) => (user_id: BigDecimal) => getusersByUser_idActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersByUser_idResponseMimeType =>
            
            

                val result =
                        new UsersUser_idGetValidator(user_id).errors match {
                            case e if e.isEmpty => processValidgetusersByUser_idRequest(f)((user_id))(getusersByUser_idResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersByUser_idResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetusersByUser_idRequest[T](f: getusersByUser_idActionType[T])(request: getusersByUser_idActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetmediaSearchType[T] extends ResultWrapper[T]
    case class GetmediaSearch200(result: MediaSearchGetResponses200)(implicit val writer: String => Option[Writeable[MediaSearchGetResponses200]]) extends GetmediaSearchType[MediaSearchGetResponses200] { val statusCode = 200 }
    

    private type getmediaSearchActionRequestType       = (MediaId, BigInt, LocationLatitude, MediaId, LocationLatitude)
    private type getmediaSearchActionType[T]            = getmediaSearchActionRequestType => GetmediaSearchType[T] forSome { type T }


    val getmediaSearchActionConstructor  = new getmediaSearchSecureAction("basic", "comments", "relationships", "likes")

def getmediaSearchAction[T] = (f: getmediaSearchActionType[T]) => (mAX_TIMESTAMP: MediaId, dISTANCE: BigInt, lNG: LocationLatitude, mIN_TIMESTAMP: MediaId, lAT: LocationLatitude) => getmediaSearchActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaSearchResponseMimeType =>
            
            

                val result =
                        new MediaSearchGetValidator(mAX_TIMESTAMP, dISTANCE, lNG, mIN_TIMESTAMP, lAT).errors match {
                            case e if e.isEmpty => processValidgetmediaSearchRequest(f)((mAX_TIMESTAMP, dISTANCE, lNG, mIN_TIMESTAMP, lAT))(getmediaSearchResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaSearchResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetmediaSearchRequest[T](f: getmediaSearchActionType[T])(request: getmediaSearchActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetgeographiesByGeo_idMediaRecentType[T] extends ResultWrapper[T]
    
    case class GetgeographiesByGeo_idMediaRecent200(headers: Seq[(String, String)] = Nil) extends EmptyReturn(200, headers)
    

    private type getgeographiesByGeo_idMediaRecentActionRequestType       = (BigInt, MediaId, MediaId)
    private type getgeographiesByGeo_idMediaRecentActionType[T]            = getgeographiesByGeo_idMediaRecentActionRequestType => GetgeographiesByGeo_idMediaRecentType[T] forSome { type T }


    val getgeographiesByGeo_idMediaRecentActionConstructor  = new getgeographiesByGeo_idMediaRecentSecureAction("basic", "comments", "relationships", "likes")

def getgeographiesByGeo_idMediaRecentAction[T] = (f: getgeographiesByGeo_idMediaRecentActionType[T]) => (geo_id: BigInt, count: MediaId, min_id: MediaId) => getgeographiesByGeo_idMediaRecentActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getgeographiesByGeo_idMediaRecentResponseMimeType =>
            
            

                val result =
                        new GeographiesGeo_idMediaRecentGetValidator(geo_id, count, min_id).errors match {
                            case e if e.isEmpty => processValidgetgeographiesByGeo_idMediaRecentRequest(f)((geo_id, count, min_id))(getgeographiesByGeo_idMediaRecentResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getgeographiesByGeo_idMediaRecentResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetgeographiesByGeo_idMediaRecentRequest[T](f: getgeographiesByGeo_idMediaRecentActionType[T])(request: getgeographiesByGeo_idMediaRecentActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetmediaByShortcodeType[T] extends ResultWrapper[T]
    case class GetmediaByShortcode200(result: Media)(implicit val writer: String => Option[Writeable[Media]]) extends GetmediaByShortcodeType[Media] { val statusCode = 200 }
    

    private type getmediaByShortcodeActionRequestType       = (String)
    private type getmediaByShortcodeActionType[T]            = getmediaByShortcodeActionRequestType => GetmediaByShortcodeType[T] forSome { type T }


    val getmediaByShortcodeActionConstructor  = new getmediaByShortcodeSecureAction("basic", "comments", "relationships", "likes")

def getmediaByShortcodeAction[T] = (f: getmediaByShortcodeActionType[T]) => (shortcode: String) => getmediaByShortcodeActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaByShortcodeResponseMimeType =>
            
            

                val result =
                        new MediaShortcodeGetValidator(shortcode).errors match {
                            case e if e.isEmpty => processValidgetmediaByShortcodeRequest(f)((shortcode))(getmediaByShortcodeResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaByShortcodeResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetmediaByShortcodeRequest[T](f: getmediaByShortcodeActionType[T])(request: getmediaByShortcodeActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetlocationsSearchType[T] extends ResultWrapper[T]
    case class GetlocationsSearch200(result: LocationsSearchGetResponses200)(implicit val writer: String => Option[Writeable[LocationsSearchGetResponses200]]) extends GetlocationsSearchType[LocationsSearchGetResponses200] { val statusCode = 200 }
    

    private type getlocationsSearchActionRequestType       = (MediaId, MediaId, MediaId, LocationLatitude, MediaId, LocationLatitude)
    private type getlocationsSearchActionType[T]            = getlocationsSearchActionRequestType => GetlocationsSearchType[T] forSome { type T }


    val getlocationsSearchActionConstructor  = new getlocationsSearchSecureAction("basic", "comments", "relationships", "likes")

def getlocationsSearchAction[T] = (f: getlocationsSearchActionType[T]) => (foursquare_v2_id: MediaId, facebook_places_id: MediaId, distance: MediaId, lat: LocationLatitude, foursquare_id: MediaId, lng: LocationLatitude) => getlocationsSearchActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getlocationsSearchResponseMimeType =>
            
            

                val result =
                        new LocationsSearchGetValidator(foursquare_v2_id, facebook_places_id, distance, lat, foursquare_id, lng).errors match {
                            case e if e.isEmpty => processValidgetlocationsSearchRequest(f)((foursquare_v2_id, facebook_places_id, distance, lat, foursquare_id, lng))(getlocationsSearchResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getlocationsSearchResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetlocationsSearchRequest[T](f: getlocationsSearchActionType[T])(request: getlocationsSearchActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetusersSelfRequested_byType[T] extends ResultWrapper[T]
    case class GetusersSelfRequested_by200(result: UsersSelfRequested_byGetResponses200)(implicit val writer: String => Option[Writeable[UsersSelfRequested_byGetResponses200]]) extends GetusersSelfRequested_byType[UsersSelfRequested_byGetResponses200] { val statusCode = 200 }
    

    private type getusersSelfRequested_byActionRequestType       = (Unit)
    private type getusersSelfRequested_byActionType[T]            = getusersSelfRequested_byActionRequestType => GetusersSelfRequested_byType[T] forSome { type T }


    val getusersSelfRequested_byActionConstructor  = new getusersSelfRequested_bySecureAction("basic", "comments", "relationships", "likes")

def getusersSelfRequested_byAction[T] = (f: getusersSelfRequested_byActionType[T]) => getusersSelfRequested_byActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersSelfRequested_byResponseMimeType =>
            
            

                val result = processValidgetusersSelfRequested_byRequest(f)()(getusersSelfRequested_byResponseMimeType)
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetusersSelfRequested_byRequest[T](f: getusersSelfRequested_byActionType[T])(request: getusersSelfRequested_byActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetmediaByMedia_idType[T] extends ResultWrapper[T]
    case class GetmediaByMedia_id200(result: Media)(implicit val writer: String => Option[Writeable[Media]]) extends GetmediaByMedia_idType[Media] { val statusCode = 200 }
    

    private type getmediaByMedia_idActionRequestType       = (BigInt)
    private type getmediaByMedia_idActionType[T]            = getmediaByMedia_idActionRequestType => GetmediaByMedia_idType[T] forSome { type T }


    val getmediaByMedia_idActionConstructor  = new getmediaByMedia_idSecureAction("basic", "comments", "relationships", "likes")

def getmediaByMedia_idAction[T] = (f: getmediaByMedia_idActionType[T]) => (media_id: BigInt) => getmediaByMedia_idActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaByMedia_idResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idGetValidator(media_id).errors match {
                            case e if e.isEmpty => processValidgetmediaByMedia_idRequest(f)((media_id))(getmediaByMedia_idResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaByMedia_idResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetmediaByMedia_idRequest[T](f: getmediaByMedia_idActionType[T])(request: getmediaByMedia_idActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetlocationsByLocation_idMediaRecentType[T] extends ResultWrapper[T]
    case class GetlocationsByLocation_idMediaRecent200(result: UsersSelfFeedGetResponses200)(implicit val writer: String => Option[Writeable[UsersSelfFeedGetResponses200]]) extends GetlocationsByLocation_idMediaRecentType[UsersSelfFeedGetResponses200] { val statusCode = 200 }
    

    private type getlocationsByLocation_idMediaRecentActionRequestType       = (BigInt, MediaId, MediaId, MediaFilter, MediaFilter)
    private type getlocationsByLocation_idMediaRecentActionType[T]            = getlocationsByLocation_idMediaRecentActionRequestType => GetlocationsByLocation_idMediaRecentType[T] forSome { type T }


    val getlocationsByLocation_idMediaRecentActionConstructor  = new getlocationsByLocation_idMediaRecentSecureAction("basic", "comments", "relationships", "likes")

def getlocationsByLocation_idMediaRecentAction[T] = (f: getlocationsByLocation_idMediaRecentActionType[T]) => (location_id: BigInt, max_timestamp: MediaId, min_timestamp: MediaId, min_id: MediaFilter, max_id: MediaFilter) => getlocationsByLocation_idMediaRecentActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getlocationsByLocation_idMediaRecentResponseMimeType =>
            
            

                val result =
                        new LocationsLocation_idMediaRecentGetValidator(location_id, max_timestamp, min_timestamp, min_id, max_id).errors match {
                            case e if e.isEmpty => processValidgetlocationsByLocation_idMediaRecentRequest(f)((location_id, max_timestamp, min_timestamp, min_id, max_id))(getlocationsByLocation_idMediaRecentResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getlocationsByLocation_idMediaRecentResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetlocationsByLocation_idMediaRecentRequest[T](f: getlocationsByLocation_idMediaRecentActionType[T])(request: getlocationsByLocation_idMediaRecentActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetusersByUser_idMediaRecentType[T] extends ResultWrapper[T]
    case class GetusersByUser_idMediaRecent200(result: UsersSelfFeedGetResponses200)(implicit val writer: String => Option[Writeable[UsersSelfFeedGetResponses200]]) extends GetusersByUser_idMediaRecentType[UsersSelfFeedGetResponses200] { val statusCode = 200 }
    

    private type getusersByUser_idMediaRecentActionRequestType       = (BigDecimal, MediaId, MediaFilter, MediaId, MediaFilter, MediaId)
    private type getusersByUser_idMediaRecentActionType[T]            = getusersByUser_idMediaRecentActionRequestType => GetusersByUser_idMediaRecentType[T] forSome { type T }


    val getusersByUser_idMediaRecentActionConstructor  = new getusersByUser_idMediaRecentSecureAction("basic", "comments", "relationships", "likes")

def getusersByUser_idMediaRecentAction[T] = (f: getusersByUser_idMediaRecentActionType[T]) => (user_id: BigDecimal, max_timestamp: MediaId, min_id: MediaFilter, min_timestamp: MediaId, max_id: MediaFilter, count: MediaId) => getusersByUser_idMediaRecentActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersByUser_idMediaRecentResponseMimeType =>
            
            

                val result =
                        new UsersUser_idMediaRecentGetValidator(user_id, max_timestamp, min_id, min_timestamp, max_id, count).errors match {
                            case e if e.isEmpty => processValidgetusersByUser_idMediaRecentRequest(f)((user_id, max_timestamp, min_id, min_timestamp, max_id, count))(getusersByUser_idMediaRecentResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersByUser_idMediaRecentResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetusersByUser_idMediaRecentRequest[T](f: getusersByUser_idMediaRecentActionType[T])(request: getusersByUser_idMediaRecentActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetmediaPopularType[T] extends ResultWrapper[T]
    case class GetmediaPopular200(result: UsersSelfFeedGetResponses200)(implicit val writer: String => Option[Writeable[UsersSelfFeedGetResponses200]]) extends GetmediaPopularType[UsersSelfFeedGetResponses200] { val statusCode = 200 }
    

    private type getmediaPopularActionRequestType       = (Unit)
    private type getmediaPopularActionType[T]            = getmediaPopularActionRequestType => GetmediaPopularType[T] forSome { type T }


    val getmediaPopularActionConstructor  = new getmediaPopularSecureAction("basic", "comments", "relationships", "likes")

def getmediaPopularAction[T] = (f: getmediaPopularActionType[T]) => getmediaPopularActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaPopularResponseMimeType =>
            
            

                val result = processValidgetmediaPopularRequest(f)()(getmediaPopularResponseMimeType)
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetmediaPopularRequest[T](f: getmediaPopularActionType[T])(request: getmediaPopularActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetmediaByMedia_idLikesType[Result] with PostmediaByMedia_idLikesType[Result] with DeletemediaByMedia_idLikesType[Result] with GetusersByUser_idFollowsType[Result] with GetlocationsByLocation_idType[Result] with GetusersSearchType[Result] with GetusersSelfMediaLikedType[Result] with GettagsByTag_nameType[Result] with GettagsSearchType[Result] with GetusersByUser_idFollowed_byType[Result] with GetmediaByMedia_idCommentsType[Result] with PostmediaByMedia_idCommentsType[Result] with DeletemediaByMedia_idCommentsType[Result] with GettagsByTag_nameMediaRecentType[Result] with PostusersByUser_idRelationshipType[Result] with GetusersSelfFeedType[Result] with GetusersByUser_idType[Result] with GetmediaSearchType[Result] with GetgeographiesByGeo_idMediaRecentType[Result] with GetmediaByShortcodeType[Result] with GetlocationsSearchType[Result] with GetusersSelfRequested_byType[Result] with GetmediaByMedia_idType[Result] with GetlocationsByLocation_idMediaRecentType[Result] with GetusersByUser_idMediaRecentType[Result] with GetmediaPopularType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with GetmediaByMedia_idLikesType[Results.EmptyContent] with PostmediaByMedia_idLikesType[Results.EmptyContent] with DeletemediaByMedia_idLikesType[Results.EmptyContent] with GetusersByUser_idFollowsType[Results.EmptyContent] with GetlocationsByLocation_idType[Results.EmptyContent] with GetusersSearchType[Results.EmptyContent] with GetusersSelfMediaLikedType[Results.EmptyContent] with GettagsByTag_nameType[Results.EmptyContent] with GettagsSearchType[Results.EmptyContent] with GetusersByUser_idFollowed_byType[Results.EmptyContent] with GetmediaByMedia_idCommentsType[Results.EmptyContent] with PostmediaByMedia_idCommentsType[Results.EmptyContent] with DeletemediaByMedia_idCommentsType[Results.EmptyContent] with GettagsByTag_nameMediaRecentType[Results.EmptyContent] with PostusersByUser_idRelationshipType[Results.EmptyContent] with GetusersSelfFeedType[Results.EmptyContent] with GetusersByUser_idType[Results.EmptyContent] with GetmediaSearchType[Results.EmptyContent] with GetgeographiesByGeo_idMediaRecentType[Results.EmptyContent] with GetmediaByShortcodeType[Results.EmptyContent] with GetlocationsSearchType[Results.EmptyContent] with GetusersSelfRequested_byType[Results.EmptyContent] with GetmediaByMedia_idType[Results.EmptyContent] with GetlocationsByLocation_idMediaRecentType[Results.EmptyContent] with GetusersByUser_idMediaRecentType[Results.EmptyContent] with GetmediaPopularType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
