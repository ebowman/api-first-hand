package full.petstore.api.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import java.time.ZonedDateTime
import de.zalando.play.controllers.ArrayWrapper
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
class UsersLoginGetUsernameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UsersLoginGetUsernameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UsersLoginGetUsernameOptConstraints(instance))
}
class PetNameConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetNameValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetNameConstraints(instance))
}
class PetPhotoUrlsArrConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetPhotoUrlsArrValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetPhotoUrlsArrConstraints(instance))
}
class PetIdOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class PetIdOptValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new PetIdOptConstraints(instance))
}
class PetStatusOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class PetStatusOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new PetStatusOptConstraints(instance))
}
class TagIdOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class TagIdOptValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new TagIdOptConstraints(instance))
}
class TagNameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class TagNameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new TagNameOptConstraints(instance))
}
class UserEmailOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserEmailOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserEmailOptConstraints(instance))
}
class UserUsernameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserUsernameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserUsernameOptConstraints(instance))
}
class UserUserStatusOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq()
}
class UserUserStatusOptValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new UserUserStatusOptConstraints(instance))
}
class UserLastNameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserLastNameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserLastNameOptConstraints(instance))
}
class UserFirstNameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserFirstNameOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserFirstNameOptConstraints(instance))
}
class UserIdOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class UserIdOptValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new UserIdOptConstraints(instance))
}
class UserPhoneOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserPhoneOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserPhoneOptConstraints(instance))
}
class UserPasswordOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserPasswordOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new UserPasswordOptConstraints(instance))
}
class OrderShipDateOptConstraints(override val instance: ZonedDateTime) extends ValidationBase[ZonedDateTime] {
    override def constraints: Seq[Constraint[ZonedDateTime]] =
        Seq()
}
class OrderShipDateOptValidator(instance: ZonedDateTime) extends RecursiveValidator {
    override val validators = Seq(new OrderShipDateOptConstraints(instance))
}
class OrderQuantityOptConstraints(override val instance: Int) extends ValidationBase[Int] {
    override def constraints: Seq[Constraint[Int]] =
        Seq()
}
class OrderQuantityOptValidator(instance: Int) extends RecursiveValidator {
    override val validators = Seq(new OrderQuantityOptConstraints(instance))
}
class OrderPetIdOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class OrderPetIdOptValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new OrderPetIdOptConstraints(instance))
}
class OrderIdOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class OrderIdOptValidator(instance: Long) extends RecursiveValidator {
    override val validators = Seq(new OrderIdOptConstraints(instance))
}
class OrderCompleteOptConstraints(override val instance: Boolean) extends ValidationBase[Boolean] {
    override def constraints: Seq[Constraint[Boolean]] =
        Seq()
}
class OrderCompleteOptValidator(instance: Boolean) extends RecursiveValidator {
    override val validators = Seq(new OrderCompleteOptConstraints(instance))
}
class OrderStatusOptConstraints(override val instance: String) extends ValidationBase[String] {
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class OrderStatusOptValidator(instance: String) extends RecursiveValidator {
    override val validators = Seq(new OrderStatusOptConstraints(instance))
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
class PetValidator(instance: Pet) extends RecursiveValidator {
    override val validators = Seq(
        new PetNameValidator(instance.name), 
        new PetPhotoUrlsValidator(instance.photoUrls), 
        new PetIdValidator(instance.id), 
        new PetStatusValidator(instance.status), 
        new PetTagsValidator(instance.tags), 
        new PetCategoryValidator(instance.category)
    )
}
class TagValidator(instance: Tag) extends RecursiveValidator {
    override val validators = Seq(
        new TagIdValidator(instance.id), 
        new TagNameValidator(instance.name)
    )
}
class UserValidator(instance: User) extends RecursiveValidator {
    override val validators = Seq(
        new UserEmailValidator(instance.email), 
        new UserUsernameValidator(instance.username), 
        new UserUserStatusValidator(instance.userStatus), 
        new UserLastNameValidator(instance.lastName), 
        new UserFirstNameValidator(instance.firstName), 
        new UserIdValidator(instance.id), 
        new UserPhoneValidator(instance.phone), 
        new UserPasswordValidator(instance.password)
    )
}
class OrderValidator(instance: Order) extends RecursiveValidator {
    override val validators = Seq(
        new OrderShipDateValidator(instance.shipDate), 
        new OrderQuantityValidator(instance.quantity), 
        new OrderPetIdValidator(instance.petId), 
        new OrderIdValidator(instance.id), 
        new OrderCompleteValidator(instance.complete), 
        new OrderStatusValidator(instance.status)
    )
}

// ----- option delegating validators -----
class UsersLoginGetUsernameValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersLoginGetUsernameOptValidator(_) }
}
class PetsPostBodyValidator(instance: Option[Pet]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetValidator(_) }
}
class PetIdValidator(instance: Option[Long]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetIdOptValidator(_) }
}
class PetStatusValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetStatusOptValidator(_) }
}
class PetTagsValidator(instance: Option[Seq[Tag]]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetTagsOptValidator(_) }
}
class TagIdValidator(instance: Option[Long]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new TagIdOptValidator(_) }
}
class TagNameValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new TagNameOptValidator(_) }
}
class PetCategoryValidator(instance: Option[Tag]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new TagValidator(_) }
}
class UsersUsernamePutBodyValidator(instance: Option[User]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserValidator(_) }
}
class UserEmailValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserEmailOptValidator(_) }
}
class UserUsernameValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserUsernameOptValidator(_) }
}
class UserUserStatusValidator(instance: Option[Int]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserUserStatusOptValidator(_) }
}
class UserLastNameValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserLastNameOptValidator(_) }
}
class UserFirstNameValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserFirstNameOptValidator(_) }
}
class UserIdValidator(instance: Option[Long]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserIdOptValidator(_) }
}
class UserPhoneValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserPhoneOptValidator(_) }
}
class UserPasswordValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UserPasswordOptValidator(_) }
}
class StoresOrderPostBodyValidator(instance: Option[Order]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderValidator(_) }
}
class OrderShipDateValidator(instance: Option[ZonedDateTime]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderShipDateOptValidator(_) }
}
class OrderQuantityValidator(instance: Option[Int]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderQuantityOptValidator(_) }
}
class OrderPetIdValidator(instance: Option[Long]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderPetIdOptValidator(_) }
}
class OrderIdValidator(instance: Option[Long]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderIdOptValidator(_) }
}
class OrderCompleteValidator(instance: Option[Boolean]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderCompleteOptValidator(_) }
}
class OrderStatusValidator(instance: Option[String]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new OrderStatusOptValidator(_) }
}
class UsersCreateWithListPostBodyValidator(instance: Option[Seq[User]]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new UsersCreateWithListPostBodyOptValidator(_) }
}
class PetsFindByStatusGetStatusValidator(instance: Option[ArrayWrapper[String]]) extends RecursiveValidator {
    override val validators = instance.toSeq.map { new PetsFindByStatusGetStatusOptValidator(_) }
}
// ----- array delegating validators -----
class PetPhotoUrlsConstraints(override val instance: Seq[String]) extends ValidationBase[Seq[String]] {
    override def constraints: Seq[Constraint[Seq[String]]] =
        Seq()
}
class PetPhotoUrlsValidator(instance: Seq[String]) extends RecursiveValidator {
    override val validators = new PetPhotoUrlsConstraints(instance) +: instance.map { new PetPhotoUrlsArrValidator(_)}
}
class PetTagsOptConstraints(override val instance: Seq[Tag]) extends ValidationBase[Seq[Tag]] {
    override def constraints: Seq[Constraint[Seq[Tag]]] =
        Seq()
}
class PetTagsOptValidator(instance: Seq[Tag]) extends RecursiveValidator {
    override val validators = new PetTagsOptConstraints(instance) +: instance.map { new TagValidator(_)}
}
class UsersCreateWithListPostBodyOptConstraints(override val instance: Seq[User]) extends ValidationBase[Seq[User]] {
    override def constraints: Seq[Constraint[Seq[User]]] =
        Seq()
}
class UsersCreateWithListPostBodyOptValidator(instance: Seq[User]) extends RecursiveValidator {
    override val validators = new UsersCreateWithListPostBodyOptConstraints(instance) +: instance.map { new UserValidator(_)}
}
class PetsFindByStatusGetStatusOptConstraints(override val instance: ArrayWrapper[String]) extends ValidationBase[ArrayWrapper[String]] {
    override def constraints: Seq[Constraint[ArrayWrapper[String]]] =
        Seq()
}
class PetsFindByStatusGetStatusOptValidator(instance: ArrayWrapper[String]) extends RecursiveValidator {
    override val validators = new PetsFindByStatusGetStatusOptConstraints(instance) +: instance.map { new PetsFindByStatusGetStatusOptArrValidator(_)}
}
// ----- catch all simple validators -----
// ----- composite validators -----
// ----- call validations -----
class UsersPostValidator(body: Option[User]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUsernamePutBodyValidator(body)
    
    )
}
class PetsPostValidator(body: Option[Pet]) extends RecursiveValidator {
    override val validators = Seq(
        new PetsPostBodyValidator(body)
    
    )
}
class PetsPutValidator(body: Option[Pet]) extends RecursiveValidator {
    override val validators = Seq(
        new PetsPostBodyValidator(body)
    
    )
}
class PetsFindByStatusGetValidator(status: Option[ArrayWrapper[String]]) extends RecursiveValidator {
    override val validators = Seq(
        new PetsFindByStatusGetStatusValidator(status)
    
    )
}
class StoresOrderPostValidator(body: Option[Order]) extends RecursiveValidator {
    override val validators = Seq(
        new StoresOrderPostBodyValidator(body)
    
    )
}
class UsersCreateWithArrayPostValidator(body: Option[Seq[User]]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersCreateWithListPostBodyValidator(body)
    
    )
}
class UsersLoginGetValidator(username: Option[String], password: Option[String]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersLoginGetUsernameValidator(username), 
    
        new UsersLoginGetUsernameValidator(password)
    
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
class UsersCreateWithListPostValidator(body: Option[Seq[User]]) extends RecursiveValidator {
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
class PetsFindByTagsGetValidator(tags: Option[ArrayWrapper[String]]) extends RecursiveValidator {
    override val validators = Seq(
        new PetsFindByStatusGetStatusValidator(tags)
    
    )
}
class UsersUsernamePutValidator(username: String, body: Option[User]) extends RecursiveValidator {
    override val validators = Seq(
        new UsersUsernamePutUsernameValidator(username), 
    
        new UsersUsernamePutBodyValidator(body)
    
    )
}
