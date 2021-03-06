import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.flywaydb.core.Flyway;
import persistence.model.Setting;
import persistence.service.MigrationService;
import persistence.service.SettingService;

import java.io.IOException;
import java.util.Locale;


public class MeasuresManager extends Application  {

    public static MigrationService migrationService = new MigrationService();
    public static SettingService settingService = new SettingService();
    public static Setting setting;

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.GERMANY);
        setting = settingService.findCurrent();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ui/MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Maßnahmenverwaltung - "+setting.getCompanyName()+" ("+setting.getCompanyNumber()+")"+" - "+setting.getCurrentYear());
        stage.setScene(scene);
        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.show();
    }

    private void closeWindowEvent(WindowEvent event) {
        //TODO Backup current database
        System.exit(0);
    }
}
