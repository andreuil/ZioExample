package features.product

import services.EmailSender
import zio._

case class Product(emailSender: EmailSender) {
  case class Request(
    email: String,
    password: String
  )

  sealed trait Error
  object Foo extends Error

  def validate() = ??? // Either[ValidationError, Unit]

  def handle(request: Request): Either[Error, Response] = {
    Right(Response(id = 1))
  }

  case class Response(
    id: Int
  )
}
