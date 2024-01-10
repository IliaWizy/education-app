ARG DEFAULT_APP_NAME=educational-app

FROM openjdk:17

ARG DEFAULT_APP_NAME
ENV APP_NAME=$DEFAULT_APP_NAME

COPY build/libs/$APP_NAME.jar app.jar

EXPOSE 8080

ENTRYPOINT java -jar app.jar