#!/bin/bash

BASE_DIR=/home/geri/Desktop/University/PF2/HOMEWORK/project-jtc/

cd $BASE_DIR
MAIN_CLASS=$(cat pom.xml | grep mainClass | sed 's/<mainClass>//g' | sed 's/<\/mainClass>//g' | awk '{print $1}')
echo $MAIN_CLASS
mvn compile
mvn exec:java -Dexec.mainClass="$MAIN_CLASS" -Dexec.args="$1"