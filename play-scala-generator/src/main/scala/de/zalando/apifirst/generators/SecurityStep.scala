package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.StrictModel
import de.zalando.apifirst.Security._
import de.zalando.apifirst.{ ParameterPlace, ScalaName, Security }

/**
 * @author  slasch
 * @since   07.03.2016
 */
trait SecurityStep extends EnrichmentStep[StrictModel] with SecurityCommons {

  override def steps = security +: super.steps

  /**
   * Puts security related information into the denotation table
   *
   * @return
   */
  protected val security: SingleStep = spec => table => {
    val extractors: Iterable[Map[String, Any]] = spec._2.securityDefinitionsTable.map {
      case (name, definition) =>
        Map(
          "name" -> securityCheck(name),
          "type" -> securityType(definition),
          "security_params" -> securityParams(definition),
          "external_security_params" -> externalSecurityParams(definition),
          "user_params" -> userParams(definition)
        )
    }
    if (extractors.isEmpty) Map.empty
    else Map("security_extractors" -> Map("extractors" -> extractors))
  }

  private def securityType(definition: Definition): String = definition match {
    case b: Basic => "basicAuth"
    case ApiKey(_, _, in) if in == ParameterPlace.HEADER => "headerApiKey"
    case ApiKey(_, _, in) if in == ParameterPlace.QUERY => "queryApiKey"
    case o: OAuth2Definition => "oAuth"
  }

  private def securityParams(definition: Definition): Seq[Map[String, String]] = definition match {
    case b: Basic => Nil
    case ApiKey(_, name, _) => Seq(Map("name" -> ("\"" + name + "\"")))
    case OAuth2Definition(_, None, _) =>
      throw new IllegalStateException("Validation URL is required for play security code generator")
    case OAuth2Definition(_, Some(validationURL), scopes) => Seq(Map("name" -> ("\"" + validationURL + "\"")))
  }

  private def externalSecurityParams(definition: Definition): Seq[Map[String, String]] = definition match {
    case o: OAuth2Definition => Seq(Map("name" -> "scopes", "type" -> "String*"))
    case _ => Nil
  }

  private def userParams(definition: Definition): Seq[Map[String, String]] = definition match {
    case b: Basic => Seq(Map("name" -> "username", "type" -> "String"), Map("name" -> "password", "type" -> "String"))
    case ApiKey(_, name, _) => Seq(Map("name" -> "apiKey", "type" -> "String"))
    case OAuth2Definition(_, tokenUrl, scopes) => Seq(Map("name" -> "token", "type" -> "play.api.libs.json.JsValue"))
  }
}

trait SecurityCommons {
  def securityCheck(name: String) =
    ScalaName.escape(name + "_Extractor")

  def securityName(suffix: String, security: Security.Constraint*): String =
    security.map(_.name).mkString("", "_", "_" + suffix)
}