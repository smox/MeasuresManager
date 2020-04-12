package ui.components.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import persistence.model.Container;
import persistence.service.ContainerService;
import ui.converter.CheckedIntegerStringConverter;
import utils.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageContainers extends Dialog<Container> implements Initializable {

    private static ContainerService containerService = new ContainerService();

    @FXML
    TableView<Container> tblViewContainers;

    @FXML
    TableColumn<Container, Integer> tblColCapacity;

    ObservableList<Container> observableContainersList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTableColumns();
        loadContainersIntoTable();
    }

    private void initializeTableColumns() {
        // Necessary to use an integer value
        tblColCapacity.setCellFactory(TextFieldTableCell.forTableColumn(new CheckedIntegerStringConverter()));
    }

    private void loadContainersIntoTable() {
        observableContainersList = FXCollections.observableList(containerService.findAll());
        tblViewContainers.setItems(observableContainersList);
    }

    public void editDesignation(CellEditEvent<Container, String> cellEditEvent) {

        String oldDesignation = cellEditEvent.getOldValue();
        String newDesignation = cellEditEvent.getNewValue();

        if(StringUtils.isNotBlank(newDesignation)) {
            if(StringUtils.isNotEqual(oldDesignation, newDesignation)) {
                newDesignation = newDesignation.trim();
                Container container = containerService.findByDesignation(newDesignation);
                if(container == null) {
                    container = cellEditEvent.getRowValue();
                    container.setDesignation(newDesignation);
                    containerService.update(container);
                } else {
                    Alerts.showErrorDialog("Bezeichnung existiert bereits!",
                            "Bitte wähle eine Bezeichnung die es noch nicht gibt!");
                }
            }
        } else {
            Alerts.showErrorDialog("Neue Bezeichnung ist leer!",
                    "Bitte gib eine gültige Bezeichnung ein!");
        }

        loadContainersIntoTable();
    }

    public void editCapacity(CellEditEvent<Container, Integer> cellEditEvent) {

        Integer oldCapacity = cellEditEvent.getOldValue();
        Integer newCapacity = cellEditEvent.getNewValue();

        if(newCapacity != null) {
            if(oldCapacity.intValue() != newCapacity.intValue()) {
            if(newCapacity > 0) {
                Container container = cellEditEvent.getRowValue();
                container.setCapacity(newCapacity);
                containerService.update(container);
            } else {
                Alerts.showErrorDialog("Menge zu klein!",
                        "Bitte gibt einen Wert ein der größer als 0 ist!");
            }
            }
        } else {
            Alerts.showErrorDialog("Neue Menge ist leer!", "Bitte gib eine gültige Menge ein!");
        }

        loadContainersIntoTable();
    }
}
