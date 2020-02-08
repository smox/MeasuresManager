package ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import persistence.model.Entry;
import persistence.model.Setting;
import persistence.model.Wine;
import persistence.service.*;
import ui.components.dialogs.AddEntryDialog;
import ui.components.dialogs.Alerts;
import ui.components.dialogs.EditEntryDialog;
import ui.components.dialogs.GenericAddDialog;
import ui.components.listview.WineCellView;
import ui.components.listview.actions.WineDeleteAction;
import ui.components.listview.actions.WineModifyAction;
import ui.components.listview.actions.WineSelectAction;
import utils.ButtonUtils;
import utils.CollectionUtils;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    public static WineService wineService = new WineService();
    public static EntryService entryService = new EntryService();
    public static SettingService settingService = new SettingService();
    public static MeasureService measureService = new MeasureService();
    public static ContainerAmountMapService containerAmountMapService = new ContainerAmountMapService();

    public static Setting setting;

    private ObservableList<Wine> observableWineList = FXCollections.observableArrayList();
    private ObservableList<Entry> observableEntryList = FXCollections.observableArrayList();

    @FXML
    private TableView<Entry> tblViewMeasures;

    @FXML
    private TableColumn<Entry, Date> tblViewColRealizedAt;

    @FXML
    private ListView<Wine> lvWines;

    @FXML
    private Button btnEditEntry;

    @FXML
    private Button btnDeleteEntry;

    @FXML
    private void closeWindowEvent(ActionEvent event) {
        //TODO Backup current database
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setting = settingService.findCurrent();

        loadWinesIntoObservableWineList();
        lvWines.setItems(observableWineList);

        WineDeleteAction wineDeleteAction = this::deleteWineAndRefreshList;
        WineModifyAction wineModifyAction = this::updateWineAndRefreshList;
        WineSelectAction wineSelectAction = this::loadEntriesIntoTableView;

        addTableViewRowListener();
        addButtonIcons();
        addButtonActions();

        lvWines.setCellFactory(lvWines -> new WineCellView(wineDeleteAction, wineModifyAction, wineSelectAction));

        tblViewColRealizedAt.setCellFactory(column -> createEntryDateTableCellFactory());
    }

    private void addTableViewRowListener() {
        tblViewMeasures.setRowFactory( tv -> {
            TableRow<Entry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Entry entry = row.getItem();
                    openEditEntryView(entry);
                }
            });
            return row ;
        });
    }


    private void addButtonActions() {
        btnEditEntry.setOnAction(actionEvent -> openEditEntryView(null));

        btnDeleteEntry.setOnAction(actionEvent -> {
            var selectedEntries = tblViewMeasures.getSelectionModel().getSelectedItems();
            var selectedWines = lvWines.getSelectionModel().getSelectedItems();

            if(CollectionUtils.isNotEmpty(selectedEntries)) {
                Entry entryToDelete = selectedEntries.get(0);
                entryService.delete(entryToDelete.getId());
                loadEntriesIntoTableView(selectedWines.get(0), null);
            } else {
                Alerts.showErrorDialog("Kein Eintrag ausgewählt!",
                        "Bitte wähle einen Eintrag aus den du bearbeiten möchtest!");
            }
        });
    }

    private void openEditEntryView(Entry entry) {

    /*  TODO Eventuell mit AddEntryDialog zusammenlegen? Generalisieren der Views und Aufrufe?
        TODO Eventuell Mehrfachauswahl möglich? */

        ObservableList<Entry> selectedEntries = FXCollections.observableArrayList();

        if(entry == null) {
            selectedEntries = tblViewMeasures.getSelectionModel().getSelectedItems();
        } else {
            selectedEntries.add(entry);
        }

        var selectedWines = lvWines.getSelectionModel().getSelectedItems();

        if(CollectionUtils.isNotEmpty(selectedEntries)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/components/dialogs/EditEntryDialog.fxml"));
                Parent parent = fxmlLoader.load();
                EditEntryDialog editEntryDialog = fxmlLoader.getController();
                editEntryDialog.setEntry(selectedEntries.get(0));
                editEntryDialog.setParent(this);
                editEntryDialog.setAddEntryDialogSuccessAction((e) ->
                        loadEntriesIntoTableView(selectedWines.get(0), e));

                editEntryDialog.initializeFields();

                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Eintrag bearbeiten");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                // TODO Logging
                e.printStackTrace();
            }
        } else {
            Alerts.showErrorDialog("Kein Eintrag ausgewählt!",
                    "Bitte wähle einen Eintrag aus den du bearbeiten möchtest!");
        }
    }

    private void addButtonIcons() {
        ButtonUtils.addIconToButton(btnEditEntry, "/assets/edit.png");
        ButtonUtils.addIconToButton(btnDeleteEntry, "/assets/delete.png");
    }

    @FXML
    void onOpenAddEntryDialog(ActionEvent event) throws IOException {

        // TODO Eventuell Mehrfachauswahl möglich?
        var selectedWines = lvWines.getSelectionModel().getSelectedItems();

        if(CollectionUtils.isNotEmpty(selectedWines)) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/components/dialogs/AddEntryDialog.fxml"));
            Parent parent = fxmlLoader.load();
            AddEntryDialog addEntryDialog = fxmlLoader.getController();
            addEntryDialog.setParent(this);
            addEntryDialog.setAddEntryDialogSuccessAction((entry) -> loadEntriesIntoTableView(selectedWines.get(0), entry));

            addEntryDialog.setWine(selectedWines.get(0));
            addEntryDialog.setYear(setting.getCurrentYear());

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Neuen Eintrag hinzufügen");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } else {
            Alerts.showErrorDialog(
                    "Fehler bei der Weinauswahl",
                    "Es muss ein Wein ausgewählt sein um einen Eintrag hinzuzufügen");
        }
    }

    private TableCell<Entry, Date> createEntryDateTableCellFactory() {
        return new TableCell<>() {
            private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

            @Override
            protected void updateItem(Date item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setText(null);
                }
                else {
                    setText(format.format(item));
                }
            }
        };
    }

    private void updateWineAndRefreshList(Wine wine, String newName) {
        newName = newName.trim();
        var existingWine = wineService.findByNameAndYear(newName, setting.getCurrentYear());
        if(existingWine == null) {
            wine.setName(newName);
            wineService.update(wine);
        } else if(!existingWine.getId().equals(wine.getId())) {
            Alerts.showErrorDialog(
                    "Wein bereits vorhanden",
                    "Der Wein mit dem Namen "+existingWine.getName()
                            +" gibt es in dem Jahr "+existingWine.getYear()+" bereits!");
        }
        loadWinesIntoObservableWineList();
    }

    private void deleteWineAndRefreshList(Wine wine) {

        Optional<ButtonType> deleteWine = Alerts.showYesNoDialog("Wein löschen",
                "Soll der Wein " + wine.getName() + " wirklich gelöscht werden?",
                "Drücke auf Ja um den Löschvorgang fortzusetzen oder auf Nein um den Vorgang abzubrechen");

        if(ButtonUtils.isButton(deleteWine, ButtonType.YES)) {
            List<Entry> allEntriesByWineAndYear = entryService.findAllByWine(wine);
            if(CollectionUtils.isNotEmpty(allEntriesByWineAndYear)) {
                Optional<ButtonType> deleteAllEntriesForWine = Alerts.showYesNoDialog("Achtung!",
                        "Achtung: Es gibt noch Einträge die " + wine.getName() + " zugeordnet sind!",
                        "Sollen alle zugeordneten Einträge unwiderruflich gelöscht werden?");

                if(ButtonUtils.isButton(deleteAllEntriesForWine, ButtonType.YES)) {
                    allEntriesByWineAndYear.stream().map(Entry::getId).forEach(entryService::delete);
                    observableEntryList.clear();
                    wineService.delete(wine.getId());
                    loadWinesIntoObservableWineList();
                }
            } else {
                wineService.delete(wine.getId());
                loadWinesIntoObservableWineList();
            }
        }
    }

    private void loadEntriesIntoTableView(Wine wine, Entry entry) {
        var allEntries = entryService.findAllByWine(wine);
        observableEntryList.clear();
        observableEntryList.addAll(allEntries);
        tblViewMeasures.setItems(observableEntryList);
        tblViewMeasures.getSelectionModel().select(entry);
    }

    private void loadWinesIntoObservableWineList() {
        List<Wine> wines = wineService.findAllByYear(setting.getCurrentYear());
        observableWineList.clear();
        observableWineList.addAll(wines);
    }

    public void addWine(ActionEvent actionEvent) {
        new GenericAddDialog(
                "Neuen Wein hinzufügen",
                "Neuer Wein",
                "Bitte gib hier den Namen des neuen Wein ein:",
                "Name des Weins",
                this::addWineAction
        );
    }

    private void addWineAction(Optional<String> result) {
        String nameOfWine = result.get().trim();

        var wine = wineService.findByNameAndYear(nameOfWine, setting.getCurrentYear());
        System.out.println(wine);
        if(wine == null) {
            wine = new Wine();
            wine.setName(nameOfWine);
            wine.setYear(setting.getCurrentYear());
            wineService.persist(wine);
            loadWinesIntoObservableWineList();
        } else {
            Alerts.showErrorDialog(
                    "Wein bereits vorhanden",
                    "Der Wein mit dem Namen "+wine.getName()+" gibt es in dem Jahr "+wine.getYear()+" bereits!");
        }

    }
}
