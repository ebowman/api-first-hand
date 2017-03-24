
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

package all_of_imports.yaml {
    // ----- Start of unmanaged code area for package All_of_importsYaml
    // ----- End of unmanaged code area for package All_of_importsYaml
    class All_of_importsYaml @Inject() (
        // ----- Start of unmanaged code area for injections All_of_importsYaml

        // ----- End of unmanaged code area for injections All_of_importsYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends All_of_importsYamlBase {
        // ----- Start of unmanaged code area for constructor All_of_importsYaml

        // ----- End of unmanaged code area for constructor All_of_importsYaml
        val post = postAction { (body: DatetimeValue) =>
            // ----- Start of unmanaged code area for action  All_of_importsYaml.post
            NotImplementedYet
            // ----- End of unmanaged code area for action  All_of_importsYaml.post
        }

    }
}
