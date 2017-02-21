package de.zalando.apifirst

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.naming.Reference
import de.zalando.apifirst.naming.dsl._
import org.scalatest.{ FunSpec, MustMatchers }

import scala.language.implicitConversions

/**
 * @author  slasch
 * @since   15.11.2015.
 */
class TypeFlattenerTest extends FunSpec with MustMatchers {

  implicit def types2model(types: TypeLookupTable): StrictModel =
    StrictModel.apply(Nil, types, Map.empty, Map.empty, "", None, Map.empty, Map.empty)

  private val noMeta = TypeMeta(None)

  private val reference1: Reference = "paths/users/get/limit"

  private val wohooo2 = TypeDef("wohooo2", Seq(Field("d", Null(noMeta))), noMeta)

  private val fields: Seq[Field] = Seq(
    Field("a", Lng(noMeta)),
    Field("b", Opt(Str(None, noMeta), noMeta)),
    Field("c", wohooo2)
  )
  private val wohooo1 = TypeDef("wohooo1", fields, noMeta)

  describe("TypeFlattener") {

    it("should not flatten nested Opt types") {
      val nested = Map[Reference, Type](
        reference1 -> Opt(Opt(Intgr(None), noMeta), noMeta)
      )
      val flat = TypeFlattener(nested)
      flat.typeDefs mustBe Map[Reference, Type](
        reference1 -> Opt(Opt(Intgr(None), noMeta), noMeta)
      )
    }

    it("should not flatten nested Arr types") {
      val nested = Map[Reference, Type](
        reference1 -> Arr(Arr(Arr(Intgr(None), noMeta, "tsv"), noMeta, "csv"), noMeta, "pipes")
      )
      val flat = TypeFlattener(nested)
      flat.typeDefs mustBe Map[Reference, Type](
        reference1 -> Arr(Arr(Arr(Intgr(None), noMeta, "tsv"), noMeta, "csv"), noMeta, "pipes")
      )
    }

    it("should not flatten nested ArrResult types") {
      val nested = Map[Reference, Type](
        reference1 -> ArrResult(ArrResult(ArrResult(Intgr(None), noMeta), noMeta), noMeta)
      )
      val flat = TypeFlattener(nested)
      flat.typeDefs mustBe Map[Reference, Type](
        reference1 -> ArrResult(ArrResult(ArrResult(Intgr(None), noMeta), noMeta), noMeta)
      )
    }

    it("should flatten CatchAll types") {
      val nested = Map[Reference, Type](
        reference1 -> CatchAll(Opt(Intgr(None), noMeta), noMeta)
      )
      val flat = TypeFlattener(nested)
      flat.typeDefs mustBe Map(
        reference1 -> CatchAll(Opt(Intgr(noMeta), noMeta), noMeta)
      )
    }

    it("should flatten OneOf types") {
      val nested = Map[Reference, Type](
        reference1 -> OneOf(reference1, noMeta,
          Seq(
            Opt(Intgr(None), noMeta),
            Arr(Str(None, None), noMeta, "csv")
          ))
      )
      val flat = TypeFlattener(nested)
      flat.typeDefs mustBe Map(
        reference1 -> OneOf(reference1, noMeta,
          Seq(
            Opt(Intgr(None), noMeta),
            Arr(Str(None, None), noMeta, "csv")
          ))
      )
    }

    it("should flatten AllOf types") {
      val nested = Map[Reference, Type](
        reference1 -> AllOf(reference1, noMeta,
          Seq(wohooo2, TypeDef("wohooo3", fields, noMeta)))
      )
      val flat = TypeFlattener(nested)
      flat.typeDefs mustBe Map(
        reference1 -> AllOf(reference1, noMeta, List(TypeRef(reference1 / "AllOf0"), TypeRef(reference1 / "AllOf1"))),
        reference1 / "AllOf0" -> wohooo2,
        reference1 / "AllOf1" ->
          TypeDef(
            "wohooo3",
            Seq(
              Field(Reference("a"), Lng(noMeta)),
              Field(Reference("b"), Opt(Str(None, noMeta), noMeta)),
              Field(Reference("c"), TypeRef("wohooo3" / "c"))
            ), noMeta
          ),
        "wohooo3" / "c" -> wohooo2
      )
    }

    it("should flatten TypeDefs") {

      val nested = Map[Reference, Type](
        reference1 -> wohooo1
      )
      val flat = TypeFlattener(nested)
      flat.typeDefs mustBe Map(
        reference1 -> TypeDef(
          "wohooo1",
          Seq(
            Field("a", Lng(noMeta)),
            Field("b", Opt(Str(None, TypeMeta(None, List())), TypeMeta(None, List()))),
            Field("c", TypeRef("wohooo1" / "c"))
          ), noMeta
        ),
        "wohooo1" / "c" -> wohooo2
      )
    }

  }
}
