package simple_petstore_api_yaml

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

import de.zalando.play.controllers.ArrayWrapper

import Generators._

    @RunWith(classOf[JUnitRunner])
    class Simple_petstore_api_yamlSpec extends Specification {
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


    "POST /api/pets" should {
        def testInvalidInput(pet: NewPet) = {


            val url = s"""/api/pets"""
            val contentTypes: Seq[String] = Seq(
                "application/json"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                    val parsed_pet = PlayBodyParsing.jacksonMapper("ErrorModel").writeValueAsString(pet)

                val request = FakeRequest(POST, url).withHeaders(headers:_*).withBody(parsed_pet)
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

                val errors = new PetsPostValidator(pet).errors

                lazy val validations = errors flatMap { _.messages } map { m =>
                    s"Contains error: $m in ${contentAsString(path)}" |:(contentAsString(path).contains(m) ?= true)
                }

                (s"given 'Content-Type' [$contentType], 'Accept' header [$acceptHeader] and URL: [$url]" + "and body [" + parsed_pet + "]") |: all(
                    "StatusCode = BAD_REQUEST" |: (requestStatusCode_(path) ?= BAD_REQUEST),
                    s"Content-Type = $acceptHeader" |: (requestContentType_(path) ?= Some(acceptHeader)),
                    "non-empty errors" |: (errors.nonEmpty ?= true),
                    "at least one validation failing" |: atLeastOne(validations:_*)
                )
            }
            if (propertyList.isEmpty) throw new IllegalStateException(s"No 'produces' defined for the $url")
            propertyList.reduce(_ && _)
        }
        def testValidInput(pet: NewPet) = {
            
            val parsed_pet = parserConstructor("ErrorModel").writeValueAsString(pet)
            
            val url = s"""/api/pets"""
            val contentTypes: Seq[String] = Seq(
                "application/json"
            )
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(POST, url).withHeaders(headers:_*).withBody(parsed_pet)
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

                val errors = new PetsPostValidator(pet).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Pet]
                )

                val expectedCode = requestStatusCode_(path)
                val mimeType = requestContentType_(path)
                val mapper = parserConstructor(mimeType.getOrElse("application/json"))

                val parsedApiResponse = scala.util.Try {
                    parseResponseContent(mapper, requestContentAsString_(path), mimeType, possibleResponseTypes(expectedCode))
                }

                (s"Given response code [$expectedCode], 'Content-Type' [$contentType], 'Accept' header [$acceptHeader] and URL: [$url]" + "and body [" + parsed_pet + "]") |: all(
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
                    pet <- NewPetGenerator
                } yield pet
            val inputs = genInputs suchThat { pet =>
                new PetsPostValidator(pet).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                pet <- NewPetGenerator
            } yield pet
            val inputs = genInputs suchThat { pet =>
                new PetsPostValidator(pet).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "GET /api/pets" should {
        def testInvalidInput(input: (PetsGetTags, PetsGetLimit)) = {

            val (tags, limit) = input

            val url = s"""/api/pets?${toQuery("tags", tags)}&${toQuery("limit", limit)}"""
            val contentTypes: Seq[String] = Seq(
                "application/json"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json", 
            
               "application/xml", 
            
               "text/xml", 
            
               "text/html"
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

                val errors = new PetsGetValidator(tags, limit).errors

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
        def testValidInput(input: (PetsGetTags, PetsGetLimit)) = {
            val (tags, limit) = input
            
            val url = s"""/api/pets?${toQuery("tags", tags)}&${toQuery("limit", limit)}"""
            val contentTypes: Seq[String] = Seq(
                "application/json"
            )
            val acceptHeaders: Seq[String] = Seq(
                "application/json", 
            
                "application/xml", 
            
                "text/xml", 
            
                "text/html"
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

                val errors = new PetsGetValidator(tags, limit).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Seq[Pet]]
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
                        tags <- PetsGetTagsGenerator
                        limit <- PetsGetLimitGenerator
                    
                } yield (tags, limit)
            val inputs = genInputs suchThat { case (tags, limit) =>
                new PetsGetValidator(tags, limit).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    tags <- PetsGetTagsGenerator
                    limit <- PetsGetLimitGenerator
                
            } yield (tags, limit)
            val inputs = genInputs suchThat { case (tags, limit) =>
                new PetsGetValidator(tags, limit).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "GET /api/pets/{id}" should {
        def testInvalidInput(id: Long) = {


            val url = s"""/api/pets/${toPath(id)}"""
            val contentTypes: Seq[String] = Seq(
                "application/json"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json", 
            
               "application/xml", 
            
               "text/xml", 
            
               "text/html"
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

                val errors = new PetsIdGetValidator(id).errors

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
        def testValidInput(id: Long) = {
            
            val url = s"""/api/pets/${toPath(id)}"""
            val contentTypes: Seq[String] = Seq(
                "application/json"
            )
            val acceptHeaders: Seq[String] = Seq(
                "application/json", 
            
                "application/xml", 
            
                "text/xml", 
            
                "text/html"
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

                val errors = new PetsIdGetValidator(id).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Pet]
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
                    id <- LongGenerator
                } yield id
            val inputs = genInputs suchThat { id =>
                new PetsIdGetValidator(id).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                id <- LongGenerator
            } yield id
            val inputs = genInputs suchThat { id =>
                new PetsIdGetValidator(id).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "DELETE /api/pets/{id}" should {
        def testInvalidInput(id: Long) = {


            val url = s"""/api/pets/${toPath(id)}"""
            val contentTypes: Seq[String] = Seq(
                "application/json"
            )
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                    Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)


                val request = FakeRequest(DELETE, url).withHeaders(headers:_*)
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

                val errors = new PetsIdDeleteValidator(id).errors

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
        def testValidInput(id: Long) = {
            
            val url = s"""/api/pets/${toPath(id)}"""
            val contentTypes: Seq[String] = Seq(
                "application/json"
            )
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
            )
            val contentHeaders = for { ct <- contentTypes; ac <- acceptHeaders } yield (ac, ct)
            val propertyList = contentHeaders.map { case (acceptHeader, contentType) =>
                val headers =
                   Seq() :+ ("Accept" -> acceptHeader) :+ ("Content-Type" -> contentType)

                val request = FakeRequest(DELETE, url).withHeaders(headers:_*)
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

                val errors = new PetsIdDeleteValidator(id).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    204 -> classOf[Null]
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
                    id <- LongGenerator
                } yield id
            val inputs = genInputs suchThat { id =>
                new PetsIdDeleteValidator(id).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                id <- LongGenerator
            } yield id
            val inputs = genInputs suchThat { id =>
                new PetsIdDeleteValidator(id).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }
}
