import org.scalatestplus.play._
import play.api.mvc.{AnyContentAsEmpty, _}
import play.api.test.{FakeHeaders, _}
import play.api.test.Helpers._
import play.api.libs.json._

class I019_datetime_constructor_Spec extends PlaySpec with OneAppPerSuite {

  val exptectedContentType = "application/json"

  val headers = FakeHeaders(Seq("Accept" -> exptectedContentType))

  "I09" should {

    "be able to construct datetime objects from string" in {
      val stringRequest =
        """{
          |  "complete_before": "2016-07-25T13:15:53.208Z",
          |  "complete_after": "2016-07-25T13:15:53.208Z"
          |}""".stripMargin

      val fromString = route(app, FakeRequest(POST, "/019", headers, Json.parse(stringRequest))).get
      status(fromString) mustBe OK
      contentType(fromString) mustBe Some(exptectedContentType)
      contentAsString(fromString) must include("\"complete_before\":")
      contentAsString(fromString) must include("\"complete_after\":")
    }

    "be able to construct datetime objects from long" in {
      val longRequest =
        """{
          |  "complete_before": 1469452060,
          |  "complete_after": 1469452060
          |}""".stripMargin

      val fromLong = route(app, FakeRequest(POST, "/019", headers, Json.parse(longRequest))).get
      status(fromLong) mustBe OK
      contentType(fromLong) mustBe Some(exptectedContentType)
      contentAsString(fromLong) must include("\"complete_before\":")
      contentAsString(fromLong) must include("\"complete_after\":")
    }

    "be able to construct datetime objects from string in header and path" in {
      val request = "2016-07-25T13:15:53.208Z"
      val withHeader = FakeHeaders(("header", request) +: headers.data)
      val fromString = route(app, FakeRequest(GET, s"/019/$request", withHeader, AnyContentAsEmpty)).get
      contentAsString(fromString) must include("\"complete_before\":")
      contentAsString(fromString) must include("\"complete_after\":")
      status(fromString) mustBe OK
      contentType(fromString) mustBe Some(exptectedContentType)
    }

    "NOT be able to construct datetime objects from long in header and path" in {
      val request = 1469452060
      val withHeader = FakeHeaders(("header", request.toString) +: headers.data)
      val fromLong = route(app, FakeRequest(GET, s"/019/$request", withHeader, AnyContentAsEmpty)).get
      status(fromLong) mustBe BAD_REQUEST
    }
  }
}