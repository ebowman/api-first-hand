
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

package i023_allOf_imports.yaml {
    // ----- Start of unmanaged code area for package I023_allOf_importsYaml
        
    // ----- End of unmanaged code area for package I023_allOf_importsYaml
    class I023_allOf_importsYaml @Inject() (
        // ----- Start of unmanaged code area for injections I023_allOf_importsYaml

        // ----- End of unmanaged code area for injections I023_allOf_importsYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends I023_allOf_importsYamlBase {
        // ----- Start of unmanaged code area for constructor I023_allOf_importsYaml

        // ----- End of unmanaged code area for constructor I023_allOf_importsYaml
        val post = postAction { (body: DatetimeValue) =>  
            // ----- Start of unmanaged code area for action  I023_allOf_importsYaml.post
            Post200(body)
            // ----- End of unmanaged code area for action  I023_allOf_importsYaml.post
        }
    
    }
}
