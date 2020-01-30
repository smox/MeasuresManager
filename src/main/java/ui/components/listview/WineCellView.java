package ui.components.listview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import persistence.model.Wine;
import ui.components.listview.actions.WineDeleteAction;
import ui.components.listview.actions.WineModifyAction;
import ui.components.listview.actions.WineSelectAction;
import utils.ButtonUtils;

import java.io.IOException;
import java.util.Optional;

public class WineCellView extends ListCell<Wine> {

    FXMLLoader fxmlLoader;

    @FXML
    private GridPane gpWineCell;

    @FXML
    private TextField txtDesignation;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    private WineDeleteAction deleteAction;
    private WineModifyAction modifyAction;
    private WineSelectAction selectAction;

    public WineCellView(WineDeleteAction deleteAction, WineModifyAction modifyAction, WineSelectAction selectAction) {
        initializeFXML();
        initializeButtons();
        this.deleteAction = deleteAction;
        this.modifyAction = modifyAction;
        this.selectAction = selectAction;
    }

    private void initializeButtons() {
        ButtonUtils.addIconToButton(btnEdit, "/assets/edit.png");
        ButtonUtils.addIconToButton(btnDelete, "/assets/delete.png");
    }


    @Override
    protected void updateItem(Wine wine, boolean empty) {
        super.updateItem(wine, empty);
        setText(null);

        if(empty) {
            setGraphic(null);
        } else {

            txtDesignation.setText(wine.getName());

            addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> selectAction.selectWine(wine));

            btnEdit.setOnAction(actionEvent -> {
                if(!txtDesignation.isDisabled()) {
                    updateWine(wine);
                } else {
                    txtDesignation.setStyle("-fx-opacity: 1.0;");
                }
                txtDesignation.setDisable(!txtDesignation.isDisabled());
            });

            txtDesignation.setOnAction(actionEvent -> {
                updateWine(wine);
                txtDesignation.setDisable(!txtDesignation.isDisabled());
            });

            btnDelete.setOnAction(actionEvent -> deleteAction.deleteWine(wine));

            setGraphic(gpWineCell);
        }
    }

    private void updateWine(Wine wine) {
        txtDesignation.setStyle("-fx-opacity: 0.8;");
        modifyAction.modifyWine(wine, txtDesignation.getText());
    }

    private void initializeFXML() {
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/ui/components/listview/WineCellView.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException ioexception) {
            // TODO logging
            System.err.println("IO Exception...");
            ioexception.printStackTrace();
        }
    }
}
