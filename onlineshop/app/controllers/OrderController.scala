package controllers

import dto.OrderDTO
import models.Order
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.OrderService

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class OrderController @Inject()(val controllerComponents: ControllerComponents, orderService: OrderService)(implicit ec: ExecutionContext)
  extends BaseController{

  def getAll: Action[AnyContent] = Action.async{
    orderService.getAll.map(orders =>Ok(Json.toJson(orders)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async {
    orderService.getById(id).map {
      case Some(order) => Ok(Json.toJson(order))
      case None => NotFound(s"Order with id: $id does not exist")
    }
  }

  def create: Action[OrderDTO] = Action.async(parse.json[OrderDTO]) { request =>
    val orderToAdd: Order = request.body

    orderService.create(orderToAdd).map {
      case Some(order) => Created(Json.toJson(order))
      case None          => Conflict
    }
  }

  def delete(id: Long): Action[AnyContent] = Action.async {
    orderService.delete(id).map {
      case Some(_) => NoContent
      case None    => NotFound
    }
  }

  def update(id: Long): Action[Order] = Action.async(parse.json[Order]) { request =>
    if (id != request.body.id)
      Future.successful(BadRequest("Id in path must be equal to id in body"))
    else
      orderService.update(id, request.body).map {
        case Some(order) => Ok(Json.toJson(order))
        case None          => NotFound
      }
  }

}
