package dto

import models.Product
import play.api.libs.json.{Json, Reads}

case class ProductDTO(name: String, category: String, price: Double, availability: Boolean, imgUrl: String, description: String)

object ProductDTO {
  implicit val jsonReader: Reads[ProductDTO] = Json.reads[ProductDTO]

  implicit def toModel(productDTO: ProductDTO): Product = Product(0, productDTO.name, productDTO.category, productDTO.price, productDTO.availability, productDTO.imgUrl, productDTO.description)

}
