#!/bin/bash

if [ ! -e "target/binary-file-sum-0.0.1-SNAPSHOT.jar" ]; then
	echo "No compiled jar was found. Did you compile it?";
	exit 0;
fi

java -jar target/binary-file-sum-0.0.1-SNAPSHOT.jar $@