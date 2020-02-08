package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;


public class FileUtils {

    public static Map<String, String> readConfigFile(String path) throws IOException {
        return Files.lines(Path.of(path))
                .filter(l -> !(l.startsWith("#") || l.isBlank())) // Remove all comments and empty lines
                .collect(Collectors.toMap(s -> s.split("=")[0],
                        o -> o.split("=")[1].replaceAll("\"", "")));
    }
}
