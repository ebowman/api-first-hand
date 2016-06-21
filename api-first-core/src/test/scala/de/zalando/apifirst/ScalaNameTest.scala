package de.zalando.apifirst

import de.zalando.apifirst.ScalaName._
import de.zalando.apifirst.naming.dsl._
import org.scalatest.{ FunSpec, MustMatchers }
/**
 * @author  slasch
 * @since   16.11.2015.
 */
class ScalaNameTest extends FunSpec with MustMatchers {

  it("must correctly capitalize names") {
    ("one" / "two" / "three").names mustBe (("one", "Two", "three"))
    ("ONE" / "TWO" / "THREE").names mustBe (("one", "TWO", "tHREE"))
    ("OnE" / "TwO" / "ThReE").names mustBe (("one", "TwO", "thReE"))
  }

  it("must correctly recognize short names") {
    ("one" / "two").names mustBe (("one", "Two", "two"))
  }

  it("must correctly escape scala names") {
    ("catch" / "if" / "match").names mustBe (("`catch`", "If", "`match`"))
  }

  it("must be able to produce import statemets") {
    ("java.util" / "date").qualifiedName("", "") mustBe (("java.util", "Date"))
  }

  it("must correctly concat names") {
    ("definitions" / "Example" / "nestedArrays" / "Opt" / "Arr:").names mustBe (("definitions", "Example", "arr_esc"))
  }

}
