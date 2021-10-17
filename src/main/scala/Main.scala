//import com.google.protobuf.util.JsonFormat
import common._
import common.AppEnv.AppEnv
//import io.grpc.ServerBuilder
//import io.grpc.protobuf.services.ProtoReflectionService
//import scalapb.zio_grpc.{Server, ServerLayer, ServerMain, ServiceList}
import services._
import zhttp.http._
import zhttp.service._
import zio._
import zio.clock.{Clock, currentDateTime}
import zio.console._
import zio.random.{Random, nextInt}
import scala.io.Source

object Main extends App {
  override def run(args: List[String]): URIO[ZEnv, ExitCode] = {
    Server.start(8090, routes)
      .provideCustomLayer(AppEnv.injection)
      .exitCode
  }
//
//  val AppEnv =
//    (Console.live >>> Logger.live) ++
//      (Logger.live >>> EmailSender.live) ++
//      AppConfiguration.live

  val otherRoutes = Http.collectM[Request] {
    case Method.GET -> Root / "b"  => ZIO.succeed(Response.ok)
  }

  val routes =
    features.product.HttpRoutes.routes +++
      otherRoutes
}


//object RouteGuideServer extends ServerMain {
//  override def port: Int = 8980
//
//  val helloWorldService = new HelloWorldService()
//
//  def services: ServiceList[zio.ZEnv] =
//    ServiceList.add(helloWorldService)
//}

//object Main extends App {
//  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
//    app
////      .provideSomeLayer[ZEnv](AppEnv.injection)
////      .provideCustomLayer(AppEnv.injection)
//      .exitCode
//
//  val app = ServerLayer.fromServiceList(builder, services)
//    .build
//    .useForever
//
//  def services =
//    ServiceList.add(new HelloWorldService())
//
//  def builder =
//    ServerBuilder
//      .forPort(8980)
//      .addService(ProtoReflectionService.newInstance())
//}

//object Main extends App {
//  def port: Int = 9000
//
//  def services: ServiceList[AppEnv] =
//    ServiceList.add(HelloWorldService())
//
//  def builder = ServerBuilder.forPort(port).addService(ProtoReflectionService.newInstance())
//
//  def serverLive: ZLayer[AppEnv, Throwable, Server] = ServerLayer.fromServiceList(builder, services)
//
//  val myAppLogic = serverLive.build.useForever
//
//  def run(args: List[String]) = myAppLogic
//    .provideSomeLayer[ZEnv](AppEnv.injection)
//    .exitCode
//}