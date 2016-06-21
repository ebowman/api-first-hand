package instagram.api

package object yaml {

    import scala.math.BigInt
    import scala.math.BigDecimal

    import de.zalando.play.controllers.PlayPathBindables



    type TagsSearchGetResponses200Meta = Option[UsersSelfRequested_byGetResponses200MetaOpt]
    type LocationsLocation_idLocation_id = BigInt
    type MediaMedia_idGetResponses200VideosStandard_resolution = Option[Image]
    type MediaFilter = Option[String]
    type MediaMedia_idCommentsDeleteResponses200Meta = Option[MediaMedia_idLikesGetResponses200MetaOpt]
    type UsersSelfFeedGetResponses200Data = Option[UsersSelfFeedGetResponses200DataOpt]
    type MediaTags = Option[MediaTagsOpt]
    type MediaMedia_idLikesGetResponses200Data = Option[MediaMedia_idLikesGetResponses200DataOpt]
    type MediaId = Option[BigInt]
    type UsersUser_idRelationshipPostAction = Option[UsersUser_idRelationshipPostActionOpt]
    type MediaTagsOpt = Seq[Tag]
    type MediaImages = Option[MediaImagesOpt]
    type MediaLikes = Option[MediaLikesOpt]
    type MediaSearchGetDISTANCE = BigInt
    type MediaMedia_idCommentsGetResponses200DataOpt = Seq[Comment]
    type MediaUsers_in_photoOpt = Seq[MiniProfile]
    type MediaMedia_idLikesGetResponses200DataOpt = Seq[Like]
    type LocationsSearchGetResponses200Data = Option[LocationsSearchGetResponses200DataOpt]
    type MediaComments_esc = Option[MediaComments_Opt]
    type MediaSearchGetResponses200DataOpt = Seq[MediaSearchGetResponses200DataOptArrResult]
    type CommentFrom = Option[MiniProfile]
    type LocationsSearchGetResponses200DataOpt = Seq[Location]
    type MediaSearchGetResponses200Data = Option[MediaSearchGetResponses200DataOpt]
    type UsersSelfFeedGetResponses200DataOpt = Seq[Media]
    type UsersUser_idGetResponses200Data = Option[User]
    type MediaVideos = Option[MediaVideosOpt]
    type MediaLocation = Option[Location]
    type GeographiesGeo_idMediaRecentGetResponses200 = Null
    type MediaUsers_in_photo = Option[MediaUsers_in_photoOpt]
    type LocationLatitude = Option[BigDecimal]
    type MediaMedia_idCommentsGetResponses200Data = Option[MediaMedia_idCommentsGetResponses200DataOpt]
    type User_id_paramUser_id = BigDecimal
    type UserCounts = Option[UserCountsOpt]
    type Tag_nameTag_name = String


    case class UsersSelfFeedGetResponses200(data: UsersSelfFeedGetResponses200Data) 
    case class MediaMedia_idCommentsDeleteResponses200(meta: MediaMedia_idCommentsDeleteResponses200Meta, data: MediaFilter) 
    case class MediaSearchGetResponses200DataOptArrResult(location: MediaLocation, created_time: MediaId, comments_esc: MediaComments_esc, tags: MediaTags, users_in_photo: MediaUsers_in_photo, filter: MediaFilter, likes: MediaLikes, id: MediaId, videos: MediaVideos, `type`: MediaFilter, images: MediaImages, user: CommentFrom, distance: LocationLatitude) 
    case class UsersUser_idFollowsGetResponses200(data: MediaUsers_in_photo) 
    case class UserCountsOpt(media: MediaId, follows: MediaId, follwed_by: MediaId) 
    case class User(website: MediaFilter, profile_picture: MediaFilter, username: MediaFilter, full_name: MediaFilter, bio: MediaFilter, id: MediaId, counts: UserCounts) 
    case class TagsTag_nameMediaRecentGetResponses200(data: MediaTags) 
    case class Image(width: MediaId, height: MediaId, url: MediaFilter) 
    case class UsersSelfRequested_byGetResponses200(meta: TagsSearchGetResponses200Meta, data: MediaUsers_in_photo) 
    case class Tag(media_count: MediaId, name: MediaFilter) 
    case class UsersSelfRequested_byGetResponses200MetaOpt(code: MediaId) 
    case class LocationsLocation_idGetResponses200(data: MediaLocation) 
    case class Comment(id: MediaFilter, created_time: MediaFilter, text: MediaFilter, from: CommentFrom) 
    case class Media(location: MediaLocation, created_time: MediaId, comments_esc: MediaComments_esc, tags: MediaTags, users_in_photo: MediaUsers_in_photo, filter: MediaFilter, likes: MediaLikes, id: MediaId, videos: MediaVideos, `type`: MediaFilter, images: MediaImages, user: CommentFrom) 
    case class MediaMedia_idLikesGetResponses200(meta: MediaMedia_idCommentsDeleteResponses200Meta, data: MediaMedia_idLikesGetResponses200Data) 
    case class MediaMedia_idLikesGetResponses200MetaOpt(code: LocationLatitude) 
    case class MediaSearchGetResponses200(data: MediaSearchGetResponses200Data) 
    case class TagsSearchGetResponses200(meta: TagsSearchGetResponses200Meta, data: MediaTags) 
    case class Like(first_name: MediaFilter, id: MediaFilter, last_name: MediaFilter, `type`: MediaFilter, user_name: MediaFilter) 
    case class MediaComments_Opt(count: MediaId, data: MediaMedia_idCommentsGetResponses200Data) 
    case class UsersUser_idGetResponses200(data: UsersUser_idGetResponses200Data) 
    case class MediaMedia_idCommentsGetResponses200(meta: MediaMedia_idCommentsDeleteResponses200Meta, data: MediaMedia_idCommentsGetResponses200Data) 
    case class MediaVideosOpt(low_resolution: MediaMedia_idGetResponses200VideosStandard_resolution, standard_resolution: MediaMedia_idGetResponses200VideosStandard_resolution) 
    case class Location(id: MediaFilter, name: MediaFilter, latitude: LocationLatitude, longitude: LocationLatitude) 
    case class MiniProfile(user_name: MediaFilter, full_name: MediaFilter, id: MediaId, profile_picture: MediaFilter) 
    case class MediaLikesOpt(count: MediaId, data: MediaUsers_in_photo) 
    case class LocationsSearchGetResponses200(data: LocationsSearchGetResponses200Data) 
    case class MediaImagesOpt(low_resolution: MediaMedia_idGetResponses200VideosStandard_resolution, thumbnail: MediaMedia_idGetResponses200VideosStandard_resolution, standard_resolution: MediaMedia_idGetResponses200VideosStandard_resolution) 

    sealed trait UsersUser_idRelationshipPostActionOpt { def value: String }
    case object Unfollow extends UsersUser_idRelationshipPostActionOpt { val value = "unfollow" }
    case object Approve extends UsersUser_idRelationshipPostActionOpt { val value = "approve" }
    case object Block extends UsersUser_idRelationshipPostActionOpt { val value = "block" }
    case object Unblock extends UsersUser_idRelationshipPostActionOpt { val value = "unblock" }
    case object Follow extends UsersUser_idRelationshipPostActionOpt { val value = "follow" }
    implicit def stringToUsersUser_idRelationshipPostActionOpt(in: String): UsersUser_idRelationshipPostActionOpt = in match {
        case "unfollow" => Unfollow
        case "approve" => Approve
        case "block" => Block
        case "unblock" => Unblock
        case "follow" => Follow
    }

    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt

    implicit val bindable_BigDecimalPath = PlayPathBindables.pathBindableBigDecimal

    implicit val bindable_BigIntPath = PlayPathBindables.pathBindableBigInt

    implicit val bindable_BigDecimalQuery = PlayPathBindables.queryBindableBigDecimal

    implicit val bindable_OptionStringQuery = PlayPathBindables.createOptionQueryBindable[String]

    implicit val bindable_OptionBigIntQuery = PlayPathBindables.createOptionQueryBindable[BigInt]

    implicit val bindable_OptionBigDecimalQuery = PlayPathBindables.createOptionQueryBindable[BigDecimal]


}
