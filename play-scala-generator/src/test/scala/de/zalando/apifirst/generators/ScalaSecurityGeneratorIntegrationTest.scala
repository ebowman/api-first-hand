package de.zalando.apifirst.generators

import de.zalando.ExpectedResults
import de.zalando.model.WithModel
import org.scalatest.{ FunSpec, MustMatchers }

class ScalaSecurityGeneratorIntegrationTest extends FunSpec with MustMatchers with ExpectedResults {

  override val expectationsFolder = super.expectationsFolder + "security/"

  describe("ScalaSecurityGenerator should generate security plumbing files") {
    examples.foreach { ast =>
      testScalaSecurityGenerator(ast)
    }
  }

  describe("ScalaSecurityGenerator should generate security helper files") {
    examples.foreach { ast =>
      testScalaSecurityExtractorsGenerator(ast)
    }
  }

  def testScalaSecurityGenerator(ast: WithModel): Unit = {
    val name = nameFromModel(ast)
    it(s"from model $name") {
      val model = ast.model
      val scalaModel = new ScalaGenerator(model).playScalaSecurity(name, ast.model.packageName.getOrElse(name))
      val expected = asInFile(name, "scala")
      if (expected.isEmpty)
        dump(scalaModel, name, "scala")
      clean(scalaModel) mustBe clean(expected)
    }
  }

  def testScalaSecurityExtractorsGenerator(ast: WithModel): Unit = {
    val name = nameFromModel(ast)
    it(s"from model $name") {
      val model = ast.model
      val scalaModel = new ScalaGenerator(model).playScalaSecurityExtractors(name, ast.model.packageName.getOrElse(name))
      val expected = asInFile(name, "extractor.scala")
      if (expected.isEmpty)
        dump(scalaModel, name, "extractor.scala")
      clean(scalaModel) mustBe clean(expected)
    }
  }

}
