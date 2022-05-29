package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.exceptions.admin.CannotBackUpDatabaseException;
import ua.nure.illiashenko.ilona.exceptions.admin.CannotRestoreDatabaseException;

import java.io.File;
import java.io.IOException;

import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_DB_NAME;
import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_PASSWORD;
import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_USERNAME;

public class DatabaseManager {

    private final String backupPath;
    private final String mySqlBinPath;

    public DatabaseManager(String backupPath, String mySqlBinPath) {
        this.backupPath = backupPath;
        this.mySqlBinPath = mySqlBinPath;
    }

    public boolean backupDB() {
        try {
            File file = new File(backupPath);
            file.mkdir();
            String savePath = "\"" + backupPath + "\\backup.sql\"";
            String executeCmd = mySqlBinPath + "\\mysqldump -u" + MY_SQL_USERNAME + " -p" + MY_SQL_PASSWORD + " --add-drop-database -B " + MY_SQL_DB_NAME + " -r " + savePath;
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            return processComplete == 0;
        } catch (IOException | InterruptedException e) {
            throw new CannotBackUpDatabaseException(e.getMessage());
        }
    }

    public boolean restoreDB() {
        try {
            String executeCmd = mySqlBinPath + "\\mysql -u" + MY_SQL_USERNAME + " -p" + MY_SQL_PASSWORD + " -A -D " + MY_SQL_DB_NAME + " -e \"SOURCE backup\\backup.sql\"";
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            return processComplete == 0;
        } catch (Exception e) {
            throw new CannotRestoreDatabaseException(e.getMessage());
        }
    }
}
