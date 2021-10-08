import zhttp.http._
import zhttp.service._
import zhttp.service.server.ServerChannelFactory
import zio._
import zio.console._

object Main extends App {
  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
    Logger.logLine("Example")
//  ZIO.accessM[Logger](_.logLine("Example"))
//  ZIO.access[Logger](_.logLine("Example"))
//      EmailSender.send("Example")
      .provideLayer(Console.live >>> Logger.live)
      .exitCode

  val AppEnv = (Console.live >>> Logger.live) ++ (Logger.live >>> EmailSender.live)

//  val PORT = 8090
//
//  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
//    server.make
//      .use(_ =>
//        // Waiting for the server to start
//        console.putStrLn(s"Server started on port $PORT")
//
//          // Ensures the server doesn't die after printing
//          *> ZIO.never
//      )
//      .provideCustomLayer(ServerChannelFactory.auto ++ EventLoopGroup.auto(4) ++ Logger.live)
//      .exitCode
//
//  val b = Http.collectM[Request] { case Method.GET -> Root / "b"  => ZIO.succeed(Response.ok) }
//
//  val routes = {
//    features.user.HttpRoutes.routes +++
//      b
//  }
//
//  val server =
//    Server.port(8090) ++
//      Server.app(routes)
}