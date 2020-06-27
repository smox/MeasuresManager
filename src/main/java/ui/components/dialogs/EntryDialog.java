package ui.components.dialogs;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;
import persistence.model.Container;
import persistence.model.Entry;
import persistence.model.Measure;
import persistence.model.Wine;
import ui.MainWindow;
import ui.components.dialogs.actions.IEntryDialogSuccessAction;
import ui.components.dialogs.actions.IGenericReference;
import utils.ButtonUtils;
import utils.CollectionUtils;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EntryDialog extends Dialog<Entry> implements Initializable {


    private Entry entry;

    MainWindow mainWindow;

    private Wine wine;

    private boolean initializeOnlyContainer = false;

    private IEntryDialogSuccessAction entryDialogSuccessAction;

    @FXML
    private DatePicker dpRealizedAt;

    @FXML
    private SearchableComboBox<Container> scbSelectContainer;

    @FXML
    private TreeView<Measure> tvMeasures;

    @FXML
    private Button btnAddMeasure;

    @FXML
    private Button btnRemoveMeasure;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addButtonIcons();
        addButtonActions();

        dpRealizedAt.setValue(LocalDate.now());
        scbSelectContainer.setItems(FXCollections.observableList(MainWindow.containerService.findAll()));

        tvMeasures.setRoot(createRootMeasureItemByMeasures());
        tvMeasures.setShowRoot(false);
    }

    private TreeItem<Measure> createRootMeasureItemByMeasures() {
        List<Measure> allMeasures = MainWindow.measureService.findAll();
        TreeItem<Measure> rootItem = new TreeItem<>();

        for(Measure measure : allMeasures) {
            if(measure.getParent() == null) { // is root Item
                TreeItem<Measure> treeItem = new TreeItem<>(measure);
                List<TreeItem<Measure>> childItems = measure.getChildren().stream().map(TreeItem::new).collect(Collectors.toList());
                treeItem.getChildren().addAll(childItems);
                rootItem.getChildren().add(treeItem);
            }
        }

        return rootItem;
    }

    public void initializeFields() {
        if(entry != null) {

            scbSelectContainer.getSelectionModel().select(entry.getContainer());

            if(!initializeOnlyContainer) {

                dpRealizedAt.setValue(entry.getRealizedAt() != null ? entry.getRealizedAt().toLocalDate() : LocalDate.now());
                scbSelectContainer.getSelectionModel().select(entry.getContainer());

                // Select Item by entry
                MultipleSelectionModel<TreeItem<Measure>> selectionModel = tvMeasures.getSelectionModel();
                int row = 0;
                TreeItem<Measure> rootNode = tvMeasures.getRoot();
                row = getRowByTreeItem(rootNode, entry.getMeasure(), row);
                selectionModel.select(row);
            }
        }
    }

    /**
     * Recursiv search function for TreeItems.
     * It iterates over every item recursively, open all Nodes with childrens, check every child item and
     * closes the nodes if the searched item is not within the the child items
     * @param treeNode the rootNode where the search should start
     * @param measureToSearch the measure to find
     * @param index the starting index (zero in the most cases)
     * @return the index of the searched treeItem.
     */
    private int getRowByTreeItem(TreeItem<Measure> treeNode, Measure measureToSearch, int index) {

        if(index != 0) {
            index++;
        }

        for(TreeItem<Measure> node : treeNode.getChildren()) {
            if(node.getChildren().size() > 0) { // has children?

                if(node.getValue().equals(measureToSearch)) {
                    return index;
                }

                node.setExpanded(true);
                // call children
                int indexOld = index;
                index = getRowByTreeItem(node, measureToSearch, indexOld);

                if(indexOld+1 == index) {
                    return index;
                } else {
                    node.setExpanded(false);
                    index--;
                }
            } else {
                Measure measure = node.getValue();
                if(measure.equals(measureToSearch)) {
                    return index; // measure found -> break up
                } else {
                    index++; // continue search
                }
            }
        }
        return index;
    }

    private void addButtonActions() {
        btnAddMeasure.setOnAction(actionEvent -> new GenericAddDialog<>(
                "Maßnahme hinzufügen",
                "Neue Maßnahme hinzufügen",
                "Bitte geben Sie die Bezeichnung der neuen Maßnahme ein: ",
                "Maßnahmenbezeichnung",
                this::addMeasure,
                () -> tvMeasures.getSelectionModel().getSelectedItem().getValue()
        ));

        btnRemoveMeasure.setOnAction(actionEvent -> {
            TreeItem<Measure> selectedItem = tvMeasures.getSelectionModel().getSelectedItem();

            if(selectedItem != null) {

                Measure measureToDelete = selectedItem.getValue();
                Optional<ButtonType> deleteMeasure = Alerts.showYesNoDialog("Bestätigung Löschvorgang",
                        "Möchten Sie die Maßnahme " + measureToDelete.getName() + " wirklich löschen?",
                                "Der Vorgang kann nicht Rückgängig gemacht werden.");

                if(ButtonUtils.isButton(deleteMeasure, ButtonType.YES)) {
                    if(CollectionUtils.isNotEmpty(measureToDelete.getEntries())) {
                        Alerts.showErrorDialog("Fehler beim Löschen der Maßnahme",
                                "Maßnahme konnte nicht gelöscht werden, da Maßnahme einträgen zugeordnet ist.\n" +
                                        "Bitte löschen sie zuerst alle Vorkommnisse der Maßnahme, bevor Sie die Maßnahme selbst löschen");
                    } else if (measureHasChildren(measureToDelete)) {
                        Alerts.showErrorDialog("Fehler beim Löschen der Maßnahme",
                                "Maßnahme konnte nicht gelöscht werden, da die Maßnahme noch weitere Maßnahmen" +
                                        " (Kindobjekte) zugeordnet hat");
                    } else {
                        MainWindow.measureService.delete(measureToDelete.getId());
                        refreshMeasures();
                    }
                }
            } else {
                Alerts.showErrorDialog(
                        "Fehler beim Löschen der Maßnahme",
                        "Es muss mindestens eine Maßnahme ausgewählt werden!");
            }

        });
    }

    private boolean measureHasChildren(Measure measureToDelete) {
        return CollectionUtils.isNotEmpty(measureToDelete.getChildren());
    }

    private void refreshMeasures() {
        tvMeasures.setRoot(createRootMeasureItemByMeasures());
    }

    private void addMeasure(String nameOfMeasure, IGenericReference<Measure> parentReference) {
        Measure measure = new Measure();
        measure.setName(nameOfMeasure);
        measure.setParent(parentReference.getReference());
        var measureByName = MainWindow.measureService.findByName(nameOfMeasure);
        if(measureByName == null) {
            MainWindow.measureService.persist(measure);
            refreshMeasures();
        } else {
            Alerts.showErrorDialog("Fehler beim Hinzufügen der Maßnahme","Diese Maßnahme existiert bereits!");
        }
    }



    private void addButtonIcons() {
        ButtonUtils.addIconToButton(btnAddMeasure, "/assets/add.png");
        ButtonUtils.addIconToButton(btnRemoveMeasure, "/assets/delete.png");
    }

    public void setParent(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void onSuccess(ActionEvent actionEvent) {

        if(isInputValid(dpRealizedAt, scbSelectContainer)){

            Date realizedAt = Date.valueOf(dpRealizedAt.getValue());
            Container selectedItem = scbSelectContainer.getSelectionModel().getSelectedItem();
            TreeItem<Measure> selectedMeasure = tvMeasures.getSelectionModel().getSelectedItem();

            boolean newEntry = false;

            if(entry == null) {
                // Save Entry
                entry = new Entry();
                entry.setCreatedAt(new Date(new java.util.Date().getTime()));
                entry.setWine(wine);
                newEntry = true;
            }

            entry.setRealizedAt(realizedAt);
            entry.setContainer(selectedItem);
            entry.setMeasure(selectedMeasure.getValue());

            if(newEntry) {
                MainWindow.entryService.persist(entry);
            } else {
                MainWindow.entryService.update(entry);
            }

            entryDialogSuccessAction.onSuccess(entry);
            closeDialog(actionEvent);
        }
    }

    private boolean isInputValid(DatePicker dpRealizedAt, SearchableComboBox<Container> scbSelectContainer) {

        try {
            dpRealizedAt.getValue();
        } catch (Exception e) {
            System.out.println("Fehler beim parsen des Datums");
            e.printStackTrace();
            return false;
        }

        if(scbSelectContainer.getSelectionModel().getSelectedItem() == null){
            Alerts.showErrorDialog(
                    "Fehler bei der Angabe des Behälters",
                    "Es muss ein Behälter angegeben werden");
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

    public void setEntryDialogSuccessAction(IEntryDialogSuccessAction entryDialogSuccessAction) {
        this.entryDialogSuccessAction = entryDialogSuccessAction;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public boolean isInitializeOnlyContainer() {
        return initializeOnlyContainer;
    }

    public void setInitializeOnlyContainer(boolean initializeOnlyContainer) {
        this.initializeOnlyContainer = initializeOnlyContainer;
    }
}
