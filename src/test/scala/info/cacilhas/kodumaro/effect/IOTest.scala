package info.cacilhas.kodumaro.effect

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class IOTest extends AnyFlatSpec with Matchers {

  "Unit I/O perform" should "perform side effect" in {
    val sideEffect = new AtomicInteger
    val io: IO[Unit] = sideEffect set (sideEffect.get + 1)
    sideEffect.get mustBe 0
    io.perform
    sideEffect.get mustBe 1
    io.perform
    sideEffect.get mustBe 2
  }

  "Valued I/O perform" should "update counter" in {
    val counter = new Counter
    val io: IO[Int] = counter()
    var buf: Int = io
    buf mustBe 0
    buf = io
    buf mustBe 1
    buf = io
    buf mustBe 2
  }

  "Valued I/O map" should "map to string" in {
    val io: IO[Int] = 12
    val buf: String = io map {_.toString}
    buf mustEqual "12"
  }

  "Value I/O foreach" should "apply the lambda" in {
    val sideEffect = new AtomicInteger
    val io: IO[AtomicInteger] = sideEffect
    sideEffect.get mustBe 0
    io foreach {e => e set (e.get + 1)}
    sideEffect.get mustBe 1
    io foreach {e => e set (e.get + 1)}
    sideEffect.get mustBe 2
  }

  "toString" should "return type" in {
    val io1: IO[Int] = 0
    val io2: IO[Unit] = ()
    val io3: IO[String] = ""
    io1.toString mustEqual "IO(=> int)"
    io2.toString mustEqual "IO(=> void)"
    io3.toString mustEqual "IO(=> string)"
  }

  private class Counter {
    private var value = -1

    def apply(): Int = {
      value += 1
      value
    }
  }
}
