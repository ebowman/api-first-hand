package de.zalando.apifirst.util

import de.zalando.apifirst.Application._
import de.zalando.apifirst.Domain._
import de.zalando.apifirst.Hypermedia.{ Self, State }
import de.zalando.apifirst.Security
import de.zalando.apifirst.naming.Reference

/**
 * @since 25.05.2016.
 */
object ScalaPrinter {
  import ScalaString._

  def nameFromModel(ast: AnyRef): String =
    ast.getClass.getName.split("\\.").last.replaceAllLiterally("_yaml$", "") + "_yaml"

  private val imports = Seq(
    "de.zalando.apifirst.Application._",
    "de.zalando.apifirst.Domain._",
    "de.zalando.apifirst.ParameterPlace",
    "de.zalando.apifirst.naming._",
    "de.zalando.apifirst.Hypermedia._",
    "de.zalando.apifirst.Http._",
    "de.zalando.apifirst.Security",
    "java.net.URL",
    "Security._"
  ).map("import " + _).mkString("\n")

  def asScala(fileName: String, ast: StrictModel): String = {
    "package de.zalando.model\n" +
      imports +
      s" \n//noinspection ScalaStyle\nobject ${fileName.replace('.', '_')} extends WithModel {\n" +
      types(ast) +
      "\n" +
      parameters(ast) +
      "\n" +
      basePath(ast) +
      "\n" +
      discriminators(ast) +
      "\n" +
      security(ast) +
      "\n" +
      transitions(ast) +
      s"""
      |def calls: Seq[ApiCall] = ${ast.calls.map(toScalaString("\t\t")).map("\n\t" + _).mkString("Seq(", ", ", ")")}
      |
      |def packageName: Option[String] = ${ast.packageName.map("\"" + _ + "\"")}
      |
      |def model = new StrictModel(calls, types, parameters, discriminators, basePath, packageName, stateTransitions, securityDefinitions)
    """.stripMargin +
      "\n} "
  }

  def transitions(ast: StrictModel): String = {
    val transStr = ast.stateTransitions.map {
      case (k, v) => "\"" + k + "\" -> " + v.map {
        case (kk, vv) =>
          "\"" + kk + "\" -> TransitionProperties(" + vv.condition.map("\"" + _ + "\"") + ")"
      }.mkString("Map[State, TransitionProperties](", ", ", ")")
    }.mkString(", ")
    s"""def stateTransitions: StateTransitionsTable = Map[State, Map[State, TransitionProperties]]($transStr)"""
  }

  def security(ast: StrictModel): String = {
    val securityStr = ast.securityDefinitionsTable.map {
      case (k, v) => "\"" + k + "\" -> " + toScalaString("")(v)
    }.mkString("\n\t", ",\n\t", "\n")
    s""" def securityDefinitions: SecurityDefinitionsTable = Map[String, Security.Definition]($securityStr)"""
  }

  def discriminators(ast: StrictModel): String = {
    val discStr = s"""${ast.discriminators.map { case (k, v) => toScalaString("\t")(k) -> toScalaString("")(v) }.mkString(",\n")}"""
    s" def discriminators: DiscriminatorLookupTable = Map[Reference, Reference](\n\t$discStr)"
  }
  def basePath(ast: StrictModel): String = {
    val path = if (ast.basePath == null) "null" else s""" "${ast.basePath}" """
    s" def basePath: String =$path"
  }

  def types(ast: StrictModel): String = {
    val typeDefs = ast.typeDefs
    val typeMap = typeDefs map { case (k, v) => k -> ("\n\t\t" + toScalaString("\t\t\t")(v)) }
    val lines = typeMap.toSeq.sortBy(_._1.parts.size).map(p => toScalaString("\t")(p._1) + " → " + p._2)
    s" \n def types = Map[Reference, Type](\n${lines.mkString(",\n")}\n) "
  }
  def parameters(ast: StrictModel): String = {
    val params = ast.params
    val lines = params.toSeq.sortBy(_._1.name.parts.size).map(p => "\tParameterRef(" + toScalaString("\t")(p._1.name) + ") → " + toScalaString("")(p._2))
    s" \n def parameters = Map[ParameterRef, Parameter](\n${lines.mkString(",\n")}\n) "
  }

}

object ScalaString {

  def toScalaString(pad: String)(a: AnyRef): String = a match {
    case call: ApiCall => apiCall(pad, call)
    case constraint: Security.OAuth2Constraint => oauth2Constraint(pad, constraint)
    case constraint: Security.Constraint => securityConstraint(pad, constraint)
    case definition: Security.ApiKey => apiKey(pad, definition)
    case definition: Security.OAuth2Definition => oauthDef(definition)
    case definition: Security.Definition => securityDef(pad, definition)

    case t: EnumTrait => enumTrait(pad, t)
    case t: EnumObject => enumObject(pad, t)

    case f: Field => field(pad, f)

    case a: Arr => arr(pad, a)
    case a: ArrResult => arrResult(pad, a)

    case p: Parameter => parameter(pad, p)
    case s: Str => str(pad, s)
    case Self => self(pad)
    case s: State => state(pad, s)
    case s: StateResponseInfo => stateResponseInfo(pad, s)
    case c: HandlerCall => handlerCall(pad, c)

    case d: TypeDef => typeDef(pad, d)

    case r: TypesResponseInfo => typeResponseInfo(pad, r)
    case r: ParameterRef => parameterRef(pad, r)
    case r: TypeRef => typeRef(pad, r)
    case r: Reference => reference(pad, r)
    case c: Composite => composite(pad, c)
    case c: Container => container(pad, c)

    case t: ProvidedType => providedType(pad, t)
    case t: Type => typeStr(pad, t)
    case stringOption: Option[_] if stringOption.nonEmpty && stringOption.get.getClass == classOf[String] =>
      stringOption.map("\"" + _ + "\"").toString
    case option: Option[_] if option.nonEmpty && option.get.isInstanceOf[AnyRef] =>
      "Some(" + toScalaString("")(option.get.asInstanceOf[AnyRef]) + ")"
    case other => pad + other.toString
  }

  private def set[T <: AnyRef: Manifest](set: Set[T], tpe: Option[String] = None): String = {
    val tpeStr = tpe.getOrElse(manifest.runtimeClass.getSimpleName)
    if (set.isEmpty) s"Set.empty[$tpeStr]"
    else set.map(a => toScalaString("")(a)).mkString("Set(", ", ", ")")
  }

  private def paddedSet[T <: AnyRef: Manifest](set: Set[T], pad: String, tpe: Option[String] = None): String = {
    val tpeStr = tpe.getOrElse(manifest.runtimeClass.getSimpleName)
    if (set.isEmpty) s"Set.empty[$tpeStr]"
    else set.map(a => toScalaString(pad)(a)).mkString(s"Set($pad\t", s",$pad\t", s"$pad)")
  }

  private def apiCall(pad: String, call: ApiCall): String = {
    val errorStr = if (call.errorMapping.isEmpty)
      "Map.empty[String, Seq[Class[Exception]]]"
    else call.errorMapping.map {
      case (k, v) =>
        "\"" + k + "\" -> Seq(" + v.map("classOf[" + _.getCanonicalName + "]").mkString(", ") + ")"
    }.mkString("Map(", ", ", ")")

    val mimeInStr = set(call.mimeIn)
    val mimeOutStr = set(call.mimeOut)
    val securityStr = paddedSet(call.security, "\n\t\t", Some("Security.Constraint"))

    s"""ApiCall(${call.verb}, ${call.path.asScala},
        |$pad${toScalaString(pad)(call.handler)},
        |$pad$mimeInStr,
        |$pad$mimeOutStr,
        |$pad$errorStr,
        |${toScalaString(pad)(call.resultTypes)},
        |$pad${toScalaString(pad + "\t")(call.targetStates)},
        |$pad$securityStr)""".stripMargin
  }

  private def securityDef(pad: String, d: Security.Definition): String =
    s"""${d.getClass.getSimpleName}(${toScalaString("")(d.description)})"""

  private def apiKey(pad: String, k: Security.ApiKey): String =
    s"""ApiKey(${toScalaString("")(k.description)}, "${k.name}", ParameterPlace.withName("${k.in}"))"""

  private def oauthDef(d: Security.OAuth2Definition): String = {
    val scopesStr = d.scopes.map { case (k, v) => s""" "$k" -> "${v.replace('\n', ' ')}" """ }.mkString(", ")
    s"""OAuth2Definition(${toScalaString("")(d.description)}, ${d.validationURL.map("new URL(\"" + _ + "\")")}, Map[String, String]($scopesStr))""".stripMargin
  }

  private def securityConstraint(pad: String, c: Security.Constraint): String =
    s"""${c.getClass.getSimpleName}("${c.name}", ${toScalaString(pad)(c.definition)})"""

  private def oauth2Constraint(pad: String, c: Security.OAuth2Constraint): String =
    s"""${c.getClass.getSimpleName}("${c.name}", ${toScalaString(pad)(c.definition)}, Set(${c.scopes.map("\"" + _ + "\"").mkString(", ")}))"""

  private def typeResponseInfo(pad: String, i: TypesResponseInfo): String = {
    val resStr =
      if (i.results.isEmpty) "Map.empty[Int, ParameterRef]"
      else s"\n$pad\tMap[Int, ParameterRef](" + i.results.map { case (k, v) => s"\n$pad\t$k -> " + toScalaString("")(v) }.mkString(",") + s"\n$pad)"
    s"""${pad}TypesResponseInfo($resStr, ${i.default.map(toScalaString("\t"))})"""
  }

  private def parameterRef(pad: String, r: ParameterRef): String =
    s"""${pad}ParameterRef(${toScalaString("")(r.name)})"""

  private def typeRef(pad: String, r: TypeRef): String =
    s"TypeRef(${toScalaString("")(r.name)})"

  private def reference(pad: String, r: Reference): String =
    s"""${pad}Reference("${r.qualified}")"""

  private def composite(pad: String, c: Composite): String = {
    val descStr = s""" Seq(${c.descendants.map(toScalaString(pad + "\t")).mkString(s"\n$pad", s",\n$pad", "")}) """
    s"$pad${c.getClass.getSimpleName}(${toScalaString("")(c.name)}, ${c.meta}, $descStr, ${toScalaString("")(c.root)})"
  }

  private def container(pad: String, c: Container): String =
    s"${c.getClass.getSimpleName}(${toScalaString(pad)(c.tpe)}, ${c.meta})"

  private def providedType(pad: String, t: Type): String =
    t.getClass.getSimpleName + "(" + t.meta + ")"

  private def typeStr(pad: String, t: Type): String =
    t.getClass.getSimpleName + "(" + t.name + ", " + t.meta + ")"

  private def arr(pad: String, a: Arr): String =
    s"""Arr(${toScalaString(pad)(a.tpe)}, ${a.meta}, "${a.format}")"""

  private def arrResult(pad: String, a: ArrResult): String =
    s"""ArrResult(${toScalaString(pad)(a.tpe)}, ${a.meta})"""

  private def field(pad: String, f: Field): String =
    s"""${pad}Field(${toScalaString("")(f.name)}, ${toScalaString(pad + "\t")(f.tpe)})"""

  private def typeDef(pad: String, t: TypeDef): String =
    s"""TypeDef(${toScalaString("")(t.name)}, \n\t\t\tSeq(${t.fields.map(toScalaString(pad)).mkString("\n\t\t", ",\n\t\t", "")}\n\t\t\t), ${t.meta})"""

  private def enumTrait(pad: String, t: EnumTrait): String = {
    val leavesStr = t.leaves.map(toScalaString(pad + "\t\t")).mkString("\n", ",\n", "\n")
    s"""${pad}EnumTrait(${toScalaString("")(t.tpe)}, ${t.meta}, \n$pad\tSet($leavesStr\n$pad\t))"""
  }

  private def enumObject(pad: String, t: EnumObject): String =
    s"""${pad}EnumObject(${toScalaString("")(t.tpe)}, "${t.fieldValue}", ${t.meta})"""

  private def parameter(pad: String, p: Parameter): String =
    s"""Parameter("${p.name}", ${toScalaString("")(p.typeName)}, ${toScalaString("")(p.fixed)}, ${toScalaString("")(p.default)}, "${p.constraint}", encode = ${p.encode}, ParameterPlace.withName("${p.place}"))"""

  private def handlerCall(pad: String, c: HandlerCall): String =
    s"""HandlerCall(\n$pad\t"${c.packageName}",\n$pad\t"${c.controller}",\n$pad\tinstantiate = ${c.instantiate},\n$pad\t"${c.method}",parameters = ${c.parameters.map(toScalaString(pad + "\t\t")).mkString(s"\n$pad\tSeq(\n", ",\n", s"\n$pad\t\t)\n")}$pad\t)"""

  private def stateResponseInfo(pad: String, t: StateResponseInfo): String = {
    val resStr =
      if (t.results.isEmpty) "Map.empty[Int, State]"
      else s"\n$pad\tMap[Int, State](" + t.results.map { case (k, v) => s"\n$pad\t\t$k -> " + toScalaString("")(v) }.mkString(",") + s"\n$pad)"
    s"""StateResponseInfo($resStr, ${toScalaString("")(t.default)})"""
  }

  private def str(pad: String, s: Str): String =
    s"""Str(${toScalaString("")(s.format)}, ${s.meta})"""

  private def state(pad: String, s: State): String =
    s"""${pad}NamedState("${s.name}")"""

  private def self(pad: String): String = pad + "Self"

}

