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
object string_formats_validation_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/string⌿post⌿string_optional") → 
		Opt(Str(None, TypeMeta(None, List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))), TypeMeta(None, List())),
	Reference("⌿paths⌿/string⌿post⌿date_time_required") → 
		DateTime(TypeMeta(Some("date-time"), List())),
	Reference("⌿paths⌿/string⌿post⌿date_required") → 
		Date(TypeMeta(Some("date"), List())),
	Reference("⌿paths⌿/string⌿post⌿password_optional") → 
		Opt(Password(TypeMeta(Some("password"), List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))), TypeMeta(None, List())),
	Reference("⌿paths⌿/string⌿post⌿date_optional") → 
		Opt(Date(TypeMeta(Some("date"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/string⌿post⌿string_required") → 
		Str(None, TypeMeta(None, List("maxLength(100)", "minLength(10)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))),
	Reference("⌿paths⌿/string⌿post⌿binary_optional") → 
		Opt(BinaryString(TypeMeta(Some("binary"), List("maxLength(110)", "minLength(10)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))), TypeMeta(None, List())),
	Reference("⌿paths⌿/string⌿post⌿password_required") → 
		Password(TypeMeta(Some("password"), List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))),
	Reference("⌿paths⌿/string2⌿post⌿binary_required") → 
		BinaryString(TypeMeta(Some("binary"), List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))),
	Reference("⌿paths⌿/string⌿post⌿base64required") → 
		Base64String(TypeMeta(Some("byte"), List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))),
	Reference("⌿paths⌿/string⌿post⌿date_time_optional") → 
		Opt(DateTime(TypeMeta(Some("date-time"), List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/string⌿post⌿base64optional") → 
		Opt(Base64String(TypeMeta(Some("byte"), List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))), TypeMeta(None, List())),
	Reference("⌿paths⌿/string2⌿post⌿responses⌿200") → 
		Null(TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿string_required")) → Parameter("string_required", Str(None, TypeMeta(None, List("maxLength(100)", "minLength(10)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string2⌿post⌿binary_required")) → Parameter("binary_required", BinaryString(TypeMeta(Some("binary"), List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿password_optional")) → Parameter("password_optional", TypeRef(Reference("⌿paths⌿/string⌿post⌿password_optional")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿date_required")) → Parameter("date_required", Date(TypeMeta(Some("date"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿binary_optional")) → Parameter("binary_optional", TypeRef(Reference("⌿paths⌿/string⌿post⌿binary_optional")), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿date_optional")) → Parameter("date_optional", TypeRef(Reference("⌿paths⌿/string⌿post⌿date_optional")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿base64required")) → Parameter("base64required", Base64String(TypeMeta(Some("byte"), List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿base64optional")) → Parameter("base64optional", TypeRef(Reference("⌿paths⌿/string⌿post⌿base64optional")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿string_optional")) → Parameter("string_optional", TypeRef(Reference("⌿paths⌿/string⌿post⌿string_optional")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿date_time_required")) → Parameter("date_time_required", DateTime(TypeMeta(Some("date-time"), List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿password_required")) → Parameter("password_required", Password(TypeMeta(Some("password"), List("maxLength(10)", "minLength(0)", """pattern("""+"""""""""+"""/[1-9][A-Z0-9]*/"""+"""""""""+""".r)"""))), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/string⌿post⌿date_time_optional")) → Parameter("date_time_optional", TypeRef(Reference("⌿paths⌿/string⌿post⌿date_time_optional")), None, None, ".+", encode = true, ParameterPlace.withName("query"))
) 
 def basePath: String =null
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(POST, Path(Reference("⌿string")), 
		HandlerCall(
			"string_formats_validation.yaml",
			"String_formats_validationYaml",
			instantiate = false,
			"poststring",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿string_required")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿password_optional")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿date_required")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿binary_optional")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿date_optional")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿base64required")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿base64optional")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿string_optional")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿date_time_required")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿password_required")),
				ParameterRef(Reference("⌿paths⌿/string⌿post⌿date_time_optional"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/yaml")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/string2⌿post⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿string2")), 
		HandlerCall(
			"string_formats_validation.yaml",
			"String_formats_validationYaml",
			instantiate = false,
			"poststring2",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/string2⌿post⌿binary_required"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/yaml")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/string2⌿post⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("string_formats_validation.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 