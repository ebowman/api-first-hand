package string_formats_validation.yaml

import de.zalando.play.controllers._
import org.scalacheck._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalacheck.Test._
import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test._
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc._

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import java.net.URLEncoder
import com.fasterxml.jackson.databind.ObjectMapper

import play.api.http.Writeable
import play.api.libs.Files.TemporaryFile
import play.api.test.Helpers.{status => requestStatusCode_}
import play.api.test.Helpers.{contentAsString => requestContentAsString_}
import play.api.test.Helpers.{contentType => requestContentType_}

import org.joda.time.DateTime
import org.joda.time.LocalDate
import de.zalando.play.controllers.BinaryString
import BinaryString._
import de.zalando.play.controllers.Base64String
import Base64String._

import Generators._

    @RunWith(classOf[JUnitRunner])
    class String_formats_validation_yamlSpec extends Specification {
        def toPath[T](value: T)(implicit binder: PathBindable[T]): String = Option(binder.unbind("", value)).getOrElse("")
        def toQuery[T](key: String, value: T)(implicit binder: QueryStringBindable[T]): String = Option(binder.unbind(key, value)).getOrElse("")
        def toHeader[T](value: T)(implicit binder: PathBindable[T]): String = Option(binder.unbind("", value)).getOrElse("")

      def checkResult(props: Prop) =
        Test.check(Test.Parameters.default, props).status match {
          case Failed(args, labels) =>
            val failureMsg = labels.mkString("\n") + " given args: " + args.map(_.arg).mkString("'", "', '","'")
            failure(failureMsg)
          case Proved(_) | Exhausted | Passed => success
          case PropException(_, e, labels) =>
            val error = if (labels.isEmpty) e.getLocalizedMessage() else labels.mkString("\n")
            failure(error)
        }

      private def parserConstructor(mimeType: String) = PlayBodyParsing.jacksonMapper(mimeType)

      def parseResponseContent[T](mapper: ObjectMapper, content: String, mimeType: Option[String], expectedType: Class[T]) =
        mapper.readValue(content, expectedType)


    "POST /string" should {
        def testInvalidInput(input: (String, StringPostPassword_optional, LocalDate, StringPostBinary_optional, StringPostDate_optional, Base64String, StringPostBase64optional, StringPostString_optional, DateTime, String, StringPostDate_time_optional)) = {

            val (string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional) = input

            val url = s"""/string?${toQuery("string_required", string_required)}&${toQuery("password_optional", password_optional)}&${toQuery("date_required", date_required)}&${toQuery("date_optional", date_optional)}&${toQuery("base64required", base64required)}&${toQuery("base64optional", base64optional)}&${toQuery("string_optional", string_optional)}&${toQuery("date_time_required", date_time_required)}&${toQuery("password_required", password_required)}&${toQuery("date_time_optional", date_time_optional)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
               "application/json", 
            
               "application/yaml"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                    val parsed_binary_optional = PlayBodyParsing.jacksonMapper("application/json").writeValueAsString(binary_optional)

                val request = FakeRequest(POST, url).withHeaders(headers:_*).withBody(parsed_binary_optional)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Nil
                        val data = Map.empty[String, Seq[String]] 
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =  Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new StringPostValidator(string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional).errors

                lazy val validations = errors flatMap { _.messages } map { m =>
                    s"Contains error: $m in ${contentAsString(path)}" |:(contentAsString(path).contains(m) ?= true)
                }

                (s"given 'Content-Type' [$contentType], 'Accept' header [$acceptHeader] and URL: [$url]" + "and body [" + parsed_binary_optional + "]") |: all(
                    "StatusCode = BAD_REQUEST" |: (requestStatusCode_(path) ?= BAD_REQUEST),
                    s"Content-Type = $acceptHeader" |: (requestContentType_(path) ?= Some(acceptHeader)),
                    "non-empty errors" |: (errors.nonEmpty ?= true),
                    "at least one validation failing" |: atLeastOne(validations:_*)
                )
            }
            if (propertyList.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")
            propertyList.reduce(_ && _)
        }
        def testValidInput(input: (String, StringPostPassword_optional, LocalDate, StringPostBinary_optional, StringPostDate_optional, Base64String, StringPostBase64optional, StringPostString_optional, DateTime, String, StringPostDate_time_optional)) = {
            val (string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional) = input
            
            val parsed_binary_optional = parserConstructor("application/json").writeValueAsString(binary_optional)
            
            val url = s"""/string?${toQuery("string_required", string_required)}&${toQuery("password_optional", password_optional)}&${toQuery("date_required", date_required)}&${toQuery("date_optional", date_optional)}&${toQuery("base64required", base64required)}&${toQuery("base64optional", base64optional)}&${toQuery("string_optional", string_optional)}&${toQuery("date_time_required", date_time_required)}&${toQuery("password_required", password_required)}&${toQuery("date_time_optional", date_time_optional)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
                "application/json", 
            
                "application/yaml"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(POST, url).withHeaders(headers:_*).withBody(parsed_binary_optional)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Nil
                        val data = Map.empty[String, Seq[String]] 
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =  Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new StringPostValidator(string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Null]
                )

                val expectedCode = requestStatusCode_(path)
                val mimeType = requestContentType_(path)
                val mapper = parserConstructor(mimeType.getOrElse("application/json"))

                val parsedApiResponse = scala.util.Try {
                    parseResponseContent(mapper, requestContentAsString_(path), mimeType, possibleResponseTypes(expectedCode))
                }

                (s"Given response code [$expectedCode], 'Content-Type' [$contentType], 'Accept' header [$acceptHeader] and URL: [$url]" + "and body [" + parsed_binary_optional + "]") |: all(
                    "Response Code is allowed" |: (possibleResponseTypes.contains(expectedCode) ?= true),
                    "Successful" |: (parsedApiResponse.isSuccess ?= true),
                    s"Content-Type = $acceptHeader" |: (requestContentType_(path) ?= Some(acceptHeader)),
                    "No errors" |: (errors.isEmpty ?= true)
                )
            }
            if (propertyList.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")
            propertyList.reduce(_ && _)
        }
        "discard invalid data" in new WithApplication {
            val genInputs = for {
                        string_required <- StringGenerator
                        password_optional <- StringPostPassword_optionalGenerator
                        date_required <- LocalDateGenerator
                        binary_optional <- StringPostBinary_optionalGenerator
                        date_optional <- StringPostDate_optionalGenerator
                        base64required <- Base64StringGenerator
                        base64optional <- StringPostBase64optionalGenerator
                        string_optional <- StringPostString_optionalGenerator
                        date_time_required <- DateTimeGenerator
                        password_required <- StringGenerator
                        date_time_optional <- StringPostDate_time_optionalGenerator
                    
                } yield (string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional)
            val inputs = genInputs suchThat { case (string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional) =>
                new StringPostValidator(string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    string_required <- StringGenerator
                    password_optional <- StringPostPassword_optionalGenerator
                    date_required <- LocalDateGenerator
                    binary_optional <- StringPostBinary_optionalGenerator
                    date_optional <- StringPostDate_optionalGenerator
                    base64required <- Base64StringGenerator
                    base64optional <- StringPostBase64optionalGenerator
                    string_optional <- StringPostString_optionalGenerator
                    date_time_required <- DateTimeGenerator
                    password_required <- StringGenerator
                    date_time_optional <- StringPostDate_time_optionalGenerator
                
            } yield (string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional)
            val inputs = genInputs suchThat { case (string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional) =>
                new StringPostValidator(string_required, password_optional, date_required, binary_optional, date_optional, base64required, base64optional, string_optional, date_time_required, password_required, date_time_optional).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "POST /string2" should {
        def testInvalidInput(binary_required: BinaryString) = {


            val url = s"""/string2"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
               "application/json", 
            
               "application/yaml"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                    val parsed_binary_required = PlayBodyParsing.jacksonMapper("application/json").writeValueAsString(binary_required)

                val request = FakeRequest(POST, url).withHeaders(headers:_*).withBody(parsed_binary_required)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Nil
                        val data = Map.empty[String, Seq[String]] 
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =  Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new String2PostValidator(binary_required).errors

                lazy val validations = errors flatMap { _.messages } map { m =>
                    s"Contains error: $m in ${contentAsString(path)}" |:(contentAsString(path).contains(m) ?= true)
                }

                (s"given 'Content-Type' [$contentType], 'Accept' header [$acceptHeader] and URL: [$url]" + "and body [" + parsed_binary_required + "]") |: all(
                    "StatusCode = BAD_REQUEST" |: (requestStatusCode_(path) ?= BAD_REQUEST),
                    s"Content-Type = $acceptHeader" |: (requestContentType_(path) ?= Some(acceptHeader)),
                    "non-empty errors" |: (errors.nonEmpty ?= true),
                    "at least one validation failing" |: atLeastOne(validations:_*)
                )
            }
            if (propertyList.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")
            propertyList.reduce(_ && _)
        }
        def testValidInput(binary_required: BinaryString) = {
            
            val parsed_binary_required = parserConstructor("application/json").writeValueAsString(binary_required)
            
            val url = s"""/string2"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
                "application/json", 
            
                "application/yaml"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(POST, url).withHeaders(headers:_*).withBody(parsed_binary_required)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Nil
                        val data = Map.empty[String, Seq[String]] 
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =  Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new String2PostValidator(binary_required).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Null]
                )

                val expectedCode = requestStatusCode_(path)
                val mimeType = requestContentType_(path)
                val mapper = parserConstructor(mimeType.getOrElse("application/json"))

                val parsedApiResponse = scala.util.Try {
                    parseResponseContent(mapper, requestContentAsString_(path), mimeType, possibleResponseTypes(expectedCode))
                }

                (s"Given response code [$expectedCode], 'Content-Type' [$contentType], 'Accept' header [$acceptHeader] and URL: [$url]" + "and body [" + parsed_binary_required + "]") |: all(
                    "Response Code is allowed" |: (possibleResponseTypes.contains(expectedCode) ?= true),
                    "Successful" |: (parsedApiResponse.isSuccess ?= true),
                    s"Content-Type = $acceptHeader" |: (requestContentType_(path) ?= Some(acceptHeader)),
                    "No errors" |: (errors.isEmpty ?= true)
                )
            }
            if (propertyList.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")
            propertyList.reduce(_ && _)
        }
        "discard invalid data" in new WithApplication {
            val genInputs = for {
                    binary_required <- BinaryStringGenerator
                } yield binary_required
            val inputs = genInputs suchThat { binary_required =>
                new String2PostValidator(binary_required).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                binary_required <- BinaryStringGenerator
            } yield binary_required
            val inputs = genInputs suchThat { binary_required =>
                new String2PostValidator(binary_required).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }
}
