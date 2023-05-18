package auth

import models.User

import javax.inject.Inject
import play.api.Configuration

import scala.util.{Failure, Success, Try}
import pdi.jwt.{Jwt, JwtAlgorithm, JwtJson}

class AuthService {

  val key = "secretKey"
  val algo = JwtAlgorithm.HS256

  def encode(user: User) = {
    val accessToken = Jwt.encode("""{"user":user}""", key, algo)

  }

}
