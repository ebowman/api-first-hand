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
					Field(Reference("⌿definitions⌿Activity⌿uuid"), Opt(Str(None, TypeMeta(Some("Unique identifier for the activity"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿PriceEstimate") → 
		TypeDef(Reference("⌿definitions⌿PriceEstimate"), 
			Seq(
					Field(Reference("⌿definitions⌿PriceEstimate⌿low_estimate"), Opt(BDcml(TypeMeta(Some("Lower bound of the estimated price."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿display_name"), Opt(Str(None, TypeMeta(Some("Display name of product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿estimate"), Opt(Str(None, TypeMeta(Some("""Formatted string of estimate in local currency of the start location. Estimate could be a range, a single number (flat rate) or "Metered" for TAXI."""), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿high_estimate"), Opt(BDcml(TypeMeta(Some("Upper bound of the estimated price."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿product_id"), Opt(Str(None, TypeMeta(Some("Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿currency_code"), Opt(Str(None, TypeMeta(Some("[ISO 4217](http://en.wikipedia.org/wiki/ISO_4217) currency code."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿surge_multiplier"), Opt(BDcml(TypeMeta(Some("Expected surge multiplier. Surge is active if surge_multiplier is greater than 1. Price estimate already factors in the surge multiplier."), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 7"), List())),
	Reference("⌿definitions⌿Product") → 
		TypeDef(Reference("⌿definitions⌿Product"), 
			Seq(
					Field(Reference("⌿definitions⌿Product⌿image"), Opt(Str(None, TypeMeta(Some("Image URL representing the product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿description"), Opt(Str(None, TypeMeta(Some("Description of product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿display_name"), Opt(Str(None, TypeMeta(Some("Display name of product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿product_id"), Opt(Str(None, TypeMeta(Some("Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿capacity"), Opt(Str(None, TypeMeta(Some("Capacity of product. For example, 4 people."), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 5"), List())),
	Reference("⌿definitions⌿Profile") → 
		TypeDef(Reference("⌿definitions⌿Profile"), 
			Seq(
					Field(Reference("⌿definitions⌿Profile⌿first_name"), Opt(Str(None, TypeMeta(Some("First name of the Uber user."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Profile⌿email"), Opt(Str(None, TypeMeta(Some("Email address of the Uber user"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Profile⌿promo_code"), Opt(Str(None, TypeMeta(Some("Promo code of the Uber user."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Profile⌿last_name"), Opt(Str(None, TypeMeta(Some("Last name of the Uber user."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Profile⌿picture"), Opt(Str(None, TypeMeta(Some("Image URL of the Uber user."), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 5"), List())),
	Reference("⌿definitions⌿Activities") → 
		TypeDef(Reference("⌿definitions⌿Activities"), 
			Seq(
					Field(Reference("⌿definitions⌿Activities⌿offset"), Opt(Intgr(TypeMeta(Some("Position in pagination."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Activities⌿limit"), Opt(Intgr(TypeMeta(Some("Number of items to retrieve (100 max)."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Activities⌿count"), Opt(Intgr(TypeMeta(Some("Total number of items available."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Activities⌿history"), Opt(Arr(TypeDef(Reference("⌿definitions⌿Activity"), 
			Seq(
						Field(Reference("⌿definitions⌿Activity⌿uuid"), Opt(Str(None, TypeMeta(Some("Unique identifier for the activity"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 4"), List())),
	Reference("⌿definitions⌿Error") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿fields"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿paths⌿/estimates/price⌿get⌿end_latitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/estimates/time⌿get⌿start_latitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/products⌿get⌿longitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/estimates/time⌿get⌿start_longitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/history⌿get⌿limit") → 
		Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/estimates/time⌿get⌿customer_uuid") → 
		Opt(UUID(TypeMeta(Some("uuid"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/history⌿get⌿offset") → 
		Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/estimates/time⌿get⌿product_id") → 
		Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/products⌿get⌿latitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/estimates/price⌿get⌿start_latitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/estimates/price⌿get⌿end_longitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/estimates/price⌿get⌿start_longitude") → 
		Dbl(TypeMeta(Some("double"), List())),
	Reference("⌿paths⌿/history⌿get⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿fields"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿paths⌿/history⌿get⌿responses⌿200") → 
		TypeDef(Reference("⌿definitions⌿Activities"), 
			Seq(
					Field(Reference("⌿definitions⌿Activities⌿offset"), Opt(Intgr(TypeMeta(Some("Position in pagination."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Activities⌿limit"), Opt(Intgr(TypeMeta(Some("Number of items to retrieve (100 max)."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Activities⌿count"), Opt(Intgr(TypeMeta(Some("Total number of items available."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Activities⌿history"), Opt(ArrResult(TypeDef(Reference("⌿definitions⌿Activity"), 
			Seq(
						Field(Reference("⌿definitions⌿Activity⌿uuid"), Opt(Str(None, TypeMeta(Some("Unique identifier for the activity"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())), TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 4"), List())),
	Reference("⌿paths⌿/products⌿get⌿responses⌿200") → 
		ArrResult(TypeDef(Reference("⌿definitions⌿Product"), 
			Seq(
					Field(Reference("⌿definitions⌿Product⌿image"), Opt(Str(None, TypeMeta(Some("Image URL representing the product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿description"), Opt(Str(None, TypeMeta(Some("Description of product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿display_name"), Opt(Str(None, TypeMeta(Some("Display name of product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿product_id"), Opt(Str(None, TypeMeta(Some("Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿capacity"), Opt(Str(None, TypeMeta(Some("Capacity of product. For example, 4 people."), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 5"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/me⌿get⌿responses⌿200") → 
		TypeDef(Reference("⌿definitions⌿Profile"), 
			Seq(
					Field(Reference("⌿definitions⌿Profile⌿first_name"), Opt(Str(None, TypeMeta(Some("First name of the Uber user."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Profile⌿email"), Opt(Str(None, TypeMeta(Some("Email address of the Uber user"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Profile⌿promo_code"), Opt(Str(None, TypeMeta(Some("Promo code of the Uber user."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Profile⌿last_name"), Opt(Str(None, TypeMeta(Some("Last name of the Uber user."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Profile⌿picture"), Opt(Str(None, TypeMeta(Some("Image URL of the Uber user."), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 5"), List())),
	Reference("⌿paths⌿/estimates/price⌿get⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿fields"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿paths⌿/estimates/time⌿get⌿responses⌿200") → 
		ArrResult(TypeDef(Reference("⌿definitions⌿Product"), 
			Seq(
					Field(Reference("⌿definitions⌿Product⌿image"), Opt(Str(None, TypeMeta(Some("Image URL representing the product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿description"), Opt(Str(None, TypeMeta(Some("Description of product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿display_name"), Opt(Str(None, TypeMeta(Some("Display name of product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿product_id"), Opt(Str(None, TypeMeta(Some("Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Product⌿capacity"), Opt(Str(None, TypeMeta(Some("Capacity of product. For example, 4 people."), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 5"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/estimates/price⌿get⌿responses⌿200") → 
		ArrResult(TypeDef(Reference("⌿definitions⌿PriceEstimate"), 
			Seq(
					Field(Reference("⌿definitions⌿PriceEstimate⌿low_estimate"), Opt(BDcml(TypeMeta(Some("Lower bound of the estimated price."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿display_name"), Opt(Str(None, TypeMeta(Some("Display name of product."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿estimate"), Opt(Str(None, TypeMeta(Some("""Formatted string of estimate in local currency of the start location. Estimate could be a range, a single number (flat rate) or "Metered" for TAXI."""), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿high_estimate"), Opt(BDcml(TypeMeta(Some("Upper bound of the estimated price."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿product_id"), Opt(Str(None, TypeMeta(Some("Unique identifier representing a specific product for a given latitude & longitude. For example, uberX in San Francisco will have a different product_id than uberX in Los Angeles"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿currency_code"), Opt(Str(None, TypeMeta(Some("[ISO 4217](http://en.wikipedia.org/wiki/ISO_4217) currency code."), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿PriceEstimate⌿surge_multiplier"), Opt(BDcml(TypeMeta(Some("Expected surge multiplier. Surge is active if surge_multiplier is greater than 1. Price estimate already factors in the surge multiplier."), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 7"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/products⌿get⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿fields"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿paths⌿/estimates/time⌿get⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿fields"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿paths⌿/me⌿get⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Error⌿fields"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 3"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/estimates/price⌿get⌿start_latitude")) → Parameter("start_latitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/price⌿get⌿end_longitude")) → Parameter("end_longitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/time⌿get⌿start_longitude")) → Parameter("start_longitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/time⌿get⌿customer_uuid")) → Parameter("customer_uuid", Opt(UUID(TypeMeta(Some("uuid"), List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/price⌿get⌿end_latitude")) → Parameter("end_latitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/time⌿get⌿start_latitude")) → Parameter("start_latitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/history⌿get⌿limit")) → Parameter("limit", Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/products⌿get⌿longitude")) → Parameter("longitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/price⌿get⌿start_longitude")) → Parameter("start_longitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/estimates/time⌿get⌿product_id")) → Parameter("product_id", Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/products⌿get⌿latitude")) → Parameter("latitude", Dbl(TypeMeta(Some("double"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/history⌿get⌿offset")) → Parameter("offset", Opt(Intgr(TypeMeta(Some("int32"), List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query"))
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
			200 -> ParameterRef(Reference("⌿paths⌿/me⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿paths⌿/me⌿get⌿responses⌿default")))),
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
		), Some(	ParameterRef(Reference("⌿paths⌿/products⌿get⌿responses⌿default")))),
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
			200 -> ParameterRef(Reference("⌿paths⌿/estimates/time⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿paths⌿/estimates/time⌿get⌿responses⌿default")))),
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
		), Some(	ParameterRef(Reference("⌿paths⌿/estimates/price⌿get⌿responses⌿default")))),
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
			200 -> ParameterRef(Reference("⌿paths⌿/history⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿paths⌿/history⌿get⌿responses⌿default")))),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)),
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("uber.api.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 