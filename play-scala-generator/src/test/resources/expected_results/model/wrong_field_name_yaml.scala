package wrong_field_name



    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type GetOptCodes = Option[GetOptCodesOpt]

    
    val Get = GetCodes("Get")
    val GET = GetCodes("GET")
    val Get = GetCodes("get")

    implicit def stringToGetCodes: String => GetCodes = {
        case "Get" => Get
        case "GET" => GET
        case "get" => Get
        case other =>
            throw new IllegalArgumentException("Couldn't parse parameter " + other)
    }
    
    val Put = GetOptCodesOpt("put")
    val PUT = GetOptCodesOpt("PUT")
    val Put = GetOptCodesOpt("Put")

    implicit def stringToGetOptCodesOpt: String => GetOptCodesOpt = {
        case "put" => Put
        case "PUT" => PUT
        case "Put" => Put
        case other =>
            throw new IllegalArgumentException("Couldn't parse parameter " + other)
    }
    
    val OK = Status("OK")
    val WARNING = Status("WARNING")
    val ERROR = Status("ERROR")

    implicit def stringToStatus: String => Status = {
        case "OK" => OK
        case "WARNING" => WARNING
        case "ERROR" => ERROR
        case other =>
            throw new IllegalArgumentException("Couldn't parse parameter " + other)
    }

import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_QueryGetOptCodesOpt: QueryStringBindable[GetOptCodesOpt] = new PlayPathBindables.createEnumQueryBindable(stringToGetOptCodesOpt)
    implicit val bindable_OptionGetOptCodesOptQuery: QueryStringBindable[Option[GetOptCodesOpt]] = PlayPathBindables.createOptionQueryBindable[GetOptCodesOpt]
    implicit val bindable_QueryGetCodes: QueryStringBindable[GetCodes] = new PlayPathBindables.createEnumQueryBindable(stringToGetCodes)

}
//noinspection ScalaStyle
package yaml {


    case class StatusAndCode(message: String, code: Status) 

    case class GetCodes(value: String) extends AnyVal {
        override def toString = value.toString
    }
    case class GetOptCodesOpt(value: String) extends AnyVal {
        override def toString = value.toString
    }
    case class Status(value: String) extends AnyVal {
        override def toString = value.toString
    }

}
