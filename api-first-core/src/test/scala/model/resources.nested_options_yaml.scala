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
object nested_options_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Basic") → 
		TypeDef(Reference("⌿definitions⌿Basic"), 
			Seq(
					Field(Reference("⌿definitions⌿Basic⌿optional"), Opt(TypeDef(Reference("⌿definitions⌿Basic⌿optional"), 
			Seq(
						Field(Reference("⌿definitions⌿Basic⌿optional⌿nested_optional"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())), TypeMeta(None, List())))
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