package de.zalando.swagger

import java.io.{ File, FileOutputStream }

import scala.io.Source

/**
 * @since 19.08.2015
 */
trait ExpectedResults {

  val resourcesPath = "swagger-parser/src/test/resources/"

  def expectationsFolder: String = "/expected_results/"

  def dump(result: String, file: File, suffix: String): Unit = {
    if (result.nonEmpty) {
      val newFile = target(file, suffix)
      newFile.getParentFile.mkdirs()
      newFile.delete()
      newFile.createNewFile()
      val out = new FileOutputStream(newFile)
      out.write(result.getBytes)
      out.close()
    }
  }

  def asInFile(file: File, suffix: String): String = {
    val expectedFile = target(file, suffix)
    if (expectedFile.canRead) {
      val src = Source.fromFile(expectedFile)
      val result = src.getLines().mkString("\n")
      src.close()
      result
    } else
      ""
  }

  def target(file: File, suffix: String): File =
    new File(file.getParentFile.getParent + expectationsFolder + file.getName + "." + suffix)

  def clean(str: String): String = str.split("\n").map(_.trim).mkString("\n")
}
