package de.zalando.swagger

import java.net.URL

import de.zalando.apifirst.Application.SecurityDefinitionsTable
import de.zalando.apifirst.{ ParameterPlace, Security }
import de.zalando.swagger.strictModel._

/**
 * @author  slasch
 * @since   05.03.2016
 */
object SecurityConverter {

  def convertDefinitions(swaggerDefinitions: SecurityDefinitions): SecurityDefinitionsTable =
    if (swaggerDefinitions == null) Map.empty
    else swaggerDefinitions.collect {
      case (name, basic: BasicAuthenticationSecurity) =>
        name -> Security.Basic(Option(basic.description))
      case (name, apiKey: ApiKeySecurity) =>
        require(apiKey.name != null && apiKey.name.nonEmpty)
        require(apiKey.in != null && apiKey.in.nonEmpty)
        val place = ParameterPlace.withName(apiKey.in.toLowerCase)
        require(place == ParameterPlace.HEADER || place == ParameterPlace.QUERY)
        name -> Security.ApiKey(Option(apiKey.description), apiKey.name, place)
      case (name, oauth: Oauth2SecurityDefinition) =>
        val validationURL = oauth.validationUrl.map(new URL(_))
        name -> Security.OAuth2Definition(Option(oauth.description), validationURL, oauth.scopes)
    }
}

