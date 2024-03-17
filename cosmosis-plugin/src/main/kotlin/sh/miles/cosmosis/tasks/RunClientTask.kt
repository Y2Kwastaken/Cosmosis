package sh.miles.cosmosis.tasks

import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import sh.miles.cosmosis.CosmosisPlugin

abstract class RunClientTask : Exec() {

    @get:Input
    abstract var loaderJar: String

    @get:Input
    abstract var librariesFolder: String

    private val javaLaunchSettings = mutableListOf("-Dfabric.skipMcProvider=true", "-Dfabric.development=true")

    override fun exec() {
        val command = mutableListOf(
            "java",
        )
        command.addAll(javaLaunchSettings)
        command.add("-classpath")
        command.add("cosmic-reach.jar:$loaderJar:$librariesFolder/*")
        command.add("net.fabricmc.loader.launch.knot.KnotClient")

        commandLine = command
        CosmosisPlugin.logger.lifecycle("Launching with arguments: $command")
        super.exec()
    }

    fun addLaunchArgument(vararg args: String) {
        javaLaunchSettings.addAll(args)
    }

}
