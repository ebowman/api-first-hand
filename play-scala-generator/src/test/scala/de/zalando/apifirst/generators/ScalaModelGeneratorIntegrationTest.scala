package de.zalando.apifirst.generators

import de.zalando.ExpectedResults
import de.zalando.model.WithModel
import org.scalatest.{ FunSpec, MustMatchers }

class ScalaModelGeneratorIntegrationTest extends FunSpec with MustMatchers with ExpectedResults {

  override val expectationsFolder = super.expectationsFolder + "model/"

  describe("ScalaGenerator should generate scala model") {
    (model ++ examples ++ validations).foreach { ast =>
      testScalaModelGenerator(ast)
    }
  }

  def testScalaModelGenerator(ast: WithModel): Unit = {
    val name = nameFromModel(ast)
    it(s"from model $name") {
      val model = ast.model
      val scalaModel = new ScalaGenerator(model).generateModel(name, ast.model.packageName.getOrElse(name))
      val expected = asInFile(name, "scala")
      if (expected.isEmpty)
        dump(scalaModel, name, "scala")
      clean(scalaModel) mustBe clean(expected)
    }
  }

}
