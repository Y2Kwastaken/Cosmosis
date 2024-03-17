package sh.miles.cosmosis

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.maven
import sh.miles.cosmosis.utils.DEFAULT_LOADER_ARTIFACT
import sh.miles.cosmosis.utils.DEFAULT_LOADER_GROUP
import sh.miles.cosmosis.utils.DEFAULT_LOADER_VERSION

abstract class CosmosisExtension(
    private val repositories: RepositoryHandler,
    private val dependencies: DependencyHandler
) {
    abstract val version: Property<String>
    abstract val javaBin: Property<String>

    fun repoBundle() {
        repositories.add(repositories.mavenLocal())
        repositories.add(repositories.mavenCentral())
        repositories.add(repositories.maven("https://repo.spongepowered.org/maven/"))
        repositories.add(repositories.maven("https://maven.fabricmc.net/"))
    }

    @JvmOverloads
    fun devBundle(
        gameGroup: String = "finalforeach",
        gameArtifact: String = "cosmicreach",
        loaderGroup: String = DEFAULT_LOADER_GROUP,
        loaderArtifact: String = DEFAULT_LOADER_ARTIFACT,
        loaderVersion: String = DEFAULT_LOADER_VERSION
    ) {
        dependencies.add("compileOnly", "$gameGroup:$gameArtifact:${version.get()}")
        dependencies.add("compileOnly", "$loaderGroup:$loaderArtifact:$loaderVersion")
    }
}
