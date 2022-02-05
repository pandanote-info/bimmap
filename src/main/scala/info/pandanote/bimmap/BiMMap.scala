package info.pandanote.bimmap

import org.slf4j.LoggerFactory
import scala.collection.mutable.{ Map => MMap }
import scala.collection._

class BiMMap_[T, U](m: MMap[T,U]) extends MMap[T, U] {

  override def iterator: Iterator[(T, U)] = m.iterator

  override def get(key: T): Option[U] = m.get(key)

  override def addOne(elem: (T, U)) = {
    if (m.values.toSeq.contains(elem._2)) {
    } else {
      m.addOne(elem)
    }
    this
  }

  override def subtractOne(elem: T) = {
    m.subtractOne(elem)
    this
  }

  def inverse() = {
    val mm: BiMMap[U,T] = new BiMMap[U,T]()
    mm ++= m.map{kv => (kv._2 -> kv._1)}
    mm
  }

  def getValue(value: U): Option[T] = inverse().get(value)
}

class BiMMap[T, U] extends BiMMap_[T,U](MMap[T, U]()) {
}

