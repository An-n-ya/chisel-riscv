#!/bin/bash
set -e
set -x
UI_INSTS=(sw lw add addi sub and andi or ori xor xori sll srl sra slli srli srai slt sltu slti sltiu beq bne blt bge bltu bgeu jal jalr lui auipc)
MI_INSTS=(csr scall)

WORK_DIR=/home/annya/playground/chisel-learn/chisel-riscv
RESULT_DIR=$WORK_DIR/results
mkdir -p $RESULT_DIR

cd $WORK_DIR

function loop_test() {
    INSTS=${!1}
    ISA=$2
    for INST in ${INSTS[@]}; do
        echo $INST
        FILE="rv32$ISA-p-$INST.hex"
        sed -i 's/".*"/'"\"\/home\/annya\/src\/riscv-tests\/target\/share\/riscv-tests\/isa\/hex\/${FILE}\""'/g' $WORK_DIR/src/main/scala/fetch/Memory.scala

        sbt "testOnly fetch.riscv_tests" >$RESULT_DIR/$INST.txt
    done
}

loop_test UI_INSTS[@] "ui"
