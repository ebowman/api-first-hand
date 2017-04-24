
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

package enum.yaml {
    // ----- Start of unmanaged code area for package EnumYaml
    
    // ----- End of unmanaged code area for package EnumYaml
    class EnumYaml @Inject() (
        // ----- Start of unmanaged code area for injections EnumYaml

        // ----- End of unmanaged code area for injections EnumYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends EnumYamlBase {
        // ----- Start of unmanaged code area for constructor EnumYaml

        // ----- End of unmanaged code area for constructor EnumYaml
        val gettest = gettestAction { (includes: Option[TestGetIncludesOptionEnum]) =>  
            // ----- Start of unmanaged code area for action  EnumYaml.gettest
            NotImplementedYet
            // ----- End of unmanaged code area for action  EnumYaml.gettest
        }
    
    }
}
