package ui.components.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import org.jboss.jandex.Main;
import persistence.model.Container;
import persistence.model.ContainerType;
import persistence.model.Location;
import ui.MainWindow;
import ui.converter.CheckedIntegerStringConverter;
import ui.converter.ContainerTypeConverter;
import utils.ButtonUtils;
import utils.CollectionUtils;
import utils.StringUtils;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageContainers extends Dialog<Container> implements Initializable {

    @FXML
    TableView<Container> tblViewContainers;

    @FXML
    TableColumn<Container, Integer> tblColCapacity;

    @FXML
    TableColumn<Container, ContainerType> tblColContainerType;

    ObservableList<Container> observableContainersList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTableColumns();
        loadContainersIntoTable();
    }

    private void initializeTableColumns() {
        tblColCapacity.setCellFactory(TextFieldTableCell.forTableColumn(new CheckedIntegerStringConverter()));
        //tblColContainerType.setCellFactory(TextFieldTableCell.forTableColumn(new ContainerTypeConverter()));
    }

    private void loadContainersIntoTable() {
        observableContainersList = FXCollections.observableList(MainWindow.containerService.findAll());
        tblViewContainers.setItems(observableContainersList);
    }

    public void editDesignation(CellEditEvent<Container, String> cellEditEvent) {

        String oldDesignation = cellEditEvent.getOldValue();
        String newDesignation = cellEditEvent.getNewValue();

        if(StringUtils.isNotBlank(newDesignation)) {
            if(StringUtils.isNotEqual(oldDesignation, newDesignation)) {
                newDesignation = newDesignation.trim();
                Container container = MainWindow.containerService.findByDesignation(newDesignation);
                if(container == null) {
                    container = cellEditEvent.getRowValue();
                    container.setDesignation(newDesignation);
                    MainWindow.containerService.update(container);
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
                MainWindow.containerService.update(container);
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

    public void editContainerType(CellEditEvent<Container, ContainerType> cellEditEvent) {

        ContainerType oldValue = cellEditEvent.getOldValue();
        ContainerType newValue = cellEditEvent.getNewValue();

        if(StringUtils.isNotBlank(newValue.getName())) {
            if(StringUtils.isNotEqual(oldValue.getName(), newValue.getName())) {

                newValue.setName(newValue.getName().trim());
                ContainerType containerType = MainWindow.containerTypeService.findByName(newValue.getName());

                if(containerType == null) {

                    containerType = new ContainerType();
                    containerType.setName(newValue.getName());
                    MainWindow.containerTypeService.persist(containerType);
                    containerType = MainWindow.containerTypeService.findByName(newValue.getName());

                }

                Container container = cellEditEvent.getRowValue();
                container.setContainerType(containerType);
                MainWindow.containerService.update(container);
            }
        } else {
            Alerts.showErrorDialog("Neue Bezeichnung ist leer!",
                    "Bitte gib eine gültige Bezeichnung ein!");
        }

        loadContainersIntoTable();
    }

    public void editLocation(CellEditEvent<Container, Location> cellEditEvent) {

        Location oldValue = cellEditEvent.getOldValue();
        Location newValue = cellEditEvent.getNewValue();

        if(StringUtils.isNotBlank(newValue.getName())) {
            if(StringUtils.isNotEqual(oldValue.getName(), newValue.getName())) {

                newValue.setName(newValue.getName().trim());
                Location location = MainWindow.locationService.findByName(newValue.getName());

                if(location == null) {

                    location = new Location();
                    location.setName(newValue.getName());
                    MainWindow.locationService.persist(location);
                    location = MainWindow.locationService.findByName(newValue.getName());

                }

                Container container = cellEditEvent.getRowValue();
                container.setLocation(location);
                MainWindow.containerService.update(container);
            }
        } else {
            Alerts.showErrorDialog("Ort ist leer!", "Bitte gib einen gültigen Ort ein!");
        }

        loadContainersIntoTable();
    }

    public void addContainer(ActionEvent actionEvent) {

    }

    public void removeContainer(ActionEvent actionEvent) {
        Container selectedItem = tblViewContainers.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            Optional<ButtonType> buttonType = Alerts.showYesNoDialog("Wirklich löschen?",
                    "Sind Sie sich sicher, dass "+ selectedItem.getDesignation() +" wirklich gelöscht werden soll?",
                    "Die Änderung kann nicht rückgängig gemacht werden!");

            if(ButtonUtils.isButton(buttonType, ButtonType.YES)){
                MainWindow.containerService.delete(selectedItem);
                loadContainersIntoTable();
            }
        } else {
            Alerts.showErrorDialog("Kein Behälter ausgewählt!", "Bitte wähle einen Behälter aus!");
        }
    }
}
