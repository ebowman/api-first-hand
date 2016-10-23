import org.scalatestplus.play._
import play.api.mvc._
import play.api.test.{FakeHeaders, _}
import play.api.test.Helpers._
import play.api.libs.json._

class I019_datetime_constructorSpec extends PlaySpec with OneAppPerSuite {

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

    }
  }
}