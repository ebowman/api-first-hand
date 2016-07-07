package instagram.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import scala.math.BigInt
import scala.math.BigDecimal

import de.zalando.play.controllers.PlayPathBindables





//noinspection ScalaStyle
trait InstagramApiYamlBase extends Controller with PlayBodyParsing  with InstagramApiYamlSecurity {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait GetmediaByMedia_idLikesType[T] extends ResultWrapper[T]
    def GetmediaByMedia_idLikes200(resultP: MediaMedia_idLikesGetResponses200)(implicit writerP: String => Option[Writeable[MediaMedia_idLikesGetResponses200]]) = success(new GetmediaByMedia_idLikesType[MediaMedia_idLikesGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetmediaByMedia_idLikes200(resultF: Future[MediaMedia_idLikesGetResponses200])(implicit writerP: String => Option[Writeable[MediaMedia_idLikesGetResponses200]]) = resultF map { resultP => (new GetmediaByMedia_idLikesType[MediaMedia_idLikesGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getmediaByMedia_idLikesActionRequestType       = (BigInt)
    private type getmediaByMedia_idLikesActionType[T]            = getmediaByMedia_idLikesActionRequestType => Future[GetmediaByMedia_idLikesType[T] forSome { type T }]


    val getmediaByMedia_idLikesActionConstructor  = new getmediaByMedia_idLikesSecureAction("basic", "comments", "relationships", "likes")

def getmediaByMedia_idLikesAction[T] = (f: getmediaByMedia_idLikesActionType[T]) => (media_id: BigInt) => getmediaByMedia_idLikesActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaByMedia_idLikesResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idLikesGetValidator(media_id).errors match {
                            case e if e.isEmpty => processValidgetmediaByMedia_idLikesRequest(f)((media_id))(getmediaByMedia_idLikesResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaByMedia_idLikesResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetmediaByMedia_idLikesRequest[T](f: getmediaByMedia_idLikesActionType[T])(request: getmediaByMedia_idLikesActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PostmediaByMedia_idLikesType[T] extends ResultWrapper[T]
    def PostmediaByMedia_idLikes200(resultP: MediaMedia_idCommentsDeleteResponses200)(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) = success(new PostmediaByMedia_idLikesType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def PostmediaByMedia_idLikes200(resultF: Future[MediaMedia_idCommentsDeleteResponses200])(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) = resultF map { resultP => (new PostmediaByMedia_idLikesType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postmediaByMedia_idLikesActionRequestType       = (BigInt)
    private type postmediaByMedia_idLikesActionType[T]            = postmediaByMedia_idLikesActionRequestType => Future[PostmediaByMedia_idLikesType[T] forSome { type T }]


    val postmediaByMedia_idLikesActionConstructor  = new postmediaByMedia_idLikesSecureAction("comments")

def postmediaByMedia_idLikesAction[T] = (f: postmediaByMedia_idLikesActionType[T]) => (media_id: BigInt) => postmediaByMedia_idLikesActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { postmediaByMedia_idLikesResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idLikesPostValidator(media_id).errors match {
                            case e if e.isEmpty => processValidpostmediaByMedia_idLikesRequest(f)((media_id))(postmediaByMedia_idLikesResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postmediaByMedia_idLikesResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpostmediaByMedia_idLikesRequest[T](f: postmediaByMedia_idLikesActionType[T])(request: postmediaByMedia_idLikesActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait DeletemediaByMedia_idLikesType[T] extends ResultWrapper[T]
    def DeletemediaByMedia_idLikes200(resultP: MediaMedia_idCommentsDeleteResponses200)(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) = success(new DeletemediaByMedia_idLikesType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def DeletemediaByMedia_idLikes200(resultF: Future[MediaMedia_idCommentsDeleteResponses200])(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) = resultF map { resultP => (new DeletemediaByMedia_idLikesType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type deletemediaByMedia_idLikesActionRequestType       = (BigInt)
    private type deletemediaByMedia_idLikesActionType[T]            = deletemediaByMedia_idLikesActionRequestType => Future[DeletemediaByMedia_idLikesType[T] forSome { type T }]


    val deletemediaByMedia_idLikesActionConstructor  = new deletemediaByMedia_idLikesSecureAction("basic", "comments", "relationships", "likes")

def deletemediaByMedia_idLikesAction[T] = (f: deletemediaByMedia_idLikesActionType[T]) => (media_id: BigInt) => deletemediaByMedia_idLikesActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { deletemediaByMedia_idLikesResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idLikesDeleteValidator(media_id).errors match {
                            case e if e.isEmpty => processValiddeletemediaByMedia_idLikesRequest(f)((media_id))(deletemediaByMedia_idLikesResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deletemediaByMedia_idLikesResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValiddeletemediaByMedia_idLikesRequest[T](f: deletemediaByMedia_idLikesActionType[T])(request: deletemediaByMedia_idLikesActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetusersByUser_idFollowsType[T] extends ResultWrapper[T]
    def GetusersByUser_idFollows200(resultP: UsersUser_idFollowsGetResponses200)(implicit writerP: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) = success(new GetusersByUser_idFollowsType[UsersUser_idFollowsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersByUser_idFollows200(resultF: Future[UsersUser_idFollowsGetResponses200])(implicit writerP: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) = resultF map { resultP => (new GetusersByUser_idFollowsType[UsersUser_idFollowsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersByUser_idFollowsActionRequestType       = (BigDecimal)
    private type getusersByUser_idFollowsActionType[T]            = getusersByUser_idFollowsActionRequestType => Future[GetusersByUser_idFollowsType[T] forSome { type T }]


    val getusersByUser_idFollowsActionConstructor  = new getusersByUser_idFollowsSecureAction("basic", "comments", "relationships", "likes")

def getusersByUser_idFollowsAction[T] = (f: getusersByUser_idFollowsActionType[T]) => (user_id: BigDecimal) => getusersByUser_idFollowsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersByUser_idFollowsResponseMimeType =>
            
            

                val result =
                        new UsersUser_idFollowsGetValidator(user_id).errors match {
                            case e if e.isEmpty => processValidgetusersByUser_idFollowsRequest(f)((user_id))(getusersByUser_idFollowsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersByUser_idFollowsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetusersByUser_idFollowsRequest[T](f: getusersByUser_idFollowsActionType[T])(request: getusersByUser_idFollowsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetlocationsByLocation_idType[T] extends ResultWrapper[T]
    def GetlocationsByLocation_id200(resultP: LocationsLocation_idGetResponses200)(implicit writerP: String => Option[Writeable[LocationsLocation_idGetResponses200]]) = success(new GetlocationsByLocation_idType[LocationsLocation_idGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetlocationsByLocation_id200(resultF: Future[LocationsLocation_idGetResponses200])(implicit writerP: String => Option[Writeable[LocationsLocation_idGetResponses200]]) = resultF map { resultP => (new GetlocationsByLocation_idType[LocationsLocation_idGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getlocationsByLocation_idActionRequestType       = (BigInt)
    private type getlocationsByLocation_idActionType[T]            = getlocationsByLocation_idActionRequestType => Future[GetlocationsByLocation_idType[T] forSome { type T }]


    val getlocationsByLocation_idActionConstructor  = new getlocationsByLocation_idSecureAction("basic", "comments", "relationships", "likes")

def getlocationsByLocation_idAction[T] = (f: getlocationsByLocation_idActionType[T]) => (location_id: BigInt) => getlocationsByLocation_idActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getlocationsByLocation_idResponseMimeType =>
            
            

                val result =
                        new LocationsLocation_idGetValidator(location_id).errors match {
                            case e if e.isEmpty => processValidgetlocationsByLocation_idRequest(f)((location_id))(getlocationsByLocation_idResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getlocationsByLocation_idResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetlocationsByLocation_idRequest[T](f: getlocationsByLocation_idActionType[T])(request: getlocationsByLocation_idActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetusersSearchType[T] extends ResultWrapper[T]
    def GetusersSearch200(resultP: UsersUser_idFollowsGetResponses200)(implicit writerP: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) = success(new GetusersSearchType[UsersUser_idFollowsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersSearch200(resultF: Future[UsersUser_idFollowsGetResponses200])(implicit writerP: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) = resultF map { resultP => (new GetusersSearchType[UsersUser_idFollowsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersSearchActionRequestType       = (String, MediaFilter)
    private type getusersSearchActionType[T]            = getusersSearchActionRequestType => Future[GetusersSearchType[T] forSome { type T }]


    val getusersSearchActionConstructor  = new getusersSearchSecureAction("basic", "comments", "relationships", "likes")

def getusersSearchAction[T] = (f: getusersSearchActionType[T]) => (q: String, count: MediaFilter) => getusersSearchActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersSearchResponseMimeType =>
            
            

                val result =
                        new UsersSearchGetValidator(q, count).errors match {
                            case e if e.isEmpty => processValidgetusersSearchRequest(f)((q, count))(getusersSearchResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersSearchResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetusersSearchRequest[T](f: getusersSearchActionType[T])(request: getusersSearchActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetusersSelfMediaLikedType[T] extends ResultWrapper[T]
    def GetusersSelfMediaLiked200(resultP: UsersSelfFeedGetResponses200)(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = success(new GetusersSelfMediaLikedType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersSelfMediaLiked200(resultF: Future[UsersSelfFeedGetResponses200])(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = resultF map { resultP => (new GetusersSelfMediaLikedType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersSelfMediaLikedActionRequestType       = (MediaId, MediaId)
    private type getusersSelfMediaLikedActionType[T]            = getusersSelfMediaLikedActionRequestType => Future[GetusersSelfMediaLikedType[T] forSome { type T }]


    val getusersSelfMediaLikedActionConstructor  = new getusersSelfMediaLikedSecureAction("basic", "comments", "relationships", "likes")

def getusersSelfMediaLikedAction[T] = (f: getusersSelfMediaLikedActionType[T]) => (count: MediaId, max_like_id: MediaId) => getusersSelfMediaLikedActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersSelfMediaLikedResponseMimeType =>
            
            

                val result =
                        new UsersSelfMediaLikedGetValidator(count, max_like_id).errors match {
                            case e if e.isEmpty => processValidgetusersSelfMediaLikedRequest(f)((count, max_like_id))(getusersSelfMediaLikedResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersSelfMediaLikedResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetusersSelfMediaLikedRequest[T](f: getusersSelfMediaLikedActionType[T])(request: getusersSelfMediaLikedActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GettagsByTag_nameType[T] extends ResultWrapper[T]
    def GettagsByTag_name200(resultP: Tag)(implicit writerP: String => Option[Writeable[Tag]]) = success(new GettagsByTag_nameType[Tag] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GettagsByTag_name200(resultF: Future[Tag])(implicit writerP: String => Option[Writeable[Tag]]) = resultF map { resultP => (new GettagsByTag_nameType[Tag] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type gettagsByTag_nameActionRequestType       = (String)
    private type gettagsByTag_nameActionType[T]            = gettagsByTag_nameActionRequestType => Future[GettagsByTag_nameType[T] forSome { type T }]


    val gettagsByTag_nameActionConstructor  = new gettagsByTag_nameSecureAction("basic", "comments", "relationships", "likes")

def gettagsByTag_nameAction[T] = (f: gettagsByTag_nameActionType[T]) => (tag_name: String) => gettagsByTag_nameActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { gettagsByTag_nameResponseMimeType =>
            
            

                val result =
                        new TagsTag_nameGetValidator(tag_name).errors match {
                            case e if e.isEmpty => processValidgettagsByTag_nameRequest(f)((tag_name))(gettagsByTag_nameResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gettagsByTag_nameResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgettagsByTag_nameRequest[T](f: gettagsByTag_nameActionType[T])(request: gettagsByTag_nameActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GettagsSearchType[T] extends ResultWrapper[T]
    def GettagsSearch200(resultP: TagsSearchGetResponses200)(implicit writerP: String => Option[Writeable[TagsSearchGetResponses200]]) = success(new GettagsSearchType[TagsSearchGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GettagsSearch200(resultF: Future[TagsSearchGetResponses200])(implicit writerP: String => Option[Writeable[TagsSearchGetResponses200]]) = resultF map { resultP => (new GettagsSearchType[TagsSearchGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type gettagsSearchActionRequestType       = (MediaFilter)
    private type gettagsSearchActionType[T]            = gettagsSearchActionRequestType => Future[GettagsSearchType[T] forSome { type T }]


    val gettagsSearchActionConstructor  = new gettagsSearchSecureAction("basic", "comments", "relationships", "likes")

def gettagsSearchAction[T] = (f: gettagsSearchActionType[T]) => (q: MediaFilter) => gettagsSearchActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { gettagsSearchResponseMimeType =>
            
            

                val result =
                        new TagsSearchGetValidator(q).errors match {
                            case e if e.isEmpty => processValidgettagsSearchRequest(f)((q))(gettagsSearchResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gettagsSearchResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgettagsSearchRequest[T](f: gettagsSearchActionType[T])(request: gettagsSearchActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetusersByUser_idFollowed_byType[T] extends ResultWrapper[T]
    def GetusersByUser_idFollowed_by200(resultP: UsersUser_idFollowsGetResponses200)(implicit writerP: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) = success(new GetusersByUser_idFollowed_byType[UsersUser_idFollowsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersByUser_idFollowed_by200(resultF: Future[UsersUser_idFollowsGetResponses200])(implicit writerP: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) = resultF map { resultP => (new GetusersByUser_idFollowed_byType[UsersUser_idFollowsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersByUser_idFollowed_byActionRequestType       = (BigDecimal)
    private type getusersByUser_idFollowed_byActionType[T]            = getusersByUser_idFollowed_byActionRequestType => Future[GetusersByUser_idFollowed_byType[T] forSome { type T }]


    val getusersByUser_idFollowed_byActionConstructor  = new getusersByUser_idFollowed_bySecureAction("basic", "comments", "relationships", "likes")

def getusersByUser_idFollowed_byAction[T] = (f: getusersByUser_idFollowed_byActionType[T]) => (user_id: BigDecimal) => getusersByUser_idFollowed_byActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersByUser_idFollowed_byResponseMimeType =>
            
            

                val result =
                        new UsersUser_idFollowed_byGetValidator(user_id).errors match {
                            case e if e.isEmpty => processValidgetusersByUser_idFollowed_byRequest(f)((user_id))(getusersByUser_idFollowed_byResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersByUser_idFollowed_byResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetusersByUser_idFollowed_byRequest[T](f: getusersByUser_idFollowed_byActionType[T])(request: getusersByUser_idFollowed_byActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetmediaByMedia_idCommentsType[T] extends ResultWrapper[T]
    def GetmediaByMedia_idComments200(resultP: MediaMedia_idCommentsGetResponses200)(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsGetResponses200]]) = success(new GetmediaByMedia_idCommentsType[MediaMedia_idCommentsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetmediaByMedia_idComments200(resultF: Future[MediaMedia_idCommentsGetResponses200])(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsGetResponses200]]) = resultF map { resultP => (new GetmediaByMedia_idCommentsType[MediaMedia_idCommentsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getmediaByMedia_idCommentsActionRequestType       = (BigInt)
    private type getmediaByMedia_idCommentsActionType[T]            = getmediaByMedia_idCommentsActionRequestType => Future[GetmediaByMedia_idCommentsType[T] forSome { type T }]


    val getmediaByMedia_idCommentsActionConstructor  = new getmediaByMedia_idCommentsSecureAction("basic", "comments", "relationships", "likes")

def getmediaByMedia_idCommentsAction[T] = (f: getmediaByMedia_idCommentsActionType[T]) => (media_id: BigInt) => getmediaByMedia_idCommentsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaByMedia_idCommentsResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idCommentsGetValidator(media_id).errors match {
                            case e if e.isEmpty => processValidgetmediaByMedia_idCommentsRequest(f)((media_id))(getmediaByMedia_idCommentsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaByMedia_idCommentsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetmediaByMedia_idCommentsRequest[T](f: getmediaByMedia_idCommentsActionType[T])(request: getmediaByMedia_idCommentsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PostmediaByMedia_idCommentsType[T] extends ResultWrapper[T]
    def PostmediaByMedia_idComments200(resultP: MediaMedia_idCommentsDeleteResponses200)(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) = success(new PostmediaByMedia_idCommentsType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def PostmediaByMedia_idComments200(resultF: Future[MediaMedia_idCommentsDeleteResponses200])(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) = resultF map { resultP => (new PostmediaByMedia_idCommentsType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postmediaByMedia_idCommentsActionRequestType       = (BigInt, LocationLatitude)
    private type postmediaByMedia_idCommentsActionType[T]            = postmediaByMedia_idCommentsActionRequestType => Future[PostmediaByMedia_idCommentsType[T] forSome { type T }]

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

def postmediaByMedia_idCommentsAction[T] = (f: postmediaByMedia_idCommentsActionType[T]) => (media_id: BigInt) => postmediaByMedia_idCommentsActionConstructor.async(BodyParsers.parse.using(postmediaByMedia_idCommentsParser(Seq[String]("application/json")))) { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { postmediaByMedia_idCommentsResponseMimeType =>
            val tEXT = request.body
            
            

                val result =
                        new MediaMedia_idCommentsPostValidator(media_id, tEXT).errors match {
                            case e if e.isEmpty => processValidpostmediaByMedia_idCommentsRequest(f)((media_id, tEXT))(postmediaByMedia_idCommentsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postmediaByMedia_idCommentsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpostmediaByMedia_idCommentsRequest[T](f: postmediaByMedia_idCommentsActionType[T])(request: postmediaByMedia_idCommentsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait DeletemediaByMedia_idCommentsType[T] extends ResultWrapper[T]
    def DeletemediaByMedia_idComments200(resultP: MediaMedia_idCommentsDeleteResponses200)(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) = success(new DeletemediaByMedia_idCommentsType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def DeletemediaByMedia_idComments200(resultF: Future[MediaMedia_idCommentsDeleteResponses200])(implicit writerP: String => Option[Writeable[MediaMedia_idCommentsDeleteResponses200]]) = resultF map { resultP => (new DeletemediaByMedia_idCommentsType[MediaMedia_idCommentsDeleteResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type deletemediaByMedia_idCommentsActionRequestType       = (BigInt)
    private type deletemediaByMedia_idCommentsActionType[T]            = deletemediaByMedia_idCommentsActionRequestType => Future[DeletemediaByMedia_idCommentsType[T] forSome { type T }]


    val deletemediaByMedia_idCommentsActionConstructor  = new deletemediaByMedia_idCommentsSecureAction("basic", "comments", "relationships", "likes")

def deletemediaByMedia_idCommentsAction[T] = (f: deletemediaByMedia_idCommentsActionType[T]) => (media_id: BigInt) => deletemediaByMedia_idCommentsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { deletemediaByMedia_idCommentsResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idCommentsDeleteValidator(media_id).errors match {
                            case e if e.isEmpty => processValiddeletemediaByMedia_idCommentsRequest(f)((media_id))(deletemediaByMedia_idCommentsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(deletemediaByMedia_idCommentsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValiddeletemediaByMedia_idCommentsRequest[T](f: deletemediaByMedia_idCommentsActionType[T])(request: deletemediaByMedia_idCommentsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GettagsByTag_nameMediaRecentType[T] extends ResultWrapper[T]
    def GettagsByTag_nameMediaRecent200(resultP: TagsTag_nameMediaRecentGetResponses200)(implicit writerP: String => Option[Writeable[TagsTag_nameMediaRecentGetResponses200]]) = success(new GettagsByTag_nameMediaRecentType[TagsTag_nameMediaRecentGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GettagsByTag_nameMediaRecent200(resultF: Future[TagsTag_nameMediaRecentGetResponses200])(implicit writerP: String => Option[Writeable[TagsTag_nameMediaRecentGetResponses200]]) = resultF map { resultP => (new GettagsByTag_nameMediaRecentType[TagsTag_nameMediaRecentGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type gettagsByTag_nameMediaRecentActionRequestType       = (String)
    private type gettagsByTag_nameMediaRecentActionType[T]            = gettagsByTag_nameMediaRecentActionRequestType => Future[GettagsByTag_nameMediaRecentType[T] forSome { type T }]


    val gettagsByTag_nameMediaRecentActionConstructor  = new gettagsByTag_nameMediaRecentSecureAction("basic", "comments", "relationships", "likes")

def gettagsByTag_nameMediaRecentAction[T] = (f: gettagsByTag_nameMediaRecentActionType[T]) => (tag_name: String) => gettagsByTag_nameMediaRecentActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { gettagsByTag_nameMediaRecentResponseMimeType =>
            
            

                val result =
                        new TagsTag_nameMediaRecentGetValidator(tag_name).errors match {
                            case e if e.isEmpty => processValidgettagsByTag_nameMediaRecentRequest(f)((tag_name))(gettagsByTag_nameMediaRecentResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gettagsByTag_nameMediaRecentResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgettagsByTag_nameMediaRecentRequest[T](f: gettagsByTag_nameMediaRecentActionType[T])(request: gettagsByTag_nameMediaRecentActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait PostusersByUser_idRelationshipType[T] extends ResultWrapper[T]
    def PostusersByUser_idRelationship200(resultP: UsersUser_idFollowsGetResponses200)(implicit writerP: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) = success(new PostusersByUser_idRelationshipType[UsersUser_idFollowsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def PostusersByUser_idRelationship200(resultF: Future[UsersUser_idFollowsGetResponses200])(implicit writerP: String => Option[Writeable[UsersUser_idFollowsGetResponses200]]) = resultF map { resultP => (new PostusersByUser_idRelationshipType[UsersUser_idFollowsGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type postusersByUser_idRelationshipActionRequestType       = (BigDecimal, UsersUser_idRelationshipPostAction)
    private type postusersByUser_idRelationshipActionType[T]            = postusersByUser_idRelationshipActionRequestType => Future[PostusersByUser_idRelationshipType[T] forSome { type T }]

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

def postusersByUser_idRelationshipAction[T] = (f: postusersByUser_idRelationshipActionType[T]) => (user_id: BigDecimal) => postusersByUser_idRelationshipActionConstructor.async(BodyParsers.parse.using(postusersByUser_idRelationshipParser(Seq[String]("application/json")))) { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { postusersByUser_idRelationshipResponseMimeType =>
            val action = request.body
            
            

                val result =
                        new UsersUser_idRelationshipPostValidator(user_id, action).errors match {
                            case e if e.isEmpty => processValidpostusersByUser_idRelationshipRequest(f)((user_id, action))(postusersByUser_idRelationshipResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(postusersByUser_idRelationshipResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidpostusersByUser_idRelationshipRequest[T](f: postusersByUser_idRelationshipActionType[T])(request: postusersByUser_idRelationshipActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetusersSelfFeedType[T] extends ResultWrapper[T]
    def GetusersSelfFeed200(resultP: UsersSelfFeedGetResponses200)(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = success(new GetusersSelfFeedType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersSelfFeed200(resultF: Future[UsersSelfFeedGetResponses200])(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = resultF map { resultP => (new GetusersSelfFeedType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersSelfFeedActionRequestType       = (MediaId, MediaId, MediaId)
    private type getusersSelfFeedActionType[T]            = getusersSelfFeedActionRequestType => Future[GetusersSelfFeedType[T] forSome { type T }]


    val getusersSelfFeedActionConstructor  = new getusersSelfFeedSecureAction("basic", "comments", "relationships", "likes")

def getusersSelfFeedAction[T] = (f: getusersSelfFeedActionType[T]) => (count: MediaId, max_id: MediaId, min_id: MediaId) => getusersSelfFeedActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersSelfFeedResponseMimeType =>
            
            

                val result =
                        new UsersSelfFeedGetValidator(count, max_id, min_id).errors match {
                            case e if e.isEmpty => processValidgetusersSelfFeedRequest(f)((count, max_id, min_id))(getusersSelfFeedResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersSelfFeedResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetusersSelfFeedRequest[T](f: getusersSelfFeedActionType[T])(request: getusersSelfFeedActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetusersByUser_idType[T] extends ResultWrapper[T]
    def GetusersByUser_id200(resultP: UsersUser_idGetResponses200)(implicit writerP: String => Option[Writeable[UsersUser_idGetResponses200]]) = success(new GetusersByUser_idType[UsersUser_idGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersByUser_id200(resultF: Future[UsersUser_idGetResponses200])(implicit writerP: String => Option[Writeable[UsersUser_idGetResponses200]]) = resultF map { resultP => (new GetusersByUser_idType[UsersUser_idGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersByUser_idActionRequestType       = (BigDecimal)
    private type getusersByUser_idActionType[T]            = getusersByUser_idActionRequestType => Future[GetusersByUser_idType[T] forSome { type T }]


    val getusersByUser_idActionConstructor  = new getusersByUser_idSecureAction("basic")

def getusersByUser_idAction[T] = (f: getusersByUser_idActionType[T]) => (user_id: BigDecimal) => getusersByUser_idActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersByUser_idResponseMimeType =>
            
            

                val result =
                        new UsersUser_idGetValidator(user_id).errors match {
                            case e if e.isEmpty => processValidgetusersByUser_idRequest(f)((user_id))(getusersByUser_idResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersByUser_idResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetusersByUser_idRequest[T](f: getusersByUser_idActionType[T])(request: getusersByUser_idActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetmediaSearchType[T] extends ResultWrapper[T]
    def GetmediaSearch200(resultP: MediaSearchGetResponses200)(implicit writerP: String => Option[Writeable[MediaSearchGetResponses200]]) = success(new GetmediaSearchType[MediaSearchGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetmediaSearch200(resultF: Future[MediaSearchGetResponses200])(implicit writerP: String => Option[Writeable[MediaSearchGetResponses200]]) = resultF map { resultP => (new GetmediaSearchType[MediaSearchGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getmediaSearchActionRequestType       = (MediaId, BigInt, LocationLatitude, MediaId, LocationLatitude)
    private type getmediaSearchActionType[T]            = getmediaSearchActionRequestType => Future[GetmediaSearchType[T] forSome { type T }]


    val getmediaSearchActionConstructor  = new getmediaSearchSecureAction("basic", "comments", "relationships", "likes")

def getmediaSearchAction[T] = (f: getmediaSearchActionType[T]) => (mAX_TIMESTAMP: MediaId, dISTANCE: BigInt, lNG: LocationLatitude, mIN_TIMESTAMP: MediaId, lAT: LocationLatitude) => getmediaSearchActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaSearchResponseMimeType =>
            
            

                val result =
                        new MediaSearchGetValidator(mAX_TIMESTAMP, dISTANCE, lNG, mIN_TIMESTAMP, lAT).errors match {
                            case e if e.isEmpty => processValidgetmediaSearchRequest(f)((mAX_TIMESTAMP, dISTANCE, lNG, mIN_TIMESTAMP, lAT))(getmediaSearchResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaSearchResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetmediaSearchRequest[T](f: getmediaSearchActionType[T])(request: getmediaSearchActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetgeographiesByGeo_idMediaRecentType[T] extends ResultWrapper[T]
    
    def GetgeographiesByGeo_idMediaRecent200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type getgeographiesByGeo_idMediaRecentActionRequestType       = (BigInt, MediaId, MediaId)
    private type getgeographiesByGeo_idMediaRecentActionType[T]            = getgeographiesByGeo_idMediaRecentActionRequestType => Future[GetgeographiesByGeo_idMediaRecentType[T] forSome { type T }]


    val getgeographiesByGeo_idMediaRecentActionConstructor  = new getgeographiesByGeo_idMediaRecentSecureAction("basic", "comments", "relationships", "likes")

def getgeographiesByGeo_idMediaRecentAction[T] = (f: getgeographiesByGeo_idMediaRecentActionType[T]) => (geo_id: BigInt, count: MediaId, min_id: MediaId) => getgeographiesByGeo_idMediaRecentActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getgeographiesByGeo_idMediaRecentResponseMimeType =>
            
            

                val result =
                        new GeographiesGeo_idMediaRecentGetValidator(geo_id, count, min_id).errors match {
                            case e if e.isEmpty => processValidgetgeographiesByGeo_idMediaRecentRequest(f)((geo_id, count, min_id))(getgeographiesByGeo_idMediaRecentResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getgeographiesByGeo_idMediaRecentResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetgeographiesByGeo_idMediaRecentRequest[T](f: getgeographiesByGeo_idMediaRecentActionType[T])(request: getgeographiesByGeo_idMediaRecentActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetmediaByShortcodeType[T] extends ResultWrapper[T]
    def GetmediaByShortcode200(resultP: Media)(implicit writerP: String => Option[Writeable[Media]]) = success(new GetmediaByShortcodeType[Media] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetmediaByShortcode200(resultF: Future[Media])(implicit writerP: String => Option[Writeable[Media]]) = resultF map { resultP => (new GetmediaByShortcodeType[Media] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getmediaByShortcodeActionRequestType       = (String)
    private type getmediaByShortcodeActionType[T]            = getmediaByShortcodeActionRequestType => Future[GetmediaByShortcodeType[T] forSome { type T }]


    val getmediaByShortcodeActionConstructor  = new getmediaByShortcodeSecureAction("basic", "comments", "relationships", "likes")

def getmediaByShortcodeAction[T] = (f: getmediaByShortcodeActionType[T]) => (shortcode: String) => getmediaByShortcodeActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaByShortcodeResponseMimeType =>
            
            

                val result =
                        new MediaShortcodeGetValidator(shortcode).errors match {
                            case e if e.isEmpty => processValidgetmediaByShortcodeRequest(f)((shortcode))(getmediaByShortcodeResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaByShortcodeResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetmediaByShortcodeRequest[T](f: getmediaByShortcodeActionType[T])(request: getmediaByShortcodeActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetlocationsSearchType[T] extends ResultWrapper[T]
    def GetlocationsSearch200(resultP: LocationsSearchGetResponses200)(implicit writerP: String => Option[Writeable[LocationsSearchGetResponses200]]) = success(new GetlocationsSearchType[LocationsSearchGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetlocationsSearch200(resultF: Future[LocationsSearchGetResponses200])(implicit writerP: String => Option[Writeable[LocationsSearchGetResponses200]]) = resultF map { resultP => (new GetlocationsSearchType[LocationsSearchGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getlocationsSearchActionRequestType       = (MediaId, MediaId, MediaId, LocationLatitude, MediaId, LocationLatitude)
    private type getlocationsSearchActionType[T]            = getlocationsSearchActionRequestType => Future[GetlocationsSearchType[T] forSome { type T }]


    val getlocationsSearchActionConstructor  = new getlocationsSearchSecureAction("basic", "comments", "relationships", "likes")

def getlocationsSearchAction[T] = (f: getlocationsSearchActionType[T]) => (foursquare_v2_id: MediaId, facebook_places_id: MediaId, distance: MediaId, lat: LocationLatitude, foursquare_id: MediaId, lng: LocationLatitude) => getlocationsSearchActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getlocationsSearchResponseMimeType =>
            
            

                val result =
                        new LocationsSearchGetValidator(foursquare_v2_id, facebook_places_id, distance, lat, foursquare_id, lng).errors match {
                            case e if e.isEmpty => processValidgetlocationsSearchRequest(f)((foursquare_v2_id, facebook_places_id, distance, lat, foursquare_id, lng))(getlocationsSearchResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getlocationsSearchResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetlocationsSearchRequest[T](f: getlocationsSearchActionType[T])(request: getlocationsSearchActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetusersSelfRequested_byType[T] extends ResultWrapper[T]
    def GetusersSelfRequested_by200(resultP: UsersSelfRequested_byGetResponses200)(implicit writerP: String => Option[Writeable[UsersSelfRequested_byGetResponses200]]) = success(new GetusersSelfRequested_byType[UsersSelfRequested_byGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersSelfRequested_by200(resultF: Future[UsersSelfRequested_byGetResponses200])(implicit writerP: String => Option[Writeable[UsersSelfRequested_byGetResponses200]]) = resultF map { resultP => (new GetusersSelfRequested_byType[UsersSelfRequested_byGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersSelfRequested_byActionRequestType       = (Unit)
    private type getusersSelfRequested_byActionType[T]            = getusersSelfRequested_byActionRequestType => Future[GetusersSelfRequested_byType[T] forSome { type T }]


    val getusersSelfRequested_byActionConstructor  = new getusersSelfRequested_bySecureAction("basic", "comments", "relationships", "likes")

def getusersSelfRequested_byAction[T] = (f: getusersSelfRequested_byActionType[T]) => getusersSelfRequested_byActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersSelfRequested_byResponseMimeType =>
            
            

                val result = processValidgetusersSelfRequested_byRequest(f)()(getusersSelfRequested_byResponseMimeType)
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetusersSelfRequested_byRequest[T](f: getusersSelfRequested_byActionType[T])(request: getusersSelfRequested_byActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetmediaByMedia_idType[T] extends ResultWrapper[T]
    def GetmediaByMedia_id200(resultP: Media)(implicit writerP: String => Option[Writeable[Media]]) = success(new GetmediaByMedia_idType[Media] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetmediaByMedia_id200(resultF: Future[Media])(implicit writerP: String => Option[Writeable[Media]]) = resultF map { resultP => (new GetmediaByMedia_idType[Media] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getmediaByMedia_idActionRequestType       = (BigInt)
    private type getmediaByMedia_idActionType[T]            = getmediaByMedia_idActionRequestType => Future[GetmediaByMedia_idType[T] forSome { type T }]


    val getmediaByMedia_idActionConstructor  = new getmediaByMedia_idSecureAction("basic", "comments", "relationships", "likes")

def getmediaByMedia_idAction[T] = (f: getmediaByMedia_idActionType[T]) => (media_id: BigInt) => getmediaByMedia_idActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaByMedia_idResponseMimeType =>
            
            

                val result =
                        new MediaMedia_idGetValidator(media_id).errors match {
                            case e if e.isEmpty => processValidgetmediaByMedia_idRequest(f)((media_id))(getmediaByMedia_idResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getmediaByMedia_idResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetmediaByMedia_idRequest[T](f: getmediaByMedia_idActionType[T])(request: getmediaByMedia_idActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetlocationsByLocation_idMediaRecentType[T] extends ResultWrapper[T]
    def GetlocationsByLocation_idMediaRecent200(resultP: UsersSelfFeedGetResponses200)(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = success(new GetlocationsByLocation_idMediaRecentType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetlocationsByLocation_idMediaRecent200(resultF: Future[UsersSelfFeedGetResponses200])(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = resultF map { resultP => (new GetlocationsByLocation_idMediaRecentType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getlocationsByLocation_idMediaRecentActionRequestType       = (BigInt, MediaId, MediaId, MediaFilter, MediaFilter)
    private type getlocationsByLocation_idMediaRecentActionType[T]            = getlocationsByLocation_idMediaRecentActionRequestType => Future[GetlocationsByLocation_idMediaRecentType[T] forSome { type T }]


    val getlocationsByLocation_idMediaRecentActionConstructor  = new getlocationsByLocation_idMediaRecentSecureAction("basic", "comments", "relationships", "likes")

def getlocationsByLocation_idMediaRecentAction[T] = (f: getlocationsByLocation_idMediaRecentActionType[T]) => (location_id: BigInt, max_timestamp: MediaId, min_timestamp: MediaId, min_id: MediaFilter, max_id: MediaFilter) => getlocationsByLocation_idMediaRecentActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getlocationsByLocation_idMediaRecentResponseMimeType =>
            
            

                val result =
                        new LocationsLocation_idMediaRecentGetValidator(location_id, max_timestamp, min_timestamp, min_id, max_id).errors match {
                            case e if e.isEmpty => processValidgetlocationsByLocation_idMediaRecentRequest(f)((location_id, max_timestamp, min_timestamp, min_id, max_id))(getlocationsByLocation_idMediaRecentResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getlocationsByLocation_idMediaRecentResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetlocationsByLocation_idMediaRecentRequest[T](f: getlocationsByLocation_idMediaRecentActionType[T])(request: getlocationsByLocation_idMediaRecentActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetusersByUser_idMediaRecentType[T] extends ResultWrapper[T]
    def GetusersByUser_idMediaRecent200(resultP: UsersSelfFeedGetResponses200)(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = success(new GetusersByUser_idMediaRecentType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersByUser_idMediaRecent200(resultF: Future[UsersSelfFeedGetResponses200])(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = resultF map { resultP => (new GetusersByUser_idMediaRecentType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersByUser_idMediaRecentActionRequestType       = (BigDecimal, MediaId, MediaFilter, MediaId, MediaFilter, MediaId)
    private type getusersByUser_idMediaRecentActionType[T]            = getusersByUser_idMediaRecentActionRequestType => Future[GetusersByUser_idMediaRecentType[T] forSome { type T }]


    val getusersByUser_idMediaRecentActionConstructor  = new getusersByUser_idMediaRecentSecureAction("basic", "comments", "relationships", "likes")

def getusersByUser_idMediaRecentAction[T] = (f: getusersByUser_idMediaRecentActionType[T]) => (user_id: BigDecimal, max_timestamp: MediaId, min_id: MediaFilter, min_timestamp: MediaId, max_id: MediaFilter, count: MediaId) => getusersByUser_idMediaRecentActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getusersByUser_idMediaRecentResponseMimeType =>
            
            

                val result =
                        new UsersUser_idMediaRecentGetValidator(user_id, max_timestamp, min_id, min_timestamp, max_id, count).errors match {
                            case e if e.isEmpty => processValidgetusersByUser_idMediaRecentRequest(f)((user_id, max_timestamp, min_id, min_timestamp, max_id, count))(getusersByUser_idMediaRecentResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getusersByUser_idMediaRecentResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetusersByUser_idMediaRecentRequest[T](f: getusersByUser_idMediaRecentActionType[T])(request: getusersByUser_idMediaRecentActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetmediaPopularType[T] extends ResultWrapper[T]
    def GetmediaPopular200(resultP: UsersSelfFeedGetResponses200)(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = success(new GetmediaPopularType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetmediaPopular200(resultF: Future[UsersSelfFeedGetResponses200])(implicit writerP: String => Option[Writeable[UsersSelfFeedGetResponses200]]) = resultF map { resultP => (new GetmediaPopularType[UsersSelfFeedGetResponses200] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getmediaPopularActionRequestType       = (Unit)
    private type getmediaPopularActionType[T]            = getmediaPopularActionRequestType => Future[GetmediaPopularType[T] forSome { type T }]


    val getmediaPopularActionConstructor  = new getmediaPopularSecureAction("basic", "comments", "relationships", "likes")

def getmediaPopularAction[T] = (f: getmediaPopularActionType[T]) => getmediaPopularActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmediaPopularResponseMimeType =>
            
            

                val result = processValidgetmediaPopularRequest(f)()(getmediaPopularResponseMimeType)
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetmediaPopularRequest[T](f: getmediaPopularActionType[T])(request: getmediaPopularActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetmediaByMedia_idLikesType[Result] with PostmediaByMedia_idLikesType[Result] with DeletemediaByMedia_idLikesType[Result] with GetusersByUser_idFollowsType[Result] with GetlocationsByLocation_idType[Result] with GetusersSearchType[Result] with GetusersSelfMediaLikedType[Result] with GettagsByTag_nameType[Result] with GettagsSearchType[Result] with GetusersByUser_idFollowed_byType[Result] with GetmediaByMedia_idCommentsType[Result] with PostmediaByMedia_idCommentsType[Result] with DeletemediaByMedia_idCommentsType[Result] with GettagsByTag_nameMediaRecentType[Result] with PostusersByUser_idRelationshipType[Result] with GetusersSelfFeedType[Result] with GetusersByUser_idType[Result] with GetmediaSearchType[Result] with GetgeographiesByGeo_idMediaRecentType[Result] with GetmediaByShortcodeType[Result] with GetlocationsSearchType[Result] with GetusersSelfRequested_byType[Result] with GetmediaByMedia_idType[Result] with GetlocationsByLocation_idMediaRecentType[Result] with GetusersByUser_idMediaRecentType[Result] with GetmediaPopularType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetmediaByMedia_idLikesType[Results.EmptyContent] with PostmediaByMedia_idLikesType[Results.EmptyContent] with DeletemediaByMedia_idLikesType[Results.EmptyContent] with GetusersByUser_idFollowsType[Results.EmptyContent] with GetlocationsByLocation_idType[Results.EmptyContent] with GetusersSearchType[Results.EmptyContent] with GetusersSelfMediaLikedType[Results.EmptyContent] with GettagsByTag_nameType[Results.EmptyContent] with GettagsSearchType[Results.EmptyContent] with GetusersByUser_idFollowed_byType[Results.EmptyContent] with GetmediaByMedia_idCommentsType[Results.EmptyContent] with PostmediaByMedia_idCommentsType[Results.EmptyContent] with DeletemediaByMedia_idCommentsType[Results.EmptyContent] with GettagsByTag_nameMediaRecentType[Results.EmptyContent] with PostusersByUser_idRelationshipType[Results.EmptyContent] with GetusersSelfFeedType[Results.EmptyContent] with GetusersByUser_idType[Results.EmptyContent] with GetmediaSearchType[Results.EmptyContent] with GetgeographiesByGeo_idMediaRecentType[Results.EmptyContent] with GetmediaByShortcodeType[Results.EmptyContent] with GetlocationsSearchType[Results.EmptyContent] with GetusersSelfRequested_byType[Results.EmptyContent] with GetmediaByMedia_idType[Results.EmptyContent] with GetlocationsByLocation_idMediaRecentType[Results.EmptyContent] with GetusersByUser_idMediaRecentType[Results.EmptyContent] with GetmediaPopularType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
