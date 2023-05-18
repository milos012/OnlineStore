package services

import repositories.UserRepository
import models.{Order, User, UserMapping}

import javax.inject.Inject
import scala.concurrent.Future

class UserService @Inject() (userRepository: UserRepository){

  def getAll: Future[Seq[User]] = userRepository.getAll

  def getById(id: Long): Future[Option[User]] = userRepository.getById(id)

  def create(user1: User): Future[Option[User]] = userRepository.insert(user1)

  def update(id: Long, user1: User) = userRepository.update(id, user1)

  def delete(id: Long): Future[Option[Int]] = userRepository.delete(id)

  def getByEmail(email: String):Future[Option[User]] = userRepository.getByEmail(email)

  def getAllUsers:Future[Seq[User]] = userRepository.getAllUsers

  def findAllOrdersFromUser(id: Long): Future[Seq[Order]] = userRepository.findAllOrdersFromUser(id)

  def getAllUO: Future[Seq[UserMapping]] = userRepository.getAllUO

}
