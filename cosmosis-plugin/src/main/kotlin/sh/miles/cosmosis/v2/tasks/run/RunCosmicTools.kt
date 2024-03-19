package sh.miles.cosmosis.v2.tasks.run

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.JavaExec
import sh.miles.cosmosis.core.DriverType
import sh.miles.cosmosis.v2.utils.COSMIC_TOOLS_JAR

abstract class RunCosmicTools : JavaExec() {

    @get:Input
    abstract var driver: DriverType

    override fun exec() {
        args("--driver=${driver.asString()}")
        super.exec()
    }

}
