plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.openapi.generator") version "7.5.0"
}

group = "com.jaz"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.0.3")
    implementation("org.openapitools:openapi-generator-gradle-plugin:7.2.0")
}

openApiGenerate {
    generatorName = "spring"
    inputSpec = "$rootDir/api-gen/src/main/resources/openapi.yaml"
    apiPackage = "com.jaz.api"
    modelPackage = "com.jaz.api.model"
    configOptions = mapOf(
        "interfaceOnly" to "true",
        "delegatePattern" to "true"
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}
