package de.zalando.apifirst.generators

import de.zalando.apifirst.Application.{ TypeLookupTable, StrictModel, DiscriminatorLookupTable }
import de.zalando.apifirst.Domain
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.naming.Reference
import de.zalando.apifirst.naming.dsl._
import org.scalatest.{ FunSpec, MustMatchers }

import scala.language.implicitConversions

/**
 * @author  slasch
 * @since   18.11.2015.
 */
class ScalaGeneratorsTest extends FunSpec with MustMatchers {

  implicit def types2model(types: TypeLookupTable): StrictModel =
    StrictModel.apply(Nil, types, Map.empty, Map.empty, "", None, Map.empty, Map.empty)

  describe("ScalaGeneratorTest") {
    it("should generate nothing for empty model") {
      new ScalaGenerator(Map.empty[Reference, Domain.Type]).generateGenerators("test", "test") mustBe ""
    }

    it("should generate single type alias for an option") {
      val model = Map(
        "definitions" / "Opti" -> Opt(Lng(None), None),
        "definitions" / "Stri" -> Opt(Str(None, None), None)
      )
      val result = new ScalaGenerator(model).generateGenerators("test.yaml", "test.yaml")
      result mustBeAs
        """package test.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
          |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |object Generators extends JsValueGenerators {
          |   def createOptiGenerator = _generate(OptiGenerator)
          |   def createStriGenerator = _generate(StriGenerator)
          |   def OptiGenerator = Gen.option(arbitrary[Long])
          |   def StriGenerator = Gen.option(arbitrary[String])
          |   def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |}"""
    }

    it("should deal with overriden type definitions") {
      val model = Map(
        "definitions" / "Option" -> Opt(Lng(None), None),
        "definitions" / "String" -> Opt(Str(None, None), None)
      )
      new ScalaGenerator(model).generateGenerators("overloaded.yaml", "overloaded.yaml") mustBeAs
        """package overloaded.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
          |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |object Generators extends JsValueGenerators {
          |   def createOptionGenerator = _generate(OptionGenerator)
          |   def createStringGenerator = _generate(StringGenerator)
          |   def OptionGenerator = Gen.option(arbitrary[Long])
          |   def StringGenerator = Gen.option(arbitrary[String])
          |   def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |}"""
    }

    it("should generate single type alias for an array") {
      val model = Map(
        "definitions" / "Int" -> Arr(Intgr(None), None, "csv"),
        "definitions" / "Dbl" -> Arr(Dbl(None), None, "tsv"),
        "definitions" / "Flt" -> Arr(Flt(None), None, "ssv")
      )
      new ScalaGenerator(model).generateGenerators("test.yaml", "test.yaml") mustBeAs
        """package test.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
          |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |import de.zalando.play.controllers.ArrayWrapper
          |object Generators extends JsValueGenerators {
          |   def createIntGenerator = _generate(IntGenerator)
          |   def createDblGenerator = _generate(DblGenerator)
          |   def createFltGenerator = _generate(FltGenerator)
          |   def IntGenerator = _genList(arbitrary[Int], "csv")
          |   def DblGenerator = _genList(arbitrary[Double], "tsv")
          |   def FltGenerator = _genList(arbitrary[Float], "ssv")
          |   def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
          |        items <- Gen.containerOf[List,T](gen)
          |    } yield ArrayWrapper(format)(items)
          |}"""
    }

    it("should generate single type alias for catchAll") {
      val model = Map(
        "parameters" / "all" -> CatchAll(Bool(None), None)
      )
      new ScalaGenerator(model).generateGenerators("test.yaml", "test.yaml") mustBeAs
        """package test.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
          |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |import scala.collection.immutable.Map
          |object Generators extends JsValueGenerators {
          |   def createAllGenerator = _generate(AllGenerator)
          |   def AllGenerator = _genMap[String,Boolean](arbitrary[String], arbitrary[Boolean])
          |   def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |   def _genMap[K,V](keyGen: Gen[K], valGen: Gen[V]): Gen[Map[K,V]] = for {
          |     keys <- Gen.containerOf[List,K](keyGen)
          |     values <- Gen.containerOfN[List,V](keys.size, valGen)
          |   } yield keys.zip(values).toMap
          |}"""
    }

    it("should generate single type alias for top-level primitive type") {
      val model = Map(
        "paths" / "/" / "get" / "responses" / "200" -> Null(None),
        "paths" / "/" / "put" / "responses" / "200" -> Null(None)
      )
      val result = new ScalaGenerator(model).generateGenerators("test.yaml", "test.yaml")
      result mustBeAs
        """|
          |package test.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
           |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |object Generators extends JsValueGenerators {
          |def createNullGenerator = _generate(NullGenerator)
          |def createNullNameClashGenerator = _generate(NullNameClashGenerator)
          |def NullGenerator = arbitrary[Null]
          |def NullNameClashGenerator = arbitrary[Null]
          | def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |}"""
    }

    it("should generate a class file for typeDef") {
      val fields = Seq(
        Field("definitions" / "User" / "name", Str(None, None)),
        Field("definitions" / "User" / "id", Lng(None))
      )
      val model = Map(
        "definitions" / "User" -> TypeDef("definitions" / "User", fields, None)
      )
      val result = new ScalaGenerator(model).generateGenerators("test.yaml", "test.yaml")
      result mustBeAs
        """package test.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
          |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |object Generators extends JsValueGenerators {
          |   def createUserGenerator = _generate(UserGenerator)
          |   def UserGenerator = for {
          |       name <- arbitrary[String]
          |       id <- arbitrary[Long]
          |     } yield User(name, id)
          |   def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |}"""
    }

    it("should generate a type alias for the TypeReference") {
      val model = Map(
        "definitions" / "OptionalData" -> Opt(TypeRef("definitions" / "Passwords"), None),
        "definitions" / "Passwords" -> Arr(Password(None), None, "pipes")
      )
      val result = new ScalaGenerator(model).generateGenerators("test.yaml", "test.yaml")

      result mustBeAs
        """package test.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
          |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |import de.zalando.play.controllers.ArrayWrapper
          |object Generators extends JsValueGenerators {
          |   def createOptionalDataGenerator = _generate(OptionalDataGenerator)
          |   def createPasswordsGenerator = _generate(PasswordsGenerator)
          |   def OptionalDataGenerator = Gen.option(PasswordsGenerator)
          |   def PasswordsGenerator = _genList(arbitrary[String], "pipes")
          |   def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |    def _genList[T](gen: Gen[T], format: String): Gen[ArrayWrapper[T]] = for {
          |        items <- Gen.containerOf[List,T](gen)
          |    } yield ArrayWrapper(format)(items)
          |}"""
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
      val result = new ScalaGenerator(model).generateGenerators("test.yaml", "test.yaml")

      result mustBeAs
        """package test.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
          |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |object Generators extends JsValueGenerators {
          |   def createCatGenerator = _generate(CatGenerator)
          |   def createDogGenerator = _generate(DogGenerator)
          |   def createCatNDogGenerator = _generate(CatNDogGenerator)
          |   def createPetGenerator = _generate(PetGenerator)
          |   def createLabradorGenerator = _generate(LabradorGenerator)
          |   def CatGenerator = for {
          |       name <- arbitrary[String]
          |       petType <- arbitrary[String]
          |       huntingSkill <- arbitrary[String]
          |     } yield Cat(name, petType, huntingSkill)
          |   def DogGenerator = for {
          |       name <- arbitrary[String]
          |       petType <- arbitrary[String]
          |       packSize <- arbitrary[Int]
          |     } yield Dog(name, petType, packSize)
          |   def CatNDogGenerator = for {
          |       name <- arbitrary[String]
          |       petType <- arbitrary[String]
          |       packSize <- arbitrary[Int]
          |       huntingSkill <- arbitrary[String]
          |     } yield CatNDog(name, petType, packSize, huntingSkill)
          |   def PetGenerator = for {
          |       name <- arbitrary[String]
          |       petType <- arbitrary[String]
          |     } yield Pet(name, petType)
          |   def LabradorGenerator = for {
          |       name <- arbitrary[String]
          |       petType <- arbitrary[String]
          |       packSize <- arbitrary[Int]
          |       cuteness <- arbitrary[Int]
          |     } yield Labrador(name, petType, packSize, cuteness)
          |   def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |}"""
    }

    it("should generate a simple composition") {
      val model = Map(
        "definitions" / "ErrorModel" ->
          TypeDef("definitions" / "ErrorModel", Seq(
            Field("definitions" / "ErrorModel" / "message", Str(None, None)),
            Field("definitions" / "ErrorModel" / "code", Intgr(None))
          ), None),
        "definitions" / "ExtendedErrorModel" ->
          AllOf("definitions" / "ExtendedErrorModel", None, Seq(
            TypeRef("definitions" / "ErrorModel"),
            TypeRef("definitions" / "ExtendedErrorModel" / "AllOf1")
          )),
        "definitions" / "ExtendedErrorModel" / "AllOf1" ->
          TypeDef("definitions" / "ExtendedErrorModel", Seq(
            Field("definitions" / "ExtendedErrorModel" / "rootCause", Str(None, None))
          ), None)
      )
      val result = new ScalaGenerator(model).generateGenerators("test.yaml", "test.yaml")

      result mustBeAs
        """package test.yaml
          |import org.scalacheck.Gen
          |import org.scalacheck.Arbitrary
          |import play.api.libs.json.scalacheck.JsValueGenerators
          |import Arbitrary._
          |object Generators extends JsValueGenerators {
          |   def createErrorModelGenerator = _generate(ErrorModelGenerator)
          |   def createExtendedErrorModelGenerator = _generate(ExtendedErrorModelGenerator)
          |   def ErrorModelGenerator = for {
          |       message <- arbitrary[String]
          |       code <- arbitrary[Int]
          |   } yield ErrorModel(message, code)
          |   def ExtendedErrorModelGenerator = for {
          |       message <- arbitrary[String]
          |       code <- arbitrary[Int]
          |       rootCause <- arbitrary[String]
          |   } yield ExtendedErrorModel(message, code, rootCause)
          |   def _generate[T](gen: Gen[T]) = (count: Int) => for (i <- 1 to count) yield gen.sample
          |}"""
    }

  }

  implicit def any2Comparator(any: Any): StringComparator = new StringComparator(any.toString)

  class StringComparator(s1: String) {

    def mustBeAs(s2: String): Unit = {
      cleanSpaces(s1) mustBe cleanSpaces(s2.stripMargin)
    }

    def cleanSpaces(s: String): String =
      s.split("\n").map(_.trim).filterNot(_.isEmpty).mkString("\n")
  }

}
