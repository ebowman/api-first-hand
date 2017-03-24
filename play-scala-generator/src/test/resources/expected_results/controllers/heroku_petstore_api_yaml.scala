
import play.api.mvc.{ Action, Controller }

import play.api.data.validation.Constraint

import play.api.inject.{ ApplicationLifecycle, ConfigurationProvider }

import de.zalando.play.controllers._

import PlayBodyParsing._

import PlayValidations._

import scala.util._

import javax.inject._

import scala.math.BigInt


/**
 * This controller is re-generated after each change in the specification.
 * Please only place your hand-written code between appropriate comments in the body of the controller.
 */

package heroku.petstore.api.yaml {
    // ----- Start of unmanaged code area for package HerokuPetstoreApiYaml
    // ----- End of unmanaged code area for package HerokuPetstoreApiYaml
    class HerokuPetstoreApiYaml @Inject() (
        // ----- Start of unmanaged code area for injections HerokuPetstoreApiYaml

        // ----- End of unmanaged code area for injections HerokuPetstoreApiYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends HerokuPetstoreApiYamlBase {
        // ----- Start of unmanaged code area for constructor HerokuPetstoreApiYaml

        // ----- End of unmanaged code area for constructor HerokuPetstoreApiYaml
        val get = getAction { (limit: BigInt) =>
            // ----- Start of unmanaged code area for action  HerokuPetstoreApiYaml.get
            NotImplementedYet
            // ----- End of unmanaged code area for action  HerokuPetstoreApiYaml.get
        }
        val put = putAction { (pet: PutPet) =>
            // ----- Start of unmanaged code area for action  HerokuPetstoreApiYaml.put
            NotImplementedYet
            // ----- End of unmanaged code area for action  HerokuPetstoreApiYaml.put
        }
        val post = postAction { (pet: Pet) =>
            // ----- Start of unmanaged code area for action  HerokuPetstoreApiYaml.post
            NotImplementedYet
            // ----- End of unmanaged code area for action  HerokuPetstoreApiYaml.post
        }
        val getbyPetId = getbyPetIdAction { (petId: String) =>
            // ----- Start of unmanaged code area for action  HerokuPetstoreApiYaml.getbyPetId
            NotImplementedYet
            // ----- End of unmanaged code area for action  HerokuPetstoreApiYaml.getbyPetId
        }

    }
}
