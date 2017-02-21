
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

package echo {
    // ----- Start of unmanaged code area for package EchoHandler
    
    // ----- End of unmanaged code area for package EchoHandler
    class EchoHandler @Inject() (
        // ----- Start of unmanaged code area for injections EchoHandler

        // ----- End of unmanaged code area for injections EchoHandler
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends EchoHandlerBase {
        // ----- Start of unmanaged code area for constructor EchoHandler

        // ----- End of unmanaged code area for constructor EchoHandler
        val method = methodAction {  _ =>  
            // ----- Start of unmanaged code area for action  EchoHandler.method
            NotImplementedYet
            // ----- End of unmanaged code area for action  EchoHandler.method
        }
    
    }
}
package echo {
    // ----- Start of unmanaged code area for package EchoApiYaml
    
    // ----- End of unmanaged code area for package EchoApiYaml
    class EchoApiYaml @Inject() (
        // ----- Start of unmanaged code area for injections EchoApiYaml

        // ----- End of unmanaged code area for injections EchoApiYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends EchoApiYamlBase {
        // ----- Start of unmanaged code area for constructor EchoApiYaml

        // ----- End of unmanaged code area for constructor EchoApiYaml
        val post = postAction { input: (Option[String], Option[String]) =>
            val (name, year) = input
            // ----- Start of unmanaged code area for action  EchoApiYaml.post
            NotImplementedYet
            // ----- End of unmanaged code area for action  EchoApiYaml.post
        }
        val gettest_pathById = gettest_pathByIdAction { (id: String) =>  
            // ----- Start of unmanaged code area for action  EchoApiYaml.gettest_pathById
            NotImplementedYet
            // ----- End of unmanaged code area for action  EchoApiYaml.gettest_pathById
        }
    
    }
}
