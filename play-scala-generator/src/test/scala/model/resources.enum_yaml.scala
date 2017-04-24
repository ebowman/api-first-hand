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
object enum_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/test⌿get⌿includes") → 
		Opt(TypeRef(Reference("⌿paths⌿/test⌿get⌿includes⌿Option⌿Enum")), TypeMeta(None, List())),
	Reference("⌿paths⌿/test⌿get⌿responses⌿200") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/test⌿get⌿includes⌿Option⌿Enum") → 
					EnumTrait(Str(None, TypeMeta(None, List("""enum("option_one,option_two")"""))), TypeMeta(None, List("""enum("option_one,option_two")""")), 
				Set(
					EnumObject(Str(None, TypeMeta(None, List("""enum("option_one,option_two")"""))), "option_one", TypeMeta(Some("option_one"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("option_one,option_two")"""))), "option_two", TypeMeta(Some("option_two"), List()))

				)),
	Reference("⌿paths⌿/test⌿get⌿includes⌿Option⌿Enum⌿option_one") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("option_one,option_two")"""))), "option_one", TypeMeta(Some("option_one"), List())),
	Reference("⌿paths⌿/test⌿get⌿includes⌿Option⌿Enum⌿option_two") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("option_one,option_two")"""))), "option_two", TypeMeta(Some("option_two"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/test⌿get⌿includes")) → Parameter("includes", TypeRef(Reference("⌿paths⌿/test⌿get⌿includes")), None, None, ".+", encode = true, ParameterPlace.withName("query"))
) 
 def basePath: String =null
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿test")),
		HandlerCall(
			"enum.yaml",
			"EnumYaml",
			instantiate = false,
			"gettest",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/test⌿get⌿includes"))
				)
			),
		Set.empty[MimeType],
		Set.empty[MimeType],
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/test⌿get⌿responses⌿200"))
		), None),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None),
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("enum.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 