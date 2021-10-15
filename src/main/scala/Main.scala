import com.google.protobuf.util.JsonFormat
import common.AppConfiguration
import scalapb.zio_grpc.{ServerMain, ServiceList}
import services._
import zhttp.http._
import zhttp.service._
import zio._
import zio.console._

import scala.io.Source

//object Main extends App {
//  override def run(args: List[String]): URIO[ZEnv, ExitCode] = {
//    Server.start(8090, routes)
//      .provideCustomLayer(AppEnv)
//      .exitCode
//  }
//
//  val AppEnv =
//    (Console.live >>> Logger.live) ++
//      (Logger.live >>> EmailSender.live) ++
//      AppConfiguration.live
//
//  val otherRoutes = Http.collectM[Request] {
//    case Method.GET -> Root / "b"  => ZIO.succeed(Response.ok)
//  }
//
//  val routes =
//    features.user.HttpRoutes.routes +++
//      otherRoutes
//}


object RouteGuideServer extends ServerMain {
  override def port: Int = 8980

  val helloWorldService = new HelloWorldService()

  def services: ServiceList[zio.ZEnv] =
    ServiceList.add(helloWorldService)
}