package info.cacilhas.kodumaro

import scala.reflect.ClassTag

package object effect {

  implicit def ioEvidence[A: ClassTag](io: IO[A]): A = io()

  implicit def ioEvidence[A: ClassTag](block: => A): IO[A] = IO {block}

  implicit class IOPerformer(val io: IO[Unit]) {

    def perform(implicit evt: IO[Unit] => Unit): Unit = evt(io)
  }
}
