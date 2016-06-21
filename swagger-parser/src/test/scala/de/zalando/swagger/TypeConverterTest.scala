package de.zalando.swagger

import java.io.File

import de.zalando.swagger.strictModel.SwaggerModel
import org.scalatest.{ FunSpec, MustMatchers }

class TypeConverterTest extends FunSpec with MustMatchers with ExpectedResults {

  override val expectationsFolder = super.expectationsFolder + "types/"

  val modelFixtures = new File(resourcesPath + "model").listFiles

  val exampleFixtures = new File(resourcesPath + "examples").listFiles

  describe("Strict Swagger Parser model") {
    modelFixtures.filter(_.getName.endsWith(".yaml")).foreach { file =>
      testTypeConverter(file)
    }
  }

  describe("Strict Swagger Parser examples") {
    exampleFixtures.filter(_.getName.endsWith(".yaml")).foreach { file =>
      testTypeConverter(file)
    }
  }

  def testTypeConverter(file: File): Unit = {
    it(s"should parse the yaml swagger file ${file.getName} as specification") {
      val (base, model) = StrictYamlParser.parse(file)
      model mustBe a[SwaggerModel]
      val typeDefs = ModelConverter.fromModel(base, model, Some(file)).typeDefs
      val typeMap = typeDefs map { case (k, v) => k -> ("\n\t" + de.zalando.apifirst.util.ShortString.toShortString("\t\t")(v)) }
      val typesStr = typeMap.toSeq.sortBy(_._1.parts.size).map(p => p._1 + " ->" + p._2).mkString("\n")
      val expected = asInFile(file, "types")
      if (expected.isEmpty) dump(typesStr, file, "types")
      clean(typesStr) mustBe clean(expected)
    }
  }

}
