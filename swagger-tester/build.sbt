import de.zalando.play.apifirst.sbt.ApiFirstCore
import de.zalando.play.generator.sbt.ApiFirstPlayScalaCodeGenerator
import de.zalando.play.swagger.sbt.ApiFirstSwaggerParser
import play.sbt.PlayScala

lazy val root = (project in file("."))
    .enablePlugins(PlayScala, ApiFirstCore, ApiFirstPlayScalaCodeGenerator, ApiFirstSwaggerParser)
    .dependsOn(swaggerApi, swaggerPlugin)

// The swagger plugin automatically adds a dependency on this, but we make it an
// explicit project ref, so that when we change it, we get the updates without
// having to publish it again
lazy val swaggerApi = ProjectRef(Path.fileProperty("user.dir").getParentFile, "api")

lazy val swaggerPlugin = ProjectRef(Path.fileProperty("user.dir").getParentFile, "plugin")

scalaVersion := "2.11.8"

apiFirstParsers := Seq(ApiFirstSwaggerParser.swaggerSpec2Ast.value).flatten


libraryDependencies += specs2
