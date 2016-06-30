package basic_polymorphism


    import de.zalando.play.controllers.ArrayWrapper


//noinspection ScalaStyle
package object yaml {

    type ZooTiersOpt = ArrayWrapper[IPet]
    type ZooTiers = Option[ZooTiersOpt]
    type PutDummy = Option[IPet]
    type PutResponses200 = Null

    
    val Clueless = CatHuntingSkill("clueless")
    val Lazy = CatHuntingSkill("lazy")
    val Adventurous = CatHuntingSkill("adventurous")
    val Aggressive = CatHuntingSkill("aggressive")

    implicit def stringToCatHuntingSkill: String => CatHuntingSkill = {
        case "clueless" => Clueless
        case "lazy" => Lazy
        case "adventurous" => Adventurous
        case "aggressive" => Aggressive
        case other =>
            throw new IllegalArgumentException("Couldn't parse parameter " + other)
    }


}
//noinspection ScalaStyle
package yaml {

    trait IPet {
        def name: String
        def petType: String
    }

    case class Zoo(tiers: ZooTiers) 
    case class Cat(name: String, petType: String, huntingSkill: CatHuntingSkill) extends IPet
    case class Dog(name: String, petType: String, packSize: Int) extends IPet
    case class CatNDog(name: String, petType: String, packSize: Int, huntingSkill: CatHuntingSkill) extends IPet
    case class Pet(name: String, petType: String) extends IPet
    case class Labrador(name: String, petType: String, packSize: Int, cuteness: Int) extends IPet

    case class CatHuntingSkill(value: String) extends AnyVal {
        override def toString = value.toString
    }

}
