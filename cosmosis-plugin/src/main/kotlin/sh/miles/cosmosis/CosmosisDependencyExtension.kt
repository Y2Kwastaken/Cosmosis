package sh.miles.cosmosis

import org.gradle.api.artifacts.dsl.DependencyHandler
import sh.miles.cosmosis.utils.DEFAULT_LOADER_ARTIFACT
import sh.miles.cosmosis.utils.DEFAULT_LOADER_GROUP
import sh.miles.cosmosis.utils.DEFAULT_LOADER_VERSION

abstract class CosmosisDependencyExtension(private val dependencies: DependencyHandler) {

    @JvmOverloads
    fun cosmosisDevBundle(
        gameVersion: String,
        gameGroup: String = "finalforeach",
        gameArtifact: String = "cosmicreach",
        loaderGroup: String = DEFAULT_LOADER_GROUP,
        loaderArtifact: String = DEFAULT_LOADER_ARTIFACT,
        loaderVersion: String = DEFAULT_LOADER_VERSION
    ) {
        dependencies.add("compileOnly", "$gameGroup:$gameArtifact:$gameVersion")
        dependencies.add("compileOnly", "$loaderGroup:$loaderArtifact:$loaderVersion")
    }

}
