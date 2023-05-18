package repositories

import models.Product
import models.SlickTables
import org.postgresql.util.PSQLException
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ProductRepository @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{

  import profile.api._

  val products = TableQuery[SlickTables.ProductTable]

  def getAll: Future[Seq[Product]] = db.run(products.result)

  def getById(id: Long): Future[Option[Product]] = db.run(products.filter(_.id === id).result).map(_.headOption)

  def insert(prod1: Product): Future[Option[Product]] = db.run((products returning products) += prod1).map(Some.apply[Product]).recover {
    case e: PSQLException => None
  }

  def delete(id: Long) = {
    db.run(products.filter(_.id ===id).delete).map{
      case 0 => None
      case 1 => Some(1)
      case deleted => throw new RuntimeException(s"Deleted $deleted rows")
    }
  }

  def update(id: Long, prodNew: Product) = {
    db.run(products.filter(_.id === id).update(prodNew).map {
      case 0 => None
      case 1 => Some(1)
      case updated => throw new RuntimeException(s"Updated $updated rows")
    })
  }

  def getByName(name: String): Future[Option[Product]] = db.run(products.filter(_.name === name).result).map(_.headOption)

  //TEST
  def getAvailableProducts:Future[Seq[Product]] = db.run(products.filter(_.availability === true).result)

  // mozda i get all by category?





}
