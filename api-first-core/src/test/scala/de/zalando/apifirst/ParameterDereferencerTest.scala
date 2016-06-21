package de.zalando.apifirst

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.naming.Reference
import org.scalatest.{ FunSpec, MustMatchers }

/**
 * @author  slasch
 * @since   15.11.2015.
 */
class ParameterDereferencerTest extends FunSpec with MustMatchers {

  describe("ParameterDereferencer") {
    it("should change nothing if parameters contain only references") {
      testChangeNothing()
    }

    it("should dereference container Opt types") {
      testContainerType(Opt.apply)
    }
    it("should dereference container CatchAll types") {
      testContainerType(CatchAll.apply)
    }
    it("should dereference  composition OneOf types") {
      testCompositionType(OneOf.apply)
    }
    it("should dereference  composition AllOf types") {
      testCompositionType(OneOf.apply)
    }
    it("should dereference TypeDefs") {
      testTypeDef(TypeDef.apply)
    }
  }

  private val reference1: Reference = Reference("/paths/users/get/limit")
  private val reference2: Reference = Reference("/paths/users/{id}/get/id")

  def testChangeNothing[T](): Unit = {
    val types = Map[Reference, Type](
      reference1 -> Intgr(Some("Limit for search queries")),
      reference2 -> Intgr(None)
    )
    val params: ParameterLookupTable = Map(
      ParameterRef(reference1) -> Parameter("limit", TypeRef(reference1), None, None, "", encode = false, ParameterPlace.BODY),
      ParameterRef(reference2) -> Parameter("id", TypeRef(reference2), None, None, "", encode = false, ParameterPlace.BODY)
    )
    checkExpectations(types)(params)
  }

  def testContainerType[T](constructor: (Type, TypeMeta) => Type): Unit = {
    val types = Map[Reference, Type](
      reference1 -> constructor(Intgr(None), TypeMeta(None)),
      reference2 -> constructor(Intgr(None), TypeMeta(Some("Limit for search queries")))
    )
    checkExpectations(types)()
  }

  def testCompositionType[T](constructor: (TypeName, TypeMeta, Seq[Type]) => Type): Unit = {
    val descendants: Seq[Type] = Seq(
      TypeRef(Reference("a")), TypeRef(Reference("b")), TypeRef(Reference("c"))
    )
    val types = Map[Reference, Type](
      reference1 -> constructor(reference1, TypeMeta(None), descendants),
      reference2 -> constructor(reference2, TypeMeta(Some("Limit for search queries")), descendants)
    )
    checkExpectations(types)()
  }

  def testTypeDef(constructor: (TypeName, Seq[Field], TypeMeta) => TypeDef): Unit = {
    val fields: Seq[Field] = Seq(
      Field(Reference("a"), Lng(TypeMeta(None))),
      Field(Reference("b"), Str(None, TypeMeta(None))),
      Field(Reference("c"), Date(TypeMeta(None)))
    )

    val types = Map[Reference, Type](
      reference1 -> constructor(reference1, fields, TypeMeta(None)),
      reference2 -> constructor(reference2, fields, TypeMeta(Some("Limit for search queries")))
    )
    checkExpectations(types)()
  }

  def parametersFromTypes(types: TypeLookupTable): Map[ParameterRef, Parameter] =
    Map(
      ParameterRef(reference1) -> Parameter("limit", types(reference1), None, None, "", encode = false, ParameterPlace.BODY),
      ParameterRef(reference2) -> Parameter("id", types(reference2), None, None, "", encode = false, ParameterPlace.BODY)
    )

  def checkExpectations[T](types: TypeLookupTable)(params: ParameterLookupTable = parametersFromTypes(types)): Unit = {
    val model = StrictModel(Nil, types, params, Map.empty, "", None, Map.empty, Map.empty)
    val result = ParameterDereferencer(model)
    types.foreach { t => result.typeDefs.contains(t._1) mustBe true }
    result.typeDefs.size mustBe (types.size + params.count(!_._2.typeName.isInstanceOf[TypeRef]))
    result.params.foreach(_._2.typeName.isInstanceOf[TypeRef] mustBe true)
    result.params.size mustBe 2
  }

}
