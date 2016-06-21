package de.zalando.apifirst

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.naming.dsl._
import de.zalando.apifirst.naming.Reference
import org.scalatest.{ FunSpec, MustMatchers }

/**
 * @author  slasch
 * @since   12.11.2015.
 */
class TypeDeduplicatorTest extends FunSpec with MustMatchers {

  describe("TypeDeduplicator") {
    it("should deduplicate container Opt types") {
      testContainerType(Opt.apply)
    }
    it("should deduplicate container CatchAll types") {
      testContainerType(CatchAll.apply)
    }
    it("should deduplicate composition OneOf types") {
      testCompositionType(OneOf.apply)
    }
    it("should deduplicate composition AllOf types") {
      testCompositionType(OneOf.apply)
    }
    it("should deduplicate TypeReferences") {
      testTypeReferences(TypeRef)
    }
    it("should deduplicate TypeDefs") {
      testTypeDef(TypeDef.apply)
    }
    it("should choose types with discriminators if possible") {
      testDiscriminator(TypeDef.apply)
    }
  }

  private val reference1 = "paths" / "users" / "get" / "limit"
  private val reference2 = "definitions" / "queryLimit"

  def testContainerType[T](constructor: (Type, TypeMeta) => Type): Unit = {
    val duplicates = Map[Reference, Type](
      reference1 -> constructor(Intgr(None), TypeMeta(None)),
      reference2 -> constructor(Intgr(None), TypeMeta(Some("Limit for search queries")))
    )
    checkExpectations(duplicates)
  }

  def testCompositionType[T](constructor: (TypeName, TypeMeta, Seq[Type]) => Type): Unit = {
    val descendants: Seq[Type] = Seq(
      TypeRef("a"), TypeRef("b"), TypeRef("c")
    )
    val duplicates = Map[Reference, Type](
      reference1 -> constructor(reference1, TypeMeta(None), descendants),
      reference2 -> constructor("definitions" / "limit", TypeMeta(Some("Limit for search queries")), descendants)
    )
    checkExpectations(duplicates)
  }

  def testTypeReferences(constructor: Reference => TypeRef): Unit = {
    val duplicates = Map[Reference, Type](
      reference1 -> constructor("wohooo"),
      reference2 -> constructor("wohooo")
    )
    checkExpectations(duplicates)
  }

  def testTypeDef(constructor: (TypeName, Seq[Field], TypeMeta) => TypeDef): Unit = {
    val fields: Seq[Field] = Seq(
      Field("a", Lng(TypeMeta(None))),
      Field("b", Str(None, TypeMeta(None))),
      Field("c", Date(TypeMeta(None)))
    )

    val duplicates = Map[Reference, Type](
      reference1 -> constructor("wohooo1", fields, TypeMeta(None)),
      reference2 -> constructor("wohooo2", fields, TypeMeta(Some("Limit for search queries")))
    )
    checkExpectations(duplicates)
  }

  def testDiscriminator(constructor: (TypeName, Seq[Field], TypeMeta) => TypeDef): Unit = {
    val longName = "very" / "deep" / "nested" / "type" / "with" / "discriminator"
    val fields: Seq[Field] = Seq(
      Field("a", Lng(TypeMeta(None))),
      Field("b", Str(None, TypeMeta(None))),
      Field("c", Date(TypeMeta(None)))
    )
    val discriminators = Map(
      longName -> longName / "a"
    )
    val duplicates = Map[Reference, Type](
      longName -> constructor(longName, fields, TypeMeta(None)),
      reference1 -> constructor("definitions" / "nothing-special", fields, TypeMeta(Some("Limit for search queries")))
    )
    checkExpectations(duplicates, discriminators)
  }

  def checkExpectations[T](types: Map[Reference, Type], discriminators: Application.DiscriminatorLookupTable = Map.empty): Unit = {
    val params: ParameterLookupTable = Map(
      ParameterRef(reference1) -> Parameter("limit1", TypeRef(reference1), None, None, "", encode = false, ParameterPlace.BODY),
      ParameterRef(reference2) -> Parameter("limit1", TypeRef(reference2), None, None, "", encode = false, ParameterPlace.BODY)
    )

    val expectedTypes = types - reference1
    val model = StrictModel(Nil, types, params, discriminators, "", None, Map.empty, Map.empty)
    val result = TypeDeduplicator(model)
    result.typeDefs mustBe expectedTypes

    // for discriminators check, params are different
    if (discriminators.isEmpty)
      result.params.foreach(_._2.typeName.asInstanceOf[TypeRef].name mustBe reference2)
  }

}
