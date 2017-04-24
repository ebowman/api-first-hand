package numbers_validation



    import de.zalando.play.controllers.PlayPathBindables


//noinspection ScalaStyle
package yaml {




}

// should be defined after the package because of the https://issues.scala-lang.org/browse/SI-9922

//noinspection ScalaStyle
package object yaml {

    type GetResponses200 = Null


import play.api.mvc.{QueryStringBindable, PathBindable}

    implicit val bindable_OptionDoubleQuery: QueryStringBindable[Option[Double]] = PlayPathBindables.createOptionQueryBindable[Double]
    implicit val bindable_OptionIntQuery: QueryStringBindable[Option[Int]] = PlayPathBindables.createOptionQueryBindable[Int]
    implicit val bindable_OptionLongQuery: QueryStringBindable[Option[Long]] = PlayPathBindables.createOptionQueryBindable[Long]
    implicit val bindable_OptionFloatQuery: QueryStringBindable[Option[Float]] = PlayPathBindables.createOptionQueryBindable[Float]

}