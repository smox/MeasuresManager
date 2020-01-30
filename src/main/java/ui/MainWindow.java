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
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import persistence.dao.ContainerAmountMapDao;
import persistence.model.Entry;
import persistence.model.Setting;
import persistence.model.Wine;
import persistence.service.*;
import ui.components.dialogs.AddEntryDialog;
import ui.components.dialogs.Alerts;
import ui.components.dialogs.GenericAddDialog;
import ui.components.listview.WineCellView;
import ui.components.listview.actions.WineDeleteAction;
import ui.components.listview.actions.WineModifyAction;
import ui.components.listview.actions.WineSelectAction;

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
        WineSelectAction wineSelectAction = this::loadWineIntoTableView;

        lvWines.setCellFactory(lvWines -> new WineCellView(wineDeleteAction, wineModifyAction, wineSelectAction));

        tblViewColRealizedAt.setCellFactory(column -> createEntryDateTableCellFactory());
    }

    @FXML
    void onOpenAddEntryDialog(ActionEvent event) throws IOException {

        // TODO Eventuell Mehrfachauswahl möglich?
        var selectedWines = lvWines.getSelectionModel().getSelectedItems();

        if(selectedWines != null && selectedWines.size() == 1) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/components/dialogs/AddEntryDialog.fxml"));
            Parent parent = fxmlLoader.load();
            AddEntryDialog addEntryDialog = fxmlLoader.<AddEntryDialog>getController();
            addEntryDialog.setParent(this);
            addEntryDialog.setAddEntryDialogSuccessAction(() -> loadWineIntoTableView(selectedWines.get(0)));

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
        TableCell<Entry, Date> cell = new TableCell<>() {
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

        return cell;
    }

    private void updateWineAndRefreshList(Wine wine, String newName) {
        wine.setName(newName);
        wineService.update(wine);
        loadWinesIntoObservableWineList();
    }

    private void deleteWineAndRefreshList(Wine wine) {
        wineService.delete(wine.getId());
        loadWinesIntoObservableWineList();
    }

    private void loadWineIntoTableView(Wine wine) {
        var allEntries = entryService.findAllByWineAndYear(wine, setting.getCurrentYear());
        observableEntryList.clear();
        observableEntryList.addAll(allEntries);
        tblViewMeasures.setItems(observableEntryList);
    }

    private void loadWinesIntoObservableWineList() {
        List<Wine> wines = wineService.findAll();
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

        Wine wine = new Wine();
        wine.setName(nameOfWine);
        wineService.persist(wine);

        loadWinesIntoObservableWineList();
    }
}
