package ui.components.listview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import persistence.model.Wine;
import ui.components.dialogs.Alerts;
import ui.components.listview.actions.WineDeleteAction;
import ui.components.listview.actions.WineModifyAction;
import ui.components.listview.actions.WineSelectAction;
import utils.ButtonUtils;
import utils.StringUtils;

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

            addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                switch (mouseEvent.getClickCount()){
                    case 1:
                        selectAction.selectWine(wine, null);
                        break;
                    case 2:
                        activateEditMode();
                        break;
                }
            });

            addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
                if(keyEvent.getCode() == KeyCode.ESCAPE) {
                    txtDesignation.setText(wine.getName());
                    deactivateEditMode();
                }
            });

            btnEdit.setOnAction(actionEvent -> {
                if(!txtDesignation.isDisabled()) {
                    var success = modifyAction.modifyWine(wine, txtDesignation.getText());
                    if(success) {
                        deactivateEditMode();
                    }
                } else {
                    activateEditMode();
                }
            });

            txtDesignation.setOnAction(actionEvent -> modifyAction.modifyWine(wine, txtDesignation.getText()));
            btnDelete.setOnAction(actionEvent -> deleteAction.deleteWine(wine));
            setGraphic(gpWineCell);
        }
    }

    private void deactivateEditMode() {
        txtDesignation.setStyle("-fx-opacity: 0.8;");
        txtDesignation.setDisable(true);
    }

    private void activateEditMode() {
        txtDesignation.setStyle("-fx-opacity: 1.0;");
        txtDesignation.setDisable(false);
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
