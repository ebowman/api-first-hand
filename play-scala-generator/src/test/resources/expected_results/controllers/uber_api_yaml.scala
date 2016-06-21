
import play.api.mvc.{Action, Controller}

import play.api.data.validation.Constraint

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._


/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package uber.api.yaml {

    class UberApiYaml extends UberApiYamlBase {
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
