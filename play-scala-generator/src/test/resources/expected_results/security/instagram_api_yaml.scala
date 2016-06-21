package instagram.api.yaml

import scala.concurrent.Future
import play.api.mvc._
import de.zalando.play.controllers.{FutureAuthenticatedBuilder,PlayBodyParsing}
import scala.math.BigInt
import scala.math.BigDecimal


trait InstagramApiYamlSecurity extends SecurityExtractors {
    import SecurityExtractorsExecutionContext.ec
    
    class getmediaByMedia_idLikesSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class postmediaByMedia_idLikesSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("comments"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class deletemediaByMedia_idLikesSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getusersByUser_idFollowsSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getlocationsByLocation_idSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getusersSearchSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getusersSelfMediaLikedSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class gettagsByTag_nameSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class gettagsSearchSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getusersByUser_idFollowed_bySecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getmediaByMedia_idCommentsSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class postmediaByMedia_idCommentsSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("comments"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class deletemediaByMedia_idCommentsSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class gettagsByTag_nameMediaRecentSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class postusersByUser_idRelationshipSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("relationships"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getusersSelfFeedSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getusersByUser_idSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(key_Extractor(), oauth_Extractor("basic"))
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getmediaSearchSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getgeographiesByGeo_idMediaRecentSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getmediaByShortcodeSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getlocationsSearchSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getusersSelfRequested_bySecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getmediaByMedia_idSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getlocationsByLocation_idMediaRecentSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getusersByUser_idMediaRecentSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
    class getmediaPopularSecureAction(scopes: String*)
 extends FutureAuthenticatedBuilder(
        req => {
            val secureChecks: Seq[RequestHeader => Future[Option[_]]] = Seq(oauth_Extractor("basic", "comments", "relationships", "likes"), key_Extractor())
            val individualChecks: Future[Seq[Option[_]]] = Future.sequence(secureChecks.map(_.apply(req)))
                individualChecks.map { checks =>
                    checks.find(_.isEmpty).getOrElse(Option(checks.map(_.get)))
                }
        }, unauthorizedContent)
    
}

