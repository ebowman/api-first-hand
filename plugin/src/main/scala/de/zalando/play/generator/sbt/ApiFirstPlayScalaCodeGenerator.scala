package de.zalando.play.generator.sbt

import java.io.File

import com.typesafe.sbt.web.incremental.{ OpSuccess, _ }
import de.zalando.BuildInfo
import de.zalando.apifirst.Application.StrictModel
import de.zalando.play.apifirst.sbt.ApiFirstCore
import de.zalando.play.apifirst.sbt.ApiFirstCore.autoImport._
import de.zalando.play.compiler.{ CompilationResult, PlayScalaCompilationTask, PlayScalaCompiler }
import de.zalando.play.controllers.WriterFactories
import de.zalando.play.generator.routes.PlayScalaRoutesCompiler
import play.routes.compiler.{ InjectedRoutesGenerator, RoutesGenerator }
import play.sbt.routes.RoutesCompiler
import sbt.Keys._
import sbt.{ Def, Defaults, Task, TaskKey, _ }

import scala.util.{ Failure, Success, Try }

/**
 * @since 24.05.2016.
 */
//noinspection ScalaStyle
object ApiFirstPlayScalaCodeGenerator extends AutoPlugin {

  object autoImport {
    lazy val playScalaCustomTemplateLocation = settingKey[Option[File]]("The location of custom templates (if needed)")
    lazy val playScalaTarget = settingKey[File]("Target folder to save generated files")
    lazy val playScalaAutogenerateControllers = settingKey[Boolean]("Auto - generate Play controllers")

    lazy val playScalaMarshallers = taskKey[Seq[File]]("Generate marshallers from api definitions")
    lazy val playScalaSecurity = taskKey[Seq[File]]("Generate security adapters from api definitions")

    lazy val playScalaApiFirstApp = taskKey[Seq[File]]("Generate full Play application from api definitions")
  }

  lazy val playScalaCompilationTasks = taskKey[Seq[(File, String, StrictModel)]]("Compilation tasks for specifications")

  private lazy val playScalaBase = taskKey[Seq[File]]("Generate model, validators and controller bases from api definitions")
  private lazy val playScalaRoutes = taskKey[Seq[File]]("Generate play routes from api definitions")
  private lazy val playScalaTests = taskKey[Seq[File]]("Generate test data generators and tests from api definitions")
  private lazy val playScalaControllers = taskKey[Seq[File]]("Generate controllers from api definitions")

  override def requires = RoutesCompiler && ApiFirstCore

  // Users have to explicitly enable it
  override def trigger = noTrigger

  import autoImport._

  override def projectSettings = Seq(
    libraryDependencies ++= Seq(
      "org.scalacheck" %% "scalacheck" % "1.12.4" % Test,
      "com.typesafe.play" %% "play-test" % play.core.PlayVersion.current % Test
    )
  ) ++ Seq(
      managedSourceDirectories in Compile += crossTarget.value / "routes" / Defaults.nameForSrc(Compile.name),
      managedSourceDirectories in Test += crossTarget.value / "routes" / Defaults.nameForSrc(Test.name),
      playScalaCustomTemplateLocation := None

    ) ++ inConfig(Compile)(playScalaGeneratorSettings) ++
      inConfig(Test)(playScalaGeneratorSettings) ++
      inConfig(Compile)(playScalaBaseSettings) ++
      inConfig(Compile)(playScalaMarshallerSettings) ++
      inConfig(Compile)(playScalaSecuritySettings) ++
      inConfig(Compile)(playScalaControllerSettings) ++
      inConfig(Compile)(playScalaRoutesSettings) ++
      inConfig(Test)(playScalaTestSettings)

  def playScalaGeneratorSettings: Seq[Setting[_]] = Seq(
    sourcePositionMappers := Seq(),

    playScalaAutogenerateControllers := true,

    playScalaCompilationTasks <<= apiFirstPreparedData map { task =>
      val version = if (BuildInfo.version.endsWith("-SNAPSHOT")) {
        // This essentially disables incremental compilation if we're using a SNAPSHOT version of the playScala plugin.
        // You may want to get rid of this eventually, but during development, it's going to make things a lot nicer,
        // since otherwise you'll need to do a clean every time you update the plugin if you want to regenerate the
        // sources
        BuildInfo.version + System.currentTimeMillis()
      } else {
        BuildInfo.version
      }
      task map { case (file, model) => (file, version, model) }
    },

    playScalaApiFirstApp := playScalaRoutes.value
  )

  def playScalaBaseSettings: Seq[Setting[_]] = Seq(
    target in playScalaBase := crossTarget.value / "routes" / Defaults.nameForSrc(Compile.name),
    playScalaBase := playScalaGenerateBase.value,
    managedSources ++= playScalaBase.value
  )

  def playScalaTestSettings: Seq[Setting[_]] = Seq(
    target in playScalaTests := crossTarget.value / "routes" / Defaults.nameForSrc(Test.name),
    playScalaTests := {
      val rout = playScalaRoutes.value
      playScalaGenerateTests.value
    },
    managedSources ++= playScalaTests.value
  )

  def playScalaControllerSettings: Seq[Setting[_]] = Seq(
    target in playScalaControllers := scalaSource.value,
    playScalaControllers := {
      val comp = playScalaBase.value
      playScalaGenerateControllers.value
    }
  // managedSources in Compile in playScalaControllers ++= playScalaControllers.value
  )

  def playScalaMarshallerSettings: Seq[Setting[_]] = Seq(
    target in playScalaMarshallers := scalaSource.value,
    playScalaMarshallers := {
      val comp = playScalaBase.value
      playScalaGenerateMarshallers.value
    }
  )

  def playScalaSecuritySettings: Seq[Setting[_]] = Seq(
    target in playScalaSecurity := scalaSource.value,
    playScalaSecurity := {
      val comp = playScalaBase.value
      playScalaGenerateSecurity.value
    }
  )

  def playScalaRoutesSettings: Seq[Setting[_]] = Seq(
    target in playScalaRoutes := crossTarget.value / "routes" / Defaults.nameForSrc(Compile.name),
    playScalaRoutes := {
      if (playScalaAutogenerateControllers.value) {
        val cont = playScalaControllers.value
      }
      playScalaGenerateRoutes(InjectedRoutesGenerator).value
    },
    managedSources ++= playScalaRoutes.value
  )

  val providedWriterFactories = WriterFactories.factories.keySet

  val playScalaGenerateBase = commonPlayScalaCompile(PlayScalaCompiler.compileBase, playScalaBase, playScalaCompilationTasks)
  def playScalaGenerateRoutes(generator: RoutesGenerator) = commonPlayScalaCompile(PlayScalaRoutesCompiler.compileRoutes(generator), playScalaRoutes, playScalaCompilationTasks)
  val playScalaGenerateTests = commonPlayScalaCompile(PlayScalaCompiler.compileTests, playScalaTests, playScalaCompilationTasks)
  val playScalaGenerateControllers = commonPlayScalaCompile(PlayScalaCompiler.compileControllers, playScalaControllers, playScalaCompilationTasks)
  val playScalaGenerateMarshallers = commonPlayScalaCompile(PlayScalaCompiler.compileMarshallers, playScalaMarshallers, playScalaCompilationTasks)
  val playScalaGenerateSecurity = commonPlayScalaCompile(PlayScalaCompiler.compileExtractors, playScalaSecurity, playScalaCompilationTasks)

  private def commonPlayScalaCompile: ((PlayScalaCompilationTask, File, Seq[String], StrictModel, Option[String], Set[String]) => CompilationResult, TaskKey[scala.Seq[sbt.File]], TaskKey[scala.Seq[(File, String, StrictModel)]]) => Def.Initialize[Task[Seq[File]]] =
    (compiler, config, filesAndModels) => Def.task {
      val routesImport = RoutesCompiler.autoImport.routesImport.value
      val cacheDirectory = streams.value.cacheDirectory
      val taskModelPairs = filesAndModels.value
      val outputDirectory = (target in config).value
      val templateDirectory = playScalaCustomTemplateLocation.value

      // Read the detailed scaladoc for syncIncremental to see how it works
      val (products, errors) = syncIncremental(cacheDirectory, taskModelPairs) { fileVersionAndModel: Seq[(File, String, StrictModel)] =>
        val results = fileVersionAndModel map {
          case (file, ver, model) =>
            val task = PlayScalaCompilationTask(file)
            (file, ver, model) -> Try {
              compiler(task, outputDirectory, routesImport, model, templateDirectory.map(_.getAbsolutePath), providedWriterFactories)
            }
        }
        // Collect the results into a map of task to OpResult for syncIncremental
        val taskResults = results.map {
          case ((file, ver, model), Success(result)) =>
            (file, ver, model) -> OpSuccess(Set(file), result.allFiles)
          case (op, Failure(_)) => op -> OpFailure
        }.toMap

        // Collect the errors for our own error reporting
        val failures = results.collect {
          case (_, Failure(e)) => e
        }
        (taskResults, failures)
      }
      if (errors.nonEmpty)
        throw errors.head
      else
        products.to[Seq]
    }
}
