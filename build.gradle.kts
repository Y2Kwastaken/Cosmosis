plugins {
    id("java")
    id("com.gradle.plugin-publish") version "1.2.1"
    id("maven-publish")
    kotlin("jvm") version "1.9.22"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "com.gradle.plugin-publish")
    apply(plugin = "maven-publish")
}
