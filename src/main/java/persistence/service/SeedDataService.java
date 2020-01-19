package persistence.service;

import persistence.model.Measure;

import java.util.List;

public class SeedDataService {

    public static MeasureService measureService = new MeasureService();

    public static void createSeedData() {
        List.of("Schwefelung", "Anreicherung", "Schönung").forEach(SeedDataService::createMeasure);
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
