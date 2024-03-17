package sh.miles.cosmosis.old.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import sh.miles.cosmosis.old.CosmosisExtension
import sh.miles.cosmosis.core.CosmosisUtils
import sh.miles.cosmosis.old.utils.FABRIC_LOADER_DIRECTORY
import java.nio.file.Files
import java.nio.file.StandardCopyOption

abstract class CopyCosmicReachTask : DefaultTask() {

    @get:Input
    abstract var cosmosisExtension: CosmosisExtension

    @get:Input
    abstract var cosmicReachFolder: String

    @TaskAction
    fun execute() {
        val topFolder = project.layout.projectDirectory

        Files.copy(
            topFolder.dir(cosmicReachFolder).dir(CosmosisUtils.asFileVersion(cosmosisExtension.version.get()))
                .file("Cosmic Reach-${cosmosisExtension.version.get()}.jar").asFile.toPath(),
            topFolder.dir(FABRIC_LOADER_DIRECTORY).file("cosmic-reach.jar").asFile.toPath(),
            StandardCopyOption.REPLACE_EXISTING
        )
    }
}
