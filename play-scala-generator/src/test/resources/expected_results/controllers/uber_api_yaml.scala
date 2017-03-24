
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

package uber.api.yaml {
    // ----- Start of unmanaged code area for package UberApiYaml
    // ----- End of unmanaged code area for package UberApiYaml
    class UberApiYaml @Inject() (
        // ----- Start of unmanaged code area for injections UberApiYaml

        // ----- End of unmanaged code area for injections UberApiYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends UberApiYamlBase {
        // ----- Start of unmanaged code area for constructor UberApiYaml

        // ----- End of unmanaged code area for constructor UberApiYaml
        val getme = getmeAction {  _ =>
            // ----- Start of unmanaged code area for action  UberApiYaml.getme
            NotImplementedYet
            // ----- End of unmanaged code area for action  UberApiYaml.getme
        }
        val getproducts = getproductsAction { input: (Double, Double) =>
            val (latitude, longitude) = input
            // ----- Start of unmanaged code area for action  UberApiYaml.getproducts
            NotImplementedYet
            // ----- End of unmanaged code area for action  UberApiYaml.getproducts
        }
        val getestimatesTime = getestimatesTimeAction { input: (Double, Double, EstimatesTimeGetCustomer_uuid, ProfilePicture) =>
            val (start_latitude, start_longitude, customer_uuid, product_id) = input
            // ----- Start of unmanaged code area for action  UberApiYaml.getestimatesTime
            NotImplementedYet
            // ----- End of unmanaged code area for action  UberApiYaml.getestimatesTime
        }
        val getestimatesPrice = getestimatesPriceAction { input: (Double, Double, Double, Double) =>
            val (start_latitude, start_longitude, end_latitude, end_longitude) = input
            // ----- Start of unmanaged code area for action  UberApiYaml.getestimatesPrice
            NotImplementedYet
            // ----- End of unmanaged code area for action  UberApiYaml.getestimatesPrice
        }
        val gethistory = gethistoryAction { input: (ErrorCode, ErrorCode) =>
            val (offset, limit) = input
            // ----- Start of unmanaged code area for action  UberApiYaml.gethistory
            NotImplementedYet
            // ----- End of unmanaged code area for action  UberApiYaml.gethistory
        }

    }
}
