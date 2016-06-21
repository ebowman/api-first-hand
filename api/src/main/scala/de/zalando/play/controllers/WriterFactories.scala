package de.zalando.play.controllers

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
/**
 * FIXME this is duplicate of WriterFactories defined in common.
 * Remove this file as soon as correct sbt dependency management is in place
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
