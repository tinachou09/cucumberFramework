package util

import com.typesafe.config.{Config, ConfigFactory}

object Configuration {

  val config: Config = ConfigFactory.load()

  val browserType: String = config.getString("browserType")

}
