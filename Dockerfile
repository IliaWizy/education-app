ARG DEFAULT_APP_NAME=educational-app

FROM openjdk:17-alpine
ARG DEFAULT_APP_NAME

COPY build/libs/$DEFAULT_APP_NAME.jar app.jar

EXPOSE 8080

ENTRYPOINT java -jar app.jar