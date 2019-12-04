FROM openjdk:11-jdk-oracle

VOLUME /tmp
ENV TZ America/Sao_Paulo
RUN cp /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ARG JAR_FILE

COPY ${JAR_FILE} app.jar


COPY entrypoint.sh entrypoint.sh
ENTRYPOINT ./entrypoint.sh
