package sh.miles.cosmosis.utils

import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.cosmosisDevBundle(
    gameVersion: String,
    gameGroup: String = "finalforeach",
    gameArtifact: String = "cosmicreach",
    loaderGroup: String = DEFAULT_LOADER_GROUP,
    loaderArtifact: String = DEFAULT_LOADER_ARTIFACT,
    loaderVersion: String = DEFAULT_LOADER_VERSION,
    configurationName: String = COSMOSIS_DEVELOPER_BUNDLE,
    configurationAction: ExternalModuleDependency.() -> Unit = {}
): ExternalModuleDependency = configurationName(gameVersion, gameGroup, gameArtifact, loaderGroup, loaderArtifact, loaderVersion, configurationAction)