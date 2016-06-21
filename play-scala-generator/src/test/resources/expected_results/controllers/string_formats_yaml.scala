
import play.api.mvc.{Action, Controller}

import play.api.data.validation.Constraint

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import de.zalando.play.controllers.BinaryString

import BinaryString._


/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package string_formats.yaml {

    class String_formatsYaml extends String_formatsYamlBase {
        val get = getAction { input: (GetDate_time, GetDate, GetBase64, GetUuid, BinaryString) =>
            val (date_time, date, base64, uuid, petId) = input
            // ----- Start of unmanaged code area for action  String_formatsYaml.get
            NotImplementedYet
            // ----- End of unmanaged code area for action  String_formatsYaml.get
        }
    
    }
}
