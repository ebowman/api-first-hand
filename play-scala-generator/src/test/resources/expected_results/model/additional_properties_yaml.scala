

    import scala.collection.immutable.Map
    import scala.math.BigInt


//noinspection ScalaStyle
package object additional_properties_yaml {

    type KeyedArraysAdditionalProperties = Map[String, KeyedArraysAdditionalPropertiesCatchAll]
    type KeyedArraysAdditionalPropertiesCatchAll = Seq[BigInt]



}
//noinspection ScalaStyle
package additional_properties_yaml {


    case class KeyedArrays(additionalProperties: KeyedArraysAdditionalProperties) 


}
