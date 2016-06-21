package additional_properties_yaml

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import play.api.libs.json.scalacheck.JsValueGenerators
import Arbitrary._
import scala.collection.immutable.Map
import de.zalando.play.controllers.ArrayWrapper
import scala.math.BigInt

object Generators extends JsValueGenerators {
    

    
    def createKeyedArraysAdditionalPropertiesGenerator = _generate(KeyedArraysAdditionalPropertiesGenerator)
    def createKeyedArraysAdditionalPropertiesCatchAllGenerator = _generate(KeyedArraysAdditionalPropertiesCatchAllGenerator)
    

    
    def KeyedArraysAdditionalPropertiesGenerator = _genMap[String,KeyedArraysAdditionalPropertiesCatchAll](arbitrary[String], KeyedArraysAdditionalPropertiesCatchAllGenerator)
    def KeyedArraysAdditionalPropertiesCatchAllGenerator = _genList(arbitrary[BigInt], "csv")
    

    def createKeyedArraysGenerator = _generate(KeyedArraysGenerator)


    def KeyedArraysGenerator = for {
        additionalProperties <- KeyedArraysAdditionalPropertiesGenerator
    } yield KeyedArrays(additionalProperties)

    def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample

    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
        items <- Gen.containerOf[List,T](gen)
    } yield ArrayWrapper(format)(items)
    
    def _genMap[K,V](keyGen: Gen[K], valGen: Gen[V]): Gen[Map[K,V]] = for {
        keys <- Gen.containerOf[List,K](keyGen)
        values <- Gen.containerOfN[List,V](keys.size, valGen)
    } yield keys.zip(values).toMap
    
    
    
}