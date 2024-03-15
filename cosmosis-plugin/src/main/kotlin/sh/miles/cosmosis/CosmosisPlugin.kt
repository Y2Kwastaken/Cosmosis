package sh.miles.cosmosis

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import sh.miles.cosmosis.tasks.DownloadCosmicToolsTask
import sh.miles.cosmosis.utils.COSMIC_TOOLS_VERSION
import sh.miles.cosmosis.utils.COSMOSIS_DEVELOPER_BUNDLE
import sh.miles.cosmosis.utils.DEFAULT_TASK_GROUP

class CosmosisPlugin : Plugin<Project> {

    companion object {
        private val logger: Logger = Logging.getLogger(CosmosisPlugin::class.java)!!
    }

    override fun apply(target: Project) {
        target.tasks.register("downloadCosmicTools", DownloadCosmicToolsTask::class.java) { task ->
            task.group = DEFAULT_TASK_GROUP
            task.cosmicToolsVersion.set(COSMIC_TOOLS_VERSION)
            task.cosmicToolsDestination.set(target.file("build/cosmic-tools"))
        }

        target.extensions.create(
            COSMOSIS_DEVELOPER_BUNDLE,
            CosmosisDependencyExtension::class.java,
            target.dependencies
        )
    }

}
