package features.product

import services.EmailSender
import zio._

case class Product() {
  val emailSender = ZIO.serviceWith[EmailSender]

  case class Request(
    email: String,
    password: String
  )

  sealed trait Error
  object Foo extends Error

  case class Validator() {
    def validate() = emailSender(_.send("Example"))
  }

  case class Handler() {
    def handle(request: Request): Either[Error, Response] = {
      Right(Response(token = "Token"))
    }
  }

  case class Response(
    token: String
  )
}
