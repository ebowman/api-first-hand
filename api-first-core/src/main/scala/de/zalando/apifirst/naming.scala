package de.zalando.apifirst

import java.net.URI

import de.zalando.apifirst.naming.Reference
import de.zalando.apifirst.naming.dsl.NameDsl

import scala.annotation.tailrec
import scala.language.{ implicitConversions, postfixOps }

/**
 * @since   03.11.2015.
 */
object naming {

  implicit def uriToReference(uri: URI): Reference = uriFragmentToReference(uri.getFragment)

  def uriFragmentToReference(fragment: String): Reference =
    Reference(Option(fragment).map(_.split("/").mkString(Reference.delimiter)).getOrElse(""))

  object dsl {
    import scala.language.implicitConversions
    implicit def nameToNameOps(name: Reference): NameDsl = new NameDsl(name)
    implicit def stringToName(s: String): Reference = Reference(s)
    class NameDsl(val name: Reference) {
      def /(pp: String): Reference = name.copy(parts = name.parts :+ pp)
      def /(other: Reference): Reference = merge(other)
      private def merge(other: Reference) = name.copy(parts = name.parts ++ other.parts)
    }
  }

  case class Reference(parts: List[String]) {
    import Reference._
    val qualified = if (parts.isEmpty) delimiter else delimiter + parts.mkString(delimiter)
    val simple = if (parts.isEmpty) delimiter else parts.last
    val parent = if (parts.isEmpty) root else Reference(parts.init)
    val tokens = parts
    override def toString: String = qualified
    def /(part: String): Reference = new NameDsl(this) / part
    def /(part: Reference): Reference = new NameDsl(this) / part
    def prepend(part: String): Reference = Reference(part :: parts)
    lazy val isResponsePath = parts.contains(responses)
    lazy val isDefinition = parts.headOption.exists(_ == definitions)
    lazy val isTopResponsePath = parts.last == responses
  }

  object Reference {
    val responses = "responses"
    val definitions = "definitions"
    val delimiter = "⌿"
    val root: Reference = Reference(List.empty)
    def apply(base: String, s: Reference): Reference = s
    def fromUrl(url: String): Reference = parse(url, "/")
    def apply(ref: String): Reference = parse(ref, delimiter)
    def apply(): Reference = Reference.root
    def definition(ref: String): Reference = new Reference(List(definitions, ref))
    private def parse(s: String, delim: String) = {
      val normalized = s.dropWhile(_.toString == delim).split(delim, -1).toList
      val parts = if (normalized.length == 1 && normalized.head.isEmpty) List.empty else normalized
      Reference(parts)
    }
    private def unescape(str: String) = str.replace("~1", "/").replace("~0", "~")
    def deref(jstr: String): Reference = Reference.fromUrl(unescape(jstr.reverse.takeWhile(_ != '#').reverse))
  }

  case class Path(private val reference: Reference) {
    val ref = reference match {
      case Reference(Nil) => Reference(List(""))
      case Reference(parts) => Reference(parts map clean)
    }
    private def sep(c: Char) = c == '/'
    val asPlay = (ref.parts map Path.toString(":", "")).mkString("/", "/", "")
    lazy val asSwagger = ref.parts.mkString("/", "/", "")
    val interpolated = (ref.parts map Path.toString("${toPath(", ")}")).mkString("/", "/", "")
    val asMethod = {
      val newParts = ref.parts map { p => Path.toString("By", "", s => s.capitalize)(p).replace('-', '_') }
      val method = newParts.mkString
      if (method.nonEmpty) method.head.toLower + method.tail else method
    }
    def prepend(prefix: String) = if (prefix != null) Path(ref.prepend(clean(prefix))) else this
    def /(suffix: String) = if (suffix != null && suffix.nonEmpty) Path(ref / clean(suffix)) else this
    private def clean(prefix: String) =
      if (prefix != null && prefix.nonEmpty) prefix.dropWhile(sep).reverse.dropWhile(sep).reverse else prefix
    def map[That](f: String => That) = ref.parts.map(f)
    def nonEmpty = ref.parts.nonEmpty
    def last = ref.parts.last
    def asScala: String = s"""Path(Reference("${ref.qualified}"))"""
  }

  object Path {
    def apply(s: String): Path = Path(Reference.fromUrl(s))
    def pathParam(part: String) = part != null && part.nonEmpty && part.startsWith("{") && part.endsWith("}")
    def strip(part: String) = if (pathParam(part)) part.tail.dropRight(1) else part
    def toString(prefix: String, suffix: String, transform: String => String = identity)(part: String) =
      if (pathParam(part)) s"$prefix${ScalaName.escape(transform(strip(part)))}$suffix" else transform(part)
  }
}

/**
 * Represents scala name converted from the ast pointer
 * This implementation makes heavily use of swagger internal
 * naming model. Probably it should be refactored to support
 * other specification formats
 */
object ScalaName {
  implicit def reference2ScalaName(r: Reference): ScalaName = ScalaName(r)
  private val scalaNames = Seq(
    "abstract", "case", "do", "for", "import", "lazy", "object", "override", "return",
    "var", "while", "class", "false", "if", "new", "package", "sealed", "trait", "try",
    "private", "super", "this", "true", "type", "def", "final", "implicit", "null",
    "protected", "throw", "val", "_", "catch", "else", "extends", "finally", "forSome",
    "match", "with", "yield", "case"
  )

  private val scalaPartNames = Seq(",", ";", ":", "=", "=>", "<-", "<:", "<%", ">:", "#", "@", "⇒", "←", "+", "-", "[", ")", "]", "}")

  @tailrec
  def escape(name: String): String =
    if (scalaPartNames.exists(name.contains))
      escape(scalaPartNames.foldLeft(name)((acc, c) => acc.replaceAllLiterally(c, "_")))
    else if (name.endsWith("_"))
      escape(name + "esc")
    else if (scalaNames.contains(name))
      escape("`" + name + "`")
    else
      name

  def scalaPackageName(fileName: String): String = scalaPackageParts(fileName).mkString(".")
  def scalaPackageParts(fileName: String): Seq[String] = fileName.split('.').map(escape)

}

object StringUtil {

  private val doubleQuoted = "\".+\"".r.pattern

  def capitalize(separator: String, str: String): String = {
    assert(str != null)
    str.split(separator).map { p => if (p.nonEmpty) p.head.toUpper +: p.tail else p }.mkString("")
  }

  def camelize(separator: String, str: String): String = capitalize(separator, str) match {
    case p if p.isEmpty => ""
    case p => p.head.toLower +: p.tail
  }

  def quoteIfNeeded(str: String): String =
    if (doubleQuoted.matcher(str).matches()) str else "\"" + str + "\""

}

case class ScalaName(ref: Reference) {
  import ScalaName._
  import StringUtil._
  val parts = ref.parts.flatMap(_.split("/").filter(_.nonEmpty)) match {
    case Nil =>
      throw new IllegalArgumentException(s"At least one part required to construct a name, but got $ref")
    case single :: Nil => "" :: removeVars(single) :: Nil
    case many => many.map(removeVars)
  }
  private def removeVars(s: String) = if (s.startsWith("{") && s.endsWith("}")) s.substring(1, s.length - 1) else s
  def packageName: String = parts.head.toLowerCase.split("/").filter(_.nonEmpty).map(escape).mkString(".")
  def qualifiedName(prefix: String, suffix: String): (String, String) = (packageName + suffix, typeAlias(prefix, suffix))
  def fullName(prefix: String, suffix: String): String = packageName + suffix + "." + typeAlias(prefix, suffix)
  def className: String = escape(capitalize("/", parts.tail.head))
  def typeAlias(prefix: String = "", suffix: String = ""): String = {
    val withSuffix = if (suffix.trim.isEmpty) parts.tail else parts.tail.:+(suffix)
    val (withPrefix, caseTransformer) =
      if (prefix.trim.isEmpty) (withSuffix, capitalize _) else (prefix :: withSuffix, camelize _)
    escape(caseTransformer("/", withPrefix.mkString("/")))
  }

  def methodName: String = escape(camelize("/", parts.last))
  def names: (String, String, String) = (packageName, className, methodName)
  def methodName(verb: Http.Verb): String = methodName + camelize("/", verb.name.toLowerCase())
}
