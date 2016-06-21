package form_data.yaml

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

import java.io.File
import scala.math.BigInt

import Generators._

    @RunWith(classOf[JUnitRunner])
    class Form_data_yamlSpec extends Specification {
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


    "POST /form_data/multipart" should {
        def testInvalidInput(input: (String, BothPostYear, MultipartPostAvatar)) = {

            val (name, year, avatar) = input

            val url = s"""/form_data/multipart"""
            val contentTypes: Seq[String] = Seq(
                "multipart/form-data"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)


                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = avatar.toSeq.map { m => FilePart("avatar", m.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(m)) } ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq     
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::       Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new MultipartPostValidator(name, year, avatar).errors

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
        def testValidInput(input: (String, BothPostYear, MultipartPostAvatar)) = {
            val (name, year, avatar) = input
            
            val url = s"""/form_data/multipart"""
            val contentTypes: Seq[String] = Seq(
                "multipart/form-data"
            )
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = avatar.toSeq.map { m => FilePart("avatar", m.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(m)) } ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq     
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::       Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new MultipartPostValidator(name, year, avatar).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[MultipartPostResponses200]
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
                        name <- StringGenerator
                        year <- BothPostYearGenerator
                        avatar <- MultipartPostAvatarGenerator
                    
                } yield (name, year, avatar)
            val inputs = genInputs suchThat { case (name, year, avatar) =>
                new MultipartPostValidator(name, year, avatar).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    name <- StringGenerator
                    year <- BothPostYearGenerator
                    avatar <- MultipartPostAvatarGenerator
                
            } yield (name, year, avatar)
            val inputs = genInputs suchThat { case (name, year, avatar) =>
                new MultipartPostValidator(name, year, avatar).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "POST /form_data/both" should {
        def testInvalidInput(input: (String, BothPostYear, MultipartPostAvatar, File)) = {

            val (name, year, avatar, ringtone) = input

            val url = s"""/form_data/both"""
            val contentTypes: Seq[String] = Seq(
                "application/x-www-form-urlencoded", 
            
                "multipart/form-data"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)


                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = avatar.toSeq.map { m => FilePart("avatar", m.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(m)) } ++ Seq(FilePart("ringtone", ringtone.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(ringtone))) ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq        
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::          Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new BothPostValidator(name, year, avatar, ringtone).errors

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
        def testValidInput(input: (String, BothPostYear, MultipartPostAvatar, File)) = {
            val (name, year, avatar, ringtone) = input
            
            val url = s"""/form_data/both"""
            val contentTypes: Seq[String] = Seq(
                "application/x-www-form-urlencoded", 
            
                "multipart/form-data"
            )
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = avatar.toSeq.map { m => FilePart("avatar", m.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(m)) } ++ Seq(FilePart("ringtone", ringtone.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(ringtone))) ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq        
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::          Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new BothPostValidator(name, year, avatar, ringtone).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[BothPostResponses200]
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
                        name <- StringGenerator
                        year <- BothPostYearGenerator
                        avatar <- MultipartPostAvatarGenerator
                        ringtone <- FileGenerator
                    
                } yield (name, year, avatar, ringtone)
            val inputs = genInputs suchThat { case (name, year, avatar, ringtone) =>
                new BothPostValidator(name, year, avatar, ringtone).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    name <- StringGenerator
                    year <- BothPostYearGenerator
                    avatar <- MultipartPostAvatarGenerator
                    ringtone <- FileGenerator
                
            } yield (name, year, avatar, ringtone)
            val inputs = genInputs suchThat { case (name, year, avatar, ringtone) =>
                new BothPostValidator(name, year, avatar, ringtone).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "POST /form_data/url-encoded" should {
        def testInvalidInput(input: (String, BothPostYear, File)) = {

            val (name, year, avatar) = input

            val url = s"""/form_data/url-encoded"""
            val contentTypes: Seq[String] = Seq(
                "application/x-www-form-urlencoded"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)


                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Seq(FilePart("avatar", avatar.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(avatar))) ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq     
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::       Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new Url_encodedPostValidator(name, year, avatar).errors

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
        def testValidInput(input: (String, BothPostYear, File)) = {
            val (name, year, avatar) = input
            
            val url = s"""/form_data/url-encoded"""
            val contentTypes: Seq[String] = Seq(
                "application/x-www-form-urlencoded"
            )
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(POST, url).withHeaders(headers:_*)
                val path =
                    if (contentType == "multipart/form-data") {
                        import de.zalando.play.controllers.WriteableWrapper.anyContentAsMultipartFormWritable

                        val files: Seq[FilePart[TemporaryFile]] = Seq(FilePart("avatar", avatar.getName, Some("Content-Type: multipart/form-data"), TemporaryFile(avatar))) ++ Nil
                        val data = Map.empty[String, Seq[String]]   ++ Seq("name" -> Seq(name.toString))     ++ year.map(m => "year" -> Seq(m.toString)).toSeq     
                        val form = new MultipartFormData(data, files, Nil)

                        route(request.withMultipartFormDataBody(form)).get
                    } else if (contentType == "application/x-www-form-urlencoded") {
                        val form =   ("name" -> name.toString) ::     year.map(m => "year" -> m.toString).toList :::       Nil
                        route(request.withFormUrlEncodedBody(form:_*)).get
                    } else route(request).get

                val errors = new Url_encodedPostValidator(name, year, avatar).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[MultipartPostResponses200]
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
                        name <- StringGenerator
                        year <- BothPostYearGenerator
                        avatar <- FileGenerator
                    
                } yield (name, year, avatar)
            val inputs = genInputs suchThat { case (name, year, avatar) =>
                new Url_encodedPostValidator(name, year, avatar).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    name <- StringGenerator
                    year <- BothPostYearGenerator
                    avatar <- FileGenerator
                
            } yield (name, year, avatar)
            val inputs = genInputs suchThat { case (name, year, avatar) =>
                new Url_encodedPostValidator(name, year, avatar).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }
}
