package de.zalando.apifirst.generators

import de.zalando.ExpectedResults
import de.zalando.model.WithModel
import org.scalatest.{ FunSpec, MustMatchers }

class ScalaControllerGeneratorIntegrationTest extends FunSpec with MustMatchers with ExpectedResults {

  override val expectationsFolder = super.expectationsFolder + "controllers/"

  describe("ScalaSecurityGenerator should generate controlers") {
    (model ++ examples).foreach { ast =>
      testScalaControllerGenerator(ast)
    }
  }

  def testScalaControllerGenerator(ast: WithModel): Unit = {
    val name = nameFromModel(ast)
    it(s"from model $name") {
      val model = ast.model
      val expected = asInFile(name, "scala")
      val scalaModel = new ScalaGenerator(model).playScalaControllers(name, ast.model.packageName.getOrElse(name), expected)
      if (expected.isEmpty)
        dump(scalaModel, name, "scala")
      clean(scalaModel) mustBe clean(expected)
    }
  }

}
