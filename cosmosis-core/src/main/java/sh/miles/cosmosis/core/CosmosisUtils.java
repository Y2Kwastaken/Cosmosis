package sh.miles.cosmosis.core;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class CosmosisUtils {


    public static void download(@NotNull final String link, @NotNull final Path destination) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(link).openStream()); OutputStream fileOutputStream = Files.newOutputStream(destination)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
