package nakadi


    import java.util.UUID

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class EventMetaData(root_id: Option[UUID], parent_id: Option[UUID], scopes: Option[Seq[String]], id: Option[UUID], created: Option[String]) 
    case class Topic(name: String) 
    case class Metrics(name: Option[String]) 
    case class Event(event_type: Option[String], partitioning_key: Option[String], metadata: Option[EventMetaData]) 
    case class Cursor(partition: String, offset: String) 
    case class Problem(detail: String) 
    case class TopicPartition(partition: String, oldest_available_offset: String, newest_available_offset: String) 
    case class SimpleStreamEvent(cursor: Cursor, events: Option[Seq[Event]]) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val EventMetaDataReads: Reads[EventMetaData] = (
            (JsPath \ "root_id").read[Option[UUID]] and (JsPath \ "parent_id").read[Option[UUID]] and (JsPath \ "scopes").read[Option[Seq[String]]] and (JsPath \ "id").read[Option[UUID]] and (JsPath \ "created").read[Option[String]]
        )(EventMetaData.apply _)
        implicit val EventReads: Reads[Event] = (
            (JsPath \ "event_type").read[Option[String]] and (JsPath \ "partitioning_key").read[Option[String]] and (JsPath \ "metadata").read[Option[EventMetaData]]
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
    implicit val EventMetaDataWrites: Writes[EventMetaData] = new Writes[EventMetaData] {
        def writes(ss: EventMetaData) =
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

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type TopicsTopicEventsPostResponses201 = Null


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionIntQuery: QueryStringBindable[Option[Int]] = PlayPathBindables.createOptionQueryBindable[Int]

}