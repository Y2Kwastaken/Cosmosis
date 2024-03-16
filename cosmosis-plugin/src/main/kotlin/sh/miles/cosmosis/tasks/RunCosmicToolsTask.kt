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

    private val execResult = objectFactory.property(ExecResult::class.java)

    override fun exec() {
//        val clazz = AbstractExecTask::class.java
//        val execSpecField = clazz.getDeclaredField("execSpec")
//        val execSpec: ExecSpec
//        if (execSpecField.trySetAccessible()) {
//            execSpec = execSpecField.get(this) as ExecSpec
//        } else {
//            throw IllegalStateException("Unable to get access to ${execSpecField.name} from ${AbstractExecTask::class.java.name}")
//        }

        CosmosisPlugin.logger.lifecycle("Executing")
        val runtime = CosmicToolsRuntime(executionDir.asFile.toPath().resolve("CosmicTools.jar"))
        CosmosisPlugin.logger.lifecycle(runtime.getArgumentsWithExtra(webDriver, arguments).toString())
        commandLine = runtime.getArgumentsWithExtra(webDriver, arguments)
//        val action = execActionFactory.newExecAction()
//        execSpec.copyTo(action)
//        action.commandLine.forEach { CosmosisPlugin.logger.lifecycle(it) }
//        execResult.set(action.execute())
        super.exec()
    }

//    override fun getExecutionResult(): Provider<ExecResult> {
//        return execResult
//    }


}
