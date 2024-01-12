FROM gradle:8.0-jdk17-alpine AS builder
WORKDIR /

COPY config /config
COPY gradle /gradle
COPY build.gradle.kts /
COPY settings.gradle.kts /
COPY /src /src

RUN gradle clean build --no-daemon --stacktrace

FROM openjdk:17-alpine
WORKDIR /
COPY --from=builder /build/libs/educational-app.jar app.jar

EXPOSE 8080

ENTRYPOINT java -jar app.jar