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
