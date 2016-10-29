package string_formats.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.Base64String
import Base64String._
import java.time.ZonedDateTime
import java.util.UUID
import java.time.LocalDate
import de.zalando.play.controllers.BinaryString
import BinaryString._

object Generators extends JsValueGenerators {
    

    
    def createGetBase64Generator = _generate(GetBase64Generator)
    def createBinaryStringGenerator = _generate(BinaryStringGenerator)
    def createGetDate_timeGenerator = _generate(GetDate_timeGenerator)
    def createGetUuidGenerator = _generate(GetUuidGenerator)
    def createGetDateGenerator = _generate(GetDateGenerator)
    def createNullGenerator = _generate(NullGenerator)
    

    
    def GetBase64Generator = Gen.option(arbitrary[Base64String])
    def BinaryStringGenerator = arbitrary[BinaryString]
    def GetDate_timeGenerator = Gen.option(arbitrary[ZonedDateTime])
    def GetUuidGenerator = Gen.option(arbitrary[UUID])
    def GetDateGenerator = Gen.option(arbitrary[LocalDate])
    def NullGenerator = arbitrary[Null]
    


    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    implicit lazy val arbLocalDate: Arbitrary[LocalDate] = Arbitrary(for {
        l <- arbitrary[Int]
        epochSec = (System.currentTimeMillis + l) / 1000
        epochDay = epochSec / 24 / 60 / 60
    } yield LocalDate.ofEpochDay(epochDay))
    
    implicit lazy val arbDateTime: Arbitrary[ZonedDateTime] = Arbitrary(for {
        l <- arbitrary[Long]
    } yield ZonedDateTime.of(java.time.LocalDateTime.ofEpochSecond(l, 0, java.time.ZoneOffset.UTC), java.time.ZoneId.systemDefault()))
    
    implicit lazy val arbBinaryString: Arbitrary[BinaryString] = Arbitrary(for {
        s <- arbitrary[String]
    } yield BinaryString.fromString(s))
    
    implicit lazy val arbBase64String: Arbitrary[Base64String] = Arbitrary(for {
        s <- arbitrary[String]
    } yield Base64String.string2base64string(s))
    
    
    implicit lazy val arbUUID: Arbitrary[UUID] = Arbitrary(Gen.uuid)
    

}