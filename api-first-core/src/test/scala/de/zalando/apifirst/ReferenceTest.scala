package de.zalando.apifirst

import de.zalando.apifirst.Domain._
import de.zalando.apifirst.naming._
import de.zalando.apifirst.naming.dsl._
import org.scalatest.{ FunSpec, MustMatchers }

class ReferenceTest extends FunSpec with MustMatchers {
  describe("Reference") {

    it("can be created from absolute URI strings, optionally containing pointer fragments") {
      Reference("file:/swagger.yaml") mustBe Reference("file:/swagger.yaml")
      Reference("http://goo.gl/swagger.yaml") mustBe Reference("http://goo.gl/swagger.yaml")
      Reference("file:/swagger.yaml#/foo/bar") mustBe Reference("file:/swagger.yaml#/foo/bar")
    }

    it("can be created containing pointer fragments identifying a path segment") {
      ("{foo}" / "{bar}").parent mustBe Reference("{foo}")
    }

    it("must be able to append pointer tokens") {
      val base = Reference("file:/swagger.yaml")
      base / "foo" mustBe "file:/swagger.yaml" / "foo"
      base / "foo" / "bar" mustBe "file:/swagger.yaml" / "foo" / "bar"
    }

    it("must be able to append pointers") {
      val base = Reference("file:/swagger.yaml")
      val foo = Reference("foo")
      val bar = Reference("bar")
      base / foo mustBe "file:/swagger.yaml" / "foo"
      base / foo / bar mustBe "file:/swagger.yaml" / "foo" / "bar"
      base / foo / "" / bar mustBe "file:/swagger.yaml" / "foo" / "" / "bar"
    }

    it("must be able to prepend pointer tokens") {
      val reference = "file:/swagger.yaml" / "bar"
      reference.prepend("foo") mustBe "foo" / "file:/swagger.yaml" / "bar"
    }

    it("must return a pointers parent reference or itself if no parent pointer reference exists") {
      val base = Reference("file:/swagger.yaml")
      (base / "foo" / "bar").parent mustBe "file:/swagger.yaml" / "foo"
      (base / "foo").parent mustBe Reference("file:/swagger.yaml")
      base.parent mustBe Reference("")
    }

    it("must ignore starting # while comparing references") {
      val one = TypeDef(Reference("/definitions/ErrorModel"), Seq(
        new Field(Reference("/definitions/ErrorModel/message"), Str(None, TypeMeta(None))),
        new Field(Reference("/definitions/ErrorModel/code"), Intgr(TypeMeta(None)))
      ), TypeMeta(None))
      val two = TypeDef(Reference("#/definitions/ErrorModel"), Seq(
        new Field(Reference("#/definitions/ErrorModel/message"), Str(None, TypeMeta(None))),
        new Field(Reference("#/definitions/ErrorModel/code"), Intgr(TypeMeta(None)))
      ), TypeMeta(None))

    }
  }
}
