#!/usr/bin/env bash

# Build AnvilLoader
# rm ../run/lib/libAnvilLoader.so 2> /dev/null || true
cd anvil && cargo build
mv target/debug/libAnvilLoader.so ../../server/src/main/resources/