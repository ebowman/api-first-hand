package de.zalando.play.controllers

import akka.util.ByteString
import de.zalando.play.controllers.ResponseWriters.choose
import org.specs2.mutable.Specification
import play.api.http.Writeable
import play.api.mvc.RequestHeader

import scala.concurrent.ExecutionContext.Implicits

/**
 * @author slasch
 * @since 28.02.2016.
 */
class ResponseWritersTest extends Specification {

  import TestEnvironment._

  "ResponseWriters" should {
    "find something" in {
      choose("text/plain")[Any](reg) must_== Some(seqText.w)
    }
    "not find anything for wrong mime type" in {
      choose("application/json")[Any](reg) must_== None
    }
    "find something for correct Writable type" in {
      choose("text/plain")[Seq[Any]](reg) must_== Some(seqText.w)
    }
    "find something for different correct Writable type" in {
      choose("text/plain")[String](reg) must_== Some(stringText.w)
    }
    "not find anything for wrong Writable type" in {
      choose("text/plain")[Numeric[_]](reg) must_== None
    }
    "not find anything by default" in {
      choose("text/plain")[Any]() must_== None
    }

  }
}

class WrappedBodyParsersTest extends Specification {

  import TestEnvironment._

  val binaryString = new WrappedBodyParsersBase {
    val binaryString: Parser[BinaryString] =
      byteArray => BinaryString(byteArray.toArray)
    /**
     * This collection contains all {@Writeable}s which could be used in
     * as a marshaller for different mime types and types of response
     */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
      "text/plain" -> binaryString
    )
  }

  val catchAll = new WrappedBodyParsersBase {
    val any: Parser[Any] = byteArray => BinaryString(byteArray.toArray)
    /**
     * This collection contains all {@Writeable}s which could be used in
     * as a marshaller for different mime types and types of response
     */
    override val custom: Seq[(String, ParserWrapper[_])] = Seq(
      "text/plain" -> any
    )
  }

  "WrappedBodyParsers" should {
    "find something" in {
      WrappedBodyParsers.anyParser[Any] must_== Nil
    }
    "not find anything for wrong type" in {
      binaryString.anyParser[String] must_== Nil
    }
    "find something for correct type" in {
      binaryString.anyParser[BinaryString].size must_== 1
    }
    "find something for every type if target is 'Any'" in {
      catchAll.anyParser[String].size must_== 1
      catchAll.anyParser[BinaryString].size must_== 1
    }
  }
}

object TestEnvironment {
  import Implicits.global
  val transformSeq: Seq[Any] => ByteString = a => ByteString.fromString(a.toString)
  val transformStr: String => ByteString = ByteString.fromString

  val seqText: WriteableWrapper[Seq[Any]] = Writeable(transformSeq, Some("text/plain"))

  val stringText: WriteableWrapper[String] = Writeable(transformStr, Some("text/plain"))

  val reg = Seq(seqText, stringText)
}