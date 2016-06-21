import sbt.Keys._
import sbt._

class Dependencies(playVersion: String, projectVersion: String, scalaVersion: String = "2.11") {

  val jacksonsJava = Seq(
    "com.fasterxml.jackson.core"        % "jackson-core",
    "com.fasterxml.jackson.core"        % "jackson-annotations",
    "com.fasterxml.jackson.core"        % "jackson-databind",
    "com.fasterxml.jackson.datatype"    % "jackson-datatype-jdk8",
    "com.fasterxml.jackson.datatype"    % "jackson-datatype-jsr310",
    "com.fasterxml.jackson.dataformat"  % "jackson-dataformat-yaml",
    "com.fasterxml.jackson.dataformat"  % "jackson-dataformat-csv"
  ).map(_ % "2.7.4")

  val jacksonScala = "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.4"

  val jacksons = jacksonScala +: jacksonsJava

  val logback     = "ch.qos.logback"    % "logback-classic" % "1.0.13"
  val jsonRef     = "me.andrz.jackson"  % "jackson-json-reference-core" % "0.2.1"
  val jodaTime    = "joda-time"         % "joda-time"         % "2.9.1"
  val commonsIO   = "commons-io"        % "commons-io"        % "2.5"

  val beard       =  "de.zalando"       %% "beard"            % "0.0.6"

  val play        = "com.typesafe.play" %% "play"             % playVersion % Provided
  val playRoutes  = "com.typesafe.play" %% "routes-compiler"  % playVersion % Provided
  val playClient  = "com.typesafe.play" %% "play-java-ws"     % playVersion % Provided

  def scalaParserCombinators(scalaVersion: String): Seq[ModuleID] =
    CrossVersion.partialVersion(scalaVersion) match {
    case Some((2, major)) if major >= 11 =>
      Seq("org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4")
    case _ =>
      Nil
  }

  val testLibs = Seq(
    "org.scalacheck"  %% "scalacheck"         % "1.12.4",
    "org.scalatest"   %% "scalatest"          % "2.2.6",
    "org.specs2"      %% "specs2-scalacheck"  % "3.6",
    "me.jeffmay"      %% "play-json-tests"    % "1.3.0"
  ).map(_ % "test")

  val test = logback +: testLibs

  val api = Seq(jodaTime, play, playClient) ++ jacksons

  val playScalaGenerator = Seq(commonsIO, beard)

  val swaggerModel = jsonRef +: jacksons

  def swaggerParser(scalaVersion: String): Seq[ModuleID] = swaggerModel ++ scalaParserCombinators(scalaVersion)
}