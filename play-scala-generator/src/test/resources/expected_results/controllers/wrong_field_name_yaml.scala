
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

package wrong_field_name.yaml {

    class Wrong_field_nameYaml @Inject() (
        // ----- Start of unmanaged code area for injections Wrong_field_nameYaml

        // ----- End of unmanaged code area for injections Wrong_field_nameYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends Wrong_field_nameYamlBase {
        // ----- Start of unmanaged code area for constructor Wrong_field_nameYaml

        // ----- End of unmanaged code area for constructor Wrong_field_nameYaml
        val get = getAction { input: (GetOptCodes, GetCodes) =>
            val (optCodes, codes) = input
            // ----- Start of unmanaged code area for action  Wrong_field_nameYaml.get
            NotImplementedYet
            // ----- End of unmanaged code area for action  Wrong_field_nameYaml.get
        }
    
    }
}
