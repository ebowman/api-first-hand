package de.zalando.swagger

import de.zalando.apifirst.Domain._
import de.zalando.swagger.strictModel._

import scala.language.implicitConversions
/**
 * @author  slasch
 * @since   15.10.2015.
 */
object TypeMetaConverter extends ParameterNaming {
  import ValidationsConverter._

  implicit def arrayTypeMeta[T](comment: String, items: ArrayValidation[T]): TypeMeta =
    TypeMeta(Option(comment), toArrayValidations(items))

  implicit def schemaTypeMeta[T](param: Schema[T]): TypeMeta =
    TypeMeta(Option(param.description).orElse(Option(param.format)), toValidations(param))

  implicit def primitivesItemsTypeMeta[T](param: PrimitivesItems[T]): TypeMeta =
    TypeMeta(Option(param.format).orElse(Option(param.collectionFormat).map(_.toString)), toValidations(param))

  implicit def nonBodyParameterCommonsTypeMeta[T, CF](nb: NonBodyParameterCommons[T, CF]): TypeMeta =
    TypeMeta(Option(nb.format), toValidations(nb))

  implicit def schemaArrayTypeMeta(nb: SchemaArray): TypeMeta =
    TypeMeta(Some(s"Schemas: ${nb.size}"), Nil)

  implicit def namedTypesTypeMeta(nb: NamedTypes): TypeMeta =
    TypeMeta(Some(s"Named types: ${nb.size}"), Nil)

  implicit def enumTypeMeta(enumSize: Int): TypeMeta =
    TypeMeta(Some(s"Enum type : $enumSize"), Nil)

  implicit def parametersListItemMeta(item: ParametersListItem): TypeMeta =
    item match {
      case r @ JsonReference(ref) =>
        TypeMeta(Some(ref))
      case nb: NonBodyParameterCommons[_, _] =>
        nonBodyParameterCommonsTypeMeta(nb)
      case bp: BodyParameter[_] =>
        TypeMeta(Option(bp.description), toValidations(bp))
      case nbp: NonBodyParameter[_] =>
        TypeMeta(Option(nbp.name), toValidations(nbp))
    }
}
