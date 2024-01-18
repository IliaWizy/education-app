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
val jjwtVersion = "0.12.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-mail:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:$springBootVersion")
//    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")

    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.liquibase:liquibase-core:$liquibaseVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
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
