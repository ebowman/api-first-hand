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
object required_parametrs_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/⌿get⌿test1") → 
		Str(None, TypeMeta(None, List("notNull"))),
	Reference("⌿paths⌿/⌿get⌿test2") → 
		Opt(TypeRef(Reference("⌿paths⌿/⌿get⌿test2⌿Opt")), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿responses⌿200") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿test2⌿Opt") → 
		TypeDef(Reference("⌿paths⌿/⌿get⌿test2"), 
			Seq(
					Field(Reference("⌿paths⌿/⌿get⌿test2⌿test3"), Intgr(TypeMeta(Some("int32"), List("max(100.toInt, false)", "notNull"))))
			), TypeMeta(Some("Named types: 1"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿test1")) → Parameter("test1", Str(None, TypeMeta(None, List("notNull"))), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿test2")) → Parameter("test2", TypeRef(Reference("⌿paths⌿/⌿get⌿test2")), None, None, ".+", encode = false, ParameterPlace.withName("body"))
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
			"required_parametrs.yaml",
			"Required_parametrsYaml",
			instantiate = false,
			"get",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿get⌿test1")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿test2"))
				)
			),
		Set.empty[MimeType],
		Set.empty[MimeType],
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

def packageName: Option[String] = Some("required_parametrs.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 