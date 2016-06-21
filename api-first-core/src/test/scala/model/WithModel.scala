package de.zalando.model

import de.zalando.apifirst.Application.StrictModel

/**
  * @since 26.05.2016.
  */
trait WithModel {
  def model: StrictModel
}
