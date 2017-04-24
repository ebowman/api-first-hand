
import play.api.mvc.{Action,Controller}

import play.api.data.validation.Constraint

import play.api.inject.{ApplicationLifecycle,ConfigurationProvider}

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import javax.inject._

import de.zalando.play.controllers.BinaryString

import BinaryString._


/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package string_formats.yaml {
    // ----- Start of unmanaged code area for package String_formatsYaml
    
    // ----- End of unmanaged code area for package String_formatsYaml
    class String_formatsYaml @Inject() (
        // ----- Start of unmanaged code area for injections String_formatsYaml

        // ----- End of unmanaged code area for injections String_formatsYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends String_formatsYamlBase {
        // ----- Start of unmanaged code area for constructor String_formatsYaml

        // ----- End of unmanaged code area for constructor String_formatsYaml
        val get = getAction { input: (Option[ZonedDateTime], Option[LocalDate], Option[Base64String], Option[UUID], BinaryString) =>
            val (date_time, date, base64, uuid, petId) = input
            // ----- Start of unmanaged code area for action  String_formatsYaml.get
            NotImplementedYet
            // ----- End of unmanaged code area for action  String_formatsYaml.get
        }
    
    }
}
