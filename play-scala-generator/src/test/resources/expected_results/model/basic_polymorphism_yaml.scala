package basic_polymorphism

package object yaml {

    import de.zalando.play.controllers.ArrayWrapper



    type ZooTiersOpt = ArrayWrapper[IPet]
    type ZooTiers = Option[ZooTiersOpt]
    type PutDummy = Option[IPet]
    type PutResponses200 = Null

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

    case class CatHuntingSkill(value: String) extends AnyVal
    val Clueless = CatHuntingSkill("clueless")
    val Lazy = CatHuntingSkill("lazy")
    val Adventurous = CatHuntingSkill("adventurous")
    val Aggressive = CatHuntingSkill("aggressive")
    implicit def stringToCatHuntingSkill(in: String): CatHuntingSkill = in match {
        case "clueless" => Clueless
        case "lazy" => Lazy
        case "adventurous" => Adventurous
        case "aggressive" => Aggressive
    }


}
