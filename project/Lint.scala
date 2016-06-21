import sbt.Keys._
import sbt._

object Lint {
  val LintTarget = config("lint").extend(Compile)

  val all = addMainSourcesToLintTarget ++
    addSlowScalacSwitchesToLintTarget ++
    addWartRemoverToLintTarget ++
    removeWartRemoverFromCompileTarget ++
    addFoursquareLinterToLintTarget ++
    removeFoursquareLinterFromCompileTarget

  def addMainSourcesToLintTarget: Seq[_root_.sbt.Def.Setting[_]] = {
    inConfig(LintTarget) {
      // I posted http://stackoverflow.com/questions/27575140/ and got back the bit below as the magic necessary
      // to create a separate lint target which we can run slow static analysis on.
      Defaults.compileSettings ++ Seq(
        sources in LintTarget := {
          val lintSources = (sources in LintTarget).value
          lintSources ++ (sources in Compile).value
        }
      )
    }
  }

  def addSlowScalacSwitchesToLintTarget: Seq[_root_.sbt.Def.Setting[_]] = {
    inConfig(LintTarget) {
      // In addition to everything we normally do when we compile, we can add additional scalac switches which are
      // normally too time consuming to run.
      scalacOptions in LintTarget ++= Seq(
        // As it says on the tin, detects unused imports. This is too slow to always include in the build.
        // "-Ywarn-unused-import",
        //This produces errors you don't want in development, but is useful.
        "-Ywarn-dead-code"
      )
    }
  }

  def addWartRemoverToLintTarget: Seq[_root_.sbt.Def.Setting[_]] = {
    import wartremover._
    import Wart._
    // I didn't simply include WartRemove in the build all the time because it roughly tripled compile time.
    inConfig(LintTarget) {
      wartremoverErrors ++= Seq(
        // Ban inferring Any, Serializable, and Product because such inference usually indicates a code error.
        Wart.Any,
        Wart.Serializable,
        Wart.Product,
        // Ban calling partial methods because they behave surprisingly
        Wart.ListOps,
        Wart.OptionPartial,
        Wart.EitherProjectionPartial,
        // Ban applying Scala's implicit any2String because it usually indicates a code error.
        Wart.Any2StringAdd
      )
    }
  }

  def removeWartRemoverFromCompileTarget: _root_.sbt.Def.Setting[_root_.sbt.Task[Seq[String]]] = {
    // WartRemover's sbt plugin calls addCompilerPlugin which always adds directly to the Compile configuration.
    // The bit below removes all switches that could be passed to scalac about WartRemover during a non-lint compile.
    scalacOptions in Compile := (scalacOptions in Compile).value filterNot { switch =>
      switch.startsWith("-P:wartremover:") ||
        "^-Xplugin:.*/org[.]brianmckenna/.*wartremover.*[.]jar$".r.pattern.matcher(switch).find
    }
  }

  def addFoursquareLinterToLintTarget: Seq[_root_.sbt.Def.Setting[_ >: _root_.sbt.Task[Seq[String]] with Seq[_root_.sbt.ModuleID] <: Equals]] = {
    Seq(
      addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.12"),
      // See https://github.com/HairyFotr/linter#list-of-implemented-checks for a list of checks that foursquare linter
      // implements
      // By default linter enables all checks.
      // I don't mind using match on boolean variables.
      scalacOptions in LintTarget += "-P:linter:disable:PreferIfToBooleanMatch"
    )
  }

  def removeFoursquareLinterFromCompileTarget: _root_.sbt.Def.Setting[_root_.sbt.Task[Seq[String]]] = {
    // We call addCompilerPlugin in project/plugins.sbt to add a depenency on the foursquare linter so that sbt magically
    // manages the JAR for us.  Unfortunately, addCompilerPlugin also adds a switch to scalacOptions in the Compile config
    // to load the plugin.
    // The bit below removes all switches that could be passed to scalac about Foursquare Linter during a non-lint compile.
    scalacOptions in Compile := (scalacOptions in Compile).value filterNot { switch =>
      switch.startsWith("-P:linter:") ||
        "^-Xplugin:.*/org[.]psywerx[.]hairyfotr/.*linter.*[.]jar$".r.pattern.matcher(switch).find
    }
  }
}
