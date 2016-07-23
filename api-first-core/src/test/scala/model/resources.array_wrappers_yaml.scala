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
object array_wrappers_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Pets") → 
		ArrResult(BInt(TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿post⌿limits") → 
		Opt(Arr(BInt(TypeMeta(None, List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿post⌿more_limits") → 
		Opt(ArrResult(BInt(TypeMeta(None, List())), TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿post⌿responses⌿200") → 
		ArrResult(BInt(TypeMeta(None, List())), TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿post⌿limits")) → Parameter("limits", Opt(Arr(BInt(TypeMeta(None, List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿post⌿more_limits")) → Parameter("more_limits", Opt(ArrResult(BInt(TypeMeta(None, List())), TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = false, ParameterPlace.withName("body"))
) 
 def basePath: String = "/api" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(POST, Path(Reference("⌿")),
		HandlerCall(
			"array_wrappers.yaml",
			"Array_wrappersYaml",
			instantiate = false,
			"post",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿post⌿limits")),
				ParameterRef(Reference("⌿paths⌿/⌿post⌿more_limits"))
				)
			),
		Set(MimeType("application/json")),
		Set(MimeType("application/json")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/⌿post⌿responses⌿200"))
		), None),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None),
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("array_wrappers.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 