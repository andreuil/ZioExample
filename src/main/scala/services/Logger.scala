package services

import zio._
import zio.console.Console

import java.io.IOException

case class Logger(
  console: Console.Service
) {
  def logLine(line: String): ZIO[Any, IOException, Unit] = { // IO[IOException, Unit]
    for {
      _ <- console.putStrLn(s"logger: $line")
    } yield ()
  }
}

object Logger {
  val live: ZLayer[Console, Nothing, Has[Logger]] =
    (new Logger(_)).toLayer
}