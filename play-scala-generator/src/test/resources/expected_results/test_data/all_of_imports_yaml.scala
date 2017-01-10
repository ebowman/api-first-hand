package all_of_imports.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import java.time.ZonedDateTime

object Generators extends JsValueGenerators {
    

    
    def createNullGenerator = _generate(NullGenerator)
    

    
    def NullGenerator = arbitrary[Null]
    

    def createValueGenerator = _generate(ValueGenerator)
    def createDatetimeValueGenerator = _generate(DatetimeValueGenerator)


    def ValueGenerator = for {
        `type` <- arbitrary[String]
    } yield Value(`type`)
    def DatetimeValueGenerator = for {
        `type` <- arbitrary[String]
        value <- arbitrary[ZonedDateTime]
    } yield DatetimeValue(`type`, value)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample



    implicit lazy val arbDateTime: Arbitrary[ZonedDateTime] = Arbitrary(for {
        d <- arbitrary[java.util.Date]
    } yield ZonedDateTime.ofInstant(d.toInstant, java.time.ZoneId.systemDefault()))

    
    

}
