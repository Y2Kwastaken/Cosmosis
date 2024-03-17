package sh.miles.cosmosis.v2.tasks.download

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import sh.miles.cosmosis.CosmosisPlugin
import sh.miles.cosmosis.core.CosmosisUtils
import java.nio.file.Files

abstract class SetupFabricLoader : DefaultTask() {

    @get:Input
    abstract var version: String

    @get:Input
    abstract var url: String

    @get:Input
    abstract var zipName: String

    @get:OutputDirectory
    abstract var outputDirectory: Directory

    @TaskAction
    fun exec() {
        val output = outputDirectory.asFile.toPath()
        CosmosisPlugin.LOGGER.debug("Output directory at {}", output)
        Files.createDirectories(output)

        val zipFile = output.resolve(zipName)
        CosmosisPlugin.LOGGER.debug("ZipFile at {}", zipFile)

        CosmosisUtils.download(url, zipFile)
        CosmosisUtils.unzip(zipFile, output) { true }
        Files.delete(zipFile)
    }

}
