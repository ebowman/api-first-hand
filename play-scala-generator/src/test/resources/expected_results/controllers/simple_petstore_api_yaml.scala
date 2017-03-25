
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

package simple_petstore_api_yaml {
    // ----- Start of unmanaged code area for package SimplePetstoreApiYaml
    // ----- End of unmanaged code area for package SimplePetstoreApiYaml
    class SimplePetstoreApiYaml @Inject() (
        // ----- Start of unmanaged code area for injections SimplePetstoreApiYaml

        // ----- End of unmanaged code area for injections SimplePetstoreApiYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends SimplePetstoreApiYamlBase {
        // ----- Start of unmanaged code area for constructor SimplePetstoreApiYaml

        // ----- End of unmanaged code area for constructor SimplePetstoreApiYaml
        val addPet = addPetAction { (pet: NewPet) =>
            // ----- Start of unmanaged code area for action  SimplePetstoreApiYaml.addPet
            NotImplementedYet
            // ----- End of unmanaged code area for action  SimplePetstoreApiYaml.addPet
        }

    }
}
package simple_petstore_api_yaml {
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
        val methodLevel = methodLevelAction { input: (PetsGetTags, PetsGetLimit) =>
            val (tags, limit) = input
            // ----- Start of unmanaged code area for action  Dashboard.methodLevel
            NotImplementedYet
            // ----- End of unmanaged code area for action  Dashboard.methodLevel
        }
        val pathLevelGet = pathLevelGetAction { (id: Long) =>
            // ----- Start of unmanaged code area for action  Dashboard.pathLevelGet
            NotImplementedYet
            // ----- End of unmanaged code area for action  Dashboard.pathLevelGet
        }
        val pathLevelDelete = pathLevelDeleteAction { (id: Long) =>
            // ----- Start of unmanaged code area for action  Dashboard.pathLevelDelete
            NotImplementedYet
            // ----- End of unmanaged code area for action  Dashboard.pathLevelDelete
        }

    }
}
