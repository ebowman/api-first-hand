
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

package simple_petstore_api_yaml {

    class SimplePetstoreApiYaml extends SimplePetstoreApiYamlBase {
        val addPet = addPetAction { (pet: NewPet) =>  
            // ----- Start of unmanaged code area for action  SimplePetstoreApiYaml.addPet
            NotImplementedYet
            // ----- End of unmanaged code area for action  SimplePetstoreApiYaml.addPet
        }
    
    }
}
package simple_petstore_api_yaml {

    class Dashboard extends DashboardBase {
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
