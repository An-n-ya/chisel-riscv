#!/bin/bash
BASE=/home/annya/src/riscv-tests/target/share/riscv-tests/isa
FILES=$BASE/rv32*i-p-*
SAVE_DIR=$BASE/hex

mkdir -p SAVE_DIR
set -x
set -e
for f in $FILES; do
    FILE_NAME="${f##*/}"
    if [[ ! "$f" == *dump ]] && [[ ! "$f" == *bin ]]; then
        riscv64-unknown-elf-objcopy -O binary $f $SAVE_DIR/$FILE_NAME.bin
        od -An -tx1 -w1 -v $SAVE_DIR/$FILE_NAME.bin >$SAVE_DIR/$FILE_NAME.hex
        rm -f $SAVE_DIR/$FILE_NAME.bin
    fi
done
