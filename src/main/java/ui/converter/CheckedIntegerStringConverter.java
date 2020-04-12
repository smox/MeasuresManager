package ui.converter;

import javafx.util.converter.IntegerStringConverter;
import ui.components.dialogs.Alerts;

public class CheckedIntegerStringConverter extends IntegerStringConverter {

    @Override
    public Integer fromString(String s) {
        try {
            return super.fromString(s);
        } catch (NumberFormatException nfe) {
            // TODO LOGGER
            System.err.println("CheckedIntegerStringConverter: Cannot parse "+s+" to an integer");
        }
        return null;
    }
}
