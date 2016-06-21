package de.zalando.swagger

import java.io.File

import de.zalando.apifirst.Hypermedia.State
import de.zalando.swagger.strictModel.SwaggerModel
import org.scalatest.{ FunSpec, MustMatchers }

class HypermediaConverterTest extends FunSpec with MustMatchers with ExpectedResults {

  override val expectationsFolder = super.expectationsFolder + "hypermedia/"

  val exampleFixtures = new File(resourcesPath + "extensions").listFiles

  describe("Strict Swagger Parser hypermedia converter") {
    exampleFixtures.filter(_.getName.startsWith("hypermedia.ok")).foreach { file =>
      testTransitionsConverter(file)
      testStateDefinitions(file)
    }
  }

  def testTransitionsConverter(file: File): Unit = {
    it(s"should parse the yaml swagger file ${file.getName} with hypermedia information") {
      val (base, model) = StrictYamlParser.parse(file)
      model mustBe a[SwaggerModel]
      val ast = ModelConverter.fromModel(base, model, Some(file))
      val hypermedia = ast.stateTransitions
      val expected = asInFile(file, "hypermedia")
      val media = State.toDot(hypermedia).mkString("\n")
      if (expected.isEmpty && media.nonEmpty)
        dump(media, file, "hypermedia")
      clean(media) mustBe clean(expected)
    }
  }

  def testStateDefinitions(file: File): Unit = {
    it(s"should parse the yaml swagger file ${file.getName} with state name information") {
      val (base, model) = StrictYamlParser.parse(file)
      model mustBe a[SwaggerModel]
      val ast = ModelConverter.fromModel(base, model, Some(file))
      val targetStates = ast.calls.map(_.targetStates)
      val expected = asInFile(file, "states")
      val media = targetStates.mkString("\n")
      if (expected.isEmpty && media.nonEmpty)
        dump(media, file, "states")
      clean(media) mustBe clean(expected)
    }
  }
}
