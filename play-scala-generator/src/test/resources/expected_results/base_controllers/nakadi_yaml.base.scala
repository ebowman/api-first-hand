package nakadi.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import java.util.UUID

import de.zalando.play.controllers.PlayPathBindables




//noinspection ScalaStyle
trait NakadiYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
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

def nakadiHackGet_metricsAction[T] = (f: nakadiHackGet_metricsActionType[T]) => nakadiHackGet_metricsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidnakadiHackGet_metricsRequest(): Either[Result, Future[NakadiHackGet_metricsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f())
            apiFirstTempResultHolder
        }

            
            

            processValidnakadiHackGet_metricsRequest() match {
                case Left(l) => success(l)
                case Right(r: Future[NakadiHackGet_metricsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { nakadiHackGet_metricsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(nakadiHackGet_metricsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
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

def nakadiHackGet_events_from_single_partitionAction[T] = (f: nakadiHackGet_events_from_single_partitionActionType[T]) => (start_from: String, partition: String, stream_limit: TopicsTopicEventsGetStream_timeout, topic: String, batch_limit: Int, batch_flush_timeout: TopicsTopicEventsGetStream_timeout, stream_timeout: TopicsTopicEventsGetStream_timeout, batch_keep_alive_limit: TopicsTopicEventsGetStream_timeout) => nakadiHackGet_events_from_single_partitionActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidnakadiHackGet_events_from_single_partitionRequest(start_from: String, partition: String, stream_limit: TopicsTopicEventsGetStream_timeout, topic: String, batch_limit: Int, batch_flush_timeout: TopicsTopicEventsGetStream_timeout, stream_timeout: TopicsTopicEventsGetStream_timeout, batch_keep_alive_limit: TopicsTopicEventsGetStream_timeout): Either[Result, Future[NakadiHackGet_events_from_single_partitionType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((start_from, partition, stream_limit, topic, batch_limit, batch_flush_timeout, stream_timeout, batch_keep_alive_limit)))
            
            new TopicsTopicPartitionsPartitionEventsGetValidator(start_from, partition, stream_limit, topic, batch_limit, batch_flush_timeout, stream_timeout, batch_keep_alive_limit).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidnakadiHackGet_events_from_single_partitionRequest(start_from, partition, stream_limit, topic, batch_limit, batch_flush_timeout, stream_timeout, batch_keep_alive_limit) match {
                case Left(l) => success(l)
                case Right(r: Future[NakadiHackGet_events_from_single_partitionType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { nakadiHackGet_events_from_single_partitionResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(nakadiHackGet_events_from_single_partitionResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait NakadiHackGet_partitionType[T] extends ResultWrapper[T]
    def NakadiHackGet_partition200(resultP: TopicPartition)(implicit writerP: String => Option[Writeable[TopicPartition]]) = success(new NakadiHackGet_partitionType[TopicPartition] { val statusCode = 200; val result = resultP; val writer = writerP })
    def NakadiHackGet_partition200(resultF: Future[TopicPartition])(implicit writerP: String => Option[Writeable[TopicPartition]]) = resultF map { resultP => (new NakadiHackGet_partitionType[TopicPartition] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type nakadiHackGet_partitionActionRequestType       = (String, String)
    private type nakadiHackGet_partitionActionType[T]            = nakadiHackGet_partitionActionRequestType => Future[NakadiHackGet_partitionType[T] forSome { type T }]


    val nakadiHackGet_partitionActionConstructor  = Action

def nakadiHackGet_partitionAction[T] = (f: nakadiHackGet_partitionActionType[T]) => (topic: String, partition: String) => nakadiHackGet_partitionActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidnakadiHackGet_partitionRequest(topic: String, partition: String): Either[Result, Future[NakadiHackGet_partitionType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((topic, partition)))
            
            new TopicsTopicPartitionsPartitionGetValidator(topic, partition).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidnakadiHackGet_partitionRequest(topic, partition) match {
                case Left(l) => success(l)
                case Right(r: Future[NakadiHackGet_partitionType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { nakadiHackGet_partitionResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(nakadiHackGet_partitionResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
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

def nakadiHackGet_topicsAction[T] = (f: nakadiHackGet_topicsActionType[T]) => nakadiHackGet_topicsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidnakadiHackGet_topicsRequest(): Either[Result, Future[NakadiHackGet_topicsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f())
            apiFirstTempResultHolder
        }

            
            

            processValidnakadiHackGet_topicsRequest() match {
                case Left(l) => success(l)
                case Right(r: Future[NakadiHackGet_topicsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { nakadiHackGet_topicsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(nakadiHackGet_topicsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
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

def nakadiHackGet_events_from_multiple_partitionsAction[T] = (f: nakadiHackGet_events_from_multiple_partitionsActionType[T]) => (stream_timeout: TopicsTopicEventsGetStream_timeout, stream_limit: TopicsTopicEventsGetStream_timeout, batch_flush_timeout: TopicsTopicEventsGetStream_timeout, batch_limit: Int, batch_keep_alive_limit: TopicsTopicEventsGetStream_timeout, topic: String) => nakadiHackGet_events_from_multiple_partitionsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidnakadiHackGet_events_from_multiple_partitionsRequest(stream_timeout: TopicsTopicEventsGetStream_timeout, stream_limit: TopicsTopicEventsGetStream_timeout, batch_flush_timeout: TopicsTopicEventsGetStream_timeout, x_nakadi_cursors: String, batch_limit: Int, batch_keep_alive_limit: TopicsTopicEventsGetStream_timeout, topic: String): Either[Result, Future[NakadiHackGet_events_from_multiple_partitionsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((stream_timeout, stream_limit, batch_flush_timeout, x_nakadi_cursors, batch_limit, batch_keep_alive_limit, topic)))
            
            new TopicsTopicEventsGetValidator(stream_timeout, stream_limit, batch_flush_timeout, x_nakadi_cursors, batch_limit, batch_keep_alive_limit, topic).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            val x_nakadi_cursors: Either[String,String] = fromParameters[String]("header")("x_nakadi_cursors", request.headers.toMap, None)
            
            
                (x_nakadi_cursors) match {
                    case (Right(x_nakadi_cursors)) =>
            

            processValidnakadiHackGet_events_from_multiple_partitionsRequest(stream_timeout, stream_limit, batch_flush_timeout, x_nakadi_cursors, batch_limit, batch_keep_alive_limit, topic) match {
                case Left(l) => success(l)
                case Right(r: Future[NakadiHackGet_events_from_multiple_partitionsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { nakadiHackGet_events_from_multiple_partitionsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(nakadiHackGet_events_from_multiple_partitionsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
                case (_) =>
                    val problem: Seq[String] = Seq(x_nakadi_cursors).filter{_.isLeft}.map(_.left.get)
                    val msg = problem.mkString("\n")
                    success(BadRequest(msg))
                }
            
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

        
        import BodyReads._
        
        val nakadiHackPost_eventParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.asOpt[Event])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val nakadiHackPost_eventActionConstructor  = Action

def nakadiHackPost_eventAction[T] = (f: nakadiHackPost_eventActionType[T]) => (topic: String) => nakadiHackPost_eventActionConstructor.async(nakadiHackPost_eventParser) { implicit request: Request[TopicsTopicEventsBatchPostEvent] =>

        def processValidnakadiHackPost_eventRequest(topic: String, event: TopicsTopicEventsBatchPostEvent): Either[Result, Future[NakadiHackPost_eventType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((topic, event)))
            
            new TopicsTopicEventsPostValidator(topic, event).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val event: TopicsTopicEventsBatchPostEvent = request.body
            
            

            processValidnakadiHackPost_eventRequest(topic, event) match {
                case Left(l) => success(l)
                case Right(r: Future[NakadiHackPost_eventType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { nakadiHackPost_eventResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(nakadiHackPost_eventResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait NakadiHackGet_partitionsType[T] extends ResultWrapper[T]
    def NakadiHackGet_partitions200(resultP: Seq[TopicPartition])(implicit writerP: String => Option[Writeable[Seq[TopicPartition]]]) = success(new NakadiHackGet_partitionsType[Seq[TopicPartition]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def NakadiHackGet_partitions200(resultF: Future[Seq[TopicPartition]])(implicit writerP: String => Option[Writeable[Seq[TopicPartition]]]) = resultF map { resultP => (new NakadiHackGet_partitionsType[Seq[TopicPartition]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type nakadiHackGet_partitionsActionRequestType       = (String)
    private type nakadiHackGet_partitionsActionType[T]            = nakadiHackGet_partitionsActionRequestType => Future[NakadiHackGet_partitionsType[T] forSome { type T }]


    val nakadiHackGet_partitionsActionConstructor  = Action

def nakadiHackGet_partitionsAction[T] = (f: nakadiHackGet_partitionsActionType[T]) => (topic: String) => nakadiHackGet_partitionsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidnakadiHackGet_partitionsRequest(topic: String): Either[Result, Future[NakadiHackGet_partitionsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((topic)))
            
            new TopicsTopicPartitionsGetValidator(topic).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidnakadiHackGet_partitionsRequest(topic) match {
                case Left(l) => success(l)
                case Right(r: Future[NakadiHackGet_partitionsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { nakadiHackGet_partitionsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(nakadiHackGet_partitionsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
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

        
        import BodyReads._
        
        val nakadiHackPost_eventsParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                case Some("application/json") => play.api.mvc.BodyParsers.parse.tolerantJson.map(_.asOpt[Event])
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val nakadiHackPost_eventsActionConstructor  = Action

def nakadiHackPost_eventsAction[T] = (f: nakadiHackPost_eventsActionType[T]) => (topic: String) => nakadiHackPost_eventsActionConstructor.async(nakadiHackPost_eventsParser) { implicit request: Request[TopicsTopicEventsBatchPostEvent] =>

        def processValidnakadiHackPost_eventsRequest(topic: String, event: TopicsTopicEventsBatchPostEvent): Either[Result, Future[NakadiHackPost_eventsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((topic, event)))
            
            new TopicsTopicEventsBatchPostValidator(topic, event).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val event: TopicsTopicEventsBatchPostEvent = request.body
            
            

            processValidnakadiHackPost_eventsRequest(topic, event) match {
                case Left(l) => success(l)
                case Right(r: Future[NakadiHackPost_eventsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { nakadiHackPost_eventsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(nakadiHackPost_eventsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with NakadiHackGet_metricsType[Result] with NakadiHackGet_events_from_single_partitionType[Result] with NakadiHackGet_partitionType[Result] with NakadiHackGet_topicsType[Result] with NakadiHackGet_events_from_multiple_partitionsType[Result] with NakadiHackPost_eventType[Result] with NakadiHackGet_partitionsType[Result] with NakadiHackPost_eventsType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with NakadiHackGet_metricsType[Results.EmptyContent] with NakadiHackGet_events_from_single_partitionType[Results.EmptyContent] with NakadiHackGet_partitionType[Results.EmptyContent] with NakadiHackGet_topicsType[Results.EmptyContent] with NakadiHackGet_events_from_multiple_partitionsType[Results.EmptyContent] with NakadiHackPost_eventType[Results.EmptyContent] with NakadiHackGet_partitionsType[Results.EmptyContent] with NakadiHackPost_eventsType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
