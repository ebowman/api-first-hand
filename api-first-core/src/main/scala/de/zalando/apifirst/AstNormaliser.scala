package de.zalando.apifirst

import de.zalando.apifirst.Application.StrictModel

object AstNormaliser {
  def flattenAST(initialAst: StrictModel): StrictModel = TypeNormaliser.flatten(initialAst)
}
