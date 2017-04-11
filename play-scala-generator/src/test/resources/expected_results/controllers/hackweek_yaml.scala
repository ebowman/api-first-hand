
import play.api.mvc.{Action,Controller}

import play.api.data.validation.Constraint

import play.api.i18n.MessagesApi

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

package hackweek.yaml {
    // ----- Start of unmanaged code area for package HackweekYaml
            
    // ----- End of unmanaged code area for package HackweekYaml
    class HackweekYaml @Inject() (
        // ----- Start of unmanaged code area for injections HackweekYaml

        // ----- End of unmanaged code area for injections HackweekYaml
        val messagesApi: MessagesApi,
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends HackweekYamlBase {
        // ----- Start of unmanaged code area for constructor HackweekYaml

        // ----- End of unmanaged code area for constructor HackweekYaml
        val getschemaModel = getschemaModelAction { (root: ModelSchemaRoot) =>  
            // ----- Start of unmanaged code area for action  HackweekYaml.getschemaModel
            NotImplementedYet
            // ----- End of unmanaged code area for action  HackweekYaml.getschemaModel
        }
    
    }
}
