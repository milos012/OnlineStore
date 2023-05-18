package enums


sealed trait UserType

case object Regular extends UserType
case object Admin extends UserType

//object UserType extends Enumeration {
//  type UserType = Value
//
//  //values
//  val admin = Value("Admin")
//  val regular = Value("Regular")
//
//}
