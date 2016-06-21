package de.zalando.model
import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.ParameterPlace
import de.zalando.apifirst.naming._
import de.zalando.apifirst.Hypermedia._
import de.zalando.apifirst.Http._
import de.zalando.apifirst.Security
import java.net.URL
import Security._ 
//noinspection ScalaStyle
object type_deduplication_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿SunlightNeeds") → 
		TypeDef(Reference("⌿definitions⌿SunlightNeeds"), 
			Seq(
					Field(Reference("⌿definitions⌿SunlightNeeds⌿amount"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿Plant") → 
		TypeDef(Reference("⌿definitions⌿Plant"), 
			Seq(
					Field(Reference("⌿definitions⌿Plant⌿species"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Plant⌿name"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Plant⌿description"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Plant⌿owner_id"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Plant⌿plant_id"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Plant⌿godparent"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 6"), List())),
	Reference("⌿definitions⌿User") → 
		TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
					Field(Reference("⌿definitions⌿User⌿user_id"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿User⌿name"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿User⌿area_id"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿definitions⌿SigninData") → 
		TypeDef(Reference("⌿definitions⌿SigninData"), 
			Seq(
					Field(Reference("⌿definitions⌿SigninData⌿username"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿SigninData⌿password"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿SigninData⌿email"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿definitions⌿Watering") → 
		TypeDef(Reference("⌿definitions⌿Watering"), 
			Seq(
					Field(Reference("⌿definitions⌿Watering⌿watering_id"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Watering⌿user_id"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Watering⌿date"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿definitions⌿Area") → 
		TypeDef(Reference("⌿definitions⌿Area"), 
			Seq(
					Field(Reference("⌿definitions⌿Area⌿area_id"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Area⌿building"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Area⌿floor"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Area⌿room"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 4"), List())),
	Reference("⌿definitions⌿Location") → 
		TypeDef(Reference("⌿definitions⌿Location"), 
			Seq(
					Field(Reference("⌿definitions⌿Location⌿area_id"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Location⌿details"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Error") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), TypeRef(Reference("⌿definitions⌿Error⌿code"))),
					Field(Reference("⌿definitions⌿Error⌿message"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿Error⌿fields"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿definitions⌿WaterNeeds") → 
		TypeDef(Reference("⌿definitions⌿WaterNeeds"), 
			Seq(
					Field(Reference("⌿definitions⌿WaterNeeds⌿amount"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id"))),
					Field(Reference("⌿definitions⌿WaterNeeds⌿period"), TypeRef(Reference("⌿definitions⌿Plant⌿plant_id")))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Plant⌿plant_id") → 
		Opt(Str(None, TypeMeta(Some("Unique identifier representing a plant."), List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿Error⌿code") → 
		Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿plant_id") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/users⌿get⌿limit") → 
		Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/plants⌿get⌿limit") → 
		Opt(BInt(TypeMeta(None, List("""min(BigInt("10"), false)"""))), TypeMeta(None, List())),
	Reference("⌿paths⌿/plants⌿get⌿offset") → 
		Opt(BInt(TypeMeta(None, List("""max(BigInt("100"), false)"""))), TypeMeta(None, List())),
	Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/users⌿get⌿responses⌿200") → 
		ArrResult(TypeRef(Reference("⌿definitions⌿User")), TypeMeta(None, List())),
	Reference("⌿paths⌿/plants⌿get⌿responses⌿200") → 
		ArrResult(TypeRef(Reference("⌿definitions⌿Plant")), TypeMeta(None, List())),
	Reference("⌿paths⌿/areas⌿get⌿responses⌿200") → 
		ArrResult(TypeRef(Reference("⌿definitions⌿Area")), TypeMeta(None, List())),
	Reference("⌿paths⌿/plants/{plant_id}/pictures⌿get⌿responses⌿200") → 
		ArrResult(Str(None, TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿responses⌿200") → 
		ArrResult(TypeRef(Reference("⌿definitions⌿Watering")), TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/waterings/{watering_id}⌿put⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{user_id}/picture⌿put⌿user_id")) → Parameter("user_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/areas⌿get⌿offset")) → Parameter("offset", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/user/{user_id}/plants⌿get⌿limit")) → Parameter("limit", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/users⌿post⌿signin_data")) → Parameter("signin_data", TypeRef(Reference("⌿definitions⌿SigninData")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/water_needs⌿put⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{user_id}/picture⌿get⌿user_id")) → Parameter("user_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{user_id}⌿get⌿user_id")) → Parameter("user_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/water_needs⌿put⌿water_needs")) → Parameter("water_needs", TypeRef(Reference("⌿definitions⌿WaterNeeds")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/areas/{area_id}⌿delete⌿area_id")) → Parameter("area_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/waterings/{watering_id}⌿get⌿watering_id")) → Parameter("watering_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/location⌿get⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{user_id}/picture⌿delete⌿user_id")) → Parameter("user_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/sunlight_needs⌿put⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/location⌿put⌿location")) → Parameter("location", TypeRef(Reference("⌿definitions⌿Location")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/location⌿delete⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures⌿get⌿offset")) → Parameter("offset", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/waterings/{watering_id}⌿put⌿watering_id")) → Parameter("watering_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}⌿put⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/areas/{area_id}⌿put⌿area_id")) → Parameter("area_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/user/{user_id}/plants⌿get⌿offset")) → Parameter("offset", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿delete⌿picture_id")) → Parameter("picture_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿get⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}⌿delete⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿limit")) → Parameter("limit", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/areas/{area_id}⌿get⌿area_id")) → Parameter("area_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{user_id}⌿put⌿user_id")) → Parameter("user_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/sunlight_needs⌿get⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures⌿get⌿limit")) → Parameter("limit", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/users/{user_id}⌿delete⌿user_id")) → Parameter("user_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users⌿get⌿offset")) → Parameter("offset", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿get⌿picture_id")) → Parameter("picture_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{user_id}⌿put⌿user")) → Parameter("user", TypeRef(Reference("⌿definitions⌿User")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿delete⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/areas⌿get⌿limit")) → Parameter("limit", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/users⌿get⌿limit")) → Parameter("limit", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿picture_id")) → Parameter("picture_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/sunlight_needs⌿put⌿sunlight_needs")) → Parameter("sunlight_needs", TypeRef(Reference("⌿definitions⌿SunlightNeeds")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}⌿put⌿plant")) → Parameter("plant", TypeRef(Reference("⌿definitions⌿Plant")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/location⌿put⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/water_needs⌿get⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants⌿get⌿offset")) → Parameter("offset", TypeRef(Reference("⌿paths⌿/plants⌿get⌿offset")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/waterings/{watering_id}⌿get⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/user/{user_id}/plants⌿get⌿user_id")) → Parameter("user_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/users/{user_id}⌿delete⌿user")) → Parameter("user", TypeRef(Reference("⌿definitions⌿User")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}⌿get⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿offset")) → Parameter("offset", TypeRef(Reference("⌿paths⌿/users⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/pictures⌿get⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/plants⌿get⌿limit")) → Parameter("limit", TypeRef(Reference("⌿paths⌿/plants⌿get⌿limit")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿plant_id")) → Parameter("plant_id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path"))
) 
 def basePath: String = "/api" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿plants⌿{plant_id}⌿waterings⌿{watering_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplantsByPlant_idWateringsByWatering_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/waterings/{watering_id}⌿get⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/waterings/{watering_id}⌿get⌿watering_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿definitions⌿Watering"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿plants⌿{plant_id}⌿waterings⌿{watering_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putplantsByPlant_idWateringsByWatering_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/waterings/{watering_id}⌿put⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/waterings/{watering_id}⌿put⌿watering_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			201 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					201 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿users⌿me")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getusersMe",parameters = 
			Seq(

				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿definitions⌿User"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿plants⌿{plant_id}⌿sunlight_needs")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplantsByPlant_idSunlight_needs",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/sunlight_needs⌿get⌿plant_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			200 -> ParameterRef(Reference("⌿definitions⌿SunlightNeeds"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿plants⌿{plant_id}⌿sunlight_needs")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putplantsByPlant_idSunlight_needs",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/sunlight_needs⌿put⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/sunlight_needs⌿put⌿sunlight_needs"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿users")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getusers",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users⌿get⌿limit")),
				ParameterRef(Reference("⌿paths⌿/users⌿get⌿offset"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/users⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿users")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"postusers",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users⌿post⌿signin_data"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿areas⌿{area_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getareasByArea_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/areas/{area_id}⌿get⌿area_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿definitions⌿Area"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿areas⌿{area_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putareasByArea_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/areas/{area_id}⌿put⌿area_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			201 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					201 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿areas⌿{area_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"deleteareasByArea_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/areas/{area_id}⌿delete⌿area_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿plants")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplants",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants⌿get⌿limit")),
				ParameterRef(Reference("⌿paths⌿/plants⌿get⌿offset"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿user⌿{user_id}⌿plants")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getuserByUser_idPlants",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/user/{user_id}/plants⌿get⌿user_id")),
				ParameterRef(Reference("⌿paths⌿/user/{user_id}/plants⌿get⌿limit")),
				ParameterRef(Reference("⌿paths⌿/user/{user_id}/plants⌿get⌿offset"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿users⌿{user_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getusersByUser_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{user_id}⌿get⌿user_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿definitions⌿User"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿users⌿{user_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putusersByUser_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{user_id}⌿put⌿user_id")),
				ParameterRef(Reference("⌿paths⌿/users/{user_id}⌿put⌿user"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			201 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					201 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿users⌿{user_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"deleteusersByUser_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{user_id}⌿delete⌿user_id")),
				ParameterRef(Reference("⌿paths⌿/users/{user_id}⌿delete⌿user"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿areas")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getareas",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/areas⌿get⌿limit")),
				ParameterRef(Reference("⌿paths⌿/areas⌿get⌿offset"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/areas⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿plants⌿{plant_id}⌿location")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplantsByPlant_idLocation",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/location⌿get⌿plant_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			200 -> ParameterRef(Reference("⌿definitions⌿Location"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿plants⌿{plant_id}⌿location")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putplantsByPlant_idLocation",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/location⌿put⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/location⌿put⌿location"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿plants⌿{plant_id}⌿location")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"deleteplantsByPlant_idLocation",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/location⌿delete⌿plant_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿users⌿{user_id}⌿picture")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getusersByUser_idPicture",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{user_id}/picture⌿get⌿user_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿users⌿{user_id}⌿picture")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putusersByUser_idPicture",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{user_id}/picture⌿put⌿user_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			201 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					201 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿users⌿{user_id}⌿picture")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"deleteusersByUser_idPicture",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/users/{user_id}/picture⌿delete⌿user_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿plants⌿{plant_id}⌿pictures")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplantsByPlant_idPictures",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures⌿get⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures⌿get⌿limit")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures⌿get⌿offset"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures⌿get⌿responses⌿200")),
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿plants⌿{plant_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplantsByPlant_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}⌿get⌿plant_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿definitions⌿Plant"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿plants⌿{plant_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putplantsByPlant_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}⌿put⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}⌿put⌿plant"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			201 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					201 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿plants⌿{plant_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"deleteplantsByPlant_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}⌿delete⌿plant_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿plants⌿{plant_id}⌿waterings")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplantsByPlant_idWaterings",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿limit")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿offset"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/waterings⌿get⌿responses⌿200")),
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿plants⌿{plant_id}⌿pictures⌿{picture_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplantsByPlant_idPicturesByPicture_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿get⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿get⌿picture_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿plants⌿{plant_id}⌿pictures⌿{picture_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putplantsByPlant_idPicturesByPicture_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿picture_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			201 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					201 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿plants⌿{plant_id}⌿pictures⌿{picture_id}")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"deleteplantsByPlant_idPicturesByPicture_id",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿delete⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿delete⌿picture_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			404 -> ParameterRef(Reference("⌿definitions⌿Error")),
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿plants⌿{plant_id}⌿water_needs")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"getplantsByPlant_idWater_needs",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/water_needs⌿get⌿plant_id"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿definitions⌿WaterNeeds"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿plants⌿{plant_id}⌿water_needs")), 
		HandlerCall(
			"type_deduplication.yaml",
			"Type_deduplicationYaml",
			instantiate = false,
			"putplantsByPlant_idWater_needs",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/water_needs⌿put⌿plant_id")),
				ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/water_needs⌿put⌿water_needs"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404")),
			404 -> ParameterRef(Reference("⌿paths⌿/plants/{plant_id}/pictures/{picture_id}⌿put⌿responses⌿404"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					404 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("type_deduplication.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 