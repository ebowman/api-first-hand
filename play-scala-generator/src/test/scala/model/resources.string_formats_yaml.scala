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
object string_formats_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/⌿get⌿base64") → 
		Opt(Base64String(TypeMeta(Some("byte"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿petId") → 
		BinaryString(TypeMeta(Some("binary"), List())),
	Reference("⌿paths⌿/⌿get⌿date_time") → 
		Opt(DateTime(TypeMeta(Some("date-time"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿uuid") → 
		Opt(UUID(TypeMeta(Some("UUID"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿date") → 
		Opt(Date(TypeMeta(Some("date"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿responses⌿200") → 
		Null(TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿date_time")) → Parameter("date_time", TypeRef(Reference("⌿paths⌿/⌿get⌿date_time")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿date")) → Parameter("date", TypeRef(Reference("⌿paths⌿/⌿get⌿date")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿base64")) → Parameter("base64", TypeRef(Reference("⌿paths⌿/⌿get⌿base64")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿uuid")) → Parameter("uuid", TypeRef(Reference("⌿paths⌿/⌿get⌿uuid")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿petId")) → Parameter("petId", BinaryString(TypeMeta(Some("binary"), List())), None, None, ".+", encode = false, ParameterPlace.withName("body"))
) 
 def basePath: String =null
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿")),
		HandlerCall(
			"string_formats.yaml",
			"String_formatsYaml",
			instantiate = false,
			"get",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿get⌿date_time")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿date")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿base64")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿uuid")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿petId"))
				)
			),
		Set.empty[MimeType],
		Set(MimeType("application/json"), MimeType("application/yaml")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/⌿get⌿responses⌿200"))
		), None),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None),
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("string_formats.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 