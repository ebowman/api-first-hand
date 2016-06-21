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
object nested_objects_validation_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿NestedObjects") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿plain"), TypeRef(Reference("⌿definitions⌿NestedObjects⌿plain"))),
					Field(Reference("⌿definitions⌿NestedObjects⌿nested"), TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested")))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿NestedObjects⌿nested") → 
		Opt(TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested⌿Opt")), TypeMeta(None, List())),
	Reference("⌿definitions⌿NestedObjects⌿plain") → 
		Opt(TypeRef(Reference("⌿definitions⌿NestedObjects⌿plain⌿Opt")), TypeMeta(None, List())),
	Reference("⌿definitions⌿NestedObjects⌿nested⌿Opt") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects⌿nested"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2"), TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2")))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿NestedObjects⌿plain⌿Opt") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects⌿plain"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿plain⌿simple"), Str(None, TypeMeta(None, List("""pattern("""+"""""""""+"""the pattern"""+"""""""""+""".r)"""))))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3"), TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3")))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3") → 
		Opt(TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3⌿Opt")), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿responses⌿200") → 
		Null(TypeMeta(None, List())),
	Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3⌿bottom") → 
		Opt(Str(None, TypeMeta(None, List("maxLength(30)", "minLength(3)"))), TypeMeta(None, List())),
	Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3⌿Opt") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3⌿bottom"), TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3⌿bottom")))
			), TypeMeta(Some("Named types: 1"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿nestedObject")) → Parameter("nestedObject", TypeRef(Reference("⌿definitions⌿NestedObjects")), None, None, ".+", encode = false, ParameterPlace.withName("body"))
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
			"nested_objects_validation.yaml",
			"Nested_objects_validationYaml",
			instantiate = false,
			"get",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿get⌿nestedObject"))
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

def packageName: Option[String] = Some("nested_objects_validation.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 