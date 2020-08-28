package info.cacilhas.kodumaro

package object effect {

  implicit def ioEvidence[A](io: IO[A]): A = io()

  implicit class IOPerformer(val io: IO[Unit]) {

    def perform(implicit evt: IO[Unit] ⇒ Unit): Unit = evt(io)
  }
}
