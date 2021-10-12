import services._
import zhttp.http._
import zhttp.service._
import zhttp.service.server.ServerChannelFactory
import zio._
import zio.console._
import zio.config._
import zio.config.magnolia.descriptor

import java.io.File
import java.nio.file.Paths

object Main extends App {
  override def run(args: List[String]): URIO[ZEnv, ExitCode] = {
    Server.start(8090, routes)
      .provideCustomLayer(AppEnv)
      .exitCode
  }

//  val currentPath = getClass.getResource("").getPath
//  val appConfigurationPath = currentPath + File.separatorChar + "AppConfiguration.json"
//  println(s"Configuration path: $appConfigurationPath")
//  val appConfigurationDescriptor = descriptor[AppConfiguration]
//  val appConfiguration = ZConfig.fromPropertiesFile(appConfigurationPath, appConfigurationDescriptor)

  val AppEnv = (Console.live >>> Logger.live) ++ (Logger.live >>> EmailSender.live) //++ appConfiguration

  val otherRoutes = Http.collectM[Request] {
    case Method.GET -> Root / "b"  => ZIO.succeed(Response.ok)
  }

  val routes =
    features.user.HttpRoutes.routes +++
      otherRoutes
}