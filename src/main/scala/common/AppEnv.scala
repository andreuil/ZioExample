package common

import services.{EmailSender, Logger}
import zio.console.Console

object AppEnv {
  type AppEnv =
    Console
    with Logger
    with EmailSender
    with AppConfiguration

  val injection =
    (Console.live >>> Logger.live) ++
      (Logger.live >>> EmailSender.live) ++
      AppConfiguration.live
}