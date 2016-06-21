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
object uber_api_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Activity") → 
		TypeDef(Reference("⌿definitions⌿Activity"), 
			Seq(
					Field(Reference("⌿definitions⌿Activity⌿uuid"), TypeRef(Reference("⌿definitions⌿Profile⌿picture")))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿PriceEstimate") → 
		TypeDef(Reference("⌿definitions⌿PriceEstimate"), 
			Seq(
					Field(Reference("⌿definitions⌿PriceEstimate⌿low_estimate"), TypeRef(Reference("⌿definitions⌿PriceEstimate⌿high_estimate"))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿display_name"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿estimate"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿high_estimate"), TypeRef(Reference("⌿definitions⌿PriceEstimate⌿high_estimate"))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿product_id"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿currency_code"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿surge_multiplier"), TypeRef(Reference("⌿definitions⌿PriceEstimate⌿high_estimate")))
			), TypeMeta(Some("Named types: 7"), List())),
	Reference("⌿definitions⌿Product") → 
		TypeDef(Reference("⌿definitions⌿Product"), 
			Seq(
					Field(Reference("⌿definitions⌿Product⌿image"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Product⌿description"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Product⌿display_name"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Product⌿product_id"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Product⌿capacity"), TypeRef(Reference("⌿definitions⌿Profile⌿picture")))
			), TypeMeta(Some("Named types: 5"), List())),
	Reference("⌿definitions⌿Profile") → 
		TypeDef(Reference("⌿definitions⌿Profile"), 
			Seq(
					Field(Reference("⌿definitions⌿Profile⌿first_name"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Profile⌿email"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Profile⌿promo_code"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Profile⌿last_name"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Profile⌿picture"), TypeRef(Reference("⌿definitions⌿Profile⌿picture")))
			), TypeMeta(Some("Named types: 5"), List())),
	Reference("⌿definitions⌿Activities") → 
		TypeDef(Reference("⌿definitions⌿Activities"), 
			Seq(
					Field(Reference("⌿definitions⌿Activities⌿offset"), TypeRef(Reference("⌿definitions⌿Error⌿code"))),
					Field(Reference("⌿definitions⌿Activities⌿limit"), TypeRef(Reference("⌿definitions⌿Error⌿code"))),
					Field(Reference("⌿definitions⌿Activities⌿count"), TypeRef(Reference("⌿definitions⌿Error⌿code"))),
					Field(Reference("⌿definitions⌿Activities⌿history"), TypeRef(Reference("⌿definitions⌿Activities⌿history")))
			), TypeMeta(Some("Named types: 4"), List())),
	Reference("⌿definitions⌿Error") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), TypeRef(Reference("⌿definitions⌿Error⌿code"))),
					Field(Reference("⌿definitions⌿Error⌿message"), TypeRef(Reference("⌿definitions⌿Profile⌿picture"))),
					Field(Reference("⌿definitions⌿Error⌿fields"), TypeRef(Reference("⌿definitions⌿Profile⌿picture")))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿definitions⌿Activities⌿history") → 
		Opt(TypeRef(Reference("⌿definitions⌿Activities⌿history⌿Opt")), TypeMeta(None, List())),
	Reference("⌿definitions⌿Profile⌿picture") → 
		Opt(Str(None, TypeMeta(Some("Image URL of the Uber user."), List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿Error⌿code") → 
		Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿PriceEstimate⌿high_estimate") → 
		Opt(BDcml(TypeMeta(Some("Upper bound of the estimated price."), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/estimates/price⌿get⌿end_latitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/estimates/time⌿get⌿customer_uuid") → 
		Opt(UUID(TypeMeta(Some("uuid"), List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿Activities⌿history⌿Opt") → 
		Arr(TypeRef(Reference("⌿definitions⌿Activity")), TypeMeta(None, List()), "csv"),
	Reference("⌿paths⌿/products⌿get⌿responses⌿200") → 
		ArrResult(TypeRef(Reference("⌿definitions⌿Product")), TypeMeta(None, List())),
	Reference("⌿paths⌿/estimates/price⌿get⌿responses⌿200") → 
		ArrResult(TypeRef(Reference("⌿definitions⌿PriceEstimate")), TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/estimates/price⌿get⌿start_latitude")) → Parameter("start_latitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/price⌿get⌿end_longitude")) → Parameter("end_longitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/time⌿get⌿start_longitude")) → Parameter("start_longitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/time⌿get⌿customer_uuid")) → Parameter("customer_uuid", TypeRef(Reference("⌿paths⌿/estimates/time⌿get⌿customer_uuid")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/price⌿get⌿end_latitude")) → Parameter("end_latitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/time⌿get⌿start_latitude")) → Parameter("start_latitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/history⌿get⌿limit")) → Parameter("limit", TypeRef(Reference("⌿definitions⌿Error⌿code")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/products⌿get⌿longitude")) → Parameter("longitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/price⌿get⌿start_longitude")) → Parameter("start_longitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/time⌿get⌿product_id")) → Parameter("product_id", TypeRef(Reference("⌿definitions⌿Profile⌿picture")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/products⌿get⌿latitude")) → Parameter("latitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/history⌿get⌿offset")) → Parameter("offset", TypeRef(Reference("⌿definitions⌿Error⌿code")), None, None, ".+", encode = true, ParameterPlace.withName("query"))
) 
 def basePath: String = "/v1" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿me")),
		HandlerCall(
			"uber.api.yaml",
			"UberApiYaml",
			instantiate = false,
			"getme",parameters = 
			Seq(

				)
			),
		Set.empty[MimeType],
		Set(MimeType("application/json")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿definitions⌿Profile"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)),
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿products")),
		HandlerCall(
			"uber.api.yaml",
			"UberApiYaml",
			instantiate = false,
			"getproducts",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/products⌿get⌿latitude")),
				ParameterRef(Reference("⌿paths⌿/products⌿get⌿longitude"))
				)
			),
		Set.empty[MimeType],
		Set(MimeType("application/json")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/products⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)),
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿estimates⌿time")),
		HandlerCall(
			"uber.api.yaml",
			"UberApiYaml",
			instantiate = false,
			"getestimatesTime",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/estimates/time⌿get⌿start_latitude")),
				ParameterRef(Reference("⌿paths⌿/estimates/time⌿get⌿start_longitude")),
				ParameterRef(Reference("⌿paths⌿/estimates/time⌿get⌿customer_uuid")),
				ParameterRef(Reference("⌿paths⌿/estimates/time⌿get⌿product_id"))
				)
			),
		Set.empty[MimeType],
		Set(MimeType("application/json")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/products⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)),
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿estimates⌿price")),
		HandlerCall(
			"uber.api.yaml",
			"UberApiYaml",
			instantiate = false,
			"getestimatesPrice",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/estimates/price⌿get⌿start_latitude")),
				ParameterRef(Reference("⌿paths⌿/estimates/price⌿get⌿start_longitude")),
				ParameterRef(Reference("⌿paths⌿/estimates/price⌿get⌿end_latitude")),
				ParameterRef(Reference("⌿paths⌿/estimates/price⌿get⌿end_longitude"))
				)
			),
		Set.empty[MimeType],
		Set(MimeType("application/json")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/estimates/price⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)),
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿history")),
		HandlerCall(
			"uber.api.yaml",
			"UberApiYaml",
			instantiate = false,
			"gethistory",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/history⌿get⌿offset")),
				ParameterRef(Reference("⌿paths⌿/history⌿get⌿limit"))
				)
			),
		Set.empty[MimeType],
		Set(MimeType("application/json")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿definitions⌿Activities"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)),
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("uber.api.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 