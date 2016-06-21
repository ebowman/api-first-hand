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
object numbers_validation_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/⌿get⌿double_optional") → 
		Opt(Dbl(TypeMeta(Some("double"), List("max(10.toDouble, true)", "min(0.toDouble, true)", "multipleOf(5.toDouble)"))), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿integer_required") → 
		Intgr(TypeMeta(Some("int32"), List("max(10.toInt, false)", "min(0.toInt, false)", "multipleOf(5.toInt)"))),
	Reference("⌿paths⌿/⌿get⌿integer_optional") → 
		Opt(Intgr(TypeMeta(Some("int32"), List("max(10.toInt, true)", "min(-10.toInt, true)", "multipleOf(5.toInt)"))), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿double_required") → 
		Dbl(TypeMeta(Some("double"), List("max(10.toDouble, false)", "min(2.toDouble, false)", "multipleOf(5.toDouble)"))),
	Reference("⌿paths⌿/⌿get⌿long_optional") → 
		Opt(Lng(TypeMeta(Some("int64"), List("max(10.toLong, true)", "min(10.toLong, true)", "multipleOf(10.toLong)"))), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿float_required") → 
		Flt(TypeMeta(Some("float"), List("max(10.toFloat, true)", "min(10.toFloat, true)", "multipleOf(5.toFloat)"))),
	Reference("⌿paths⌿/⌿get⌿float_optional") → 
		Opt(Flt(TypeMeta(Some("float"), List("max(10.toFloat, false)", "min(1.toFloat, false)", "multipleOf(5.toFloat)"))), TypeMeta(None, List())),
	Reference("⌿paths⌿/⌿get⌿long_required") → 
		Lng(TypeMeta(Some("int64"), List("max(10.toLong, true)", "min(0.toLong, true)", "multipleOf(5.toLong)"))),
	Reference("⌿paths⌿/⌿get⌿responses⌿200") → 
		Null(TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿float_required")) → Parameter("float_required", Flt(TypeMeta(Some("float"), List("max(10.toFloat, true)", "min(10.toFloat, true)", "multipleOf(5.toFloat)"))), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿double_required")) → Parameter("double_required", Dbl(TypeMeta(Some("double"), List("max(10.toDouble, false)", "min(2.toDouble, false)", "multipleOf(5.toDouble)"))), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿integer_optional")) → Parameter("integer_optional", TypeRef(Reference("⌿paths⌿/⌿get⌿integer_optional")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿long_required")) → Parameter("long_required", Lng(TypeMeta(Some("int64"), List("max(10.toLong, true)", "min(0.toLong, true)", "multipleOf(5.toLong)"))), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿integer_required")) → Parameter("integer_required", Intgr(TypeMeta(Some("int32"), List("max(10.toInt, false)", "min(0.toInt, false)", "multipleOf(5.toInt)"))), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿float_optional")) → Parameter("float_optional", TypeRef(Reference("⌿paths⌿/⌿get⌿float_optional")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿double_optional")) → Parameter("double_optional", TypeRef(Reference("⌿paths⌿/⌿get⌿double_optional")), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/⌿get⌿long_optional")) → Parameter("long_optional", TypeRef(Reference("⌿paths⌿/⌿get⌿long_optional")), None, None, ".+", encode = true, ParameterPlace.withName("query"))
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
			"numbers_validation.yaml",
			"Numbers_validationYaml",
			instantiate = false,
			"get",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿get⌿float_required")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿double_required")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿integer_optional")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿long_required")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿integer_required")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿float_optional")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿double_optional")),
				ParameterRef(Reference("⌿paths⌿/⌿get⌿long_optional"))
				)
			), 
		Set.empty[MimeType], 
		Set(MimeType("application/json"), MimeType("application/yaml")), 
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

def packageName: Option[String] = Some("numbers_validation.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 