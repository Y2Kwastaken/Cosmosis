package sh.miles.cosmosis.v2.tasks.run

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.JavaExec
import sh.miles.cosmosis.CosmosisPlugin

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
        mainClass.set("net.fabricmc.loader.launch.knot.KnotClient")
        CosmosisPlugin.LOGGER.debug("main-class: {}", mainClass)
        super.exec()
    }

}
