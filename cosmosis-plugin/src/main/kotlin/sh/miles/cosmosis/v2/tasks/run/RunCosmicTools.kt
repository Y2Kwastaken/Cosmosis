package sh.miles.cosmosis.v2.tasks.run

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.JavaExec
import sh.miles.cosmosis.v2.utils.COSMIC_TOOLS_JAR

abstract class RunCosmicTools : JavaExec() {

    @get:Input
    abstract var driver: String

    override fun exec() {
        classpath = project.files(COSMIC_TOOLS_JAR)
        mainClass.set("sh.miles.cosmictools.CosmicTools")
        args("--driver=$driver")

        super.exec()
    }

}
