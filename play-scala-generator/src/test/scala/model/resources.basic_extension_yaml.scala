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
object basic_extension_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿ErrorModel") → 
		TypeDef(Reference("⌿definitions⌿ErrorModel"), 
			Seq(
					Field(Reference("⌿definitions⌿ErrorModel⌿message"), Str(None, TypeMeta(Some("The text of the error message"), List()))),
					Field(Reference("⌿definitions⌿ErrorModel⌿code"), BInt(TypeMeta(Some("The error code"), List("""max(BigInt("600"), false)""", """min(BigInt("100"), false)"""))))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿ExtendedErrorModel") → 
					AllOf(Reference("⌿definitions⌿ExtendedErrorModel⌿ExtendedErrorModel"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeRef(Reference("⌿definitions⌿ErrorModel")),
			TypeRef(Reference("⌿definitions⌿ExtendedErrorModel⌿AllOf1"))) , None),
	Reference("⌿definitions⌿ExtendedErrorModel⌿AllOf1") → 
		TypeDef(Reference("⌿definitions⌿ExtendedErrorModel"), 
			Seq(
					Field(Reference("⌿definitions⌿ExtendedErrorModel⌿rootCause"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](

) 
 def basePath: String =null
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq()

def packageName: Option[String] = None

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 