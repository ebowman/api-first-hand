package security.api.yaml

import scala.concurrent.Future
import play.api.mvc._
import de.zalando.play.controllers.{FutureAuthenticatedBuilder,PlayBodyParsing}
import de.zalando.play.controllers.ArrayWrapper


trait SecurityApiYamlSecurity extends SecurityExtractors {
    import SecurityExtractorsExecutionContext.ec
    
    class getPetsByIdSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(githubAccessCode_Extractor("user"), internalApiKey_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
}

