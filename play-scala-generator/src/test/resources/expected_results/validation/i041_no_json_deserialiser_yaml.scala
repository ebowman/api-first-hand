package i041_no_json_deserialiser.yaml
import play.api.mvc.{Action, Controller}
import play.api.data.validation.Constraint
import de.zalando.play.controllers._
import PlayBodyParsing._
import PlayValidations._

import java.time.ZonedDateTime
import scala.math.BigDecimal
import scala.math.BigInt
// ----- constraints and wrapper validations -----
class UserIdGetIdConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override val reference = "⌿paths⌿/user/{id}⌿get⌿id"
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class UserIdGetIdValidator(instance: BigInt) extends RecursiveValidator {
    override val reference = "⌿paths⌿/user/{id}⌿get⌿id"
    override val validators = Seq(new UserIdGetIdConstraints(instance))
}
class UserPostNameConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿paths⌿/user⌿post⌿name"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserPostNameValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿paths⌿/user⌿post⌿name"
    override val validators = Seq(new UserPostNameConstraints(instance))
}
class UserIdPutIdConstraints(override val instance: BigInt) extends ValidationBase[BigInt] {
    override val reference = "⌿paths⌿/user/{id}⌿put⌿id"
    override def constraints: Seq[Constraint[BigInt]] =
        Seq()
}
class UserIdPutIdValidator(instance: BigInt) extends RecursiveValidator {
    override val reference = "⌿paths⌿/user/{id}⌿put⌿id"
    override val validators = Seq(new UserIdPutIdConstraints(instance))
}
class UserIdOptConstraints(override val instance: Long) extends ValidationBase[Long] {
    override val reference = "⌿definitions⌿User⌿id⌿Opt"
    override def constraints: Seq[Constraint[Long]] =
        Seq()
}
class UserIdOptValidator(instance: Long) extends RecursiveValidator {
    override val reference = "⌿definitions⌿User⌿id⌿Opt"
    override val validators = Seq(new UserIdOptConstraints(instance))
}
class UserNameOptConstraints(override val instance: String) extends ValidationBase[String] {
    override val reference = "⌿definitions⌿User⌿name⌿Opt"
    override def constraints: Seq[Constraint[String]] =
        Seq()
}
class UserNameOptValidator(instance: String) extends RecursiveValidator {
    override val reference = "⌿definitions⌿User⌿name⌿Opt"
    override val validators = Seq(new UserNameOptConstraints(instance))
}
class MoneyAmountOptConstraints(override val instance: BigDecimal) extends ValidationBase[BigDecimal] {
    override val reference = "⌿definitions⌿Money⌿amount⌿Opt"
    override def constraints: Seq[Constraint[BigDecimal]] =
        Seq()
}
class MoneyAmountOptValidator(instance: BigDecimal) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Money⌿amount⌿Opt"
    override val validators = Seq(new MoneyAmountOptConstraints(instance))
}
class MoneyCreateDateOptConstraints(override val instance: ZonedDateTime) extends ValidationBase[ZonedDateTime] {
    override val reference = "⌿definitions⌿Money⌿createDate⌿Opt"
    override def constraints: Seq[Constraint[ZonedDateTime]] =
        Seq()
}
class MoneyCreateDateOptValidator(instance: ZonedDateTime) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Money⌿createDate⌿Opt"
    override val validators = Seq(new MoneyCreateDateOptConstraints(instance))
}
// ----- complex type validators -----
class UserValidator(instance: User) extends RecursiveValidator {
    override val reference = "⌿definitions⌿User"
    override val validators = Seq(
        new UserIdValidator(instance.id), 
        new UserNameValidator(instance.name), 
        new UserMoneyValidator(instance.money)
    )
}
class MoneyValidator(instance: Money) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Money"
    override val validators = Seq(
        new UserIdValidator(instance.id), 
        new UserIdValidator(instance.userId), 
        new MoneyAmountValidator(instance.amount), 
        new MoneyCreateDateValidator(instance.createDate)
    )
}

// ----- option delegating validators -----
class UserIdValidator(instance: UserId) extends RecursiveValidator {
    override val reference = "⌿definitions⌿User⌿id"
    override val validators = instance.toSeq.map { new UserIdOptValidator(_) }
}
class UserNameValidator(instance: UserName) extends RecursiveValidator {
    override val reference = "⌿definitions⌿User⌿name"
    override val validators = instance.toSeq.map { new UserNameOptValidator(_) }
}
class MoneyAmountValidator(instance: MoneyAmount) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Money⌿amount"
    override val validators = instance.toSeq.map { new MoneyAmountOptValidator(_) }
}
class MoneyCreateDateValidator(instance: MoneyCreateDate) extends RecursiveValidator {
    override val reference = "⌿definitions⌿Money⌿createDate"
    override val validators = instance.toSeq.map { new MoneyCreateDateOptValidator(_) }
}
// ----- array delegating validators -----
// ----- catch all simple validators -----
// ----- composite validators -----
class UserMoneyValidator(instance: UserMoney) extends RecursiveValidator {
    override val reference = "⌿definitions⌿User⌿money"
    override val validators = Seq(
        new UserIdValidator(instance.id), new UserIdValidator(instance.userId), new MoneyAmountValidator(instance.amount), new MoneyCreateDateValidator(instance.createDate)
    )
}
// ----- call validations -----
class UserIdGetValidator(id: BigInt) extends RecursiveValidator {
    override val reference = "⌿paths⌿user⌿{id}⌿get"
    override val validators = Seq(
        new UserIdGetIdValidator(id)
    
    )
}
class UserIdPutValidator(id: BigInt, body: User) extends RecursiveValidator {
    override val reference = "⌿paths⌿user⌿{id}⌿put"
    override val validators = Seq(
        new UserIdPutIdValidator(id), 
    
        new UserValidator(body)
    
    )
}
class UserPostValidator(name: String) extends RecursiveValidator {
    override val reference = "⌿paths⌿user⌿post"
    override val validators = Seq(
        new UserPostNameValidator(name)
    
    )
}
