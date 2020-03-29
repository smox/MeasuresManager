package ui.components.dialogs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistence.model.ContainerAmountMap;
import persistence.model.Entry;
import persistence.model.Measure;
import persistence.model.Wine;
import ui.MainWindow;
import ui.components.dialogs.actions.IEntryDialogSuccessAction;
import ui.components.spinner.CustomIntegerSpinnerValueFactory;
import utils.ButtonUtils;
import utils.CollectionUtils;
import utils.StringUtils;

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

    private IEntryDialogSuccessAction entryDialogSuccessAction;

    @FXML
    private DatePicker dpRealizedAt;

    @FXML
    private TextField tfContainer;

    @FXML
    private Spinner<Integer> spinAmount;

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
        addContainerListener();

        dpRealizedAt.setValue(LocalDate.now());
        spinAmount.setValueFactory(new CustomIntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));

        tvMeasures.setRoot(createRootMeasureItemByMeasures());
        tvMeasures.setShowRoot(false);


    }

    public void setSpinAmount(int amount) {
        spinAmount.getEditor().setText(String.valueOf(amount));
        spinAmount.commitValue();
    }

    public void setContainer(String container) {
        tfContainer.setText(container);
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

            dpRealizedAt.setValue(entry.getRealizedAt() != null ? entry.getRealizedAt().toLocalDate() : LocalDate.now());
            tfContainer.setText(entry.getContainer());
            spinAmount.getEditor().setText(entry.getAmount() != null ? String.valueOf(entry.getAmount()) : "0");
            spinAmount.commitValue();

            // Select Item by entry
            MultipleSelectionModel<TreeItem<Measure>> selectionModel = tvMeasures.getSelectionModel();
            int row = 0;
            TreeItem<Measure> rootNode = tvMeasures.getRoot();
            row = getRowByTreeItem(rootNode, entry.getMeasure(), row);
            selectionModel.select(row);

        } else {
            // TODO logger.warm
            System.err.println("Cannot initialize fields when entry is not given or null");
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

    public void setParent(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void onSuccess(ActionEvent actionEvent) {

        if(isInputValid(dpRealizedAt, tfContainer, spinAmount.getValue())){

            Date realizedAt = Date.valueOf(dpRealizedAt.getValue());
            String container = tfContainer.getText();
            Integer amount = spinAmount.getValue();
            TreeItem<Measure> selectedMeasure = tvMeasures.getSelectionModel().getSelectedItem();

            //Save ContainerAmountMap
            Integer amountByContainerAmountMap = getAmountByContainer(container);
            if(amountByContainerAmountMap == null) {
                MainWindow.containerAmountMapService.persist(new ContainerAmountMap(container, amount));
            } else if (amountByContainerAmountMap.intValue() != amount.intValue()) {
                MainWindow.containerAmountMapService.update(new ContainerAmountMap(container, amount));
            }


            boolean newEntry = false;

            if(entry == null) {
                // Save Entry
                entry = new Entry();
                entry.setCreatedAt(new Date(new java.util.Date().getTime()));
                entry.setWine(wine);
                newEntry = true;
            }

            entry.setRealizedAt(realizedAt);
            entry.setContainer(container);
            entry.setAmount(amount);
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

    public void setEntryDialogSuccessAction(IEntryDialogSuccessAction entryDialogSuccessAction) {
        this.entryDialogSuccessAction = entryDialogSuccessAction;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
