package enum



    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {



    case class TestGetIncludesOptionEnum(override val value: String) extends AnyVal with de.zalando.play.controllers.StringAnyVal

}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {


    object TestGetIncludesOptionEnum {
        
        val Option_one = new TestGetIncludesOptionEnum("option_one")
        val Option_two = new TestGetIncludesOptionEnum("option_two")

        implicit def stringToTestGetIncludesOptionEnum: String => TestGetIncludesOptionEnum = {
            case "option_one" => Option_one
            case "option_two" => Option_two
            case other =>
                throw new IllegalArgumentException("Couldn't parse parameter " + other)
        }
    }

import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_QueryTestGetIncludesOptionEnum: QueryStringBindable[TestGetIncludesOptionEnum] = new PlayPathBindables.createEnumQueryBindable(TestGetIncludesOptionEnum.stringToTestGetIncludesOptionEnum)
    implicit val bindable_OptionTestGetIncludesOptionEnumQuery: QueryStringBindable[Option[TestGetIncludesOptionEnum]] = PlayPathBindables.createOptionQueryBindable[TestGetIncludesOptionEnum]

}