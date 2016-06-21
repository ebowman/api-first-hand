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
object nested_options_validation_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Basic") → 
		TypeDef(Reference("⌿definitions⌿Basic"), 
			Seq(
					Field(Reference("⌿definitions⌿Basic⌿optional"), TypeRef(Reference("⌿definitions⌿Basic⌿optional")))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿Basic⌿optional") → 
		Opt(TypeRef(Reference("⌿definitions⌿Basic⌿optional⌿Opt")), TypeMeta(None, List())),
	Reference("⌿definitions⌿Basic⌿optional⌿Opt") → 
		TypeDef(Reference("⌿definitions⌿Basic⌿optional"), 
			Seq(
					Field(Reference("⌿definitions⌿Basic⌿optional⌿nested_optional"), TypeRef(Reference("⌿definitions⌿Basic⌿optional⌿nested_optional")))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿Basic⌿optional⌿nested_optional") → 
		Opt(Str(None, TypeMeta(None, List("maxLength(6)", "minLength(5)"))), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿responses⌿200") → 
		Null(TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿basic")) → Parameter("basic", TypeRef(Reference("⌿definitions⌿Basic")), None, None, ".+", encode = false, ParameterPlace.withName("body"))
) 
 def basePath: String = "/api" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿")), 
		HandlerCall(
			"nested_options_validation.yaml",
			"Nested_options_validationYaml",
			instantiate = false,
			"get",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿get⌿basic"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json")), 
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

def packageName: Option[String] = Some("nested_options_validation.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 