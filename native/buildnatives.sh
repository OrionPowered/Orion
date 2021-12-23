#!/usr/bin/env bash

# Build AnvilLoader
cd anvil && cargo build

if [ "$(uname)" == "Darwin" ]; then
    mv target/debug/libAnvilLoader.dylib ../../server/src/main/resources/
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
  mv target/debug/libAnvilLoader.so ../../server/src/main/resources/
fi
