package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.DatabaseManager;

public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    private final DatabaseManager databaseManager;

    public AdminService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public boolean backUpDatabase() {
        return databaseManager.backupDB();
    }

    public boolean restoreDatabase() {
        return databaseManager.restoreDB();
    }
}
