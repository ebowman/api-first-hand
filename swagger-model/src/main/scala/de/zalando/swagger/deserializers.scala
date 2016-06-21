package de.zalando.swagger

import java.util.Map.Entry

import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.{ NullNode, BaseJsonNode }
import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import de.zalando.swagger.strictModel._
import com.fasterxml.jackson.core.{ JsonParser => JParser, _ }

/**
 * @author  slasch
 * @since   09.10.2015.
 */
//noinspection ScalaStyle
object deserializers {
  import scala.collection.JavaConverters._

  /*
    TODO wrong error location

    the error location is pointing to the wrong line and column because we are not working with the original file,
    but with "prepared" version (needed due jackson's luck of support for some YAML features)
 */

  /**
   * Deserializer class for SecurityDefinitions
   * Creates right instance based on the data included in the specification
   *
   */
  private class SecurityDefinitionDeserializer extends StdDeserializer[SecurityDefinition](classOf[SecurityDefinition]) {

    @Override
    def deserialize(jp: JParser, ctxt: DeserializationContext): SecurityDefinition = {
      val mapper = jp.getCodec.asInstanceOf[ObjectMapper]
      val root = mapper.readTree(jp).asInstanceOf[BaseJsonNode]

      if (root == null || root == NullNode.instance) null
      else {
        val fields = root.fields.asScala.toSeq
        val typeField = getFieldValue(fields, "type")
        lazy val parser = root.traverse
        val instance = typeField match {
          case "basic" =>
            mapper.readValue(parser, classOf[BasicAuthenticationSecurity])
          case "apiKey" =>
            val in = getFieldValue(fields, "in")
            in match {
              case a if a == "header" || a == "query" =>
                val name = getFieldValue(fields, "name")
                if (name != null && name.nonEmpty)
                  mapper.readValue(parser, classOf[ApiKeySecurity])
                else
                  throw new JsonParseException(parser, "Name should not be empty for apiKey", jp.getTokenLocation)
              case other =>
                throw new JsonParseException(parser, s"Wrong in value '$other', expected one of [header, query]", jp.getTokenLocation)
            }
          case "oauth2" =>
            val flow = getFieldValue(fields, "flow")
            lazy val tokenUrl = getFieldValue(fields, "tokenUrl")
            flow match {
              case "implicit" =>
                val authorizationUrl = getFieldValue(fields, "authorizationUrl")
                if (authorizationUrl != null && authorizationUrl.nonEmpty)
                  mapper.readValue(parser, classOf[Oauth2ImplicitSecurity])
                else
                  throw new JsonParseException(parser, "authorizationUrl should not be empty for implicit oauth2", jp.getTokenLocation)

              case "password" =>
                if (tokenUrl != null && tokenUrl.nonEmpty)
                  mapper.readValue(parser, classOf[Oauth2PasswordSecurity])
                else
                  throw new JsonParseException(parser, "tokenUrl should not be empty for password oauth2", jp.getTokenLocation)

              case "application" =>
                if (tokenUrl != null && tokenUrl.nonEmpty)
                  mapper.readValue(parser, classOf[Oauth2ApplicationSecurity])
                else
                  throw new JsonParseException(parser, "tokenUrl should not be empty for application oauth2", jp.getTokenLocation)

              case "accessCode" =>
                if (tokenUrl != null && tokenUrl.nonEmpty)
                  mapper.readValue(parser, classOf[Oauth2AccessCodeSecurity])
                else
                  throw new JsonParseException(parser, "tokenUrl should not be empty for accessCode oauth2", jp.getTokenLocation)

              case other =>
                throw new JsonParseException(parser, s"Wrong oauth2 value '$other', expected one of [implicit, password, application, accessCode]", jp.getTokenLocation)
            }
          case other =>
            throw new JsonParseException(parser, s"Wrong type value '$other', expected one of [basic, apiKey, oauth2]", jp.getTokenLocation)
        }
        instance.asInstanceOf[SecurityDefinition]
      }
    }
  }

  /**
   * Deserializer class for ParametersListItem
   *
   * Creates right instance based on the data included in the specification for one of following parameter list items:
   *
   * - JsonRef($ref)
   * - BodyParameter(in: body)
   * - PathParameter(in: path)
   * - FormDataParameter(in: formData)
   * - QueryParameter(in: query)
   * - HeaderParameter(in: header)
   *
   */
  private class ParametersListItemDeserializer[ParamType <: ParametersListItem] extends StdDeserializer[ParamType](classOf[ParametersListItem]) {

    @Override
    def deserialize(jp: JParser, ctxt: DeserializationContext): ParamType = {
      val mapper = jp.getCodec.asInstanceOf[ObjectMapper]
      val root = mapper.readTree(jp).asInstanceOf[BaseJsonNode]

      val instance = if (root == null || root == NullNode.instance) null
      else {
        val fields = root.fields.asScala.toSeq
        val refField = getFieldValue(fields, "$" + "ref")
        lazy val parser = root.traverse
        refField match {
          case someRef if someRef != null && someRef.trim.nonEmpty =>
            mapper.convertValue(root, classOf[JsonReference])
          case otherRef =>
            val in = getFieldValue(fields, "in")
            if (in == null || in.trim.isEmpty)
              throw new JsonParseException(parser, "Parameter should have '$ ref' or 'in' defined, but neither was found", jp.getTokenLocation)
            in.toLowerCase match {
              case "header" =>
                checkTypeIsNotFile(fields, parser)
                mapper.convertValue(root, classOf[HeaderParameter[_]])
              case "path" =>
                checkTypeIsNotFile(fields, parser)
                if ("true" != getFieldValue(fields, "required"))
                  throw new JsonParseException(parser, "Path parameter MUST be required", jp.getTokenLocation)
                mapper.convertValue(root, classOf[PathParameter[_]])
              case "formdata" =>
                mapper.convertValue(root, classOf[FormDataParameter[_]])
              case "query" =>
                checkTypeIsNotFile(fields, parser)
                mapper.convertValue(root, classOf[QueryParameter[_]])
              case "body" =>
                checkTypeIsNotFile(fields, parser)
                mapper.convertValue(root, classOf[BodyParameter[_]])
            }
        }
      }
      instance.asInstanceOf[ParamType]
    }
  }

  private class SchemaOrFileSchemaDeserializer[T] extends StdDeserializer[SchemaOrFileSchema[T]](classOf[SchemaOrFileSchema[T]]) {

    @Override
    def deserialize(jp: JParser, ctxt: DeserializationContext): SchemaOrFileSchema[T] = {
      val mapper = jp.getCodec.asInstanceOf[ObjectMapper]
      val root = mapper.readTree(jp).asInstanceOf[BaseJsonNode]

      if (root == null || root == NullNode.instance) null
      else {
        val fields = root.fields.asScala.toSeq
        val typeField = getFieldValue(fields, "type")
        typeField match {
          case "file" =>
            Right(mapper.convertValue(root, classOf[FileSchema[T]]))
          case other =>
            Left(schemaOrReference(mapper, root))
        }
      }
    }
  }

  private class AnySchemaDeserializer extends StdDeserializer[SchemaOrSchemaArray[_]](classOf[SchemaOrSchemaArray[_]]) {

    @Override
    def deserialize(jp: JParser, ctxt: DeserializationContext): SchemaOrSchemaArray[_] = {
      val mapper = jp.getCodec.asInstanceOf[ObjectMapper]
      val root = mapper.readTree(jp).asInstanceOf[BaseJsonNode]

      if (root == null || root == NullNode.instance) null
      else if (root.isArray) Right(mapper.convertValue(root, classOf[SchemaArray]))
      else Left(schemaOrReference(mapper, root))
    }
  }

  private class SchemaOrReferenceDeserializer extends StdDeserializer[SchemaOrReference[_]](classOf[SchemaOrReference[_]]) {

    @Override
    def deserialize(jp: JParser, ctxt: DeserializationContext): SchemaOrReference[_] = {
      val mapper = jp.getCodec.asInstanceOf[ObjectMapper]
      val root = mapper.readTree(jp).asInstanceOf[BaseJsonNode]

      if (root == null || root == NullNode.instance) null
      else schemaOrReference(mapper, root)
    }
  }

  def schemaOrReference[T](mapper: ObjectMapper, root: BaseJsonNode): SchemaOrReference[T] = {
    val fields = root.fields.asScala.toSeq
    val ref = getFieldValue(fields, "$" + "ref")
    if (ref == null || ref.trim.isEmpty)
      Left(mapper.convertValue(root, classOf[Schema[T]]))
    else
      Right(mapper.convertValue(root, classOf[JsonReference]))
  }

  private class JsonSchemaTypeDeserializer extends StdDeserializer[JsonSchemaType](classOf[JsonSchemaType]) {

    @Override
    def deserialize(jp: JParser, ctxt: DeserializationContext): JsonSchemaType = {
      val mapper = jp.getCodec.asInstanceOf[ObjectMapper]
      val root = mapper.readTree(jp).asInstanceOf[BaseJsonNode]

      if (root == null || root == NullNode.instance) null
      else if (root.isTextual) PrimitiveType.withName(root.asText()).asInstanceOf[JsonSchemaType]
      else if (root.isArray) {
        val array = mapper.convertValue(root, classOf[Array[String]])
        if (array.distinct.length == array.length) array.toSet.asInstanceOf[JsonSchemaType]
        else throw new JsonParseException(jp, "'JsonSchemaType array must contain unique values", jp.getTokenLocation)
      } else
        throw new JsonParseException(jp, "'JsonSchemaType must be array or primitive type", jp.getTokenLocation)
    }
  }

  private class SchemaOrBooleanDeserializer extends StdDeserializer[SchemaOrBoolean[_]](classOf[SchemaOrBoolean[_]]) {

    @Override
    def deserialize(jp: JParser, ctxt: DeserializationContext): SchemaOrBoolean[_] = {
      val mapper = jp.getCodec.asInstanceOf[ObjectMapper]
      val root = mapper.readTree(jp).asInstanceOf[BaseJsonNode]

      if (root == null || root == NullNode.instance) null
      else if (root.isBoolean) Right(root.asBoolean())
      else if (root.isObject) Left(schemaOrReference(mapper, root))
      else throw new JsonParseException(jp, "'Could not recognize boolean or Schema", jp.getTokenLocation)
    }
  }

  private def checkTypeIsNotFile(fields: Seq[Entry[SimpleTag, JsonNode]], jp: JParser): Unit =
    if ("File".equalsIgnoreCase(getFieldValue(fields, "type")))
      throw new JsonParseException(jp, "'File' type is not allowed here", jp.getTokenLocation)

  private def getFieldValue(fields: Seq[Entry[String, JsonNode]], name: String, default: String = ""): String =
    fields find (_.getKey == name) map (_.getValue.asText) getOrElse default

  private val securityDeserializer = new SecurityDefinitionDeserializer
  val securityModule = new SimpleModule("PolymorphicSecurityDefinitionDeserializerModule", new Version(1, 0, 0, null, "", ""))
  securityModule.addDeserializer(classOf[SecurityDefinition], securityDeserializer)

  val parametersListItemModule = new SimpleModule("PolymorphicParametersListItemDeserializerModule", new Version(1, 0, 0, null, "", ""))

  private val paramListItemDeserializer = new ParametersListItemDeserializer[ParametersListItem]
  parametersListItemModule.addDeserializer(classOf[ParametersListItem], paramListItemDeserializer)

  private val parameterDeserializer = new ParametersListItemDeserializer[Parameter[_]]
  parametersListItemModule.addDeserializer(classOf[Parameter[_]], parameterDeserializer)

  private val arrayJsonSchemaDeserializer = new JsonSchemaTypeDeserializer
  parametersListItemModule.addDeserializer(classOf[JsonSchemaType], arrayJsonSchemaDeserializer)

  private val schemaOrBooleanDeserializer = new SchemaOrBooleanDeserializer
  parametersListItemModule.addDeserializer(classOf[SchemaOrBoolean[_]], schemaOrBooleanDeserializer)

  private val schemaOrFileSchemaDeserializer = new SchemaOrFileSchemaDeserializer
  parametersListItemModule.addDeserializer(classOf[SchemaOrFileSchema[_]], schemaOrFileSchemaDeserializer)

  private val schemaOrReferenceDeserializer = new SchemaOrReferenceDeserializer
  parametersListItemModule.addDeserializer(classOf[SchemaOrReference[_]], schemaOrReferenceDeserializer)

  private val anySchemaDeserializer = new AnySchemaDeserializer
  parametersListItemModule.addDeserializer(classOf[SchemaOrSchemaArray[_]], anySchemaDeserializer)

}