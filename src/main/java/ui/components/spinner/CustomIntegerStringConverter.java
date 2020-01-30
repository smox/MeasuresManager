package ui.components.spinner;

import javafx.util.converter.IntegerStringConverter;

public class CustomIntegerStringConverter extends IntegerStringConverter {

    @Override
    public Integer fromString(String s) {
        s = s.replaceAll("[^\\d]", "");
        return super.fromString(s);
    }
}
