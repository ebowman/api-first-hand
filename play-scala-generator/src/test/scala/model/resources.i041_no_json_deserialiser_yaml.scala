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
object i041_no_json_deserialiser_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Money") → 
		TypeDef(Reference("⌿definitions⌿Money"), 
			Seq(
					Field(Reference("⌿definitions⌿Money⌿id"), TypeRef(Reference("⌿definitions⌿User⌿id"))),
					Field(Reference("⌿definitions⌿Money⌿userId"), TypeRef(Reference("⌿definitions⌿User⌿id"))),
					Field(Reference("⌿definitions⌿Money⌿amount"), TypeRef(Reference("⌿definitions⌿Money⌿amount"))),
					Field(Reference("⌿definitions⌿Money⌿createDate"), TypeRef(Reference("⌿definitions⌿Money⌿createDate")))
			), TypeMeta(Some("Named types: 4"), List())),
	Reference("⌿definitions⌿User") → 
		TypeDef(Reference("⌿definitions⌿User"), 
			Seq(
					Field(Reference("⌿definitions⌿User⌿id"), TypeRef(Reference("⌿definitions⌿User⌿id"))),
					Field(Reference("⌿definitions⌿User⌿name"), TypeRef(Reference("⌿definitions⌿User⌿name"))),
					Field(Reference("⌿definitions⌿User⌿money"), TypeRef(Reference("⌿definitions⌿User⌿money")))
			), TypeMeta(Some("Named types: 3"), List())),
	Reference("⌿definitions⌿Error") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Intgr(TypeMeta(Some("int32"), List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Money⌿createDate") → 
		Opt(DateTime(TypeMeta(Some("date-time"), List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿User⌿id") → 
		Opt(Lng(TypeMeta(Some("int64"), List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿Money⌿amount") → 
		Opt(BDcml(TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿User⌿name") → 
		Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿User⌿money") → 
					AllOf(Reference("⌿definitions⌿User⌿money⌿money"), TypeMeta(Some("Schemas: 1"), List()),  Seq(
			TypeRef(Reference("⌿definitions⌿Money"))) , None),
	Reference("⌿paths⌿/user/{id}⌿get⌿id") → 
		BInt(TypeMeta(None, List())),
	Reference("⌿paths⌿/user⌿post⌿name") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/user⌿get⌿responses⌿200") → 
		ArrResult(TypeRef(Reference("⌿definitions⌿User")), TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/user⌿post⌿name")) → Parameter("name", Str(None, TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/user/{id}⌿get⌿id")) → Parameter("id", BInt(TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/user/{id}⌿put⌿id")) → Parameter("id", BInt(TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/user/{id}⌿put⌿body")) → Parameter("body", TypeRef(Reference("⌿definitions⌿User")), None, None, ".+", encode = false, ParameterPlace.withName("body"))
) 
 def basePath: String = "/v1" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿user")),
		HandlerCall(
			"i041_no_json_deserialiser.yaml",
			"I041_no_json_deserialiserYaml",
			instantiate = false,
			"listUser",parameters = 
			Seq(

				)
			),
		Set(MimeType("application/json")),
		Set(MimeType("application/json")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/user⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿definitions⌿Error")))),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)),
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿user")),
		HandlerCall(
			"i041_no_json_deserialiser.yaml",
			"I041_no_json_deserialiserYaml",
			instantiate = false,
			"createUser",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/user⌿post⌿name"))
				)
			),
		Set(MimeType("application/json")),
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
	ApiCall(GET, Path(Reference("⌿user⌿{id}")),
		HandlerCall(
			"i041_no_json_deserialiser.yaml",
			"I041_no_json_deserialiserYaml",
			instantiate = false,
			"showUserById",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/user/{id}⌿get⌿id"))
				)
			),
		Set(MimeType("application/json")),
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
	ApiCall(PUT, Path(Reference("⌿user⌿{id}")),
		HandlerCall(
			"i041_no_json_deserialiser.yaml",
			"I041_no_json_deserialiserYaml",
			instantiate = false,
			"putUser",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/user/{id}⌿put⌿id")),
				ParameterRef(Reference("⌿paths⌿/user/{id}⌿put⌿body"))
				)
			),
		Set(MimeType("application/json")),
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
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("i041_no_json_deserialiser.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 