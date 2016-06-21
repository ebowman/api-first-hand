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
object nested_arrays_yaml extends WithModel {
 
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
		Opt(Str(None, TypeMeta(Some("The text of the error message"), List())), TypeMeta(None, List())),
	Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt") → 
		Arr(TypeRef(Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr")), TypeMeta(None, List()), "csv"),
	Reference("⌿definitions⌿Example⌿messages⌿Opt") → 
		Arr(TypeRef(Reference("⌿definitions⌿Example⌿messages⌿Opt⌿Arr")), TypeMeta(Some("The text of the error message"), List()), "csv"),
	Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr") → 
		Arr(TypeRef(Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr⌿Arr")), TypeMeta(None, List()), "csv"),
	Reference("⌿definitions⌿Example⌿messages⌿Opt⌿Arr") → 
		Arr(TypeRef(Reference("⌿definitions⌿Activity")), TypeMeta(None, List()), "csv"),
	Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr⌿Arr") → 
		Arr(TypeRef(Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr⌿Arr⌿Arr")), TypeMeta(None, List()), "csv"),
	Reference("⌿definitions⌿Example⌿nestedArrays⌿Opt⌿Arr⌿Arr⌿Arr") → 
		Arr(Str(Some("nested arrays"), TypeMeta(Some("nested arrays"), List())), TypeMeta(None, List()), "csv")
) 
 
 def parameters = Map[ParameterRef, Parameter](

) 
 def basePath: String = "/api" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq()

def packageName: Option[String] = None

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 