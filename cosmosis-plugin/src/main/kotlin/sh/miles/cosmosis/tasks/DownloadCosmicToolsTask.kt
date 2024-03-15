package sh.miles.cosmosis.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import sh.miles.cosmosis.core.CosmosisUtils
import sh.miles.cosmosis.utils.COSMIC_TOOLS_GITHUB_RELEASES
import java.nio.file.Path


abstract class DownloadCosmicToolsTask : DefaultTask() {

    @get:Input
    abstract val cosmicToolsVersion: Property<String>

    @get:Input
    abstract val cosmicToolsDestination: DirectoryProperty

    @TaskAction
    fun execute() {
        CosmosisUtils.download(
            COSMIC_TOOLS_GITHUB_RELEASES + cosmicToolsVersion.get(),
            cosmicToolsDestination.get().asFile.toPath()
        )
    }

}
