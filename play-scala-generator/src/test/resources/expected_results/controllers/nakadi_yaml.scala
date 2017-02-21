
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

package nakadi.yaml {
    // ----- Start of unmanaged code area for package NakadiYaml
    
    // ----- End of unmanaged code area for package NakadiYaml
    class NakadiYaml @Inject() (
        // ----- Start of unmanaged code area for injections NakadiYaml

        // ----- End of unmanaged code area for injections NakadiYaml
        lifecycle: ApplicationLifecycle,
        config: ConfigurationProvider
    ) extends NakadiYamlBase {
        // ----- Start of unmanaged code area for constructor NakadiYaml

        // ----- End of unmanaged code area for constructor NakadiYaml
        val nakadiHackGet_metrics = nakadiHackGet_metricsAction {  _ =>  
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackGet_metrics
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackGet_metrics
        }
        val nakadiHackGet_events_from_single_partition = nakadiHackGet_events_from_single_partitionAction { input: (String, String, Option[Int], String, Int, Option[Int], Option[Int], Option[Int]) =>
            val (start_from, partition, stream_limit, topic, batch_limit, batch_flush_timeout, stream_timeout, batch_keep_alive_limit) = input
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackGet_events_from_single_partition
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackGet_events_from_single_partition
        }
        val nakadiHackGet_partition = nakadiHackGet_partitionAction { input: (String, String) =>
            val (topic, partition) = input
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackGet_partition
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackGet_partition
        }
        val nakadiHackGet_topics = nakadiHackGet_topicsAction {  _ =>  
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackGet_topics
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackGet_topics
        }
        val nakadiHackGet_events_from_multiple_partitions = nakadiHackGet_events_from_multiple_partitionsAction { input: (Option[Int], Option[Int], Option[Int], String, Int, Option[Int], String) =>
            val (stream_timeout, stream_limit, batch_flush_timeout, x_nakadi_cursors, batch_limit, batch_keep_alive_limit, topic) = input
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackGet_events_from_multiple_partitions
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackGet_events_from_multiple_partitions
        }
        val nakadiHackPost_event = nakadiHackPost_eventAction { input: (String, Option[Event]) =>
            val (topic, event) = input
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackPost_event
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackPost_event
        }
        val nakadiHackGet_partitions = nakadiHackGet_partitionsAction { (topic: String) =>  
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackGet_partitions
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackGet_partitions
        }
        val nakadiHackPost_events = nakadiHackPost_eventsAction { input: (String, Option[Event]) =>
            val (topic, event) = input
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackPost_events
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackPost_events
        }
    
    }
}
