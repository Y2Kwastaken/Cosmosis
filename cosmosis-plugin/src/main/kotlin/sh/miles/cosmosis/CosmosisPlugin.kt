package sh.miles.cosmosis

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import sh.miles.cosmosis.tasks.DownloadCosmicToolsTask
import sh.miles.cosmosis.tasks.RunCosmicToolsTask
import sh.miles.cosmosis.utils.COSMIC_TOOLS_VERSION
import sh.miles.cosmosis.utils.COSMOSIS_DEVELOPER_BUNDLE
import sh.miles.cosmosis.utils.DEFAULT_TASK_GROUP

class CosmosisPlugin : Plugin<Project> {

    companion object {
        val logger: Logger = Logging.getLogger(CosmosisPlugin::class.java)!!
    }

    override fun apply(target: Project) {
        target.tasks.register("downloadCosmicTools", DownloadCosmicToolsTask::class.java) {
            group = DEFAULT_TASK_GROUP
            description = "Downloads CosmicTools"
            cosmicToolsVersion = COSMIC_TOOLS_VERSION
            cosmicToolsDestination = target.file("build/cosmic-tools")

            mustRunAfter(target.tasks.getByName("clean"))
        }

        target.tasks.register("runCosmicTools", RunCosmicToolsTask::class.java) {
            group = DEFAULT_TASK_GROUP
            description = "Runs CosmicTools"
            webDriver = project.property("web_driver") as String
            executionDir = target.layout.buildDirectory.get().dir("cosmic-tools")
        }

        target.extensions.create(
            COSMOSIS_DEVELOPER_BUNDLE,
            CosmosisDependencyExtension::class.java,
            target.dependencies
        )
    }

}
