#!/bin/bash

BASE_DIR=/home/geri/Desktop/University/PF2/HOMEWORK/project-jtc/
TARGET_FILE=target/jtc-1.0-SNAPSHOT.jar
FINAL_FILE=bin/jtc-1.0.jar

cd $BASE_DIR
mvn clean package && mv $TARGET_FILE $FINAL_FILE && echo Project built and packaged. Packaged jar is saved at $FINAL_FILE


