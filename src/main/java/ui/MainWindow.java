package ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainWindow {


    @FXML
    private void closeWindowEvent(ActionEvent event) {
        //TODO Backup current database
        System.exit(0);
    }

}
