
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

package required_parametrs.yaml {
    // ----- Start of unmanaged code area for package Required_parametrsYaml
    
    // ----- End of unmanaged code area for package Required_parametrsYaml
    class Required_parametrsYaml @Inject() (
        // ----- Start of unmanaged code area for injections Required_parametrsYaml

        // ----- End of unmanaged code area for injections Required_parametrsYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends Required_parametrsYamlBase {
        // ----- Start of unmanaged code area for constructor Required_parametrsYaml

        // ----- End of unmanaged code area for constructor Required_parametrsYaml
        val get = getAction { input: (String, GetTest2) =>
            val (test1, test2) = input
            // ----- Start of unmanaged code area for action  Required_parametrsYaml.get
            NotImplementedYet
            // ----- End of unmanaged code area for action  Required_parametrsYaml.get
        }
    
    }
}
