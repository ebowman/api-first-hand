package de.zalando.swagger

import java.io.File
import java.net.URI

import de.zalando.apifirst.Application.StrictModel
import de.zalando.swagger.strictModel.SwaggerModel

object SwaggerParser {
  def readSwaggerModel(definitionFile: File): (URI, SwaggerModel) = {
    val parser = if (definitionFile.getName.endsWith(".yaml")) StrictYamlParser else StrictJsonParser
    val (uri, model) = parser.parse(definitionFile)
    (uri, model)
  }

  def convertModelToAST(definitionFile: File, uri: URI, model: SwaggerModel): StrictModel =
    ModelConverter.fromModel(uri, model, Option(definitionFile))

}
