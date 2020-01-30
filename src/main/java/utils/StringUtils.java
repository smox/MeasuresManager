package utils;

public class StringUtils {

    public static boolean isNotBlank(String stringToTest) {
        return !isBlank(stringToTest);
    }

    public static boolean isBlank(String stringToTest) {
        return stringToTest == null || stringToTest.trim().isBlank();
    }
}
