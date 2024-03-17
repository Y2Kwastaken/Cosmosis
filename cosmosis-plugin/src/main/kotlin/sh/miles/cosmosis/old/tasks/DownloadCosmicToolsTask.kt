package sh.miles.cosmosis.old.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import sh.miles.cosmosis.CosmosisPlugin
import sh.miles.cosmosis.core.CosmosisUtils
import sh.miles.cosmosis.old.utils.COSMIC_TOOLS_GITHUB_RELEASES
import java.io.File
import java.nio.file.Files
import kotlin.io.path.createDirectory


abstract class DownloadCosmicToolsTask : DefaultTask() {

    @get:Input
    abstract var cosmicToolsVersion: String

    @get:OutputDirectory
    abstract var cosmicToolsDestination: File

    @TaskAction
    fun execute() {
        val path = project.layout.buildDirectory.get().asFile.toPath().resolve(cosmicToolsDestination.toPath())
        if (Files.notExists(path)) {
            path.createDirectory()
        }

//        CosmosisPlugin.logger.lifecycle("Downloading CosmicTools from ${COSMIC_TOOLS_GITHUB_RELEASES + cosmicToolsVersion}")
        CosmosisUtils.download("$COSMIC_TOOLS_GITHUB_RELEASES$cosmicToolsVersion/CosmicTools.jar", path.resolve("CosmicTools.jar"))
//        CosmosisPlugin.logger.lifecycle("Finished Downloading CosmicTools")
    }

}
