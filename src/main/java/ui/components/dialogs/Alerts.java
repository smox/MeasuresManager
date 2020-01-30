package ui.components.dialogs;

import javafx.scene.control.Alert;

public class Alerts {

    public static void showErrorDialog(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
