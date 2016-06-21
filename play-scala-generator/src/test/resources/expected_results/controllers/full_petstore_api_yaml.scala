
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

package full.petstore.api.yaml {

    class FullPetstoreApiYaml extends FullPetstoreApiYamlBase {
        val findPetsByTags = findPetsByTagsAction { (tags: PetsFindByStatusGetStatus) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.findPetsByTags
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.findPetsByTags
        }
        val placeOrder = placeOrderAction { (body: StoresOrderPostBody) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.placeOrder
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.placeOrder
        }
        val createUser = createUserAction { (body: UsersUsernamePutBody) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.createUser
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.createUser
        }
        val createUsersWithListInput = createUsersWithListInputAction { (body: UsersCreateWithListPostBody) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.createUsersWithListInput
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.createUsersWithListInput
        }
        val getUserByName = getUserByNameAction { (username: String) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.getUserByName
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.getUserByName
        }
        val updateUser = updateUserAction { input: (String, UsersUsernamePutBody) =>
            val (username, body) = input
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.updateUser
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.updateUser
        }
        val deleteUser = deleteUserAction { (username: String) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.deleteUser
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.deleteUser
        }
        val updatePet = updatePetAction { (body: PetsPostBody) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.updatePet
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.updatePet
        }
        val addPet = addPetAction { (body: PetsPostBody) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.addPet
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.addPet
        }
        val createUsersWithArrayInput = createUsersWithArrayInputAction { (body: UsersCreateWithListPostBody) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.createUsersWithArrayInput
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.createUsersWithArrayInput
        }
        val getOrderById = getOrderByIdAction { (orderId: String) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.getOrderById
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.getOrderById
        }
        val deleteOrder = deleteOrderAction { (orderId: String) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.deleteOrder
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.deleteOrder
        }
        val logoutUser = logoutUserAction {  _ =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.logoutUser
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.logoutUser
        }
        val getPetById = getPetByIdAction { (petId: Long) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.getPetById
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.getPetById
        }
        val updatePetWithForm = updatePetWithFormAction { input: (String, String, String) =>
            val (petId, name, status) = input
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.updatePetWithForm
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.updatePetWithForm
        }
        val deletePet = deletePetAction { input: (String, Long) =>
            val (api_key, petId) = input
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.deletePet
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.deletePet
        }
        val findPetsByStatus = findPetsByStatusAction { (status: PetsFindByStatusGetStatus) =>  
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.findPetsByStatus
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.findPetsByStatus
        }
        val loginUser = loginUserAction { input: (OrderStatus, OrderStatus) =>
            val (username, password) = input
            // ----- Start of unmanaged code area for action  FullPetstoreApiYaml.loginUser
            NotImplementedYet
            // ----- End of unmanaged code area for action  FullPetstoreApiYaml.loginUser
        }
    
    }
}
