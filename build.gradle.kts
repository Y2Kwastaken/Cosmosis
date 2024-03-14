plugins {
    id("java")
    id("com.gradle.plugin-publish") version "1.2.1"
    id("maven-publish")
    kotlin("jvm") version "1.9.22"
}

group = "sh.miles.cosmosis"
version = "1.0.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "com.gradle.plugin-publish")
    apply(plugin = "maven-publish")

    version = parent!!.version

    publishing {
        publications {
            create<MavenPublication>("Maven") {
                this.artifact(tasks.jar)
                this.groupId = "sh.miles.cosmosis"
                this.version = rootProject.version.toString()
            }
        }

        repositories {
            maven("https://maven.miles.sh/snapshots") {
                credentials {
                    this.username = System.getenv("PINEAPPLE_REPOSILITE_USERNAME")
                    this.password = System.getenv("PINEAPPLE_REPOSILITE_PASSWORD")
                }
            }
        }
    }
}
