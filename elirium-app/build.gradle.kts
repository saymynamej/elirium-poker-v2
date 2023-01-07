import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.6.21"
    application
}

val springBootVersion: String by project

dependencies {
    implementation(project(":elirium-combination-lib"))
    implementation(project(":elirium-common"))
    implementation(project(":elirium-core"))
    implementation("org.springframework.boot:spring-boot-starter-websocket:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("Application")
}