package de.zalando.play.controllers

import play.api.Play
import scala.concurrent.ExecutionContext

/**
 * @since 12.05.2016.
 */
object Contexts {
  implicit val tokenChecking: ExecutionContext = Play.maybeApplication map { app =>
    app.actorSystem.dispatchers.lookup("akka.actor.play-api-first-oauth-token-checker")
  } getOrElse { play.api.libs.concurrent.Execution.Implicits.defaultContext }
}
