
package instagram.api.yaml

import scala.concurrent.Future
import play.api.mvc._
import de.zalando.play.controllers.SwaggerSecurityExtractors._
import scala.math.BigInt
import scala.math.BigDecimal

object SecurityExtractorsExecutionContext {
    // this ExecutionContext might be overridden if default configuration is not suitable for some reason
    implicit val ec = de.zalando.play.controllers.Contexts.tokenChecking
}

trait SecurityExtractors {
    def oauth_Extractor[User >: Any](scopes: String*): RequestHeader => Future[Option[User]] =
        header => oAuth(scopes)("https://instagram.com/oauth/authorize/?client_id=CLIENT-ID&redirect_uri=REDIRECT-URI&response_type=token")(header) { (token: play.api.libs.json.JsValue) =>
            ???
    }
    def key_Extractor[User >: Any](): RequestHeader => Future[Option[User]] =
        header => queryApiKey("access_token")(header) { (apiKey: String) =>
            ???
    }
    implicit val unauthorizedContentWriter = ???
    def unauthorizedContent(req: RequestHeader) = Results.Unauthorized(???)
}
