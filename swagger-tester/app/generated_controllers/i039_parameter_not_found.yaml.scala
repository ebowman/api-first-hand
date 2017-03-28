
import play.api.mvc.{Action,Controller}

import play.api.data.validation.Constraint

import play.api.inject.{ApplicationLifecycle,ConfigurationProvider}

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import javax.inject._

// -----

/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package i039_parameter_not_found.yaml {
    // ----- Start of unmanaged code area for package I039_parameter_not_foundYaml
    
    // ----- End of unmanaged code area for package I039_parameter_not_foundYaml
    class I039_parameter_not_foundYaml @Inject() (
        // ----- Start of unmanaged code area for injections I039_parameter_not_foundYaml

        // ----- End of unmanaged code area for injections I039_parameter_not_foundYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends I039_parameter_not_foundYamlBase {
        // ----- Start of unmanaged code area for constructor I039_parameter_not_foundYaml

        // ----- End of unmanaged code area for constructor I039_parameter_not_foundYaml
        val post = postAction { (body: OpStatus) =>  
            // ----- Start of unmanaged code area for action  I039_parameter_not_foundYaml.post
            NotImplementedYet
            // ----- End of unmanaged code area for action  I039_parameter_not_foundYaml.post
        }
    
    }
}
