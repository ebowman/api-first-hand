package form_data.yaml

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

import scala.math.BigInt
import java.io.File

//noinspection ScalaStyle
class Form_data_yamlSpec extends WordSpec with OptionValues with WsScalaTestClient with OneAppPerTest  {
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


    "POST /form_data/multipart" should {
        def testInvalidInput(input: (String, Option[BigInt], Option[File])): Prop = {

            val (name, year, avatar) = input

            val url = s"""/form_data/multipart"""
            val contentTypes: Seq[String] = Seq(
                "multipart/form-data"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ct, ac)
            if (contentHeaders.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")

            val propertyList = contentHeaders.map { case (consumes, produces) =>
                val headers =
                    Seq() :+ ("Accept" -> produces) :+ ("Content-Type" -> consumes)


                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (consumes == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = avatar.toSeq.map { m => FilePart("avatar", m.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(m)) } ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq     
                        val form = new MultipartFormData(data, files, Nil)

                        route(app, request.withMultipartFormDataBody(form)).get
                    } else if (consumes == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::       Nil
                        route(app, request.withFormUrlEncodedBody(form:_*)).get
                    } else route(app, request).get

                val errors = new MultipartPostValidator(name, year, avatar).errors

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
        def testValidInput(input: (String, Option[BigInt], Option[File])): Prop = {
            val (name, year, avatar) = input
            
            val url = s"""/form_data/multipart"""
            val consumesAll: Seq[String] = Seq(
                "multipart/form-data"
            )
            val producesAll: Seq[String] = Seq(
                "application/json"
            )
            val contentHeaders = for { cs <- consumesAll; ps <- producesAll } yield (cs, ps)
            if (contentHeaders.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")

            val propertyList = contentHeaders.map { case (consumes, produces) =>
                val headers =
                   Seq() :+ ("Accept" -> produces) :+ ("Content-Type" -> consumes)

                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (consumes == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = avatar.toSeq.map { m => FilePart("avatar", m.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(m)) } ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq     
                        val form = new MultipartFormData(data, files, Nil)

                        route(app, request.withMultipartFormDataBody(form)).get
                    } else if (consumes == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::       Nil
                        route(app, request.withFormUrlEncodedBody(form:_*)).get
                    } else route(app, request).get

                val errors = new MultipartPostValidator(name, year, avatar).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[MultipartPostResponses200]
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
                        name <- StringGenerator
                        year <- OptionBigIntGenerator
                        avatar <- OptionFileGenerator
                    
                } yield (name, year, avatar)
            val inputs = genInputs suchThat { case (name, year, avatar) =>
                new MultipartPostValidator(name, year, avatar).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            assert(checkResult(props) == Success())
        }
        "do something with valid data" in {
            val genInputs = for {
                    name <- StringGenerator
                    year <- OptionBigIntGenerator
                    avatar <- OptionFileGenerator
                
            } yield (name, year, avatar)
            val inputs = genInputs suchThat { case (name, year, avatar) =>
                new MultipartPostValidator(name, year, avatar).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            assert(checkResult(props) == Success())
        }

    }

    "POST /form_data/both" should {
        def testInvalidInput(input: (String, Option[BigInt], Option[File], File)): Prop = {

            val (name, year, avatar, ringtone) = input

            val url = s"""/form_data/both"""
            val contentTypes: Seq[String] = Seq(
                "application/x-www-form-urlencoded", 
            
                "multipart/form-data"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ct, ac)
            if (contentHeaders.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")

            val propertyList = contentHeaders.map { case (consumes, produces) =>
                val headers =
                    Seq() :+ ("Accept" -> produces) :+ ("Content-Type" -> consumes)


                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (consumes == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = avatar.toSeq.map { m => FilePart("avatar", m.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(m)) } ++ Seq(FilePart("ringtone", ringtone.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(ringtone))) ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq        
                        val form = new MultipartFormData(data, files, Nil)

                        route(app, request.withMultipartFormDataBody(form)).get
                    } else if (consumes == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::          Nil
                        route(app, request.withFormUrlEncodedBody(form:_*)).get
                    } else route(app, request).get

                val errors = new BothPostValidator(name, year, avatar, ringtone).errors

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
        def testValidInput(input: (String, Option[BigInt], Option[File], File)): Prop = {
            val (name, year, avatar, ringtone) = input
            
            val url = s"""/form_data/both"""
            val consumesAll: Seq[String] = Seq(
                "application/x-www-form-urlencoded", 
            
                "multipart/form-data"
            )
            val producesAll: Seq[String] = Seq(
                "application/json"
            )
            val contentHeaders = for { cs <- consumesAll; ps <- producesAll } yield (cs, ps)
            if (contentHeaders.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")

            val propertyList = contentHeaders.map { case (consumes, produces) =>
                val headers =
                   Seq() :+ ("Accept" -> produces) :+ ("Content-Type" -> consumes)

                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (consumes == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = avatar.toSeq.map { m => FilePart("avatar", m.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(m)) } ++ Seq(FilePart("ringtone", ringtone.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(ringtone))) ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq        
                        val form = new MultipartFormData(data, files, Nil)

                        route(app, request.withMultipartFormDataBody(form)).get
                    } else if (consumes == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::          Nil
                        route(app, request.withFormUrlEncodedBody(form:_*)).get
                    } else route(app, request).get

                val errors = new BothPostValidator(name, year, avatar, ringtone).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[BothPostResponses200]
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
                        name <- StringGenerator
                        year <- OptionBigIntGenerator
                        avatar <- OptionFileGenerator
                        ringtone <- FileGenerator
                    
                } yield (name, year, avatar, ringtone)
            val inputs = genInputs suchThat { case (name, year, avatar, ringtone) =>
                new BothPostValidator(name, year, avatar, ringtone).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            assert(checkResult(props) == Success())
        }
        "do something with valid data" in {
            val genInputs = for {
                    name <- StringGenerator
                    year <- OptionBigIntGenerator
                    avatar <- OptionFileGenerator
                    ringtone <- FileGenerator
                
            } yield (name, year, avatar, ringtone)
            val inputs = genInputs suchThat { case (name, year, avatar, ringtone) =>
                new BothPostValidator(name, year, avatar, ringtone).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            assert(checkResult(props) == Success())
        }

    }

    "POST /form_data/url-encoded" should {
        def testInvalidInput(input: (String, Option[BigInt], File)): Prop = {

            val (name, year, avatar) = input

            val url = s"""/form_data/url-encoded"""
            val contentTypes: Seq[String] = Seq(
                "application/x-www-form-urlencoded"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ct, ac)
            if (contentHeaders.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")

            val propertyList = contentHeaders.map { case (consumes, produces) =>
                val headers =
                    Seq() :+ ("Accept" -> produces) :+ ("Content-Type" -> consumes)


                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (consumes == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Seq(FilePart("avatar", avatar.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(avatar))) ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq     
                        val form = new MultipartFormData(data, files, Nil)

                        route(app, request.withMultipartFormDataBody(form)).get
                    } else if (consumes == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::       Nil
                        route(app, request.withFormUrlEncodedBody(form:_*)).get
                    } else route(app, request).get

                val errors = new Url_encodedPostValidator(name, year, avatar).errors

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
        def testValidInput(input: (String, Option[BigInt], File)): Prop = {
            val (name, year, avatar) = input
            
            val url = s"""/form_data/url-encoded"""
            val consumesAll: Seq[String] = Seq(
                "application/x-www-form-urlencoded"
            )
            val producesAll: Seq[String] = Seq(
                "application/json"
            )
            val contentHeaders = for { cs <- consumesAll; ps <- producesAll } yield (cs, ps)
            if (contentHeaders.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")

            val propertyList = contentHeaders.map { case (consumes, produces) =>
                val headers =
                   Seq() :+ ("Accept" -> produces) :+ ("Content-Type" -> consumes)

                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (consumes == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Seq(FilePart("avatar", avatar.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(avatar))) ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq     
                        val form = new MultipartFormData(data, files, Nil)

                        route(app, request.withMultipartFormDataBody(form)).get
                    } else if (consumes == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::       Nil
                        route(app, request.withFormUrlEncodedBody(form:_*)).get
                    } else route(app, request).get

                val errors = new Url_encodedPostValidator(name, year, avatar).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[MultipartPostResponses200]
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
                        name <- StringGenerator
                        year <- OptionBigIntGenerator
                        avatar <- FileGenerator
                    
                } yield (name, year, avatar)
            val inputs = genInputs suchThat { case (name, year, avatar) =>
                new Url_encodedPostValidator(name, year, avatar).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            assert(checkResult(props) == Success())
        }
        "do something with valid data" in {
            val genInputs = for {
                    name <- StringGenerator
                    year <- OptionBigIntGenerator
                    avatar <- FileGenerator
                
            } yield (name, year, avatar)
            val inputs = genInputs suchThat { case (name, year, avatar) =>
                new Url_encodedPostValidator(name, year, avatar).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            assert(checkResult(props) == Success())
        }

    }
}
