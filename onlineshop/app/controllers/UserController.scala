package controllers

import dto.UserDTO
import models.User
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.UserService

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import pdi.jwt.{Jwt, JwtAlgorithm, JwtHeader, JwtClaim, JwtOptions}

@Singleton
class UserController @Inject()(val controllerComponents: ControllerComponents, userService: UserService)(implicit ec: ExecutionContext)
  extends BaseController{

  //Returns all users (basic ones and admins)
  def getAll: Action[AnyContent] = Action.async{
    userService.getAll.map(users =>Ok(Json.toJson(users)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async {
    userService.getById(id).map {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound(s"User with id: $id does not exist.")
    }
  }

  def create: Action[UserDTO] = Action.async(parse.json[UserDTO]) { request =>
    val userToAdd: User = request.body

    userService.create(userToAdd).map {
      case Some(user) => Created(Json.toJson(user))
      case None          => Conflict
    }
  }

  def delete(id: Long): Action[AnyContent] = Action.async {
    userService.delete(id).map {
      case Some(_) => NoContent
      case None    => NotFound
    }
  }

  def update(id: Long): Action[User] = Action.async(parse.json[User]) { request =>
    if (id != request.body.id)
      Future.successful(BadRequest("Id in path must be equal to id in body"))
    else
      userService.update(id, request.body).map {
        case Some(user) => Ok(Json.toJson(user))
        case None          => NotFound
      }
  }

  //Returns all basic users (does not return admins)
  def getAllUsers: Action[AnyContent] = Action.async {
    userService.getAllUsers.map(users =>Ok(Json.toJson(users)))
  }

  def getByEmail(email: String): Action[AnyContent] = Action.async {
    userService.getByEmail(email).map {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound(s"User with email: $email does not exist.")
    }
  }

  def findAllOrdersFromUser(id: Long): Action[AnyContent] = Action.async {
    userService.findAllOrdersFromUser(id).map(orders => Ok(Json.toJson(orders)))
  }

  def login(email: String, password: String): Unit ={
    val user = getByEmail(email)

    val accessToken = Jwt.encode("""{"user":1}""", "secretKey", JwtAlgorithm.HS256)
  }


}
