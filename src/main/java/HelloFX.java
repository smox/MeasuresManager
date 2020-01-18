import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloFX {

    @FXML
    private Label lblHello;


    public void sayHello(ActionEvent actionEvent) {
        if(lblHello.getText().equals("Hello JavaFX!")) {
            lblHello.setText("Hello World!!");
        } else {
            lblHello.setText("Hello JavaFX!");
        }

    }
}
