package i041_no_json_deserialiser


    import java.time.ZonedDateTime
    import scala.math.BigDecimal
    import scala.math.BigInt

    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {


    case class Money(id: UserId, userId: UserId, amount: MoneyAmount, createDate: MoneyCreateDate) 
    case class User(id: UserId, name: UserName, money: UserMoney) 
    case class UserMoney(id: UserId, userId: UserId, amount: MoneyAmount, createDate: MoneyCreateDate) 
    case class Error(code: Int, message: String) 


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val MoneyReads: Reads[Money] = (
            (JsPath \ "id").readNullable[Long] and (JsPath \ "userId").readNullable[Long] and (JsPath \ "amount").readNullable[BigDecimal] and (JsPath \ "createDate").readNullable[ZonedDateTime]
        )(Money.apply _)
        implicit val UserMoneyReads: Reads[UserMoney] = (
            (JsPath \ "id").readNullable[Long] and (JsPath \ "userId").readNullable[Long] and (JsPath \ "amount").readNullable[BigDecimal] and (JsPath \ "createDate").readNullable[ZonedDateTime]
        )(UserMoney.apply _)
        implicit val UserReads: Reads[User] = (
            (JsPath \ "id").readNullable[Long] and (JsPath \ "name").readNullable[String] and (JsPath \ "money").read[UserMoney]
        )(User.apply _)
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val MoneyWrites: Writes[Money] = new Writes[Money] {
        def writes(ss: Money) =
          Json.obj(
            "id" -> ss.id, 
            "userId" -> ss.userId, 
            "amount" -> ss.amount, 
            "createDate" -> ss.createDate
          )
        }
    implicit val UserMoneyWrites: Writes[UserMoney] = new Writes[UserMoney] {
        def writes(ss: UserMoney) =
          Json.obj(
            "id" -> ss.id, 
            "userId" -> ss.userId, 
            "amount" -> ss.amount, 
            "createDate" -> ss.createDate
          )
        }
    implicit val UserWrites: Writes[User] = new Writes[User] {
        def writes(ss: User) =
          Json.obj(
            "id" -> ss.id, 
            "name" -> ss.name, 
            "money" -> ss.money
          )
        }
    }
}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type MoneyCreateDate = Option[ZonedDateTime]
    type UserId = Option[Long]
    type MoneyAmount = Option[BigDecimal]
    type UserName = Option[String]
    type UserGetResponses200 = Seq[User]


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_BigIntPath = PlayPathBindables.pathBindableBigInt

}