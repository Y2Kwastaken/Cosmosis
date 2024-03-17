package sh.miles.cosmosis

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.Exec
import org.gradle.jvm.tasks.Jar
import sh.miles.cosmosis.core.CosmosisUtils
import sh.miles.cosmosis.tasks.CopyCosmicReachTask
import sh.miles.cosmosis.tasks.DownloadCosmicToolsTask
import sh.miles.cosmosis.tasks.DownloadUnzipModLoader
import sh.miles.cosmosis.tasks.RunCosmicToolsTask
import sh.miles.cosmosis.utils.COSMIC_LOADER_URL
import sh.miles.cosmosis.utils.COSMIC_TOOLS_VERSION
import sh.miles.cosmosis.utils.COSMOSIS_EXTENSION
import sh.miles.cosmosis.utils.DEFAULT_SETUP_TASK_GROUP
import sh.miles.cosmosis.utils.DEFAULT_TASK_GROUP
import sh.miles.cosmosis.utils.FABRIC_LOADER_DIRECTORY

class CosmosisPlugin : Plugin<Project> {

    companion object {
        val logger: Logger = Logging.getLogger(CosmosisPlugin::class.java)!!
    }

    override fun apply(target: Project) {
        val cosmosisExtension = target.extensions.create(
            COSMOSIS_EXTENSION, CosmosisExtension::class.java, target.dependencies
        )

        setupSetupTasks(cosmosisExtension, target)
        setupCosmosisTasks(target)
    }

    private fun setupCosmosisTasks(target: Project) {
        target.tasks.register("setup", DefaultTask::class.java) {
            group = DEFAULT_TASK_GROUP

            val tasks = target.tasks
            dependsOn(tasks.getByName("downloadCosmicTools"))
            dependsOn(tasks.getByName("downloadLoader"))
            dependsOn(tasks.getByName("runCosmicTools"))
            dependsOn(tasks.getByName("copyCosmicReach"))
        }

        target.tasks.register("cleanLoader", Delete::class.java) {
            group = DEFAULT_TASK_GROUP
            description = "Deletes the current fabric loader"
            delete(FABRIC_LOADER_DIRECTORY, FABRIC_LOADER_DIRECTORY)

            mustRunAfter(target.tasks.getByName("clean"))
        }

        target.tasks.register("copyJarToMods", Copy::class.java) {
            group = DEFAULT_TASK_GROUP
            description = "Copies the output jar into the mods folder"

            val jarTask = target.tasks.getByName("jar") as Jar
            dependsOn(jarTask)

            from(jarTask.archiveFile)
            into("$FABRIC_LOADER_DIRECTORY/mods")
        }

        target.tasks.register("runClient", Exec::class.java) {
            group = DEFAULT_TASK_GROUP
            description = "runs the client"

            workingDir(FABRIC_LOADER_DIRECTORY)
            commandLine = if (CosmosisUtils.ON_WINDOWS) {
                listOf("launcher.bat")
            } else {
                listOf("bash", "launcher.sh")
            }
        }
    }

    private fun setupSetupTasks(cosmosisExtension: CosmosisExtension, target: Project) {
        target.tasks.register("downloadCosmicTools", DownloadCosmicToolsTask::class.java) {
            group = DEFAULT_SETUP_TASK_GROUP
            description = "Downloads CosmicTools"
            cosmicToolsVersion = COSMIC_TOOLS_VERSION
            cosmicToolsDestination = target.file("cosmic-tools")
        }

        target.tasks.register("downloadLoader", DownloadUnzipModLoader::class.java) {
            group = DEFAULT_SETUP_TASK_GROUP
            description = "Downloads and Unzips the FabricLoader"
            outputDirectory = target.layout.projectDirectory
            loaderLink = target.uri(COSMIC_LOADER_URL).toURL()
        }

        target.tasks.register("runCosmicTools", RunCosmicToolsTask::class.java) {
            group = DEFAULT_SETUP_TASK_GROUP
            description = "Runs CosmicTools"
            webDriver = project.property("web_driver") as String
            executionDir = target.layout.projectDirectory.dir("cosmic-tools")

            mustRunAfter("downloadCosmicTools", "downloadLoader")
        }

        target.tasks.register("copyCosmicReach", CopyCosmicReachTask::class.java) {
            group = DEFAULT_SETUP_TASK_GROUP
            description = "Copies CosmicReach from the cosmic-tools directory to the FabricLoader"

            this.cosmosisExtension = cosmosisExtension
            cosmicReachFolder = "cosmic-tools/cosmic-reach/download/"

            mustRunAfter("runCosmicTools")
        }
    }

}
