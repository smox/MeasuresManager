package persistence.service;

import persistence.model.Measure;
import persistence.model.Setting;
import utils.FileUtils;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static utils.ExceptionUtils.setOrThrow;

public class SeedDataService {

    public static MeasureService measureService = new MeasureService();
    public static SettingService settingService = new SettingService();

    public static void createSeedData() throws IOException {
        createInitialSetting();
        List.of("Schwefelung", "Anreicherung", "Schönung").forEach(SeedDataService::createMeasure);

    }

    private static void createInitialSetting() throws IOException {

        boolean isAnyUndeletedSetting = settingService.findAll()
                .stream()
                .anyMatch(setting -> setting.getDeletedAt() == null);

        if(!isAnyUndeletedSetting) {

            String homeDirPath = System.getProperty("user.home")+"/.MeasuresManager/";
            Setting setting = new Setting();

            Map<String, String> initialSettingsMap
                    = FileUtils.readConfigFile(homeDirPath + "initialSettings.cfg");

            setting.setCompanyNumber(set(homeDirPath, initialSettingsMap, Setting.COMPANY_NUMBER));
            setting.setCompanyName(set(homeDirPath, initialSettingsMap, Setting.COMPANY_NAME));

            setting.setHomeDirectory(homeDirPath);
            setting.setCurrentYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

            settingService.persist(setting);
        } else {
            // TODO Add logger
            System.out.println("Initial Setting exists! No action necessary!");
        }
    }

    private static String set(String homeDirPath, Map<String, String> initialSettingsMap, String property) {
        return setOrThrow(initialSettingsMap.get(property),
                "Wert nicht gesetzt! Bitte setze den Wert "+property+" in "+
                        homeDirPath + "initialSettings.cfg");
    }

    private static void createMeasure(String measureName) {
        Measure measure = measureService.findByName(measureName);
        if(measure == null) {
            measure = new Measure();
            measure.setName(measureName);
            measureService.persist(measure);
        }
    }

}
