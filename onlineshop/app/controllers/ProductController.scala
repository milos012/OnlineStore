package controllers

import models.Product
import dto.ProductDTO
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.ProductService

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents, productService: ProductService)(implicit ec: ExecutionContext)
  extends BaseController{

  def getAll: Action[AnyContent] = Action.async{
    productService.getAll.map(products =>Ok(Json.toJson(products)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async {
    productService.getById(id).map {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(s"Product with id: $id does not exist.")
    }
  }

  def create: Action[ProductDTO] = Action.async(parse.json[ProductDTO]) { request =>
    val productToAdd: Product = request.body

    productService.create(productToAdd).map {
      case Some(product) => Created(Json.toJson(product))
      case None          => Conflict
    }
  }

  def delete(id: Long): Action[AnyContent] = Action.async {
    productService.delete(id).map {
      case Some(_) => NoContent
      case None    => NotFound
    }
  }

  def update(id: Long): Action[Product] = Action.async(parse.json[Product]) { request =>
    if (id != request.body.id)
      Future.successful(BadRequest("Id in path must be equal to id in body"))
    else
      productService.update(id, request.body).map {
        case Some(product) => Ok(Json.toJson(product))
        case None          => NotFound
      }
  }

  def getByName(n: String): Action[AnyContent] = Action.async {
    productService.getByName(n).map {
      case Some(product) => Ok(Json.toJson(product))
      case None => NotFound(s"Product with name: $n does not exist.")
    }
  }

  def getAvailableProducts: Action[AnyContent] = Action.async{
    productService.getAvailableProducts.map(products =>Ok(Json.toJson(products)))
  }

}
