package numbers_validation.yaml

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


import Generators._

    @RunWith(classOf[JUnitRunner])
    class Numbers_validation_yamlSpec extends Specification {
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


    "GET /" should {
        def testInvalidInput(input: (Float, Double, GetInteger_optional, Long, Int, GetFloat_optional, GetDouble_optional, GetLong_optional)) = {

            val (float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional) = input

            val url = s"""/?${toQuery("float_required", float_required)}&${toQuery("double_required", double_required)}&${toQuery("integer_optional", integer_optional)}&${toQuery("long_required", long_required)}&${toQuery("integer_required", integer_required)}&${toQuery("float_optional", float_optional)}&${toQuery("double_optional", double_optional)}&${toQuery("long_optional", long_optional)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
               "application/json", 
            
               "application/yaml"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)


                val request = FakeRequest(GET, url).withHeaders(headers:_*)
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

                val errors = new GetValidator(float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional).errors

                lazy val validations = errors flatMap { _.messages } map { m =>
                    s"Contains error: $m in ${contentAsString(path)}" |:(contentAsString(path).contains(m) ?= true)
                }

                (s"given 'Content-Type' [$contentType], 'Accept' header [$acceptHeader] and URL: [$url]" ) |: all(
                    "StatusCode = BAD_REQUEST" |: (requestStatusCode_(path) ?= BAD_REQUEST),
                    s"Content-Type = $acceptHeader" |: (requestContentType_(path) ?= Some(acceptHeader)),
                    "non-empty errors" |: (errors.nonEmpty ?= true),
                    "at least one validation failing" |: atLeastOne(validations:_*)
                )
            }
            if (propertyList.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")
            propertyList.reduce(_ && _)
        }
        def testValidInput(input: (Float, Double, GetInteger_optional, Long, Int, GetFloat_optional, GetDouble_optional, GetLong_optional)) = {
            val (float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional) = input
            
            val url = s"""/?${toQuery("float_required", float_required)}&${toQuery("double_required", double_required)}&${toQuery("integer_optional", integer_optional)}&${toQuery("long_required", long_required)}&${toQuery("integer_required", integer_required)}&${toQuery("float_optional", float_optional)}&${toQuery("double_optional", double_optional)}&${toQuery("long_optional", long_optional)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
                "application/json", 
            
                "application/yaml"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(GET, url).withHeaders(headers:_*)
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

                val errors = new GetValidator(float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Null]
                )

                val expectedCode = requestStatusCode_(path)
                val mimeType = requestContentType_(path)
                val mapper = parserConstructor(mimeType.getOrElse("application/json"))

                val parsedApiResponse = scala.util.Try {
                    parseResponseContent(mapper, requestContentAsString_(path), mimeType, possibleResponseTypes(expectedCode))
                }

                (s"Given response code [$expectedCode], 'Content-Type' [$contentType], 'Accept' header [$acceptHeader] and URL: [$url]" ) |: all(
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
                        float_required <- FloatGenerator
                        double_required <- DoubleGenerator
                        integer_optional <- GetInteger_optionalGenerator
                        long_required <- LongGenerator
                        integer_required <- IntGenerator
                        float_optional <- GetFloat_optionalGenerator
                        double_optional <- GetDouble_optionalGenerator
                        long_optional <- GetLong_optionalGenerator
                    
                } yield (float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional)
            val inputs = genInputs suchThat { case (float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional) =>
                new GetValidator(float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    float_required <- FloatGenerator
                    double_required <- DoubleGenerator
                    integer_optional <- GetInteger_optionalGenerator
                    long_required <- LongGenerator
                    integer_required <- IntGenerator
                    float_optional <- GetFloat_optionalGenerator
                    double_optional <- GetDouble_optionalGenerator
                    long_optional <- GetLong_optionalGenerator
                
            } yield (float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional)
            val inputs = genInputs suchThat { case (float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional) =>
                new GetValidator(float_required, double_required, integer_optional, long_required, integer_required, float_optional, double_optional, long_optional).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }
}
