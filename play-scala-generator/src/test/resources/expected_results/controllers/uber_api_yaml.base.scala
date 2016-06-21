package uber.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._

import scala.util._
import java.util.UUID
import scala.math.BigDecimal
import de.zalando.play.controllers.ArrayWrapper

import de.zalando.play.controllers.PlayPathBindables





trait UberApiYamlBase extends Controller with PlayBodyParsing {
    sealed trait GetmeType[T] extends ResultWrapper[T]
    case class Getme200(result: Profile)(implicit val writer: String => Option[Writeable[Profile]]) extends GetmeType[Profile] { val statusCode = 200 }
    

    private type getmeActionRequestType       = (Unit)
    private type getmeActionType[T]            = getmeActionRequestType => GetmeType[T] forSome { type T }


    val getmeActionConstructor  = Action

def getmeAction[T] = (f: getmeActionType[T]) => getmeActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmeResponseMimeType =>
            
            

                val result = processValidgetmeRequest(f)()(getmeResponseMimeType)
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetmeRequest[T](f: getmeActionType[T])(request: getmeActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetproductsType[T] extends ResultWrapper[T]
    case class Getproducts200(result: Seq[Product])(implicit val writer: String => Option[Writeable[Seq[Product]]]) extends GetproductsType[Seq[Product]] { val statusCode = 200 }
    

    private type getproductsActionRequestType       = (Double, Double)
    private type getproductsActionType[T]            = getproductsActionRequestType => GetproductsType[T] forSome { type T }


    val getproductsActionConstructor  = Action

def getproductsAction[T] = (f: getproductsActionType[T]) => (latitude: Double, longitude: Double) => getproductsActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getproductsResponseMimeType =>
            
            

                val result =
                        new ProductsGetValidator(latitude, longitude).errors match {
                            case e if e.isEmpty => processValidgetproductsRequest(f)((latitude, longitude))(getproductsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getproductsResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetproductsRequest[T](f: getproductsActionType[T])(request: getproductsActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetestimatesTimeType[T] extends ResultWrapper[T]
    case class GetestimatesTime200(result: Seq[Product])(implicit val writer: String => Option[Writeable[Seq[Product]]]) extends GetestimatesTimeType[Seq[Product]] { val statusCode = 200 }
    

    private type getestimatesTimeActionRequestType       = (Double, Double, EstimatesTimeGetCustomer_uuid, ProfilePicture)
    private type getestimatesTimeActionType[T]            = getestimatesTimeActionRequestType => GetestimatesTimeType[T] forSome { type T }


    val getestimatesTimeActionConstructor  = Action

def getestimatesTimeAction[T] = (f: getestimatesTimeActionType[T]) => (start_latitude: Double, start_longitude: Double, customer_uuid: EstimatesTimeGetCustomer_uuid, product_id: ProfilePicture) => getestimatesTimeActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getestimatesTimeResponseMimeType =>
            
            

                val result =
                        new EstimatesTimeGetValidator(start_latitude, start_longitude, customer_uuid, product_id).errors match {
                            case e if e.isEmpty => processValidgetestimatesTimeRequest(f)((start_latitude, start_longitude, customer_uuid, product_id))(getestimatesTimeResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getestimatesTimeResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetestimatesTimeRequest[T](f: getestimatesTimeActionType[T])(request: getestimatesTimeActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GetestimatesPriceType[T] extends ResultWrapper[T]
    case class GetestimatesPrice200(result: Seq[PriceEstimate])(implicit val writer: String => Option[Writeable[Seq[PriceEstimate]]]) extends GetestimatesPriceType[Seq[PriceEstimate]] { val statusCode = 200 }
    

    private type getestimatesPriceActionRequestType       = (Double, Double, Double, Double)
    private type getestimatesPriceActionType[T]            = getestimatesPriceActionRequestType => GetestimatesPriceType[T] forSome { type T }


    val getestimatesPriceActionConstructor  = Action

def getestimatesPriceAction[T] = (f: getestimatesPriceActionType[T]) => (start_latitude: Double, start_longitude: Double, end_latitude: Double, end_longitude: Double) => getestimatesPriceActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getestimatesPriceResponseMimeType =>
            
            

                val result =
                        new EstimatesPriceGetValidator(start_latitude, start_longitude, end_latitude, end_longitude).errors match {
                            case e if e.isEmpty => processValidgetestimatesPriceRequest(f)((start_latitude, start_longitude, end_latitude, end_longitude))(getestimatesPriceResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getestimatesPriceResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgetestimatesPriceRequest[T](f: getestimatesPriceActionType[T])(request: getestimatesPriceActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    sealed trait GethistoryType[T] extends ResultWrapper[T]
    case class Gethistory200(result: Activities)(implicit val writer: String => Option[Writeable[Activities]]) extends GethistoryType[Activities] { val statusCode = 200 }
    

    private type gethistoryActionRequestType       = (ErrorCode, ErrorCode)
    private type gethistoryActionType[T]            = gethistoryActionRequestType => GethistoryType[T] forSome { type T }


    val gethistoryActionConstructor  = Action

def gethistoryAction[T] = (f: gethistoryActionType[T]) => (offset: ErrorCode, limit: ErrorCode) => gethistoryActionConstructor { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { gethistoryResponseMimeType =>
            
            

                val result =
                        new HistoryGetValidator(offset, limit).errors match {
                            case e if e.isEmpty => processValidgethistoryRequest(f)((offset, limit))(gethistoryResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gethistoryResponseMimeType)
                                BadRequest(l)
                        }
                result
            
        }.getOrElse(Status(415)("The server doesn't support any of the requested mime types"))
    }

    private def processValidgethistoryRequest[T](f: gethistoryActionType[T])(request: gethistoryActionRequestType)(mimeType: String) = {
      f(request).toResult(mimeType).getOrElse {
        Results.NotAcceptable
      }
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetmeType[Result] with GetproductsType[Result] with GetestimatesTimeType[Result] with GetestimatesPriceType[Result] with GethistoryType[Result] { val result = Results.Status(204).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.Status(204)) }
    case object NotImplementedYet extends ResultWrapper[Results.EmptyContent]  with GetmeType[Results.EmptyContent] with GetproductsType[Results.EmptyContent] with GetestimatesTimeType[Results.EmptyContent] with GetestimatesPriceType[Results.EmptyContent] with GethistoryType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
}
