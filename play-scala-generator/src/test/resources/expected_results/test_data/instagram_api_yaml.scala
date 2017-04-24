package instagram.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt
import scala.math.BigDecimal

object Generators extends JsValueGenerators {
    

    
    def createOptionBigIntGenerator = _generate(OptionBigIntGenerator)
    def createBigIntGenerator = _generate(BigIntGenerator)
    def createOptionBigDecimalGenerator = _generate(OptionBigDecimalGenerator)
    def createOptionStringGenerator = _generate(OptionStringGenerator)
    def createOptionUsersUser_idRelationshipPostActionOptionEnumGenerator = _generate(OptionUsersUser_idRelationshipPostActionOptionEnumGenerator)
    def createUsersUser_idRelationshipPostActionOptionEnumGenerator = _generate(UsersUser_idRelationshipPostActionOptionEnumGenerator)
    def createBigIntNameClashGenerator = _generate(BigIntNameClashGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createBigDecimalGenerator = _generate(BigDecimalGenerator)
    def createStringGenerator = _generate(StringGenerator)
    

    
    def OptionBigIntGenerator = Gen.option(arbitrary[BigInt])
    def BigIntGenerator = arbitrary[BigInt]
    def OptionBigDecimalGenerator = Gen.option(arbitrary[BigDecimal])
    def OptionStringGenerator = Gen.option(arbitrary[String])
    def OptionUsersUser_idRelationshipPostActionOptionEnumGenerator = Gen.option(UsersUser_idRelationshipPostActionOptionEnumGenerator)
    def UsersUser_idRelationshipPostActionOptionEnumGenerator = { import UsersUser_idRelationshipPostActionOptionEnum._ ; Gen.oneOf(Seq(Follow, Approve, Unfollow, Block, Unblock)) }
    def BigIntNameClashGenerator = arbitrary[BigInt]
    def NullGenerator = arbitrary[Null]
    def BigDecimalGenerator = arbitrary[BigDecimal]
    def StringGenerator = arbitrary[String]
    

    def createUserCountsOptionCountsGenerator = _generate(UserCountsOptionCountsGenerator)
    def createUsersSelfFeedGetResponses200Generator = _generate(UsersSelfFeedGetResponses200Generator)
    def createMediaSearchGetResponses200DataOptionSeqDataGenerator = _generate(MediaSearchGetResponses200DataOptionSeqDataGenerator)
    def createTagsSearchGetResponses200MetaOptionMetaGenerator = _generate(TagsSearchGetResponses200MetaOptionMetaGenerator)
    def createMediaMedia_idCommentsDeleteResponses200Generator = _generate(MediaMedia_idCommentsDeleteResponses200Generator)
    def createUsersUser_idFollowsGetResponses200Generator = _generate(UsersUser_idFollowsGetResponses200Generator)
    def createUserGenerator = _generate(UserGenerator)
    def createTagsTag_nameMediaRecentGetResponses200Generator = _generate(TagsTag_nameMediaRecentGetResponses200Generator)
    def createImageGenerator = _generate(ImageGenerator)
    def createUsersSelfRequested_byGetResponses200Generator = _generate(UsersSelfRequested_byGetResponses200Generator)
    def createMediaMedia_idCommentsDeleteResponses200MetaOptionMetaGenerator = _generate(MediaMedia_idCommentsDeleteResponses200MetaOptionMetaGenerator)
    def createTagGenerator = _generate(TagGenerator)
    def createLocationsLocation_idGetResponses200Generator = _generate(LocationsLocation_idGetResponses200Generator)
    def createCommentGenerator = _generate(CommentGenerator)
    def createMediaGenerator = _generate(MediaGenerator)
    def createMediaMedia_idLikesGetResponses200Generator = _generate(MediaMedia_idLikesGetResponses200Generator)
    def createMediaSearchGetResponses200Generator = _generate(MediaSearchGetResponses200Generator)
    def createTagsSearchGetResponses200Generator = _generate(TagsSearchGetResponses200Generator)
    def createLikeGenerator = _generate(LikeGenerator)
    def createUsersUser_idGetResponses200Generator = _generate(UsersUser_idGetResponses200Generator)
    def createMediaMedia_idCommentsGetResponses200Generator = _generate(MediaMedia_idCommentsGetResponses200Generator)
    def createMediaLikesOptionLikesGenerator = _generate(MediaLikesOptionLikesGenerator)
    def createLocationGenerator = _generate(LocationGenerator)
    def createMediaComments_OptionComments_escGenerator = _generate(MediaComments_OptionComments_escGenerator)
    def createMiniProfileGenerator = _generate(MiniProfileGenerator)
    def createMediaVideosOptionVideosGenerator = _generate(MediaVideosOptionVideosGenerator)
    def createMediaImagesOptionImagesGenerator = _generate(MediaImagesOptionImagesGenerator)
    def createLocationsSearchGetResponses200Generator = _generate(LocationsSearchGetResponses200Generator)


    def UserCountsOptionCountsGenerator = for {
        media <- Gen.option(arbitrary[BigInt])
        follows <- Gen.option(arbitrary[BigInt])
        follwed_by <- Gen.option(arbitrary[BigInt])
    } yield UserCountsOptionCounts(media, follows, follwed_by)
    def UsersSelfFeedGetResponses200Generator = for {
        data <- Gen.option(Gen.containerOf[List,Media](MediaGenerator))
    } yield UsersSelfFeedGetResponses200(data)
    def MediaSearchGetResponses200DataOptionSeqDataGenerator = for {
        created_time <- Gen.option(arbitrary[BigInt])
        filter <- Gen.option(arbitrary[String])
        id <- Gen.option(arbitrary[BigInt])
        `type` <- Gen.option(arbitrary[String])
        location <- Gen.option(LocationGenerator)
        comments_esc <- Gen.option(MediaComments_OptionComments_escGenerator)
        tags <- Gen.option(Gen.containerOf[List,Tag](TagGenerator))
        users_in_photo <- Gen.option(Gen.containerOf[List,MiniProfile](MiniProfileGenerator))
        likes <- Gen.option(MediaLikesOptionLikesGenerator)
        videos <- Gen.option(MediaVideosOptionVideosGenerator)
        images <- Gen.option(MediaImagesOptionImagesGenerator)
        user <- Gen.option(MiniProfileGenerator)
        distance <- Gen.option(arbitrary[BigDecimal])
    } yield MediaSearchGetResponses200DataOptionSeqData(created_time, filter, id, `type`, location, comments_esc, tags, users_in_photo, likes, videos, images, user, distance)
    def TagsSearchGetResponses200MetaOptionMetaGenerator = for {
        code <- Gen.option(arbitrary[BigInt])
    } yield TagsSearchGetResponses200MetaOptionMeta(code)
    def MediaMedia_idCommentsDeleteResponses200Generator = for {
        data <- Gen.option(arbitrary[String])
        meta <- Gen.option(MediaMedia_idCommentsDeleteResponses200MetaOptionMetaGenerator)
    } yield MediaMedia_idCommentsDeleteResponses200(data, meta)
    def UsersUser_idFollowsGetResponses200Generator = for {
        data <- Gen.option(Gen.containerOf[List,MiniProfile](MiniProfileGenerator))
    } yield UsersUser_idFollowsGetResponses200(data)
    def UserGenerator = for {
        website <- Gen.option(arbitrary[String])
        profile_picture <- Gen.option(arbitrary[String])
        username <- Gen.option(arbitrary[String])
        full_name <- Gen.option(arbitrary[String])
        bio <- Gen.option(arbitrary[String])
        id <- Gen.option(arbitrary[BigInt])
        counts <- Gen.option(UserCountsOptionCountsGenerator)
    } yield User(website, profile_picture, username, full_name, bio, id, counts)
    def TagsTag_nameMediaRecentGetResponses200Generator = for {
        data <- Gen.option(Gen.containerOf[List,Tag](TagGenerator))
    } yield TagsTag_nameMediaRecentGetResponses200(data)
    def ImageGenerator = for {
        width <- Gen.option(arbitrary[BigInt])
        height <- Gen.option(arbitrary[BigInt])
        url <- Gen.option(arbitrary[String])
    } yield Image(width, height, url)
    def UsersSelfRequested_byGetResponses200Generator = for {
        meta <- Gen.option(TagsSearchGetResponses200MetaOptionMetaGenerator)
        data <- Gen.option(Gen.containerOf[List,MiniProfile](MiniProfileGenerator))
    } yield UsersSelfRequested_byGetResponses200(meta, data)
    def MediaMedia_idCommentsDeleteResponses200MetaOptionMetaGenerator = for {
        code <- Gen.option(arbitrary[BigDecimal])
    } yield MediaMedia_idCommentsDeleteResponses200MetaOptionMeta(code)
    def TagGenerator = for {
        media_count <- Gen.option(arbitrary[BigInt])
        name <- Gen.option(arbitrary[String])
    } yield Tag(media_count, name)
    def LocationsLocation_idGetResponses200Generator = for {
        data <- Gen.option(LocationGenerator)
    } yield LocationsLocation_idGetResponses200(data)
    def CommentGenerator = for {
        id <- Gen.option(arbitrary[String])
        created_time <- Gen.option(arbitrary[String])
        text <- Gen.option(arbitrary[String])
        from <- Gen.option(MiniProfileGenerator)
    } yield Comment(id, created_time, text, from)
    def MediaGenerator = for {
        created_time <- Gen.option(arbitrary[BigInt])
        filter <- Gen.option(arbitrary[String])
        id <- Gen.option(arbitrary[BigInt])
        `type` <- Gen.option(arbitrary[String])
        location <- Gen.option(LocationGenerator)
        comments_esc <- Gen.option(MediaComments_OptionComments_escGenerator)
        tags <- Gen.option(Gen.containerOf[List,Tag](TagGenerator))
        users_in_photo <- Gen.option(Gen.containerOf[List,MiniProfile](MiniProfileGenerator))
        likes <- Gen.option(MediaLikesOptionLikesGenerator)
        videos <- Gen.option(MediaVideosOptionVideosGenerator)
        images <- Gen.option(MediaImagesOptionImagesGenerator)
        user <- Gen.option(MiniProfileGenerator)
    } yield Media(created_time, filter, id, `type`, location, comments_esc, tags, users_in_photo, likes, videos, images, user)
    def MediaMedia_idLikesGetResponses200Generator = for {
        meta <- Gen.option(MediaMedia_idCommentsDeleteResponses200MetaOptionMetaGenerator)
        data <- Gen.option(Gen.containerOf[List,Like](LikeGenerator))
    } yield MediaMedia_idLikesGetResponses200(meta, data)
    def MediaSearchGetResponses200Generator = for {
        data <- Gen.option(Gen.containerOf[List,MediaSearchGetResponses200DataOptionSeqData](MediaSearchGetResponses200DataOptionSeqDataGenerator))
    } yield MediaSearchGetResponses200(data)
    def TagsSearchGetResponses200Generator = for {
        meta <- Gen.option(TagsSearchGetResponses200MetaOptionMetaGenerator)
        data <- Gen.option(Gen.containerOf[List,Tag](TagGenerator))
    } yield TagsSearchGetResponses200(meta, data)
    def LikeGenerator = for {
        first_name <- Gen.option(arbitrary[String])
        id <- Gen.option(arbitrary[String])
        last_name <- Gen.option(arbitrary[String])
        `type` <- Gen.option(arbitrary[String])
        user_name <- Gen.option(arbitrary[String])
    } yield Like(first_name, id, last_name, `type`, user_name)
    def UsersUser_idGetResponses200Generator = for {
        data <- Gen.option(UserGenerator)
    } yield UsersUser_idGetResponses200(data)
    def MediaMedia_idCommentsGetResponses200Generator = for {
        meta <- Gen.option(MediaMedia_idCommentsDeleteResponses200MetaOptionMetaGenerator)
        data <- Gen.option(Gen.containerOf[List,Comment](CommentGenerator))
    } yield MediaMedia_idCommentsGetResponses200(meta, data)
    def MediaLikesOptionLikesGenerator = for {
        count <- Gen.option(arbitrary[BigInt])
        data <- Gen.option(Gen.containerOf[List,MiniProfile](MiniProfileGenerator))
    } yield MediaLikesOptionLikes(count, data)
    def LocationGenerator = for {
        id <- Gen.option(arbitrary[String])
        name <- Gen.option(arbitrary[String])
        latitude <- Gen.option(arbitrary[BigDecimal])
        longitude <- Gen.option(arbitrary[BigDecimal])
    } yield Location(id, name, latitude, longitude)
    def MediaComments_OptionComments_escGenerator = for {
        count <- Gen.option(arbitrary[BigInt])
        data <- Gen.option(Gen.containerOf[List,Comment](CommentGenerator))
    } yield MediaComments_OptionComments_esc(count, data)
    def MiniProfileGenerator = for {
        user_name <- Gen.option(arbitrary[String])
        full_name <- Gen.option(arbitrary[String])
        id <- Gen.option(arbitrary[BigInt])
        profile_picture <- Gen.option(arbitrary[String])
    } yield MiniProfile(user_name, full_name, id, profile_picture)
    def MediaVideosOptionVideosGenerator = for {
        low_resolution <- Gen.option(ImageGenerator)
        standard_resolution <- Gen.option(ImageGenerator)
    } yield MediaVideosOptionVideos(low_resolution, standard_resolution)
    def MediaImagesOptionImagesGenerator = for {
        low_resolution <- Gen.option(ImageGenerator)
        thumbnail <- Gen.option(ImageGenerator)
        standard_resolution <- Gen.option(ImageGenerator)
    } yield MediaImagesOptionImages(low_resolution, thumbnail, standard_resolution)
    def LocationsSearchGetResponses200Generator = for {
        data <- Gen.option(Gen.containerOf[List,Location](LocationGenerator))
    } yield LocationsSearchGetResponses200(data)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}