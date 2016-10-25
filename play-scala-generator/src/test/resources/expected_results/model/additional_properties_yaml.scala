

    import scala.collection.immutable.Map
    import scala.math.BigInt


//noinspection ScalaStyle
package additional_properties_yaml {


    case class KeyedArrays(additionalProperties: KeyedArraysAdditionalProperties) 


}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object additional_properties_yaml {

    type KeyedArraysAdditionalProperties = Map[String, KeyedArraysAdditionalPropertiesCatchAll]
    type KeyedArraysAdditionalPropertiesCatchAll = Seq[BigInt]



}