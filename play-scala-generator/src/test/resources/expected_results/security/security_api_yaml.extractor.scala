
package security.api.yaml

import scala.concurrent.Future
import play.api.mvc._
import de.zalando.play.controllers.SwaggerSecurityExtractors._
import de.zalando.play.controllers.ArrayWrapper

object SecurityExtractorsExecutionContext {
    // this ExecutionContext might be overridden if default configuration is not suitable for some reason
    implicit val ec = de.zalando.play.controllers.Contexts.tokenChecking
}

trait SecurityExtractors {
    def petstoreImplicit_Extractor[User >: Any](scopes: String*): RequestHeader => Future[Option[User]] =
        header => oAuth(scopes)("http://petstore.swagger.wordnik.com/oauth/dialog")(header) { (token: play.api.libs.json.JsValue) =>
            ???
    }
    def githubAccessCode_Extractor[User >: Any](scopes: String*): RequestHeader => Future[Option[User]] =
        header => oAuth(scopes)("https://github.com/login/oauth/access_token")(header) { (token: play.api.libs.json.JsValue) =>
            ???
    }
    def petstorePassword_Extractor[User >: Any](scopes: String*): RequestHeader => Future[Option[User]] =
        header => oAuth(scopes)("http://petstore.swagger.wordnik.com/oauth/dialog")(header) { (token: play.api.libs.json.JsValue) =>
            ???
    }
    def justBasicStuff_Extractor[User >: Any](): RequestHeader => Future[Option[User]] =
        header => basicAuth(header) { (username: String, password: String) =>
            ???
    }
    def petstoreApplication_Extractor[User >: Any](scopes: String*): RequestHeader => Future[Option[User]] =
        header => oAuth(scopes)("http://petstore.swagger.wordnik.com/oauth/token")(header) { (token: play.api.libs.json.JsValue) =>
            ???
    }
    def internalApiKey_Extractor[User >: Any](): RequestHeader => Future[Option[User]] =
        header => headerApiKey("api_key")(header) { (apiKey: String) =>
            ???
    }
    implicit val unauthorizedContentWriter = ???
    def unauthorizedContent(req: RequestHeader) = Results.Unauthorized(???)
}
