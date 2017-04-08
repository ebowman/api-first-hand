
import play.api.mvc.{Action,Controller}

import play.api.data.validation.Constraint

import play.api.inject.{ApplicationLifecycle,ConfigurationProvider}

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import javax.inject._

import java.time.ZonedDateTime


/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package i019_date_time_constructor.yaml {
    // ----- Start of unmanaged code area for package I019_date_time_constructorYaml
    
    // ----- End of unmanaged code area for package I019_date_time_constructorYaml
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
        val getbyTask = getbyTaskAction { input: (TaskComplete_before, ZonedDateTime) =>
            val (header, task) = input
            // ----- Start of unmanaged code area for action  I019_date_time_constructorYaml.getbyTask
            GetbyTask200(Task(header, Option(task.toLocalDate)))
            // ----- End of unmanaged code area for action  I019_date_time_constructorYaml.getbyTask
        }
    
    }
}
