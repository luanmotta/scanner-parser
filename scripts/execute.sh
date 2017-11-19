#!/bin/bash

# Get input file name from first argument
InputFileName=$1

# Clean all generated files
rm -rf *.java ./*.java~ *.class

# Use byacc
./bin/yacc.linux -J ./src/CT.y

# Use jflex
./bin/jflex-1.6.1/bin/jflex -d ./ src/CT.jflex

# Generate .class files with java
javac *.java

# Execute Parser in input file and generates output C file
java Parser ./inputs/$1.txt > ./outputs/$1.c

# Compile C file
gcc -o ./outputs/$1 ./outputs/$1.c

# Execute C file
./outputs/$1;
