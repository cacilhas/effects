package info.cacilhas.kodumaro.effect

import org.specs2.mutable.Specification

class IOTest extends Specification {

  "I/O" >> {

    "perform" >> {

      "Unit I/O" >> {
        val sideEffect = new MutableIntWrapper(0)
        val io = IO {sideEffect.value += 1}

        "it should perform side effect" >> {
          sideEffect.value must beEqualTo(0)
          io.perform
          sideEffect.value must beEqualTo(1)
          io.perform
          sideEffect.value must beEqualTo(2)
        }
      }

      "Valued I/O" >> {
        val counter = new Counter
        val io = IO {counter()}

        "it should update counter" >> {
          var buf: Int = io
          buf must beEqualTo(0)
          buf = io
          buf must beEqualTo(1)
          buf = io
          buf must beEqualTo(2)
        }
      }
    }

    "map" >> {
      val io = IO {12}
      val buf: String = io map {_.toString}

      "it should map to string" >> {
        buf must beEqualTo("12")
      }
    }

    "foreach" >> {
      val sideEffect = new MutableIntWrapper(0)
      val io = IO {sideEffect}

      "it should apply the lambda" >> {
        sideEffect.value must beEqualTo(0)
        io foreach {_.value += 1}
        sideEffect.value must beEqualTo(1)
        io foreach {_.value += 1}
        sideEffect.value must beEqualTo(2)
      }
    }
  }

  private class MutableIntWrapper(var value: Int)

  private class Counter {
    private var value = -1

    def apply(): Int = {
      value += 1
      value
    }
  }
}
