package info.cacilhas.kodumaro.effect

import scala.reflect.ClassTag

final class IO[+A: ClassTag] private(block: () => A) {

  def foreach(f: A => Unit): Unit = map(f).perform

  def map[B: ClassTag](f: A => B): IO[B] = new IO[B](() => f(block()))

  private[effect] def apply(): A = block()

  private[this] lazy val className: String = implicitly[ClassTag[A]]
    .map(_.runtimeClass.getName.split("\\.").last.toLowerCase)

  override def toString: String = s"IO(=> $className)"
}

object IO {

  def apply[A: ClassTag](block: => A): IO[A] = new IO[A](() => block)
}
