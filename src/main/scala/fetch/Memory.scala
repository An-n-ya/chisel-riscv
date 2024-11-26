package fetch

import chisel3._
import chisel3.util._
import chisel3.util.experimental.{loadMemoryFromFile, loadMemoryFromFileInline}
import common.Consts._

class ImemPortIo extends Bundle {
  val addr = Input(UInt(WORD_LEN.W))
  val inst = Output(UInt(WORD_LEN.W))
}

class Memory extends Module {
  val io = IO(new Bundle{
    val imem = new ImemPortIo()
  })

  val mem = Mem(16384, UInt(8.W))

  loadMemoryFromFileInline(mem, "src/hex/fetch.hex")

  io.imem.inst := Cat(
    mem(io.imem.addr + 3.U(WORD_LEN.W)),
    mem(io.imem.addr + 2.U(WORD_LEN.W)),
    mem(io.imem.addr + 1.U(WORD_LEN.W)),
    mem(io.imem.addr),
  )
//  loadMemoryFromFile(mem, "/home/annya/playground/chisel-learn/chisel-template/src/hex/fetch.hex")
  val addr = io.imem.addr
  val inst = io.imem.inst
}
