package sh.miles.cosmosis.tasks

import org.gradle.api.file.Directory
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.process.ExecResult
import org.gradle.work.DisableCachingByDefault
import sh.miles.cosmosis.CosmosisPlugin
import sh.miles.cosmosis.core.CosmicToolsRuntime

@DisableCachingByDefault(because = "Gradle would require more information to cache this task")
abstract class RunCosmicToolsTask : Exec() {

    @get:InputDirectory
    abstract var executionDir: Directory

    @get:Input
    abstract var webDriver: String

    @get:Input
    var arguments: List<String> = listOf()

    override fun exec() {
        val runtime = CosmicToolsRuntime(executionDir.asFile.toPath().resolve("CosmicTools.jar"))
        val arguments = runtime.getArgumentsWithExtra(
            webDriver,
            executionDir.asFile.toPath().toAbsolutePath().toString(),
            arguments
        )
        CosmosisPlugin.logger.lifecycle("Arguments: $arguments")
        commandLine = arguments
        super.exec()
    }
}
