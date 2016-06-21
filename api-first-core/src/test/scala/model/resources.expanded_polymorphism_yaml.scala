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
object expanded_polymorphism_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿NewPet") → 
		TypeDef(Reference("⌿definitions⌿NewPet"), 
			Seq(
					Field(Reference("⌿definitions⌿NewPet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿NewPet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Pet") → 
					AllOf(Reference("⌿definitions⌿Pet⌿Pet"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿NewPet"), 
			Seq(
						Field(Reference("⌿definitions⌿NewPet⌿name"), Str(None, TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿NewPet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
			TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
						Field(Reference("⌿definitions⌿Pet⌿id"), Lng(TypeMeta(Some("int64"), List())))
			), TypeMeta(Some("Named types: 1"), List()))) , None),
	Reference("⌿definitions⌿Error") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Intgr(TypeMeta(Some("int32"), List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/pets/{id}⌿delete⌿id") → 
		Lng(TypeMeta(Some("int64"), List())),
	Reference("⌿paths⌿/pets⌿get⌿limit") → 
		Opt(Intgr(TypeMeta(Some("int32"), List("max(10.toInt, false)", "min(1.toInt, false)"))), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets/{id}⌿get⌿id") → 
		Lng(TypeMeta(Some("int64"), List())),
	Reference("⌿paths⌿/pets⌿get⌿tags") → 
		Opt(Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿post⌿pet") → 
		TypeDef(Reference("⌿definitions⌿NewPet"), 
			Seq(
					Field(Reference("⌿definitions⌿NewPet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿NewPet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/pets/{id}⌿delete⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Intgr(TypeMeta(Some("int32"), List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/pets⌿post⌿responses⌿200") → 
					AllOf(Reference("⌿paths⌿/pets⌿post⌿responses⌿200⌿200"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿NewPet"), 
			Seq(
						Field(Reference("⌿definitions⌿NewPet⌿name"), Str(None, TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿NewPet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
			TypeDef(Reference("⌿paths⌿/pets⌿post⌿responses⌿200"), 
			Seq(
						Field(Reference("⌿paths⌿/pets⌿post⌿responses⌿200⌿id"), Lng(TypeMeta(Some("int64"), List())))
			), TypeMeta(Some("Named types: 1"), List()))) , None),
	Reference("⌿paths⌿/pets/{id}⌿delete⌿responses⌿204") → 
		Null(TypeMeta(None, List())),
	Reference("⌿paths⌿/pets⌿get⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Intgr(TypeMeta(Some("int32"), List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Intgr(TypeMeta(Some("int32"), List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿200") → 
					AllOf(Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿200⌿200"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿NewPet"), 
			Seq(
						Field(Reference("⌿definitions⌿NewPet⌿name"), Str(None, TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿NewPet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
			TypeDef(Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿200"), 
			Seq(
						Field(Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿200⌿id"), Lng(TypeMeta(Some("int64"), List())))
			), TypeMeta(Some("Named types: 1"), List()))) , None),
	Reference("⌿paths⌿/pets⌿post⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿Error"), 
			Seq(
					Field(Reference("⌿definitions⌿Error⌿code"), Intgr(TypeMeta(Some("int32"), List()))),
					Field(Reference("⌿definitions⌿Error⌿message"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/pets⌿get⌿responses⌿200") → 
		ArrResult(			AllOf(Reference("⌿paths⌿/pets⌿get⌿responses⌿200⌿200"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿NewPet"), 
			Seq(
						Field(Reference("⌿definitions⌿NewPet⌿name"), Str(None, TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿NewPet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
			TypeDef(Reference("⌿paths⌿/pets⌿get⌿responses⌿200"), 
			Seq(
						Field(Reference("⌿paths⌿/pets⌿get⌿responses⌿200⌿id"), Lng(TypeMeta(Some("int64"), List())))
			), TypeMeta(Some("Named types: 1"), List()))) , None), TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/pets/{id}⌿get⌿id")) → Parameter("id", Lng(TypeMeta(Some("int64"), List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/pets/{id}⌿delete⌿id")) → Parameter("id", Lng(TypeMeta(Some("int64"), List())), None, None, "[^/]+", encode = true, ParameterPlace.withName("path")),
	ParameterRef(	Reference("⌿paths⌿/pets⌿post⌿pet")) → Parameter("pet", TypeDef(Reference("⌿definitions⌿NewPet"), 
			Seq(
		Field(Reference("⌿definitions⌿NewPet⌿name"), Str(None, TypeMeta(None, List()))),
		Field(Reference("⌿definitions⌿NewPet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), None, None, ".+", encode = false, ParameterPlace.withName("body")),
	ParameterRef(	Reference("⌿paths⌿/pets⌿get⌿limit")) → Parameter("limit", Opt(Intgr(TypeMeta(Some("int32"), List("max(10.toInt, false)", "min(1.toInt, false)"))), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query")),
	ParameterRef(	Reference("⌿paths⌿/pets⌿get⌿tags")) → Parameter("tags", Opt(Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv"), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("query"))
) 
 def basePath: String = "/api" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿pets")), 
		HandlerCall(
			"expanded",
			"Expanded_polymorphismYaml",
			instantiate = false,
			"findPets",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets⌿get⌿tags")),
				ParameterRef(Reference("⌿paths⌿/pets⌿get⌿limit"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json")), 
		Map("404" -> Seq(classOf[java.util.NoSuchElementException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/pets⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿paths⌿/pets⌿get⌿responses⌿default")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿pets")), 
		HandlerCall(
			"expanded",
			"Expanded_polymorphismYaml",
			instantiate = false,
			"addPet",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets⌿post⌿pet"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json")), 
		Map("404" -> Seq(classOf[java.util.NoSuchElementException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/pets⌿post⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿paths⌿/pets⌿post⌿responses⌿default")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]), 
	ApiCall(DELETE, Path(Reference("⌿pets⌿{id}")), 
		HandlerCall(
			"expanded",
			"Expanded_polymorphismYaml",
			instantiate = false,
			"deletePet",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/{id}⌿delete⌿id"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json")), 
		Map("404" -> Seq(classOf[java.util.NoSuchElementException])), 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			204 -> ParameterRef(Reference("⌿paths⌿/pets/{id}⌿delete⌿responses⌿204"))
		), Some(	ParameterRef(Reference("⌿paths⌿/pets/{id}⌿delete⌿responses⌿default")))), 
		StateResponseInfo(
				Map[Int, State](
					204 -> Self
			), Some(Self)), 
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("expanded")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 