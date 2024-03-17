package sh.miles.cosmosis

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import sh.miles.cosmosis.v2.tasks.download.DownloadCosmicTools
import sh.miles.cosmosis.v2.tasks.download.SetupFabricLoader
import sh.miles.cosmosis.v2.tasks.run.RunClientTask
import sh.miles.cosmosis.v2.tasks.run.RunCosmicTools
import sh.miles.cosmosis.v2.utils.COSMIC_TOOLS_FOLDER
import sh.miles.cosmosis.v2.utils.COSMIC_TOOLS_VERSION
import sh.miles.cosmosis.v2.utils.COSMOSIS_CATEGORY
import sh.miles.cosmosis.v2.utils.COSMOSIS_SETUP_CATEGORY
import sh.miles.cosmosis.v2.utils.FABRIC_LOADER_ARTIFACT
import sh.miles.cosmosis.v2.utils.FABRIC_LOADER_FOLDER
import sh.miles.cosmosis.v2.utils.FABRIC_LOADER_JAR
import sh.miles.cosmosis.v2.utils.FABRIC_LOADER_URL
import sh.miles.cosmosis.v2.utils.FABRIC_LOADER_VERSION

class CosmosisPlugin : Plugin<Project> {

    companion object {
        val LOGGER = Logging.getLogger(CosmosisPlugin::class.java)!!
    }

    override fun apply(target: Project) {
        cosmosis(target)
        setup(target)
    }

    private fun cosmosis(target: Project) {
        target.tasks.register("runClient", RunClientTask::class.java) {
            group = COSMOSIS_CATEGORY
            loaderJar = FABRIC_LOADER_JAR
            librariesFolder = "libs"
        }
    }

    private fun setup(target: Project) {
        target.tasks.register("downloadCosmicTools", DownloadCosmicTools::class.java) {
            group = COSMOSIS_SETUP_CATEGORY
            version = COSMIC_TOOLS_VERSION
            outputDirectory = target.layout.projectDirectory.dir(COSMIC_TOOLS_FOLDER)
        }

        target.tasks.register("setupFabricLoader", SetupFabricLoader::class.java) {
            group = COSMOSIS_SETUP_CATEGORY
            version = FABRIC_LOADER_VERSION
            url = FABRIC_LOADER_URL
            zipName = "$FABRIC_LOADER_ARTIFACT-$FABRIC_LOADER_VERSION.zip"
            outputDirectory = target.layout.projectDirectory.dir(FABRIC_LOADER_FOLDER)
        }

        target.tasks.register("runCosmicTools", RunCosmicTools::class.java) {
            group = COSMOSIS_SETUP_CATEGORY
            workingDir(COSMIC_TOOLS_FOLDER)
            driver = "firefox"
        }
    }

    //    companion object {
//        val logger: Logger = Logging.getLogger(CosmosisPlugin::class.java)!!
//    }
//
//    override fun apply(target: Project) {
//        val cosmosisExtension = target.extensions.create(
//            COSMOSIS_EXTENSION, CosmosisExtension::class.java, target.dependencies
//        )
//
//        cosmosisExtension.javaBin.set("java")
//
//        setupSetupTasks(cosmosisExtension, target)
//        setupCosmosisTasks(cosmosisExtension, target)
//    }
//
//    private fun setupCosmosisTasks(cosmosisExtension: CosmosisExtension, target: Project) {
//        target.tasks.register("setup", DefaultTask::class.java) {
//            group = DEFAULT_TASK_GROUP
//
//            val tasks = target.tasks
//            dependsOn(tasks.getByName("downloadCosmicTools"))
//            dependsOn(tasks.getByName("downloadLoader"))
//            dependsOn(tasks.getByName("runCosmicTools"))
//            dependsOn(tasks.getByName("copyCosmicReach"))
//        }
//
//        target.tasks.register("cleanLoader", Delete::class.java) {
//            group = DEFAULT_TASK_GROUP
//            description = "Deletes the current fabric loader"
//            delete(FABRIC_LOADER_DIRECTORY, FABRIC_LOADER_DIRECTORY)
//
//            mustRunAfter(target.tasks.getByName("clean"))
//        }
//
//        target.tasks.register("copyJarToMods", Copy::class.java) {
//            group = DEFAULT_TASK_GROUP
//            description = "Copies the output jar into the mods folder"
//
//            val jarTask = target.tasks.getByName("jar") as Jar
//            dependsOn(jarTask)
//
//            from(jarTask.archiveFile)
//            into("$FABRIC_LOADER_DIRECTORY/mods")
//        }
//
//        target.tasks.register("runClient", RunClientTask::class.java) {
//            group = DEFAULT_TASK_GROUP
//            description = "runs the client"
//
//            this.cosmosisExtension = cosmosisExtension
//            loaderJar = "$DEFAULT_LOADER_ARTIFACT-$DEFAULT_LOADER_VERSION.jar"
//            librariesFolder = "libs"
//
//            workingDir(FABRIC_LOADER_DIRECTORY)
//        }
//
//        target.tasks.register("runClientLegacy", RunClientTaskLegacy::class.java) {
//            group = DEFAULT_TASK_GROUP
//            description = "runs the client through a launcher shell or batch file"
//
//            launcherFileGeneric = "launcher"
//        }
//    }
//
//    private fun setupSetupTasks(cosmosisExtension: CosmosisExtension, target: Project) {
//        target.tasks.register("downloadCosmicTools", DownloadCosmicToolsTask::class.java) {
//            group = DEFAULT_SETUP_TASK_GROUP
//            description = "Downloads CosmicTools"
//            cosmicToolsVersion = COSMIC_TOOLS_VERSION
//            cosmicToolsDestination = target.file("cosmic-tools")
//        }
//
//        target.tasks.register("downloadLoader", DownloadUnzipModLoader::class.java) {
//            group = DEFAULT_SETUP_TASK_GROUP
//            description = "Downloads and Unzips the FabricLoader"
//            outputDirectory = target.layout.projectDirectory
//            loaderLink = target.uri(COSMIC_LOADER_URL).toURL()
//        }
//
//        target.tasks.register("runCosmicTools", RunCosmicToolsTask::class.java) {
//            group = DEFAULT_SETUP_TASK_GROUP
//            description = "Runs CosmicTools"
//
//            this.cosmosisExtension = cosmosisExtension
//            webDriver = project.property("web_driver") as String
//            executionDir = target.layout.projectDirectory.dir("cosmic-tools")
//
//            mustRunAfter("downloadCosmicTools", "downloadLoader")
//        }
//
//        target.tasks.register("copyCosmicReach", CopyCosmicReachTask::class.java) {
//            group = DEFAULT_SETUP_TASK_GROUP
//            description = "Copies CosmicReach from the cosmic-tools directory to the FabricLoader"
//
//            this.cosmosisExtension = cosmosisExtension
//            cosmicReachFolder = "cosmic-tools/cosmic-reach/download/"
//
//            mustRunAfter("runCosmicTools")
//        }
//    }

}
