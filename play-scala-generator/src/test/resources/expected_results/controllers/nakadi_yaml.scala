
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

package nakadi.yaml {

    class NakadiYaml extends NakadiYamlBase {
        val nakadiHackGet_metrics = nakadiHackGet_metricsAction {  _ =>  
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackGet_metrics
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackGet_metrics
        }
        val nakadiHackGet_events_from_single_partition = nakadiHackGet_events_from_single_partitionAction { input: (String, String, TopicsTopicEventsGetStream_timeout, String, Int, TopicsTopicEventsGetStream_timeout, TopicsTopicEventsGetStream_timeout, TopicsTopicEventsGetStream_timeout) =>
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
        val nakadiHackGet_events_from_multiple_partitions = nakadiHackGet_events_from_multiple_partitionsAction { input: (TopicsTopicEventsGetStream_timeout, TopicsTopicEventsGetStream_timeout, TopicsTopicEventsGetStream_timeout, String, Int, TopicsTopicEventsGetStream_timeout, String) =>
            val (stream_timeout, stream_limit, batch_flush_timeout, x_nakadi_cursors, batch_limit, batch_keep_alive_limit, topic) = input
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackGet_events_from_multiple_partitions
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackGet_events_from_multiple_partitions
        }
        val nakadiHackPost_event = nakadiHackPost_eventAction { input: (String, TopicsTopicEventsBatchPostEvent) =>
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
        val nakadiHackPost_events = nakadiHackPost_eventsAction { input: (String, TopicsTopicEventsBatchPostEvent) =>
            val (topic, event) = input
            // ----- Start of unmanaged code area for action  NakadiYaml.nakadiHackPost_events
            NotImplementedYet
            // ----- End of unmanaged code area for action  NakadiYaml.nakadiHackPost_events
        }
    
    }
}
