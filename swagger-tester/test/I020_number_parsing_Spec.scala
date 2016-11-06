import org.scalatestplus.play._
import play.api.mvc.{AnyContentAsEmpty, _}
import play.api.test.{FakeHeaders, _}
import play.api.test.Helpers._
import play.api.libs.json._

class I020_number_parsing_Spec extends PlaySpec with OneAppPerSuite {

  val exptectedContentType = "application/json"

  val headers = FakeHeaders(Seq("Accept" -> exptectedContentType))

  "I20" should {

    "be able to parse array of doubles" in {
      val request =
        """{
          |  "coordinates":
          |        [
          |          4.49965,
          |          52.06891
          |        ]
          |
          |}""".stripMargin

      val result = route(app, FakeRequest(POST, "/020", headers, Json.parse(request))).get
      status(result) mustBe OK
      contentType(result) mustBe Some(exptectedContentType)
      contentAsString(result) must include("""{"coordinates":[4.49965,52.06891]}""")
    }

    "NOT be able to parse nested array of doubles" in {
      val request =
        """{
          |  "coordinates": [
          |        [
          |          4.49965,
          |          52.06891
          |        ]
          | ]
          |}""".stripMargin

      val result = route(app, FakeRequest(POST, "/020", headers, Json.parse(request))).get
      status(result) mustBe BAD_REQUEST
    }

  }
}