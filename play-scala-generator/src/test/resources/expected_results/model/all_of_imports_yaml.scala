package all_of_imports


    import java.time.ZonedDateTime


//noinspection ScalaStyle
package yaml {

    trait IValue {
        def `type`: String
    }

    case class Value(`type`: String) extends IValue
    case class DatetimeValue(`type`: String, value: ZonedDateTime) extends IValue


    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultReads
    object BodyReads extends MissingDefaultReads {
        implicit val DatetimeValueReads: Reads[DatetimeValue] = (
            (JsPath \ "`type`").read[String] and (JsPath \ "value").read[ZonedDateTime]
        )(DatetimeValue.apply _)
        implicit val ValueReads: Reads[Value] = (
            (JsPath \ "`type`").read[String]
        ).map(Value.apply )
    }

    import play.api.libs.json._
    import play.api.libs.functional.syntax._
    import de.zalando.play.controllers.MissingDefaultWrites
    object ResponseWrites extends MissingDefaultWrites {
    implicit val DatetimeValueWrites: Writes[DatetimeValue] = new Writes[DatetimeValue] {
        def writes(ss: DatetimeValue) =
          Json.obj(
            "`type`" -> ss.`type`, 
            "value" -> ss.value
          )
        }
    implicit val ValueWrites: Writes[Value] = new Writes[Value] {
        def writes(ss: Value) =
          Json.obj(
            "`type`" -> ss.`type`
          )
        }
    }
}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type PostResponses400 = Null



}