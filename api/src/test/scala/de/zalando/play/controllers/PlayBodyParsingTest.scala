package de.zalando.play.controllers

import org.specs2.mutable.Specification
import play.api.http.MediaRange

/**
 * @since 26.02.2016.
 */
class PlayBodyParsingTest extends Specification {

  val acceptedTypes = MediaRange.parse("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
  "PlayBodyParsingTest" should {
    "negotiateContent as None" in {
      PlayBodyParsing.negotiateContent(Nil, Nil) must_== None
    }
    "negotiateContent as None" in {
      PlayBodyParsing.negotiateContent(MediaRange.parse("multipart/*"), Nil) must_== None
    }

    "negotiateContent as text/plain" in {
      val acceptedTypes = MediaRange.parse("text/plain; q=0.5, text/html, text/x-dvi; q=0.8, text/x-c")
      PlayBodyParsing.negotiateContent(acceptedTypes, Seq("image/web", "text/plain")) must_== Some("text/plain")
    }
    "negotiateContent as image/webp" in {
      PlayBodyParsing.negotiateContent(acceptedTypes, Seq("image/web", "text/plain")) must_== Some("image/web")
    }
    "negotiateContent as text/html" in {
      PlayBodyParsing.negotiateContent(acceptedTypes, Seq("image/web", "text/html")) must_== Some("text/html")
    }

    "negotiateContent as image/web" in {
      PlayBodyParsing.negotiateContent(MediaRange.parse("*/*"), Seq("image/web", "text/plain")) must_== Some("image/web")
    }
    "negotiateContent as text/plain" in {
      PlayBodyParsing.negotiateContent(MediaRange.parse("*/*"), Seq("text/plain", "image/web")) must_== Some("text/plain")
    }

  }
}
