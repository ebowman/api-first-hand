package uber.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import java.util.UUID
import scala.math.BigDecimal
import de.zalando.play.controllers.ArrayWrapper

import de.zalando.play.controllers.PlayPathBindables





//noinspection ScalaStyle
trait UberApiYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    def success[T](t: => T) = Future.successful(t)
    sealed trait GetmeType[T] extends ResultWrapper[T]
    def Getme200(resultP: Profile)(implicit writerP: String => Option[Writeable[Profile]]) = success(new GetmeType[Profile] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Getme200(resultF: Future[Profile])(implicit writerP: String => Option[Writeable[Profile]]) = resultF map { resultP => (new GetmeType[Profile] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getmeActionRequestType       = (Unit)
    private type getmeActionType[T]            = getmeActionRequestType => Future[GetmeType[T] forSome { type T }]


    val getmeActionConstructor  = Action

def getmeAction[T] = (f: getmeActionType[T]) => getmeActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getmeResponseMimeType =>
            
            

                val result = processValidgetmeRequest(f)()(getmeResponseMimeType)
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetmeRequest[T](f: getmeActionType[T])(request: getmeActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetproductsType[T] extends ResultWrapper[T]
    def Getproducts200(resultP: Seq[Product])(implicit writerP: String => Option[Writeable[Seq[Product]]]) = success(new GetproductsType[Seq[Product]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Getproducts200(resultF: Future[Seq[Product]])(implicit writerP: String => Option[Writeable[Seq[Product]]]) = resultF map { resultP => (new GetproductsType[Seq[Product]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getproductsActionRequestType       = (Double, Double)
    private type getproductsActionType[T]            = getproductsActionRequestType => Future[GetproductsType[T] forSome { type T }]


    val getproductsActionConstructor  = Action

def getproductsAction[T] = (f: getproductsActionType[T]) => (latitude: Double, longitude: Double) => getproductsActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getproductsResponseMimeType =>
            
            

                val result =
                        new ProductsGetValidator(latitude, longitude).errors match {
                            case e if e.isEmpty => processValidgetproductsRequest(f)((latitude, longitude))(getproductsResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getproductsResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetproductsRequest[T](f: getproductsActionType[T])(request: getproductsActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetestimatesTimeType[T] extends ResultWrapper[T]
    def GetestimatesTime200(resultP: Seq[Product])(implicit writerP: String => Option[Writeable[Seq[Product]]]) = success(new GetestimatesTimeType[Seq[Product]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetestimatesTime200(resultF: Future[Seq[Product]])(implicit writerP: String => Option[Writeable[Seq[Product]]]) = resultF map { resultP => (new GetestimatesTimeType[Seq[Product]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getestimatesTimeActionRequestType       = (Double, Double, EstimatesTimeGetCustomer_uuid, ProfilePicture)
    private type getestimatesTimeActionType[T]            = getestimatesTimeActionRequestType => Future[GetestimatesTimeType[T] forSome { type T }]


    val getestimatesTimeActionConstructor  = Action

def getestimatesTimeAction[T] = (f: getestimatesTimeActionType[T]) => (start_latitude: Double, start_longitude: Double, customer_uuid: EstimatesTimeGetCustomer_uuid, product_id: ProfilePicture) => getestimatesTimeActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getestimatesTimeResponseMimeType =>
            
            

                val result =
                        new EstimatesTimeGetValidator(start_latitude, start_longitude, customer_uuid, product_id).errors match {
                            case e if e.isEmpty => processValidgetestimatesTimeRequest(f)((start_latitude, start_longitude, customer_uuid, product_id))(getestimatesTimeResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getestimatesTimeResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetestimatesTimeRequest[T](f: getestimatesTimeActionType[T])(request: getestimatesTimeActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GetestimatesPriceType[T] extends ResultWrapper[T]
    def GetestimatesPrice200(resultP: Seq[PriceEstimate])(implicit writerP: String => Option[Writeable[Seq[PriceEstimate]]]) = success(new GetestimatesPriceType[Seq[PriceEstimate]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetestimatesPrice200(resultF: Future[Seq[PriceEstimate]])(implicit writerP: String => Option[Writeable[Seq[PriceEstimate]]]) = resultF map { resultP => (new GetestimatesPriceType[Seq[PriceEstimate]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getestimatesPriceActionRequestType       = (Double, Double, Double, Double)
    private type getestimatesPriceActionType[T]            = getestimatesPriceActionRequestType => Future[GetestimatesPriceType[T] forSome { type T }]


    val getestimatesPriceActionConstructor  = Action

def getestimatesPriceAction[T] = (f: getestimatesPriceActionType[T]) => (start_latitude: Double, start_longitude: Double, end_latitude: Double, end_longitude: Double) => getestimatesPriceActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { getestimatesPriceResponseMimeType =>
            
            

                val result =
                        new EstimatesPriceGetValidator(start_latitude, start_longitude, end_latitude, end_longitude).errors match {
                            case e if e.isEmpty => processValidgetestimatesPriceRequest(f)((start_latitude, start_longitude, end_latitude, end_longitude))(getestimatesPriceResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(getestimatesPriceResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgetestimatesPriceRequest[T](f: getestimatesPriceActionType[T])(request: getestimatesPriceActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    sealed trait GethistoryType[T] extends ResultWrapper[T]
    def Gethistory200(resultP: Activities)(implicit writerP: String => Option[Writeable[Activities]]) = success(new GethistoryType[Activities] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Gethistory200(resultF: Future[Activities])(implicit writerP: String => Option[Writeable[Activities]]) = resultF map { resultP => (new GethistoryType[Activities] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type gethistoryActionRequestType       = (ErrorCode, ErrorCode)
    private type gethistoryActionType[T]            = gethistoryActionRequestType => Future[GethistoryType[T] forSome { type T }]


    val gethistoryActionConstructor  = Action

def gethistoryAction[T] = (f: gethistoryActionType[T]) => (offset: ErrorCode, limit: ErrorCode) => gethistoryActionConstructor.async { request =>
        val providedTypes = Seq[String]("application/json")

        negotiateContent(request.acceptedTypes, providedTypes).map { gethistoryResponseMimeType =>
            
            

                val result =
                        new HistoryGetValidator(offset, limit).errors match {
                            case e if e.isEmpty => processValidgethistoryRequest(f)((offset, limit))(gethistoryResponseMimeType)
                            case l =>
                                implicit val marshaller: Writeable[Seq[ParsingError]] = parsingErrors2Writable(gethistoryResponseMimeType)
                                success(BadRequest(l))
                        }
                result
            
        }.getOrElse(success(Status(406)("The server doesn't support any of the requested mime types")))
    }

    private def processValidgethistoryRequest[T](f: gethistoryActionType[T])(request: gethistoryActionRequestType)(mimeType: String) = {
        f(request).map(_.toResult(mimeType).getOrElse(Results.NotAcceptable))
    }
    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetmeType[Result] with GetproductsType[Result] with GetestimatesTimeType[Result] with GetestimatesPriceType[Result] with GethistoryType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetmeType[Results.EmptyContent] with GetproductsType[Results.EmptyContent] with GetestimatesTimeType[Results.EmptyContent] with GetestimatesPriceType[Results.EmptyContent] with GethistoryType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
