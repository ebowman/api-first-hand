
package object additional_properties_yaml {

    import scala.collection.immutable.Map
    import de.zalando.play.controllers.ArrayWrapper
    import scala.math.BigInt



    type KeyedArraysAdditionalProperties = Map[String, KeyedArraysAdditionalPropertiesCatchAll]
    type KeyedArraysAdditionalPropertiesCatchAll = ArrayWrapper[BigInt]


    case class KeyedArrays(additionalProperties: KeyedArraysAdditionalProperties) 



}
