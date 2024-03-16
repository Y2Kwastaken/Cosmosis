dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")

    compileOnly("org.jetbrains:annotations-java5:24.0.1")
}

tasks.withType<Javadoc>() {
    options.quiet()
    val options = this.options
    if (options !is StandardJavadocDocletOptions) return@withType
    options.addStringOption("Xdoclint:none", "-quiet")
}
