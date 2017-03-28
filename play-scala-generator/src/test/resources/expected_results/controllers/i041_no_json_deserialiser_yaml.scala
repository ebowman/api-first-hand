
import play.api.mvc.{Action, Controller}

import play.api.data.validation.Constraint

import play.api.inject.{ApplicationLifecycle, ConfigurationProvider}

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

package i041_no_json_deserialiser.yaml {
    // ----- Start of unmanaged code area for package I041_no_json_deserialiserYaml

    // ----- End of unmanaged code area for package I041_no_json_deserialiserYaml
    class I041_no_json_deserialiserYaml @Inject() (
        // ----- Start of unmanaged code area for injections I041_no_json_deserialiserYaml

        // ----- End of unmanaged code area for injections I041_no_json_deserialiserYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends I041_no_json_deserialiserYamlBase {
        // ----- Start of unmanaged code area for constructor I041_no_json_deserialiserYaml

        // ----- End of unmanaged code area for constructor I041_no_json_deserialiserYaml
        val listUser = listUserAction {  _ =>
            // ----- Start of unmanaged code area for action  I041_no_json_deserialiserYaml.listUser
            NotImplementedYet
            // ----- End of unmanaged code area for action  I041_no_json_deserialiserYaml.listUser
        }
        val createUser = createUserAction { (name: String) =>
            // ----- Start of unmanaged code area for action  I041_no_json_deserialiserYaml.createUser
            NotImplementedYet
            // ----- End of unmanaged code area for action  I041_no_json_deserialiserYaml.createUser
        }
        val showUserById = showUserByIdAction { (id: BigInt) =>
            // ----- Start of unmanaged code area for action  I041_no_json_deserialiserYaml.showUserById
            NotImplementedYet
            // ----- End of unmanaged code area for action  I041_no_json_deserialiserYaml.showUserById
        }
        val putUser = putUserAction { input: (BigInt, User) =>
            val (id, body) = input
            // ----- Start of unmanaged code area for action  I041_no_json_deserialiserYaml.putUser
            NotImplementedYet
            // ----- End of unmanaged code area for action  I041_no_json_deserialiserYaml.putUser
        }

    }
}
