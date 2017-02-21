package de.zalando.apifirst

import de.zalando.apifirst.Domain.{ Field, Opt, Str, TypeDef, TypeMeta, TypeRef }
import de.zalando.apifirst.naming.Reference
import org.scalatest._

import scala.language.implicitConversions

class TypeAnalyzerTest extends FunSpec with Matchers {

  private def analyzer = new TypeAnalyzer {}

  describe("isComplexType") {
    it("should be false for Opt[Str]") {
      val tpe = Opt(Str(None, TypeMeta(None, List())), TypeMeta(None))
      analyzer.isComplexType(tpe) shouldBe false
    }

    it("should be false for Str") {
      val tpe = Str(None, TypeMeta(None, List()))
      analyzer.isComplexType(tpe) shouldBe false
    }

    it("should be true for TypeDef") {
      val tpe = TypeDef(
        Reference("⌿definitions⌿Basic"),
        Seq(
          Field(Reference("⌿definitions⌿Basic⌿optional"), Opt(TypeRef(Reference("⌿definitions⌿Basic⌿optional")), TypeMeta(None, List())))
        ),
        TypeMeta(Some("Named types: 1"), List())
      )
      analyzer.isComplexType(tpe) shouldBe true
    }

    it("should be false for Opt[TypeDef]") {
      val tpe = Opt(
        TypeDef(
          Reference("⌿definitions⌿Basic"),
          Seq(
            Field(Reference("⌿definitions⌿Basic⌿optional"), Opt(TypeRef(Reference("⌿definitions⌿Basic⌿optional")), TypeMeta(None, List())))
          ),
          TypeMeta(Some("Named types: 1"), List())
        ),
        TypeMeta(None)
      )
      analyzer.isComplexType(tpe) shouldBe false
    }
  }

  describe("isRecursiveComplexType") {
    it("should be true for Opt[TypeDef]") {
      val tpe = Opt(
        TypeDef(
          Reference("⌿definitions⌿Basic"),
          Seq(
            Field(Reference("⌿definitions⌿Basic⌿optional"), Opt(TypeRef(Reference("⌿definitions⌿Basic⌿optional")), TypeMeta(None, List())))
          ),
          TypeMeta(Some("Named types: 1"), List())
        ),
        TypeMeta(None)
      )
      analyzer.isRecursiveComplexType(tpe) shouldBe true
    }
  }

}
