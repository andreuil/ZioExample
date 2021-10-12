package services

import zio._
import java.io.IOException

case class EmailSender(logger: Logger) {
  def send(message: String): ZIO[Any, IOException, Int] =
    for {
      _ <- logger.logLine(message)
    } yield 5
}

object EmailSender {
  val live: ZLayer[Has[Logger], Nothing, Has[EmailSender]] =
    (new EmailSender(_)).toLayer
}