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
object security_api_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿ErrorModel") → 
		TypeDef(Reference("⌿definitions⌿ErrorModel"), 
			Seq(
					Field(Reference("⌿definitions⌿ErrorModel⌿code"), Intgr(TypeMeta(Some("int32"), List()))),
					Field(Reference("⌿definitions⌿ErrorModel⌿message"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Pet") → 
		TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/pets/{id}⌿get⌿id") → 
		Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv"),
	Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿default") → 
		TypeDef(Reference("⌿definitions⌿ErrorModel"), 
			Seq(
					Field(Reference("⌿definitions⌿ErrorModel⌿code"), Intgr(TypeMeta(Some("int32"), List()))),
					Field(Reference("⌿definitions⌿ErrorModel⌿message"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿200") → 
		ArrResult(TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿tag"), Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())), TypeMeta(None, List()))
) 
 
 def parameters = Map[ParameterRef, Parameter](
	ParameterRef(	Reference("⌿paths⌿/pets/{id}⌿get⌿id")) → Parameter("id", Arr(Str(None, TypeMeta(None, List())), TypeMeta(None, List()), "csv"), None, None, "[^/]+", encode = true, ParameterPlace.withName("path"))
) 
 def basePath: String = "/v1" 
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
	)
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	"petstoreImplicit" -> OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "admin:org" -> "Fully manage organization, teams, and memberships." ,  "user:email" -> "Grants read access to a user’s email addresses." ,  "read:org" -> "Read-only access to organization, teams, and membership." ,  "public_repo" -> "Grants read/write access to code, commit statuses, and deployment statuses for public repositories and organizations." ,  "write:public_key" -> "Create, list, and view details for public keys." ,  "repo_deployment" -> "Grants access to deployment statuses for public and private repositories. This scope is only necessary to grant other users or services access to deployment statuses, without granting access to the code." ,  "write:repo_hook" -> "Grants read, write, and ping access to hooks in public or private repositories." ,  "admin:public_key" -> "Fully manage public keys." ,  "repo:status" -> "Grants read/write access to public and private repository commit statuses. This scope is only necessary to grant other users or services access to private repository commit statuses without granting access to the code." ,  "gist" -> "Grants write access to gists." ,  "user:follow" -> "Grants access to follow or unfollow other users." ,  "repo" -> "Grants read/write access to code, commit statuses, and deployment statuses for public and private repositories and organizations." ,  "read:repo_hook" -> "Grants read and ping access to hooks in public or private repositories." ,  "notifications" -> "Grants read access to a user’s notifications. repo also provides this access." ,  "read:public_key" -> "List and view details for public keys." ,  "admin:repo_hook" -> "Grants read, write, ping, and delete access to hooks in public or private repositories." ,  "user" -> "Grants read/write access to profile info only. Note that this scope includes user:email and user:follow." ,  "write:org" -> "Publicize and unpublicize organization membership." ,  "delete_repo" -> "Grants access to delete adminable repositories." )),
	"githubAccessCode" -> OAuth2Definition(None, Some(new URL("https://github.com/login/oauth/access_token")), Map[String, String]( "admin:org" -> "Fully manage organization, teams, and memberships." ,  "user:email" -> "Grants read access to a user’s email addresses." ,  "read:org" -> "Read-only access to organization, teams, and membership." ,  "public_repo" -> "Grants read/write access to code, commit statuses, and deployment statuses for public repositories and organizations." ,  "write:public_key" -> "Create, list, and view details for public keys." ,  "repo_deployment" -> "Grants access to deployment statuses for public and private repositories. This scope is only necessary to grant other users or services access to deployment statuses, without granting access to the code." ,  "write:repo_hook" -> "Grants read, write, and ping access to hooks in public or private repositories." ,  "admin:public_key" -> "Fully manage public keys." ,  "repo:status" -> "Grants read/write access to public and private repository commit statuses. This scope is only necessary to grant other users or services access to private repository commit statuses without granting access to the code." ,  "gist" -> "Grants write access to gists." ,  "user:follow" -> "Grants access to follow or unfollow other users." ,  "repo" -> "Grants read/write access to code, commit statuses, and deployment statuses for public and private repositories and organizations." ,  "read:repo_hook" -> "Grants read and ping access to hooks in public or private repositories." ,  "notifications" -> "Grants read access to a user’s notifications. repo also provides this access." ,  "read:public_key" -> "List and view details for public keys." ,  "admin:repo_hook" -> "Grants read, write, ping, and delete access to hooks in public or private repositories." ,  "user" -> "Grants read/write access to profile info only. Note that this scope includes user:email and user:follow." ,  "write:org" -> "Publicize and unpublicize organization membership." ,  "delete_repo" -> "Grants access to delete adminable repositories." )),
	"petstorePassword" -> OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/dialog")), Map[String, String]( "user" -> "Grants read/write access to profile" ,  "admin" -> "Fully manage" )),
	"justBasicStuff" -> Basic(None),
	"petstoreApplication" -> OAuth2Definition(None, Some(new URL("http://petstore.swagger.wordnik.com/oauth/token")), Map[String, String]( "user" -> "Grants read/write access to profile" ,  "admin" -> "Fully manage" )),
	"internalApiKey" -> ApiKey(None, "api_key", ParameterPlace.withName("header"))
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq(
	ApiCall(GET, Path(Reference("⌿pets⌿{id}")), 
		HandlerCall(
			"security.api.yaml",
			"SecurityApiYaml",
			instantiate = false,
			"getPetsById",parameters = 
			Seq(
				ParameterRef(Reference("⌿paths⌿/pets/{id}⌿get⌿id"))
				)
			), 
		Set(MimeType("application/json")), 
		Set(MimeType("application/json"), MimeType("text/html")), 
		Map.empty[String, Seq[Class[Exception]]], 
		TypesResponseInfo(
			Map[Int, ParameterRef](
			200 -> ParameterRef(Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿200"))
		), Some(	ParameterRef(Reference("⌿paths⌿/pets/{id}⌿get⌿responses⌿default")))), 
		StateResponseInfo(
				Map[Int, State](
					200 -> Self
			), Some(Self)), 
		Set(
			OAuth2Constraint("githubAccessCode", OAuth2Definition(None, Some(new URL("https://github.com/login/oauth/access_token")), Map[String, String]( "admin:org" -> "Fully manage organization, teams, and memberships." ,  "user:email" -> "Grants read access to a user’s email addresses." ,  "read:org" -> "Read-only access to organization, teams, and membership." ,  "public_repo" -> "Grants read/write access to code, commit statuses, and deployment statuses for public repositories and organizations." ,  "write:public_key" -> "Create, list, and view details for public keys." ,  "repo_deployment" -> "Grants access to deployment statuses for public and private repositories. This scope is only necessary to grant other users or services access to deployment statuses, without granting access to the code." ,  "write:repo_hook" -> "Grants read, write, and ping access to hooks in public or private repositories." ,  "admin:public_key" -> "Fully manage public keys." ,  "repo:status" -> "Grants read/write access to public and private repository commit statuses. This scope is only necessary to grant other users or services access to private repository commit statuses without granting access to the code." ,  "gist" -> "Grants write access to gists." ,  "user:follow" -> "Grants access to follow or unfollow other users." ,  "repo" -> "Grants read/write access to code, commit statuses, and deployment statuses for public and private repositories and organizations." ,  "read:repo_hook" -> "Grants read and ping access to hooks in public or private repositories." ,  "notifications" -> "Grants read access to a user’s notifications. repo also provides this access." ,  "read:public_key" -> "List and view details for public keys." ,  "admin:repo_hook" -> "Grants read, write, ping, and delete access to hooks in public or private repositories." ,  "user" -> "Grants read/write access to profile info only. Note that this scope includes user:email and user:follow." ,  "write:org" -> "Publicize and unpublicize organization membership." ,  "delete_repo" -> "Grants access to delete adminable repositories." )), Set("user")),
			ApiKeyConstraint("internalApiKey", ApiKey(None, "api_key", ParameterPlace.withName("header")))
		)))

def packageName: Option[String] = Some("security.api.yaml")

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 