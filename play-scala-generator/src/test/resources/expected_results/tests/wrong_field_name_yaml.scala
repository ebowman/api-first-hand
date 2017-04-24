package wrong_field_name.yaml

import de.zalando.play.controllers._
import org.scalacheck._
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._
import org.scalacheck.Test._
import org.specs2.mutable._
import org.specs2.execute._
import play.api.test.Helpers._
import play.api.test._
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc._

import org.junit.runner.RunWith
import java.net.URLEncoder
import com.fasterxml.jackson.databind.ObjectMapper

import play.api.http.Writeable
import play.api.libs.Files.TemporaryFile
import play.api.test.Helpers.{status => requestStatusCode_}
import play.api.test.Helpers.{contentAsString => requestContentAsString_}
import play.api.test.Helpers.{contentType => requestContentType_}

import org.scalatest.{OptionValues, WordSpec}
import org.scalatestplus.play.{OneAppPerTest, WsScalaTestClient}

import Generators._


//noinspection ScalaStyle
class Wrong_field_name_yamlSpec extends WordSpec with OptionValues with WsScalaTestClient with OneAppPerTest  {
    def toPath[T](value: T)(implicit binder: PathBindable[T]): String = Option(binder.unbind("", value)).getOrElse("")
    def toQuery[T](key: String, value: T)(implicit binder: QueryStringBindable[T]): String = Option(binder.unbind(key, value)).getOrElse("")
    def toHeader[T](value: T)(implicit binder: QueryStringBindable[T]): String = Option(binder.unbind("", value)).getOrElse("")

  def checkResult(props: Prop): org.specs2.execute.Result =
    Test.check(Test.Parameters.default, props).status match {
      case Failed(args, labels) =>
        val failureMsg = labels.mkString("\n") + " given args: " + args.map(_.arg).mkString("'", "', '","'")
        org.specs2.execute.Failure(failureMsg)
      case Proved(_) | Exhausted | Passed => org.specs2.execute.Success()
      case PropException(_, e: IllegalStateException, _) => org.specs2.execute.Error(e.getMessage)
      case PropException(_, e, labels) =>
        val error = if (labels.isEmpty) e.getLocalizedMessage else labels.mkString("\n")
        org.specs2.execute.Failure(error)
    }

  private def parserConstructor(mimeType: String) = PlayBodyParsing.jacksonMapper(mimeType)

  def parseResponseContent[T](mapper: ObjectMapper, content: String, mimeType: Option[String], expectedType: Class[T]) =
    if (expectedType.getCanonicalName == "scala.runtime.Null$") null else mapper.readValue(content, expectedType)


    "GET /status/" should {
        def testInvalidInput(input: (Option[GetOptCodesOpt], GetCodes)): Prop = {

            val (optCodes, codes) = input

            val url = s"""/status/"""
            val contentTypes: Seq[String] = Seq(
                "*/*"
            )
            val acceptHeaders: Seq[String] = Seq()
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ct, ac)
            if (contentHeaders.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")

            val propertyList = contentHeaders.map { case (consumes, produces) =>
                val headers =
                    Seq(
                        "optCodes" -> toHeader(optCodes), 
                        
                        "codes" -> toHeader(codes)
                        ) :+ ("Accept" -> produces) :+ ("Content-Type" -> consumes)


                val request = FakeRequest(GET, url).withHeaders(headers:_*)
                val path =
                    if (consumes == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Nil
                        val data = Map.empty[String, Seq[String]] 
                        val form = new MultipartFormData(data, files, Nil)

                        route(app, request.withMultipartFormDataBody(form)).get
                    } else if (consumes == "application/x-www-form-urlencoded") {
                        route(app, request.withFormUrlEncodedBody()).get
                    } else route(app, request).get

                val errors = new GetValidator(optCodes, codes).errors

                lazy val validations = errors flatMap { _.messages } map { m =>
                    s"Contains error: $m in ${contentAsString(path)}" |:(contentAsString(path).contains(m) ?= true)
                }

                (s"given 'Content-Type' [$consumes], 'Accept' header [$produces] and URL: [$url]" ) |: all(
                    "StatusCode = BAD_REQUEST" |: (requestStatusCode_(path) ?= BAD_REQUEST),
                    s"Content-Type = $produces" |: ((consumes ?= "*/*") || (requestContentType_(path) ?= Some(produces))),
                    "non-empty errors" |: (errors.nonEmpty ?= true),
                    "at least one validation failing" |: atLeastOne(validations:_*)
                )
            }
            propertyList.reduce(_ && _)
        }
        def testValidInput(input: (Option[GetOptCodesOpt], GetCodes)): Prop = {
            val (optCodes, codes) = input
            
            val url = s"""/status/"""
            val consumesAll: Seq[String] = Seq(
                "*/*"
            )
            val producesAll: Seq[String] = Seq()
            val contentHeaders = for { cs <- consumesAll; ps <- producesAll } yield (cs, ps)
            if (contentHeaders.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")

            val propertyList = contentHeaders.map { case (consumes, produces) =>
                val headers =
                   Seq(
                        "optCodes" -> toHeader(optCodes), 
                    
                        "codes" -> toHeader(codes)
                    ) :+ ("Accept" -> produces) :+ ("Content-Type" -> consumes)

                val request = FakeRequest(GET, url).withHeaders(headers:_*)
                val path =
                    if (consumes == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Nil
                        val data = Map.empty[String, Seq[String]] 
                        val form = new MultipartFormData(data, files, Nil)

                        route(app, request.withMultipartFormDataBody(form)).get
                    } else if (consumes == "application/x-www-form-urlencoded") {
                        route(app, request.withFormUrlEncodedBody()).get
                    } else route(app, request).get

                val errors = new GetValidator(optCodes, codes).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[StatusAndCode]
                )

                val expectedCode = requestStatusCode_(path)
                val mimeType = requestContentType_(path)
                val mapper = parserConstructor(mimeType.getOrElse("*/*"))

                val parsedApiResponse = scala.util.Try {
                    parseResponseContent(mapper, requestContentAsString_(path), mimeType, possibleResponseTypes(expectedCode))
                }

                (s"Given response code [$expectedCode], 'Content-Type' [$consumes], 'Accept' header [$produces] and URL: [$url]" ) |: all(
                    "Response Code is allowed" |: (possibleResponseTypes.contains(expectedCode) ?= true),
                    "Successful" |: (parsedApiResponse.isSuccess ?= true),
                    s"Content-Type = $produces" |: ((parsedApiResponse.get ?= null) || (consumes ?= "*/*") || (requestContentType_(path) ?= Some(produces))),
                    "No errors" |: (errors.isEmpty ?= true)
                )
            }
            propertyList.reduce(_ && _)
        }
        "discard invalid data" in {
            val genInputs = for {
                        optCodes <- OptionGetOptCodesOptGenerator
                        codes <- GetCodesGenerator
                    
                } yield (optCodes, codes)
            val inputs = genInputs suchThat { case (optCodes, codes) =>
                new GetValidator(optCodes, codes).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            assert(checkResult(props) == Success())
        }
        "do something with valid data" in {
            val genInputs = for {
                    optCodes <- OptionGetOptCodesOptGenerator
                    codes <- GetCodesGenerator
                
            } yield (optCodes, codes)
            val inputs = genInputs suchThat { case (optCodes, codes) =>
                new GetValidator(optCodes, codes).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            assert(checkResult(props) == Success())
        }

    }
}
