package echo

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



class Echo_api_yamlSpec extends WordSpec with OptionValues with WsScalaTestClient with OneAppPerTest  {
    def toPath[T](value: T)(implicit binder: PathBindable[T]): String = Option(binder.unbind("", value)).getOrElse("")
    def toQuery[T](key: String, value: T)(implicit binder: QueryStringBindable[T]): String = Option(binder.unbind(key, value)).getOrElse("")
    def toHeader[T](value: T)(implicit binder: PathBindable[T]): String = Option(binder.unbind("", value)).getOrElse("")

  def checkResult(props: Prop) =
    Test.check(Test.Parameters.default, props).status match {
      case Failed(args, labels) =>
        val failureMsg = labels.mkString("\n") + " given args: " + args.map(_.arg).mkString("'", "', '","'")
        Failure(failureMsg)
      case Proved(_) | Exhausted | Passed => Success()
      case PropException(_, e, labels) =>
        val error = if (labels.isEmpty) e.getLocalizedMessage() else labels.mkString("\n")
        Failure(error)
    }

  private def parserConstructor(mimeType: String) = PlayBodyParsing.jacksonMapper(mimeType)

  def parseResponseContent[T](mapper: ObjectMapper, content: String, mimeType: Option[String], expectedType: Class[T]) =
    mapper.readValue(content, expectedType)


    "GET /echo/test-path/{id}" should {
        def testInvalidInput(id: String): Prop = {


            val url = s"""/echo/test-path/${toPath(id)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq()
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

                val errors = new Test_pathIdGetValidator(id).errors

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
        def testValidInput(id: String): Prop = {
            
            val url = s"""/echo/test-path/${toPath(id)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq()
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

                val errors = new Test_pathIdGetValidator(id).errors
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
        "discard invalid data" in {
            val genInputs = for {
                    id <- StringGenerator
                } yield id
            val inputs = genInputs suchThat { id =>
                new Test_pathIdGetValidator(id).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            assert(checkResult(props) == Success())
        }
        "do something with valid data" in {
            val genInputs = for {
                id <- StringGenerator
            } yield id
            val inputs = genInputs suchThat { id =>
                new Test_pathIdGetValidator(id).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            assert(checkResult(props) == Success())
        }

    }

    "POST /echo/" should {
        def testInvalidInput(input: (PostName, PostName)): Prop = {

            val (name, year) = input

            val url = s"""/echo/"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq()
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)


                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Nil
                        val data = Map.empty[String, Seq[String]]   ++ name.map(m => "name" -> Seq(m.toString)).toSeq    ++ year.map(m => "year" -> Seq(m.toString)).toSeq  
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =   name.map(m => "name" -> m.toString).toList :::     year.map(m => "year" -> m.toString).toList :::    Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new PostValidator(name, year).errors

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
        def testValidInput(input: (PostName, PostName)): Prop = {
            val (name, year) = input
            
            val url = s"""/echo/"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq()
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Nil
                        val data = Map.empty[String, Seq[String]]   ++ name.map(m => "name" -> Seq(m.toString)).toSeq    ++ year.map(m => "year" -> Seq(m.toString)).toSeq  
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =   name.map(m => "name" -> m.toString).toList :::     year.map(m => "year" -> m.toString).toList :::    Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new PostValidator(name, year).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[PostResponses200]
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
        "discard invalid data" in {
            val genInputs = for {
                        name <- PostNameGenerator
                        year <- PostNameGenerator
                    
                } yield (name, year)
            val inputs = genInputs suchThat { case (name, year) =>
                new PostValidator(name, year).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            assert(checkResult(props) == Success())
        }
        "do something with valid data" in {
            val genInputs = for {
                    name <- PostNameGenerator
                    year <- PostNameGenerator
                
            } yield (name, year)
            val inputs = genInputs suchThat { case (name, year) =>
                new PostValidator(name, year).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            assert(checkResult(props) == Success())
        }

    }
}
