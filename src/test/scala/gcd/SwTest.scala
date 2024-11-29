package fetch

import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class SwTest extends AnyFlatSpec with ChiselScalatestTester {
  "mycpu" should "work in sw" in {
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
