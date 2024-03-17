package sh.miles.cosmosis.core;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record CosmicToolsRuntime(@NotNull Path jarPath) {

    public List<String> getRequiredArguments(String jarBin, String driver, String relative) {
        return Arrays.stream(new String[]{
                jarBin, "-jar", jarPath.normalize().toAbsolutePath().toString(), "--driver=" + driver, "--cwd=" + relative
        }).collect(Collectors.toList());
    }

    public List<String> getArgumentsWithExtra(String jarBin, String driver, String relative, List<String> arguments) {
        var list = getRequiredArguments(jarBin, driver, relative);
        list.addAll(arguments);
        return list;
    }

}
