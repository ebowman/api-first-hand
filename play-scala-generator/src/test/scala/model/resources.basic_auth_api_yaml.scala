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
object basic_auth_api_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/⌿get⌿responses⌿200") → 
		Null(TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](

) 
 def basePath: String =null
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	"basicAuth" -> Basic(Some("HTTP Basic Authentication. Works over `HTTP` and `HTTPS`"))
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿")), 
		HandlerCall(
			"basic.auth.api.yaml",
			"BasicAuthApiYaml",
			instantiate = false,
			"get",parameters = 
			Seq(

				)
			), 
		Set.empty[MimeType], 
		Set.empty[MimeType], 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/⌿get⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set(
			BasicConstraint("basicAuth", Basic(Some("HTTP Basic Authentication. Works over `HTTP` and `HTTPS`")))
		)))

def packageName: Option[String] = Some("basic.auth.api.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 