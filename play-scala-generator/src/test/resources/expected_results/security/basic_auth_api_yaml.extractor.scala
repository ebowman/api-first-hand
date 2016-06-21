
package basic.auth.api.yaml

import scala.concurrent.Future
import play.api.mvc._
import de.zalando.play.controllers.SwaggerSecurityExtractors._

object SecurityExtractorsExecutionContext {
    // this ExecutionContext might be overridden if default configuration is not suitable for some reason
    implicit val ec = de.zalando.play.controllers.Contexts.tokenChecking
}

trait SecurityExtractors {
    def basicAuth_Extractor[User >: Any](): RequestHeader => Future[Option[User]] =
        header => basicAuth(header) { (username: String, password: String) =>
            ???
    }
    implicit val unauthorizedContentWriter = ???
    def unauthorizedContent(req: RequestHeader) = Results.Unauthorized(???)
}
