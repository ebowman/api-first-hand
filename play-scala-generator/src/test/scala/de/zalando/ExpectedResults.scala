package de.zalando

import java.io.{ File, FileOutputStream }

import de.zalando.apifirst.util.ScalaPrinter
import de.zalando.model._

import scala.io.Source

/**
 * @since 19.08.2015
 */
trait ExpectedResults {

  val model = Seq[WithModel](
    additional_properties_yaml,
    basic_polymorphism_yaml,
    nested_arrays_yaml,
    nested_options_yaml,
    basic_extension_yaml,
    expanded_polymorphism_yaml,
    nested_objects_yaml,
    options_yaml
  )
  val examples = Seq[WithModel](
    basic_auth_api_yaml,
    cross_spec_references_yaml,
    echo_api_yaml,
    error_in_array_yaml,
    form_data_yaml,
    full_petstore_api_yaml,
    hackweek_yaml,
    heroku_petstore_api_yaml,
    instagram_api_yaml,
    minimal_api_yaml,
    nakadi_yaml,
    security_api_yaml,
    simple_petstore_api_yaml,
    split_petstore_api_yaml,
    string_formats_yaml,
    type_deduplication_yaml,
    uber_api_yaml
  )
  val validations = Seq[WithModel](
    nested_arrays_validation_yaml,
    nested_objects_validation_yaml,
    nested_options_validation_yaml,
    numbers_validation_yaml,
    string_formats_validation_yaml
  )

  val resourcesPath = "play-scala-generator/src/test/resources/"

  def expectationsFolder: String = "/expected_results/"

  def dump(result: String, name: String, suffix: String): Unit = {
    if (result.nonEmpty) {
      val newFile = target(name, suffix)
      newFile.getParentFile.mkdirs()
      newFile.delete()
      newFile.createNewFile()
      val out = new FileOutputStream(newFile)
      out.write(result.getBytes)
      out.close()
    }
  }

  def asInFile(name: String, suffix: String): String = {
    val expectedFile = target(name, suffix)
    if (expectedFile.canRead) {
      val src = Source.fromFile(expectedFile)
      val result = src.getLines().mkString("\n")
      src.close()
      result
    } else
      ""
  }

  def target(name: String, suffix: String): File =
    new File(resourcesPath + expectationsFolder + name + "." + suffix)

  def clean(str: String): String = str.split("\n").map(_.trim).filter(_.nonEmpty).mkString("\n")

  def nameFromModel(ast: WithModel): String = ScalaPrinter.nameFromModel(ast)

}
