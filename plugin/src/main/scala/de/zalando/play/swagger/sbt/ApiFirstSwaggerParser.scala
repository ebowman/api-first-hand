package de.zalando.play.swagger.sbt

import java.io.File

import de.zalando.BuildInfo
import de.zalando.apifirst.Application.StrictModel
import de.zalando.swagger.SwaggerParser
import de.zalando.swagger.strictModel.SwaggerModel
import sbt.Keys._
import sbt.{ Defaults, _ }

/**
 * @since 24.05.2016.
 */
//noinspection ScalaStyle
object ApiFirstSwaggerParser extends AutoPlugin {

  object autoImport {
    lazy val swaggerKeyPrefix = settingKey[String]("The key prefix is a name for swagger vendor extension")
    lazy val swaggerDefinitions = taskKey[Seq[File]]("The swagger definition files")
  }
  private lazy val swaggerParseSpec = taskKey[Seq[(java.net.URI, SwaggerModel)]]("Parse API specifications (swaggerDefinitions)")

  lazy val swaggerSpec2Ast = taskKey[Seq[(File, StrictModel)]]("Convert API specifications (swaggerDefinitions) to AST")

  // Users have to explicitly enable it
  override def trigger: PluginTrigger = noTrigger

  import autoImport._

  override def projectSettings = Seq(
    libraryDependencies ++= Seq(
      "de.zalando" %% "play-swagger-api" % BuildInfo.version,
      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.4.4",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.6.1",
      "org.scalacheck" %% "scalacheck" % "1.12.4" % Test,
      "com.typesafe.play" %% "play-test" % play.core.PlayVersion.current % Test
    )
  ) ++ inConfig(Compile)(swaggerParserSettings)

  /**
   * We define these unscoped, and then scope later using inConfig, this means we could define different definitions
   * to be compiled in compile and test, for example.
   */
  private def swaggerParserSettings: Seq[Setting[_]] = Seq(

    sourcePositionMappers := Seq(),

    swaggerKeyPrefix := "x-api-first",

    swaggerDefinitions := (resourceDirectory.value * "*.yaml").get,

    watchSources in Defaults.ConfigGlobal <++= sources in swaggerDefinitions,

    swaggerParseSpec <<= swaggerDefinitions map { t => t.map(SwaggerParser.readSwaggerModel) },

    swaggerSpec2Ast in Defaults.ConfigGlobal <<= (swaggerDefinitions, swaggerParseSpec) map { (t, s) =>
      s.zip(t) map { case ((uri, model), file) => file -> SwaggerParser.convertModelToAST(file, uri, model) }
    }
  )
}
