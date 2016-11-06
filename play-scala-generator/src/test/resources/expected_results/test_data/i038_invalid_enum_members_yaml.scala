package i038_invalid_enum_members.yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._

object Generators extends JsValueGenerators {
    

    
    def createGetResponses200Generator = _generate(GetResponses200Generator)
    

    
    def GetResponses200Generator = { import GetResponses200._ ; Gen.oneOf(Seq(Status_One, Status_Two, Status_Three)) }
    


    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    
    
    
    

}