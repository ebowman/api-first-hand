package de.zalando.swagger

import java.io.File

import de.zalando.apifirst.util.ScalaPrinter
import de.zalando.apifirst.{ ScalaName, TypeNormaliser }
import org.scalatest.{ FunSpec, Ignore, MustMatchers }

/**
 * @since 18.11.2015.
 */
object ModelDumper extends App with ExpectedResults {

  val flatExpectations = "/../../play-scala-generator/src/test/scala/model/"
  val origExpectations = "/../../api-first-core/src/test/scala/model/"

  var flatten: Boolean = false

  override def expectationsFolder: String = if (flatten) flatExpectations else origExpectations

  val modelFixtures = new File(resourcesPath + "model").listFiles

  val exampleFixtures = new File(resourcesPath + "examples").listFiles

  val validationFixtures = new File(resourcesPath + "validations").listFiles

  def toTest: File => Boolean = f => f.getName.endsWith(".yaml") && f.getName.startsWith("i038")

  def run: Unit = {
    (modelFixtures ++ exampleFixtures ++ validationFixtures).filter(toTest).foreach { file =>
      dumpFile(file, flatten)
    }
  }

  def dumpFile(file: File, flatten: Boolean): Unit = {
    val (base, model) = StrictYamlParser.parse(file)
    val packageName = ScalaName.scalaPackageName(file.getName)
    val ast = ModelConverter.fromModel(base, model, Option(file))
    val flatAst = if (flatten) TypeNormaliser.flatten(ast) else ast
    val root = file.getParentFile.getParentFile
    dump(ScalaPrinter.asScala(file.getName, flatAst), root, file.getName.replace('.', '_') + ".scala")
  }

  //  run
  flatten = true
  run

}
