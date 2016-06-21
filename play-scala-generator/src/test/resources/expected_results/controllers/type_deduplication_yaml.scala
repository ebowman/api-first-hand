
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

package type_deduplication.yaml {

    class Type_deduplicationYaml extends Type_deduplicationYamlBase {
        val getplantsByPlant_idWateringsByWatering_id = getplantsByPlant_idWateringsByWatering_idAction { input: (String, String) =>
            val (plant_id, watering_id) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idWateringsByWatering_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idWateringsByWatering_id
        }
        val putplantsByPlant_idWateringsByWatering_id = putplantsByPlant_idWateringsByWatering_idAction { input: (String, String) =>
            val (plant_id, watering_id) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idWateringsByWatering_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idWateringsByWatering_id
        }
        val getusersMe = getusersMeAction {  _ =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getusersMe
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getusersMe
        }
        val getplantsByPlant_idSunlight_needs = getplantsByPlant_idSunlight_needsAction { (plant_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idSunlight_needs
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idSunlight_needs
        }
        val putplantsByPlant_idSunlight_needs = putplantsByPlant_idSunlight_needsAction { input: (String, SunlightNeeds) =>
            val (plant_id, sunlight_needs) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idSunlight_needs
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idSunlight_needs
        }
        val getusers = getusersAction { input: (UsersGetLimit, UsersGetLimit) =>
            val (limit, offset) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getusers
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getusers
        }
        val postusers = postusersAction { (signin_data: SigninData) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.postusers
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.postusers
        }
        val getareasByArea_id = getareasByArea_idAction { (area_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getareasByArea_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getareasByArea_id
        }
        val putareasByArea_id = putareasByArea_idAction { (area_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putareasByArea_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putareasByArea_id
        }
        val deleteareasByArea_id = deleteareasByArea_idAction { (area_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.deleteareasByArea_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.deleteareasByArea_id
        }
        val getplants = getplantsAction { input: (PlantsGetLimit, PlantsGetOffset) =>
            val (limit, offset) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplants
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplants
        }
        val getuserByUser_idPlants = getuserByUser_idPlantsAction { input: (String, UsersGetLimit, UsersGetLimit) =>
            val (user_id, limit, offset) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getuserByUser_idPlants
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getuserByUser_idPlants
        }
        val getusersByUser_id = getusersByUser_idAction { (user_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getusersByUser_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getusersByUser_id
        }
        val putusersByUser_id = putusersByUser_idAction { input: (String, User) =>
            val (user_id, user) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putusersByUser_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putusersByUser_id
        }
        val deleteusersByUser_id = deleteusersByUser_idAction { input: (String, User) =>
            val (user_id, user) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.deleteusersByUser_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.deleteusersByUser_id
        }
        val getareas = getareasAction { input: (UsersGetLimit, UsersGetLimit) =>
            val (limit, offset) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getareas
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getareas
        }
        val getplantsByPlant_idLocation = getplantsByPlant_idLocationAction { (plant_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idLocation
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idLocation
        }
        val putplantsByPlant_idLocation = putplantsByPlant_idLocationAction { input: (String, Location) =>
            val (plant_id, location) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idLocation
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idLocation
        }
        val deleteplantsByPlant_idLocation = deleteplantsByPlant_idLocationAction { (plant_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.deleteplantsByPlant_idLocation
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.deleteplantsByPlant_idLocation
        }
        val getusersByUser_idPicture = getusersByUser_idPictureAction { (user_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getusersByUser_idPicture
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getusersByUser_idPicture
        }
        val putusersByUser_idPicture = putusersByUser_idPictureAction { (user_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putusersByUser_idPicture
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putusersByUser_idPicture
        }
        val deleteusersByUser_idPicture = deleteusersByUser_idPictureAction { (user_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.deleteusersByUser_idPicture
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.deleteusersByUser_idPicture
        }
        val getplantsByPlant_idPictures = getplantsByPlant_idPicturesAction { input: (String, UsersGetLimit, UsersGetLimit) =>
            val (plant_id, limit, offset) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idPictures
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idPictures
        }
        val getplantsByPlant_id = getplantsByPlant_idAction { (plant_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_id
        }
        val putplantsByPlant_id = putplantsByPlant_idAction { input: (String, Plant) =>
            val (plant_id, plant) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_id
        }
        val deleteplantsByPlant_id = deleteplantsByPlant_idAction { (plant_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.deleteplantsByPlant_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.deleteplantsByPlant_id
        }
        val getplantsByPlant_idWaterings = getplantsByPlant_idWateringsAction { input: (String, UsersGetLimit, UsersGetLimit) =>
            val (plant_id, limit, offset) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idWaterings
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idWaterings
        }
        val getplantsByPlant_idPicturesByPicture_id = getplantsByPlant_idPicturesByPicture_idAction { input: (String, String) =>
            val (plant_id, picture_id) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idPicturesByPicture_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idPicturesByPicture_id
        }
        val putplantsByPlant_idPicturesByPicture_id = putplantsByPlant_idPicturesByPicture_idAction { input: (String, String) =>
            val (plant_id, picture_id) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idPicturesByPicture_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idPicturesByPicture_id
        }
        val deleteplantsByPlant_idPicturesByPicture_id = deleteplantsByPlant_idPicturesByPicture_idAction { input: (String, String) =>
            val (plant_id, picture_id) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.deleteplantsByPlant_idPicturesByPicture_id
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.deleteplantsByPlant_idPicturesByPicture_id
        }
        val getplantsByPlant_idWater_needs = getplantsByPlant_idWater_needsAction { (plant_id: String) =>  
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idWater_needs
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.getplantsByPlant_idWater_needs
        }
        val putplantsByPlant_idWater_needs = putplantsByPlant_idWater_needsAction { input: (String, WaterNeeds) =>
            val (plant_id, water_needs) = input
            // ----- Start of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idWater_needs
            NotImplementedYet
            // ----- End of unmanaged code area for action  Type_deduplicationYaml.putplantsByPlant_idWater_needs
        }
    
    }
}
