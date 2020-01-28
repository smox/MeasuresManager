package ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import persistence.model.Entry;
import persistence.model.Measure;
import persistence.model.Setting;
import persistence.model.Wine;
import persistence.service.EntryService;
import persistence.service.MeasureService;
import persistence.service.SettingService;
import persistence.service.WineService;
import ui.component.listview.WineCellView;
import ui.component.listview.actions.WineDeleteAction;
import ui.component.listview.actions.WineModifyAction;
import ui.component.listview.actions.WineSelectAction;

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

        TextInputDialog dialog = new TextInputDialog("Name des Weins");
        dialog.setTitle("Neuen Wein hinzufügen");
        dialog.setHeaderText("Neuer Wein");
        dialog.setContentText("Bitte gib hier den Namen des neuen Wein ein:");

        TextField txtWineName = dialog.getEditor();
        txtWineName.textProperty().addListener((observableValue, oldValue, newValue) -> {
            boolean isTextBlank = txtWineName.getText().trim().isBlank();
            dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(isTextBlank);
        });

        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(txtWineName,
                Validator.createEmptyValidator("Es muss ein Weinname eingegeben werden"));

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {

            String nameOfWine = result.get().trim();

            Wine wine = new Wine();
            wine.setName(nameOfWine);
            wineService.persist(wine);

            loadWinesIntoObservableWineList();
        });
    }
}
