package repositories


import models.OrderTables.OrderTable
import models.{Order, User, UserMapping}
import models.UserTables.{UserMappingTable, UserTable}
import org.postgresql.util.PSQLException
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class UserRepository @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]{

  import profile.api._

  val users = TableQuery[UserTable]

  val userMappingTable = TableQuery[UserMappingTable]

  val orderMappingTable = TableQuery[OrderTable]


  def getAll: Future[Seq[User]] = db.run(users.result)

  def getById(id: Long): Future[Option[User]] = {
    db.run(users.filter(_.id === id).result).map(_.headOption)
  }

  def insert(u1: User): Future[Option[User]] = {
    db.run((users returning users) += u1).map(Some.apply[User]).recover {
      case e: PSQLException => None
    }
  }

  def delete(id: Long) = {
    db.run(users.filter(_.id === id).delete).map{
      case 0 => None
      case 1 => Some(1)
      case deleted => throw new RuntimeException(s"deleted $deleted rows")
    }
  }

  def update(id: Long, uNew: User) = {
    db.run(users.filter(_.id === id).update(uNew).map{
      case 0 => None
      case 1 => Some(1)
      case updated => throw new RuntimeException(s"updated $updated rows")
    })
  }

  def getByEmail(email: String): Future[Option[User]] = {
    db.run(users.filter(_.email === email).result).map(_.headOption)
  }

  //proveri; promeniti uType na enum?
  def getAllUsers:Future[Seq[User]] = db.run(users.filter(_.uType === "user" ).result)

  def findAllOrdersFromUser(userId: Long): Future[Seq[Order]] = {
    val joinQuery = userMappingTable
      .filter(_.userId === userId)
      .join(orderMappingTable).on(_.orderId === _.id)
      .map(_._2)

    db.run(joinQuery.result)
  }

  def getAllUO: Future[Seq[UserMapping]] = db.run(userMappingTable.result)

}
