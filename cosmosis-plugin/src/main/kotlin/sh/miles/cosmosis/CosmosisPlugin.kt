package sh.miles.cosmosis

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class CosmosisPlugin : Plugin<Project> {

    companion object {
        private val logger: Logger = Logging.getLogger(CosmosisPlugin::class.java)!!
    }

    override fun apply(target: Project) {
    }

}
