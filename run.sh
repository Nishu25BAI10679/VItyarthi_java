#!/bin/sh
set -e
rm -rf out/classes
mkdir -p out/classes
javac -d out/classes src/tracker/*.java
java -cp out/classes tracker.App "$@"
