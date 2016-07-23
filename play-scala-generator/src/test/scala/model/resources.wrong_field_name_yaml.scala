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
object wrong_field_name_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿StatusAndCode") → 
		TypeDef(Reference("⌿definitions⌿StatusAndCode"), 
			Seq(
					Field(Reference("⌿definitions⌿StatusAndCode⌿message"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿StatusAndCode⌿Status"), TypeRef(Reference("⌿definitions⌿Status")))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Status") → 
					EnumTrait(Str(None, TypeMeta(None, List("""enum("OK,WARNING,ERROR")"""))), TypeMeta(None, List("""enum("OK,WARNING,ERROR")""")), 
				Set(
					EnumObject(Str(None, TypeMeta(None, List("""enum("OK,WARNING,ERROR")"""))), "OK", TypeMeta(Some("OK"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("OK,WARNING,ERROR")"""))), "WARNING", TypeMeta(Some("WARNING"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("OK,WARNING,ERROR")"""))), "ERROR", TypeMeta(Some("ERROR"), List()))

				)),
	Reference("⌿definitions⌿Status⌿ERROR") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("OK,WARNING,ERROR")"""))), "ERROR", TypeMeta(Some("ERROR"), List())),
	Reference("⌿definitions⌿Status⌿WARNING") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("OK,WARNING,ERROR")"""))), "WARNING", TypeMeta(Some("WARNING"), List())),
	Reference("⌿definitions⌿Status⌿OK") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("OK,WARNING,ERROR")"""))), "OK", TypeMeta(Some("OK"), List())),
	Reference("⌿paths⌿/⌿get⌿codes") → 
					EnumTrait(Str(None, TypeMeta(None, List("""enum("Get,GET,get")"""))), TypeMeta(None, List("""enum("Get,GET,get")""")), 
				Set(
					EnumObject(Str(None, TypeMeta(None, List("""enum("Get,GET,get")"""))), "Get", TypeMeta(Some("Get"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("Get,GET,get")"""))), "GET", TypeMeta(Some("GET"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("Get,GET,get")"""))), "get", TypeMeta(Some("get"), List()))

				)),
	Reference("⌿paths⌿/⌿get⌿optCodes") → 
		Opt(TypeRef(Reference("⌿paths⌿/⌿get⌿optCodes⌿Opt")), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿codes⌿get") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("Get,GET,get")"""))), "get", TypeMeta(Some("get"), List())),
	Reference("⌿paths⌿/⌿get⌿optCodes⌿Opt") → 
					EnumTrait(Str(None, TypeMeta(None, List("""enum("put,PUT,Put")"""))), TypeMeta(None, List("""enum("put,PUT,Put")""")), 
				Set(
					EnumObject(Str(None, TypeMeta(None, List("""enum("put,PUT,Put")"""))), "put", TypeMeta(Some("put"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("put,PUT,Put")"""))), "PUT", TypeMeta(Some("PUT"), List())),
					EnumObject(Str(None, TypeMeta(None, List("""enum("put,PUT,Put")"""))), "Put", TypeMeta(Some("Put"), List()))

				)),
	Reference("⌿paths⌿/⌿get⌿codes⌿GET") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("Get,GET,get")"""))), "GET", TypeMeta(Some("GET"), List())),
	Reference("⌿paths⌿/⌿get⌿codes⌿Get") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("Get,GET,get")"""))), "Get", TypeMeta(Some("Get"), List())),
	Reference("⌿paths⌿/⌿get⌿optCodes⌿Opt⌿Put") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("put,PUT,Put")"""))), "Put", TypeMeta(Some("Put"), List())),
	Reference("⌿paths⌿/⌿get⌿optCodes⌿Opt⌿PUT") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("put,PUT,Put")"""))), "PUT", TypeMeta(Some("PUT"), List())),
	Reference("⌿paths⌿/⌿get⌿optCodes⌿Opt⌿put") → 
					EnumObject(Str(None, TypeMeta(None, List("""enum("put,PUT,Put")"""))), "put", TypeMeta(Some("put"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿optCodes")) → Parameter("optCodes", TypeRef(Reference("⌿paths⌿/⌿get⌿optCodes")), None, None, ".+", encode = false, ParameterPlace.withName("header")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿codes")) → Parameter("codes", TypeRef(Reference("⌿paths⌿/⌿get⌿codes")), None, None, ".+", encode = false, ParameterPlace.withName("header"))
) 
 def basePath: String = "/status" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿")),
		HandlerCall(
			"wrong_field_name.yaml",
			"Wrong_field_nameYaml",
			instantiate = false,
			"get",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿get⌿optCodes")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿codes"))
				)
			),
		Set.empty[MimeType],
		Set.empty[MimeType],
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿definitions⌿StatusAndCode"))
		), None),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None),
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("wrong_field_name.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 