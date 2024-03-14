dependencies {
    implementation(project(":cosmosis-core"))
}

gradlePlugin {
    plugins {
        create("rind-verification") {
            id = "sh.miles.cosmosis"
            implementationClass = "sh.miles.cosmosis.CosmosisPlugin"
            displayName = "Rind verification injector"
            description = "Rind verification injector"
        }
    }
}

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
