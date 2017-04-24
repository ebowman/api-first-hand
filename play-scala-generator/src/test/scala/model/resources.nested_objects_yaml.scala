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
object nested_objects_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿NestedObjects") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿plain"), Opt(TypeRef(Reference("⌿definitions⌿NestedObjects⌿plain⌿Option⌿plain")), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿NestedObjects⌿nested"), Opt(TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested⌿Option⌿nested")), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3"), Opt(TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3⌿Option⌿nested3")), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿NestedObjects⌿plain⌿Option⌿plain") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects⌿plain"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿plain⌿simple"), Str(None, TypeMeta(None, List("""pattern("""+"""""""""+"""the pattern"""+"""""""""+""".r)"""))))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿NestedObjects⌿nested⌿Option⌿nested") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects⌿nested"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2"), TypeRef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2")))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3⌿Option⌿nested3") → 
		TypeDef(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3"), 
			Seq(
					Field(Reference("⌿definitions⌿NestedObjects⌿nested⌿nested2⌿nested3⌿bottom"), Opt(Str(None, TypeMeta(None, List("maxLength(30)", "minLength(3)"))), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List()))
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