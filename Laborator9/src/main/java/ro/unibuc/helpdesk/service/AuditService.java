package ro.unibuc.helpdesk.service;

import ro.unibuc.helpdesk.config.AppLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public final class AuditService {

    private static final Logger logger = AppLogger.getLogger(AuditService.class);
    private static final AuditService instance = new AuditService();

    private AuditService() {
    }

    public static AuditService getInstance() {
        return instance;
    }

    public void log(String actionName) {
        try (FileWriter writer = new FileWriter("audit.csv", true)) {
            writer.write(actionName + "," + LocalDateTime.now() + System.lineSeparator());
        } catch (IOException e) {
            logger.warning("Could not write audit log: " + e.getMessage());
        }
    }
}