package de.zalando.apifirst.generators

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.naming.Reference
import de.zalando.apifirst.naming.dsl._
import org.scalatest.{ FunSpec, MustMatchers }

import scala.language.implicitConversions

/**
 * @author  slasch
 * @since   16.11.2015.
 */
class ScalaModelGeneratorTest extends FunSpec with MustMatchers {

  implicit def types2model(types: TypeLookupTable): StrictModel =
    StrictModel.apply(Nil, types, Map.empty, Map.empty, "", None, Map.empty, Map.empty)

  describe("ScalaGeneratorTest") {
    it("should generate nothing for empty model") {
      new ScalaGenerator(Map.empty[Reference, Domain.Type]).generateModel("test.scala", "test.scala") mustBe ""
    }

    it("should generate single type alias for an option") {
      val model = Map(
        "definitions" / "Opti" -> Opt(Lng(None), None),
        "definitions" / "Stri" -> Opt(Str(None, None), None)
      )
      val result = new ScalaGenerator(model).generateModel("test-file.scala", "`test-file`.scala")
      result mustBeAs
        """package `test-file`
          |package object scala {
          |type Opti = Option[Long]
          |type Stri = Option[String]
          |}
          | """
    }

    it("should deal with overriden type definitions") {
      val model = Map(
        "definitions" / "Option" -> Opt(Lng(None), None),
        "definitions" / "String" -> Opt(Str(None, None), None)
      )
      new ScalaGenerator(model).generateModel("overloaded.txt", "overloaded.txt") mustBeAs
        """package overloaded
          |package object txt {
          |type Option = Option[Long]
          |type String = Option[String]
          |}
          | """
    }

    it("should generate single type alias for an array") {
      val model = Map(
        "definitions" / "Int" -> Arr(Intgr(None), None, "csv"),
        "definitions" / "Dbl" -> Arr(Dbl(None), None, "tsv"),
        "definitions" / "Flt" -> Arr(Flt(None), None, "ssv")
      )
      new ScalaGenerator(model).generateModel("test.scala", "test.scala") mustBeAs
        """package test
          |package object scala {
          |import de.zalando.play.controllers.ArrayWrapper
          |type Int = ArrayWrapper[Int]
          |type Dbl = ArrayWrapper[Double]
          |type Flt = ArrayWrapper[Float]
          |}
          | """
    }

    it("should generate single type alias for catchAll") {
      val model = Map(
        "parameters" / "all" -> CatchAll(Bool(None), None)
      )
      new ScalaGenerator(model).generateModel("test.scala", "test.scala") mustBeAs
        """package test
          |package object scala {
          |import scala.collection.immutable.Map
          |type All = Map[String, Boolean]
          |}
          | """
    }

    it("should generate single type alias for top-level primitive type") {
      val model = Map(
        "paths" / "/" / "get" / "responses" / "200" -> Null(None)
      )
      new ScalaGenerator(model).generateModel("test.scala", "test.scala") mustBeAs
        """"""
    }

    it("should generate a class file for typeDef") {
      val fields = Seq(
        Field("definitions" / "User" / "name", Str(None, None)),
        Field("definitions" / "User" / "id", Lng(None))
      )
      val model = Map(
        "definitions" / "User" -> TypeDef("definitions" / "User", fields, None)
      )
      new ScalaGenerator(model).generateModel("test.scala", "test.scala") mustBeAs
        """package test
          |package object scala {
          |case class User(name: String, id: Long)
          |}
          | """
    }

    it("should generate a type alias for the TypeReference") {
      val model = Map(
        "definitions" / "OptionalData" -> Opt(TypeRef("definitions" / "Passwords"), None),
        "definitions" / "Passwords" -> Arr(Password(None), None, "csv")
      )
      new ScalaGenerator(model).generateModel("test.scala", "test.scala") mustBeAs
        """package test
          |package object scala {
          |import de.zalando.play.controllers.ArrayWrapper
          |type OptionalData = Option[Passwords]
          |type Passwords = ArrayWrapper[String]
          |}
          | """
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
      val strictModel =
        StrictModel(Nil, model, Map.empty, discriminators, "", Some("overriden.package.scala"), Map.empty, Map.empty)

      val result = new ScalaGenerator(strictModel).generateModel("test.scala", "test.scala")

      result mustBeAs
        """package overriden.package
          |package object scala {
          |trait IPet {
          |    def name: String
          |    def petType: String
          |}
          |case class Cat(name: String, petType: String, huntingSkill: String) extends IPet
          |
          |case class Dog(name: String, petType: String, packSize: Int) extends IPet
          |
          |case class CatNDog(name: String, petType: String, packSize: Int, huntingSkill: String) extends IPet
          |
          |case class Pet(name: String, petType: String) extends IPet
          |
          |case class Labrador(name: String, petType: String, packSize: Int, cuteness: Int) extends IPet
          |}
          | """
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
      new ScalaGenerator(model).generateModel("test.scala", "test.scala") mustBeAs
        """package test
          |package object scala {
          |case class ErrorModel(message: String, code: Int)
          |
          |case class ExtendedErrorModel(message: String, code: Int, rootCause: String)
          |}
          | """
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
