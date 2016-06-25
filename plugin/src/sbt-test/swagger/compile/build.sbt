import de.zalando.play.apifirst.sbt.ApiFirstCore
import de.zalando.play.generator.sbt.ApiFirstPlayScalaCodeGenerator
import de.zalando.play.swagger.sbt._
import play.sbt.PlayScala

lazy val root = (project in file(".")).enablePlugins(PlayScala, ApiFirstCore, ApiFirstPlayScalaCodeGenerator, ApiFirstSwaggerParser)

scalaVersion := sys.props.get("scala.version").getOrElse("2.11.8")

libraryDependencies ++= Seq(
  specs2,
  "org.scalacheck" %% "scalacheck" % "1.12.4",
  "org.specs2" %% "specs2-scalacheck" % "3.6",
  "me.jeffmay" %% "play-json-tests" % "1.3.0" ,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1"
).map(_ % "test")

apiFirstParsers := Seq(ApiFirstSwaggerParser.swaggerSpec2Ast.value).flatten

logLevel := sbt.Level.Warn

crossPaths := false