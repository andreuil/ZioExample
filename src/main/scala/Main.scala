import services._
import zhttp.http._
import zhttp.service._
import zhttp.service.server.ServerChannelFactory
import zio._
import zio.console._
import zio.config._

object Main extends App {
  override def run(args: List[String]): URIO[ZEnv, ExitCode] = {
    Server.start(8090, routes)
      .provideCustomLayer(AppEnv)
      .exitCode
  }

  val AppEnv = (Console.live >>> Logger.live) ++ (Logger.live >>> EmailSender.live)

  val otherRoutes = Http.collectM[Request] {
    case Method.GET -> Root / "b"  => ZIO.succeed(Response.ok)
  }

  val routes =
    features.user.HttpRoutes.routes +++
      otherRoutes
}