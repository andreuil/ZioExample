package features.user

import services.EmailSender
import zhttp.http._
import zio.ZIO

object HttpRoutes {
  private val prefixPath = Root / "users"

  val routes = Http.collectM[Request] {
    case request @ Method.GET -> prefixPath / "test" =>
      ZIO.succeed(
        Response.text(
          request.getBodyAsString.getOrElse("No body!")))
    case Method.GET -> prefixPath / "text" => ZIO.succeed(Response.text("Text"))
    case Method.GET -> prefixPath / "message1" =>
      EmailSender.send("Example")
        .map(x => Response.text(x.toString))
        .orElse(ZIO.succeed(Response.text("Error")))
    case Method.GET -> prefixPath / "message2" =>
      for {
        number <- EmailSender.send("Example")
//        number <- ZIO.accessM[EmailSender](_.send("Example"))
      } yield Response.text(number.toString)
  }
}
