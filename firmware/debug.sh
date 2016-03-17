#!/bin/bash

ARGS="./build/a.out -x gdbx"

if [ "$1" == "init" ]; then
	ARGS+="-ex monitor reset init"
fi

arm-none-eabi-gdb $ARGS
