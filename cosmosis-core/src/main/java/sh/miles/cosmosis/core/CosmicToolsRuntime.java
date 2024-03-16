package sh.miles.cosmosis.core;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record CosmicToolsRuntime(@NotNull Path jarPath) {

    public List<String> getRequiredArguments(String driver) {
        return Arrays.stream(new String[]{
                "java", "-jar", jarPath.normalize().toAbsolutePath().toString(), "--driver=" + driver
        }).collect(Collectors.toList());
    }

    public List<String> getArgumentsWithExtra(String driver, List<String> arguments) {
        var list = getRequiredArguments(driver);
        list.addAll(arguments);
        return list;
    }

}
