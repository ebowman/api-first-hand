
import play.api.mvc.{Action,Controller}

import play.api.data.validation.Constraint

import play.api.inject.{ApplicationLifecycle,ConfigurationProvider}

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import javax.inject._


/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package i020_number_parsing.yaml {
    // ----- Start of unmanaged code area for package I020_number_parsingYaml
    
    // ----- End of unmanaged code area for package I020_number_parsingYaml
    class I020_number_parsingYaml @Inject() (
        // ----- Start of unmanaged code area for injections I020_number_parsingYaml

        // ----- End of unmanaged code area for injections I020_number_parsingYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends I020_number_parsingYamlBase {
        // ----- Start of unmanaged code area for constructor I020_number_parsingYaml

        // ----- End of unmanaged code area for constructor I020_number_parsingYaml
        val post = postAction { (body: PostBody) =>  
            // ----- Start of unmanaged code area for action  I020_number_parsingYaml.post
            body.map(Post200).getOrElse(Post400())
            // ----- End of unmanaged code area for action  I020_number_parsingYaml.post
        }
    
    }
}
