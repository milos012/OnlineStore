package models

//import enums.UserType.UserType
import play.api.libs.json.{Json, OFormat}
import slick.lifted.ProvenShape

import java.time.LocalDate


case class User(id: Long, email: String, password: String, fName: String, lName: String,dateBirth: LocalDate,imgUrl: String, uType: String)
case class UserMapping(id: Long, userId: Long, orderId: Long)
object User{
  implicit val jsonFormat: OFormat[User] = Json.format[User]
}

object UserMapping{
  implicit val jsonFormat: OFormat[UserMapping] = Json.format[UserMapping]
}

object UserTables {

  import slick.jdbc.PostgresProfile.api._


  class UserTable(tag: Tag) extends Table[User](tag, Some("public"), "User") {

    def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
    def email = column[String]("email")
    def password = column[String]("password")
    def fName = column[String]("fName")
    def lName = column[String]("lName")
    def dateBirth = column[LocalDate]("dateBirth")
    def imgUrl = column[String]("imgUrl")
    def uType = column[String]("uType")
//    def activated = column[Boolean]("activated")

    override def * = (id, password,email,fName,lName,dateBirth,imgUrl,uType) <> ((User.apply _).tupled, User.unapply)

  }

  class UserMappingTable(tag: Tag) extends Table[UserMapping](tag, Some("public"), "UserMapping") {
    def id = column[Long]("user_order_id", O.PrimaryKey,O.AutoInc)
    def userId = column[Long]("user_id")
    def orderId = column[Long]("order_id")

    override def * : ProvenShape[UserMapping] = (id,userId,orderId) <> ((UserMapping.apply _).tupled, UserMapping.unapply)
  }
}