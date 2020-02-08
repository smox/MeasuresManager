package utils;

import exceptions.WrongConfigurationException;

public class ExceptionUtils {

    public static String setOrThrow(String string, String errorMessage) {
        if(StringUtils.isNotBlank(string)) {
            return string;
        }
        if(StringUtils.isBlank(errorMessage)) {
            throw new WrongConfigurationException(string+" konnte nicht gesetzt werden, weil kein Wert gefunden wurde.");
        }
        throw new WrongConfigurationException(errorMessage+": "+string);
    }
}
