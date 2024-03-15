package sh.miles.cosmosis.core;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record CosmicToolsRuntime(@NotNull Path jarPath) {

    public int runStandard(@NotNull final String driver) {
        try {
            return run("--driver=%s".formatted(driver)).waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Process run(@NotNull final String... args) {
        final List<String> arguments = new ArrayList<>();
        arguments.add("java");
        arguments.add("-jar");
        arguments.add(jarPath.normalize().toAbsolutePath().toString());
        arguments.addAll(Arrays.asList(args));

        try {
            return new ProcessBuilder()
                    .command(arguments)
                    .inheritIO()
                    .start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
