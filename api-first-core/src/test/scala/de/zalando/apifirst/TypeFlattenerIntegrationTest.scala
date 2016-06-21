package de.zalando.apifirst

import java.io.File

import de.zalando.apifirst.util.ScalaPrinter
import org.scalatest.{ FunSpec, MustMatchers }

import scala.io.Source

class TypeFlattenerIntegrationTest extends FunSpec with MustMatchers {

  val expectation_path = "play-scala-generator/src/test/scala/model/"
  val prefix = "resources."

  import de.zalando.model._
  val plainModels = Seq[WithModel](
    additional_properties_yaml,
    basic_auth_api_yaml,
    basic_extension_yaml,
    basic_polymorphism_yaml,
    cross_spec_references_yaml,
    echo_api_yaml,
    error_in_array_yaml,
    expanded_polymorphism_yaml,
    form_data_yaml,
    full_petstore_api_yaml,
    hackweek_yaml,
    heroku_petstore_api_yaml,
    instagram_api_yaml,
    minimal_api_yaml,
    nakadi_yaml,
    nested_arrays_yaml,
    nested_arrays_validation_yaml,
    nested_objects_yaml,
    nested_objects_validation_yaml,
    nested_options_yaml,
    nested_options_validation_yaml,
    numbers_validation_yaml,
    options_yaml,
    security_api_yaml,
    simple_petstore_api_yaml,
    split_petstore_api_yaml,
    string_formats_yaml,
    string_formats_validation_yaml,
    type_deduplication_yaml,
    uber_api_yaml
  )

  describe("TypeFlattener") {
    plainModels.foreach { model =>
      testTypeFlattener(model)
    }
  }

  def testTypeFlattener(ast: WithModel): Unit = {
    val name = ScalaPrinter.nameFromModel(ast)
    it(s"should flatten API model $name") {
      val scalaModel = TypeNormaliser.flatten(ast.model)
      val expected = asInFile(name, ".scala")
      clean(ScalaPrinter.asScala(name, scalaModel)) mustBe clean(expected)
    }
  }

  def asInFile(name: String, suffix: String): String = {
    val expectedFile = new File(expectation_path, prefix + name + suffix)
    if (expectedFile.canRead) {
      val src = Source.fromFile(expectedFile)
      val result = src.getLines().mkString("\n")
      src.close()
      result
    } else
      ""
  }

  def clean(str: String): String =
    str.split("\n").map(_.trim).filter(_.nonEmpty).mkString("\n")

}
