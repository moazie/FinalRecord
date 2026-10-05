#!/bin/bash

echo "Compiling..."
javac -cp "lib/*:src" src/Client.java

if [ $? -eq 0 ]; then
    echo "Running..."
    java -cp "lib/*:src" Client
else
    echo "Compilation failed."
fi
