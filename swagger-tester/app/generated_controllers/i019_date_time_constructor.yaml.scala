
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

package i019_date_time_constructor.yaml {

    class I019_date_time_constructorYaml @Inject() (
        // ----- Start of unmanaged code area for injections I019_date_time_constructorYaml

        // ----- End of unmanaged code area for injections I019_date_time_constructorYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends I019_date_time_constructorYamlBase {
        // ----- Start of unmanaged code area for constructor I019_date_time_constructorYaml

        // ----- End of unmanaged code area for constructor I019_date_time_constructorYaml
        val post = postAction { (body: PostBody) =>  
            // ----- Start of unmanaged code area for action  I019_date_time_constructorYaml.post
            Post200(body.getOrElse(throw new IllegalStateException("Body is expected")))
            // ----- End of unmanaged code area for action  I019_date_time_constructorYaml.post
        }
    
    }
}
