package basic_polymorphism




//noinspection ScalaStyle
package yaml {

    trait IPet {
        def name: String
        def petType: String
    }

    case class Zoo(tiers: ZooTiers) 
    case class Cat(name: String, petType: String, huntingSkill: CatHuntingSkill) extends IPet
    case class LabradorAllOf0(name: String, petType: String, packSize: Int) extends IPet
    case class Dog(name: String, petType: String, packSize: Int) extends IPet
    case class CatNDogAllOf1(name: String, petType: String, huntingSkill: CatHuntingSkill) extends IPet
    case class CatNDog(name: String, petType: String, packSize: Int, huntingSkill: CatHuntingSkill) extends IPet
    case class Pet(name: String, petType: String) extends IPet
    case class Labrador(name: String, petType: String, packSize: Int, cuteness: Int) extends IPet
    case class CatNDogAllOf0(name: String, petType: String, packSize: Int) extends IPet

    case class CatHuntingSkill(override val value: String) extends AnyVal with de.zalando.play.controllers.StringAnyVal

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val PetReads: Reads[Pet] = (
            (JsPath \ "name").read[String] and (JsPath \ "petType").read[String]
        )(Pet.apply _)
    }

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type ZooTiersOpt = Seq[IPet]
    type ZooTiers = Option[ZooTiersOpt]
    type PutDummy = Option[IPet]
    type PutResponses200 = Null

    object CatHuntingSkill {
        
        val Clueless = new CatHuntingSkill("clueless")
        val Lazy = new CatHuntingSkill("lazy")
        val Adventurous = new CatHuntingSkill("adventurous")
        val Aggressive = new CatHuntingSkill("aggressive")

        implicit def stringToCatHuntingSkill: String => CatHuntingSkill = {
            case "clueless" => Clueless
            case "lazy" => Lazy
            case "adventurous" => Adventurous
            case "aggressive" => Aggressive
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }


}