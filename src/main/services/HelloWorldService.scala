import helloWorld._
import zio._
import io.grpc._

class HelloWorldService() extends ZioHelloWorld.ZHelloWorld[ZEnv, Any] {
  def sayHello(request: helloWorld.HelloRequest): ZIO[ZEnv, Status, HelloResponse] =
    ZIO.succeed(new HelloResponse(message = "Hello!"))
}