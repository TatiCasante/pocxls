#!/bin/sh

source /etc/profile
#-agentlib:jdwp=transport=dt_socket,address=8787,suspend=n,server=y
java -jar  -Djava.security.egd=file:/dev/./urandom /app.jar