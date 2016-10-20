package instagram.api


    import scala.math.BigInt
    import scala.math.BigDecimal

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type TagsSearchGetResponses200Meta = Option[UsersSelfRequested_byGetResponses200MetaOpt]
    type MediaFilter = Option[String]
    type MediaMedia_idCommentsDeleteResponses200Meta = Option[MediaMedia_idLikesGetResponses200MetaOpt]
    type UsersSelfFeedGetResponses200Data = Option[UsersSelfFeedGetResponses200DataOpt]
    type MediaTags = Option[MediaTagsOpt]
    type MediaMedia_idLikesGetResponses200Data = Option[MediaMedia_idLikesGetResponses200DataOpt]
    type MediaId = Option[BigInt]
    type MediaVideosLow_resolution = Option[Image]
    type UsersUser_idRelationshipPostAction = Option[UsersUser_idRelationshipPostActionOpt]
    type MediaTagsOpt = Seq[Tag]
    type MediaImages = Option[MediaImagesOpt]
    type MediaLikes = Option[MediaLikesOpt]
    type MediaUsers_in_photoOpt = Seq[MiniProfile]
    type MediaMedia_idLikesGetResponses200DataOpt = Seq[Like]
    type LocationsSearchGetResponses200Data = Option[LocationsSearchGetResponses200DataOpt]
    type MediaComments_esc = Option[MediaComments_Opt]
    type MediaSearchGetResponses200DataOpt = Seq[MediaSearchGetResponses200DataOptArrResult]
    type CommentFrom = Option[MiniProfile]
    type LocationsSearchGetResponses200DataOpt = Seq[Location]
    type MediaSearchGetResponses200Data = Option[MediaSearchGetResponses200DataOpt]
    type UsersSelfFeedGetResponses200DataOpt = Seq[Media]
    type MediaComments_DataOpt = Seq[Comment]
    type UsersUser_idGetResponses200Data = Option[User]
    type MediaVideos = Option[MediaVideosOpt]
    type MediaLocation = Option[Location]
    type GeographiesGeo_idMediaRecentGetResponses200 = Null
    type MediaComments_Data = Option[MediaComments_DataOpt]
    type MediaUsers_in_photo = Option[MediaUsers_in_photoOpt]
    type LocationLatitude = Option[BigDecimal]
    type UserCounts = Option[UserCountsOpt]

    object UsersUser_idRelationshipPostActionOpt {
        
        val Unfollow = new UsersUser_idRelationshipPostActionOpt("unfollow")
        val Approve = new UsersUser_idRelationshipPostActionOpt("approve")
        val Block = new UsersUser_idRelationshipPostActionOpt("block")
        val Unblock = new UsersUser_idRelationshipPostActionOpt("unblock")
        val Follow = new UsersUser_idRelationshipPostActionOpt("follow")

        implicit def stringToUsersUser_idRelationshipPostActionOpt: String => UsersUser_idRelationshipPostActionOpt = {
            case "unfollow" => Unfollow
            case "approve" => Approve
            case "block" => Block
            case "unblock" => Unblock
            case "follow" => Follow
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }

import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_BigIntQuery = PlayPathBindables.queryBindableBigInt
    implicit val bindable_BigDecimalPath = PlayPathBindables.pathBindableBigDecimal
    implicit val bindable_BigIntPath = PlayPathBindables.pathBindableBigInt
    implicit val bindable_BigDecimalQuery = PlayPathBindables.queryBindableBigDecimal
    implicit val bindable_OptionStringQuery: QueryStringBindable[Option[String]] = PlayPathBindables.createOptionQueryBindable[String]
    implicit val bindable_OptionBigIntQuery: QueryStringBindable[Option[BigInt]] = PlayPathBindables.createOptionQueryBindable[BigInt]
    implicit val bindable_OptionBigDecimalQuery: QueryStringBindable[Option[BigDecimal]] = PlayPathBindables.createOptionQueryBindable[BigDecimal]

}
//noinspection ScalaStyle
package yaml {


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
    case class MediaComments_Opt(count: MediaId, data: MediaComments_Data) 
    case class UsersUser_idGetResponses200(data: UsersUser_idGetResponses200Data) 
    case class MediaMedia_idCommentsGetResponses200(meta: MediaMedia_idCommentsDeleteResponses200Meta, data: MediaComments_Data) 
    case class MediaVideosOpt(low_resolution: MediaVideosLow_resolution, standard_resolution: MediaVideosLow_resolution) 
    case class Location(id: MediaFilter, name: MediaFilter, latitude: LocationLatitude, longitude: LocationLatitude) 
    case class MiniProfile(user_name: MediaFilter, full_name: MediaFilter, id: MediaId, profile_picture: MediaFilter) 
    case class MediaLikesOpt(count: MediaId, data: MediaUsers_in_photo) 
    case class LocationsSearchGetResponses200(data: LocationsSearchGetResponses200Data) 
    case class MediaImagesOpt(low_resolution: MediaVideosLow_resolution, thumbnail: MediaVideosLow_resolution, standard_resolution: MediaVideosLow_resolution) 

    case class UsersUser_idRelationshipPostActionOpt(value: String) extends AnyVal {
        override def toString = value.toString
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val UsersSelfRequested_byGetResponses200Writes: Writes[UsersSelfRequested_byGetResponses200] = new Writes[UsersSelfRequested_byGetResponses200] {
        def writes(ss: UsersSelfRequested_byGetResponses200) =
          Json.obj(
            "meta" -> ss.meta, 
            "data" -> ss.data
          )
        }
    implicit val LocationsSearchGetResponses200Writes: Writes[LocationsSearchGetResponses200] = new Writes[LocationsSearchGetResponses200] {
        def writes(ss: LocationsSearchGetResponses200) =
          Json.obj(
            "data" -> ss.data
          )
        }
    implicit val MediaSearchGetResponses200Writes: Writes[MediaSearchGetResponses200] = new Writes[MediaSearchGetResponses200] {
        def writes(ss: MediaSearchGetResponses200) =
          Json.obj(
            "data" -> ss.data
          )
        }
    implicit val UserWrites: Writes[User] = new Writes[User] {
        def writes(ss: User) =
          Json.obj(
            "website" -> ss.website, 
            "profile_picture" -> ss.profile_picture, 
            "username" -> ss.username, 
            "full_name" -> ss.full_name, 
            "bio" -> ss.bio, 
            "id" -> ss.id, 
            "counts" -> ss.counts
          )
        }
    implicit val UsersUser_idGetResponses200Writes: Writes[UsersUser_idGetResponses200] = new Writes[UsersUser_idGetResponses200] {
        def writes(ss: UsersUser_idGetResponses200) =
          Json.obj(
            "data" -> ss.data
          )
        }
    implicit val TagsTag_nameMediaRecentGetResponses200Writes: Writes[TagsTag_nameMediaRecentGetResponses200] = new Writes[TagsTag_nameMediaRecentGetResponses200] {
        def writes(ss: TagsTag_nameMediaRecentGetResponses200) =
          Json.obj(
            "data" -> ss.data
          )
        }
    implicit val MediaMedia_idCommentsGetResponses200Writes: Writes[MediaMedia_idCommentsGetResponses200] = new Writes[MediaMedia_idCommentsGetResponses200] {
        def writes(ss: MediaMedia_idCommentsGetResponses200) =
          Json.obj(
            "meta" -> ss.meta, 
            "data" -> ss.data
          )
        }
    implicit val TagsSearchGetResponses200Writes: Writes[TagsSearchGetResponses200] = new Writes[TagsSearchGetResponses200] {
        def writes(ss: TagsSearchGetResponses200) =
          Json.obj(
            "meta" -> ss.meta, 
            "data" -> ss.data
          )
        }
    implicit val ImageWrites: Writes[Image] = new Writes[Image] {
        def writes(ss: Image) =
          Json.obj(
            "width" -> ss.width, 
            "height" -> ss.height, 
            "url" -> ss.url
          )
        }
    implicit val TagWrites: Writes[Tag] = new Writes[Tag] {
        def writes(ss: Tag) =
          Json.obj(
            "media_count" -> ss.media_count, 
            "name" -> ss.name
          )
        }
    implicit val CommentWrites: Writes[Comment] = new Writes[Comment] {
        def writes(ss: Comment) =
          Json.obj(
            "id" -> ss.id, 
            "created_time" -> ss.created_time, 
            "text" -> ss.text, 
            "from" -> ss.from
          )
        }
    implicit val MediaWrites: Writes[Media] = new Writes[Media] {
        def writes(ss: Media) =
          Json.obj(
            "location" -> ss.location, 
            "created_time" -> ss.created_time, 
            "comments_esc" -> ss.comments_esc, 
            "tags" -> ss.tags, 
            "users_in_photo" -> ss.users_in_photo, 
            "filter" -> ss.filter, 
            "likes" -> ss.likes, 
            "id" -> ss.id, 
            "videos" -> ss.videos, 
            "`type`" -> ss.`type`, 
            "images" -> ss.images, 
            "user" -> ss.user
          )
        }
    implicit val UsersSelfFeedGetResponses200Writes: Writes[UsersSelfFeedGetResponses200] = new Writes[UsersSelfFeedGetResponses200] {
        def writes(ss: UsersSelfFeedGetResponses200) =
          Json.obj(
            "data" -> ss.data
          )
        }
    implicit val LocationWrites: Writes[Location] = new Writes[Location] {
        def writes(ss: Location) =
          Json.obj(
            "id" -> ss.id, 
            "name" -> ss.name, 
            "latitude" -> ss.latitude, 
            "longitude" -> ss.longitude
          )
        }
    implicit val LocationsLocation_idGetResponses200Writes: Writes[LocationsLocation_idGetResponses200] = new Writes[LocationsLocation_idGetResponses200] {
        def writes(ss: LocationsLocation_idGetResponses200) =
          Json.obj(
            "data" -> ss.data
          )
        }
    implicit val MiniProfileWrites: Writes[MiniProfile] = new Writes[MiniProfile] {
        def writes(ss: MiniProfile) =
          Json.obj(
            "user_name" -> ss.user_name, 
            "full_name" -> ss.full_name, 
            "id" -> ss.id, 
            "profile_picture" -> ss.profile_picture
          )
        }
    implicit val UsersUser_idFollowsGetResponses200Writes: Writes[UsersUser_idFollowsGetResponses200] = new Writes[UsersUser_idFollowsGetResponses200] {
        def writes(ss: UsersUser_idFollowsGetResponses200) =
          Json.obj(
            "data" -> ss.data
          )
        }
    implicit val MediaMedia_idCommentsDeleteResponses200Writes: Writes[MediaMedia_idCommentsDeleteResponses200] = new Writes[MediaMedia_idCommentsDeleteResponses200] {
        def writes(ss: MediaMedia_idCommentsDeleteResponses200) =
          Json.obj(
            "meta" -> ss.meta, 
            "data" -> ss.data
          )
        }
    implicit val LikeWrites: Writes[Like] = new Writes[Like] {
        def writes(ss: Like) =
          Json.obj(
            "first_name" -> ss.first_name, 
            "id" -> ss.id, 
            "last_name" -> ss.last_name, 
            "`type`" -> ss.`type`, 
            "user_name" -> ss.user_name
          )
        }
    implicit val MediaMedia_idLikesGetResponses200Writes: Writes[MediaMedia_idLikesGetResponses200] = new Writes[MediaMedia_idLikesGetResponses200] {
        def writes(ss: MediaMedia_idLikesGetResponses200) =
          Json.obj(
            "meta" -> ss.meta, 
            "data" -> ss.data
          )
        }
    }
}
