
import play.api.mvc.{ Action, Controller }

import play.api.data.validation.Constraint

import play.api.inject.{ ApplicationLifecycle, ConfigurationProvider }

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import javax.inject._


/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package basic.auth.api.yaml {
    // ----- Start of unmanaged code area for package BasicAuthApiYaml
    
    // ----- End of unmanaged code area for package BasicAuthApiYaml
    class BasicAuthApiYaml @Inject() (
        // ----- Start of unmanaged code area for injections BasicAuthApiYaml

        // ----- End of unmanaged code area for injections BasicAuthApiYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends BasicAuthApiYamlBase {
        // ----- Start of unmanaged code area for constructor BasicAuthApiYaml

        // ----- End of unmanaged code area for constructor BasicAuthApiYaml
        val get = getAction {  _ =>  
            // ----- Start of unmanaged code area for action  BasicAuthApiYaml.get
            NotImplementedYet
            // ----- End of unmanaged code area for action  BasicAuthApiYaml.get
        }
    
    }
}
