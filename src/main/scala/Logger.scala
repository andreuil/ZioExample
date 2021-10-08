import zio._
import zio.console.Console
import java.io.IOException

class Logger(
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
    ZLayer.fromService[Console.Service, Logger](console =>
      new Logger(console))

  // accessor
  def logLine(message: String): ZIO[Has[Logger], IOException, Unit] =
    ZIO.accessM(_.get.logLine(message))
}

//// Interface definition
//trait Logger {
//  def logLine(line: String): UIO[Unit]
//}
//
//object Logger {
//  // Interface implementation
//  val live: ZLayer[Any, Nothing, Has[Logger]] =
//    ZLayer.succeed {
//      new Logger {
//        def logLine(line: String): UIO[Unit] =
//          UIO.succeed(println(line))
//      }
//    }
//
//  // Accessor methods
//  def logLine(line: String): URIO[Has[Logger], Unit] =
//    ZIO.accessM(_.get.logLine(line))
//}

//class Logger {
//  def logLine(line: String): UIO[Unit] =
//    UIO.succeed(println(line))
//}
//
//object Logger {
//  val live: ZLayer[Any, Nothing, Has[Logger]] =
//    ZLayer.succeed(new Logger())
//}