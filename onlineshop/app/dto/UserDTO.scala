package dto

import models.User
import play.api.libs.json.{Json, Reads}

import java.time.LocalDate

case class UserDTO(email: String, password: String, fName: String, lName: String,dateBirth: LocalDate, imgUrl: String, uType: String)

object UserDTO {

  implicit val jsonReader: Reads[UserDTO] = Json.reads[UserDTO]

  implicit def toModel(userDTO: UserDTO): User = User(0, userDTO.email, userDTO.password, userDTO.fName, userDTO.lName, userDTO.dateBirth, userDTO.imgUrl, userDTO.uType)

}
