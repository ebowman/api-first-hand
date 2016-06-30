
import play.api.mvc.{Action, Controller}

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

package security.api.yaml {

    class SecurityApiYaml @Inject() (lifecycle: ApplicationLifecycle, config: ConfigurationProvider) extends SecurityApiYamlBase {
    // ----- Start of unmanaged code area for constructor SecurityApiYaml
    // ----- End of unmanaged code area for constructor SecurityApiYaml
        val getPetsById = getPetsByIdAction { (id: PetsIdGetId) =>  
            // ----- Start of unmanaged code area for action  SecurityApiYaml.getPetsById
            NotImplementedYet
            // ----- End of unmanaged code area for action  SecurityApiYaml.getPetsById
        }
    
    }
}
