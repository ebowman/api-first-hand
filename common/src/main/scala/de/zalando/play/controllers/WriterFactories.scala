package de.zalando.play.controllers

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
/**
 * @since 02.09.2015
 */
object WriterFactories {
  private val jsonFactory = new JsonFactory()

  /**
   * Contains proper Jackson Factories for different mime types
   * JsonFactory is a default
   */
  val factories: Map[String, JsonFactory] = Map(
    "application/json" -> jsonFactory,
    "text/x-yaml" -> new YAMLFactory() // TODO implement workaround for bug in yaml parser
  ).withDefaultValue(jsonFactory)
}
