import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import persistence.service.SeedDataService;

import java.io.IOException;
import java.util.Objects;

public class MeasuresManager extends Application  {

    public static void main(String[] args) {
        createSeedData();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ui/MainWindow.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Maßnahmenverwaltung - Winzerhof Schwanzelberger");
        stage.setScene(scene);
        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        stage.show();
    }

    private static void createSeedData() {
        SeedDataService.createSeedData();
    }

    private void closeWindowEvent(WindowEvent event) {
        //TODO Backup current database
        System.exit(0);
    }
}
