package com.foodpanda.popsey.api

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._




//noinspection ScalaStyle
trait FoodpandaYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait SearchVendorsType[T] extends ResultWrapper[T]
    def SearchVendors200(resultP: Int)(implicit writerP: String => Option[Writeable[Int]]) = success(new SearchVendorsType[Int] { val statusCode = 200; val result = resultP; val writer = writerP })
    def SearchVendors200(resultF: Future[Int])(implicit writerP: String => Option[Writeable[Int]]) = resultF map { resultP => (new SearchVendorsType[Int] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type searchVendorsActionRequestType       = (VendorQuery)
    private type searchVendorsActionType[T]            = searchVendorsActionRequestType => Future[SearchVendorsType[T] forSome { type T }]

        
        import BodyReads._
        
        val searchVendorsParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val searchVendorsActionConstructor  = Action

def searchVendorsAction[T] = (f: searchVendorsActionType[T]) => searchVendorsActionConstructor.async(searchVendorsParser) { implicit request: Request[VendorQuery] =>

        def processValidsearchVendorsRequest(query: VendorQuery): Either[Result, Future[SearchVendorsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((query)))
            
            new VendorsGetValidator(query).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val query: VendorQuery = request.body
            
            

            processValidsearchVendorsRequest(query) match {
                case Left(l) => success(l)
                case Right(r: Future[SearchVendorsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { searchVendorsResponseMimeType =>
                        import MissingDefaultWrites._
                        r.map(_.toResult(searchVendorsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with SearchVendorsType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with SearchVendorsType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
