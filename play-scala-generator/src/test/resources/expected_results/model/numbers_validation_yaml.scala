package numbers_validation



    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package object yaml {

    type GetDouble_optional = Option[Double]
    type GetInteger_optional = Option[Int]
    type GetLong_optional = Option[Long]
    type GetFloat_optional = Option[Float]
    type GetResponses200 = Null


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionDoubleQuery: QueryStringBindable[Option[Double]] = PlayPathBindables.createOptionQueryBindable[Double]
    implicit val bindable_OptionIntQuery: QueryStringBindable[Option[Int]] = PlayPathBindables.createOptionQueryBindable[Int]
    implicit val bindable_OptionLongQuery: QueryStringBindable[Option[Long]] = PlayPathBindables.createOptionQueryBindable[Long]
    implicit val bindable_OptionFloatQuery: QueryStringBindable[Option[Float]] = PlayPathBindables.createOptionQueryBindable[Float]

}
//noinspection ScalaStyle
package yaml {




}
