package persistence.service;

import org.flywaydb.core.Flyway;

public class MigrationService {
    public MigrationService() {
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:./database", "sa", "").load();
        flyway.migrate();
    }
}
