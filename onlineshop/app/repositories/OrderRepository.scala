package repositories

import models.Order
import models.OrderTables.OrderTable
import org.postgresql.util.PSQLException
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class OrderRepository @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{

  import profile.api._

  val orders = TableQuery[OrderTable]

  def getAll: Future[Seq[Order]] = db.run(orders.result)

  def getById(id: Long): Future[Option[Order]] = db.run(orders.filter(_.id === id).result).map(_.headOption)

  def insert(ord1: Order): Future[Option[Order]] = db.run((orders returning orders) += ord1).map(Some.apply[Order]).recover {
    case e: PSQLException => None
  }

  def delete(id: Long) = {
    db.run(orders.filter(_.id === id).delete).map {
      case 0 => None
      case 1 => Some(1)
      case deleted => throw new RuntimeException(s"Deleted $deleted rows")
    }
  }

  def update(id: Long, ordNew: Order) = {
    db.run(orders.filter(_.id === id).update(ordNew).map {
      case 0 => None
      case 1 => Some(1)
      case updated => throw new RuntimeException(s"Updated $updated rows")
    })
  }

}
