#!/usr/bin/env bash

# who needs fancy build tools when bash exists?

cd $HOME/dino-game

mkdir -p bin

package_path="io/github/mxz_schwarz/dino_game"

javac -d bin $package_path/*.java
cp -r $package_path/imgs bin/$package_path

jar cvfm dino-game.jar MANIFEST.MF -C bin/ .

rm -r bin

#  "java -jar dino-game.jar" to run
