package split.petstore.api.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import de.zalando.play.controllers.ArrayWrapper
import org.joda.time.DateTime
// ----- constraints and wrapper validations -----
class UsersUsernameGetUsernameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUsernameGetUsernameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUsernameGetUsernameConstraints(instance))
}
class PetsPetIdPostStatusConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetsPetIdPostStatusValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetsPetIdPostStatusConstraints(instance))
}
class OrderStatusOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class OrderStatusOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new OrderStatusOptConstraints(instance))
}
class PetNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetNameConstraints(instance))
}
class OrderPetIdOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class OrderPetIdOptValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new OrderPetIdOptConstraints(instance))
}
class PetPhotoUrlsArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetPhotoUrlsArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetPhotoUrlsArrConstraints(instance))
}
class OrderQuantityOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq()
}
class OrderQuantityOptValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new OrderQuantityOptConstraints(instance))
}
class OrderShipDateOptConstraints(override val instance: DateTime) extends ValidationBase[DateTime] {
    override def constraints: Seq[Constraint[DateTime]] =
        Seq()
}
class OrderShipDateOptValidator(instance: DateTime) extends RecursiveValidator {
    override val validators = Seq(new OrderShipDateOptConstraints(instance))
}
class OrderCompleteOptConstraints(override val instance: Boolean) extends ValidationBase[Boolean] {
    override def constraints: Seq[Constraint[Boolean]] =
        Seq()
}
class OrderCompleteOptValidator(instance: Boolean) extends RecursiveValidator {
    override val validators = Seq(new OrderCompleteOptConstraints(instance))
}
class PetsPetIdPostPetIdConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetsPetIdPostPetIdValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetsPetIdPostPetIdConstraints(instance))
}
class PetsPetIdDeletePetIdConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class PetsPetIdDeletePetIdValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new PetsPetIdDeletePetIdConstraints(instance))
}
class PetsPetIdDeleteApi_keyConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetsPetIdDeleteApi_keyValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetsPetIdDeleteApi_keyConstraints(instance))
}
class PetsPetIdPostNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetsPetIdPostNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetsPetIdPostNameConstraints(instance))
}
class UsersUsernamePutUsernameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUsernamePutUsernameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUsernamePutUsernameConstraints(instance))
}
class PetsFindByStatusGetStatusOptArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetsFindByStatusGetStatusOptArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetsFindByStatusGetStatusOptArrConstraints(instance))
}
class PetsPetIdGetPetIdConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class PetsPetIdGetPetIdValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new PetsPetIdGetPetIdConstraints(instance))
}
class StoresOrderOrderIdDeleteOrderIdConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class StoresOrderOrderIdDeleteOrderIdValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new StoresOrderOrderIdDeleteOrderIdConstraints(instance))
}
class StoresOrderOrderIdGetOrderIdConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class StoresOrderOrderIdGetOrderIdValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new StoresOrderOrderIdGetOrderIdConstraints(instance))
}
class UsersUsernameDeleteUsernameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersUsernameDeleteUsernameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersUsernameDeleteUsernameConstraints(instance))
}
// ----- complex type validators -----
class PetsPostBodyOptValidator(instance: Pet) extends RecursiveValidator {
    override val validators = Seq(
        new PetNameValidator(instance.name), 
        new PetTagsValidator(instance.tags), 
        new PetPhotoUrlsValidator(instance.photoUrls), 
        new OrderPetIdValidator(instance.id), 
        new OrderStatusValidator(instance.status), 
        new PetCategoryValidator(instance.category)
    )
}
class PetCategoryOptValidator(instance: PetCategoryOpt) extends RecursiveValidator {
    override val validators = Seq(
        new OrderPetIdValidator(instance.id), 
        new OrderStatusValidator(instance.name)
    )
}
class UsersUsernamePutBodyOptValidator(instance: User) extends RecursiveValidator {
    override val validators = Seq(
        new OrderStatusValidator(instance.email), 
        new OrderStatusValidator(instance.username), 
        new OrderQuantityValidator(instance.userStatus), 
        new OrderStatusValidator(instance.lastName), 
        new OrderStatusValidator(instance.firstName), 
        new OrderPetIdValidator(instance.id), 
        new OrderStatusValidator(instance.phone), 
        new OrderStatusValidator(instance.password)
    )
}
class StoresOrderPostBodyOptValidator(instance: Order) extends RecursiveValidator {
    override val validators = Seq(
        new OrderShipDateValidator(instance.shipDate), 
        new OrderQuantityValidator(instance.quantity), 
        new OrderPetIdValidator(instance.petId), 
        new OrderPetIdValidator(instance.id), 
        new OrderCompleteValidator(instance.complete), 
        new OrderStatusValidator(instance.status)
    )
}
// ----- option delegating validators -----
class OrderStatusValidator(instance: OrderStatus) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderStatusOptValidator(_) }
}
class PetsPostBodyValidator(instance: PetsPostBody) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetsPostBodyOptValidator(_) }
}
class PetTagsValidator(instance: PetTags) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetTagsOptValidator(_) }
}
class OrderPetIdValidator(instance: OrderPetId) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderPetIdOptValidator(_) }
}
class PetCategoryValidator(instance: PetCategory) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetCategoryOptValidator(_) }
}
class UsersUsernamePutBodyValidator(instance: UsersUsernamePutBody) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersUsernamePutBodyOptValidator(_) }
}
class OrderQuantityValidator(instance: OrderQuantity) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderQuantityOptValidator(_) }
}
class StoresOrderPostBodyValidator(instance: StoresOrderPostBody) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new StoresOrderPostBodyOptValidator(_) }
}
class OrderShipDateValidator(instance: OrderShipDate) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderShipDateOptValidator(_) }
}
class OrderCompleteValidator(instance: OrderComplete) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderCompleteOptValidator(_) }
}
class UsersCreateWithListPostBodyValidator(instance: UsersCreateWithListPostBody) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersCreateWithListPostBodyOptValidator(_) }
}
class PetsFindByStatusGetStatusValidator(instance: PetsFindByStatusGetStatus) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetsFindByStatusGetStatusOptValidator(_) }
}
// ----- array delegating validators -----
class PetTagsOptConstraints(override val instance: PetTagsOpt) extends ValidationBase[PetTagsOpt] {
    override def constraints: Seq[Constraint[PetTagsOpt]] =
        Seq()
}
class PetTagsOptValidator(instance: PetTagsOpt) extends RecursiveValidator {
    override val validators = new PetTagsOptConstraints(instance) +: instance.map { new PetCategoryOptValidator(_)}
}
class PetPhotoUrlsConstraints(override val instance: PetPhotoUrls) extends ValidationBase[PetPhotoUrls] {
    override def constraints: Seq[Constraint[PetPhotoUrls]] =
        Seq()
}
class PetPhotoUrlsValidator(instance: PetPhotoUrls) extends RecursiveValidator {
    override val validators = new PetPhotoUrlsConstraints(instance) +: instance.map { new PetPhotoUrlsArrValidator(_)}
}
class UsersCreateWithListPostBodyOptConstraints(override val instance: UsersCreateWithListPostBodyOpt) extends ValidationBase[UsersCreateWithListPostBodyOpt] {
    override def constraints: Seq[Constraint[UsersCreateWithListPostBodyOpt]] =
        Seq()
}
class UsersCreateWithListPostBodyOptValidator(instance: UsersCreateWithListPostBodyOpt) extends RecursiveValidator {
    override val validators = new UsersCreateWithListPostBodyOptConstraints(instance) +: instance.map { new UsersUsernamePutBodyOptValidator(_)}
}
class PetsFindByStatusGetStatusOptConstraints(override val instance: PetsFindByStatusGetStatusOpt) extends ValidationBase[PetsFindByStatusGetStatusOpt] {
    override def constraints: Seq[Constraint[PetsFindByStatusGetStatusOpt]] =
        Seq()
}
class PetsFindByStatusGetStatusOptValidator(instance: PetsFindByStatusGetStatusOpt) extends RecursiveValidator {
    override val validators = new PetsFindByStatusGetStatusOptConstraints(instance) +: instance.map { new PetsFindByStatusGetStatusOptArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- call validations -----
class UsersPostValidator(body: UsersUsernamePutBody) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUsernamePutBodyValidator(body)
    
    )
}
class PetsPostValidator(body: PetsPostBody) extends RecursiveValidator {
    override val validators = Seq(
        new PetsPostBodyValidator(body)
    
    )
}
class PetsPutValidator(body: PetsPostBody) extends RecursiveValidator {
    override val validators = Seq(
        new PetsPostBodyValidator(body)
    
    )
}
class PetsFindByStatusGetValidator(status: PetsFindByStatusGetStatus) extends RecursiveValidator {
    override val validators = Seq(
        new PetsFindByStatusGetStatusValidator(status)
    
    )
}
class StoresOrderPostValidator(body: StoresOrderPostBody) extends RecursiveValidator {
    override val validators = Seq(
        new StoresOrderPostBodyValidator(body)
    
    )
}
class UsersCreateWithArrayPostValidator(body: UsersCreateWithListPostBody) extends RecursiveValidator {
    override val validators = Seq(
        new UsersCreateWithListPostBodyValidator(body)
    
    )
}
class UsersLoginGetValidator(username: OrderStatus, password: OrderStatus) extends RecursiveValidator {
    override val validators = Seq(
        new OrderStatusValidator(username), 
    
        new OrderStatusValidator(password)
    
    )
}
class StoresOrderOrderIdGetValidator(orderId: String) extends RecursiveValidator {
    override val validators = Seq(
        new StoresOrderOrderIdGetOrderIdValidator(orderId)
    
    )
}
class PetsPetIdGetValidator(petId: Long) extends RecursiveValidator {
    override val validators = Seq(
        new PetsPetIdGetPetIdValidator(petId)
    
    )
}
class UsersUsernameGetValidator(username: String) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUsernameGetUsernameValidator(username)
    
    )
}
class UsersCreateWithListPostValidator(body: UsersCreateWithListPostBody) extends RecursiveValidator {
    override val validators = Seq(
        new UsersCreateWithListPostBodyValidator(body)
    
    )
}
class PetsPetIdPostValidator(petId: String, name: String, status: String) extends RecursiveValidator {
    override val validators = Seq(
        new PetsPetIdPostPetIdValidator(petId), 
    
        new PetsPetIdPostNameValidator(name), 
    
        new PetsPetIdPostStatusValidator(status)
    
    )
}
class UsersUsernameDeleteValidator(username: String) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUsernameDeleteUsernameValidator(username)
    
    )
}
class StoresOrderOrderIdDeleteValidator(orderId: String) extends RecursiveValidator {
    override val validators = Seq(
        new StoresOrderOrderIdDeleteOrderIdValidator(orderId)
    
    )
}
class PetsPetIdDeleteValidator(api_key: String, petId: Long) extends RecursiveValidator {
    override val validators = Seq(
        new PetsPetIdDeleteApi_keyValidator(api_key), 
    
        new PetsPetIdDeletePetIdValidator(petId)
    
    )
}
class PetsFindByTagsGetValidator(tags: PetsFindByStatusGetStatus) extends RecursiveValidator {
    override val validators = Seq(
        new PetsFindByStatusGetStatusValidator(tags)
    
    )
}
class UsersUsernamePutValidator(username: String, body: UsersUsernamePutBody) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUsernamePutUsernameValidator(username), 
    
        new UsersUsernamePutBodyValidator(body)
    
    )
}
