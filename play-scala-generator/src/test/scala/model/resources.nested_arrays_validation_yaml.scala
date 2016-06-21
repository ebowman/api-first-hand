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
object nested_arrays_validation_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Activity") → 
		TypeDef(Reference("⌿definitions⌿Activity"), 
			Seq(
					Field(Reference("⌿definitions⌿Activity⌿actions"), TypeRef(Reference("⌿definitions⌿Activity⌿actions")))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿Example") → 
		TypeDef(Reference("⌿definitions⌿Example"), 
			Seq(
					Field(Reference("⌿definitions⌿Example⌿messages"), TypeRef(Reference("⌿definitions⌿Example⌿messages"))),
					Field(Reference("⌿definitions⌿Example⌿nestedArrays"), TypeRef(Reference("⌿definitions⌿Example⌿nestedArrays")))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Example⌿messages") → 
		Opt(TypeRef(Reference("⌿definitions⌿Example⌿messages⌿Opt")), TypeMeta(None, List())),
	Reference("⌿definitions⌿Example⌿nestedArrays") → 
		Opt(TypeRef(Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt")), TypeMeta(None, List())),
	Reference("⌿definitions⌿Activity⌿actions") → 
		Opt(Str(None, TypeMeta(Some("The text of the error message"), List("""pattern("""+"""""""""+"""the pattern to validate"""+"""""""""+""".r)"""))), TypeMeta(None, List())),
	Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt") → 
		Arr(TypeRef(Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr")), TypeMeta(None, List("maxItems(6)", "minItems(5)")), "csv"),
	Reference("⌿definitions⌿Example⌿messages⌿Opt") → 
		Arr(TypeRef(Reference("⌿definitions⌿Example⌿messages⌿Opt⌿Arr")), TypeMeta(Some("The text of the error message"), List("maxItems(6)", "minItems(5)")), "csv"),
	Reference("⌿paths⌿/another⌿post⌿example") → 
		Opt(TypeRef(Reference("⌿definitions⌿Example")), TypeMeta(None, List())),
	Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr") → 
		Arr(TypeRef(Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr⌿Arr")), TypeMeta(None, List("maxItems(16)", "minItems(15)")), "csv"),
	Reference("⌿definitions⌿Example⌿messages⌿Opt⌿Arr") → 
		Arr(TypeRef(Reference("⌿definitions⌿Activity")), TypeMeta(None, List("maxItems(6)", "minItems(5)")), "csv"),
	Reference("⌿paths⌿/another⌿post⌿responses⌿200") → 
		Null(TypeMeta(None, List())),
	Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr⌿Arr") → 
		Arr(TypeRef(Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr⌿Arr⌿Arr")), TypeMeta(None, List("maxItems(26)", "minItems(25)")), "csv"),
	Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr⌿Arr⌿Arr") → 
		Arr(Str(Some("nested arrays"), TypeMeta(Some("nested arrays"), List("maxLength(6)", "minLength(5)"))), TypeMeta(None, List("maxItems(36)", "minItems(35)")), "csv")
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿activity")) → Parameter("activity", TypeRef(Reference("⌿definitions⌿Activity")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/another⌿post⌿example")) → Parameter("example", TypeRef(Reference("⌿paths⌿/another⌿post⌿example")), None, None, ".+", encode = false, ParameterPlace.withName("body"))
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
			"nested_arrays_validation.yaml",
			"Nested_arrays_validationYaml",
			instantiate = false,
			"get",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿get⌿activity"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/another⌿post⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿another")), 
		HandlerCall(
			"nested_arrays_validation.yaml",
			"Nested_arrays_validationYaml",
			instantiate = false,
			"postanother",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/another⌿post⌿example"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/another⌿post⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("nested_arrays_validation.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 