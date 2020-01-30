package ui.components.spinner;

import javafx.scene.control.SpinnerValueFactory;

public class CustomIntegerSpinnerValueFactory extends SpinnerValueFactory.IntegerSpinnerValueFactory {


    public CustomIntegerSpinnerValueFactory(int from, int to) {
        super(from, to);
        setConverter(new CustomIntegerStringConverter());
    }

    public CustomIntegerSpinnerValueFactory(int from, int to, int initialValue) {
        super(from, to, initialValue);
        setConverter(new CustomIntegerStringConverter());
    }

    public CustomIntegerSpinnerValueFactory(int from, int to, int initialValue, int stepSize) {
        super(from, to, initialValue, stepSize);
        setConverter(new CustomIntegerStringConverter());
    }
}
