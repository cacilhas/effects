package info.cacilhas.kodumaro.effect

final class IO[+A] private(block: () => A) {

  def foreach(f: A => Unit): Unit = map(f).perform

  def map[B](f: A => B): IO[B] = new IO[B](() => f(block()))

  override def toString: String = s"IO($block)"

  private[effect] def apply(): A = block()
}

object IO {

  def apply[A](block: => A): IO[A] = new IO[A](() => block)
}
