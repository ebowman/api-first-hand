package split.petstore.api.yaml

import scala.concurrent.Future
import play.api.mvc._
import de.zalando.play.controllers.{FutureAuthenticatedBuilder,PlayBodyParsing}
import de.zalando.play.controllers.ArrayWrapper
import org.joda.time.DateTime


trait SplitPetstoreApiYamlSecurity extends SecurityExtractors {
    import SecurityExtractorsExecutionContext.ec
    
    class findPetsByTagsSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(petstore_auth_Extractor("write_pets", "read_pets"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class updatePetSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(petstore_auth_Extractor("write_pets", "read_pets"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class addPetSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(petstore_auth_Extractor("write_pets", "read_pets"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getPetByIdSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(api_key_Extractor(), petstore_auth_Extractor("write_pets", "read_pets"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class updatePetWithFormSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(petstore_auth_Extractor("write_pets", "read_pets"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class deletePetSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(petstore_auth_Extractor("write_pets", "read_pets"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class findPetsByStatusSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(petstore_auth_Extractor("write_pets", "read_pets"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
}

