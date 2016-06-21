package de.zalando.model
import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.ParameterPlace
import de.zalando.apifirst.naming._
import de.zalando.apifirst.Hypermedia._
import de.zalando.apifirst.Http._
import de.zalando.apifirst.Security
import java.net.URL
import Security._ 
//noinspection ScalaStyle
object basic_polymorphism_yaml extends WithModel {
 
 def types = Map[Reference, Type](
	Reference("⌿definitions⌿Cat") → 
					AllOf(Reference("⌿definitions⌿Cat⌿Cat"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
						Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Pet⌿petType"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
			TypeDef(Reference("⌿definitions⌿Cat"), 
			Seq(
						Field(Reference("⌿definitions⌿Cat⌿huntingSkill"), 					EnumTrait(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), TypeMeta(Some("Enum type : 4"), List()), 
						Set(
							EnumObject(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), "clueless", TypeMeta(Some("clueless"), List())),
							EnumObject(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), "lazy", TypeMeta(Some("lazy"), List())),
							EnumObject(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), "adventurous", TypeMeta(Some("adventurous"), List())),
							EnumObject(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), "aggressive", TypeMeta(Some("aggressive"), List()))

						)))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Pet⌿petType"))),
	Reference("⌿definitions⌿Dog") → 
					AllOf(Reference("⌿definitions⌿Dog⌿Dog"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
			TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
						Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
						Field(Reference("⌿definitions⌿Pet⌿petType"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
			TypeDef(Reference("⌿definitions⌿Dog"), 
			Seq(
						Field(Reference("⌿definitions⌿Dog⌿packSize"), Intgr(TypeMeta(Some("the size of the pack the dog is from"), List("min(0.toInt, false)"))))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Pet⌿petType"))),
	Reference("⌿definitions⌿CatNDog") → 
					AllOf(Reference("⌿definitions⌿CatNDog⌿CatNDog"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
							AllOf(Reference("⌿definitions⌿CatNDog⌿CatNDog"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
				TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
							Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
							Field(Reference("⌿definitions⌿Pet⌿petType"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
				TypeDef(Reference("⌿definitions⌿CatNDog"), 
			Seq(
							Field(Reference("⌿definitions⌿CatNDog⌿packSize"), Intgr(TypeMeta(Some("the size of the pack the dog is from"), List("min(0.toInt, false)"))))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Pet⌿petType"))),
							AllOf(Reference("⌿definitions⌿CatNDog⌿CatNDog"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
				TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
							Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
							Field(Reference("⌿definitions⌿Pet⌿petType"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
				TypeDef(Reference("⌿definitions⌿CatNDog"), 
			Seq(
							Field(Reference("⌿definitions⌿CatNDog⌿huntingSkill"), 						EnumTrait(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), TypeMeta(Some("Enum type : 4"), List()), 
							Set(
								EnumObject(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), "clueless", TypeMeta(Some("clueless"), List())),
								EnumObject(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), "lazy", TypeMeta(Some("lazy"), List())),
								EnumObject(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), "adventurous", TypeMeta(Some("adventurous"), List())),
								EnumObject(Str(None, TypeMeta(Some("The measured skill for hunting"), List("""enum("clueless,lazy,adventurous,aggressive")"""))), "aggressive", TypeMeta(Some("aggressive"), List()))

							)))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Pet⌿petType")))) , Some(Reference("⌿definitions⌿Pet⌿petType"))),
	Reference("⌿definitions⌿Pet") → 
		TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
					Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
					Field(Reference("⌿definitions⌿Pet⌿petType"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
	Reference("⌿definitions⌿Labrador") → 
					AllOf(Reference("⌿definitions⌿Labrador⌿Labrador"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
							AllOf(Reference("⌿definitions⌿Labrador⌿Labrador"), TypeMeta(Some("Schemas: 2"), List()),  Seq(
				TypeDef(Reference("⌿definitions⌿Pet"), 
			Seq(
							Field(Reference("⌿definitions⌿Pet⌿name"), Str(None, TypeMeta(None, List()))),
							Field(Reference("⌿definitions⌿Pet⌿petType"), Str(None, TypeMeta(None, List())))
			), TypeMeta(Some("Named types: 2"), List())),
				TypeDef(Reference("⌿definitions⌿Labrador"), 
			Seq(
							Field(Reference("⌿definitions⌿Labrador⌿packSize"), Intgr(TypeMeta(Some("the size of the pack the dog is from"), List("min(0.toInt, false)"))))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Pet⌿petType"))),
			TypeDef(Reference("⌿definitions⌿Labrador"), 
			Seq(
						Field(Reference("⌿definitions⌿Labrador⌿cuteness"), Intgr(TypeMeta(Some("the cuteness of the animal in percent"), List("min(0.toInt, false)"))))
			), TypeMeta(Some("Named types: 1"), List()))) , Some(Reference("⌿definitions⌿Pet⌿petType")))
) 
 
 def parameters = Map[ParameterRef, Parameter](

) 
 def basePath: String =null
 def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](
		Reference("⌿definitions⌿CatNDog") -> Reference("⌿definitions⌿Pet⌿petType"),
	Reference("⌿definitions⌿Dog") -> Reference("⌿definitions⌿Pet⌿petType"),
	Reference("⌿definitions⌿Cat") -> Reference("⌿definitions⌿Pet⌿petType"),
	Reference("⌿definitions⌿Labrador") -> Reference("⌿definitions⌿Pet⌿petType"),
	Reference("⌿definitions⌿Pet") -> Reference("⌿definitions⌿Pet⌿petType"))
 def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition](
	
)
def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]()
def calls: Seq[ApiCall] = Seq()

def packageName: Option[String] = None

def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    
} 