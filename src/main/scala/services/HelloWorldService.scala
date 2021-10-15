package services

import helloWorld._
import io.grpc._
import zio._

class HelloWorldService() extends ZioHelloWorld.ZHelloWorld[ZEnv, Any] {
  def sayHello(request: HelloRequest): ZIO[ZEnv, Status, HelloResponse] =
    console.putStrLn("Request received")
      .map(_ => new HelloResponse(message = request.name.get))
      .mapError(_ => Status.NOT_FOUND)

//  console.putStrLn("Request received")
//    .mapBoth(
//      _ => Status.NOT_FOUND,
//      _ => new HelloResponse(message = request.name.get))

//    (for {
//      _ <- console.putStrLn("Request received")
//    } yield new HelloResponse(message = request.name.get))
//      .mapError(_ => Status.NOT_FOUND)

//    val response = for {
//      _ <- console.putStrLn("Request received")
//    } yield new HelloResponse(message = request.name.get)
//    val response2 = response.mapError(_ => Status.NOT_FOUND)
//    response2
//  }
}