package instagram.api.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import scala.math.BigInt
import scala.math.BigDecimal
// ----- constraints and wrapper validations -----
class UsersUser_idMediaRecentGetCountOptConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class UsersUser_idMediaRecentGetCountOptValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idMediaRecentGetCountOptConstraints(instance))
}
class UsersUser_idFollowsGetUser_idConstraints(override val instance: BigDecimal) extends ValidationBase[BigDecimal] {
    override def constraints: Seq[Constraint[BigDecimal]] =
        Seq()
}
class UsersUser_idFollowsGetUser_idValidator(instance: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idFollowsGetUser_idConstraints(instance))
}
class TagsTag_nameMediaRecentGetTag_nameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TagsTag_nameMediaRecentGetTag_nameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TagsTag_nameMediaRecentGetTag_nameConstraints(instance))
}
class LocationsLocation_idGetLocation_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class LocationsLocation_idGetLocation_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new LocationsLocation_idGetLocation_idConstraints(instance))
}
class LocationsSearchGetLngOptConstraints(override val instance: BigDecimal) extends ValidationBase[BigDecimal] {
    override def constraints: Seq[Constraint[BigDecimal]] =
        Seq()
}
class LocationsSearchGetLngOptValidator(instance: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(new LocationsSearchGetLngOptConstraints(instance))
}
class MediaMedia_idCommentsDeleteMedia_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaMedia_idCommentsDeleteMedia_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaMedia_idCommentsDeleteMedia_idConstraints(instance))
}
class UsersUser_idMediaRecentGetMax_idOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUser_idMediaRecentGetMax_idOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idMediaRecentGetMax_idOptConstraints(instance))
}
class UsersUser_idRelationshipPostActionOptionEnumEnumConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq(enum("approve,unblock,block,unfollow,follow"))
}
class UsersUser_idRelationshipPostActionOptionEnumEnumValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idRelationshipPostActionOptionEnumEnumConstraints(instance))
}
class MediaMedia_idLikesGetMedia_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaMedia_idLikesGetMedia_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaMedia_idLikesGetMedia_idConstraints(instance))
}
class TagsTag_nameGetTag_nameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TagsTag_nameGetTag_nameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TagsTag_nameGetTag_nameConstraints(instance))
}
class MediaMedia_idLikesDeleteMedia_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaMedia_idLikesDeleteMedia_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaMedia_idLikesDeleteMedia_idConstraints(instance))
}
class MediaMedia_idCommentsGetMedia_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaMedia_idCommentsGetMedia_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaMedia_idCommentsGetMedia_idConstraints(instance))
}
class MediaSearchGetDISTANCEConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq(max(BigInt("5000"), false))
}
class MediaSearchGetDISTANCEValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaSearchGetDISTANCEConstraints(instance))
}
class LocationsLocation_idMediaRecentGetLocation_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class LocationsLocation_idMediaRecentGetLocation_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new LocationsLocation_idMediaRecentGetLocation_idConstraints(instance))
}
class UsersUser_idFollowed_byGetUser_idConstraints(override val instance: BigDecimal) extends ValidationBase[BigDecimal] {
    override def constraints: Seq[Constraint[BigDecimal]] =
        Seq()
}
class UsersUser_idFollowed_byGetUser_idValidator(instance: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idFollowed_byGetUser_idConstraints(instance))
}
class MediaMedia_idGetMedia_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaMedia_idGetMedia_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaMedia_idGetMedia_idConstraints(instance))
}
class MediaMedia_idCommentsPostMedia_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaMedia_idCommentsPostMedia_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaMedia_idCommentsPostMedia_idConstraints(instance))
}
class MediaShortcodeGetShortcodeConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class MediaShortcodeGetShortcodeValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new MediaShortcodeGetShortcodeConstraints(instance))
}
class UsersUser_idGetUser_idConstraints(override val instance: BigDecimal) extends ValidationBase[BigDecimal] {
    override def constraints: Seq[Constraint[BigDecimal]] =
        Seq()
}
class UsersUser_idGetUser_idValidator(instance: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idGetUser_idConstraints(instance))
}
class GeographiesGeo_idMediaRecentGetGeo_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class GeographiesGeo_idMediaRecentGetGeo_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new GeographiesGeo_idMediaRecentGetGeo_idConstraints(instance))
}
class MediaMedia_idLikesPostMedia_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaMedia_idLikesPostMedia_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaMedia_idLikesPostMedia_idConstraints(instance))
}
class UsersSearchGetQConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersSearchGetQValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersSearchGetQConstraints(instance))
}
class UsersUser_idRelationshipPostUser_idConstraints(override val instance: BigDecimal) extends ValidationBase[BigDecimal] {
    override def constraints: Seq[Constraint[BigDecimal]] =
        Seq()
}
class UsersUser_idRelationshipPostUser_idValidator(instance: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idRelationshipPostUser_idConstraints(instance))
}
class UsersUser_idMediaRecentGetUser_idConstraints(override val instance: BigDecimal) extends ValidationBase[BigDecimal] {
    override def constraints: Seq[Constraint[BigDecimal]] =
        Seq()
}
class UsersUser_idMediaRecentGetUser_idValidator(instance: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idMediaRecentGetUser_idConstraints(instance))
}
// ----- complex type validators -----

// ----- enum delegating validators -----
class UsersUser_idRelationshipPostActionOptionEnumValidator(instance: UsersUser_idRelationshipPostActionOptionEnum) extends RecursiveValidator {
    override val validators = Seq(new UsersUser_idRelationshipPostActionOptionEnumEnumValidator(instance.value))
}

// ----- option delegating validators -----
class UsersUser_idMediaRecentGetCountValidator(instance: Option[BigInt]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersUser_idMediaRecentGetCountOptValidator(_) }
}
class LocationsSearchGetLngValidator(instance: Option[BigDecimal]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new LocationsSearchGetLngOptValidator(_) }
}
class UsersUser_idMediaRecentGetMax_idValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersUser_idMediaRecentGetMax_idOptValidator(_) }
}
class UsersUser_idRelationshipPostActionValidator(instance: Option[UsersUser_idRelationshipPostActionOptionEnum]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersUser_idRelationshipPostActionOptionEnumValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class UsersUser_idGetValidator(user_id: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idGetUser_idValidator(user_id)
    
    )
}
class UsersUser_idFollowed_byGetValidator(user_id: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idFollowed_byGetUser_idValidator(user_id)
    
    )
}
class MediaMedia_idLikesGetValidator(media_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idLikesGetMedia_idValidator(media_id)
    
    )
}
class LocationsSearchGetValidator(foursquare_v2_id: Option[BigInt], facebook_places_id: Option[BigInt], distance: Option[BigInt], lat: Option[BigDecimal], foursquare_id: Option[BigInt], lng: Option[BigDecimal]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idMediaRecentGetCountValidator(foursquare_v2_id), 
    
        new UsersUser_idMediaRecentGetCountValidator(facebook_places_id), 
    
        new UsersUser_idMediaRecentGetCountValidator(distance), 
    
        new LocationsSearchGetLngValidator(lat), 
    
        new UsersUser_idMediaRecentGetCountValidator(foursquare_id), 
    
        new LocationsSearchGetLngValidator(lng)
    
    )
}
class MediaMedia_idCommentsDeleteValidator(media_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idCommentsDeleteMedia_idValidator(media_id)
    
    )
}
class UsersSelfMediaLikedGetValidator(count: Option[BigInt], max_like_id: Option[BigInt]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idMediaRecentGetCountValidator(count), 
    
        new UsersUser_idMediaRecentGetCountValidator(max_like_id)
    
    )
}
class TagsSearchGetValidator(q: Option[String]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idMediaRecentGetMax_idValidator(q)
    
    )
}
class MediaMedia_idCommentsGetValidator(media_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idCommentsGetMedia_idValidator(media_id)
    
    )
}
class MediaMedia_idLikesDeleteValidator(media_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idLikesDeleteMedia_idValidator(media_id)
    
    )
}
class MediaMedia_idGetValidator(media_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idGetMedia_idValidator(media_id)
    
    )
}
class MediaShortcodeGetValidator(shortcode: String) extends RecursiveValidator {
    override val validators = Seq(
        new MediaShortcodeGetShortcodeValidator(shortcode)
    
    )
}
class UsersSearchGetValidator(q: String, count: Option[String]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersSearchGetQValidator(q), 
    
        new UsersUser_idMediaRecentGetMax_idValidator(count)
    
    )
}
class MediaMedia_idCommentsPostValidator(media_id: BigInt, tEXT: Option[BigDecimal]) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idCommentsPostMedia_idValidator(media_id), 
    
        new LocationsSearchGetLngValidator(tEXT)
    
    )
}
class MediaMedia_idLikesPostValidator(media_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idLikesPostMedia_idValidator(media_id)
    
    )
}
class UsersUser_idRelationshipPostValidator(user_id: BigDecimal, action: Option[UsersUser_idRelationshipPostActionOptionEnum]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idRelationshipPostUser_idValidator(user_id), 
    
        new UsersUser_idRelationshipPostActionValidator(action)
    
    )
}
class TagsTag_nameGetValidator(tag_name: String) extends RecursiveValidator {
    override val validators = Seq(
        new TagsTag_nameGetTag_nameValidator(tag_name)
    
    )
}
class LocationsLocation_idGetValidator(location_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new LocationsLocation_idGetLocation_idValidator(location_id)
    
    )
}
class LocationsLocation_idMediaRecentGetValidator(location_id: BigInt, max_timestamp: Option[BigInt], min_timestamp: Option[BigInt], min_id: Option[String], max_id: Option[String]) extends RecursiveValidator {
    override val validators = Seq(
        new LocationsLocation_idMediaRecentGetLocation_idValidator(location_id), 
    
        new UsersUser_idMediaRecentGetCountValidator(max_timestamp), 
    
        new UsersUser_idMediaRecentGetCountValidator(min_timestamp), 
    
        new UsersUser_idMediaRecentGetMax_idValidator(min_id), 
    
        new UsersUser_idMediaRecentGetMax_idValidator(max_id)
    
    )
}
class MediaSearchGetValidator(mAX_TIMESTAMP: Option[BigInt], dISTANCE: BigInt, lNG: Option[BigDecimal], mIN_TIMESTAMP: Option[BigInt], lAT: Option[BigDecimal]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idMediaRecentGetCountValidator(mAX_TIMESTAMP), 
    
        new MediaSearchGetDISTANCEValidator(dISTANCE), 
    
        new LocationsSearchGetLngValidator(lNG), 
    
        new UsersUser_idMediaRecentGetCountValidator(mIN_TIMESTAMP), 
    
        new LocationsSearchGetLngValidator(lAT)
    
    )
}
class TagsTag_nameMediaRecentGetValidator(tag_name: String) extends RecursiveValidator {
    override val validators = Seq(
        new TagsTag_nameMediaRecentGetTag_nameValidator(tag_name)
    
    )
}
class UsersUser_idFollowsGetValidator(user_id: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idFollowsGetUser_idValidator(user_id)
    
    )
}
class UsersUser_idMediaRecentGetValidator(user_id: BigDecimal, max_timestamp: Option[BigInt], min_id: Option[String], min_timestamp: Option[BigInt], max_id: Option[String], count: Option[BigInt]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idMediaRecentGetUser_idValidator(user_id), 
    
        new UsersUser_idMediaRecentGetCountValidator(max_timestamp), 
    
        new UsersUser_idMediaRecentGetMax_idValidator(min_id), 
    
        new UsersUser_idMediaRecentGetCountValidator(min_timestamp), 
    
        new UsersUser_idMediaRecentGetMax_idValidator(max_id), 
    
        new UsersUser_idMediaRecentGetCountValidator(count)
    
    )
}
class UsersSelfFeedGetValidator(count: Option[BigInt], max_id: Option[BigInt], min_id: Option[BigInt]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idMediaRecentGetCountValidator(count), 
    
        new UsersUser_idMediaRecentGetCountValidator(max_id), 
    
        new UsersUser_idMediaRecentGetCountValidator(min_id)
    
    )
}
class GeographiesGeo_idMediaRecentGetValidator(geo_id: BigInt, count: Option[BigInt], min_id: Option[BigInt]) extends RecursiveValidator {
    override val validators = Seq(
        new GeographiesGeo_idMediaRecentGetGeo_idValidator(geo_id), 
    
        new UsersUser_idMediaRecentGetCountValidator(count), 
    
        new UsersUser_idMediaRecentGetCountValidator(min_id)
    
    )
}
