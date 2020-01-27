package ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import persistence.model.Wine;
import ui.component.listview.WineCellView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    private ObservableList<Wine> observableWineList = FXCollections.observableArrayList();

    @FXML
    private ListView<Wine> lvWines;

    @FXML
    private void closeWindowEvent(ActionEvent event) {
        //TODO Backup current database
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadWinesIntoObservableWineList();
        lvWines.setItems(observableWineList);
        lvWines.setCellFactory(lvWines -> new WineCellView());
    }

    private void loadWinesIntoObservableWineList() {
        observableWineList.addAll(
                createWine(1, "GV Sandgrube"),
                createWine(2, "GV - Der Junge"),
                createWine(3, "Riesling"),
                createWine(4, "GV - Alte Rebe")
        );
    }

    private Wine createWine(int id, String name) {

        Wine wine = new Wine();
        wine.setId((long) id);
        wine.setName(name);

        return wine;
    }
}
