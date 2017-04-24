package uber.api.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import scala.math.BigDecimal
import java.util.UUID

import de.zalando.play.controllers.PlayPathBindables




//noinspection ScalaStyle
trait UberApiYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait GetmeType[T] extends ResultWrapper[T]
    def Getme200(resultP: Profile)(implicit writerP: String => Option[Writeable[Profile]]) = success(new GetmeType[Profile] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Getme200(resultF: Future[Profile])(implicit writerP: String => Option[Writeable[Profile]]) = resultF map { resultP => (new GetmeType[Profile] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getmeActionRequestType       = (Unit)
    private type getmeActionType[T]            = getmeActionRequestType => Future[GetmeType[T] forSome { type T }]


    val getmeActionConstructor  = Action

def getmeAction[T] = (f: getmeActionType[T]) => getmeActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetmeRequest(): Either[Result, Future[GetmeType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f(()))
            apiFirstTempResultHolder
        }

            
            

            processValidgetmeRequest() match {
                case Left(l) => success(l)
                case Right(r: Future[GetmeType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getmeResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getmeResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetproductsType[T] extends ResultWrapper[T]
    def Getproducts200(resultP: Seq[Product])(implicit writerP: String => Option[Writeable[Seq[Product]]]) = success(new GetproductsType[Seq[Product]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Getproducts200(resultF: Future[Seq[Product]])(implicit writerP: String => Option[Writeable[Seq[Product]]]) = resultF map { resultP => (new GetproductsType[Seq[Product]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getproductsActionRequestType       = (Double, Double)
    private type getproductsActionType[T]            = getproductsActionRequestType => Future[GetproductsType[T] forSome { type T }]


    val getproductsActionConstructor  = Action

def getproductsAction[T] = (f: getproductsActionType[T]) => (latitude: Double, longitude: Double) => getproductsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetproductsRequest(latitude: Double, longitude: Double): Either[Result, Future[GetproductsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((latitude, longitude)))
            
            new ProductsGetValidator(latitude, longitude).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetproductsRequest(latitude, longitude) match {
                case Left(l) => success(l)
                case Right(r: Future[GetproductsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getproductsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getproductsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetestimatesTimeType[T] extends ResultWrapper[T]
    def GetestimatesTime200(resultP: Seq[Product])(implicit writerP: String => Option[Writeable[Seq[Product]]]) = success(new GetestimatesTimeType[Seq[Product]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetestimatesTime200(resultF: Future[Seq[Product]])(implicit writerP: String => Option[Writeable[Seq[Product]]]) = resultF map { resultP => (new GetestimatesTimeType[Seq[Product]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getestimatesTimeActionRequestType       = (Double, Double, Option[UUID], Option[String])
    private type getestimatesTimeActionType[T]            = getestimatesTimeActionRequestType => Future[GetestimatesTimeType[T] forSome { type T }]


    val getestimatesTimeActionConstructor  = Action

def getestimatesTimeAction[T] = (f: getestimatesTimeActionType[T]) => (start_latitude: Double, start_longitude: Double, customer_uuid: Option[UUID], product_id: Option[String]) => getestimatesTimeActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetestimatesTimeRequest(start_latitude: Double, start_longitude: Double, customer_uuid: Option[UUID], product_id: Option[String]): Either[Result, Future[GetestimatesTimeType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((start_latitude, start_longitude, customer_uuid, product_id)))
            
            new EstimatesTimeGetValidator(start_latitude, start_longitude, customer_uuid, product_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetestimatesTimeRequest(start_latitude, start_longitude, customer_uuid, product_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetestimatesTimeType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getestimatesTimeResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getestimatesTimeResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetestimatesPriceType[T] extends ResultWrapper[T]
    def GetestimatesPrice200(resultP: Seq[PriceEstimate])(implicit writerP: String => Option[Writeable[Seq[PriceEstimate]]]) = success(new GetestimatesPriceType[Seq[PriceEstimate]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetestimatesPrice200(resultF: Future[Seq[PriceEstimate]])(implicit writerP: String => Option[Writeable[Seq[PriceEstimate]]]) = resultF map { resultP => (new GetestimatesPriceType[Seq[PriceEstimate]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getestimatesPriceActionRequestType       = (Double, Double, Double, Double)
    private type getestimatesPriceActionType[T]            = getestimatesPriceActionRequestType => Future[GetestimatesPriceType[T] forSome { type T }]


    val getestimatesPriceActionConstructor  = Action

def getestimatesPriceAction[T] = (f: getestimatesPriceActionType[T]) => (start_latitude: Double, start_longitude: Double, end_latitude: Double, end_longitude: Double) => getestimatesPriceActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetestimatesPriceRequest(start_latitude: Double, start_longitude: Double, end_latitude: Double, end_longitude: Double): Either[Result, Future[GetestimatesPriceType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((start_latitude, start_longitude, end_latitude, end_longitude)))
            
            new EstimatesPriceGetValidator(start_latitude, start_longitude, end_latitude, end_longitude).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetestimatesPriceRequest(start_latitude, start_longitude, end_latitude, end_longitude) match {
                case Left(l) => success(l)
                case Right(r: Future[GetestimatesPriceType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getestimatesPriceResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getestimatesPriceResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GethistoryType[T] extends ResultWrapper[T]
    def Gethistory200(resultP: Activities)(implicit writerP: String => Option[Writeable[Activities]]) = success(new GethistoryType[Activities] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Gethistory200(resultF: Future[Activities])(implicit writerP: String => Option[Writeable[Activities]]) = resultF map { resultP => (new GethistoryType[Activities] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type gethistoryActionRequestType       = (Option[Int], Option[Int])
    private type gethistoryActionType[T]            = gethistoryActionRequestType => Future[GethistoryType[T] forSome { type T }]


    val gethistoryActionConstructor  = Action

def gethistoryAction[T] = (f: gethistoryActionType[T]) => (offset: Option[Int], limit: Option[Int]) => gethistoryActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgethistoryRequest(offset: Option[Int], limit: Option[Int]): Either[Result, Future[GethistoryType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((offset, limit)))
            
            new HistoryGetValidator(offset, limit).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgethistoryRequest(offset, limit) match {
                case Left(l) => success(l)
                case Right(r: Future[GethistoryType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { gethistoryResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(gethistoryResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetmeType[Result] with GetproductsType[Result] with GetestimatesTimeType[Result] with GetestimatesPriceType[Result] with GethistoryType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetmeType[Results.EmptyContent] with GetproductsType[Results.EmptyContent] with GetestimatesTimeType[Results.EmptyContent] with GetestimatesPriceType[Results.EmptyContent] with GethistoryType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
