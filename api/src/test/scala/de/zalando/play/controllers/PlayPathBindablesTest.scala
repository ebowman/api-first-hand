package de.zalando.play.controllers

import org.specs2.mutable._

/**
 * @author slasch
 * @since 07.01.2016.
 */
class PlayPathBindablesTest extends Specification {

  "createMapper" should {
    "should read different formats" in {
      val wrapper = PipesArrayWrapper.apply(Seq.empty[String])
      val mapper = PlayPathBindables.createMapper
      val reader = PlayPathBindables.createReader(mapper, wrapper)
      val result = PlayPathBindables.readArray(reader)("a|b|c|d")
      result must have size 4
    }

    "should write different formats" in {
      val wrapper = PipesArrayWrapper.apply(Seq.empty[String])
      val mapper = PlayPathBindables.createMapper
      val writer = PlayPathBindables.createWriter(mapper, wrapper)
      val result = PlayPathBindables.writeArray(writer)(Seq("a", "b", "c", "d"))
      result must_== """"a"|"b"|"c"|"d"""" + "\n"
    }

  }
}
