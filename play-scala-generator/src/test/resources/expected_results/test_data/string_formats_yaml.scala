package string_formats.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import de.zalando.play.controllers.Base64String
import Base64String._
import de.zalando.play.controllers.BinaryString
import BinaryString._
import java.time.ZonedDateTime
import java.util.UUID
import java.time.LocalDate

object Generators extends JsValueGenerators {
    

    
    def createOptionBase64StringGenerator = _generate(OptionBase64StringGenerator)
    def createBinaryStringGenerator = _generate(BinaryStringGenerator)
    def createOptionZonedDateTimeGenerator = _generate(OptionZonedDateTimeGenerator)
    def createOptionUUIDGenerator = _generate(OptionUUIDGenerator)
    def createOptionLocalDateGenerator = _generate(OptionLocalDateGenerator)
    def createNullGenerator = _generate(NullGenerator)
    

    
    def OptionBase64StringGenerator = Gen.option(arbitrary[Base64String])
    def BinaryStringGenerator = arbitrary[BinaryString]
    def OptionZonedDateTimeGenerator = Gen.option(arbitrary[ZonedDateTime])
    def OptionUUIDGenerator = Gen.option(arbitrary[UUID])
    def OptionLocalDateGenerator = Gen.option(arbitrary[LocalDate])
    def NullGenerator = arbitrary[Null]
    


    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    implicit lazy val arbLocalDate: Arbitrary[LocalDate] = Arbitrary(for {
        l <- arbitrary[Int]
        epochSec = (System.currentTimeMillis + l) / 1000
        epochDay = epochSec / 24 / 60 / 60
    } yield LocalDate.ofEpochDay(epochDay))
    
    implicit lazy val arbDateTime: Arbitrary[ZonedDateTime] = Arbitrary(for {
        d <- arbitrary[java.util.Date]
    } yield ZonedDateTime.ofInstant(d.toInstant, java.time.ZoneId.systemDefault()))
    
    implicit lazy val arbBinaryString: Arbitrary[BinaryString] = Arbitrary(for {
        s <- arbitrary[String]
    } yield BinaryString.fromString(s))
    
    implicit lazy val arbBase64String: Arbitrary[Base64String] = Arbitrary(for {
        s <- arbitrary[String]
    } yield Base64String.string2base64string(s))
    
    
    implicit lazy val arbUUID: Arbitrary[UUID] = Arbitrary(Gen.uuid)
    

}