package nakadi


    import java.util.UUID

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type TopicsTopicEventsGetStream_timeout = Option[Int]
    type EventEvent_type = Option[String]
    type SimpleStreamEventEventsOpt = Seq[Event]
    type EventMetaDataParent_id = Option[UUID]
    type EventMetadata = Option[EventMetaDataNameClash]
    type TopicsTopicEventsPostResponses201 = Null
    type EventMetaDataScopesOpt = Seq[String]
    type TopicsTopicPartitionsGetResponses200 = Seq[TopicPartition]
    type TopicsTopicEventsBatchPostEvent = Option[Event]
    type SimpleStreamEventEvents = Option[SimpleStreamEventEventsOpt]
    type EventMetaDataScopes = Option[EventMetaDataScopesOpt]
    type TopicsGetResponses200 = Seq[Topic]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionIntQuery: QueryStringBindable[Option[Int]] = PlayPathBindables.createOptionQueryBindable[Int]

}
//noinspection ScalaStyle
package yaml {


    case class EventMetaDataNameClash(root_id: EventMetaDataParent_id, parent_id: EventMetaDataParent_id, scopes: EventMetaDataScopes, id: EventMetaDataParent_id, created: EventEvent_type) 
    case class Topic(name: String) 
    case class Metrics(name: EventEvent_type) 
    case class Event(event_type: EventEvent_type, partitioning_key: EventEvent_type, metadata: EventMetadata) 
    case class Cursor(partition: String, offset: String) 
    case class Problem(detail: String) 
    case class TopicPartition(partition: String, oldest_available_offset: String, newest_available_offset: String) 
    case class SimpleStreamEvent(cursor: Cursor, events: SimpleStreamEventEvents) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val EventMetaDataNameClashReads: Reads[EventMetaDataNameClash] = (
            (JsPath \ "root_id").readNullable[UUID] and (JsPath \ "parent_id").readNullable[UUID] and (JsPath \ "scopes").readNullable[EventMetaDataScopesOpt] and (JsPath \ "id").readNullable[UUID] and (JsPath \ "created").readNullable[String]
        )(EventMetaDataNameClash.apply _)
        implicit val EventReads: Reads[Event] = (
            (JsPath \ "event_type").readNullable[String] and (JsPath \ "partitioning_key").readNullable[String] and (JsPath \ "metadata").readNullable[EventMetaDataNameClash]
        )(Event.apply _)
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val TopicWrites: Writes[Topic] = new Writes[Topic] {
        def writes(ss: Topic) =
          Json.obj(
            "name" -> ss.name
          )
        }
    implicit val TopicPartitionWrites: Writes[TopicPartition] = new Writes[TopicPartition] {
        def writes(ss: TopicPartition) =
          Json.obj(
            "partition" -> ss.partition, 
            "oldest_available_offset" -> ss.oldest_available_offset, 
            "newest_available_offset" -> ss.newest_available_offset
          )
        }
    implicit val EventMetaDataNameClashWrites: Writes[EventMetaDataNameClash] = new Writes[EventMetaDataNameClash] {
        def writes(ss: EventMetaDataNameClash) =
          Json.obj(
            "root_id" -> ss.root_id, 
            "parent_id" -> ss.parent_id, 
            "scopes" -> ss.scopes, 
            "id" -> ss.id, 
            "created" -> ss.created
          )
        }
    implicit val EventWrites: Writes[Event] = new Writes[Event] {
        def writes(ss: Event) =
          Json.obj(
            "event_type" -> ss.event_type, 
            "partitioning_key" -> ss.partitioning_key, 
            "metadata" -> ss.metadata
          )
        }
    implicit val CursorWrites: Writes[Cursor] = new Writes[Cursor] {
        def writes(ss: Cursor) =
          Json.obj(
            "partition" -> ss.partition, 
            "offset" -> ss.offset
          )
        }
    implicit val SimpleStreamEventWrites: Writes[SimpleStreamEvent] = new Writes[SimpleStreamEvent] {
        def writes(ss: SimpleStreamEvent) =
          Json.obj(
            "cursor" -> ss.cursor, 
            "events" -> ss.events
          )
        }
    implicit val MetricsWrites: Writes[Metrics] = new Writes[Metrics] {
        def writes(ss: Metrics) =
          Json.obj(
            "name" -> ss.name
          )
        }
    implicit val ProblemWrites: Writes[Problem] = new Writes[Problem] {
        def writes(ss: Problem) =
          Json.obj(
            "detail" -> ss.detail
          )
        }
    }
}
