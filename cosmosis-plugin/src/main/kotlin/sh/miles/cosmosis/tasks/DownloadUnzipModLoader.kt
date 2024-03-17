package sh.miles.cosmosis.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import sh.miles.cosmosis.CosmosisPlugin
import sh.miles.cosmosis.core.CosmosisUtils
import sh.miles.cosmosis.utils.FABRIC_LOADER_DIRECTORY
import sh.miles.cosmosis.utils.FABRIC_LOADER_ZIP_NAME
import java.net.URL
import java.nio.file.Files

abstract class DownloadUnzipModLoader : DefaultTask() {

    @get:OutputDirectory
    abstract var outputDirectory: Directory

    @get:Input
    abstract var loaderLink: URL

    @TaskAction
    fun execute() {
        val downloadDirectory = outputDirectory.asFile.toPath()
        CosmosisPlugin.logger.lifecycle("Downloading a Fabric Mod Loader $loaderLink")
        CosmosisUtils.download(loaderLink, downloadDirectory.resolve(FABRIC_LOADER_ZIP_NAME))
        CosmosisPlugin.logger.lifecycle("Finished Downloading the Fabric Mod Loader from $loaderLink")
        CosmosisPlugin.logger.lifecycle("Attempting to unzip loader")
        CosmosisUtils.unzip(
            downloadDirectory.resolve(FABRIC_LOADER_ZIP_NAME),
            downloadDirectory.resolve(FABRIC_LOADER_DIRECTORY)
        ) { true }
        CosmosisPlugin.logger.lifecycle("Unzipped loader successfully")
        Files.delete(downloadDirectory.resolve(FABRIC_LOADER_ZIP_NAME))
    }

}
