
package split.petstore.api.yaml

import scala.concurrent.Future
import play.api.mvc._
import de.zalando.play.controllers.SwaggerSecurityExtractors._
import de.zalando.play.controllers.ArrayWrapper
import org.joda.time.DateTime

object SecurityExtractorsExecutionContext {
    // this ExecutionContext might be overridden if default configuration is not suitable for some reason
    implicit val ec = de.zalando.play.controllers.Contexts.tokenChecking
}

trait SecurityExtractors {
    def api_key_Extractor[User >: Any](): RequestHeader => Future[Option[User]] =
        header => headerApiKey("api_key")(header) { (apiKey: String) =>
            ???
    }
    def petstore_auth_Extractor[User >: Any](scopes: String*): RequestHeader => Future[Option[User]] =
        header => oAuth(scopes)("http://petstore.swagger.wordnik.com/oauth/dialog")(header) { (token: play.api.libs.json.JsValue) =>
            ???
    }
    implicit val unauthorizedContentWriter = ???
    def unauthorizedContent(req: RequestHeader) = Results.Unauthorized(???)
}
