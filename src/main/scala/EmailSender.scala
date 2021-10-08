import zio._
import zio.console._
import java.io.IOException

class EmailSender(logger: Logger) {
  def send(message: String): ZIO[Any, IOException, Int] =
    for {
//      _ <- console.putStrLn("Hello, world")
      _ <- logger.logLine("Example from another service")
    } yield 5
}

object EmailSender {
  val live: ZLayer[Has[Logger], Nothing, Has[EmailSender]] = {
    ZLayer.fromService[Logger, EmailSender](logger =>
      new EmailSender(logger))
  }

  // accessor
  def send(message: String): ZIO[Has[EmailSender], IOException, Int] =
    ZIO.accessM(_.get.send(message))
}





//trait EmailSender {
//  def send(message: String): ZIO[Has[Logger], IOException, Int]
//}
//
//object EmailSender {
//  val live: ZLayer[Has[Logger], Nothing, Has[EmailSender]] =
//    ZLayer.succeed(
//      new EmailSender {
//        def send(message: String): ZIO[Has[Logger], IOException, Int] = {
//          for {
//            _ <- Logger.logLine("Hello")
//          } yield 5
//        }
//      }
//    )
//
//  def send(message: String): ZIO[Has[EmailSender] with Has[Logger], IOException, Int] =
//    ZIO.accessM(_.get.send(message))
//}





//trait EmailSender {
//  def send(message: String): ZIO[Has[Logger], IOException, Int]
//}
//
//object EmailSender {
//  val live: ZLayer[Has[Logger], Nothing, Has[EmailSender]] =
//    ZLayer.succeed(
//      new EmailSender {
//        def send(message: String): ZIO[Has[Logger], IOException, Int] = {
//          for {
//            _ <- Logger.logLine("Hello")
//          } yield 5
//        }
//      }
//  )
//
//  def send(message: String): ZIO[Has[EmailSender] with Has[Logger], IOException, Int] =
//    ZIO.accessM(_.get.send(message))
//}
