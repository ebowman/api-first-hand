package de.zalando.swagger

import de.zalando.apifirst.Application.HandlerCall

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.input.CharSequenceReader

/**
 * @since 17.07.2015
 */
object HandlerParser extends HandlerParser
// handler related part of the play's parser
// we can use it if we won't change handler definition syntax
trait HandlerParser extends JavaTokenParsers {
  import scala.language.{ implicitConversions, postfixOps }

  override def skipWhitespace: Boolean = false

  override val whiteSpace = """[ \t]+""".r

  def EOF: util.matching.Regex = "\\z".r

  def namedError[A](p: Parser[A], msg: String): Parser[A] = Parser[A] { i =>
    p(i) match {
      case Failure(_, in) => Failure(msg, in)
      case o => o
    }
  }

  def several[T](p: => Parser[T]): Parser[List[T]] = Parser { in =>
    import scala.collection.mutable.ListBuffer
    val elems = new ListBuffer[T]
    def continue(in: Input): ParseResult[List[T]] = {
      val p0 = p // avoid repeatedly re-evaluating by-name parser
      @scala.annotation.tailrec
      def applyp(in0: Input): ParseResult[List[T]] = p0(in0) match {
        case Success(x, rest) =>
          elems += x; applyp(rest)
        case Failure(_, _) => Success(elems.toList, in0)
        case err: Error => err
      }
      applyp(in)
    }
    continue(in)
  }

  def separator: Parser[String] = namedError(whiteSpace, "Whitespace expected")

  def ignoreWhiteSpace: Parser[Option[String]] = opt(whiteSpace)

  // This won't be needed when we upgrade to Scala 2.11, we will then be able to use JavaTokenParser.ident:
  // https://github.com/scala/scala/pull/1466
  def javaIdent: Parser[String] = """\p{javaJavaIdentifierStart}\p{javaJavaIdentifierPart}*""".r

  def identifier: Parser[String] = namedError(javaIdent, "Identifier expected")

  def end: util.matching.Regex = """\s*""".r

  def parentheses: Parser[String] = {
    "(" ~ several(parentheses | not(")") ~> """.""".r) ~ commit(")") ^^ {
      case p1 ~ charList ~ p2 => p1 + charList.mkString + p2
    }
  }

  def brackets: Parser[String] = {
    "[" ~ several(parentheses | not("]") ~> """.""".r) ~ commit("]") ^^ {
      case p1 ~ charList ~ p2 => p1 + charList.mkString + p2
    }
  }

  def string: Parser[String] = {
    "\"" ~ several(parentheses | not("\"") ~> """.""".r) ~ commit("\"") ^^ {
      case p1 ~ charList ~ p2 => p1 + charList.mkString + p2
    }
  }

  def space(s: String): Parser[String] = ignoreWhiteSpace ~> s <~ ignoreWhiteSpace

  def stableId: Parser[String] = rep1sep(identifier, space(".")) ^^ (_ mkString ".")

  def expression: Parser[String] = (string | parentheses | brackets | """[^),?=\n]""".r +) ^^ {
    case p => p.mkString
  }

  // Absolute method consists of a series of Java identifiers representing the package name, controller and method.
  // Since the Scala parser is greedy, we can't easily extract this out, so just parse at least 3
  def absoluteMethod: Parser[List[String]] = namedError(javaIdent ~ "." ~ javaIdent ~ "." ~ rep1sep(javaIdent, ".") ^^ {
    case first ~ _ ~ second ~ _ ~ rest => first :: second :: rest
  }, "Controller method call expected")

  def call: Parser[HandlerCall] = opt("@") ~ absoluteMethod ^^ {
    case instantiate ~ absMethod => {
      val (packageParts, classAndMethod) = absMethod.splitAt(absMethod.size - 2)
      val packageName = packageParts.mkString(".")
      val className = classAndMethod.head
      val methodName = classAndMethod(1)
      val dynamic = instantiate.isDefined
      HandlerCall(packageName, className, dynamic, methodName, Nil)
    }
  }

  def router: Parser[String] = rep1sep(identifier, ".") ^^ {
    case parts => parts.mkString(".")
  }

  def parser = phrase(call <~ end) ^^ {
    case r: HandlerCall => r
  }

  def parse(text: String): ParseResult[HandlerCall] = {
    parser(new CharSequenceReader(text))
  }

}