package instagram.api.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.math.BigInt
import scala.math.BigDecimal

object Generators extends JsValueGenerators {
    

    
    def createTagsSearchGetResponses200MetaGenerator = _generate(TagsSearchGetResponses200MetaGenerator)
    def createBigIntGenerator = _generate(BigIntGenerator)
    def createMediaMedia_idGetResponses200VideosStandard_resolutionGenerator = _generate(MediaMedia_idGetResponses200VideosStandard_resolutionGenerator)
    def createMediaFilterGenerator = _generate(MediaFilterGenerator)
    def createMediaMedia_idCommentsDeleteResponses200MetaGenerator = _generate(MediaMedia_idCommentsDeleteResponses200MetaGenerator)
    def createUsersSelfFeedGetResponses200DataGenerator = _generate(UsersSelfFeedGetResponses200DataGenerator)
    def createMediaTagsGenerator = _generate(MediaTagsGenerator)
    def createMediaMedia_idLikesGetResponses200DataGenerator = _generate(MediaMedia_idLikesGetResponses200DataGenerator)
    def createMediaIdGenerator = _generate(MediaIdGenerator)
    def createUsersUser_idRelationshipPostActionGenerator = _generate(UsersUser_idRelationshipPostActionGenerator)
    def createMediaTagsOptGenerator = _generate(MediaTagsOptGenerator)
    def createMediaImagesGenerator = _generate(MediaImagesGenerator)
    def createMediaLikesGenerator = _generate(MediaLikesGenerator)
    def createBigIntNameClashGenerator = _generate(BigIntNameClashGenerator)
    def createMediaMedia_idCommentsGetResponses200DataOptGenerator = _generate(MediaMedia_idCommentsGetResponses200DataOptGenerator)
    def createMediaUsers_in_photoOptGenerator = _generate(MediaUsers_in_photoOptGenerator)
    def createMediaMedia_idLikesGetResponses200DataOptGenerator = _generate(MediaMedia_idLikesGetResponses200DataOptGenerator)
    def createLocationsSearchGetResponses200DataGenerator = _generate(LocationsSearchGetResponses200DataGenerator)
    def createMediaComments_escGenerator = _generate(MediaComments_escGenerator)
    def createMediaSearchGetResponses200DataOptGenerator = _generate(MediaSearchGetResponses200DataOptGenerator)
    def createCommentFromGenerator = _generate(CommentFromGenerator)
    def createLocationsSearchGetResponses200DataOptGenerator = _generate(LocationsSearchGetResponses200DataOptGenerator)
    def createMediaSearchGetResponses200DataGenerator = _generate(MediaSearchGetResponses200DataGenerator)
    def createUsersSelfFeedGetResponses200DataOptGenerator = _generate(UsersSelfFeedGetResponses200DataOptGenerator)
    def createUsersUser_idRelationshipPostActionOptGenerator = _generate(UsersUser_idRelationshipPostActionOptGenerator)
    def createUsersUser_idGetResponses200DataGenerator = _generate(UsersUser_idGetResponses200DataGenerator)
    def createMediaVideosGenerator = _generate(MediaVideosGenerator)
    def createMediaLocationGenerator = _generate(MediaLocationGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createMediaUsers_in_photoGenerator = _generate(MediaUsers_in_photoGenerator)
    def createLocationLatitudeGenerator = _generate(LocationLatitudeGenerator)
    def createMediaMedia_idCommentsGetResponses200DataGenerator = _generate(MediaMedia_idCommentsGetResponses200DataGenerator)
    def createBigDecimalGenerator = _generate(BigDecimalGenerator)
    def createUserCountsGenerator = _generate(UserCountsGenerator)
    def createStringGenerator = _generate(StringGenerator)
    

    
    def TagsSearchGetResponses200MetaGenerator = Gen.option(UsersSelfRequested_byGetResponses200MetaOptGenerator)
    def BigIntGenerator = arbitrary[BigInt]
    def MediaMedia_idGetResponses200VideosStandard_resolutionGenerator = Gen.option(ImageGenerator)
    def MediaFilterGenerator = Gen.option(arbitrary[String])
    def MediaMedia_idCommentsDeleteResponses200MetaGenerator = Gen.option(MediaMedia_idLikesGetResponses200MetaOptGenerator)
    def UsersSelfFeedGetResponses200DataGenerator = Gen.option(UsersSelfFeedGetResponses200DataOptGenerator)
    def MediaTagsGenerator = Gen.option(MediaTagsOptGenerator)
    def MediaMedia_idLikesGetResponses200DataGenerator = Gen.option(MediaMedia_idLikesGetResponses200DataOptGenerator)
    def MediaIdGenerator = Gen.option(arbitrary[BigInt])
    def UsersUser_idRelationshipPostActionGenerator = Gen.option(UsersUser_idRelationshipPostActionOptGenerator)
    def MediaTagsOptGenerator = Gen.containerOf[List,Tag](TagGenerator)
    def MediaImagesGenerator = Gen.option(MediaImagesOptGenerator)
    def MediaLikesGenerator = Gen.option(MediaLikesOptGenerator)
    def BigIntNameClashGenerator = arbitrary[BigInt]
    def MediaMedia_idCommentsGetResponses200DataOptGenerator = Gen.containerOf[List,Comment](CommentGenerator)
    def MediaUsers_in_photoOptGenerator = Gen.containerOf[List,MiniProfile](MiniProfileGenerator)
    def MediaMedia_idLikesGetResponses200DataOptGenerator = Gen.containerOf[List,Like](LikeGenerator)
    def LocationsSearchGetResponses200DataGenerator = Gen.option(LocationsSearchGetResponses200DataOptGenerator)
    def MediaComments_escGenerator = Gen.option(MediaComments_OptGenerator)
    def MediaSearchGetResponses200DataOptGenerator = Gen.containerOf[List,MediaSearchGetResponses200DataOptArrResult](MediaSearchGetResponses200DataOptArrResultGenerator)
    def CommentFromGenerator = Gen.option(MiniProfileGenerator)
    def LocationsSearchGetResponses200DataOptGenerator = Gen.containerOf[List,Location](LocationGenerator)
    def MediaSearchGetResponses200DataGenerator = Gen.option(MediaSearchGetResponses200DataOptGenerator)
    def UsersSelfFeedGetResponses200DataOptGenerator = Gen.containerOf[List,Media](MediaGenerator)
    def UsersUser_idRelationshipPostActionOptGenerator = Gen.oneOf(Seq(Follow, Approve, Unfollow, Block, Unblock))
    def UsersUser_idGetResponses200DataGenerator = Gen.option(UserGenerator)
    def MediaVideosGenerator = Gen.option(MediaVideosOptGenerator)
    def MediaLocationGenerator = Gen.option(LocationGenerator)
    def NullGenerator = arbitrary[Null]
    def MediaUsers_in_photoGenerator = Gen.option(MediaUsers_in_photoOptGenerator)
    def LocationLatitudeGenerator = Gen.option(arbitrary[BigDecimal])
    def MediaMedia_idCommentsGetResponses200DataGenerator = Gen.option(MediaMedia_idCommentsGetResponses200DataOptGenerator)
    def BigDecimalGenerator = arbitrary[BigDecimal]
    def UserCountsGenerator = Gen.option(UserCountsOptGenerator)
    def StringGenerator = arbitrary[String]
    

    def createUsersSelfFeedGetResponses200Generator = _generate(UsersSelfFeedGetResponses200Generator)
    def createMediaMedia_idCommentsDeleteResponses200Generator = _generate(MediaMedia_idCommentsDeleteResponses200Generator)
    def createMediaSearchGetResponses200DataOptArrResultGenerator = _generate(MediaSearchGetResponses200DataOptArrResultGenerator)
    def createUsersUser_idFollowsGetResponses200Generator = _generate(UsersUser_idFollowsGetResponses200Generator)
    def createUserCountsOptGenerator = _generate(UserCountsOptGenerator)
    def createUserGenerator = _generate(UserGenerator)
    def createTagsTag_nameMediaRecentGetResponses200Generator = _generate(TagsTag_nameMediaRecentGetResponses200Generator)
    def createImageGenerator = _generate(ImageGenerator)
    def createUsersSelfRequested_byGetResponses200Generator = _generate(UsersSelfRequested_byGetResponses200Generator)
    def createTagGenerator = _generate(TagGenerator)
    def createUsersSelfRequested_byGetResponses200MetaOptGenerator = _generate(UsersSelfRequested_byGetResponses200MetaOptGenerator)
    def createLocationsLocation_idGetResponses200Generator = _generate(LocationsLocation_idGetResponses200Generator)
    def createCommentGenerator = _generate(CommentGenerator)
    def createMediaGenerator = _generate(MediaGenerator)
    def createMediaMedia_idLikesGetResponses200Generator = _generate(MediaMedia_idLikesGetResponses200Generator)
    def createMediaMedia_idLikesGetResponses200MetaOptGenerator = _generate(MediaMedia_idLikesGetResponses200MetaOptGenerator)
    def createMediaSearchGetResponses200Generator = _generate(MediaSearchGetResponses200Generator)
    def createTagsSearchGetResponses200Generator = _generate(TagsSearchGetResponses200Generator)
    def createLikeGenerator = _generate(LikeGenerator)
    def createMediaComments_OptGenerator = _generate(MediaComments_OptGenerator)
    def createUsersUser_idGetResponses200Generator = _generate(UsersUser_idGetResponses200Generator)
    def createMediaMedia_idCommentsGetResponses200Generator = _generate(MediaMedia_idCommentsGetResponses200Generator)
    def createMediaVideosOptGenerator = _generate(MediaVideosOptGenerator)
    def createLocationGenerator = _generate(LocationGenerator)
    def createMiniProfileGenerator = _generate(MiniProfileGenerator)
    def createMediaLikesOptGenerator = _generate(MediaLikesOptGenerator)
    def createLocationsSearchGetResponses200Generator = _generate(LocationsSearchGetResponses200Generator)
    def createMediaImagesOptGenerator = _generate(MediaImagesOptGenerator)


    def UsersSelfFeedGetResponses200Generator = for {
        data <- UsersSelfFeedGetResponses200DataGenerator
    } yield UsersSelfFeedGetResponses200(data)
    def MediaMedia_idCommentsDeleteResponses200Generator = for {
        meta <- MediaMedia_idCommentsDeleteResponses200MetaGenerator
        data <- MediaFilterGenerator
    } yield MediaMedia_idCommentsDeleteResponses200(meta, data)
    def MediaSearchGetResponses200DataOptArrResultGenerator = for {
        location <- MediaLocationGenerator
        created_time <- MediaIdGenerator
        comments_esc <- MediaComments_escGenerator
        tags <- MediaTagsGenerator
        users_in_photo <- MediaUsers_in_photoGenerator
        filter <- MediaFilterGenerator
        likes <- MediaLikesGenerator
        id <- MediaIdGenerator
        videos <- MediaVideosGenerator
        `type` <- MediaFilterGenerator
        images <- MediaImagesGenerator
        user <- CommentFromGenerator
        distance <- LocationLatitudeGenerator
    } yield MediaSearchGetResponses200DataOptArrResult(location, created_time, comments_esc, tags, users_in_photo, filter, likes, id, videos, `type`, images, user, distance)
    def UsersUser_idFollowsGetResponses200Generator = for {
        data <- MediaUsers_in_photoGenerator
    } yield UsersUser_idFollowsGetResponses200(data)
    def UserCountsOptGenerator = for {
        media <- MediaIdGenerator
        follows <- MediaIdGenerator
        follwed_by <- MediaIdGenerator
    } yield UserCountsOpt(media, follows, follwed_by)
    def UserGenerator = for {
        website <- MediaFilterGenerator
        profile_picture <- MediaFilterGenerator
        username <- MediaFilterGenerator
        full_name <- MediaFilterGenerator
        bio <- MediaFilterGenerator
        id <- MediaIdGenerator
        counts <- UserCountsGenerator
    } yield User(website, profile_picture, username, full_name, bio, id, counts)
    def TagsTag_nameMediaRecentGetResponses200Generator = for {
        data <- MediaTagsGenerator
    } yield TagsTag_nameMediaRecentGetResponses200(data)
    def ImageGenerator = for {
        width <- MediaIdGenerator
        height <- MediaIdGenerator
        url <- MediaFilterGenerator
    } yield Image(width, height, url)
    def UsersSelfRequested_byGetResponses200Generator = for {
        meta <- TagsSearchGetResponses200MetaGenerator
        data <- MediaUsers_in_photoGenerator
    } yield UsersSelfRequested_byGetResponses200(meta, data)
    def TagGenerator = for {
        media_count <- MediaIdGenerator
        name <- MediaFilterGenerator
    } yield Tag(media_count, name)
    def UsersSelfRequested_byGetResponses200MetaOptGenerator = for {
        code <- MediaIdGenerator
    } yield UsersSelfRequested_byGetResponses200MetaOpt(code)
    def LocationsLocation_idGetResponses200Generator = for {
        data <- MediaLocationGenerator
    } yield LocationsLocation_idGetResponses200(data)
    def CommentGenerator = for {
        id <- MediaFilterGenerator
        created_time <- MediaFilterGenerator
        text <- MediaFilterGenerator
        from <- CommentFromGenerator
    } yield Comment(id, created_time, text, from)
    def MediaGenerator = for {
        location <- MediaLocationGenerator
        created_time <- MediaIdGenerator
        comments_esc <- MediaComments_escGenerator
        tags <- MediaTagsGenerator
        users_in_photo <- MediaUsers_in_photoGenerator
        filter <- MediaFilterGenerator
        likes <- MediaLikesGenerator
        id <- MediaIdGenerator
        videos <- MediaVideosGenerator
        `type` <- MediaFilterGenerator
        images <- MediaImagesGenerator
        user <- CommentFromGenerator
    } yield Media(location, created_time, comments_esc, tags, users_in_photo, filter, likes, id, videos, `type`, images, user)
    def MediaMedia_idLikesGetResponses200Generator = for {
        meta <- MediaMedia_idCommentsDeleteResponses200MetaGenerator
        data <- MediaMedia_idLikesGetResponses200DataGenerator
    } yield MediaMedia_idLikesGetResponses200(meta, data)
    def MediaMedia_idLikesGetResponses200MetaOptGenerator = for {
        code <- LocationLatitudeGenerator
    } yield MediaMedia_idLikesGetResponses200MetaOpt(code)
    def MediaSearchGetResponses200Generator = for {
        data <- MediaSearchGetResponses200DataGenerator
    } yield MediaSearchGetResponses200(data)
    def TagsSearchGetResponses200Generator = for {
        meta <- TagsSearchGetResponses200MetaGenerator
        data <- MediaTagsGenerator
    } yield TagsSearchGetResponses200(meta, data)
    def LikeGenerator = for {
        first_name <- MediaFilterGenerator
        id <- MediaFilterGenerator
        last_name <- MediaFilterGenerator
        `type` <- MediaFilterGenerator
        user_name <- MediaFilterGenerator
    } yield Like(first_name, id, last_name, `type`, user_name)
    def MediaComments_OptGenerator = for {
        count <- MediaIdGenerator
        data <- MediaMedia_idCommentsGetResponses200DataGenerator
    } yield MediaComments_Opt(count, data)
    def UsersUser_idGetResponses200Generator = for {
        data <- UsersUser_idGetResponses200DataGenerator
    } yield UsersUser_idGetResponses200(data)
    def MediaMedia_idCommentsGetResponses200Generator = for {
        meta <- MediaMedia_idCommentsDeleteResponses200MetaGenerator
        data <- MediaMedia_idCommentsGetResponses200DataGenerator
    } yield MediaMedia_idCommentsGetResponses200(meta, data)
    def MediaVideosOptGenerator = for {
        low_resolution <- MediaMedia_idGetResponses200VideosStandard_resolutionGenerator
        standard_resolution <- MediaMedia_idGetResponses200VideosStandard_resolutionGenerator
    } yield MediaVideosOpt(low_resolution, standard_resolution)
    def LocationGenerator = for {
        id <- MediaFilterGenerator
        name <- MediaFilterGenerator
        latitude <- LocationLatitudeGenerator
        longitude <- LocationLatitudeGenerator
    } yield Location(id, name, latitude, longitude)
    def MiniProfileGenerator = for {
        user_name <- MediaFilterGenerator
        full_name <- MediaFilterGenerator
        id <- MediaIdGenerator
        profile_picture <- MediaFilterGenerator
    } yield MiniProfile(user_name, full_name, id, profile_picture)
    def MediaLikesOptGenerator = for {
        count <- MediaIdGenerator
        data <- MediaUsers_in_photoGenerator
    } yield MediaLikesOpt(count, data)
    def LocationsSearchGetResponses200Generator = for {
        data <- LocationsSearchGetResponses200DataGenerator
    } yield LocationsSearchGetResponses200(data)
    def MediaImagesOptGenerator = for {
        low_resolution <- MediaMedia_idGetResponses200VideosStandard_resolutionGenerator
        thumbnail <- MediaMedia_idGetResponses200VideosStandard_resolutionGenerator
        standard_resolution <- MediaMedia_idGetResponses200VideosStandard_resolutionGenerator
    } yield MediaImagesOpt(low_resolution, thumbnail, standard_resolution)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
}