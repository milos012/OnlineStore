package dto

import models.Order
import play.api.libs.json.{Json, Reads}

import java.time.LocalDate

case class OrderDTO(totalSum: Double, dateMade: LocalDate, itemsOrdered: String)

object OrderDTO {

  implicit val jsonReader: Reads[OrderDTO] = Json.reads[OrderDTO]

  implicit def toModel(orderDTO: OrderDTO): Order = Order(0, orderDTO.totalSum, orderDTO.dateMade, orderDTO.itemsOrdered)

}
