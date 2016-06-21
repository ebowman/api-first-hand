package de.zalando.play.generator.routes

import de.zalando.apifirst.Application.StrictModel
import de.zalando.apifirst.naming.Path
import org.scalatest.{ FunSpec, MustMatchers }
import play.routes.compiler.{ DynamicPart, StaticPart }

/**
 * @author  slasch
 * @since   18.12.2015.
 */
class RuleGeneratorTest extends FunSpec with MustMatchers {

  implicit val model = StrictModel(Nil, Map.empty, Map.empty, Map.empty, "/base/", None, Map.empty, Map.empty)

  val routes = Map(
    "/" -> Nil, "/a/b/c/d" -> List(StaticPart("a/b/c/d")), "/a/b/c/d/" -> List(StaticPart("a/b/c/d/")), "/a/{b}/c/{d}" -> List(StaticPart("a/"), DynamicPart("b", """[^/]+""", true), StaticPart("/c/"), DynamicPart("d", """[^/]+""", true)), "/{a}/{b}/{c}/{d}/" -> List(DynamicPart("a", """[^/]+""", true), StaticPart("/"), DynamicPart("b", """[^/]+""", true), StaticPart("/"), DynamicPart("c", """[^/]+""", true), StaticPart("/"), DynamicPart("d", """[^/]+""", true), StaticPart("/")), "/{a}/b/{c}/d/" -> List(DynamicPart("a", """[^/]+""", true), StaticPart("/b/"), DynamicPart("c", """[^/]+""", true), StaticPart("/d/"))
  )

  describe("RuleGeneratorTest") {
    routes.foreach {
      case (path, expected) =>
        it(s"should parse $path as expected") {
          val result = RuleGenerator.convertPath(Path(path)).parts
          result must contain theSameElementsInOrderAs expected
        }
    }
  }
}

