package de.zalando.swagger

import java.io.File

import de.zalando.apifirst.util.ScalaPrinter
import de.zalando.apifirst.{ ScalaName, TypeNormaliser }
import org.scalatest.{ FunSpec, Ignore, MustMatchers }

/**
 * @since 18.11.2015.
 */
@Ignore
class ModelDumper extends FunSpec with MustMatchers with ExpectedResults {

  override def expectationsFolder: String = "/../../play-scala-generator/src/test/scala/model/"

  val modelFixtures = new File(resourcesPath + "model").listFiles

  val exampleFixtures = new File(resourcesPath + "examples").listFiles

  val validationFixtures = new File(resourcesPath + "validations").listFiles

  def toTest: File => Boolean = f => f.getName.endsWith(".yaml") // && f.getName.startsWith("basic_poly")

  describe("ScalaModelDumper should generate scala files") {
    (modelFixtures ++ exampleFixtures ++ validationFixtures).filter(toTest).foreach { file =>
      dumpFile(file)
    }
  }

  def dumpFile(file: File): Unit = {
    it(s"should parse the yaml swagger file ${file.getName} as specification") {
      val (base, model) = StrictYamlParser.parse(file)
      val packageName = ScalaName.scalaPackageName(file.getName)
      val ast = ModelConverter.fromModel(base, model, Option(file))
      val flatAst = TypeNormaliser.flatten(ast)

      val root = file.getParentFile.getParentFile
      dump(ScalaPrinter.asScala(file.getName, flatAst), root, file.getName.replace('.', '_') + ".scala")

    }
  }

}
