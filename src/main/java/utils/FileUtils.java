package utils;

import persistence.model.Measure;
import persistence.service.MeasureService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FileUtils {

    public interface LineToEntityMapper<T> {
        public T mapLine(String line);
    }


    public static Map<String, String> readConfigFile(String path) throws IOException {
        return Files.lines(Path.of(path))
                .filter(l -> !(l.startsWith("#") || l.isBlank())) // Remove all comments and empty lines
                .collect(Collectors.toMap(s -> s.split("=")[0],
                        o -> o.split("=")[1].replaceAll("\"", "")));
    }

    public static <T> List<T> readSeedFile(String path, LineToEntityMapper<T> lineToEntityMapper) throws IOException {
        return Files.lines(Path.of(path))
                .filter(l -> !(l.trim().startsWith("#") || l.isBlank())) // Remove all comments and empty lines
                .map(lineToEntityMapper::mapLine)
                .collect(Collectors.toList());
    }

    public static Long parseLong(String s) {
        String parsedString = parseString(s);
        if(parsedString != null) {
            return Long.valueOf(parsedString);
        }

        return null;
    }

    public static String parseString(String s) {
        if(StringUtils.isNotBlank(s)) {
            return s.trim();
        }

        return null;
    }
}
