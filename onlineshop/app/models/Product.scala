package models
import enums.ProductCategory.ProductCategory
import play.api.libs.json.{Json, OFormat}
import slick.lifted.ProvenShape

// availability - true=in stock ; false = out of stock
case class Product(id: Long, name: String, category: String, price: Double, availability: Boolean, imgUrl: String, description: String)


object Product{
  implicit val jsonFormat: OFormat[Product] = Json.format[Product]
}

object SlickTables{
  import slick.jdbc.PostgresProfile.api._

  class ProductTable(tag: Tag) extends Table[Product](tag, Some("public"), "Product"){
    def id = column[Long]("id",O.PrimaryKey, O.AutoInc)
    def name = column[String]("name",O.Unique)
    def category = column[String]("category")
    def price = column[Double]("price")
    def availability = column[Boolean]("availability")
    def imgUrl = column[String]("imgUrl")
    def description = column[String]("description")

    override def * : ProvenShape[Product] = (id, name, category, price, availability,imgUrl,description) <> ((Product.apply _).tupled,Product.unapply)
  }

  // Api entry point
  //lazy val productTable = TableQuery[ProductTable]
}
