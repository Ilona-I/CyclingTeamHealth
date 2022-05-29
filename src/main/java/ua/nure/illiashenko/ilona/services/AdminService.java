package ua.nure.illiashenko.ilona.services;

import ua.nure.illiashenko.ilona.dao.DatabaseManager;

public class AdminService {

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
