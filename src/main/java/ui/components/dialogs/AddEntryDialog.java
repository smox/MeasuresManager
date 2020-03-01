package ui.components.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import persistence.model.ContainerAmountMap;
import persistence.model.Entry;
import persistence.model.Measure;
import persistence.model.Wine;
import ui.MainWindow;
import ui.components.dialogs.actions.IAddOrEditEntryDialogSuccessAction;
import ui.components.spinner.CustomIntegerSpinnerValueFactory;
import utils.ButtonUtils;
import utils.CollectionUtils;
import utils.StringUtils;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddEntryDialog extends Dialog<Entry> implements Initializable {

    MainWindow mainWindow;

    private Wine wine;

    private String year;

    private IAddOrEditEntryDialogSuccessAction addEntryDialogSuccessAction;

    @FXML
    private DatePicker dpRealizedAt;

    @FXML
    private TextField tfContainer;

    @FXML
    private Spinner<Integer> spinAmount;

    @FXML
    private CheckListView<Measure> cbMeasures;

    @FXML
    private Button btnAddMeasure;

    @FXML
    private Button btnRemoveMeasure;

    final ObservableList<Measure> observableMeasureList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadMeasures();
        addButtonIcons();
        addButtonActions();
        addContainerListener();

        dpRealizedAt.setValue(LocalDate.now());
        spinAmount.setValueFactory(new CustomIntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));
        cbMeasures.getItems().addAll(observableMeasureList);
    }

    private void addContainerListener() {
        tfContainer.focusedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println(newVal ? "Focused" : "Unfocused");
            if(!newVal) {
                Integer amountByContainer = getAmountByContainer(tfContainer.getText());
                if(amountByContainer != null) {
                    spinAmount.getEditor().setText(String.valueOf(amountByContainer));
                    spinAmount.commitValue();
                }
            }
        });
    }

    private Integer getAmountByContainer(String container) {
        if(StringUtils.isNotBlank(container)) {
            System.out.println("getAmountByContainer: "+container);
            var containerAmountMap = MainWindow.containerAmountMapService.findById(container);
            if(containerAmountMap != null) {
                return containerAmountMap.getAmount();
            }
        }

        return null;
    }

    private void addButtonActions() {
        btnAddMeasure.setOnAction(actionEvent -> {
            new GenericAddDialog(
                    "Maßnahme hinzufügen",
                    "Neue Maßnahme hinzufügen",
                    "Bitte geben Sie die Bezeichnung der neuen Maßnahme ein: ",
                    "Maßnahmenbezeichnung",
                    this::addMeasure
            );
        });

        btnRemoveMeasure.setOnAction(actionEvent -> {
            ObservableList<Measure> checkedItems = cbMeasures.getCheckModel().getCheckedItems();
            if(checkedItems != null && checkedItems.size() > 0) {

                checkedItems.stream().map(measure -> {
                    cbMeasures.getCheckModel().clearCheck(measure);
                    return measure.getId();
                }).forEach(MainWindow.measureService::delete);

                refreshMeasures();

            } else {
                Alerts.showErrorDialog(
                        "Fehler beim Löschen der Maßnahme",
                        "Es muss mindestens eine Maßnahme ausgewählt werden!");
            }

        });
    }

    private void refreshMeasures() {
        observableMeasureList.clear();
        List<Measure> allMeasures = MainWindow.measureService.findAll();
        observableMeasureList.addAll(allMeasures != null ? allMeasures : new ArrayList<>());
        cbMeasures.getItems().clear();
        cbMeasures.getItems().addAll(observableMeasureList);
    }

    private void addMeasure(Optional<String> result) {

        String nameOfMeasure = result.get().trim();

        Measure measure = new Measure();
        measure.setName(nameOfMeasure);
        var measureByName = MainWindow.measureService.findByName(nameOfMeasure);
        if(measureByName == null) {
            MainWindow.measureService.persist(measure);
            refreshMeasures();
        } else {
            Alerts.showErrorDialog(
                    "Fehler beim Hinzufügen der Maßnahme",
                    "Diese Maßnahme existiert bereits!");
        }
    }



    private void addButtonIcons() {
        ButtonUtils.addIconToButton(btnAddMeasure, "/assets/add.png");
        ButtonUtils.addIconToButton(btnRemoveMeasure, "/assets/delete.png");
    }

    private void loadMeasures() {
        observableMeasureList.addAll(MainWindow.measureService.findAll());
    }

    public void setParent(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void onSuccess(ActionEvent actionEvent) {

        if(isInputValid(dpRealizedAt, tfContainer, spinAmount.getValue())){

            LocalDate realizedAt = dpRealizedAt.getValue();
            String container = tfContainer.getText();
            Integer amount = spinAmount.getValue();
            ObservableList<Measure> checkedMeasures = cbMeasures.getCheckModel().getCheckedItems();

            //Save ContainerAmountMap
            Integer amountByContainerAmountMap = getAmountByContainer(container);
            if(amountByContainerAmountMap == null) {
                MainWindow.containerAmountMapService.persist(new ContainerAmountMap(container, amount));
            } else if (amountByContainerAmountMap.intValue() != amount.intValue()) {
                MainWindow.containerAmountMapService.update(new ContainerAmountMap(container, amount));
            }

            // Save Entry
            Entry entry = new Entry();
            entry.setRealizedAt(Date.valueOf(realizedAt));
            entry.setContainer(container);
            entry.setWine(wine);
            entry.setAmount(amount);

            if(CollectionUtils.isNotEmpty(checkedMeasures)) {
                entry.setMeasure(checkedMeasures.get(0)); // FIXME replace this after UI change to TreeList
            }

            MainWindow.entryService.persist(entry);

            addEntryDialogSuccessAction.onSuccess(entry);
            closeDialog(actionEvent);
        }
    }

    private boolean isInputValid(DatePicker dpRealizedAt, TextField tfContainer, int amount) {

        try {
            dpRealizedAt.getValue();
        } catch (Exception e) {
            System.out.println("Fehler beim parsen des Datums");
            e.printStackTrace();
            return false;
        }

        if(StringUtils.isBlank(tfContainer.getText())){
            Alerts.showErrorDialog(
                    "Fehler bei der Angabe des Behälters",
                    "Es muss ein Behälter angegeben werden");
            return false;
        }

        if(amount <= 0) {
            Alerts.showErrorDialog(
                    "Falsche Angabe der Menge",
                    "Die Menge muss mindestens 1 Liter betragen");
            return false;
        }

        return true;
    }

    public void onCancel(ActionEvent actionEvent) {
        closeDialog(actionEvent);
    }

    private void closeDialog(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setAddEntryDialogSuccessAction(IAddOrEditEntryDialogSuccessAction addEntryDialogSuccessAction) {
        this.addEntryDialogSuccessAction = addEntryDialogSuccessAction;
    }
}
