package de.zalando.apifirst.generators

import de.zalando.ExpectedResults
import de.zalando.model.WithModel
import org.scalatest.{ FunSpec, MustMatchers }

class ScalaFormParserGeneratorIntegrationTest extends FunSpec with MustMatchers with ExpectedResults {

  override val expectationsFolder = super.expectationsFolder + "form_parsers/"

  describe("ScalaGenerator should generate a form parser") {
    examples.foreach { file =>
      testScalaFormParserGenerator(file)
    }
  }

  def testScalaFormParserGenerator(ast: WithModel): Unit = {
    val name = nameFromModel(ast)
    it(s"from model $name") {
      val model = ast.model
      val scalaModel = new ScalaGenerator(model).playScalaFormParsers(name, ast.model.packageName.getOrElse(name))
      val expected = asInFile(name, "scala")
      if (expected.isEmpty)
        dump(scalaModel, name, "scala")
      clean(scalaModel) mustBe clean(expected)
    }
  }

}
