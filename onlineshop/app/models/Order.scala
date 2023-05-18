package models

import play.api.libs.json.{Json, OFormat}
import slick.lifted.MappedToBase.mappedToIsomorphism
import slick.lifted.ProvenShape

import java.time.LocalDate

// add items: List[Product] to Order
case class Order(id: Long, totalSum: Double, dateMade: LocalDate, itemsOrdered: String)

object Order{
  implicit val jsonFormat: OFormat[Order] = Json.format[Order]
}

object OrderTables {

  import slick.jdbc.PostgresProfile.api._

  class OrderTable(tag: Tag) extends Table[Order](tag, Some("public"), "Order"){
    def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
    //def items = column[List[Product]]("items")
    def totalSum = column[Double]("totalSum")
    def dateMade = column[LocalDate]("dateMade")

    def itemsOrdered = column[String]("itemsOrdered")

    override def * = (id,totalSum,dateMade, itemsOrdered) <> ((Order.apply _).tupled, Order.unapply)
  }

}
