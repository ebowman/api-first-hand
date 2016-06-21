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
object heroku_petstore_api_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Pet") → 
		TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Opt(Str(None, TypeMeta(None, List("maxLength(100)", "minLength(3)"))), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿birthday"), Opt(Intgr(TypeMeta(Some("int32"), List("max(100.toInt, false)", "min(1.toInt, false)"))), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/{petId}⌿get⌿petId") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿post⌿pet") → 
		TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Opt(Str(None, TypeMeta(None, List("maxLength(100)", "minLength(3)"))), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿birthday"), Opt(Intgr(TypeMeta(Some("int32"), List("max(100.toInt, false)", "min(1.toInt, false)"))), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/⌿get⌿limit") → 
		BInt(TypeMeta(None, List("""max(BigInt("10000"), false)""", """min(BigInt("11"), false)"""))),
	Reference("⌿paths⌿/⌿put⌿pet") → 
		Opt(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Opt(Str(None, TypeMeta(None, List("maxLength(100)", "minLength(3)"))), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿birthday"), Opt(Intgr(TypeMeta(Some("int32"), List("max(100.toInt, false)", "min(1.toInt, false)"))), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿post⌿responses⌿200") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/{petId}⌿get⌿responses⌿200") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿put⌿responses⌿200") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿responses⌿200") → 
		ArrResult(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Opt(Str(None, TypeMeta(None, List("maxLength(100)", "minLength(3)"))), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿birthday"), Opt(Intgr(TypeMeta(Some("int32"), List("max(100.toInt, false)", "min(1.toInt, false)"))), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿limit")) → Parameter("limit", BInt(TypeMeta(None, List("""max(BigInt("10000"), false)""", """min(BigInt("11"), false)"""))), None, Some("11"), ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿put⌿pet")) → Parameter("pet", Opt(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
		Field(Reference("⌿definitions⌿Pet⌿name"), Opt(Str(None, TypeMeta(None, List("maxLength(100)", "minLength(3)"))), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿birthday"), Opt(Intgr(TypeMeta(Some("int32"), List("max(100.toInt, false)", "min(1.toInt, false)"))), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/⌿post⌿pet")) → Parameter("pet", TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
		Field(Reference("⌿definitions⌿Pet⌿name"), Opt(Str(None, TypeMeta(None, List("maxLength(100)", "minLength(3)"))), TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿Pet⌿birthday"), Opt(Intgr(TypeMeta(Some("int32"), List("max(100.toInt, false)", "min(1.toInt, false)"))), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/{petId}⌿get⌿petId")) → Parameter("petId", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path"))
) 
 def basePath: String = "/pet" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿")), 
		HandlerCall(
			"heroku.petstore.api.yaml",
			"HerokuPetstoreApiYaml",
			instantiate = false,
			"get",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿get⌿limit"))
				)
			), 
		Set(MimeType("application/json"), MimeType("text/xml")), 
		Set(MimeType("application/json"), MimeType("text/html")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/⌿get⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(PUT, Path(Reference("⌿")), 
		HandlerCall(
			"heroku.petstore.api.yaml",
			"HerokuPetstoreApiYaml",
			instantiate = false,
			"put",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿put⌿pet"))
				)
			), 
		Set(MimeType("application/json"), MimeType("text/xml")), 
		Set(MimeType("application/json"), MimeType("text/html")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/⌿put⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿")), 
		HandlerCall(
			"heroku.petstore.api.yaml",
			"HerokuPetstoreApiYaml",
			instantiate = false,
			"post",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿post⌿pet"))
				)
			), 
		Set(MimeType("application/json"), MimeType("text/xml")), 
		Set(MimeType("application/json"), MimeType("text/html")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/⌿post⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(GET, Path(Reference("⌿{petId}")), 
		HandlerCall(
			"heroku.petstore.api.yaml",
			"HerokuPetstoreApiYaml",
			instantiate = false,
			"getbyPetId",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/{petId}⌿get⌿petId"))
				)
			), 
		Set(MimeType("application/json"), MimeType("text/xml")), 
		Set(MimeType("application/json"), MimeType("text/html")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/{petId}⌿get⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("heroku.petstore.api.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 