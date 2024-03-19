package sh.miles.cosmosis.v2.tasks.copy

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.internal.provider.AbstractProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import sh.miles.cosmosis.core.CosmosisUtils
import java.nio.file.Files

abstract class CopyCosmicReach : DefaultTask() {

    @get:InputDirectory
    abstract var cosmicTools: Directory

    @get:InputDirectory
    abstract var fabricLoader: Directory

    @get:Input
    abstract var finalName: String

    @get:Input
    abstract var version: String

    @TaskAction
    fun exec() {
        val fileVersion = CosmosisUtils.asFileVersion(version)
        val cosmicToolsPath = cosmicTools.asFile.toPath()

        val source = cosmicToolsPath.resolve("cosmic-reach").resolve("download").resolve(fileVersion)
            .resolve("Cosmic Reach-$version.jar")
        val dest = fabricLoader.asFile.toPath().resolve(finalName)

        Files.copy(source, dest)
    }

}
