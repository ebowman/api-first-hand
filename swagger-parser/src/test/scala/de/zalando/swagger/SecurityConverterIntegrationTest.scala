package de.zalando.swagger

import java.io.File

import de.zalando.swagger.strictModel.SwaggerModel
import org.scalatest.{ FunSpec, MustMatchers }

/**
 * @since 05.03.2016
 */
class SecurityConverterIntegrationTest extends FunSpec with MustMatchers with ExpectedResults {

  override val expectationsFolder = super.expectationsFolder + "security_definitions/"

  val fixtures = new File(resourcesPath + "examples").listFiles

  describe("Swagger Security Converter") {
    fixtures.filter(_.getName.endsWith(".yaml")).foreach { file =>
      testSecurityConverter(file)
    }
  }

  def testSecurityConverter(file: File): Unit = {
    it(s"should convert security definitions from ${file.getName}") {
      val (base, model) = StrictYamlParser.parse(file)
      model mustBe a[SwaggerModel]
      val securityDefs = SecurityConverter.convertDefinitions(model.securityDefinitions)
      val fullResult = securityDefs.mkString("\n")
      val expected = asInFile(file, "types")
      if (expected.isEmpty)
        dump(fullResult, file, "types")
      clean(fullResult) mustBe clean(expected)
    }
  }
}
