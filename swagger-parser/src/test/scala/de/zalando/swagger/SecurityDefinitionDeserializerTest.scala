package de.zalando.swagger

import java.io.File

import de.zalando.swagger.strictModel._
import org.scalatest.{ MustMatchers, FunSpec }

/**
 * @author  slasch
 * @since   09.10.2015.
 */
class SecurityDefinitionDeserializerTest extends FunSpec with MustMatchers with ExpectedResults {

  val file = new File(resourcesPath + "examples/security.api.yaml")

  describe("SecurityDefinitionDeserializer") {
    it(s"should parse security definitions in the ${file.getName}") {
      val (uri, model) = StrictYamlParser.parse(file)
      val result = model.securityDefinitions
      result.size mustBe 6
      result("petstoreImplicit") mustBe a[Oauth2ImplicitSecurity]
      result("githubAccessCode") mustBe a[Oauth2AccessCodeSecurity]
      result("petstorePassword") mustBe a[Oauth2PasswordSecurity]
      result("justBasicStuff") mustBe a[BasicAuthenticationSecurity]
      result("petstoreApplication") mustBe a[Oauth2ApplicationSecurity]
      result("internalApiKey") mustBe a[ApiKeySecurity]
    }
  }
}
