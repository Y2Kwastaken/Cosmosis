package sh.miles.cosmosis.v2.tasks.run

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.JavaExec
import sh.miles.cosmosis.CosmosisPlugin
import sh.miles.cosmosis.v2.utils.PropertyHacker

abstract class RunClientTask : JavaExec() {

    @get:Input
    abstract var loaderJar: String

    @get:Input
    abstract var librariesFolder: String

    override fun exec() {
        jvmArgs("-Dfabric.skipMcProvider=true", "-Dfabric.development=true")
        CosmosisPlugin.LOGGER.debug("JVM Arguments: {}", jvmArgs)
        classpath("cosmic-reach.jar", loaderJar, librariesFolder)
        CosmosisPlugin.LOGGER.debug("classpath: {}", classpath)
        hackFinalizer(mainClass)
        CosmosisPlugin.LOGGER.debug("main-class: {}", mainClass)
        super.exec()
    }

    private fun <T> hackFinalizer(property: Property<T>) {
        val hacker = PropertyHacker(objectFactory)
        val finalizedState = hacker.hackFinalizationState()
        val supplier = hacker.useFinalizedState(finalizedState, "net.fabricmc.loader.launch.knot.KnotClient")
        hacker.setStateAndValue(property, finalizedState, supplier)
    }

}
