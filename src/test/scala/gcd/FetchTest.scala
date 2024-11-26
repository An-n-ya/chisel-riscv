package fetch

import org.scalatest._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class HexTest extends AnyFlatSpec with ChiselScalatestTester {
  "mycpu" should "work through hex" in {
    test(new Top) {
      c => {
        c.clock.setTimeout(10)
        while (!c.io.exit.peek().litToBoolean) {
          c.clock.step(1)
        }
      }

    }
  }
}
