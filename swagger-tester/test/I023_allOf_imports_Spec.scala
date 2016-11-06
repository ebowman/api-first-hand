import org.scalatestplus.play._
import play.api.mvc.{AnyContentAsEmpty, _}
import play.api.test.{FakeHeaders, _}
import play.api.test.Helpers._
import play.api.libs.json._

class I023_allOf_imports_Spec extends PlaySpec with OneAppPerSuite {

  val exptectedContentType = "application/json"

  val headers = FakeHeaders(Seq("Accept" -> exptectedContentType))

  "I23" should {

    "be able to validate an allOf type" in {
      val request = """{ "`type`": "DatetimeValue", "value": "2016-07-25T13:15:53.208Z" }"""

      val result = route(app, FakeRequest(POST, "/023", headers, Json.parse(request))).get
      status(result) mustBe OK
      contentType(result) mustBe Some(exptectedContentType)
      contentAsString(result) must include("""{"type":"DatetimeValue","value":""")
    }

    "NOT be able to validate an allOf type if required field is not present" in {
      val request = """{ "`type`": "DatetimeValue" }"""
      intercept[JsResultException] {
        val result = route(app, FakeRequest(POST, "/023", headers, Json.parse(request))).get
        status(result)
      }.getClass mustBe classOf[JsResultException]
    }

  }
}