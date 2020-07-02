package utils;

public class StringUtils {

    public static boolean isNotBlank(String stringToTest) {
        return !isBlank(stringToTest);
    }

    public static boolean isBlank(String stringToTest) {
        return stringToTest == null || stringToTest.trim().isBlank();
    }

    public static boolean isEqual(String oldDesignation, String newDesignation) {
        return (oldDesignation == null && newDesignation == null)
                || (oldDesignation != null && newDesignation != null && oldDesignation.trim().equals(newDesignation.trim()));
    }

    public static boolean isNotEqual(String oldDesignation, String newDesignation) {
        return !isEqual(oldDesignation, newDesignation);
    }
}
