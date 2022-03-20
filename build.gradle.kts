import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20-M1" apply false
}

subprojects {
    apply(plugin = "org.gradle.java-library")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "io.github.orioncraftmc.tellmeicheat"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    dependencies {
        "implementation"(kotlin("reflect"))
    }
}