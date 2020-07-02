package ui.factories;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import persistence.model.Container;
import ui.MainWindow;

public class CheckBoxTableCellFactory<S, T> implements Callback<TableColumn<S, Boolean>, TableCell<S, Boolean>> {

    public TableCell<S, Boolean> call(TableColumn<S, Boolean> param) {

        CheckBoxTableCell<S,Boolean> checkBoxTableCell = new CheckBoxTableCell<>();

        final BooleanProperty selected = new SimpleBooleanProperty();
        checkBoxTableCell.setSelectedStateCallback(index -> selected);

        selected.addListener((obs, wasSelected, isSelected) -> {
            Container container = (Container) checkBoxTableCell.getTableRow().getItem();
            container.setAlwaysFullContainer(isSelected);
            MainWindow.containerService.update(container);
        });

        return checkBoxTableCell;
    }

}