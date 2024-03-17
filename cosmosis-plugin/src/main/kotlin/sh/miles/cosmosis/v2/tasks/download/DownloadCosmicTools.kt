package sh.miles.cosmosis.v2.tasks.download

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import sh.miles.cosmosis.CosmosisPlugin
import sh.miles.cosmosis.core.CosmosisUtils
import sh.miles.cosmosis.v2.utils.COSMIC_TOOLS_JAR
import sh.miles.cosmosis.v2.utils.COSMIC_TOOLS_URL
import java.nio.file.Files

abstract class DownloadCosmicTools : DefaultTask() {

    @get:Input
    abstract var version: String

    @get:OutputDirectory
    abstract var outputDirectory: Directory

    @TaskAction
    fun exec() {
        val url = "$COSMIC_TOOLS_URL/$version/$COSMIC_TOOLS_JAR"
        CosmosisPlugin.LOGGER.debug("Downloading from {}", url)
        val path = outputDirectory.asFile.toPath()
        CosmosisPlugin.LOGGER.debug("Downloading to {}", path)
        Files.createDirectories(path)
        CosmosisUtils.download(url, path.resolve(COSMIC_TOOLS_JAR))
    }

}
