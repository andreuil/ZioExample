package features.user

object SignUp {
  case class Request(
    email: String,
    password: String
  )

  sealed trait Error
  object Foo extends Error

  class Validator() {
    def validate() = ???
  }

  case class Handler() {
    def handle(): Either[Error, Response] = {
      Right(Response(token = "Token"))
    }
  }

  case class Response(
    token: String
  )
}
