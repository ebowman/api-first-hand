package de.zalando.swagger

import java.io.File
import java.net.URI

import de.zalando.swagger.strictModel.SwaggerModel
import org.scalatest.{ FunSpec, MustMatchers }

class StrictParseExamplesTest extends FunSpec with MustMatchers with ExpectedResults {

  val fixtures = new File(resourcesPath + "examples").listFiles ++
    new File(resourcesPath + "schema_examples").listFiles

  describe("Strict Swagger Parser") {
    fixtures.filter(_.getName.endsWith(".yaml")).foreach { file =>
      it(s"should parse the yaml swagger file ${file.getName} as specification") {
        val result = StrictYamlParser.parse(file)
        result._1 mustBe a[URI]
        result._2 mustBe a[SwaggerModel]
      }
    }
  }
}
