package uber.api.yaml

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

import java.util.UUID
import scala.math.BigDecimal
import de.zalando.play.controllers.ArrayWrapper

import Generators._

    @RunWith(classOf[JUnitRunner])
    class Uber_api_yamlSpec extends Specification {
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


    "GET /v1/history" should {
        def testInvalidInput(input: (ErrorCode, ErrorCode)) = {

            val (offset, limit) = input

            val url = s"""/v1/history?${toQuery("offset", offset)}&${toQuery("limit", limit)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
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

                val errors = new HistoryGetValidator(offset, limit).errors

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
        def testValidInput(input: (ErrorCode, ErrorCode)) = {
            val (offset, limit) = input
            
            val url = s"""/v1/history?${toQuery("offset", offset)}&${toQuery("limit", limit)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
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

                val errors = new HistoryGetValidator(offset, limit).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Activities]
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
                        offset <- ErrorCodeGenerator
                        limit <- ErrorCodeGenerator
                    
                } yield (offset, limit)
            val inputs = genInputs suchThat { case (offset, limit) =>
                new HistoryGetValidator(offset, limit).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    offset <- ErrorCodeGenerator
                    limit <- ErrorCodeGenerator
                
            } yield (offset, limit)
            val inputs = genInputs suchThat { case (offset, limit) =>
                new HistoryGetValidator(offset, limit).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "GET /v1/estimates/time" should {
        def testInvalidInput(input: (Double, Double, EstimatesTimeGetCustomer_uuid, ProfilePicture)) = {

            val (start_latitude, start_longitude, customer_uuid, product_id) = input

            val url = s"""/v1/estimates/time?${toQuery("start_latitude", start_latitude)}&${toQuery("start_longitude", start_longitude)}&${toQuery("customer_uuid", customer_uuid)}&${toQuery("product_id", product_id)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
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

                val errors = new EstimatesTimeGetValidator(start_latitude, start_longitude, customer_uuid, product_id).errors

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
        def testValidInput(input: (Double, Double, EstimatesTimeGetCustomer_uuid, ProfilePicture)) = {
            val (start_latitude, start_longitude, customer_uuid, product_id) = input
            
            val url = s"""/v1/estimates/time?${toQuery("start_latitude", start_latitude)}&${toQuery("start_longitude", start_longitude)}&${toQuery("customer_uuid", customer_uuid)}&${toQuery("product_id", product_id)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
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

                val errors = new EstimatesTimeGetValidator(start_latitude, start_longitude, customer_uuid, product_id).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Seq[Product]]
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
                        start_latitude <- DoubleGenerator
                        start_longitude <- DoubleGenerator
                        customer_uuid <- EstimatesTimeGetCustomer_uuidGenerator
                        product_id <- ProfilePictureGenerator
                    
                } yield (start_latitude, start_longitude, customer_uuid, product_id)
            val inputs = genInputs suchThat { case (start_latitude, start_longitude, customer_uuid, product_id) =>
                new EstimatesTimeGetValidator(start_latitude, start_longitude, customer_uuid, product_id).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    start_latitude <- DoubleGenerator
                    start_longitude <- DoubleGenerator
                    customer_uuid <- EstimatesTimeGetCustomer_uuidGenerator
                    product_id <- ProfilePictureGenerator
                
            } yield (start_latitude, start_longitude, customer_uuid, product_id)
            val inputs = genInputs suchThat { case (start_latitude, start_longitude, customer_uuid, product_id) =>
                new EstimatesTimeGetValidator(start_latitude, start_longitude, customer_uuid, product_id).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "GET /v1/products" should {
        def testInvalidInput(input: (Double, Double)) = {

            val (latitude, longitude) = input

            val url = s"""/v1/products?${toQuery("latitude", latitude)}&${toQuery("longitude", longitude)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
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

                val errors = new ProductsGetValidator(latitude, longitude).errors

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
        def testValidInput(input: (Double, Double)) = {
            val (latitude, longitude) = input
            
            val url = s"""/v1/products?${toQuery("latitude", latitude)}&${toQuery("longitude", longitude)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
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

                val errors = new ProductsGetValidator(latitude, longitude).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Seq[Product]]
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
                        latitude <- DoubleGenerator
                        longitude <- DoubleGenerator
                    
                } yield (latitude, longitude)
            val inputs = genInputs suchThat { case (latitude, longitude) =>
                new ProductsGetValidator(latitude, longitude).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    latitude <- DoubleGenerator
                    longitude <- DoubleGenerator
                
            } yield (latitude, longitude)
            val inputs = genInputs suchThat { case (latitude, longitude) =>
                new ProductsGetValidator(latitude, longitude).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }

    "GET /v1/estimates/price" should {
        def testInvalidInput(input: (Double, Double, Double, Double)) = {

            val (start_latitude, start_longitude, end_latitude, end_longitude) = input

            val url = s"""/v1/estimates/price?${toQuery("start_latitude", start_latitude)}&${toQuery("start_longitude", start_longitude)}&${toQuery("end_latitude", end_latitude)}&${toQuery("end_longitude", end_longitude)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
               "application/json"
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

                val errors = new EstimatesPriceGetValidator(start_latitude, start_longitude, end_latitude, end_longitude).errors

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
        def testValidInput(input: (Double, Double, Double, Double)) = {
            val (start_latitude, start_longitude, end_latitude, end_longitude) = input
            
            val url = s"""/v1/estimates/price?${toQuery("start_latitude", start_latitude)}&${toQuery("start_longitude", start_longitude)}&${toQuery("end_latitude", end_latitude)}&${toQuery("end_longitude", end_longitude)}"""
            val contentTypes: Seq[String] = Seq()
            val acceptHeaders: Seq[String] = Seq(
                "application/json"
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

                val errors = new EstimatesPriceGetValidator(start_latitude, start_longitude, end_latitude, end_longitude).errors
                val possibleResponseTypes: Map[Int,Class[_ <: Any]] = Map(
                    200 -> classOf[Seq[PriceEstimate]]
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
                        start_latitude <- DoubleGenerator
                        start_longitude <- DoubleGenerator
                        end_latitude <- DoubleGenerator
                        end_longitude <- DoubleGenerator
                    
                } yield (start_latitude, start_longitude, end_latitude, end_longitude)
            val inputs = genInputs suchThat { case (start_latitude, start_longitude, end_latitude, end_longitude) =>
                new EstimatesPriceGetValidator(start_latitude, start_longitude, end_latitude, end_longitude).errors.nonEmpty
            }
            val props = forAll(inputs) { i => testInvalidInput(i) }
            checkResult(props)
        }
        "do something with valid data" in new WithApplication {
            val genInputs = for {
                    start_latitude <- DoubleGenerator
                    start_longitude <- DoubleGenerator
                    end_latitude <- DoubleGenerator
                    end_longitude <- DoubleGenerator
                
            } yield (start_latitude, start_longitude, end_latitude, end_longitude)
            val inputs = genInputs suchThat { case (start_latitude, start_longitude, end_latitude, end_longitude) =>
                new EstimatesPriceGetValidator(start_latitude, start_longitude, end_latitude, end_longitude).errors.isEmpty
            }
            val props = forAll(inputs) { i => testValidInput(i) }
            checkResult(props)
        }

    }
}
