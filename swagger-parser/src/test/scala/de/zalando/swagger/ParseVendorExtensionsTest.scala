package de.zalando.swagger

import java.io.File

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.parser.ParserException
import de.zalando.apifirst.Application.ApiCall
import de.zalando.apifirst.Http.{ GET, POST, PUT }
import org.scalatest.{ FunSpec, MustMatchers }

class ParseVendorExtensionsTest extends FunSpec with MustMatchers with ExpectedResults {

  val ok = new File(resourcesPath + "extensions/extensions.ok.yaml")
  val nok = new File(resourcesPath + "extensions/extensions.nok.yaml")
  val hypermediaOk = new File(resourcesPath + "extensions/hypermedia.ok.yaml")
  val hypermediaNOk1 = new File(resourcesPath + "extensions/hypermedia.nok1.yaml")
  val hypermediaNOk2 = new File(resourcesPath + "extensions/hypermedia.nok2.yaml")
  val errorMapping = new File(resourcesPath + "extensions/error_mapping.yaml")

  describe("The swagger parser") {
    it("should read valid vendor extensions") {
      implicit val (uri, swagger) = StrictYamlParser.parse(ok)
      swagger.info.vendorExtensions contains "x-info-extension" mustBe true
      swagger.paths("/").vendorExtensions contains "x-path-extension" mustBe true
      swagger.paths("/").get.vendorExtensions contains "x-operation-extension" mustBe true
      swagger.paths("/").get.responses("200").vendorExtensions contains "x-response-extension" mustBe true
      swagger.tags.head.vendorExtensions contains "x-tag-extension" mustBe true
      swagger.securityDefinitions("internalApiKey").vendorExtensions contains "x-security-extension" mustBe true
    }
    it("should reject invalid vendor extensions") {
      intercept[JsonParseException] {
        StrictYamlParser.parse(nok)
      }.getClass mustBe classOf[JsonParseException]
    }
    it("should read hypermedia definitions") {
      implicit val (uri, swagger) = StrictYamlParser.parse(hypermediaOk)
      val expected = Map(
        "resource created" ->
          Map("resource updated" -> Map("condition" -> "some rule to show the transition"), "subresource added" -> null),
        "resource updated" -> Map(
          "subresource added" -> Map("condition" -> ""),
          "self" -> Map("condition" -> "non-empty rule")
        ), "resource deleted" -> Map("self" -> null),
        "subresource added" -> Map("resource updated" -> null, "self" -> null, "resource deleted" -> null)
      )
      swagger.transitions.nonEmpty mustBe true
      swagger.transitions mustEqual expected
      swagger.paths("/").get.responses("200").targetState mustEqual Some("resource created")
      swagger.paths("/").get.responses("default").targetState mustEqual None
    }
    it("should reject hypermedia definitions without well-formed definition") {
      val exception = intercept[JsonParseException] {
        StrictYamlParser.parse(hypermediaNOk1)
      }
      exception.getMessage mustEqual "Malformed transition definitions"
    }
    it("should reject hypermedia definitions with incorrect initial state") {
      intercept[ParserException] {
        StrictYamlParser.parse(hypermediaNOk2)
      }.getClass mustBe classOf[ParserException]
    }

    it("should read error mappings and assign right preference to them") {
      val (uri, model) = StrictYamlParser.parse(errorMapping)
      val ast = ModelConverter.fromModel(errorMapping.toURI, model, Option(errorMapping))
      val expectedForPUT = Map(
        "404" -> List(classOf[java.util.NoSuchElementException]),
        "403" -> List(classOf[java.lang.SecurityException]),
        "405" -> List(classOf[java.lang.IllegalStateException]),
        "400" -> List(classOf[java.util.NoSuchElementException])
      )
      val expectedForPOST = Map(
        "403" -> List(classOf[java.lang.SecurityException]),
        "404" -> List(classOf[java.util.NoSuchElementException]),
        "405" -> List(classOf[java.lang.IllegalStateException])
      )
      ast.calls.foreach {
        case ApiCall(POST, _, _, _, _, mapping, _, _, _) =>
          mapping must contain theSameElementsAs expectedForPOST
        case ApiCall(PUT, _, _, _, _, mapping, _, _, _) =>
          mapping must contain theSameElementsAs expectedForPUT
      }
    }
  }
}
