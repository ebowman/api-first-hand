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
object echo_api_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/⌿post⌿name") → 
		Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/test-path/{id}⌿get⌿id") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/test-path/{id}⌿get⌿responses⌿200") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿post⌿responses⌿200") → 
		TypeDef(Reference("⌿paths⌿/⌿post⌿responses⌿200"), 
			Seq(
					Field(Reference("⌿paths⌿/⌿post⌿responses⌿200⌿name"), TypeRef(Reference("⌿paths⌿/⌿post⌿name"))),
					Field(Reference("⌿paths⌿/⌿post⌿responses⌿200⌿year"), TypeRef(Reference("⌿paths⌿/⌿post⌿name")))
			), TypeMeta(Some("Named types: 2"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿post⌿name")) → Parameter("name", TypeRef(Reference("⌿paths⌿/⌿post⌿name")), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/⌿post⌿year")) → Parameter("year", TypeRef(Reference("⌿paths⌿/⌿post⌿name")), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/test-path/{id}⌿get⌿id")) → Parameter("id", Str(None, TypeMeta(None, List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path"))
) 
 def basePath: String = "/echo" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿")), 
		HandlerCall(
			"funnypackage",
			"EchoHandler",
			instantiate = false,
			"method",parameters = 
			Seq(

				)
			), 
		Set.empty[MimeType], 
		Set.empty[MimeType], 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/test-path/{id}⌿get⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿")), 
		HandlerCall(
			"echo",
			"EchoApiYaml",
			instantiate = false,
			"post",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿post⌿name")),
				ParameterRef(Reference("⌿paths⌿/⌿post⌿year"))
				)
			), 
		Set.empty[MimeType], 
		Set.empty[MimeType], 
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
	ApiCall(GET, Path(Reference("⌿test-path⌿{id}")), 
		HandlerCall(
			"echo",
			"EchoApiYaml",
			instantiate = false,
			"gettest_pathById",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/test-path/{id}⌿get⌿id"))
				)
			), 
		Set.empty[MimeType], 
		Set.empty[MimeType], 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/test-path/{id}⌿get⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("echo")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 