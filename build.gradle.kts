plugins {
    kotlin("jvm") version "1.9.23"
    // Versions – use ./gradlew dependencyUpdates to check for updates
    id ("com.github.ben-manes.versions") version "0.51.0"
}

group = "net.joshuabrandes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val retrofit_version: String by project
val jackson_version: String by project
val kotlin_coroutines_version: String by project

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version")

    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-jackson:$retrofit_version")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}