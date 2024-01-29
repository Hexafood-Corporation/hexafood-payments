import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("jacoco")
    id("org.sonarqube") version "3.5.0.2730"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
}

group = "br.com.fiap"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
val awsSpringVersion = "3.0.1"


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    testImplementation("org.testng:testng:7.1.0")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("com.h2database:h2")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("io.awspring.cloud:spring-cloud-aws-starter-sqs")
    implementation("org.springframework:spring-messaging:6.0.11")

    implementation("org.springframework.cloud:spring-cloud-starter-aws-messaging:2.2.6.RELEASE")
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.mockk:mockk:1.13.9")
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

jacoco {
    toolVersion = "0.8.7"
}

tasks.test {
    finalizedBy("jacocoTestCoverageVerification")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val excludePackage: Iterable<String> = listOf(
    "**/br/com/fiap/config/**"
)

extra["excludePackages"] = excludePackage
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(excludePackage)
        }
    )
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)
    violationRules {
        rule {
            limit {
                minimum = 0.8.toBigDecimal()
                counter = "LINE"
            }
        }
    }
    classDirectories.setFrom(
        sourceSets.main.get().output.asFileTree.matching {
            exclude(excludePackage)
        }
    )
}

sonarqube {
    properties {
        property("sonar.sources", "src/main")
        property("sonar.scm.disabled", "True")
        property("sonar.coverage.exclusions", excludePackage)
    }
}

tasks.sonarqube {
    dependsOn(tasks.jacocoTestReport)
}