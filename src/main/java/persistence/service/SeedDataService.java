package persistence.service;

import persistence.model.Measure;
import persistence.model.Setting;

import java.util.Calendar;
import java.util.List;

public class SeedDataService {

    public static MeasureService measureService = new MeasureService();
    public static SettingService settingService = new SettingService();

    public static void createSeedData() {
        List.of("Schwefelung", "Anreicherung", "Schönung").forEach(SeedDataService::createMeasure);
        createInitialSetting();

    }

    private static void createInitialSetting() {

        boolean isAnyUndeletedSetting = settingService.findAll()
                .stream()
                .anyMatch(setting -> setting.getDeletedAt() == null);

        if(!isAnyUndeletedSetting) {

            Setting setting = new Setting();
            setting.setCompanyNumber("1563157");
            setting.setCurrentYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));

            settingService.persist(setting);
        } else {
            // TODO Add logger
            System.out.println("Initial Setting exists! No action necessary!");
        }
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
