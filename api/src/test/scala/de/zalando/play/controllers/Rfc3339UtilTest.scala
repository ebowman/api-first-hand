package de.zalando.play.controllers

import java.time.{ LocalDateTime, ZoneId, ZoneOffset, ZonedDateTime }

import org.scalatest.{ FunSpec, MustMatchers }

/**
 * @author slasch
 * @since 04.01.2016.
 */
class Rfc3339UtilTest extends FunSpec with MustMatchers {

  val dtz = ZoneId.of("UTC")
  val offset = ZoneOffset.UTC
  //noinspection ScalaStyle
  val date = ZonedDateTime.of(LocalDateTime.ofEpochSecond(1451911387L, 0, offset), dtz)

  describe("Rfc3339UtilTest") {

    it("should parse RFC3339 DateTime") {
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26-00:00").withZoneSameInstant(dtz).toString mustBe "2007-05-01T15:43:26Z[UTC]"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26+00:00").withZoneSameInstant(dtz).toString mustBe "2007-05-01T15:43:26Z[UTC]"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26.3452-01:00").withZoneSameInstant(dtz).toString mustBe "2007-05-01T16:43:26.345200Z[UTC]"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26.3452+01:00").withZoneSameInstant(dtz).toString mustBe "2007-05-01T14:43:26.345200Z[UTC]"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26.3452+00:00").withZoneSameInstant(dtz).toString mustBe "2007-05-01T15:43:26.345200Z[UTC]"
    }
    it("should parse RFC3339 Date") {
      Rfc3339Util.parseDate("2007-05-01").toString mustBe "2007-05-01"
      Rfc3339Util.parseDate("2008-05-01").toString mustBe "2008-05-01"
      Rfc3339Util.parseDate("2007-08-01").toString mustBe "2007-08-01"
      Rfc3339Util.parseDate("2007-05-08").toString mustBe "2007-05-08"
    }
    it("should write DateTime") {
      Rfc3339Util.writeDateTime(date) mustBe "2016-01-04T12:43:07.0000+0000"
    }
    it("should write Date") {
      Rfc3339Util.writeDate(date.toLocalDate) mustBe "2016-01-04"
    }
  }
}
