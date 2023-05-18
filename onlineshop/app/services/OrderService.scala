package services

import repositories.OrderRepository
import models.Order

import javax.inject.Inject
import scala.concurrent.Future

class OrderService @Inject() (orderRepository: OrderRepository){

  def getAll: Future[Seq[Order]] = orderRepository.getAll

  def getById(id: Long): Future[Option[Order]] = orderRepository.getById(id)

  def create(ord: Order): Future[Option[Order]] = orderRepository.insert(ord)

  def update(id: Long, ord: Order) = orderRepository.update(id, ord)

  def delete(id: Long): Future[Option[Int]] = orderRepository.delete(id)

}
