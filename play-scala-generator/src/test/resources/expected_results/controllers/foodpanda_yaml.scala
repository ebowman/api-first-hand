
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

package com.foodpanda.popsey.api {
    // ----- Start of unmanaged code area for package FoodpandaYaml
    
    // ----- End of unmanaged code area for package FoodpandaYaml
    class FoodpandaYaml @Inject() (
        // ----- Start of unmanaged code area for injections FoodpandaYaml

        // ----- End of unmanaged code area for injections FoodpandaYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends FoodpandaYamlBase {
        // ----- Start of unmanaged code area for constructor FoodpandaYaml

        // ----- End of unmanaged code area for constructor FoodpandaYaml
        val searchVendors = searchVendorsAction { (query: VendorQuery) =>  
            // ----- Start of unmanaged code area for action  FoodpandaYaml.searchVendors
            NotImplementedYet
            // ----- End of unmanaged code area for action  FoodpandaYaml.searchVendors
        }
    
    }
}
