package ui.component.listview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import persistence.model.Wine;

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

    public WineCellView() {
        initializeFXML();
        initializeButtons();



    }

    private void initializeButtons() {
        addIconToButton(btnEdit, "/assets/edit.png");
        addIconToButton(btnDelete, "/assets/delete.png");
    }

    private void addIconToButton(Button btnDelete, String pathToIcon) {
        Image imageDelete = new Image(getClass().getResourceAsStream(pathToIcon));
        ImageView imageView = new ImageView(imageDelete);
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        btnDelete.setGraphic(imageView);
    }


    @Override
    protected void updateItem(Wine wine, boolean empty) {
        super.updateItem(wine, empty);
        setText(null);

        if(empty) {
            setGraphic(null);
        } else {

            txtDesignation.setText(wine.getName());

            btnEdit.setOnAction(actionEvent -> {
                if(!txtDesignation.isDisabled()) {
                    txtDesignation.setStyle("-fx-opacity: 0.8;");
                    System.out.println("TODO: Save changes...");
                } else {
                    txtDesignation.setStyle("-fx-opacity: 1.0;");
                }
                txtDesignation.setDisable(!txtDesignation.isDisabled());
            });

            btnDelete.setOnAction(actionEvent -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Wein löschen");
                alert.setHeaderText("Bist du dir sicher, dass du den Wein "+wine.getName()+" löschen willst?");
                alert.setContentText("Drücke auf OK um den Wein zu löschen oder auf" +
                        "Abbrechen um den Vorgang abzubrechen");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.out.println("TODO: Wein löschen...");
                }
            });

            setGraphic(gpWineCell);
        }
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
