package de.zalando.play.apifirst.sbt

import java.io.File

import de.zalando.BuildInfo
import de.zalando.apifirst.Application.StrictModel
import de.zalando.apifirst.AstNormaliser
import sbt.Keys._
import sbt.{ Types, _ }

/**
 * @since 28.07.2015
 */
//noinspection ScalaStyle
object ApiFirstCore extends AutoPlugin {

  // Anything in this object will automatically be available in all *.sbt files
  object autoImport {

    type StrictModelWithFile = (File, StrictModel)

    lazy val apiFirstPreparedData = taskKey[Seq[StrictModelWithFile]]("Pairs compilation tasks with models to prepare them for code generation")

    lazy val apiFirstParsers = taskKey[Seq[StrictModelWithFile]]("Specifications converted to ASTs")

    lazy val apiFirstFlattenAst = taskKey[Seq[StrictModelWithFile]]("Prepares AST by removing duplicate types and flattening it")

    lazy val apiFirstRawData = taskKey[Seq[StrictModelWithFile]]("Pairs compilation tasks with raw model to be used for debugging purposes")

    lazy val apiFirstPrintRawAstTypes = taskKey[Unit]("Prints AST type information before type optimisation")
    lazy val apiFirstPrintRawAstParameters = taskKey[Unit]("Prints AST parameter information before type optimisation")

    lazy val apiFirstPrintFlatAstTypes = taskKey[Unit]("Prints AST type information before after optimisation")
    lazy val apiFirstPrintFlatAstParameters = taskKey[Unit]("Prints AST parameter information after type optimisation")

    lazy val apiFirstStateDiagram = taskKey[Unit]("Prints hypermedia state transitions")
    lazy val apiFirstPrintDenotations = taskKey[Unit]("Prints AST denotation.")

  }

  // Users have to explicitly enable it
  override def trigger = noTrigger

  import autoImport._

  override def projectSettings = Seq(
    libraryDependencies ++= Seq(
      "de.zalando" %% "play-swagger-api" % BuildInfo.version,
      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.4.4",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.6.1",
      "org.scalacheck" %% "scalacheck" % "1.12.4" % Test,
      "com.typesafe.play" %% "play-test" % play.core.PlayVersion.current % Test
    )
  ) ++ apiFirstCoreSettings

  def apiFirstCoreSettings: Seq[Setting[_]] = Seq(

    sourcePositionMappers := Seq(),

    apiFirstRawData := apiFirstParsers.value,

    apiFirstPreparedData := apiFirstRawData.value map { case (file, model) => (file, AstNormaliser.flattenAST(model)) },

    apiFirstPrintRawAstTypes <<= (apiFirstRawData, streams) map prettyPrint(ApiFirstPrettyPrinter.types),
    apiFirstPrintRawAstParameters <<= (apiFirstRawData, streams) map prettyPrint(ApiFirstPrettyPrinter.parameters),

    apiFirstPrintFlatAstTypes <<= (apiFirstPreparedData, streams) map prettyPrint(ApiFirstPrettyPrinter.types),
    apiFirstPrintFlatAstParameters <<= (apiFirstPreparedData, streams) map prettyPrint(ApiFirstPrettyPrinter.parameters),

    apiFirstPrintDenotations <<= (apiFirstPreparedData, streams) map prettyPrint(ApiFirstPrettyPrinter.denotations),

    apiFirstStateDiagram <<= (apiFirstPreparedData, streams) map prettyPrint(ApiFirstPrettyPrinter.states)
  )

  def prettyPrint(printer: (File, StrictModel) => Seq[String]): (Types.Id[Seq[(File, StrictModel)]], Types.Id[TaskStreams]) => Unit = {
    case (r, s) => r map { case (a, b) => printer(a, b) } foreach {
      _ foreach { m => s.log.info(m) }
    }
  }

}
