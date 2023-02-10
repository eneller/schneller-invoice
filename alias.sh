#!/bin/bash
# combined with a special gradle task provides a CLI to playwright like you installed it in your system
playwright () {
  if [ $# -eq 0 ]; then
    ./gradlew playwright -Pargs="--help";
  else
    ./gradlew playwright -Pargs="$@";
  fi
}
