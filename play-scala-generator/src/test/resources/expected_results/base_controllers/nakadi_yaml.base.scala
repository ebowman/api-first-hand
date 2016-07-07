package nakadi.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import de.zalando.play.controllers.ArrayWrapper
import java.util.UUID

import de.zalando.play.controllers.PlayPathBindables





//noinspection ScalaStyle
trait NakadiYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait NakadiHackGet_metricsType[T] extends ResultWrapper[T]
    def NakadiHackGet_metrics401(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_metricsType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP })
    def NakadiHackGet_metrics401(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_metricsType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_metrics503(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_metricsType[Problem] { val statusCode = 503; val result = resultP; val writer = writerP })
    def NakadiHackGet_metrics503(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_metricsType[Problem] { val statusCode = 503; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_metrics200(resultP: Metrics)(implicit writerP: String => Option[Writeable[Metrics]]) = success(new NakadiHackGet_metricsType[Metrics] { val statusCode = 200; val result = resultP; val writer = writerP })
    def NakadiHackGet_metrics200(resultF: Future[Metrics])(implicit writerP: String => Option[Writeable[Metrics]]) = resultF map { resultP => (new NakadiHackGet_metricsType[Metrics] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type nakadiHackGet_metricsActionRequestType       = (Unit)
    private type nakadiHackGet_metricsActionType[T]            = nakadiHackGet_metricsActionRequestType => Future[NakadiHackGet_metricsType[T] forSome { type T }]


    val nakadiHackGet_metricsActionConstructor  = Action

def nakadiHackGet_metricsAction[T] = (f: nakadiHackGet_metricsActionType[T]) => nakadiHackGet_metricsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { nakadiHackGet_metricsResponseMimeType =>
            
            

                val result = processValidnakadiHackGet_metricsRequest(f)()(nakadiHackGet_metricsResponseMimeType)
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidnakadiHackGet_metricsRequest[T](f: nakadiHackGet_metricsActionType[T])(request: nakadiHackGet_metricsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait NakadiHackGet_events_from_single_partitionType[T] extends ResultWrapper[T]
    def NakadiHackGet_events_from_single_partition500(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_events_from_single_partitionType[Problem] { val statusCode = 500; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_single_partition500(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_events_from_single_partitionType[Problem] { val statusCode = 500; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_events_from_single_partition404(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_events_from_single_partitionType[Problem] { val statusCode = 404; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_single_partition404(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_events_from_single_partitionType[Problem] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_events_from_single_partition401(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_events_from_single_partitionType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_single_partition401(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_events_from_single_partitionType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_events_from_single_partition400(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_events_from_single_partitionType[Problem] { val statusCode = 400; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_single_partition400(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_events_from_single_partitionType[Problem] { val statusCode = 400; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_events_from_single_partition200(resultP: SimpleStreamEvent)(implicit writerP: String => Option[Writeable[SimpleStreamEvent]]) = success(new NakadiHackGet_events_from_single_partitionType[SimpleStreamEvent] { val statusCode = 200; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_single_partition200(resultF: Future[SimpleStreamEvent])(implicit writerP: String => Option[Writeable[SimpleStreamEvent]]) = resultF map { resultP => (new NakadiHackGet_events_from_single_partitionType[SimpleStreamEvent] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type nakadiHackGet_events_from_single_partitionActionRequestType       = (String, String, TopicsTopicEventsGetStream_timeout, String, Int, TopicsTopicEventsGetStream_timeout, TopicsTopicEventsGetStream_timeout, TopicsTopicEventsGetStream_timeout)
    private type nakadiHackGet_events_from_single_partitionActionType[T]            = nakadiHackGet_events_from_single_partitionActionRequestType => Future[NakadiHackGet_events_from_single_partitionType[T] forSome { type T }]


    val nakadiHackGet_events_from_single_partitionActionConstructor  = Action

def nakadiHackGet_events_from_single_partitionAction[T] = (f: nakadiHackGet_events_from_single_partitionActionType[T]) => (start_from: String, partition: String, stream_limit: TopicsTopicEventsGetStream_timeout, topic: String, batch_limit: Int, batch_flush_timeout: TopicsTopicEventsGetStream_timeout, stream_timeout: TopicsTopicEventsGetStream_timeout, batch_keep_alive_limit: TopicsTopicEventsGetStream_timeout) => nakadiHackGet_events_from_single_partitionActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { nakadiHackGet_events_from_single_partitionResponseMimeType =>
            
            

                val result =
                        new TopicsTopicPartitionsPartitionEventsGetValidator(start_from, partition, stream_limit, topic, batch_limit, batch_flush_timeout, stream_timeout, batch_keep_alive_limit).errors match {
                            case e if e.isEmpty => processValidnakadiHackGet_events_from_single_partitionRequest(f)((start_from, partition, stream_limit, topic, batch_limit, batch_flush_timeout, stream_timeout, batch_keep_alive_limit))(nakadiHackGet_events_from_single_partitionResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(nakadiHackGet_events_from_single_partitionResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidnakadiHackGet_events_from_single_partitionRequest[T](f: nakadiHackGet_events_from_single_partitionActionType[T])(request: nakadiHackGet_events_from_single_partitionActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait NakadiHackGet_partitionType[T] extends ResultWrapper[T]
    def NakadiHackGet_partition200(resultP: TopicPartition)(implicit writerP: String => Option[Writeable[TopicPartition]]) = success(new NakadiHackGet_partitionType[TopicPartition] { val statusCode = 200; val result = resultP; val writer = writerP })
    def NakadiHackGet_partition200(resultF: Future[TopicPartition])(implicit writerP: String => Option[Writeable[TopicPartition]]) = resultF map { resultP => (new NakadiHackGet_partitionType[TopicPartition] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type nakadiHackGet_partitionActionRequestType       = (String, String)
    private type nakadiHackGet_partitionActionType[T]            = nakadiHackGet_partitionActionRequestType => Future[NakadiHackGet_partitionType[T] forSome { type T }]


    val nakadiHackGet_partitionActionConstructor  = Action

def nakadiHackGet_partitionAction[T] = (f: nakadiHackGet_partitionActionType[T]) => (topic: String, partition: String) => nakadiHackGet_partitionActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { nakadiHackGet_partitionResponseMimeType =>
            
            

                val result =
                        new TopicsTopicPartitionsPartitionGetValidator(topic, partition).errors match {
                            case e if e.isEmpty => processValidnakadiHackGet_partitionRequest(f)((topic, partition))(nakadiHackGet_partitionResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(nakadiHackGet_partitionResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidnakadiHackGet_partitionRequest[T](f: nakadiHackGet_partitionActionType[T])(request: nakadiHackGet_partitionActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait NakadiHackGet_topicsType[T] extends ResultWrapper[T]
    def NakadiHackGet_topics200(resultP: Seq[Topic])(implicit writerP: String => Option[Writeable[Seq[Topic]]]) = success(new NakadiHackGet_topicsType[Seq[Topic]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def NakadiHackGet_topics200(resultF: Future[Seq[Topic]])(implicit writerP: String => Option[Writeable[Seq[Topic]]]) = resultF map { resultP => (new NakadiHackGet_topicsType[Seq[Topic]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_topics401(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_topicsType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP })
    def NakadiHackGet_topics401(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_topicsType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_topics503(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_topicsType[Problem] { val statusCode = 503; val result = resultP; val writer = writerP })
    def NakadiHackGet_topics503(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_topicsType[Problem] { val statusCode = 503; val result = resultP; val writer = writerP }) }
    

    private type nakadiHackGet_topicsActionRequestType       = (Unit)
    private type nakadiHackGet_topicsActionType[T]            = nakadiHackGet_topicsActionRequestType => Future[NakadiHackGet_topicsType[T] forSome { type T }]


    val nakadiHackGet_topicsActionConstructor  = Action

def nakadiHackGet_topicsAction[T] = (f: nakadiHackGet_topicsActionType[T]) => nakadiHackGet_topicsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { nakadiHackGet_topicsResponseMimeType =>
            
            

                val result = processValidnakadiHackGet_topicsRequest(f)()(nakadiHackGet_topicsResponseMimeType)
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidnakadiHackGet_topicsRequest[T](f: nakadiHackGet_topicsActionType[T])(request: nakadiHackGet_topicsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait NakadiHackGet_events_from_multiple_partitionsType[T] extends ResultWrapper[T]
    def NakadiHackGet_events_from_multiple_partitions500(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_events_from_multiple_partitionsType[Problem] { val statusCode = 500; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_multiple_partitions500(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_events_from_multiple_partitionsType[Problem] { val statusCode = 500; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_events_from_multiple_partitions404(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_events_from_multiple_partitionsType[Problem] { val statusCode = 404; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_multiple_partitions404(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_events_from_multiple_partitionsType[Problem] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_events_from_multiple_partitions401(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_events_from_multiple_partitionsType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_multiple_partitions401(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_events_from_multiple_partitionsType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_events_from_multiple_partitions400(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackGet_events_from_multiple_partitionsType[Problem] { val statusCode = 400; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_multiple_partitions400(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackGet_events_from_multiple_partitionsType[Problem] { val statusCode = 400; val result = resultP; val writer = writerP }) }
    def NakadiHackGet_events_from_multiple_partitions200(resultP: SimpleStreamEvent)(implicit writerP: String => Option[Writeable[SimpleStreamEvent]]) = success(new NakadiHackGet_events_from_multiple_partitionsType[SimpleStreamEvent] { val statusCode = 200; val result = resultP; val writer = writerP })
    def NakadiHackGet_events_from_multiple_partitions200(resultF: Future[SimpleStreamEvent])(implicit writerP: String => Option[Writeable[SimpleStreamEvent]]) = resultF map { resultP => (new NakadiHackGet_events_from_multiple_partitionsType[SimpleStreamEvent] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type nakadiHackGet_events_from_multiple_partitionsActionRequestType       = (TopicsTopicEventsGetStream_timeout, TopicsTopicEventsGetStream_timeout, TopicsTopicEventsGetStream_timeout, String, Int, TopicsTopicEventsGetStream_timeout, String)
    private type nakadiHackGet_events_from_multiple_partitionsActionType[T]            = nakadiHackGet_events_from_multiple_partitionsActionRequestType => Future[NakadiHackGet_events_from_multiple_partitionsType[T] forSome { type T }]


    val nakadiHackGet_events_from_multiple_partitionsActionConstructor  = Action

def nakadiHackGet_events_from_multiple_partitionsAction[T] = (f: nakadiHackGet_events_from_multiple_partitionsActionType[T]) => (stream_timeout: TopicsTopicEventsGetStream_timeout, stream_limit: TopicsTopicEventsGetStream_timeout, batch_flush_timeout: TopicsTopicEventsGetStream_timeout, batch_limit: Int, batch_keep_alive_limit: TopicsTopicEventsGetStream_timeout, topic: String) => nakadiHackGet_events_from_multiple_partitionsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { nakadiHackGet_events_from_multiple_partitionsResponseMimeType =>
            
            val x_nakadi_cursors: Either[String,String] =
            fromParameters[String]("header")("x_nakadi_cursors", request.headers.toMap, None)
            
            
                (x_nakadi_cursors) match {
                    case (Right(x_nakadi_cursors)) =>
            

                val result =
                        new TopicsTopicEventsGetValidator(stream_timeout, stream_limit, batch_flush_timeout, x_nakadi_cursors, batch_limit, batch_keep_alive_limit, topic).errors match {
                            case e if e.isEmpty => processValidnakadiHackGet_events_from_multiple_partitionsRequest(f)((stream_timeout, stream_limit, batch_flush_timeout, x_nakadi_cursors, batch_limit, batch_keep_alive_limit, topic))(nakadiHackGet_events_from_multiple_partitionsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(nakadiHackGet_events_from_multiple_partitionsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
                case (_) =>
                    val problem: Seq[String] = Seq(x_nakadi_cursors).filter{_.isLeft}.map(_.left.get)
                    val msg = problem.mkString("\n")
                    success(BadRequest(msg))
                }
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidnakadiHackGet_events_from_multiple_partitionsRequest[T](f: nakadiHackGet_events_from_multiple_partitionsActionType[T])(request: nakadiHackGet_events_from_multiple_partitionsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait NakadiHackPost_eventType[T] extends ResultWrapper[T]
    def NakadiHackPost_event403(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackPost_eventType[Problem] { val statusCode = 403; val result = resultP; val writer = writerP })
    def NakadiHackPost_event403(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackPost_eventType[Problem] { val statusCode = 403; val result = resultP; val writer = writerP }) }
    def NakadiHackPost_event503(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackPost_eventType[Problem] { val statusCode = 503; val result = resultP; val writer = writerP })
    def NakadiHackPost_event503(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackPost_eventType[Problem] { val statusCode = 503; val result = resultP; val writer = writerP }) }
    def NakadiHackPost_event401(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackPost_eventType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP })
    def NakadiHackPost_event401(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackPost_eventType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP }) }
    def NakadiHackPost_event422(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackPost_eventType[Problem] { val statusCode = 422; val result = resultP; val writer = writerP })
    def NakadiHackPost_event422(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackPost_eventType[Problem] { val statusCode = 422; val result = resultP; val writer = writerP }) }
    
    def NakadiHackPost_event201(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(201, headers){})
    

    private type nakadiHackPost_eventActionRequestType       = (String, TopicsTopicEventsBatchPostEvent)
    private type nakadiHackPost_eventActionType[T]            = nakadiHackPost_eventActionRequestType => Future[NakadiHackPost_eventType[T] forSome { type T }]

        private def nakadiHackPost_eventParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.optionParser[Event]
            optionParser[Event](bodyMimeType, customParsers, "Invalid TopicsTopicEventsBatchPostEvent", maxLength) _
        }

    val nakadiHackPost_eventActionConstructor  = Action

def nakadiHackPost_eventAction[T] = (f: nakadiHackPost_eventActionType[T]) => (topic: String) => nakadiHackPost_eventActionConstructor.async(BodyParsers.parse.using(nakadiHackPost_eventParser(Seq[String]("application/json")))) { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { nakadiHackPost_eventResponseMimeType =>
            val event = request.body
            
            

                val result =
                        new TopicsTopicEventsPostValidator(topic, event).errors match {
                            case e if e.isEmpty => processValidnakadiHackPost_eventRequest(f)((topic, event))(nakadiHackPost_eventResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(nakadiHackPost_eventResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidnakadiHackPost_eventRequest[T](f: nakadiHackPost_eventActionType[T])(request: nakadiHackPost_eventActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait NakadiHackGet_partitionsType[T] extends ResultWrapper[T]
    def NakadiHackGet_partitions200(resultP: Seq[TopicPartition])(implicit writerP: String => Option[Writeable[Seq[TopicPartition]]]) = success(new NakadiHackGet_partitionsType[Seq[TopicPartition]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def NakadiHackGet_partitions200(resultF: Future[Seq[TopicPartition]])(implicit writerP: String => Option[Writeable[Seq[TopicPartition]]]) = resultF map { resultP => (new NakadiHackGet_partitionsType[Seq[TopicPartition]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type nakadiHackGet_partitionsActionRequestType       = (String)
    private type nakadiHackGet_partitionsActionType[T]            = nakadiHackGet_partitionsActionRequestType => Future[NakadiHackGet_partitionsType[T] forSome { type T }]


    val nakadiHackGet_partitionsActionConstructor  = Action

def nakadiHackGet_partitionsAction[T] = (f: nakadiHackGet_partitionsActionType[T]) => (topic: String) => nakadiHackGet_partitionsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { nakadiHackGet_partitionsResponseMimeType =>
            
            

                val result =
                        new TopicsTopicPartitionsGetValidator(topic).errors match {
                            case e if e.isEmpty => processValidnakadiHackGet_partitionsRequest(f)((topic))(nakadiHackGet_partitionsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(nakadiHackGet_partitionsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidnakadiHackGet_partitionsRequest[T](f: nakadiHackGet_partitionsActionType[T])(request: nakadiHackGet_partitionsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait NakadiHackPost_eventsType[T] extends ResultWrapper[T]
    def NakadiHackPost_events403(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackPost_eventsType[Problem] { val statusCode = 403; val result = resultP; val writer = writerP })
    def NakadiHackPost_events403(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackPost_eventsType[Problem] { val statusCode = 403; val result = resultP; val writer = writerP }) }
    def NakadiHackPost_events503(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackPost_eventsType[Problem] { val statusCode = 503; val result = resultP; val writer = writerP })
    def NakadiHackPost_events503(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackPost_eventsType[Problem] { val statusCode = 503; val result = resultP; val writer = writerP }) }
    def NakadiHackPost_events401(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackPost_eventsType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP })
    def NakadiHackPost_events401(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackPost_eventsType[Problem] { val statusCode = 401; val result = resultP; val writer = writerP }) }
    def NakadiHackPost_events422(resultP: Problem)(implicit writerP: String => Option[Writeable[Problem]]) = success(new NakadiHackPost_eventsType[Problem] { val statusCode = 422; val result = resultP; val writer = writerP })
    def NakadiHackPost_events422(resultF: Future[Problem])(implicit writerP: String => Option[Writeable[Problem]]) = resultF map { resultP => (new NakadiHackPost_eventsType[Problem] { val statusCode = 422; val result = resultP; val writer = writerP }) }
    
    def NakadiHackPost_events201(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(201, headers){})
    

    private type nakadiHackPost_eventsActionRequestType       = (String, TopicsTopicEventsBatchPostEvent)
    private type nakadiHackPost_eventsActionType[T]            = nakadiHackPost_eventsActionRequestType => Future[NakadiHackPost_eventsType[T] forSome { type T }]

        private def nakadiHackPost_eventsParser(acceptedTypes: Seq[String], maxLength: Int = parse.DefaultMaxTextLength) = {
            def bodyMimeType: Option[MediaType] => String = mediaType => {
                val requestType = mediaType.toSeq.map {
                    case m: MediaRange => m
                    case MediaType(a,b,c) => new MediaRange(a,b,c,None,Nil)
                }
                negotiateContent(requestType, acceptedTypes).orElse(acceptedTypes.headOption).getOrElse("application/json")
            }
            
            
            val customParsers = WrappedBodyParsers.optionParser[Event]
            optionParser[Event](bodyMimeType, customParsers, "Invalid TopicsTopicEventsBatchPostEvent", maxLength) _
        }

    val nakadiHackPost_eventsActionConstructor  = Action

def nakadiHackPost_eventsAction[T] = (f: nakadiHackPost_eventsActionType[T]) => (topic: String) => nakadiHackPost_eventsActionConstructor.async(BodyParsers.parse.using(nakadiHackPost_eventsParser(Seq[String]("application/json")))) { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { nakadiHackPost_eventsResponseMimeType =>
            val event = request.body
            
            

                val result =
                        new TopicsTopicEventsBatchPostValidator(topic, event).errors match {
                            case e if e.isEmpty => processValidnakadiHackPost_eventsRequest(f)((topic, event))(nakadiHackPost_eventsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(nakadiHackPost_eventsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidnakadiHackPost_eventsRequest[T](f: nakadiHackPost_eventsActionType[T])(request: nakadiHackPost_eventsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with NakadiHackGet_metricsType[Result] with NakadiHackGet_events_from_single_partitionType[Result] with NakadiHackGet_partitionType[Result] with NakadiHackGet_topicsType[Result] with NakadiHackGet_events_from_multiple_partitionsType[Result] with NakadiHackPost_eventType[Result] with NakadiHackGet_partitionsType[Result] with NakadiHackPost_eventsType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with NakadiHackGet_metricsType[Results.EmptyContent] with NakadiHackGet_events_from_single_partitionType[Results.EmptyContent] with NakadiHackGet_partitionType[Results.EmptyContent] with NakadiHackGet_topicsType[Results.EmptyContent] with NakadiHackGet_events_from_multiple_partitionsType[Results.EmptyContent] with NakadiHackPost_eventType[Results.EmptyContent] with NakadiHackGet_partitionsType[Results.EmptyContent] with NakadiHackPost_eventsType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
