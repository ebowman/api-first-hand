
package object basic_polymorphism_yaml {





    trait IPet {
        def name: String
        def petType: String
    }

    case class Cat(name: String, petType: String, huntingSkill: CatHuntingSkill) extends IPet
    case class Dog(name: String, petType: String, packSize: Int) extends IPet
    case class CatNDog(name: String, petType: String, packSize: Int, huntingSkill: CatHuntingSkill) extends IPet
    case class Pet(name: String, petType: String) extends IPet
    case class Labrador(name: String, petType: String, packSize: Int, cuteness: Int) extends IPet

    sealed trait CatHuntingSkill { def value: String }
    case object Clueless extends CatHuntingSkill { val value = "clueless" }
    case object Lazy extends CatHuntingSkill { val value = "lazy" }
    case object Adventurous extends CatHuntingSkill { val value = "adventurous" }
    case object Aggressive extends CatHuntingSkill { val value = "aggressive" }
    implicit def stringToCatHuntingSkill(in: String): CatHuntingSkill = in match {
        case "clueless" => Clueless
        case "lazy" => Lazy
        case "adventurous" => Adventurous
        case "aggressive" => Aggressive
    }


}
