package nakadi.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.ArrayWrapper
import java.util.UUID
// ----- constraints and wrapper validations -----
class TopicsTopicEventsBatchPostTopicConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicEventsBatchPostTopicValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicEventsBatchPostTopicConstraints(instance))
}
class TopicsTopicEventsGetStream_timeoutOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq()
}
class TopicsTopicEventsGetStream_timeoutOptValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicEventsGetStream_timeoutOptConstraints(instance))
}
class TopicsTopicPartitionsPartitionEventsGetStart_fromConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicPartitionsPartitionEventsGetStart_fromValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicPartitionsPartitionEventsGetStart_fromConstraints(instance))
}
class TopicsTopicEventsGetBatch_limitConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq()
}
class TopicsTopicEventsGetBatch_limitValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicEventsGetBatch_limitConstraints(instance))
}
class TopicsTopicPartitionsGetTopicConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicPartitionsGetTopicValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicPartitionsGetTopicConstraints(instance))
}
class EventEvent_typeOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class EventEvent_typeOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new EventEvent_typeOptConstraints(instance))
}
class EventMetaDataParent_idOptConstraints(override val instance: UUID) extends ValidationBase[UUID] {
    override def constraints: Seq[Constraint[UUID]] =
        Seq()
}
class EventMetaDataParent_idOptValidator(instance: UUID) extends RecursiveValidator {
    override val validators = Seq(new EventMetaDataParent_idOptConstraints(instance))
}
class EventMetaDataScopesOptArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class EventMetaDataScopesOptArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new EventMetaDataScopesOptArrConstraints(instance))
}
class TopicsTopicPartitionsPartitionGetTopicConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicPartitionsPartitionGetTopicValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicPartitionsPartitionGetTopicConstraints(instance))
}
class TopicsTopicEventsGetX_nakadi_cursorsConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicEventsGetX_nakadi_cursorsValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicEventsGetX_nakadi_cursorsConstraints(instance))
}
class TopicsTopicEventsPostTopicConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicEventsPostTopicValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicEventsPostTopicConstraints(instance))
}
class TopicsTopicEventsGetTopicConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicEventsGetTopicValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicEventsGetTopicConstraints(instance))
}
class TopicsTopicPartitionsPartitionEventsGetTopicConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicPartitionsPartitionEventsGetTopicValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicPartitionsPartitionEventsGetTopicConstraints(instance))
}
class TopicsTopicPartitionsPartitionEventsGetPartitionConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicPartitionsPartitionEventsGetPartitionValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicPartitionsPartitionEventsGetPartitionConstraints(instance))
}
class TopicsTopicPartitionsPartitionGetPartitionConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TopicsTopicPartitionsPartitionGetPartitionValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicPartitionsPartitionGetPartitionConstraints(instance))
}
class TopicsTopicPartitionsPartitionEventsGetBatch_limitConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq()
}
class TopicsTopicPartitionsPartitionEventsGetBatch_limitValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new TopicsTopicPartitionsPartitionEventsGetBatch_limitConstraints(instance))
}
// ----- complex type validators -----
class EventValidator(instance: Event) extends RecursiveValidator {
    override val validators = Seq(
        new EventEvent_typeValidator(instance.event_type), 
        new EventEvent_typeValidator(instance.partitioning_key), 
        new EventMetadataValidator(instance.metadata)
    )
}
class EventMetaDataValidator(instance: EventMetaDataNameClash) extends RecursiveValidator {
    override val validators = Seq(
        new EventMetaDataParent_idValidator(instance.root_id), 
        new EventMetaDataParent_idValidator(instance.parent_id), 
        new EventMetaDataScopesValidator(instance.scopes), 
        new EventMetaDataParent_idValidator(instance.id), 
        new EventEvent_typeValidator(instance.created)
    )
}
// ----- option delegating validators -----
class TopicsTopicEventsGetStream_timeoutValidator(instance: TopicsTopicEventsGetStream_timeout) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new TopicsTopicEventsGetStream_timeoutOptValidator(_) }
}
class TopicsTopicEventsBatchPostEventValidator(instance: TopicsTopicEventsBatchPostEvent) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new EventValidator(_) }
}
class EventEvent_typeValidator(instance: EventEvent_type) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new EventEvent_typeOptValidator(_) }
}
class EventMetadataValidator(instance: EventMetadata) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new EventMetaDataValidator(_) }
}
class EventMetaDataParent_idValidator(instance: EventMetaDataParent_id) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new EventMetaDataParent_idOptValidator(_) }
}
class EventMetaDataScopesValidator(instance: EventMetaDataScopes) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new EventMetaDataScopesOptValidator(_) }
}
// ----- array delegating validators -----
class EventMetaDataScopesOptConstraints(override val instance: EventMetaDataScopesOpt) extends ValidationBase[EventMetaDataScopesOpt] {
    override def constraints: Seq[Constraint[EventMetaDataScopesOpt]] =
        Seq()
}
class EventMetaDataScopesOptValidator(instance: EventMetaDataScopesOpt) extends RecursiveValidator {
    override val validators = new EventMetaDataScopesOptConstraints(instance) +: instance.map { new EventMetaDataScopesOptArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- call validations -----
class TopicsTopicPartitionsPartitionGetValidator(topic: String, partition: String) extends RecursiveValidator {
    override val validators = Seq(
        new TopicsTopicPartitionsPartitionGetTopicValidator(topic), 
    
        new TopicsTopicPartitionsPartitionGetPartitionValidator(partition)
    
    )
}
class TopicsTopicEventsGetValidator(stream_timeout: TopicsTopicEventsGetStream_timeout, stream_limit: TopicsTopicEventsGetStream_timeout, batch_flush_timeout: TopicsTopicEventsGetStream_timeout, x_nakadi_cursors: String, batch_limit: Int, batch_keep_alive_limit: TopicsTopicEventsGetStream_timeout, topic: String) extends RecursiveValidator {
    override val validators = Seq(
        new TopicsTopicEventsGetStream_timeoutValidator(stream_timeout), 
    
        new TopicsTopicEventsGetStream_timeoutValidator(stream_limit), 
    
        new TopicsTopicEventsGetStream_timeoutValidator(batch_flush_timeout), 
    
        new TopicsTopicEventsGetX_nakadi_cursorsValidator(x_nakadi_cursors), 
    
        new TopicsTopicEventsGetBatch_limitValidator(batch_limit), 
    
        new TopicsTopicEventsGetStream_timeoutValidator(batch_keep_alive_limit), 
    
        new TopicsTopicEventsGetTopicValidator(topic)
    
    )
}
class TopicsTopicPartitionsPartitionEventsGetValidator(start_from: String, partition: String, stream_limit: TopicsTopicEventsGetStream_timeout, topic: String, batch_limit: Int, batch_flush_timeout: TopicsTopicEventsGetStream_timeout, stream_timeout: TopicsTopicEventsGetStream_timeout, batch_keep_alive_limit: TopicsTopicEventsGetStream_timeout) extends RecursiveValidator {
    override val validators = Seq(
        new TopicsTopicPartitionsPartitionEventsGetStart_fromValidator(start_from), 
    
        new TopicsTopicPartitionsPartitionEventsGetPartitionValidator(partition), 
    
        new TopicsTopicEventsGetStream_timeoutValidator(stream_limit), 
    
        new TopicsTopicPartitionsPartitionEventsGetTopicValidator(topic), 
    
        new TopicsTopicPartitionsPartitionEventsGetBatch_limitValidator(batch_limit), 
    
        new TopicsTopicEventsGetStream_timeoutValidator(batch_flush_timeout), 
    
        new TopicsTopicEventsGetStream_timeoutValidator(stream_timeout), 
    
        new TopicsTopicEventsGetStream_timeoutValidator(batch_keep_alive_limit)
    
    )
}
class TopicsTopicEventsPostValidator(topic: String, event: TopicsTopicEventsBatchPostEvent) extends RecursiveValidator {
    override val validators = Seq(
        new TopicsTopicEventsPostTopicValidator(topic), 
    
        new TopicsTopicEventsBatchPostEventValidator(event)
    
    )
}
class TopicsTopicPartitionsGetValidator(topic: String) extends RecursiveValidator {
    override val validators = Seq(
        new TopicsTopicPartitionsGetTopicValidator(topic)
    
    )
}
class TopicsTopicEventsBatchPostValidator(topic: String, event: TopicsTopicEventsBatchPostEvent) extends RecursiveValidator {
    override val validators = Seq(
        new TopicsTopicEventsBatchPostTopicValidator(topic), 
    
        new TopicsTopicEventsBatchPostEventValidator(event)
    
    )
}
