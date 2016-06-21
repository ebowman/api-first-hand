package nakadi.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.ArrayWrapper
import java.util.UUID

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createTopicsTopicEventsGetStream_timeoutGenerator = _generate(TopicsTopicEventsGetStream_timeoutGenerator)
    def createIntGenerator = _generate(IntGenerator)
    def createEventEvent_typeGenerator = _generate(EventEvent_typeGenerator)
    def createSimpleStreamEventEventsOptGenerator = _generate(SimpleStreamEventEventsOptGenerator)
    def createEventMetaDataParent_idGenerator = _generate(EventMetaDataParent_idGenerator)
    def createEventMetadataGenerator = _generate(EventMetadataGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createEventMetaDataScopesOptGenerator = _generate(EventMetaDataScopesOptGenerator)
    def createTopicsTopicPartitionsGetResponses200Generator = _generate(TopicsTopicPartitionsGetResponses200Generator)
    def createTopicsTopicEventsBatchPostEventGenerator = _generate(TopicsTopicEventsBatchPostEventGenerator)
    def createSimpleStreamEventEventsGenerator = _generate(SimpleStreamEventEventsGenerator)
    def createEventMetaDataScopesGenerator = _generate(EventMetaDataScopesGenerator)
    def createTopicsGetResponses200Generator = _generate(TopicsGetResponses200Generator)
    

    
    def StringGenerator = arbitrary[String]
    def TopicsTopicEventsGetStream_timeoutGenerator = Gen.option(arbitrary[Int])
    def IntGenerator = arbitrary[Int]
    def EventEvent_typeGenerator = Gen.option(arbitrary[String])
    def SimpleStreamEventEventsOptGenerator = _genList(EventGenerator, "csv")
    def EventMetaDataParent_idGenerator = Gen.option(arbitrary[UUID])
    def EventMetadataGenerator = Gen.option(EventMetaDataNameClashGenerator)
    def NullGenerator = arbitrary[Null]
    def EventMetaDataScopesOptGenerator = _genList(arbitrary[String], "csv")
    def TopicsTopicPartitionsGetResponses200Generator = Gen.containerOf[List,TopicPartition](TopicPartitionGenerator)
    def TopicsTopicEventsBatchPostEventGenerator = Gen.option(EventGenerator)
    def SimpleStreamEventEventsGenerator = Gen.option(SimpleStreamEventEventsOptGenerator)
    def EventMetaDataScopesGenerator = Gen.option(EventMetaDataScopesOptGenerator)
    def TopicsGetResponses200Generator = Gen.containerOf[List,Topic](TopicGenerator)
    

    def createEventMetaDataNameClashGenerator = _generate(EventMetaDataNameClashGenerator)
    def createTopicGenerator = _generate(TopicGenerator)
    def createMetricsGenerator = _generate(MetricsGenerator)
    def createEventGenerator = _generate(EventGenerator)
    def createCursorGenerator = _generate(CursorGenerator)
    def createProblemGenerator = _generate(ProblemGenerator)
    def createTopicPartitionGenerator = _generate(TopicPartitionGenerator)
    def createSimpleStreamEventGenerator = _generate(SimpleStreamEventGenerator)


    def EventMetaDataNameClashGenerator = for {
        root_id <- EventMetaDataParent_idGenerator
        parent_id <- EventMetaDataParent_idGenerator
        scopes <- EventMetaDataScopesGenerator
        id <- EventMetaDataParent_idGenerator
        created <- EventEvent_typeGenerator
    } yield EventMetaDataNameClash(root_id, parent_id, scopes, id, created)
    def TopicGenerator = for {
        name <- arbitrary[String]
    } yield Topic(name)
    def MetricsGenerator = for {
        name <- EventEvent_typeGenerator
    } yield Metrics(name)
    def EventGenerator = for {
        event_type <- EventEvent_typeGenerator
        partitioning_key <- EventEvent_typeGenerator
        metadata <- EventMetadataGenerator
    } yield Event(event_type, partitioning_key, metadata)
    def CursorGenerator = for {
        partition <- arbitrary[String]
        offset <- arbitrary[String]
    } yield Cursor(partition, offset)
    def ProblemGenerator = for {
        detail <- arbitrary[String]
    } yield Problem(detail)
    def TopicPartitionGenerator = for {
        partition <- arbitrary[String]
        oldest_available_offset <- arbitrary[String]
        newest_available_offset <- arbitrary[String]
    } yield TopicPartition(partition, oldest_available_offset, newest_available_offset)
    def SimpleStreamEventGenerator = for {
        cursor <- CursorGenerator
        events <- SimpleStreamEventEventsGenerator
    } yield SimpleStreamEvent(cursor, events)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    
    
    
    implicit lazy val arbUUID: Arbitrary[UUID] = Arbitrary(Gen.uuid)
    

}