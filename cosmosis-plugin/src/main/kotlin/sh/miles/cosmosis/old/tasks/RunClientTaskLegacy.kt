package sh.miles.cosmosis.old.tasks

import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import sh.miles.cosmosis.core.CosmosisUtils
import sh.miles.cosmosis.old.utils.FABRIC_LOADER_DIRECTORY

abstract class RunClientTaskLegacy : Exec() {

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
