package ui.components.dialogs;

import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import ui.components.dialogs.actions.IGenericAddAction;

import java.util.Optional;

public class GenericAddDialog extends TextInputDialog {

    public GenericAddDialog(
            String title, String headerText, String contentText, String defaultValue, IGenericAddAction addAction) {

        this.getEditor().setText(defaultValue);
        this.setTitle(title);
        this.setHeaderText(headerText);
        this.setContentText(contentText);

        TextField txtWineName = this.getEditor();
        txtWineName.textProperty().addListener((observableValue, oldValue, newValue) -> {
            boolean isTextBlank = txtWineName.getText().trim().isBlank();
            this.getDialogPane().lookupButton(ButtonType.OK).setDisable(isTextBlank);
        });

        ValidationSupport validationSupport = new ValidationSupport();
        validationSupport.registerValidator(txtWineName,
                Validator.createEmptyValidator("Es muss ein Weinname eingegeben werden"));

        Optional<String> result = this.showAndWait();
        result.ifPresent(inputText -> addAction.genericAddAction(result));
    }

}
