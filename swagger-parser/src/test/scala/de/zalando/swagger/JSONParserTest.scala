package de.zalando.swagger

import java.io.File

import org.scalatest.{ FunSpec, MustMatchers }

class JSONParserTest extends FunSpec with MustMatchers with ExpectedResults {

  val json = new File(resourcesPath + "examples/uber.api.json")

  describe("The swagger parser") {
    it("should parse json specification in the same way as yaml specification") {
      val jsonModel = StrictJsonParser.parse(json)
      jsonModel._2.basePath mustBe "/v1"
    }
  }
}
