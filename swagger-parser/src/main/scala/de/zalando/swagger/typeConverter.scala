package de.zalando.swagger

import java.net.URI

import de.zalando.apifirst.Domain
import Domain._
import TypeMetaConverter._
import de.zalando.apifirst.naming._
import strictModel._

import scala.collection.mutable
import scala.language.{ implicitConversions, postfixOps }

/**
 * @author  slasch
 * @since   14.10.2015.
 */
class TypeConverter(base: URI, model: strictModel.SwaggerModel, keyPrefix: String) extends ParameterNaming with DiscriminatorMemoizer {

  lazy val convert: NamedTypes =
    fromDefinitions(model.definitions) ++
      fromPaths(model.paths) ++
      fromParameters(model.parameters)

  private type TypeConstructor = TypeMeta => Type
  private type TypeConstructors = Seq[TypeConstructor]

  private def fromParameters(parameters: ParameterDefinitions): NamedTypes =
    Option(parameters).toSeq.flatten flatMap { p =>
      fromParamListItem(base / "parameters" / p._1, p._2)
    }

  private def fromDefinitions(definitions: Definitions): NamedTypes =
    Option(definitions).toSeq.flatten flatMap { d =>
      fromSchema(base / "definitions" / d._1, d._2, None)
    }

  private def fromPaths(paths: Paths): NamedTypes =
    fromPathParameters(paths) ++ fromResponses(paths).flatten ++ fromOperationParameters(paths).toSeq.flatten

  private def fromPathParameters(paths: Paths): NamedTypes =
    allPathItems(paths) flatMap fromNamedParamListItem

  private def forAllOperations[T](paths: Paths, logic: (Reference, Operation) => T) = for {
    (prefix, path) <- Option(paths).toSeq.flatten
    operationName <- path.operationNames
    operation = path.operation(operationName)
    name = base / "paths" / prefix / operationName
  } yield logic(name, operation)

  private def fromOperationParameters(paths: Paths): Iterable[NamedTypes] =
    forAllOperations(paths, parametersCollector)

  private def parametersCollector(name: Reference, operation: Operation): NamedTypes =
    Option(operation.parameters).toSeq.flatten flatMap {
      fromParamListItem(name, _)
    }

  private def allPathItems(paths: Paths): Seq[(Reference, ParametersListItem)] = for {
    (url, pathItem) <- Option(paths).toSeq.flatten
    parameterList <- Option(pathItem.parameters).toSeq
    paramListItem <- parameterList
    name = base / "paths" / url / ""
  } yield name -> paramListItem

  private def responseCollector: (Reference, Operation) => (Reference, Responses) = (name, op) => name -> op.responses

  private def fromResponses(paths: Paths): Seq[NamedTypes] = for {
    (prefix, responses) <- forAllOperations(paths, responseCollector)
    (suffix, response) <- responses
    fullName = prefix / Reference.responses / suffix
  } yield fromSchemaOrFileSchema(fullName, response.schema, Some(Nil))

  private def fromNamedParamListItem[T](pair: (Reference, ParametersListItem)): NamedTypes =
    fromParamListItem(pair._1, pair._2)

  private def fromParamListItem[T](name: Reference, param: ParametersListItem): NamedTypes = param match {
    case r: JsonReference => Seq(fromReference(name, r, None))
    case nb: NonBodyParameterCommons[_, _] => Seq(fromNonBodyParameter(name, nb))
    case bp: BodyParameter[_] => fromBodyParameter(name, bp)
    case nbp: NonBodyParameter[_] =>
      throw new IllegalStateException("Something went wrong, this case should not be reachable")
  }

  private def fromBodyParameter[T](name: Reference, param: BodyParameter[T]): NamedTypes =
    fromSchemaOrFileSchema(name / param.name, param.schema,
      if (param.required) Some(Seq(param.name)) else Some(Nil))

  private def fromSchemaOrReference[T](name: Reference, param: SchemaOrReference[T], required: Option[Seq[String]]): NamedTypes =
    Option(param).toSeq flatMap {
      case Left(s) => fromSchema(name, s, required)
      case Right(r: JsonReference) => Seq(fromReference(name, r, required))
    }

  private def fromSchemaOrFileSchema[T](name: Reference, param: SchemaOrFileSchema[T], required: Option[Seq[String]]): NamedTypes =
    param match {
      case any if any == null => Seq(fromNull(name))
      case Left(s: SchemaOrReference[_]) => fromSchemaOrReference(name, s, required)
      case Right(fs: FileSchema[_]) => Seq(fromFileSchema(fs, required))
    }

  private def fromSchemaOrSchemaArray[T](name: Reference, param: SchemaOrSchemaArray[T], required: Option[Seq[String]]): NamedTypes =
    param match {
      case Right(sa) => fromSchemaArray(name, sa, required)
      case Left(sr) => fromSchemaOrReference(name, sr, required)
    }

  private def fromSchemaArray(name: Reference, sa: SchemaArray, required: Option[Seq[String]]): NamedTypes =
    sa flatMap { s => fromSchemaOrFileSchema(name, s, required) }

  private def fromSchema[T](name: Reference, param: Schema[_], required: Option[Seq[String]]): NamedTypes = {
    val tpe = if (param.`type` != null) param.`type` else PrimitiveType.OBJECT
    tpe match {
      case t: ArrayJsonSchemaType => Seq(fromArrayJsonSchema(name, param, t))
      case p: PrimitiveType.Val => fromPrimitiveType(name, param, p, required)
    }
  }

  private def fromPrimitiveType(name: Reference, param: Schema[_], p: PrimitiveType.Val, required: Option[Seq[String]]): NamedTypes = {
    p match {
      case PrimitiveType.ARRAY =>
        require(param.items.nonEmpty, s"Items should not be empty for $name")
        val types = fromSchemaOrSchemaArray(name, param.items.get, None)
        val meta = arrayTypeMeta(param.comment.getOrElse(param.format), param)
        checkRequired(name, required, wrapInArray(types.head, meta, None), param.default) +: types.tail
      case PrimitiveType.OBJECT =>
        val obj = param.allOf map { p =>
          val everythingIsRequired = None
          extensionType(name, everythingIsRequired)(p)
        } getOrElse {
          val typeName = typeNameFromInlinedReference(param) getOrElse name
          val catchAll = fromSchemaOrBoolean(name / "additionalProperties", param.additionalProperties, param)
          val normal = fromSchemaProperties(name, param.properties, paramRequired(param.required, param.default))
          val types = fromTypes(name, normal ++ catchAll.toSeq.flatten, typeName)
          Option(param.discriminator) foreach { d => memoizeDiscriminator(name, typeName / d) }
          checkRequired(name, required, types, param.default)
        }
        Seq(obj)
      case tpe if param.enum.isDefined =>
        val meta = enumTypeMeta(param.enum.get.size)
        val typeName = typeNameFromInlinedReference(param) getOrElse name
        val primitiveType = (p, param.format)(param)
        val leaves = param.enum.get map { value =>
          EnumObject(primitiveType, value.toString, TypeMeta(Some(value.toString)))
        }
        val rootType = typeName -> EnumTrait(primitiveType, meta, leaves)
        Seq(checkRequired(name, required, rootType, param.default))
      case _ =>
        val primitiveType = name -> (p, param.format)(param)
        Seq(checkRequired(name, required, primitiveType, param.default))
    }
  }

  private def typeNameFromInlinedReference(param: Schema[_]): Option[Reference] =
    param.vendorExtensions.get("x-$ref").map(Reference.deref).map(Reference(base.toString, _))

  private def fromSchemaProperties[T](name: Reference, param: SchemaProperties, required: Option[Seq[String]]): NamedTypes =
    Option(param).toSeq.flatten flatMap { p =>
      fromSchemaOrFileSchema(name / p._1, p._2, required)
    }

  // FIXME the boolean value is basically ignored here
  private def fromSchemaOrBoolean[T](name: Reference, param: SchemaOrBoolean[T], meta: TypeMeta): Option[NamedTypes] =
    Option(param) map {
      case Left(s) =>
        val typeDefs = fromSchemaOrReference(name, s, None)
        val topMeta = s match {
          case Left(schema) => schemaTypeMeta(schema)
          case _ => meta
        }
        wrapInCatchAll(typeDefs.head, topMeta) +: typeDefs.tail
      case Right(true) => Seq(wrapInCatchAll(name -> Str(None, meta), meta))
      case Right(false) => Seq(wrapInCatchAll(name -> Str(None, meta), meta))
    }

  // ------------------------------------ Single Types ------------------------------------
  private def fromNull(name: Reference): NamedType = name -> Null(TypeMeta(None))

  private def extensionType[T](name: Reference, required: Option[Seq[String]])(schema: SchemaArray): NamedType = {
    val allOf = fromSchemaArray(name, schema, required).map(_._2)
    val root = schema.collect {
      case Left(Left(s: Schema[_])) if s.discriminator != null => typeNameFromInlinedReference(s).map(_ / s.discriminator)
    }.flatten.headOption
    val inheritedRoot = allOf.collect { case c: Composite => c.root }.flatten.headOption
    name -> AllOf(name / name.simple, schema, allOf, root orElse inheritedRoot)
  }

  private def fromReference(name: Reference, ref: JsonReference, required: Option[Seq[String]]): NamedType = {
    assert(ref != null && ref.$ref != null)
    checkRequired(name, required, name -> TypeRef(base / Reference.deref(ref.$ref)), null: Default[String])
  }

  private def fromPrimitivesItems[T](name: Reference, items: PrimitivesItems[T]): NamedType = {
    if (items.isArray) {
      val meta = arrayTypeMeta(items.comment.getOrElse(items.format), items)
      wrapInArray(fromPrimitivesItems(name, items.items), meta, Option(items.collectionFormat).map(_.toString))
    } else {
      name -> (items.`type`, items.format)(items)
    }
  }

  private def fromArrayJsonSchema[T](name: Reference, param: Schema[_], t: ArrayJsonSchemaType): NamedType = {
    val descendants = t.toSeq map { d =>
      val typeDef = PrimitiveType.fromString(d)
      fromPrimitiveType(typeDef, param.format)(param)
    }
    name -> OneOf(name, param, descendants)
  }

  private def fromNonBodyParameter[T, CF](name: Reference, param: NonBodyParameterCommons[T, CF]): NamedType = {
    val fullName = name / param.name
    val result =
      if (param.isArray) {
        val meta = arrayTypeMeta(param.comment.getOrElse(param.format), param.items)
        wrapInArray(fromPrimitivesItems(fullName, param.items), meta, Option(param.collectionFormat).map(_.toString))
      } else {
        fullName -> (param.`type`, param.format)(param)
      }
    if (!param.required && param.default == null) wrapInOption(result) else result
  }

  private def fromTypes(name: Reference, types: NamedTypes, typeName: Reference): NamedType = {
    val fields = types map { t => Field(typeName / t._1.simple, t._2) }
    name -> Domain.TypeDef(typeName, fields, types)
  }

  private def fromFileSchema[T](schema: FileSchema[T], required: Option[Seq[String]]): NamedType = ???

  // ------------------------------------ Primitives ------------------------------------

  private implicit def fromParameterType(tpe: (ParameterType.Value, String)): TypeConstructor =
    (tpe._1, Option(tpe._2).map(_.toLowerCase)) match {
      case (ParameterType.INTEGER, Some("int64")) => Domain.Lng
      case (ParameterType.INTEGER, Some("int32")) => Domain.Intgr
      case (ParameterType.INTEGER, _) => Domain.BInt
      case (ParameterType.NUMBER, Some("float")) => Domain.Flt
      case (ParameterType.NUMBER, Some("double")) => Domain.Dbl
      case (ParameterType.NUMBER, _) => Domain.BDcml
      case (ParameterType.BOOLEAN, _) => Domain.Bool
      case (ParameterType.STRING, Some("binary")) => Domain.BinaryString
      case (ParameterType.STRING, Some("byte")) => Domain.Base64String
      case (ParameterType.STRING, Some("date")) => Domain.Date
      case (ParameterType.STRING, Some("date-time")) => Domain.DateTime
      case (ParameterType.STRING, Some("password")) => Domain.Password
      case (ParameterType.STRING, Some("uuid")) => Domain.UUID
      case (ParameterType.STRING, fmt) => Domain.Str.curried(fmt)
      case (ParameterType.FILE, _) => Domain.File
      case (a, b) => throw new IllegalArgumentException(s"Combination if $a and $b is not supported")
    }

  private implicit def fromPrimitiveType(tpe: (PrimitiveType.Val, String)): TypeConstructor =
    (tpe._1, Option(tpe._2).map(_.toLowerCase)) match {
      case (PrimitiveType.INTEGER, Some("int64")) => Domain.Lng
      case (PrimitiveType.INTEGER, Some("int32")) => Domain.Intgr
      case (PrimitiveType.INTEGER, _) => Domain.BInt
      case (PrimitiveType.NUMBER, Some("float")) => Domain.Flt
      case (PrimitiveType.NUMBER, Some("double")) => Domain.Dbl
      case (PrimitiveType.NUMBER, _) => Domain.BDcml
      case (PrimitiveType.BOOLEAN, _) => Domain.Bool
      case (PrimitiveType.STRING, Some("binary")) => Domain.BinaryString
      case (PrimitiveType.STRING, Some("byte")) => Domain.Base64String
      case (PrimitiveType.STRING, Some("date")) => Domain.Date
      case (PrimitiveType.STRING, Some("date-time")) => Domain.DateTime
      case (PrimitiveType.STRING, Some("password")) => Domain.Password
      case (PrimitiveType.STRING, Some("uuid")) => Domain.UUID
      case (PrimitiveType.STRING, fmt) => Domain.Str.curried(fmt)
      case (PrimitiveType.NULL, _) => Domain.Null
      case (a, b) => throw new IllegalArgumentException(s"Combination if $a and $b is not supported")
    }

  // ------------------------------------ Wrappers ------------------------------------

  private def wrapInArray(t: NamedType, m: TypeMeta, collectionFormat: Option[String]): NamedType = {
    val wrapper =
      if (t._1.isResponsePath) Domain.ArrResult(t._2, m)
      else Domain.Arr(t._2, m, collectionFormat.map(_.toString).getOrElse(CollectionFormat.default.toString))
    t._1 -> wrapper
  }

  private def wrapInOption(t: NamedType): NamedType =
    t._1 -> Domain.Opt(t._2, TypeMeta(None))

  private def wrapInCatchAll(t: NamedType, m: TypeMeta): NamedType =
    t._1 -> Domain.CatchAll(t._2, m)

  // ------------------------------------ Helper methods ------------------------------------

  private def paramRequired(required: Seq[String], default: Default[_]) =
    Some(if (default != null || required == null) Nil else required)

  private def checkRequired(name: Reference, required: Option[Seq[String]], tpe: NamedType, default: Default[_]): NamedType =
    if (isRequired(name, required, default)) tpe else wrapInOption(tpe)

  // Use required = None if everything is required
  // Use required = Some(listOfFields) to specify what exactly is required
  // Use required = Some(Nil) to define that everything is optional
  // The return type is also required by definition
  private def isRequired[T](name: Reference, required: Option[Seq[String]], default: Default[T]): Boolean =
    default != null || required.isEmpty || required.get.contains(name.simple) || name.parent.isTopResponsePath

}

/*
 * It's safe to use mutable map without locking in current setup
 */
trait DiscriminatorMemoizer {
  val discriminators = new mutable.HashMap[Reference, Reference]()

  def memoizeDiscriminator(name: Reference, discriminator: Reference) =
    Option(discriminator) map {
      discriminators += name -> _
    }

  def findDiscriminator(allOf: Seq[Type]): Option[Reference] =
    allOf find { t =>
      discriminators.contains(t.name)
    } map { t => discriminators(t.name) }
}