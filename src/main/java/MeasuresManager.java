import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import persistence.model.Setting;
import persistence.service.SeedDataService;
import persistence.service.SettingService;

import java.io.IOException;
import java.util.Locale;


public class MeasuresManager extends Application  {

    public static SettingService settingService = new SettingService();
    public static Setting setting;

    public static void main(String[] args) {
        Locale.setDefault(Locale.GERMANY);
        createSeedData();
        setting = settingService.findCurrent();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ui/MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Maßnahmenverwaltung - Winzerhof Schwanzelberger - "+setting.getCurrentYear());
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
