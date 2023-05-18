package services

import models.Product
import repositories.ProductRepository

import javax.inject.Inject
import scala.concurrent.Future

class ProductService @Inject() (productRepository: ProductRepository){

  def getAll: Future[Seq[Product]] = productRepository.getAll

  def getById(id: Long): Future[Option[Product]] = productRepository.getById(id)

  def create(prod: Product): Future[Option[Product]] = productRepository.insert(prod)

  def update(id: Long, prod: Product) = productRepository.update(id, prod)

  def delete(id: Long): Future[Option[Int]] = productRepository.delete(id)

  def getByName(name: String):Future[Option[Product]] = productRepository.getByName(name)

  def getAvailableProducts:Future[Seq[Product]] = productRepository.getAvailableProducts

}
