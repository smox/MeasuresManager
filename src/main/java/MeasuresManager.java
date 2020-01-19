import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.service.SeedDataService;

import java.io.IOException;

public class MeasuresManager extends Application  {

    public static void main(String[] args) {
        createSeedData();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HelloFX.fxml"));
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    private static void createSeedData() {
        SeedDataService.createSeedData();
    }
}
