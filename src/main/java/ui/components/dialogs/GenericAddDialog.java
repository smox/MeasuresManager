package ui.components.dialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import ui.components.dialogs.actions.IGenericAddAction;
import ui.components.dialogs.actions.IGenericReference;

import java.util.Optional;

public class GenericAddDialog<T> extends TextInputDialog {

    public GenericAddDialog(
            String title, String headerText, String contentText, String defaultValue, IGenericAddAction<T> addAction, IGenericReference<T> reference) {

        this.getEditor().setText(defaultValue);
        this.setTitle(title);
        this.setHeaderText(headerText);
        this.setContentText(contentText);

        TextField txtDesignation = this.getEditor();
        txtDesignation.textProperty().addListener((observableValue, oldValue, newValue) -> {
            boolean isTextBlank = txtDesignation.getText().trim().isBlank();
            this.getDialogPane().lookupButton(ButtonType.OK).setDisable(isTextBlank);
        });

        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(txtDesignation,
                Validator.createEmptyValidator("Es muss eine Bezeichnung eingegeben werden!"));

        Optional<String> result = this.showAndWait();
        result.ifPresent(text -> addAction.genericAddAction(text, reference));
    }
}
