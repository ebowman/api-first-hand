
import play.api.mvc.{Action, Controller}

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

package expanded {

    class Expanded_polymorphismYaml @Inject() (lifecycle: ApplicationLifecycle, config: ConfigurationProvider) extends Expanded_polymorphismYamlBase {
    // ----- Start of unmanaged code area for constructor Expanded_polymorphismYaml
    // ----- End of unmanaged code area for constructor Expanded_polymorphismYaml
        val findPets = findPetsAction { input: (PetsGetTags, PetsGetLimit) =>
            val (tags, limit) = input
            // ----- Start of unmanaged code area for action  Expanded_polymorphismYaml.findPets
            NotImplementedYet
            // ----- End of unmanaged code area for action  Expanded_polymorphismYaml.findPets
        }
        val addPet = addPetAction { (pet: NewPet) =>  
            // ----- Start of unmanaged code area for action  Expanded_polymorphismYaml.addPet
            NotImplementedYet
            // ----- End of unmanaged code area for action  Expanded_polymorphismYaml.addPet
        }
        val deletePet = deletePetAction { (id: Long) =>  
            // ----- Start of unmanaged code area for action  Expanded_polymorphismYaml.deletePet
            NotImplementedYet
            // ----- End of unmanaged code area for action  Expanded_polymorphismYaml.deletePet
        }
    
    }
}
