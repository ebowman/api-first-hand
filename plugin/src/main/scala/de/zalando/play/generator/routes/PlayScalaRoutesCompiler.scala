package de.zalando.play.generator.routes

import java.io.File

import de.zalando.apifirst.Application.StrictModel
import de.zalando.play.compiler.{ CompilationResult, PlayScalaCompilationTask, PlayScalaCompiler }
import play.routes.compiler.RoutesCompiler.RoutesCompilerTask
import play.routes.compiler.RoutesGenerator
import PlayScalaCompiler._

/**
 * The routes compiler
 */
object PlayScalaRoutesCompiler {

  def compileRoutes(generator: RoutesGenerator)(task: PlayScalaCompilationTask, outputDir: File, routesImport: Seq[String],
    flatAst: StrictModel, customTemplates: Option[String], providedWriterFactories: Set[String]): CompilationResult = {
    val routesFiles = compileRoutes(task, outputDir, routesImport, generator)(flatAst)
    CompilationResult(routesFiles)
  }

  private def compileRoutes(task: PlayScalaCompilationTask, outputDir: File, routesImport: Seq[String], generator: RoutesGenerator)(implicit flatAst: StrictModel) = {
    val namespace = flatAst.packageName.getOrElse(taskPackageName(task))
    val allImports = ((namespace + "._") +: (additionalImports(flatAst) ++ routesImport)).distinct
    val playNameSpace = Some(namespace)
    val playRules = RuleGenerator.apiCalls2PlayRules(flatAst.calls: _*).toList
    val playTask = RoutesCompilerTask(task.definitionFile, allImports, forwardsRouter = true, reverseRouter = true, namespaceReverseRouter = false)
    val generated = generator.generate(playTask, playNameSpace, playRules).filter(_._1.endsWith(".scala"))
    val persister = persist(None, outputDir, overwrite = true) _
    val routesFiles = generated map persister.tupled
    Seq(routesFiles.flatten)
  }
}

