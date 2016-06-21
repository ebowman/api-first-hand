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
object form_data_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿paths⌿/multipart⌿post⌿avatar") → 
		Opt(File(TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/both⌿post⌿avatar") → 
		Opt(File(TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/multipart⌿post⌿name") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/url-encoded⌿post⌿name") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/both⌿post⌿name") → 
		Str(None, TypeMeta(None, List())),
	Reference("⌿paths⌿/both⌿post⌿ringtone") → 
		File(TypeMeta(None, List())),
	Reference("⌿paths⌿/both⌿post⌿year") → 
		Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/multipart⌿post⌿year") → 
		Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/url-encoded⌿post⌿avatar") → 
		File(TypeMeta(None, List())),
	Reference("⌿paths⌿/url-encoded⌿post⌿year") → 
		Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List())),
	Reference("⌿paths⌿/multipart⌿post⌿responses⌿200") → 
		TypeDef(Reference("⌿paths⌿/multipart⌿post⌿responses⌿200"), 
			Seq(
					Field(Reference("⌿paths⌿/multipart⌿post⌿responses⌿200⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/multipart⌿post⌿responses⌿200⌿year"), Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/multipart⌿post⌿responses⌿200⌿fileSize"), Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/multipart⌿post⌿responses⌿200⌿fileName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 4"), List())),
	Reference("⌿paths⌿/both⌿post⌿responses⌿200") → 
		TypeDef(Reference("⌿paths⌿/both⌿post⌿responses⌿200"), 
			Seq(
					Field(Reference("⌿paths⌿/both⌿post⌿responses⌿200⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/both⌿post⌿responses⌿200⌿year"), Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/both⌿post⌿responses⌿200⌿avatarSize"), Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/both⌿post⌿responses⌿200⌿ringtoneSize"), Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 4"), List())),
	Reference("⌿paths⌿/url-encoded⌿post⌿responses⌿200") → 
		TypeDef(Reference("⌿paths⌿/url-encoded⌿post⌿responses⌿200"), 
			Seq(
					Field(Reference("⌿paths⌿/url-encoded⌿post⌿responses⌿200⌿name"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/url-encoded⌿post⌿responses⌿200⌿year"), Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/url-encoded⌿post⌿responses⌿200⌿fileSize"), Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List()))),
					Field(Reference("⌿paths⌿/url-encoded⌿post⌿responses⌿200⌿fileName"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 4"), List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/url-encoded⌿post⌿name")) → Parameter("name", Str(None, TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/both⌿post⌿year")) → Parameter("year", Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/multipart⌿post⌿name")) → Parameter("name", Str(None, TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/url-encoded⌿post⌿year")) → Parameter("year", Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/url-encoded⌿post⌿avatar")) → Parameter("avatar", File(TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/both⌿post⌿avatar")) → Parameter("avatar", Opt(File(TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/both⌿post⌿ringtone")) → Parameter("ringtone", File(TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/both⌿post⌿name")) → Parameter("name", Str(None, TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/multipart⌿post⌿avatar")) → Parameter("avatar", Opt(File(TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData")),
	ParameterRef(	Reference("⌿paths⌿/multipart⌿post⌿year")) → Parameter("year", Opt(BInt(TypeMeta(None, List())), TypeMeta(None, List())), None, None, ".+", encode = true, ParameterPlace.withName("formData"))
) 
 def basePath: String = "/form_data" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(POST, Path(Reference("⌿multipart")), 
		HandlerCall(
			"form_data.yaml",
			"Form_dataYaml",
			instantiate = false,
			"postmultipart",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/multipart⌿post⌿name")),
				ParameterRef(Reference("⌿paths⌿/multipart⌿post⌿year")),
				ParameterRef(Reference("⌿paths⌿/multipart⌿post⌿avatar"))
				)
			), 
		Set(MimeType("multipart/form-data")), 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/multipart⌿post⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿url-encoded")), 
		HandlerCall(
			"form_data.yaml",
			"Form_dataYaml",
			instantiate = false,
			"posturl_encoded",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/url-encoded⌿post⌿name")),
				ParameterRef(Reference("⌿paths⌿/url-encoded⌿post⌿year")),
				ParameterRef(Reference("⌿paths⌿/url-encoded⌿post⌿avatar"))
				)
			), 
		Set(MimeType("application/x-www-form-urlencoded")), 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/url-encoded⌿post⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]), 
	ApiCall(POST, Path(Reference("⌿both")), 
		HandlerCall(
			"form_data.yaml",
			"Form_dataYaml",
			instantiate = false,
			"postboth",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/both⌿post⌿name")),
				ParameterRef(Reference("⌿paths⌿/both⌿post⌿year")),
				ParameterRef(Reference("⌿paths⌿/both⌿post⌿avatar")),
				ParameterRef(Reference("⌿paths⌿/both⌿post⌿ringtone"))
				)
			), 
		Set(MimeType("application/x-www-form-urlencoded"), MimeType("multipart/form-data")), 
		Set(MimeType("application/json")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/both⌿post⌿responses⌿200"))
		), None), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), None), 
		Set.empty[Security.Constraint]))

def packageName: Option[String] = Some("form_data.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 