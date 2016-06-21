package de.zalando.play.controllers

import org.joda.time.{ DateTime, DateTimeZone }
import org.scalatest.{ FunSpec, MustMatchers }

/**
 * @author slasch
 * @since 04.01.2016.
 */
class Rfc3339UtilTest extends FunSpec with MustMatchers {

  val dtz = DateTimeZone.UTC
  val date = new DateTime(1451911387284L, dtz)

  describe("Rfc3339UtilTest") {

    it("should parse RFC3339 DateTime") {
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26-00:00").withZone(dtz).toString mustBe "2007-05-01T15:43:26.000Z"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26.3-00:00").withZone(dtz).toString mustBe "2007-05-01T15:43:26.300Z"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26.3452-01:00").withZone(dtz).toString mustBe "2007-05-01T16:43:26.345Z"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26.3452+01:00").withZone(dtz).toString mustBe "2007-05-01T14:43:26.345Z"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26.3452+00:00").withZone(dtz).toString mustBe "2007-05-01T15:43:26.345Z"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26.3-00:00").withZone(dtz).toString mustBe "2007-05-01T15:43:26.300Z"
      Rfc3339Util.parseDateTime("2007-05-01T15:43:26+00:00").withZone(dtz).toString mustBe "2007-05-01T15:43:26.000Z"
    }
    it("should parse RFC3339 Date") {
      Rfc3339Util.parseDate("2007-05-01").toString mustBe "2007-05-01"
      Rfc3339Util.parseDate("2008-05-01").toString mustBe "2008-05-01"
      Rfc3339Util.parseDate("2007-08-01").toString mustBe "2007-08-01"
      Rfc3339Util.parseDate("2007-05-08").toString mustBe "2007-05-08"
    }
    it("should write DateTime") {
      Rfc3339Util.writeDateTime(date.withZone(dtz)) mustBe "2016-01-04T12:43:07.284000+0000"
    }
    it("should write Date") {
      Rfc3339Util.writeDate(date.toLocalDate) mustBe "2016-01-04"
    }
  }
}
