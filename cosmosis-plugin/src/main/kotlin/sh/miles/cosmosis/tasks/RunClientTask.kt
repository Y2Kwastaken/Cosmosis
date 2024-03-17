package sh.miles.cosmosis.tasks

import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import sh.miles.cosmosis.core.CosmosisUtils
import sh.miles.cosmosis.utils.FABRIC_LOADER_DIRECTORY

abstract class RunClientTask : Exec() {

    @get:Input
    abstract var launcherFileGeneric: String

    override fun exec() {
        workingDir(FABRIC_LOADER_DIRECTORY)
        commandLine = if (CosmosisUtils.ON_WINDOWS) {
            listOf("$launcherFileGeneric.bat")
        } else {
            listOf("bash", "$launcherFileGeneric.sh")
        }
        super.exec()
    }

}
