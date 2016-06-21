package instagram.api.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import scala.math.BigInt
import scala.math.BigDecimal
// ----- constraints and wrapper validations -----
class MediaIdOptConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaIdOptValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaIdOptConstraints(instance))
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
class LocationLatitudeOptConstraints(override val instance: BigDecimal) extends ValidationBase[BigDecimal] {
    override def constraints: Seq[Constraint[BigDecimal]] =
        Seq()
}
class LocationLatitudeOptValidator(instance: BigDecimal) extends RecursiveValidator {
    override val validators = Seq(new LocationLatitudeOptConstraints(instance))
}
class MediaMedia_idCommentsDeleteMedia_idConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class MediaMedia_idCommentsDeleteMedia_idValidator(instance: BigInt) extends RecursiveValidator {
    override val validators = Seq(new MediaMedia_idCommentsDeleteMedia_idConstraints(instance))
}
class MediaFilterOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class MediaFilterOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new MediaFilterOptConstraints(instance))
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
// ----- option delegating validators -----
class MediaIdValidator(instance: MediaId) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new MediaIdOptValidator(_) }
}
class LocationLatitudeValidator(instance: LocationLatitude) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new LocationLatitudeOptValidator(_) }
}
class MediaFilterValidator(instance: MediaFilter) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new MediaFilterOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
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
class LocationsSearchGetValidator(foursquare_v2_id: MediaId, facebook_places_id: MediaId, distance: MediaId, lat: LocationLatitude, foursquare_id: MediaId, lng: LocationLatitude) extends RecursiveValidator {
    override val validators = Seq(
        new MediaIdValidator(foursquare_v2_id), 
    
        new MediaIdValidator(facebook_places_id), 
    
        new MediaIdValidator(distance), 
    
        new LocationLatitudeValidator(lat), 
    
        new MediaIdValidator(foursquare_id), 
    
        new LocationLatitudeValidator(lng)
    
    )
}
class MediaMedia_idCommentsDeleteValidator(media_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idCommentsDeleteMedia_idValidator(media_id)
    
    )
}
class UsersSelfMediaLikedGetValidator(count: MediaId, max_like_id: MediaId) extends RecursiveValidator {
    override val validators = Seq(
        new MediaIdValidator(count), 
    
        new MediaIdValidator(max_like_id)
    
    )
}
class TagsSearchGetValidator(q: MediaFilter) extends RecursiveValidator {
    override val validators = Seq(
        new MediaFilterValidator(q)
    
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
class UsersSearchGetValidator(q: String, count: MediaFilter) extends RecursiveValidator {
    override val validators = Seq(
        new UsersSearchGetQValidator(q), 
    
        new MediaFilterValidator(count)
    
    )
}
class MediaMedia_idCommentsPostValidator(media_id: BigInt, tEXT: LocationLatitude) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idCommentsPostMedia_idValidator(media_id), 
    
        new LocationLatitudeValidator(tEXT)
    
    )
}
class MediaMedia_idLikesPostValidator(media_id: BigInt) extends RecursiveValidator {
    override val validators = Seq(
        new MediaMedia_idLikesPostMedia_idValidator(media_id)
    
    )
}
class UsersUser_idRelationshipPostValidator(user_id: BigDecimal, action: UsersUser_idRelationshipPostAction) extends RecursiveValidator {
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
class LocationsLocation_idMediaRecentGetValidator(location_id: BigInt, max_timestamp: MediaId, min_timestamp: MediaId, min_id: MediaFilter, max_id: MediaFilter) extends RecursiveValidator {
    override val validators = Seq(
        new LocationsLocation_idMediaRecentGetLocation_idValidator(location_id), 
    
        new MediaIdValidator(max_timestamp), 
    
        new MediaIdValidator(min_timestamp), 
    
        new MediaFilterValidator(min_id), 
    
        new MediaFilterValidator(max_id)
    
    )
}
class MediaSearchGetValidator(mAX_TIMESTAMP: MediaId, dISTANCE: BigInt, lNG: LocationLatitude, mIN_TIMESTAMP: MediaId, lAT: LocationLatitude) extends RecursiveValidator {
    override val validators = Seq(
        new MediaIdValidator(mAX_TIMESTAMP), 
    
        new MediaSearchGetDISTANCEValidator(dISTANCE), 
    
        new LocationLatitudeValidator(lNG), 
    
        new MediaIdValidator(mIN_TIMESTAMP), 
    
        new LocationLatitudeValidator(lAT)
    
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
class UsersUser_idMediaRecentGetValidator(user_id: BigDecimal, max_timestamp: MediaId, min_id: MediaFilter, min_timestamp: MediaId, max_id: MediaFilter, count: MediaId) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUser_idMediaRecentGetUser_idValidator(user_id), 
    
        new MediaIdValidator(max_timestamp), 
    
        new MediaFilterValidator(min_id), 
    
        new MediaIdValidator(min_timestamp), 
    
        new MediaFilterValidator(max_id), 
    
        new MediaIdValidator(count)
    
    )
}
class UsersSelfFeedGetValidator(count: MediaId, max_id: MediaId, min_id: MediaId) extends RecursiveValidator {
    override val validators = Seq(
        new MediaIdValidator(count), 
    
        new MediaIdValidator(max_id), 
    
        new MediaIdValidator(min_id)
    
    )
}
class GeographiesGeo_idMediaRecentGetValidator(geo_id: BigInt, count: MediaId, min_id: MediaId) extends RecursiveValidator {
    override val validators = Seq(
        new GeographiesGeo_idMediaRecentGetGeo_idValidator(geo_id), 
    
        new MediaIdValidator(count), 
    
        new MediaIdValidator(min_id)
    
    )
}
