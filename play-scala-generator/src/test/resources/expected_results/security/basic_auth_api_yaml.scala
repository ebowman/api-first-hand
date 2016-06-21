package basic.auth.api.yaml

import scala.concurrent.Future
import play.api.mvc._
import de.zalando.play.controllers.{FutureAuthenticatedBuilder,PlayBodyParsing}


trait BasicAuthApiYamlSecurity extends SecurityExtractors {
    import SecurityExtractorsExecutionContext.ec
    
    object getSecureAction
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(basicAuth_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
}

