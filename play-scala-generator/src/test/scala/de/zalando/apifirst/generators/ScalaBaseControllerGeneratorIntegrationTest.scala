package de.zalando.apifirst.generators

import de.zalando.ExpectedResults
import de.zalando.model._
import org.scalatest.{ FunSpec, MustMatchers }

class ScalaBaseControllerGeneratorIntegrationTest extends FunSpec with MustMatchers with ExpectedResults {

  override val expectationsFolder = super.expectationsFolder + "base_controllers/"

  describe("ScalaBaseControllerGenerator should generate controller bases") {
    (model ++ examples).foreach { ast =>
      testScalaBaseControllerGenerator(ast)
    }
  }

  def testScalaBaseControllerGenerator(ast: WithModel): Unit = {
    val name = nameFromModel(ast)
    it(s"from model $name") {
      val model = ast.model
      val scalaModel = new ScalaGenerator(model).playScalaControllerBases(name, ast.model.packageName.getOrElse(name))
      val expected = asInFile(name, "base.scala")
      if (expected.isEmpty)
        dump(scalaModel, name, "base.scala")
      clean(scalaModel) mustBe clean(expected)
    }
  }

}
