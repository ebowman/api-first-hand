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
object i038_invalid_enum_members_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/⌿get⌿responses⌿200") → 
					EnumTrait(Str(None, TypeMeta(None, List("""enum("Status One,Status Two,Status Three")"""))), TypeMeta(None, List("""enum("Status One,Status Two,Status Three")""")), 
				Set(
					EnumObject(Str(None, TypeMeta(None, List("""enum("Status One,Status Two,Status Three")"""))), "Status One", TypeMeta(Some("Status One"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("Status One,Status Two,Status Three")"""))), "Status Two", TypeMeta(Some("Status Two"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("Status One,Status Two,Status Three")"""))), "Status Three", TypeMeta(Some("Status Three"), List()))

				)),
	Reference("⌿paths⌿/⌿get⌿responses⌿200⌿Status One") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("Status One,Status Two,Status Three")"""))), "Status One", TypeMeta(Some("Status One"), List())),
	Reference("⌿paths⌿/⌿get⌿responses⌿200⌿Status Two") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("Status One,Status Two,Status Three")"""))), "Status Two", TypeMeta(Some("Status Two"), List())),
	Reference("⌿paths⌿/⌿get⌿responses⌿200⌿Status Three") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("Status One,Status Two,Status Three")"""))), "Status Three", TypeMeta(Some("Status Three"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](

) 
 def basePath: String =null
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿")),
		HandlerCall(
			"i038_invalid_enum_members.yaml",
			"I038_invalid_enum_membersYaml",
			instantiate = false,
			"get",parameters = 
			Seq(

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

def packageName: Option[String] = Some("i038_invalid_enum_members.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 