package de.zalando.apifirst.generators

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.naming.Reference
import de.zalando.apifirst.naming.dsl._
import org.scalatest.{ FunSpec, MustMatchers }

import scala.language.implicitConversions

/**
 * @author slasch
 * @since 21.12.2015.
 */
class AstScalaPlayEnricherTest extends FunSpec with MustMatchers {

  implicit def types2model(types: TypeLookupTable): StrictModel =
    StrictModel.apply(Nil, types, Map.empty, Map.empty, "", None, Map.empty, Map.empty)

  describe("AstScalaPlayEnricher") {
    it("should generate nothing for empty model") {
      AstScalaPlayEnricher(Map.empty[Reference, Domain.Type], Set.empty[String]) mustBe empty
    }

    it("should generate a complex polymorphic hierarchy") {
      val model = Map(
        "definitions" / "Cat" ->
          AllOf("definitions" / "Cat", None,
            Seq(
              TypeRef("definitions" / "Pet"),
              TypeRef("definitions" / "Cat" / "AllOf1")
            ), Some("definitions" / "Pet")),
        "definitions" / "Dog" ->
          AllOf("definitions" / "Dog", None,
            Seq(
              TypeRef("definitions" / "Pet"),
              TypeRef("definitions" / "Dog" / "AllOf1")
            ), Some("definitions" / "Pet")),
        "definitions" / "CatNDog" ->
          AllOf("definitions" / "CatNDog", None,
            Seq(
              TypeRef("definitions" / "Dog"),
              TypeRef("definitions" / "Cat")
            ), Some("definitions" / "Pet")),
        "definitions" / "Pet" ->
          TypeDef("definitions" / "Pet", Seq(
            Field("definitions" / "Pet" / "name", Str(None, None)),
            Field("definitions" / "Pet" / "petType", Str(None, None))
          ), None),
        "definitions" / "Labrador" ->
          AllOf("definitions" / "Labrador", None,
            Seq(
              TypeRef("definitions" / "Dog"),
              TypeRef("definitions" / "Labrador" / "AllOf1")
            ), Some("definitions" / "Pet")),
        "definitions" / "Cat" / "AllOf1" ->
          TypeDef("definitions" / "Cat", Seq(
            Field("definitions" / "Cat" / "huntingSkill", Str(None, None))
          ), None),
        "definitions" / "Dog" / "AllOf1" ->
          TypeDef("definitions" / "Dog", Seq(
            Field("definitions" / "Dog" / "packSize", Intgr(None))
          ), None),
        "definitions" / "Labrador" / "AllOf1" ->
          TypeDef("definitions" / "Labrador", Seq(
            Field("definitions" / "Labrador" / "cuteness", Intgr(None))
          ), None)
      )
      val discriminators: DiscriminatorLookupTable = Map(
        "definitions" / "Pet" -> "definitions" / "Pet" / "petType"
      )
      val strictModel = StrictModel(Nil, model, Map.empty, discriminators, "", None, Map.empty, Map.empty)

      val result = AstScalaPlayEnricher(strictModel, Set.empty[String])

      val expected =
        Map(
          "definitions" / "Cat" / "AllOf1" -> Map("common" -> Map("type_name" -> "CatAllOf1", "fields" -> List(Field("definitions" / "Cat" / "huntingSkill", Str(None, TypeMeta(None, List())))), "member_name" -> "CatAllOf1")),
          "definitions" / "Cat" -> Map("common" -> Map("type_name" -> "Cat", "fields" -> List(Field("definitions" / "Pet" / "name", Str(None, TypeMeta(None, List()))), Field("definitions" / "Pet" / "petType", Str(None, TypeMeta(None, List()))), Field("definitions" / "Cat" / "huntingSkill", Str(None, TypeMeta(None, List())))), "member_name" -> "Cat"), "classes" -> Map("name" -> "Cat", "single_field" -> false, "optional" -> false, "trait" -> Some(Map("name" -> "IPet")), "fields" -> List(Map("name" -> "name", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "petType", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "huntingSkill", "nullable_type_name" -> None, "type_name" -> "String")), "imports" -> Set()), "test_data_classes" -> Map("generator" -> "CatGenerator", "generator_type" -> "", "fields" -> List(Map("name" -> "name", "generator" -> "arbitrary[String]"), Map("name" -> "petType", "generator" -> "arbitrary[String]"), Map("name" -> "huntingSkill", "generator" -> "arbitrary[String]")), "class_name" -> "Cat", "generator_name" -> "CatGenerator", "creator_method" -> "createCatGenerator")),
          "definitions" / "Dog" / "AllOf1" -> Map("common" -> Map("type_name" -> "DogAllOf1", "fields" -> List(Field("definitions" / "Dog" / "packSize", Intgr(TypeMeta(None, List())))), "member_name" -> "DogAllOf1")),
          "definitions" / "Dog" -> Map("common" -> Map("type_name" -> "Dog", "fields" -> List(Field("definitions" / "Pet" / "name", Str(None, TypeMeta(None, List()))), Field("definitions" / "Pet" / "petType", Str(None, TypeMeta(None, List()))), Field("definitions" / "Dog" / "packSize", Intgr(TypeMeta(None, List())))), "member_name" -> "Dog"), "classes" -> Map("name" -> "Dog", "single_field" -> false, "optional" -> false, "trait" -> Some(Map("name" -> "IPet")), "fields" -> List(Map("name" -> "name", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "petType", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "packSize", "nullable_type_name" -> None, "type_name" -> "Int")), "imports" -> Set()), "test_data_classes" -> Map("generator" -> "DogGenerator", "generator_type" -> "", "fields" -> List(Map("name" -> "name", "generator" -> "arbitrary[String]"), Map("name" -> "petType", "generator" -> "arbitrary[String]"), Map("name" -> "packSize", "generator" -> "arbitrary[Int]")), "class_name" -> "Dog", "generator_name" -> "DogGenerator", "creator_method" -> "createDogGenerator")),
          "definitions" / "CatNDog" -> Map("common" -> Map("type_name" -> "CatNDog", "fields" -> List(Field("definitions" / "Pet" / "name", Str(None, TypeMeta(None, List()))), Field("definitions" / "Pet" / "petType", Str(None, TypeMeta(None, List()))), Field("definitions" / "Dog" / "packSize", Intgr(TypeMeta(None, List()))), Field("definitions" / "Cat" / "huntingSkill", Str(None, TypeMeta(None, List())))), "member_name" -> "CatNDog"), "classes" -> Map("name" -> "CatNDog", "single_field" -> false, "optional" -> false, "trait" -> Some(Map("name" -> "IPet")), "fields" -> List(Map("name" -> "name", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "petType", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "packSize", "nullable_type_name" -> None, "type_name" -> "Int"), Map("name" -> "huntingSkill", "nullable_type_name" -> None, "type_name" -> "String")), "imports" -> Set()), "test_data_classes" -> Map("generator" -> "CatNDogGenerator", "generator_type" -> "", "fields" -> List(Map("name" -> "name", "generator" -> "arbitrary[String]"), Map("name" -> "petType", "generator" -> "arbitrary[String]"), Map("name" -> "packSize", "generator" -> "arbitrary[Int]"), Map("name" -> "huntingSkill", "generator" -> "arbitrary[String]")), "class_name" -> "CatNDog", "generator_name" -> "CatNDogGenerator", "creator_method" -> "createCatNDogGenerator")),
          "definitions" / "Pet" -> Map("common" -> Map("type_name" -> "Pet", "fields" -> List(Field("definitions" / "Pet" / "name", Str(None, TypeMeta(None, List()))), Field("definitions" / "Pet" / "petType", Str(None, TypeMeta(None, List())))), "member_name" -> "Pet", "abstract_type_name" -> "IPet"), "classes" -> Map("name" -> "Pet", "single_field" -> false, "optional" -> false, "abstract_name" -> "IPet", "trait" -> Some(Map("name" -> "IPet")), "fields" -> List(Map("name" -> "name", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "petType", "nullable_type_name" -> None, "type_name" -> "String")), "imports" -> Set()), "traits" -> Map("name" -> "Pet", "single_field" -> false, "optional" -> false, "abstract_name" -> "IPet", "fields" -> List(Map("name" -> "name", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "petType", "nullable_type_name" -> None, "type_name" -> "String")), "imports" -> Set()), "test_data_classes" -> Map("generator" -> "PetGenerator", "generator_type" -> "", "fields" -> List(Map("name" -> "name", "generator" -> "arbitrary[String]"), Map("name" -> "petType", "generator" -> "arbitrary[String]")), "class_name" -> "Pet", "generator_name" -> "PetGenerator", "creator_method" -> "createPetGenerator")),
          "definitions" / "Labrador" -> Map("common" -> Map("type_name" -> "Labrador", "fields" -> List(Field("definitions" / "Pet" / "name", Str(None, TypeMeta(None, List()))), Field("definitions" / "Pet" / "petType", Str(None, TypeMeta(None, List()))), Field("definitions" / "Dog" / "packSize", Intgr(TypeMeta(None, List()))), Field("definitions" / "Labrador" / "cuteness", Intgr(TypeMeta(None, List())))), "member_name" -> "Labrador"), "classes" -> Map("name" -> "Labrador", "single_field" -> false, "optional" -> false, "trait" -> Some(Map("name" -> "IPet")), "fields" -> List(Map("name" -> "name", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "petType", "nullable_type_name" -> None, "type_name" -> "String"), Map("name" -> "packSize", "nullable_type_name" -> None, "type_name" -> "Int"), Map("name" -> "cuteness", "nullable_type_name" -> None, "type_name" -> "Int")), "imports" -> Set()), "test_data_classes" -> Map("generator" -> "LabradorGenerator", "generator_type" -> "", "fields" -> List(Map("name" -> "name", "generator" -> "arbitrary[String]"), Map("name" -> "petType", "generator" -> "arbitrary[String]"), Map("name" -> "packSize", "generator" -> "arbitrary[Int]"), Map("name" -> "cuteness", "generator" -> "arbitrary[Int]")), "class_name" -> "Labrador", "generator_name" -> "LabradorGenerator", "creator_method" -> "createLabradorGenerator")),
          "definitions" / "Labrador" / "AllOf1" -> Map("common" -> Map("type_name" -> "LabradorAllOf1", "fields" -> List(Field("definitions" / "Labrador" / "cuteness", Intgr(TypeMeta(None, List())))), "member_name" -> "LabradorAllOf1"))
        )
      result mustBe expected
    }

  }
}
