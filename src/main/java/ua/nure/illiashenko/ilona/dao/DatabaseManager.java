package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.exceptions.admin.CannotBackUpDatabaseException;
import ua.nure.illiashenko.ilona.exceptions.admin.CannotRestoreDatabaseException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_DB_NAME;
import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_PASSWORD;
import static ua.nure.illiashenko.ilona.constants.DBConnectionData.MY_SQL_USERNAME;

public class DatabaseManager {

    public boolean backupDB() {
        try {
            CodeSource codeSource = DatabaseManager.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            String folderPath = jarDir + "\\backup";
            File file = new File(folderPath);
            file.mkdir();
            String savePath = "\"" + jarDir + "\\backup\\backup.sql\"";
            String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump -u" + MY_SQL_USERNAME + " -p" + MY_SQL_PASSWORD + " --add-drop-database -B " + MY_SQL_DB_NAME + " -r " + savePath;
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            return processComplete == 0;
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new CannotBackUpDatabaseException(e.getMessage());
        }
    }

    public boolean restoreDB() {
        try {
            String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql -u" + MY_SQL_USERNAME + " -p" + MY_SQL_PASSWORD + " -A -D " + MY_SQL_DB_NAME + " -e \"SOURCE target\\backup\\backup.sql\"";
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            return processComplete == 0;
        } catch (Exception e) {
            throw new CannotRestoreDatabaseException(e.getMessage());
        }
    }
}
