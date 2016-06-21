package de.zalando.play.apifirst.sbt

import java.io.File

import de.zalando.apifirst.Application.StrictModel
import de.zalando.apifirst.generators.AstScalaPlayEnricher
import de.zalando.apifirst.util.PrettyPrinter

/**
 * @since 23.03.2016.
 */
object ApiFirstPrettyPrinter extends PrettyPrinter {

  def denotations(file: File, ast: StrictModel): Seq[String] = {
    val play = AstScalaPlayEnricher(ast, Set.empty[String])
    val lines = play.toSeq.map {
      case (ref, den) =>
        formatText(ref.toString)(dyellow, black) + " → " + den
    }
    withFileName("Parameters:\t", file, lines)
  }

}
