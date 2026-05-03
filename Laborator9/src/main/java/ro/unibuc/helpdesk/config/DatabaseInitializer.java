package ro.unibuc.helpdesk.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class DatabaseInitializer {

    private static final Logger logger = AppLogger.getLogger(DatabaseInitializer.class);

    private DatabaseInitializer() {
    }

    public static void initializeDatabase() {
        logger.info("Starting database initialization...");

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = new BufferedReader(
                    new InputStreamReader(
                            Objects.requireNonNull(DatabaseInitializer.class.getClassLoader()
                                    .getResourceAsStream("schema.sql"))
                    )
            ).lines().collect(Collectors.joining("\n"));

            String[] statements = sql.split(";");

            for (String statementSql : statements) {
                if (!statementSql.isBlank()) {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute(statementSql);
                    }
                }
            }

            logger.info("Database initialized successfully.");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Database initialization failed", e);
        }
    }
}