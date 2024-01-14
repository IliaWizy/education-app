plugins {
    kotlin("jvm") version "1.9.20"
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    checkstyle
}

group = "com.wizy"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val springBootVersion = "3.2.1"
val postgresqlVersion = "42.7.1"
val liquibaseVersion = "4.25.1"
val lombokVersion = "1.18.30"
val mapstructVersion = "1.5.5.Final"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.liquibase:liquibase-core:$liquibaseVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-mail:$springBootVersion")

    compileOnly ("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor ("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly ("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor ("org.projectlombok:lombok:$lombokVersion")

    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    bootJar {
        archiveFileName.set("educational-app.jar")
    }

    checkstyle {
        toolVersion = "10.12.5"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
