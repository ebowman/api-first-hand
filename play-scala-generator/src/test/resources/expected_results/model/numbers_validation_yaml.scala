package numbers_validation

package object yaml {


    import de.zalando.play.controllers.PlayPathBindables



    type GetDouble_optional = Option[Double]
    type GetInteger_required = Int
    type GetInteger_optional = Option[Int]
    type GetDouble_required = Double
    type GetLong_optional = Option[Long]
    type GetFloat_required = Float
    type GetFloat_optional = Option[Float]
    type GetLong_required = Long
    type GetResponses200 = Null




    implicit val bindable_OptionDoubleQuery = PlayPathBindables.createOptionQueryBindable[Double]

    implicit val bindable_OptionIntQuery = PlayPathBindables.createOptionQueryBindable[Int]

    implicit val bindable_OptionLongQuery = PlayPathBindables.createOptionQueryBindable[Long]

    implicit val bindable_OptionFloatQuery = PlayPathBindables.createOptionQueryBindable[Float]


}
