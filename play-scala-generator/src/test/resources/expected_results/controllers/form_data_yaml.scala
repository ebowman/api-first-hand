
import play.api.mvc.{ Action, Controller }

import play.api.data.validation.Constraint

import play.api.inject.{ ApplicationLifecycle, ConfigurationProvider }

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import javax.inject._

import java.io.File


/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package form_data.yaml {

    class Form_dataYaml @Inject() (
        // ----- Start of unmanaged code area for injections Form_dataYaml

        // ----- End of unmanaged code area for injections Form_dataYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends Form_dataYamlBase {
        // ----- Start of unmanaged code area for constructor Form_dataYaml

        // ----- End of unmanaged code area for constructor Form_dataYaml
        val postmultipart = postmultipartAction { input: (String, BothPostYear, MultipartPostAvatar) =>
            val (name, year, avatar) = input
            // ----- Start of unmanaged code area for action  Form_dataYaml.postmultipart
            NotImplementedYet
            // ----- End of unmanaged code area for action  Form_dataYaml.postmultipart
        }
        val posturl_encoded = posturl_encodedAction { input: (String, BothPostYear, File) =>
            val (name, year, avatar) = input
            // ----- Start of unmanaged code area for action  Form_dataYaml.posturl_encoded
            NotImplementedYet
            // ----- End of unmanaged code area for action  Form_dataYaml.posturl_encoded
        }
        val postboth = postbothAction { input: (String, BothPostYear, MultipartPostAvatar, File) =>
            val (name, year, avatar, ringtone) = input
            // ----- Start of unmanaged code area for action  Form_dataYaml.postboth
            NotImplementedYet
            // ----- End of unmanaged code area for action  Form_dataYaml.postboth
        }

    }
}
