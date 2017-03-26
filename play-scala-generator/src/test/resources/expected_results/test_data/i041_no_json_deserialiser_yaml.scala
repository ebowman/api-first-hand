package i041_no_json_deserialiser.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import java.time.ZonedDateTime
import scala.math.BigDecimal
import scala.math.BigInt

object Generators extends JsValueGenerators {
    

    
    def createMoneyCreateDateGenerator = _generate(MoneyCreateDateGenerator)
    def createUserIdGenerator = _generate(UserIdGenerator)
    def createMoneyAmountGenerator = _generate(MoneyAmountGenerator)
    def createBigIntGenerator = _generate(BigIntGenerator)
    def createUserNameGenerator = _generate(UserNameGenerator)
    def createStringGenerator = _generate(StringGenerator)
    def createUserGetResponses200Generator = _generate(UserGetResponses200Generator)
    

    
    def MoneyCreateDateGenerator = Gen.option(arbitrary[ZonedDateTime])
    def UserIdGenerator = Gen.option(arbitrary[Long])
    def MoneyAmountGenerator = Gen.option(arbitrary[BigDecimal])
    def BigIntGenerator = arbitrary[BigInt]
    def UserNameGenerator = Gen.option(arbitrary[String])
    def StringGenerator = arbitrary[String]
    def UserGetResponses200Generator: Gen[List[User]] = Gen.containerOf[List,User](UserGenerator)
    

    def createMoneyGenerator = _generate(MoneyGenerator)
    def createUserGenerator = _generate(UserGenerator)
    def createUserMoneyGenerator = _generate(UserMoneyGenerator)
    def createErrorGenerator = _generate(ErrorGenerator)


    def MoneyGenerator = for {
        id <- UserIdGenerator
        userId <- UserIdGenerator
        amount <- MoneyAmountGenerator
        createDate <- MoneyCreateDateGenerator
    } yield Money(id, userId, amount, createDate)
    def UserGenerator = for {
        id <- UserIdGenerator
        name <- UserNameGenerator
        money <- UserMoneyGenerator
    } yield User(id, name, money)
    def UserMoneyGenerator = for {
        id <- UserIdGenerator
        userId <- UserIdGenerator
        amount <- MoneyAmountGenerator
        createDate <- MoneyCreateDateGenerator
    } yield UserMoney(id, userId, amount, createDate)
    def ErrorGenerator = for {
        code <- arbitrary[Int]
        message <- arbitrary[String]
    } yield Error(code, message)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    implicit lazy val arbDateTime: Arbitrary[ZonedDateTime] = Arbitrary(for {
        d <- arbitrary[java.util.Date]
    } yield ZonedDateTime.ofInstant(d.toInstant, java.time.ZoneId.systemDefault()))
    
    
    

}