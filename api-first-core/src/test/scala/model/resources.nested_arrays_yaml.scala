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
					Field(Reference("⌿definitions⌿Activity⌿actions"), Opt(Str(None, TypeMeta(Some("The text of the error message"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿Example") → 
		TypeDef(Reference("⌿definitions⌿Example"), 
			Seq(
					Field(Reference("⌿definitions⌿Example⌿messages"), Opt(ArrResult(ArrResult(TypeDef(Reference("⌿definitions⌿Activity"), 
			Seq(
						Field(Reference("⌿definitions⌿Activity⌿actions"), Opt(Str(None, TypeMeta(Some("The text of the error message"), List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())), TypeMeta(None, List())), TypeMeta(Some("The text of the error message"), List())), TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Example⌿nestedArrays"), Opt(ArrResult(ArrResult(ArrResult(ArrResult(Str(Some("nested arrays"), TypeMeta(Some("nested arrays"), List())), TypeMeta(None, List())), TypeMeta(None, List())), TypeMeta(None, List())), TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List()))
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