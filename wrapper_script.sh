#!/bin/bash

# Start filebeats
cd /filebeat
./filebeat -e -c filebeat.yml &
status=$?
if [ $status -ne 0 ]; then
  echo "Failed to start filebeats: $status"
  exit $status
fi

# Start Spring Boot standalone JAR
cd /
java -Djava.security.egd=file:/dev/./urandom -jar /aplicacao-template.jar
