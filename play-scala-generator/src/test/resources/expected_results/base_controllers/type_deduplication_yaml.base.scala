package type_deduplication.yaml

import scala.language.existentials
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import de.zalando.play.controllers._
import Results.Status
import PlayBodyParsing._
import scala.concurrent.Future

import scala.util._
import scala.math.BigInt

import de.zalando.play.controllers.PlayPathBindables




//noinspection ScalaStyle
trait Type_deduplicationYamlBase extends Controller with PlayBodyParsing {
    import play.api.libs.concurrent.Execution.Implicits.defaultContext
    sealed trait GetplantsByPlant_idWateringsByWatering_idType[T] extends ResultWrapper[T]
    def GetplantsByPlant_idWateringsByWatering_id404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new GetplantsByPlant_idWateringsByWatering_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def GetplantsByPlant_idWateringsByWatering_id404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new GetplantsByPlant_idWateringsByWatering_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    def GetplantsByPlant_idWateringsByWatering_id200(resultP: Watering)(implicit writerP: String => Option[Writeable[Watering]]) = success(new GetplantsByPlant_idWateringsByWatering_idType[Watering] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetplantsByPlant_idWateringsByWatering_id200(resultF: Future[Watering])(implicit writerP: String => Option[Writeable[Watering]]) = resultF map { resultP => (new GetplantsByPlant_idWateringsByWatering_idType[Watering] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getplantsByPlant_idWateringsByWatering_idActionRequestType       = (String, String)
    private type getplantsByPlant_idWateringsByWatering_idActionType[T]            = getplantsByPlant_idWateringsByWatering_idActionRequestType => Future[GetplantsByPlant_idWateringsByWatering_idType[T] forSome { type T }]


    val getplantsByPlant_idWateringsByWatering_idActionConstructor  = Action

def getplantsByPlant_idWateringsByWatering_idAction[T] = (f: getplantsByPlant_idWateringsByWatering_idActionType[T]) => (plant_id: String, watering_id: String) => getplantsByPlant_idWateringsByWatering_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsByPlant_idWateringsByWatering_idRequest(plant_id: String, watering_id: String): Either[Result, Future[GetplantsByPlant_idWateringsByWatering_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, watering_id)))
            
            new PlantsPlant_idWateringsWatering_idGetValidator(plant_id, watering_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsByPlant_idWateringsByWatering_idRequest(plant_id, watering_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsByPlant_idWateringsByWatering_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsByPlant_idWateringsByWatering_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsByPlant_idWateringsByWatering_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutplantsByPlant_idWateringsByWatering_idType[T] extends ResultWrapper[T]
    def PutplantsByPlant_idWateringsByWatering_id404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new PutplantsByPlant_idWateringsByWatering_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def PutplantsByPlant_idWateringsByWatering_id404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new PutplantsByPlant_idWateringsByWatering_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    
    def PutplantsByPlant_idWateringsByWatering_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutplantsByPlant_idWateringsByWatering_id201(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(201, headers){})
    

    private type putplantsByPlant_idWateringsByWatering_idActionRequestType       = (String, String)
    private type putplantsByPlant_idWateringsByWatering_idActionType[T]            = putplantsByPlant_idWateringsByWatering_idActionRequestType => Future[PutplantsByPlant_idWateringsByWatering_idType[T] forSome { type T }]


    val putplantsByPlant_idWateringsByWatering_idActionConstructor  = Action

def putplantsByPlant_idWateringsByWatering_idAction[T] = (f: putplantsByPlant_idWateringsByWatering_idActionType[T]) => (plant_id: String, watering_id: String) => putplantsByPlant_idWateringsByWatering_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidputplantsByPlant_idWateringsByWatering_idRequest(plant_id: String, watering_id: String): Either[Result, Future[PutplantsByPlant_idWateringsByWatering_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, watering_id)))
            
            new PlantsPlant_idWateringsWatering_idPutValidator(plant_id, watering_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidputplantsByPlant_idWateringsByWatering_idRequest(plant_id, watering_id) match {
                case Left(l) => success(l)
                case Right(r: Future[PutplantsByPlant_idWateringsByWatering_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putplantsByPlant_idWateringsByWatering_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putplantsByPlant_idWateringsByWatering_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetusersMeType[T] extends ResultWrapper[T]
    def GetusersMe200(resultP: User)(implicit writerP: String => Option[Writeable[User]]) = success(new GetusersMeType[User] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersMe200(resultF: Future[User])(implicit writerP: String => Option[Writeable[User]]) = resultF map { resultP => (new GetusersMeType[User] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersMeActionRequestType       = (Unit)
    private type getusersMeActionType[T]            = getusersMeActionRequestType => Future[GetusersMeType[T] forSome { type T }]


    val getusersMeActionConstructor  = Action

def getusersMeAction[T] = (f: getusersMeActionType[T]) => getusersMeActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetusersMeRequest(): Either[Result, Future[GetusersMeType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f())
            apiFirstTempResultHolder
        }

            
            

            processValidgetusersMeRequest() match {
                case Left(l) => success(l)
                case Right(r: Future[GetusersMeType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getusersMeResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getusersMeResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetplantsByPlant_idSunlight_needsType[T] extends ResultWrapper[T]
    def GetplantsByPlant_idSunlight_needs200(resultP: SunlightNeeds)(implicit writerP: String => Option[Writeable[SunlightNeeds]]) = success(new GetplantsByPlant_idSunlight_needsType[SunlightNeeds] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetplantsByPlant_idSunlight_needs200(resultF: Future[SunlightNeeds])(implicit writerP: String => Option[Writeable[SunlightNeeds]]) = resultF map { resultP => (new GetplantsByPlant_idSunlight_needsType[SunlightNeeds] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def GetplantsByPlant_idSunlight_needs404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type getplantsByPlant_idSunlight_needsActionRequestType       = (String)
    private type getplantsByPlant_idSunlight_needsActionType[T]            = getplantsByPlant_idSunlight_needsActionRequestType => Future[GetplantsByPlant_idSunlight_needsType[T] forSome { type T }]


    val getplantsByPlant_idSunlight_needsActionConstructor  = Action

def getplantsByPlant_idSunlight_needsAction[T] = (f: getplantsByPlant_idSunlight_needsActionType[T]) => (plant_id: String) => getplantsByPlant_idSunlight_needsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsByPlant_idSunlight_needsRequest(plant_id: String): Either[Result, Future[GetplantsByPlant_idSunlight_needsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id)))
            
            new PlantsPlant_idSunlight_needsGetValidator(plant_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsByPlant_idSunlight_needsRequest(plant_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsByPlant_idSunlight_needsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsByPlant_idSunlight_needsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsByPlant_idSunlight_needsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutplantsByPlant_idSunlight_needsType[T] extends ResultWrapper[T]
    
    def PutplantsByPlant_idSunlight_needs200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutplantsByPlant_idSunlight_needs404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type putplantsByPlant_idSunlight_needsActionRequestType       = (String, SunlightNeeds)
    private type putplantsByPlant_idSunlight_needsActionType[T]            = putplantsByPlant_idSunlight_needsActionRequestType => Future[PutplantsByPlant_idSunlight_needsType[T] forSome { type T }]

        
        import BodyReads._
        
        val putplantsByPlant_idSunlight_needsParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val putplantsByPlant_idSunlight_needsActionConstructor  = Action

def putplantsByPlant_idSunlight_needsAction[T] = (f: putplantsByPlant_idSunlight_needsActionType[T]) => (plant_id: String) => putplantsByPlant_idSunlight_needsActionConstructor.async(putplantsByPlant_idSunlight_needsParser) { implicit request: Request[SunlightNeeds] =>

        def processValidputplantsByPlant_idSunlight_needsRequest(plant_id: String, sunlight_needs: SunlightNeeds): Either[Result, Future[PutplantsByPlant_idSunlight_needsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, sunlight_needs)))
            
            new PlantsPlant_idSunlight_needsPutValidator(plant_id, sunlight_needs).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val sunlight_needs: SunlightNeeds = request.body
            
            

            processValidputplantsByPlant_idSunlight_needsRequest(plant_id, sunlight_needs) match {
                case Left(l) => success(l)
                case Right(r: Future[PutplantsByPlant_idSunlight_needsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putplantsByPlant_idSunlight_needsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putplantsByPlant_idSunlight_needsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetusersType[T] extends ResultWrapper[T]
    def Getusers200(resultP: Seq[User])(implicit writerP: String => Option[Writeable[Seq[User]]]) = success(new GetusersType[Seq[User]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Getusers200(resultF: Future[Seq[User]])(implicit writerP: String => Option[Writeable[Seq[User]]]) = resultF map { resultP => (new GetusersType[Seq[User]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersActionRequestType       = (UsersGetLimit, UsersGetLimit)
    private type getusersActionType[T]            = getusersActionRequestType => Future[GetusersType[T] forSome { type T }]


    val getusersActionConstructor  = Action

def getusersAction[T] = (f: getusersActionType[T]) => (limit: UsersGetLimit, offset: UsersGetLimit) => getusersActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetusersRequest(limit: UsersGetLimit, offset: UsersGetLimit): Either[Result, Future[GetusersType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((limit, offset)))
            
            new UsersGetValidator(limit, offset).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetusersRequest(limit, offset) match {
                case Left(l) => success(l)
                case Right(r: Future[GetusersType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getusersResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getusersResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PostusersType[T] extends ResultWrapper[T]
    
    def Postusers200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type postusersActionRequestType       = (SigninData)
    private type postusersActionType[T]            = postusersActionRequestType => Future[PostusersType[T] forSome { type T }]

        
        import BodyReads._
        
        val postusersParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val postusersActionConstructor  = Action

def postusersAction[T] = (f: postusersActionType[T]) => postusersActionConstructor.async(postusersParser) { implicit request: Request[SigninData] =>

        def processValidpostusersRequest(signin_data: SigninData): Either[Result, Future[PostusersType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((signin_data)))
            
            new UsersPostValidator(signin_data).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val signin_data: SigninData = request.body
            
            

            processValidpostusersRequest(signin_data) match {
                case Left(l) => success(l)
                case Right(r: Future[PostusersType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { postusersResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(postusersResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetareasByArea_idType[T] extends ResultWrapper[T]
    def GetareasByArea_id200(resultP: Area)(implicit writerP: String => Option[Writeable[Area]]) = success(new GetareasByArea_idType[Area] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetareasByArea_id200(resultF: Future[Area])(implicit writerP: String => Option[Writeable[Area]]) = resultF map { resultP => (new GetareasByArea_idType[Area] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getareasByArea_idActionRequestType       = (String)
    private type getareasByArea_idActionType[T]            = getareasByArea_idActionRequestType => Future[GetareasByArea_idType[T] forSome { type T }]


    val getareasByArea_idActionConstructor  = Action

def getareasByArea_idAction[T] = (f: getareasByArea_idActionType[T]) => (area_id: String) => getareasByArea_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetareasByArea_idRequest(area_id: String): Either[Result, Future[GetareasByArea_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((area_id)))
            
            new AreasArea_idGetValidator(area_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetareasByArea_idRequest(area_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetareasByArea_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getareasByArea_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getareasByArea_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutareasByArea_idType[T] extends ResultWrapper[T]
    
    def PutareasByArea_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutareasByArea_id201(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(201, headers){})
    

    private type putareasByArea_idActionRequestType       = (String)
    private type putareasByArea_idActionType[T]            = putareasByArea_idActionRequestType => Future[PutareasByArea_idType[T] forSome { type T }]


    val putareasByArea_idActionConstructor  = Action

def putareasByArea_idAction[T] = (f: putareasByArea_idActionType[T]) => (area_id: String) => putareasByArea_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidputareasByArea_idRequest(area_id: String): Either[Result, Future[PutareasByArea_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((area_id)))
            
            new AreasArea_idPutValidator(area_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidputareasByArea_idRequest(area_id) match {
                case Left(l) => success(l)
                case Right(r: Future[PutareasByArea_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putareasByArea_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putareasByArea_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait DeleteareasByArea_idType[T] extends ResultWrapper[T]
    
    def DeleteareasByArea_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type deleteareasByArea_idActionRequestType       = (String)
    private type deleteareasByArea_idActionType[T]            = deleteareasByArea_idActionRequestType => Future[DeleteareasByArea_idType[T] forSome { type T }]


    val deleteareasByArea_idActionConstructor  = Action

def deleteareasByArea_idAction[T] = (f: deleteareasByArea_idActionType[T]) => (area_id: String) => deleteareasByArea_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeleteareasByArea_idRequest(area_id: String): Either[Result, Future[DeleteareasByArea_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((area_id)))
            
            new AreasArea_idDeleteValidator(area_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValiddeleteareasByArea_idRequest(area_id) match {
                case Left(l) => success(l)
                case Right(r: Future[DeleteareasByArea_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deleteareasByArea_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deleteareasByArea_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetplantsType[T] extends ResultWrapper[T]
    def Getplants200(resultP: Seq[Plant])(implicit writerP: String => Option[Writeable[Seq[Plant]]]) = success(new GetplantsType[Seq[Plant]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Getplants200(resultF: Future[Seq[Plant]])(implicit writerP: String => Option[Writeable[Seq[Plant]]]) = resultF map { resultP => (new GetplantsType[Seq[Plant]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getplantsActionRequestType       = (PlantsGetLimit, PlantsGetOffset)
    private type getplantsActionType[T]            = getplantsActionRequestType => Future[GetplantsType[T] forSome { type T }]


    val getplantsActionConstructor  = Action

def getplantsAction[T] = (f: getplantsActionType[T]) => (limit: PlantsGetLimit, offset: PlantsGetOffset) => getplantsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsRequest(limit: PlantsGetLimit, offset: PlantsGetOffset): Either[Result, Future[GetplantsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((limit, offset)))
            
            new PlantsGetValidator(limit, offset).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsRequest(limit, offset) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetuserByUser_idPlantsType[T] extends ResultWrapper[T]
    def GetuserByUser_idPlants200(resultP: Seq[Plant])(implicit writerP: String => Option[Writeable[Seq[Plant]]]) = success(new GetuserByUser_idPlantsType[Seq[Plant]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetuserByUser_idPlants200(resultF: Future[Seq[Plant]])(implicit writerP: String => Option[Writeable[Seq[Plant]]]) = resultF map { resultP => (new GetuserByUser_idPlantsType[Seq[Plant]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def GetuserByUser_idPlants404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type getuserByUser_idPlantsActionRequestType       = (String, UsersGetLimit, UsersGetLimit)
    private type getuserByUser_idPlantsActionType[T]            = getuserByUser_idPlantsActionRequestType => Future[GetuserByUser_idPlantsType[T] forSome { type T }]


    val getuserByUser_idPlantsActionConstructor  = Action

def getuserByUser_idPlantsAction[T] = (f: getuserByUser_idPlantsActionType[T]) => (user_id: String, limit: UsersGetLimit, offset: UsersGetLimit) => getuserByUser_idPlantsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetuserByUser_idPlantsRequest(user_id: String, limit: UsersGetLimit, offset: UsersGetLimit): Either[Result, Future[GetuserByUser_idPlantsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((user_id, limit, offset)))
            
            new UserUser_idPlantsGetValidator(user_id, limit, offset).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetuserByUser_idPlantsRequest(user_id, limit, offset) match {
                case Left(l) => success(l)
                case Right(r: Future[GetuserByUser_idPlantsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getuserByUser_idPlantsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getuserByUser_idPlantsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetusersByUser_idType[T] extends ResultWrapper[T]
    def GetusersByUser_id404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new GetusersByUser_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def GetusersByUser_id404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new GetusersByUser_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    def GetusersByUser_id200(resultP: User)(implicit writerP: String => Option[Writeable[User]]) = success(new GetusersByUser_idType[User] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetusersByUser_id200(resultF: Future[User])(implicit writerP: String => Option[Writeable[User]]) = resultF map { resultP => (new GetusersByUser_idType[User] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getusersByUser_idActionRequestType       = (String)
    private type getusersByUser_idActionType[T]            = getusersByUser_idActionRequestType => Future[GetusersByUser_idType[T] forSome { type T }]


    val getusersByUser_idActionConstructor  = Action

def getusersByUser_idAction[T] = (f: getusersByUser_idActionType[T]) => (user_id: String) => getusersByUser_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetusersByUser_idRequest(user_id: String): Either[Result, Future[GetusersByUser_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((user_id)))
            
            new UsersUser_idGetValidator(user_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetusersByUser_idRequest(user_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetusersByUser_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getusersByUser_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getusersByUser_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutusersByUser_idType[T] extends ResultWrapper[T]
    
    def PutusersByUser_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutusersByUser_id201(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(201, headers){})
    

    private type putusersByUser_idActionRequestType       = (String, User)
    private type putusersByUser_idActionType[T]            = putusersByUser_idActionRequestType => Future[PutusersByUser_idType[T] forSome { type T }]

        
        import BodyReads._
        
        val putusersByUser_idParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val putusersByUser_idActionConstructor  = Action

def putusersByUser_idAction[T] = (f: putusersByUser_idActionType[T]) => (user_id: String) => putusersByUser_idActionConstructor.async(putusersByUser_idParser) { implicit request: Request[User] =>

        def processValidputusersByUser_idRequest(user_id: String, user: User): Either[Result, Future[PutusersByUser_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((user_id, user)))
            
            new UsersUser_idPutValidator(user_id, user).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val user: User = request.body
            
            

            processValidputusersByUser_idRequest(user_id, user) match {
                case Left(l) => success(l)
                case Right(r: Future[PutusersByUser_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putusersByUser_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putusersByUser_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait DeleteusersByUser_idType[T] extends ResultWrapper[T]
    def DeleteusersByUser_id404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new DeleteusersByUser_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def DeleteusersByUser_id404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new DeleteusersByUser_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    
    def DeleteusersByUser_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type deleteusersByUser_idActionRequestType       = (String, User)
    private type deleteusersByUser_idActionType[T]            = deleteusersByUser_idActionRequestType => Future[DeleteusersByUser_idType[T] forSome { type T }]

        
        import BodyReads._
        
        val deleteusersByUser_idParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val deleteusersByUser_idActionConstructor  = Action

def deleteusersByUser_idAction[T] = (f: deleteusersByUser_idActionType[T]) => (user_id: String) => deleteusersByUser_idActionConstructor.async(deleteusersByUser_idParser) { implicit request: Request[User] =>

        def processValiddeleteusersByUser_idRequest(user_id: String, user: User): Either[Result, Future[DeleteusersByUser_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((user_id, user)))
            
            new UsersUser_idDeleteValidator(user_id, user).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val user: User = request.body
            
            

            processValiddeleteusersByUser_idRequest(user_id, user) match {
                case Left(l) => success(l)
                case Right(r: Future[DeleteusersByUser_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deleteusersByUser_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deleteusersByUser_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetareasType[T] extends ResultWrapper[T]
    def Getareas200(resultP: Seq[Area])(implicit writerP: String => Option[Writeable[Seq[Area]]]) = success(new GetareasType[Seq[Area]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def Getareas200(resultF: Future[Seq[Area]])(implicit writerP: String => Option[Writeable[Seq[Area]]]) = resultF map { resultP => (new GetareasType[Seq[Area]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getareasActionRequestType       = (UsersGetLimit, UsersGetLimit)
    private type getareasActionType[T]            = getareasActionRequestType => Future[GetareasType[T] forSome { type T }]


    val getareasActionConstructor  = Action

def getareasAction[T] = (f: getareasActionType[T]) => (limit: UsersGetLimit, offset: UsersGetLimit) => getareasActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetareasRequest(limit: UsersGetLimit, offset: UsersGetLimit): Either[Result, Future[GetareasType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((limit, offset)))
            
            new AreasGetValidator(limit, offset).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetareasRequest(limit, offset) match {
                case Left(l) => success(l)
                case Right(r: Future[GetareasType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getareasResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getareasResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetplantsByPlant_idLocationType[T] extends ResultWrapper[T]
    def GetplantsByPlant_idLocation200(resultP: Location)(implicit writerP: String => Option[Writeable[Location]]) = success(new GetplantsByPlant_idLocationType[Location] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetplantsByPlant_idLocation200(resultF: Future[Location])(implicit writerP: String => Option[Writeable[Location]]) = resultF map { resultP => (new GetplantsByPlant_idLocationType[Location] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def GetplantsByPlant_idLocation404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type getplantsByPlant_idLocationActionRequestType       = (String)
    private type getplantsByPlant_idLocationActionType[T]            = getplantsByPlant_idLocationActionRequestType => Future[GetplantsByPlant_idLocationType[T] forSome { type T }]


    val getplantsByPlant_idLocationActionConstructor  = Action

def getplantsByPlant_idLocationAction[T] = (f: getplantsByPlant_idLocationActionType[T]) => (plant_id: String) => getplantsByPlant_idLocationActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsByPlant_idLocationRequest(plant_id: String): Either[Result, Future[GetplantsByPlant_idLocationType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id)))
            
            new PlantsPlant_idLocationGetValidator(plant_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsByPlant_idLocationRequest(plant_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsByPlant_idLocationType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsByPlant_idLocationResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsByPlant_idLocationResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutplantsByPlant_idLocationType[T] extends ResultWrapper[T]
    
    def PutplantsByPlant_idLocation200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutplantsByPlant_idLocation404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type putplantsByPlant_idLocationActionRequestType       = (String, Location)
    private type putplantsByPlant_idLocationActionType[T]            = putplantsByPlant_idLocationActionRequestType => Future[PutplantsByPlant_idLocationType[T] forSome { type T }]

        
        import BodyReads._
        
        val putplantsByPlant_idLocationParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val putplantsByPlant_idLocationActionConstructor  = Action

def putplantsByPlant_idLocationAction[T] = (f: putplantsByPlant_idLocationActionType[T]) => (plant_id: String) => putplantsByPlant_idLocationActionConstructor.async(putplantsByPlant_idLocationParser) { implicit request: Request[Location] =>

        def processValidputplantsByPlant_idLocationRequest(plant_id: String, location: Location): Either[Result, Future[PutplantsByPlant_idLocationType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, location)))
            
            new PlantsPlant_idLocationPutValidator(plant_id, location).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val location: Location = request.body
            
            

            processValidputplantsByPlant_idLocationRequest(plant_id, location) match {
                case Left(l) => success(l)
                case Right(r: Future[PutplantsByPlant_idLocationType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putplantsByPlant_idLocationResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putplantsByPlant_idLocationResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait DeleteplantsByPlant_idLocationType[T] extends ResultWrapper[T]
    
    def DeleteplantsByPlant_idLocation200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def DeleteplantsByPlant_idLocation404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type deleteplantsByPlant_idLocationActionRequestType       = (String)
    private type deleteplantsByPlant_idLocationActionType[T]            = deleteplantsByPlant_idLocationActionRequestType => Future[DeleteplantsByPlant_idLocationType[T] forSome { type T }]


    val deleteplantsByPlant_idLocationActionConstructor  = Action

def deleteplantsByPlant_idLocationAction[T] = (f: deleteplantsByPlant_idLocationActionType[T]) => (plant_id: String) => deleteplantsByPlant_idLocationActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeleteplantsByPlant_idLocationRequest(plant_id: String): Either[Result, Future[DeleteplantsByPlant_idLocationType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id)))
            
            new PlantsPlant_idLocationDeleteValidator(plant_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValiddeleteplantsByPlant_idLocationRequest(plant_id) match {
                case Left(l) => success(l)
                case Right(r: Future[DeleteplantsByPlant_idLocationType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deleteplantsByPlant_idLocationResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deleteplantsByPlant_idLocationResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetusersByUser_idPictureType[T] extends ResultWrapper[T]
    def GetusersByUser_idPicture404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new GetusersByUser_idPictureType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def GetusersByUser_idPicture404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new GetusersByUser_idPictureType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    
    def GetusersByUser_idPicture200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type getusersByUser_idPictureActionRequestType       = (String)
    private type getusersByUser_idPictureActionType[T]            = getusersByUser_idPictureActionRequestType => Future[GetusersByUser_idPictureType[T] forSome { type T }]


    val getusersByUser_idPictureActionConstructor  = Action

def getusersByUser_idPictureAction[T] = (f: getusersByUser_idPictureActionType[T]) => (user_id: String) => getusersByUser_idPictureActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetusersByUser_idPictureRequest(user_id: String): Either[Result, Future[GetusersByUser_idPictureType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((user_id)))
            
            new UsersUser_idPictureGetValidator(user_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetusersByUser_idPictureRequest(user_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetusersByUser_idPictureType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getusersByUser_idPictureResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getusersByUser_idPictureResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutusersByUser_idPictureType[T] extends ResultWrapper[T]
    def PutusersByUser_idPicture404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new PutusersByUser_idPictureType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def PutusersByUser_idPicture404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new PutusersByUser_idPictureType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    
    def PutusersByUser_idPicture200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutusersByUser_idPicture201(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(201, headers){})
    

    private type putusersByUser_idPictureActionRequestType       = (String)
    private type putusersByUser_idPictureActionType[T]            = putusersByUser_idPictureActionRequestType => Future[PutusersByUser_idPictureType[T] forSome { type T }]


    val putusersByUser_idPictureActionConstructor  = Action

def putusersByUser_idPictureAction[T] = (f: putusersByUser_idPictureActionType[T]) => (user_id: String) => putusersByUser_idPictureActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidputusersByUser_idPictureRequest(user_id: String): Either[Result, Future[PutusersByUser_idPictureType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((user_id)))
            
            new UsersUser_idPicturePutValidator(user_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidputusersByUser_idPictureRequest(user_id) match {
                case Left(l) => success(l)
                case Right(r: Future[PutusersByUser_idPictureType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putusersByUser_idPictureResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putusersByUser_idPictureResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait DeleteusersByUser_idPictureType[T] extends ResultWrapper[T]
    def DeleteusersByUser_idPicture404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new DeleteusersByUser_idPictureType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def DeleteusersByUser_idPicture404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new DeleteusersByUser_idPictureType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    
    def DeleteusersByUser_idPicture200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type deleteusersByUser_idPictureActionRequestType       = (String)
    private type deleteusersByUser_idPictureActionType[T]            = deleteusersByUser_idPictureActionRequestType => Future[DeleteusersByUser_idPictureType[T] forSome { type T }]


    val deleteusersByUser_idPictureActionConstructor  = Action

def deleteusersByUser_idPictureAction[T] = (f: deleteusersByUser_idPictureActionType[T]) => (user_id: String) => deleteusersByUser_idPictureActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeleteusersByUser_idPictureRequest(user_id: String): Either[Result, Future[DeleteusersByUser_idPictureType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((user_id)))
            
            new UsersUser_idPictureDeleteValidator(user_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValiddeleteusersByUser_idPictureRequest(user_id) match {
                case Left(l) => success(l)
                case Right(r: Future[DeleteusersByUser_idPictureType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deleteusersByUser_idPictureResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deleteusersByUser_idPictureResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetplantsByPlant_idPicturesType[T] extends ResultWrapper[T]
    def GetplantsByPlant_idPictures200(resultP: Seq[String])(implicit writerP: String => Option[Writeable[Seq[String]]]) = success(new GetplantsByPlant_idPicturesType[Seq[String]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetplantsByPlant_idPictures200(resultF: Future[Seq[String]])(implicit writerP: String => Option[Writeable[Seq[String]]]) = resultF map { resultP => (new GetplantsByPlant_idPicturesType[Seq[String]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def GetplantsByPlant_idPictures404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type getplantsByPlant_idPicturesActionRequestType       = (String, UsersGetLimit, UsersGetLimit)
    private type getplantsByPlant_idPicturesActionType[T]            = getplantsByPlant_idPicturesActionRequestType => Future[GetplantsByPlant_idPicturesType[T] forSome { type T }]


    val getplantsByPlant_idPicturesActionConstructor  = Action

def getplantsByPlant_idPicturesAction[T] = (f: getplantsByPlant_idPicturesActionType[T]) => (plant_id: String, limit: UsersGetLimit, offset: UsersGetLimit) => getplantsByPlant_idPicturesActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsByPlant_idPicturesRequest(plant_id: String, limit: UsersGetLimit, offset: UsersGetLimit): Either[Result, Future[GetplantsByPlant_idPicturesType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, limit, offset)))
            
            new PlantsPlant_idPicturesGetValidator(plant_id, limit, offset).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsByPlant_idPicturesRequest(plant_id, limit, offset) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsByPlant_idPicturesType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsByPlant_idPicturesResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsByPlant_idPicturesResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetplantsByPlant_idType[T] extends ResultWrapper[T]
    def GetplantsByPlant_id404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new GetplantsByPlant_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def GetplantsByPlant_id404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new GetplantsByPlant_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    def GetplantsByPlant_id200(resultP: Plant)(implicit writerP: String => Option[Writeable[Plant]]) = success(new GetplantsByPlant_idType[Plant] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetplantsByPlant_id200(resultF: Future[Plant])(implicit writerP: String => Option[Writeable[Plant]]) = resultF map { resultP => (new GetplantsByPlant_idType[Plant] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getplantsByPlant_idActionRequestType       = (String)
    private type getplantsByPlant_idActionType[T]            = getplantsByPlant_idActionRequestType => Future[GetplantsByPlant_idType[T] forSome { type T }]


    val getplantsByPlant_idActionConstructor  = Action

def getplantsByPlant_idAction[T] = (f: getplantsByPlant_idActionType[T]) => (plant_id: String) => getplantsByPlant_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsByPlant_idRequest(plant_id: String): Either[Result, Future[GetplantsByPlant_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id)))
            
            new PlantsPlant_idGetValidator(plant_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsByPlant_idRequest(plant_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsByPlant_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsByPlant_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsByPlant_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutplantsByPlant_idType[T] extends ResultWrapper[T]
    
    def PutplantsByPlant_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutplantsByPlant_id201(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(201, headers){})
    
    def PutplantsByPlant_id404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type putplantsByPlant_idActionRequestType       = (String, Plant)
    private type putplantsByPlant_idActionType[T]            = putplantsByPlant_idActionRequestType => Future[PutplantsByPlant_idType[T] forSome { type T }]

        
        import BodyReads._
        
        val putplantsByPlant_idParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val putplantsByPlant_idActionConstructor  = Action

def putplantsByPlant_idAction[T] = (f: putplantsByPlant_idActionType[T]) => (plant_id: String) => putplantsByPlant_idActionConstructor.async(putplantsByPlant_idParser) { implicit request: Request[Plant] =>

        def processValidputplantsByPlant_idRequest(plant_id: String, plant: Plant): Either[Result, Future[PutplantsByPlant_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, plant)))
            
            new PlantsPlant_idPutValidator(plant_id, plant).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val plant: Plant = request.body
            
            

            processValidputplantsByPlant_idRequest(plant_id, plant) match {
                case Left(l) => success(l)
                case Right(r: Future[PutplantsByPlant_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putplantsByPlant_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putplantsByPlant_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait DeleteplantsByPlant_idType[T] extends ResultWrapper[T]
    def DeleteplantsByPlant_id404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new DeleteplantsByPlant_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def DeleteplantsByPlant_id404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new DeleteplantsByPlant_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    
    def DeleteplantsByPlant_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type deleteplantsByPlant_idActionRequestType       = (String)
    private type deleteplantsByPlant_idActionType[T]            = deleteplantsByPlant_idActionRequestType => Future[DeleteplantsByPlant_idType[T] forSome { type T }]


    val deleteplantsByPlant_idActionConstructor  = Action

def deleteplantsByPlant_idAction[T] = (f: deleteplantsByPlant_idActionType[T]) => (plant_id: String) => deleteplantsByPlant_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeleteplantsByPlant_idRequest(plant_id: String): Either[Result, Future[DeleteplantsByPlant_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id)))
            
            new PlantsPlant_idDeleteValidator(plant_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValiddeleteplantsByPlant_idRequest(plant_id) match {
                case Left(l) => success(l)
                case Right(r: Future[DeleteplantsByPlant_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deleteplantsByPlant_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deleteplantsByPlant_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetplantsByPlant_idWateringsType[T] extends ResultWrapper[T]
    def GetplantsByPlant_idWaterings200(resultP: Seq[Watering])(implicit writerP: String => Option[Writeable[Seq[Watering]]]) = success(new GetplantsByPlant_idWateringsType[Seq[Watering]] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetplantsByPlant_idWaterings200(resultF: Future[Seq[Watering]])(implicit writerP: String => Option[Writeable[Seq[Watering]]]) = resultF map { resultP => (new GetplantsByPlant_idWateringsType[Seq[Watering]] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    
    def GetplantsByPlant_idWaterings404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type getplantsByPlant_idWateringsActionRequestType       = (String, UsersGetLimit, UsersGetLimit)
    private type getplantsByPlant_idWateringsActionType[T]            = getplantsByPlant_idWateringsActionRequestType => Future[GetplantsByPlant_idWateringsType[T] forSome { type T }]


    val getplantsByPlant_idWateringsActionConstructor  = Action

def getplantsByPlant_idWateringsAction[T] = (f: getplantsByPlant_idWateringsActionType[T]) => (plant_id: String, limit: UsersGetLimit, offset: UsersGetLimit) => getplantsByPlant_idWateringsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsByPlant_idWateringsRequest(plant_id: String, limit: UsersGetLimit, offset: UsersGetLimit): Either[Result, Future[GetplantsByPlant_idWateringsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, limit, offset)))
            
            new PlantsPlant_idWateringsGetValidator(plant_id, limit, offset).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsByPlant_idWateringsRequest(plant_id, limit, offset) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsByPlant_idWateringsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsByPlant_idWateringsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsByPlant_idWateringsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetplantsByPlant_idPicturesByPicture_idType[T] extends ResultWrapper[T]
    def GetplantsByPlant_idPicturesByPicture_id404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new GetplantsByPlant_idPicturesByPicture_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def GetplantsByPlant_idPicturesByPicture_id404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new GetplantsByPlant_idPicturesByPicture_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    
    def GetplantsByPlant_idPicturesByPicture_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type getplantsByPlant_idPicturesByPicture_idActionRequestType       = (String, String)
    private type getplantsByPlant_idPicturesByPicture_idActionType[T]            = getplantsByPlant_idPicturesByPicture_idActionRequestType => Future[GetplantsByPlant_idPicturesByPicture_idType[T] forSome { type T }]


    val getplantsByPlant_idPicturesByPicture_idActionConstructor  = Action

def getplantsByPlant_idPicturesByPicture_idAction[T] = (f: getplantsByPlant_idPicturesByPicture_idActionType[T]) => (plant_id: String, picture_id: String) => getplantsByPlant_idPicturesByPicture_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsByPlant_idPicturesByPicture_idRequest(plant_id: String, picture_id: String): Either[Result, Future[GetplantsByPlant_idPicturesByPicture_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, picture_id)))
            
            new PlantsPlant_idPicturesPicture_idGetValidator(plant_id, picture_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsByPlant_idPicturesByPicture_idRequest(plant_id, picture_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsByPlant_idPicturesByPicture_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsByPlant_idPicturesByPicture_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsByPlant_idPicturesByPicture_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutplantsByPlant_idPicturesByPicture_idType[T] extends ResultWrapper[T]
    
    def PutplantsByPlant_idPicturesByPicture_id404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    
    def PutplantsByPlant_idPicturesByPicture_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutplantsByPlant_idPicturesByPicture_id201(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(201, headers){})
    

    private type putplantsByPlant_idPicturesByPicture_idActionRequestType       = (String, String)
    private type putplantsByPlant_idPicturesByPicture_idActionType[T]            = putplantsByPlant_idPicturesByPicture_idActionRequestType => Future[PutplantsByPlant_idPicturesByPicture_idType[T] forSome { type T }]


    val putplantsByPlant_idPicturesByPicture_idActionConstructor  = Action

def putplantsByPlant_idPicturesByPicture_idAction[T] = (f: putplantsByPlant_idPicturesByPicture_idActionType[T]) => (plant_id: String, picture_id: String) => putplantsByPlant_idPicturesByPicture_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidputplantsByPlant_idPicturesByPicture_idRequest(plant_id: String, picture_id: String): Either[Result, Future[PutplantsByPlant_idPicturesByPicture_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, picture_id)))
            
            new PlantsPlant_idPicturesPicture_idPutValidator(plant_id, picture_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidputplantsByPlant_idPicturesByPicture_idRequest(plant_id, picture_id) match {
                case Left(l) => success(l)
                case Right(r: Future[PutplantsByPlant_idPicturesByPicture_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putplantsByPlant_idPicturesByPicture_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putplantsByPlant_idPicturesByPicture_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait DeleteplantsByPlant_idPicturesByPicture_idType[T] extends ResultWrapper[T]
    def DeleteplantsByPlant_idPicturesByPicture_id404(resultP: Error)(implicit writerP: String => Option[Writeable[Error]]) = success(new DeleteplantsByPlant_idPicturesByPicture_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP })
    def DeleteplantsByPlant_idPicturesByPicture_id404(resultF: Future[Error])(implicit writerP: String => Option[Writeable[Error]]) = resultF map { resultP => (new DeleteplantsByPlant_idPicturesByPicture_idType[Error] { val statusCode = 404; val result = resultP; val writer = writerP }) }
    
    def DeleteplantsByPlant_idPicturesByPicture_id200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    

    private type deleteplantsByPlant_idPicturesByPicture_idActionRequestType       = (String, String)
    private type deleteplantsByPlant_idPicturesByPicture_idActionType[T]            = deleteplantsByPlant_idPicturesByPicture_idActionRequestType => Future[DeleteplantsByPlant_idPicturesByPicture_idType[T] forSome { type T }]


    val deleteplantsByPlant_idPicturesByPicture_idActionConstructor  = Action

def deleteplantsByPlant_idPicturesByPicture_idAction[T] = (f: deleteplantsByPlant_idPicturesByPicture_idActionType[T]) => (plant_id: String, picture_id: String) => deleteplantsByPlant_idPicturesByPicture_idActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValiddeleteplantsByPlant_idPicturesByPicture_idRequest(plant_id: String, picture_id: String): Either[Result, Future[DeleteplantsByPlant_idPicturesByPicture_idType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, picture_id)))
            
            new PlantsPlant_idPicturesPicture_idDeleteValidator(plant_id, picture_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValiddeleteplantsByPlant_idPicturesByPicture_idRequest(plant_id, picture_id) match {
                case Left(l) => success(l)
                case Right(r: Future[DeleteplantsByPlant_idPicturesByPicture_idType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { deleteplantsByPlant_idPicturesByPicture_idResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(deleteplantsByPlant_idPicturesByPicture_idResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait GetplantsByPlant_idWater_needsType[T] extends ResultWrapper[T]
    def GetplantsByPlant_idWater_needs200(resultP: WaterNeeds)(implicit writerP: String => Option[Writeable[WaterNeeds]]) = success(new GetplantsByPlant_idWater_needsType[WaterNeeds] { val statusCode = 200; val result = resultP; val writer = writerP })
    def GetplantsByPlant_idWater_needs200(resultF: Future[WaterNeeds])(implicit writerP: String => Option[Writeable[WaterNeeds]]) = resultF map { resultP => (new GetplantsByPlant_idWater_needsType[WaterNeeds] { val statusCode = 200; val result = resultP; val writer = writerP }) }
    

    private type getplantsByPlant_idWater_needsActionRequestType       = (String)
    private type getplantsByPlant_idWater_needsActionType[T]            = getplantsByPlant_idWater_needsActionRequestType => Future[GetplantsByPlant_idWater_needsType[T] forSome { type T }]


    val getplantsByPlant_idWater_needsActionConstructor  = Action

def getplantsByPlant_idWater_needsAction[T] = (f: getplantsByPlant_idWater_needsActionType[T]) => (plant_id: String) => getplantsByPlant_idWater_needsActionConstructor.async { implicit request: Request[AnyContent] =>

        def processValidgetplantsByPlant_idWater_needsRequest(plant_id: String): Either[Result, Future[GetplantsByPlant_idWater_needsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id)))
            
            new PlantsPlant_idWater_needsGetValidator(plant_id).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            
            

            processValidgetplantsByPlant_idWater_needsRequest(plant_id) match {
                case Left(l) => success(l)
                case Right(r: Future[GetplantsByPlant_idWater_needsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { getplantsByPlant_idWater_needsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(getplantsByPlant_idWater_needsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    sealed trait PutplantsByPlant_idWater_needsType[T] extends ResultWrapper[T]
    
    def PutplantsByPlant_idWater_needs200(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(200, headers){})
    
    def PutplantsByPlant_idWater_needs404(headers: Seq[(String, String)] = Nil) = success(new EmptyReturn(404, headers){})
    

    private type putplantsByPlant_idWater_needsActionRequestType       = (String, WaterNeeds)
    private type putplantsByPlant_idWater_needsActionType[T]            = putplantsByPlant_idWater_needsActionRequestType => Future[PutplantsByPlant_idWater_needsType[T] forSome { type T }]

        
        import BodyReads._
        
        val putplantsByPlant_idWater_needsParser = parse.using { request =>
            request.contentType.map(_.toLowerCase(java.util.Locale.ENGLISH)) match {
                
                case other => play.api.mvc.BodyParsers.parse.error(Future.successful(UnsupportedMediaType(s"Invalid content type specified $other")))
            }
        }

    val putplantsByPlant_idWater_needsActionConstructor  = Action

def putplantsByPlant_idWater_needsAction[T] = (f: putplantsByPlant_idWater_needsActionType[T]) => (plant_id: String) => putplantsByPlant_idWater_needsActionConstructor.async(putplantsByPlant_idWater_needsParser) { implicit request: Request[WaterNeeds] =>

        def processValidputplantsByPlant_idWater_needsRequest(plant_id: String, water_needs: WaterNeeds): Either[Result, Future[PutplantsByPlant_idWater_needsType[_]]] = {
          lazy val apiFirstTempResultHolder = Right(f((plant_id, water_needs)))
            
            new PlantsPlant_idWater_needsPutValidator(plant_id, water_needs).errors match {
                case e if e.isEmpty =>
                    apiFirstTempResultHolder
                case l =>
                    import ResponseWriters.jsonParsingErrorsWrites
                    Left(BadRequest(Json.toJson(l)))
            }
            
          
        }

            val water_needs: WaterNeeds = request.body
            
            

            processValidputplantsByPlant_idWater_needsRequest(plant_id, water_needs) match {
                case Left(l) => success(l)
                case Right(r: Future[PutplantsByPlant_idWater_needsType[_] @unchecked]) =>
                    val providedTypes = Seq[String]("application/json")
                    val result = negotiateContent(request.acceptedTypes, providedTypes) map { putplantsByPlant_idWater_needsResponseMimeType =>
                        import ResponseWrites._
                        r.map(_.toResult(putplantsByPlant_idWater_needsResponseMimeType).getOrElse(Results.NotAcceptable))
                    }
                    result getOrElse notAcceptable
            }
            
    }

    abstract class EmptyReturn(override val statusCode: Int, headers: Seq[(String, String)]) extends ResultWrapper[Result]  with GetplantsByPlant_idWateringsByWatering_idType[Result] with PutplantsByPlant_idWateringsByWatering_idType[Result] with GetusersMeType[Result] with GetplantsByPlant_idSunlight_needsType[Result] with PutplantsByPlant_idSunlight_needsType[Result] with GetusersType[Result] with PostusersType[Result] with GetareasByArea_idType[Result] with PutareasByArea_idType[Result] with DeleteareasByArea_idType[Result] with GetplantsType[Result] with GetuserByUser_idPlantsType[Result] with GetusersByUser_idType[Result] with PutusersByUser_idType[Result] with DeleteusersByUser_idType[Result] with GetareasType[Result] with GetplantsByPlant_idLocationType[Result] with PutplantsByPlant_idLocationType[Result] with DeleteplantsByPlant_idLocationType[Result] with GetusersByUser_idPictureType[Result] with PutusersByUser_idPictureType[Result] with DeleteusersByUser_idPictureType[Result] with GetplantsByPlant_idPicturesType[Result] with GetplantsByPlant_idType[Result] with PutplantsByPlant_idType[Result] with DeleteplantsByPlant_idType[Result] with GetplantsByPlant_idWateringsType[Result] with GetplantsByPlant_idPicturesByPicture_idType[Result] with PutplantsByPlant_idPicturesByPicture_idType[Result] with DeleteplantsByPlant_idPicturesByPicture_idType[Result] with GetplantsByPlant_idWater_needsType[Result] with PutplantsByPlant_idWater_needsType[Result] { val result = Results.Status(statusCode).withHeaders(headers:_*); val writer = (x: String) => Some(new Writeable((_:Any) => emptyByteString, None)); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(result) }
    case object NotImplementedYetSync extends ResultWrapper[Results.EmptyContent]  with GetplantsByPlant_idWateringsByWatering_idType[Results.EmptyContent] with PutplantsByPlant_idWateringsByWatering_idType[Results.EmptyContent] with GetusersMeType[Results.EmptyContent] with GetplantsByPlant_idSunlight_needsType[Results.EmptyContent] with PutplantsByPlant_idSunlight_needsType[Results.EmptyContent] with GetusersType[Results.EmptyContent] with PostusersType[Results.EmptyContent] with GetareasByArea_idType[Results.EmptyContent] with PutareasByArea_idType[Results.EmptyContent] with DeleteareasByArea_idType[Results.EmptyContent] with GetplantsType[Results.EmptyContent] with GetuserByUser_idPlantsType[Results.EmptyContent] with GetusersByUser_idType[Results.EmptyContent] with PutusersByUser_idType[Results.EmptyContent] with DeleteusersByUser_idType[Results.EmptyContent] with GetareasType[Results.EmptyContent] with GetplantsByPlant_idLocationType[Results.EmptyContent] with PutplantsByPlant_idLocationType[Results.EmptyContent] with DeleteplantsByPlant_idLocationType[Results.EmptyContent] with GetusersByUser_idPictureType[Results.EmptyContent] with PutusersByUser_idPictureType[Results.EmptyContent] with DeleteusersByUser_idPictureType[Results.EmptyContent] with GetplantsByPlant_idPicturesType[Results.EmptyContent] with GetplantsByPlant_idType[Results.EmptyContent] with PutplantsByPlant_idType[Results.EmptyContent] with DeleteplantsByPlant_idType[Results.EmptyContent] with GetplantsByPlant_idWateringsType[Results.EmptyContent] with GetplantsByPlant_idPicturesByPicture_idType[Results.EmptyContent] with PutplantsByPlant_idPicturesByPicture_idType[Results.EmptyContent] with DeleteplantsByPlant_idPicturesByPicture_idType[Results.EmptyContent] with GetplantsByPlant_idWater_needsType[Results.EmptyContent] with PutplantsByPlant_idWater_needsType[Results.EmptyContent] { val statusCode = 501; val result = Results.EmptyContent(); val writer = (x: String) => Some(new DefaultWriteables{}.writeableOf_EmptyContent); override def toResult(mimeType: String): Option[play.api.mvc.Result] = Some(Results.NotImplemented) }
    lazy val NotImplementedYet = Future.successful(NotImplementedYetSync)
}
