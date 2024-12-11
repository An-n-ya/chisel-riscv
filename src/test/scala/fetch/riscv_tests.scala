package fetch

import chisel3.fromIntToLiteral
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class riscv_tests extends AnyFlatSpec with ChiselScalatestTester {
  "mycpu" should "work in riscv tests" in {
    test(new Top) { c =>
      {
        c.clock.setTimeout(600)
        while (!c.io.exit.peek().litToBoolean) {
          c.clock.step(1)
        }
        c.io.gp.expect(1.U)
      }

    }
  }
}
