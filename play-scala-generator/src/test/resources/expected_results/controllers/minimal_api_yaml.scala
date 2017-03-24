
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

package admin {
    // ----- Start of unmanaged code area for package Dashboard
    // ----- End of unmanaged code area for package Dashboard
    class Dashboard @Inject() (
        // ----- Start of unmanaged code area for injections Dashboard

        // ----- End of unmanaged code area for injections Dashboard
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends DashboardBase {
        // ----- Start of unmanaged code area for constructor Dashboard

        // ----- End of unmanaged code area for constructor Dashboard
        val index = indexAction {  _ =>
            // ----- Start of unmanaged code area for action  Dashboard.index
            NotImplementedYet
            // ----- End of unmanaged code area for action  Dashboard.index
        }

    }
}
