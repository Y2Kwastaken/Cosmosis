plugins {
    id("org.gradle.kotlin.kotlin-dsl") version "4.3.0"
    id("de.undercouch.download") version "5.6.0"
}

dependencies {
    api(project(":cosmosis-core"))
}

gradlePlugin {
    plugins {
        create("cosmosis") {
            id = "sh.miles.cosmosis"
            implementationClass = "sh.miles.cosmosis.CosmosisPlugin"
            displayName = "Rind verification injector"
            description = "Rind verification injector"
        }
    }
}
