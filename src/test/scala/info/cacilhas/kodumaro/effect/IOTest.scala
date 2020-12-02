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

  private class Counter {
    private var value = -1

    def apply(): Int = {
      value += 1
      value
    }
  }
}
