
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

package basic_polymorphism.yaml {
    // ----- Start of unmanaged code area for package Basic_polymorphismYaml
    
    // ----- End of unmanaged code area for package Basic_polymorphismYaml
    class Basic_polymorphismYaml @Inject() (
        // ----- Start of unmanaged code area for injections Basic_polymorphismYaml

        // ----- End of unmanaged code area for injections Basic_polymorphismYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends Basic_polymorphismYamlBase {
        // ----- Start of unmanaged code area for constructor Basic_polymorphismYaml

        // ----- End of unmanaged code area for constructor Basic_polymorphismYaml
        val put = putAction { (dummy: Option[Pet]) =>  
            // ----- Start of unmanaged code area for action  Basic_polymorphismYaml.put
            NotImplementedYet
            // ----- End of unmanaged code area for action  Basic_polymorphismYaml.put
        }
    
    }
}
