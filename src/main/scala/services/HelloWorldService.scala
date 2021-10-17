package services

import common.AppEnv.AppEnv
import helloWorld._
import io.grpc._
import zio._

case class HelloWorldService() extends ZioHelloWorld.ZHelloWorld[AppEnv, Any] {
  def sayHello(request: HelloRequest): ZIO[AppEnv, Status, HelloResponse] =
    ZIO.access[EmailSender](_.send("Email sent"))
        .map(_ => new HelloResponse(message = request.name.get))
}