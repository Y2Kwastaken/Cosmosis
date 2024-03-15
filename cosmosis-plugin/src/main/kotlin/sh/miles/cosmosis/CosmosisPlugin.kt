package sh.miles.cosmosis

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import sh.miles.cosmosis.utils.DEFAULT_TASK_GROUP
import sh.miles.cosmosis.utils.COSMIC_TOOLS_VERSION
import sh.miles.cosmosis.tasks.DownloadCosmicToolsTask

class CosmosisPlugin : Plugin<Project> {

    companion object {
        private val logger: Logger = Logging.getLogger(CosmosisPlugin::class.java)!!
    }

    override fun apply(target: Project) {
        target.tasks.register("downloadCosmicTools", DownloadCosmicToolsTask::class.java) {
            group = DEFAULT_TASK_GROUP
            cosmicToolsVersion.set(COSMIC_TOOLS_VERSION)
            cosmicToolsDestination.set(target.file("build/cosmic-tools"))
        }
    }

}
