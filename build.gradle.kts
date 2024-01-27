import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
}

group = "br.com.fiap"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
val awsSpringVersion = "3.0.1"
val mainClassName = "TechChallengeApplication"
val testcontainersVersion = "1.18.0"
val awsJavaSdkCoreVersion = "1.12.481"
val awsLambdaJavaEventsVersion = "3.11.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("io.awspring.cloud:spring-cloud-aws-starter-sqs")

    runtimeOnly("com.h2database:h2")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.testcontainers:localstack")
    testImplementation ("org.testcontainers:junit-jupiter")
    // Required to compile due to coupling between the LocalStack Testcontainers module and the AWS Java SDK v1
    testImplementation ("com.amazonaws:aws-java-sdk-core:${awsJavaSdkCoreVersion}")

}

dependencyManagement {
    imports {
        mavenBom("io.awspring.cloud:spring-cloud-aws-dependencies:$awsSpringVersion")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "${project.group}.$mainClassName"
    }
}

