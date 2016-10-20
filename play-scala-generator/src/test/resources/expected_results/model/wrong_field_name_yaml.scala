package wrong_field_name



    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type GetOptCodes = Option[GetOptCodesOpt]

    object GetCodes {
        
        val Get = new GetCodes("Get")
        val GET = new GetCodes("GET")
        val Get = new GetCodes("get")

        implicit def stringToGetCodes: String => GetCodes = {
            case "Get" => Get
            case "GET" => GET
            case "get" => Get
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }
    object GetOptCodesOpt {
        
        val Put = new GetOptCodesOpt("put")
        val PUT = new GetOptCodesOpt("PUT")
        val Put = new GetOptCodesOpt("Put")

        implicit def stringToGetOptCodesOpt: String => GetOptCodesOpt = {
            case "put" => Put
            case "PUT" => PUT
            case "Put" => Put
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }
    object Status {
        
        val OK = new Status("OK")
        val WARNING = new Status("WARNING")
        val ERROR = new Status("ERROR")

        implicit def stringToStatus: String => Status = {
            case "OK" => OK
            case "WARNING" => WARNING
            case "ERROR" => ERROR
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }

import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_QueryGetOptCodesOpt: QueryStringBindable[GetOptCodesOpt] = new PlayPathBindables.createEnumQueryBindable(stringToGetOptCodesOpt)
    implicit val bindable_OptionGetOptCodesOptQuery: QueryStringBindable[Option[GetOptCodesOpt]] = PlayPathBindables.createOptionQueryBindable[GetOptCodesOpt]
    implicit val bindable_QueryGetCodes: QueryStringBindable[GetCodes] = new PlayPathBindables.createEnumQueryBindable(stringToGetCodes)

}
//noinspection ScalaStyle
package yaml {


    case class StatusAndCode(message: String, Status: Status) 

    case class GetCodes(value: String) extends AnyVal {
        override def toString = value.toString
    }
    case class GetOptCodesOpt(value: String) extends AnyVal {
        override def toString = value.toString
    }
    case class Status(value: String) extends AnyVal {
        override def toString = value.toString
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val StatusAndCodeWrites: Writes[StatusAndCode] = new Writes[StatusAndCode] {
        def writes(ss: StatusAndCode) =
          Json.obj(
            "message" -> ss.message, 
            "Status" -> ss.Status
          )
        }
    }
}
