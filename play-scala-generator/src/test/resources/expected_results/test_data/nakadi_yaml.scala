package nakadi.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import java.util.UUID

object Generators extends JsValueGenerators {
    

    
    def createStringGenerator = _generate(StringGenerator)
    def createOptionIntGenerator = _generate(OptionIntGenerator)
    def createIntGenerator = _generate(IntGenerator)
    def createNullGenerator = _generate(NullGenerator)
    def createSeqTopicPartitionGenerator = _generate(SeqTopicPartitionGenerator)
    def createOptionEventGenerator = _generate(OptionEventGenerator)
    def createSeqTopicGenerator = _generate(SeqTopicGenerator)
    

    
    def StringGenerator = arbitrary[String]
    def OptionIntGenerator = Gen.option(arbitrary[Int])
    def IntGenerator = arbitrary[Int]
    def NullGenerator = arbitrary[Null]
    def SeqTopicPartitionGenerator: Gen[List[TopicPartition]] = Gen.containerOf[List,TopicPartition](TopicPartitionGenerator)
    def OptionEventGenerator = Gen.option(EventGenerator)
    def SeqTopicGenerator: Gen[List[Topic]] = Gen.containerOf[List,Topic](TopicGenerator)
    

    def createEventMetaDataGenerator = _generate(EventMetaDataGenerator)
    def createTopicGenerator = _generate(TopicGenerator)
    def createMetricsGenerator = _generate(MetricsGenerator)
    def createEventGenerator = _generate(EventGenerator)
    def createCursorGenerator = _generate(CursorGenerator)
    def createProblemGenerator = _generate(ProblemGenerator)
    def createTopicPartitionGenerator = _generate(TopicPartitionGenerator)
    def createSimpleStreamEventGenerator = _generate(SimpleStreamEventGenerator)


    def EventMetaDataGenerator = for {
        root_id <- Gen.option(arbitrary[UUID])
        parent_id <- Gen.option(arbitrary[UUID])
        scopes <- Gen.option(Gen.containerOf[List,String](arbitrary[String]))
        id <- Gen.option(arbitrary[UUID])
        created <- Gen.option(arbitrary[String])
    } yield EventMetaData(root_id, parent_id, scopes, id, created)
    def TopicGenerator = for {
        name <- arbitrary[String]
    } yield Topic(name)
    def MetricsGenerator = for {
        name <- Gen.option(arbitrary[String])
    } yield Metrics(name)
    def EventGenerator = for {
        event_type <- Gen.option(arbitrary[String])
        partitioning_key <- Gen.option(arbitrary[String])
        metadata <- Gen.option(EventMetaDataGenerator)
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
        events <- Gen.option(Gen.containerOf[List,Event](EventGenerator))
    } yield SimpleStreamEvent(cursor, events)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    
    implicit lazy val arbUUID: Arbitrary[UUID] = Arbitrary(Gen.uuid)
    

}