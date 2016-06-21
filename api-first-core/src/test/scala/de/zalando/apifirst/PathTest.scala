package de.zalando.apifirst

import de.zalando.apifirst.naming.Path
import org.scalatest.{ FunSpec, MustMatchers }

class PathTest extends FunSpec with MustMatchers {

  describe("Path") {
    it("should convert the root path") {
      val root = Path("/")
      root.asSwagger mustBe "/"
      root.prepend("/echo/").asSwagger mustBe "/echo/"
      root.prepend("/echo").asSwagger mustBe "/echo/"
    }
    it("should convert absolute segments") {
      val a = Path("/a")
      a.asSwagger mustBe "/a"
      a.prepend("/echo/").asSwagger mustBe "/echo/a"
      a.prepend("/echo").asSwagger mustBe "/echo/a"
    }
    it("should convert relative segments") {
      val a = Path("a")
      a.asSwagger mustBe "/a"
      a.prepend("/echo/").asSwagger mustBe "/echo/a"
      a.prepend("/echo").asSwagger mustBe "/echo/a"
    }
    it("should convert absolute segments with trailing slash") {
      val a = Path("/a/")
      a.asSwagger mustBe "/a/"
      a.prepend("/echo/").asSwagger mustBe "/echo/a/"
      a.prepend("/echo").asSwagger mustBe "/echo/a/"
    }

    it("should convert nested path segments") {
      val a = Path("a/b")
      a.asSwagger mustBe "/a/b"
      a.prepend("/echo/").asSwagger mustBe "/echo/a/b"
      a.prepend("/echo").asSwagger mustBe "/echo/a/b"
    }
    it("should convert in-path parameters") {
      val a = Path("/{a}")
      a.asSwagger mustBe "/{a}"
      a.prepend("/echo/").asSwagger mustBe "/echo/{a}"
      a.prepend("/echo").asSwagger mustBe "/echo/{a}"

      a.interpolated mustBe "/${toPath(a)}"
      a.prepend("/echo/").interpolated mustBe "/echo/${toPath(a)}"
      a.prepend("/echo").interpolated mustBe "/echo/${toPath(a)}"
      a.asPlay mustBe "/:a"
      a.prepend("/echo/").asPlay mustBe "/echo/:a"
      a.prepend("/echo").asPlay mustBe "/echo/:a"
      a.asMethod mustBe "byA"
      a.prepend("/echo/").asMethod mustBe "echoByA"
      a.prepend("/echo").asMethod mustBe "echoByA"
    }

    it("should convert in-path parameters with trailing slash") {
      val a = Path("/a/{a}/")
      a.asSwagger mustBe "/a/{a}/"
      a.prepend("/echo/").asSwagger mustBe "/echo/a/{a}/"
      a.prepend("/echo").asSwagger mustBe "/echo/a/{a}/"

      a.interpolated mustBe "/a/${toPath(a)}/"
      a.prepend("/echo/").interpolated mustBe "/echo/a/${toPath(a)}/"
      a.prepend("/echo").interpolated mustBe "/echo/a/${toPath(a)}/"
      a.asPlay mustBe "/a/:a/"
      a.prepend("/echo/").asPlay mustBe "/echo/a/:a/"
      a.prepend("/echo").asPlay mustBe "/echo/a/:a/"
      a.asMethod mustBe "aByA"
      a.prepend("/echo/").asMethod mustBe "echoAByA"
      a.prepend("/echo").asMethod mustBe "echoAByA"
    }

    it("should convert multiple in-path parameters") {
      val a = Path("/a/{b}/{c}/d/{e}")
      a.asSwagger mustBe "/a/{b}/{c}/d/{e}"
      a.prepend("/echo/").asSwagger mustBe "/echo/a/{b}/{c}/d/{e}"
      a.prepend("/echo").asSwagger mustBe "/echo/a/{b}/{c}/d/{e}"

      a.interpolated mustBe "/a/${toPath(b)}/${toPath(c)}/d/${toPath(e)}"
      a.prepend("/echo/").interpolated mustBe "/echo/a/${toPath(b)}/${toPath(c)}/d/${toPath(e)}"
      a.prepend("/echo").interpolated mustBe "/echo/a/${toPath(b)}/${toPath(c)}/d/${toPath(e)}"
      a.asPlay mustBe "/a/:b/:c/d/:e"
      a.prepend("/echo/").asPlay mustBe "/echo/a/:b/:c/d/:e"
      a.prepend("/echo").asPlay mustBe "/echo/a/:b/:c/d/:e"
      a.asMethod mustBe "aByBByCDByE"
      a.prepend("/echo/").asMethod mustBe "echoAByBByCDByE"
      a.prepend("/echo").asMethod mustBe "echoAByBByCDByE"
    }
  }
}
