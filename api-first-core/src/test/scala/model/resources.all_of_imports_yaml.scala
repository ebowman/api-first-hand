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
object all_of_imports_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Value") → 
		TypeDef(Reference("⌿definitions⌿Value"), 
			Seq(
					Field(Reference("⌿definitions⌿Value⌿type"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())),
	Reference("⌿definitions⌿DatetimeValue") → 
					AllOf(Reference("⌿definitions⌿DatetimeValue⌿DatetimeValue"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿Value"), 
			Seq(
						Field(Reference("⌿definitions⌿Value⌿type"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())),
			TypeDef(Reference("⌿definitions⌿DatetimeValue"), 
			Seq(
						Field(Reference("⌿definitions⌿DatetimeValue⌿value"), DateTime(TypeMeta(Some("date-time"), List())))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Value⌿type"))),
	Reference("⌿paths⌿/⌿post⌿body") → 
					AllOf(Reference("⌿paths⌿/⌿post⌿body⌿body"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿Value"), 
			Seq(
						Field(Reference("⌿definitions⌿Value⌿type"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())),
			TypeDef(Reference("⌿paths⌿/⌿post⌿body"), 
			Seq(
						Field(Reference("⌿paths⌿/⌿post⌿body⌿value"), DateTime(TypeMeta(Some("date-time"), List())))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Value⌿type"))),
	Reference("⌿paths⌿/⌿post⌿responses⌿200") → 
					AllOf(Reference("⌿paths⌿/⌿post⌿responses⌿200⌿200"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿Value"), 
			Seq(
						Field(Reference("⌿definitions⌿Value⌿type"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())),
			TypeDef(Reference("⌿paths⌿/⌿post⌿responses⌿200"), 
			Seq(
						Field(Reference("⌿paths⌿/⌿post⌿responses⌿200⌿value"), DateTime(TypeMeta(Some("date-time"), List())))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Value⌿type"))),
	Reference("⌿paths⌿/⌿post⌿responses⌿400") → 
		Null(TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/⌿post⌿body")) → Parameter("body", AllOf(Reference("⌿paths⌿/⌿post⌿body⌿body"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
TypeDef(Reference("⌿definitions⌿Value"), 
			Seq(
			Field(Reference("⌿definitions⌿Value⌿type"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 1"), List())),
TypeDef(Reference("⌿paths⌿/⌿post⌿body"), 
			Seq(
			Field(Reference("⌿paths⌿/⌿post⌿body⌿value"), DateTime(TypeMeta(Some("date-time"), List())))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Value⌿type"))), None, None, ".+", encode = false, ParameterPlace.withName("body"))
) 
 def basePath: String = "/020" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
		Reference("⌿paths⌿/⌿post⌿body") -> Reference("⌿definitions⌿Value⌿type"),
	Reference("⌿paths⌿/⌿post⌿responses⌿200") -> Reference("⌿definitions⌿Value⌿type"),
	Reference("⌿definitions⌿DatetimeValue") -> Reference("⌿definitions⌿Value⌿type"),
	Reference("⌿definitions⌿Value") -> Reference("⌿definitions⌿Value⌿type"))
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(POST, Path(Reference("⌿")),
		HandlerCall(
			"all_of_imports.yaml",
			"All_of_importsYaml",
			instantiate = false,
			"post",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/⌿post⌿body"))
				)
			),
		Set(MimeType("application/json")),
		Set(MimeType("application/json")),
		Map.empty[String, Seq[Class[Exception]]],
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/⌿post⌿responses⌿200")),
			400 -> ParameterRef(Reference("⌿paths⌿/⌿post⌿responses⌿400"))
		), None),
		StateResponseInfo(
				Map[Int, State](
					200 -> Self,
					400 -> Self
			), None),
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("all_of_imports.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 